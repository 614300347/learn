package com.example.demo.controller;

import com.example.demo.pojo.vo.ResponseVo;
import com.example.demo.service.GameplayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Ligou
 * @date : 2023-5-31 20:31
 */
@RestController
public class RedisController {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private GameplayerService gameplayerService;
    @PostMapping("/testredis")
    public ResponseVo testredis() {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setMes("ok");
        responseVo.setCode(100);
        gameplayerService.getAll();
        return responseVo;
    }




}
