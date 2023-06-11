package com.example.demo.controller.springboot;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author :Ligou
 * @date : 2023-6-11 15:53
 */
@Component
public class TestAsync {

    @Async
    public void test() {
        System.out.println("test.currentThread().getName() = " + Thread.currentThread().getName());
    }
}
