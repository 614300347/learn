package com.example.demo.service.impl;

import com.example.demo.dao.MyUserPoMapper;
import com.example.demo.pojo.dto.UserDto;
import com.example.demo.pojo.po.MyUserPo;
import com.example.demo.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName:UserServiceImpl
 * @Author:LiGou
 * @Date:2023/5/5 15:06
 * @Version:1.0
 * @Description:
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    MyUserPoMapper userPOMapper;

    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    public void insert2(UserDto userDto) {
        userDto.setName("wdnmd");
        MyUserPo userPO = new MyUserPo();
        BeanUtils.copyProperties(userDto,userPO);
        userPOMapper.insert(userPO);
        int i = 1/0;
    }


    @Transactional(rollbackFor = Exception.class,propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
    @Override
    public void insert(UserDto userDto) {
        MyUserPo userPO = new MyUserPo();
        BeanUtils.copyProperties(userDto,userPO);
        insert2(userDto);
//        userPOMapper.insert(userPO);
//        int i = 1/0;
    }
}
