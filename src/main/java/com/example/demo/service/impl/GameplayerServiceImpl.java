package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.po.Gameplayer;
import com.example.demo.service.GameplayerService;
import com.example.demo.dao.GameplayerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Lqy
 * @description 针对表【gameplayer】的数据库操作Service实现
 * @createDate 2023-05-23 15:29:20
 */
@Service
public class GameplayerServiceImpl extends ServiceImpl<GameplayerMapper, Gameplayer>
        implements GameplayerService {

    @Autowired
    private GameplayerMapper gameplayerMapper;

    @Autowired
    private RedisTemplate redisTemplate;


    @Override
    public void getAll() {

        HashOperations hashOperations = redisTemplate.opsForHash();
        List<Gameplayer> gameplayers = gameplayerMapper.selectList(null);
        Map<String,Gameplayer> map = new HashMap<>();
        for (Gameplayer gameplayer : gameplayers) {
            map.put(gameplayer.getId(),gameplayer);
        }
        hashOperations.putAll("gameplayer",map);

    }
}




