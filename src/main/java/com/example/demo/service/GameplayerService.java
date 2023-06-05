package com.example.demo.service;

import com.example.demo.pojo.po.Gameplayer;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Lqy
* @description 针对表【gameplayer】的数据库操作Service
* @createDate 2023-05-23 15:29:20
*/
public interface GameplayerService extends IService<Gameplayer> {
        public void getAll();
}
