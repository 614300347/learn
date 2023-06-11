package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.mapper.SysRoleMapper;
import com.example.demo.mapper.SysRoleUserMapper;
import com.example.demo.mapper.SysUserMapper;
import com.example.demo.pojo.po.SysRole;
import com.example.demo.pojo.po.SysRoleUser;
import com.example.demo.pojo.po.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author :Ligou
 * @date : 2023-6-9 20:35
 * 实现自定义的认证实现
 */
@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper;

    @Autowired
    private SysRoleMapper sysRoleMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("username", username);
        SysUser sysUser = sysUserMapper.selectOne(queryWrapper);
        if (sysUser == null) {
            //抛出用户名未找到异常
            throw new UsernameNotFoundException("用户名不存在");
        }
        QueryWrapper queryRoleUserWrapper = new QueryWrapper();
        queryRoleUserWrapper.eq("uid", sysUser.getUserId());
        List<SysRoleUser> list = sysRoleUserMapper.selectList(queryRoleUserWrapper);
        QueryWrapper queryRoleWrapper = new QueryWrapper();
        list.stream().forEach(sysRoleUser ->
                queryRoleWrapper.eq("id", sysRoleUser.getRid()));
        List<SysRole> roleList = sysRoleMapper.selectList(queryRoleWrapper);

        UserDetails userDetails = User.builder().username(username).password(sysUser.getPassword()).authorities(
                roleList.stream().map((Function<SysRole, String>) sysRole -> sysRole.getRolename()).collect(Collectors.joining())
        ).build();
        return userDetails;
    }
}
