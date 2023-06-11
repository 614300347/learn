package com.example.demo.springboot.first;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Ligou
 * @date : 2023-6-10 20:42
 */
@RestController
@RequestMapping("/first")
public class TestController {

    @Autowired
    @Qualifier("dog")
    private Animal animal;

    @RequestMapping("/yell")
    public void yell() {
        animal.yell("ww");
    }
}
