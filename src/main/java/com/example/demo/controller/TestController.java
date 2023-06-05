package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.annotation.MyLogFirst;
import com.example.demo.dao.GameplayerMapper;
import com.example.demo.pojo.po.Gameplayer;
import com.example.demo.pojo.po.TestUserPo;
import com.example.demo.service.GameplayerService;
import com.example.demo.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName:TestController
 * @Author:LiGou
 * @Date:2023/5/4 14:33
 * @Version:1.0
 * @Description:
 */
@RestController
public class TestController {

    @RequestMapping("/test")
    @MyLogFirst
    public void test(Integer id, String a) {
        System.out.println("id = " + id);
        testUserService.test();
    }

    @Autowired
    TestUserService testUserService;


    @Autowired
    private GameplayerService gameplayerService;


    @RequestMapping("/test2")
    public void test2() {
        QueryWrapper<Gameplayer> wrapper = new QueryWrapper<>();
        wrapper.in("sex","M");
        List<Gameplayer> gameplayers = gameplayerService.list(wrapper);
        System.out.println("gameplayers.size() = " + gameplayers.size());

    }

}
