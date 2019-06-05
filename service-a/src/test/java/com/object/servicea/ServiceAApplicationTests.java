package com.object.servicea;

import com.object.servicea.rabbitmq.HelloSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceAApplicationTests {
    @Autowired
    private HelloSender sender;

    @Test
    public void contextLoads() throws InterruptedException {
        while (true) {
            String msg = new Date().toString();
            sender.send(msg);
            Thread.sleep(1000);
        }
    }

    private final static String QUEUE_NAME = "hello";


}
