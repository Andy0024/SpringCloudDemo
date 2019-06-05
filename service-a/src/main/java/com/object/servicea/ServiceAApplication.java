package com.object.servicea;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

/**
 * @author xlt
 */
@SpringBootApplication
@EnableEurekaClient
public class ServiceAApplication {
    public static String queueName = "hello";

    @Bean
    public Queue helloQueue() {
        return new Queue("hello");
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceAApplication.class, args);
    }

}
