package com.example.demo.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName sys_role_menu
 */
@TableName(value ="sys_role_menu")
@Data
public class SysRoleMenu implements Serializable {
    /**
     * 菜单表的编号
     */
    private Integer mid;

    /**
     * 角色表的编号
     */
    private Integer rid;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}