package com.object.servicea.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Copyright: Copyright (c) 2019 Asiainfo
 *
 * @ClassName: com.object.servicea.rabbitmq.HelloSender
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: xianglt
 * @date: 2019/6/3 15:47
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2019/6/3      xianglt          v1.0.0               修改原因
 */
@Component
public class HelloSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init(){
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            System.out.println("消息发送成功"+correlationData);
        } else {
            System.out.println("消息发送失败"+cause);
        }

    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println(message.getMessageProperties().getCorrelationId()+"发送失败");

    }

    /**
     * 发送消息不需要实现任何接口，供外部调用
     */
    public void send(String msg) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        System.out.println("开始发送消息 : " + msg.toLowerCase());
        Object topicExchange = rabbitTemplate.convertSendAndReceive("topicExchange", "key.1", msg, correlationId);
        Object topicExchange2 = rabbitTemplate.convertSendAndReceive("topicExchange", "key.#", msg, correlationId);
//        String response = topicExchange.toString();
        System.out.println("结束发送消息 : " + msg.toLowerCase());
        System.out.println("消费者响应 : " + topicExchange + " 消息处理完成");
    }
}
