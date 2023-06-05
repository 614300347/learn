package com.example.demo.aspect;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName:ControllerAspect
 * @Author:LiGou
 * @Date:2023/5/4 14:26
 * @Version:1.0
 * @Description:
 */
@Component
@Aspect
@Slf4j
public class ControllerAspect {
    @Pointcut("@annotation(com.example.demo.annotation.MyLogFirst)")
    private void log() {

    }

    @Before("log()")
    public void before(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        /**
         * 打印请求入参
         */
        log.info("Request Args: {}", JSON.toJSONString(joinPoint.getArgs()));
        /**
         * 打印请求方式
         */
        log.info("Request method: {}", request.getMethod());
        /**
         * 打印请求 url
         */
        log.info("Request URL: {}", request.getRequestURL().toString());

        /**
         * 打印调用方法全路径以及执行方法
         */
        log.info("Request Class and Method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }


    /**
     * @param proceedingJoinPoint
     * @author xiaofu
     * @description 切面方法返回结果日志打印
     * @date 2020/7/15 10:32
     */
    @Around("log()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {


        long startTime = System.currentTimeMillis();

        Object result = proceedingJoinPoint.proceed();
        /**
         * 输出结果
         */
        log.info("{}，Response result  : {}", "", JSON.toJSONString(result));

        /**
         * 方法执行耗时
         */
        log.info("Time Consuming: {} ms", System.currentTimeMillis() - startTime);

        return result;
    }


}
