package com.example.demo.controller.springboot;

import com.example.demo.pojo.po.Book;
import io.swagger.annotations.*;
import org.aspectj.lang.annotation.RequiredTypes;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    @Secured({"ROLE_ADMIN"})
    @ApiOperation(("测试权限Secured"))
    public void testasync() throws InterruptedException {
        String test = testAsync.test();
        testasync1();
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
        System.out.println("test = " + test);
    }

    @Async
    public void testasync1() {
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    }

    @GetMapping("/testsync2")
    @PreAuthorize("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
    @ApiOperation("测试PreAuthorize")
    public void testsync2() throws InterruptedException {
        testAsync.test();
        testasync1();
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    }

    @PostFilter("filterObject.name=='test1'")
    @GetMapping("/testsync3")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiOperation("测试PostFilter返回值")
    public List<Book> testsync3() {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Book book = new Book();
            book.setName("test" + i);
            books.add(book);
        }
        return books;

    }



    @PostMapping("/testsync4")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostFilter("filterObject.name=='test1'")
    @PreFilter("filterObject.score==1")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "book", value = "书本")
    })
    @ApiResponses({
            @ApiResponse(code = 400, message = "正常")
    })
    @ApiOperation("测试PreFilter")
    public List<Book> books(@RequestBody List<Book> bookList) {
        Book book=bookList.get(0);
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < book.getScore(); i++) {
            Book book1 = new Book();
            book1.setName("test" + i);
            books.add(book1);
        }
        return books;
    }
}
