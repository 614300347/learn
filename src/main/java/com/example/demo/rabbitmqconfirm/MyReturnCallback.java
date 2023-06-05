package com.example.demo.rabbitmqconfirm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author :Ligou
 * @date : 2023-6-3 15:48
 */
@Component
@Slf4j
public class MyReturnCallback implements  RabbitTemplate.ReturnsCallback {
    @Override
    public void returnedMessage(ReturnedMessage returnedMessage) {
        int replyCode = returnedMessage.getReplyCode();
        System.out.println("replyCode = " + replyCode);
        log.info(returnedMessage.getMessage().toString());
    }
}
