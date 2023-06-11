package com.example.demo.springboot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Ligou
 * @date : 2023-6-11 14:23
 */
@RestController
@RequestMapping("/redis1")
public class RedisTestController {

    @Autowired
    private RedisTemplate redisTemplate;


}
