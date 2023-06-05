package com.example.demo.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @ClassName:UserDto
 * @Author:LiGou
 * @Date:2023/5/5 14:08
 * @Version:1.0
 * @Description:
 */
@Data
public class UserDto {

    private Integer id;

    private String name;

    private String username;

    private String password;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birthday;
}
