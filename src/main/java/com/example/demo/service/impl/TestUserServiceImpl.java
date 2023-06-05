package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.TestUserMapper;
import com.example.demo.pojo.po.TestUserPo;
import com.example.demo.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Wrapper;

/**
 * @ClassName:TestUserServiceImpl
 * @Author:LiGou
 * @Date:2023/5/15 10:16
 * @Version:1.0
 * @Description:
 */
@Service
public class TestUserServiceImpl extends ServiceImpl<TestUserMapper, TestUserPo> implements TestUserService {

    @Autowired
    TestUserMapper testUserMapper;

    @Override
    @Transactional
    public void test() {
        TestUserPo user = new TestUserPo();
        user.setUsername("小文");
        user.setPassword("111");
        testUserMapper.insert(user);


        QueryWrapper<TestUserPo> wrapper = new QueryWrapper<>();

    }
}
