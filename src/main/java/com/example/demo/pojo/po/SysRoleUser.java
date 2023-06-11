package com.example.demo.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_role_user
 */
@TableName(value ="sys_role_user")
@Data
public class SysRoleUser implements Serializable {
    /**
     * 用户编号
     */
    private Integer uid;

    /**
     * 角色编号
     */
    private Integer rid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}