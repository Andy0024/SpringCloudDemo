package com.object.servicea;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.Test;

/**
 * Copyright: Copyright (c) 2019 Asiainfo
 *
 * @ClassName: com.object.servicea.TestRabbit
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: xianglt
 * @date: 2019/6/3 16:50
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2019/6/3      xianglt          v1.0.0               修改原因
 */
public class TestRabbit {
    private final static String QUEUE_NAME = "hello";
    @Test
    public  void testGovern() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.41.130");
        factory.setUsername("admin");
        factory.setPassword("admin");
        factory.setPort(5672);
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, true, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }



}
