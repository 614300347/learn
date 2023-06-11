package com.example.demo.springboot.first;

import org.springframework.stereotype.Component;

/**
 * @author :Ligou
 * @date : 2023-6-10 20:38
 */
@Component
public class Cat implements Animal{
    @Override
    public void yell(String text) {
        System.out.println("cat = " );
    }
}
