package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.UserPoMapper;
import com.example.demo.pojo.po.UserPo;
import com.example.demo.service.UserPoService;
import org.springframework.stereotype.Service;

/**
* @author Lqy
* @description 针对表【user】的数据库操作Service实现
* @createDate 2023-05-23 16:07:17
*/
@Service
public class UserPoServiceImpl extends ServiceImpl<UserPoMapper, UserPo>
    implements UserPoService{

}




