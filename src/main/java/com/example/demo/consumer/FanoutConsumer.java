package com.example.demo.consumer;

import com.example.demo.pojo.po.Gameplayer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;

/**
 * @author :Ligou
 * @date : 2023-6-1 20:54
 */
@Component
@Slf4j
public class FanoutConsumer {

    //接收多个队列的消息
    @RabbitListener(queues = {"queue.fanout.a", "queue.fanout.b"})
    public void consumer(Message message) {
        byte[] body = message.getBody();
        String s = new String(body);
        System.out.println("s = " + s);
    }


    @RabbitListener(queues = {"direct.queue.456"})
    public void consumer1(Message message, Channel channel) throws IOException {
        MessageProperties messageProperties = message.getMessageProperties();
        //获取当前消息的唯一标识
        long deliveryTag = messageProperties.getDeliveryTag();
        //消费者手动确认消息，第一个参数是消息的唯一标识，第二个参数是false，表示只确认当前，true为确认当前及之前队列的
//        log.info("接收消息");
//        channel.basicAck(deliveryTag,false);
        //前两个参数和上面一致，第三个参数表示是否重新入队
//        log.info("重新入队。。。");
//        channel.basicNack(deliveryTag,false,false);
        log.info("拒绝消息");
        channel.basicReject(deliveryTag, false);
    }


    @RabbitListener(queues = {"dead.queue"})
    public void consumer2(Message message, Channel channel) throws IOException {
        MessageProperties messageProperties = message.getMessageProperties();
        //获取当前消息的唯一标识
        long deliveryTag = messageProperties.getDeliveryTag();
        //消费者手动确认消息，第一个参数是消息的唯一标识，第二个参数是false，表示只确认当前，true为确认当前及之前队列的
//        log.info("接收消息");
//        channel.basicAck(deliveryTag,false);
        //前两个参数和上面一致，第三个参数表示是否重新入队
        log.info("不重新入队。。。死信接收");
        byte[] body = message.getBody();
        System.out.println("body = " + new String(body));
    }

    @RabbitListener(queues = {"deadqueue4"})
    public void consumer3(Message message) {
        log.info("监听死信");
        log.info(new String(message.getBody()));
    }

    @RabbitListener(queues = {"quque4"})
    public void consumer4(Message message) {
        byte[] body = message.getBody();
        String mes = new String("body");
        log.info("接收时间:{}", new Date());
    }

    @Resource
    ObjectMapper objectMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queues = {"queue5"})
    public void consumer5(Message message, Channel channel) throws IOException {
        byte[] body = message.getBody();
        MessageProperties messageProperties = message.getMessageProperties();
        long deliveryTag = messageProperties.getDeliveryTag();
        Gameplayer gameplayer = objectMapper.readValue(new String(body), Gameplayer.class);
        System.out.println("gameplayer = " + gameplayer);

        HashOperations hashOperations = redisTemplate.opsForHash();
        Gameplayer gameplayer1 = (Gameplayer) hashOperations.get("gameplayer", gameplayer.getId());
        if (gameplayer1 != null) {
            channel.basicNack(deliveryTag, false, false);
        } else {
            hashOperations.put("gameplayer",gameplayer.getId(),gameplayer);
            channel.basicAck(deliveryTag,false);
        }

    }
}
