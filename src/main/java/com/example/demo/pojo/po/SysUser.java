package com.example.demo.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_user
 */
@TableName(value ="sys_user")
@Data
public class SysUser implements Serializable {
    /**
     * 编号
     */
    @TableId(type = IdType.AUTO)
    private Integer userId;

    /**
     * 登陆名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private String sex;

    /**
     * 地址
     */
    private String address;

    /**
     * 是否启动账户0禁用 1启用
     */
    private Integer enabled;

    /**
     * 账户是否没有过期0已过期 1 正常
     */
    private Integer accountNoExpired;

    /**
     * 密码是否没有过期0已过期 1 正常
     */
    private Integer credentialsNoExpired;

    /**
     * 账户是否没有锁定0已锁定 1 正常
     */
    private Integer accountNoLocked;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}