package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import java.time.Duration;

/**
 * @author :Ligou
 * @date : 2023-5-30 18:54
 */
@Configuration
public class RedisConfig extends CachingConfigurerSupport {

//    @Value("${jedis.pool.max-active}")
//    private Integer maxActive;
//
//    @Value("${jedis.pool.max-idle}")
//    private Integer maxIdle;
//    @Value("${jedis.pool.max-wait}")
//    private Integer maxWait;
//    @Value("${jedis.password}")
//    private String password;
//    @Value("${jedis.port}")
//    private Integer port;
//    @Value("${jedis.hostname}")
//    private String hostname;
//

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        //开启乐观锁
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

//    private RedisConnectionFactory redisConnectionFactory = null;
//
//    @Bean(name = "connectionFactory")
//    public RedisConnectionFactory initRedisConnectionFactory() {
//        if (this.redisConnectionFactory != null) {
//            return this.redisConnectionFactory;
//        }
//        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
//        //最大空闲数
//        jedisPoolConfig.setMaxIdle(maxIdle);
//        //最大连接数
//        jedisPoolConfig.setMaxTotal(maxActive);
//        //最大等待时间
//        jedisPoolConfig.setMaxWait(Duration.ofMillis(maxWait));
//        //创建jedis连接工厂
//        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
//        //获取单机配置
//        RedisStandaloneConfiguration standaloneConfiguration = jedisConnectionFactory.getStandaloneConfiguration();
//        standaloneConfiguration.setPort(port);
//        standaloneConfiguration.setPassword(password);
//        standaloneConfiguration.setHostName(hostname);
//        this.redisConnectionFactory = jedisConnectionFactory;
//        return jedisConnectionFactory;
//    }
}
