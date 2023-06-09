package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @ClassName:SecurityConfig
 * @Author:LiGou
 * @Date:2023/6/9 8:50
 * @Version:1.0
 * @Description: 配置认证
 */
@Configuration
public class SecurityConfig {


    @Bean
    public UserDetailsService userDetailsService() {
        //创建两个自定义用户,自定义用户名，密码，角色
        //UserDetails 存储了用户的用户名，密码，权限，是否过期，是否可用等
        UserDetails ligou = User.builder().username("ligou")
                //密码加密
                .password(passwordEncoder().encode("123"))
                //配置角色
                .roles("student")
                //配置权限
                .authorities("student:delete")
                .build();
        UserDetails ligou2 = User.builder().username("ligou2")
                .password("123")
                .roles("teacher").build();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(ligou);
        manager.createUser(ligou2);
        return manager;
    }


    /**
     * 自定义用户必须配置密码加密器，
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
