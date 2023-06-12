package com.example.demo.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @ClassName:OrderConsumer
 * @Author:LiGou
 * @Date:2023/6/12 15:35
 * @Version:1.0
 * @Description:
 */
@Component
public class OrderConsumer {

    @RabbitListener(queues = {"order.create.queue"})
    public void cosume(Channel channel, Message message) throws IOException {
        MessageProperties messageProperties = message.getMessageProperties();
        long deliveryTag = messageProperties.getDeliveryTag();
        channel.basicNack(deliveryTag,false,false);
    }
}
