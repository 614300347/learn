package com.example.demo.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;

/**
 * @author :Ligou
 * @date : 2023-6-9 19:21
 * 认证成功和失败处理器
 */
@Component
public class MyAuthecationHandler implements AuthenticationSuccessHandler, AuthenticationFailureHandler {
    //认证失败
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        //返回json到前端
        PrintWriter writer = response.getWriter();
        writer.println("登录失败");
        writer.flush();
    }

    //认证成功
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=utf-8");
        //返回json到前端
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            String authority1 = authority.getAuthority();
            if ("ROLE_ADMIN".equals(authority1)){
                response.sendRedirect("http://localhost:9090/doc.html");
            }
        }

//        response.sendRedirect("http://localhost:9090/testredis");
//        PrintWriter writer = response.getWriter();
//        writer.println("登录成功");
//        writer.flush();
    }
}
