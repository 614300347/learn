package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @author :Ligou
 * @date : 2023-6-11 15:38
 */
@Configuration
@EnableAsync //开启spring异步
public class MyAsyncConfiguration implements AsyncConfigurer {

    @Override
    public Executor getAsyncExecutor() {
        //定义线程池
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数
        threadPoolTaskExecutor.setCorePoolSize(10);
        //线程池最大线程数
        threadPoolTaskExecutor.setMaxPoolSize(30);
        //线程队列最大线程数
        threadPoolTaskExecutor.setQueueCapacity(2000);
        //初始化
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
