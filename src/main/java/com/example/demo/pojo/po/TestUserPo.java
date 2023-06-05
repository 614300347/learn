package com.example.demo.pojo.po;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @ClassName:TestUserPo
 * @Author:LiGou
 * @Date:2023/5/15 10:10
 * @Version:1.0
 * @Description:
 */
@Data
@TableName("testuser")
public class TestUserPo {

    @TableId(type = IdType.ASSIGN_UUID)
    private String id;
    private String username;
    private String password;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;
}
