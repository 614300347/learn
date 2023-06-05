package com.example.demo.controller2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Ligou
 * @date : 2023-5-30 19:54
 */
@RestController
public class Test2Controller {

    @GetMapping("/aaa")
    public void test(String aaa) {

    }
}
