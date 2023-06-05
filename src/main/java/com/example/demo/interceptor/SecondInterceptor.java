package com.example.demo.interceptor;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName:SecondInterceptor
 * @Author:LiGou
 * @Date:2023/5/5 14:39
 * @Version:1.0
 * @Description:
 */
@Component
@Order()
public class SecondInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("2 = " + 2);
        return true;
    }
}
