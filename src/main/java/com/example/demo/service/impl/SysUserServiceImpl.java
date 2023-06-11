package com.example.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.pojo.po.SysUser;
import com.example.demo.service.SysUserService;
import com.example.demo.mapper.SysUserMapper;
import org.springframework.stereotype.Service;

/**
* @author LQY
* @description 针对表【sys_user】的数据库操作Service实现
* @createDate 2023-06-09 20:24:09
*/
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser>
    implements SysUserService{

}




