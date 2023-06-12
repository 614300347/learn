package com.example.demo.controller.springboot;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author :Ligou
 * @date : 2023-6-11 15:53
 */
@Component
public class TestAsync {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Async
    public String test() throws InterruptedException {
        Thread.sleep(10000);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("x-delay", 10000);

        Message message = new Message("测试".getBytes(), messageProperties);
        rabbitTemplate.convertAndSend("lessonexchange", "lesson", message);
        System.out.println("test.currentThread().getName() = " + Thread.currentThread().getName());
        return "ok";
    }

//    @RabbitListener(queues = {"lessonqueue"})
//    public void consume() {
//
//    }
}
