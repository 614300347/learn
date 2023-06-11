package com.example.demo.controller.springboot;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Ligou
 * @date : 2023-6-11 15:37
 */
@RestController
@RequestMapping("/lesson")
public class LessonController {

    @Autowired
    private TestAsync testAsync;

    @GetMapping("/testasync")
    public void testasync() {
        testAsync.test();
        testasync1();
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    }

    @Async
    public void testasync1() {
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    }
}
