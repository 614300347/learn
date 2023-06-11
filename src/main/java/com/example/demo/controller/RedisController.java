package com.example.demo.controller;

import com.example.demo.pojo.po.Ticket;
import com.example.demo.pojo.vo.ResponseVo;
import com.example.demo.service.GameplayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        ValueOperations valueOperations = redisTemplate.opsForValue();
        Ticket ticket0 = (Ticket) valueOperations.get("ticket0");
        System.out.println("ticket0 = " + ticket0);
        return responseVo;
    }

    @RequestMapping("/testredis1")
    public void testredis1() {
        redisTemplate.opsForValue().set("key1","value1");
        Object execute = redisTemplate.execute(new SessionCallback() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                operations.watch("key1");
                operations.multi();
                operations.opsForValue().set("key2", "values");
                operations.opsForValue().increment("key1",1);
                Object key2 = operations.opsForValue().get("key2");
                System.out.println("key2 = " + key2);
                operations.opsForValue().set("key3", "value3");
                Object key3 = operations.opsForValue().get("key3");
                System.out.println("key3 = " + key3);
                return operations.exec();
            }
        });
        System.out.println("execute = " + execute);
    }


    @RequestMapping("/testredis2")
    public void testredis2() {
        StopWatch stopWatch = new StopWatch();
        HashOperations hashOperations = redisTemplate.opsForHash();
        stopWatch.start();
        for (int i = 0; i < 50000; i++) {

            hashOperations.put("key","key"+i,"value"+i);
        }

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());

        StopWatch stopWatch1 = new StopWatch();

        stopWatch1.start();
        redisTemplate.executePipelined(new SessionCallback<Object>() {
            @Override
            public  Object execute(RedisOperations operations) throws DataAccessException {
                HashOperations hashOperations1 = operations.opsForHash();
                for (int i = 0; i < 50000; i++) {

                    hashOperations1.put("key","key"+i,"value"+i);
                }
                return null;
            }
        });

        stopWatch1.stop();
        System.out.println(stopWatch1.prettyPrint());

    }
}
