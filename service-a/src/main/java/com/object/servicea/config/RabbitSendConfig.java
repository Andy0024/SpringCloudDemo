package com.object.servicea.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Copyright: Copyright (c) 2019 Asiainfo
 *
 * @ClassName: com.object.servicea.config.RabbitSendConfig
 * @Description: 该类的功能描述
 * @version: v1.0.0
 * @author: xianglt
 * @date: 2019/6/3 14:40
 * <p>
 * Modification History:
 * Date         Author          Version            Description
 * ------------------------------------------------------------
 * 2019/6/3      xianglt          v1.0.0               修改原因
 */
@Configuration
public class RabbitSendConfig {
    private final static String QUEUE_NAME = "hello";

    /**
     * 声明队列
     */
    @Bean
    public Queue queue1() {
        // 持久化该队列
        return new Queue("hello-queue1", true);
    }

    @Bean
    public Queue queue2() {
        return new Queue("hello-queue2", true);
    }

    /**
     * 声明交换机
     */
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    /**
     * 绑定
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue1()).to(topicExchange()).with("key1");
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("key.#");
    }




}
