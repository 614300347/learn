package com.example.demo.config;

import com.example.demo.rabbitmqconfirm.MyConfirmCallback;
import com.example.demo.rabbitmqconfirm.MyReturnCallback;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author :Ligou
 * @date : 2023-6-1 20:35
 */
@Configuration
public class RabbitMqConfig {

    /**
     * 创建扇形交换机
     *
     * @return
     */
    @Bean
    public FanoutExchange createFanouExchange() {
        //参数是交换机的名字
        return new FanoutExchange("exchange.test");
    }

    /**
     * 创建队列a
     *
     * @return
     */
    @Bean
    public Queue queueA() {
        //队列的名字
        return new Queue("queue.fanout.a");
    }

    /**
     * 创建队列ab
     *
     * @return
     */
    @Bean
    public Queue queueB() {
        //队列的名字
        return new Queue("queue.fanout.b");
    }

    /**
     * 交换机绑定队列
     */
    @Bean
    public Binding bindQueueA(FanoutExchange fanoutExchange, Queue queueA) {
        return BindingBuilder.bind(queueA).to(fanoutExchange);
    }

    /**
     * 交换机绑定队列
     */
    @Bean
    public Binding bindQueueB(FanoutExchange fanoutExchange, Queue queueB) {
        return BindingBuilder.bind(queueB).to(fanoutExchange);
    }


    @Bean
    public DirectExchange directExchange2() {
        return ExchangeBuilder.directExchange("direct.exchange2").build();
    }

    @Bean
    public Queue directQueueB() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-message-ttl", 8000);  //设置过期时间
        args.put("x-dead-letter-exchange", "dead.exchange"); //设置当前队列的死信交换机
        args.put("x-dead-letter-routing-key", "dead"); //设置当前队列的死信路由，注意要和死信交换机绑定死信队列的路由一致，否则无法发送
        args.put("x-max-length", 16);//设置队列最大长度
        return QueueBuilder.durable("direct.queue.456").withArguments(args).build();
//        return QueueBuilder.durable("direct.queue.123").build();
    }

    @Bean
    public Binding createDirectBinding(DirectExchange directExchange2, Queue directQueueB) {
        return BindingBuilder.bind(directQueueB).to(directExchange2).with("test123");
    }

    @Bean
    public DirectExchange deadExchange() {
        return ExchangeBuilder.directExchange("dead.exchange").build();
    }

    @Bean
    public Queue deadQueue() {
        return QueueBuilder.durable("dead.queue").build();
    }

    @Bean
    public Binding deadBinding(DirectExchange deadExchange, Queue deadQueue) {
        return BindingBuilder.bind(deadQueue).to(deadExchange).with("dead");
    }


    @Bean
    public DirectExchange directExchange4() {
        return ExchangeBuilder.directExchange("directExchange4")
//                .alternate("备用交换机名称")
                .build();
    }

    @Bean
    public Queue queue4() {

        Map<String, Object> map = new HashMap<>();
        map.put("x-dead-letter-exchange", "directExchange4");
        map.put("x-dead-letter-routing-key", "test4dead");
//        map.put("x-message-ttl", 12000);
        return QueueBuilder.durable("quque4").withArguments(map).build();
    }

    @Bean
    public Binding binding4(DirectExchange directExchange4, Queue queue4) {
        return BindingBuilder.bind(queue4).to(directExchange4).with("test4");
    }

    @Bean
    public Queue deadqueue4() {
        return QueueBuilder.durable("deadqueue4").build();
    }

    @Bean
    public Binding binding4dead(DirectExchange directExchange4, Queue deadqueue4) {
        return BindingBuilder.bind(deadqueue4).to(directExchange4).with("test4dead");
    }


    @Bean
    public CustomExchange customExchange() {
        Map<String, Object> map = new HashMap<>();
        map.put("x-delayed-type", "direct");
        return new CustomExchange("customerex", "x-delayed-message", true, false, map);
    }

    @Bean
    public Queue queue5() {
        return QueueBuilder.durable("queue5").build();
    }

    @Bean
    public Binding binding5(CustomExchange customExchange, Queue queue5) {
        return BindingBuilder.bind(queue5).to(customExchange).with("test5").noargs();
    }

    @Resource
    MyConfirmCallback myConfirmCallback;
    @Resource
    MyReturnCallback myReturnCallback;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);
        rabbitTemplate.setConfirmCallback(myConfirmCallback);
        //设置队列接收消息失败回调类
        rabbitTemplate.setReturnsCallback(myReturnCallback);
        //必须开启，否则不会回调
        rabbitTemplate.setMandatory(true);
        return rabbitTemplate;
    }
}
