package com.example.demo.consumer;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName:TicketConsumer
 * @Author:LiGou
 * @Date:2023/6/9 10:33
 * @Version:1.0
 * @Description:
 */
@Component
public class TicketConsumer {

//    @RabbitListener(queues = {"test4"})
//    public void consmumeTest4(Message message, Channel channel) {
//        MessageProperties messageProperties = message.getMessageProperties();
//        String messageId = messageProperties.getMessageId();
////        channel.basicNack();
//    }
}
