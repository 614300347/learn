package com.example.demo.controller;

import com.example.demo.annotation.MyLogFirst;
import com.example.demo.pojo.dto.UserDto;
import com.example.demo.pojo.vo.ResponseVo;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @ClassName:UserController
 * @Author:LiGou
 * @Date:2023/5/5 14:10
 * @Version:1.0
 * @Description:
 */
@RestController
@Api(tags = "IndexController测试接口")
public class UserController {

    @Autowired
    UserService userService;

    @MyLogFirst
    @PostMapping("/create")
    @ApiOperation(value = "测试index接口", nickname = "测试IndexController的index接口")
    public ResponseVo createUser(@RequestBody UserDto userDto) {
        userService.insert(userDto);
        System.out.println("userDto = " + userDto);
        ResponseVo responseVo = new ResponseVo();
        responseVo.setReturnDate(new Date());
        responseVo.setCode(200);
        responseVo.setMes("test");
        return responseVo;
    }


}
