package com.example.demo.controller;

import com.example.demo.pojo.po.Gameplayer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeoutException;

/**
 * @author :Ligou
 * @date : 2023-6-1 19:37
 */
@RestController
@Api(tags = "消息队列controller")
@Slf4j
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/fanoutproduct")
    @ApiOperation("扇形交换机生产者")
    public void product() throws IOException, TimeoutException {
        Message message = new Message("wdnmd".getBytes());
        //第一个参数交换机的名称,第二个参数路由名称,扇形交换机可以不填,第三个参数消息
        rabbitTemplate.convertAndSend("exchange.test", "", message);
    }

    @GetMapping("/fanoutconsumer")
    @ApiOperation("扇形交换机消费者")
    public void consumer() throws IOException, TimeoutException {

    }


    @GetMapping("/directexchange")
    @ApiOperation("直连交换机")
    public void testDirect() {
        MessageProperties messageProperties = new MessageProperties();
//        messageProperties.setExpiration("15000");//毫秒为单位
        Message message = MessageBuilder.withBody("wdnmd456".getBytes()).andProperties(messageProperties).build();
        rabbitTemplate.convertAndSend("direct.exchange2", "test123", message);


    }


    @GetMapping("/directchangeyc")
    @ApiOperation("死信交换机和普通交换机是同一台")
    public void test123() {
        Message message = new Message("死信和普通是同一个".getBytes());
        rabbitTemplate.convertAndSend("directExchange4", "test4", message);

    }

    @Resource
    private ObjectMapper objectMapper;

    @GetMapping("/customerex")
    @ApiOperation("延迟交换机")
    public void test456() throws JsonProcessingException {
        Gameplayer gameplayer1 = new Gameplayer();
        gameplayer1.setId("t123");
        gameplayer1.setMagic(new Date());
        String s1 = objectMapper.writeValueAsString(gameplayer1);

        Gameplayer gameplayer2 = new Gameplayer();
        gameplayer2.setId("t123");
        gameplayer2.setMagic(new Date());
        String s2 = objectMapper.writeValueAsString(gameplayer2);

        {
            Message message = new Message(s1.getBytes());
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setHeader("x-delay", 10);//设置延迟时间
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId("test123_123");
            rabbitTemplate.convertAndSend("customerex", "test5", message, correlationData);
//        log.info("发送时间:{}" ,new Date());
        }
        {
            Message message = new Message(s2.getBytes());
            MessageProperties messageProperties = message.getMessageProperties();
            messageProperties.setHeader("x-delay", 10);//设置延迟时间
            CorrelationData correlationData = new CorrelationData();
            correlationData.setId("test123_456");
            rabbitTemplate.convertAndSend("customerex", "test5", message, correlationData);
        }
    }
}
