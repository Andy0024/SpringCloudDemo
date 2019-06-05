package com.object.serviceb;

import com.rabbitmq.client.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceBApplicationTests {

    @Test
    public void contextLoads() {
    }

    /**
     * 测试Rabbitmq 的消费端
     */
    @Test
    public void testRabbitConsumer() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        //设置ip 和端口
        factory.setHost("192.168.41.130");
        factory.setPort(5672);
        factory.setUsername("admin");
        factory.setPassword("admin");
        //创建连接，获取通道
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare("hello-queue1", true, false, false, null);
        //定义消费方法
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel) {
            /**
             * 消费者接收消息调用此方法
             * @param consumerTag  消费者的标签，在channel.basicConsume()去指定
             * @param envelope  消息包的内容，可从中获取消息id，消息routingkey，交换机，消息和重传标志
             * (收到消息失败后是否需要重新发送)
             * @param properties
             * @param body
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body)
                    throws IOException {
                //交换机
                String exchange = envelope.getExchange();
                //路由key
                String routingKey = envelope.getRoutingKey();
                //消息id
                long deliveryTag = envelope.getDeliveryTag();
                //消息内容
                String msg = new String(body, "utf-8");
                System.out.println("receive  message.." + msg);
            }
        };
        channel.basicConsume("hello-queue2", true, defaultConsumer);
    }
}


