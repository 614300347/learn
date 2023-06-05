package com.example.demo.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @TableName gameplayer
 */
@TableName(value ="gameplayer")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gameplayer implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String gameplayer;

    /**
     * 
     */
    private String sex;

    /**
     * 
     */
    private String grade;

    /**
     * 
     */
    private Integer hp;

    /**
     * 
     */
    private Date magic;

    /**
     * 
     */
    private Integer strength1;

    /**
     * 
     */
    private Integer intelligence;

    /**
     * 
     */
    private Integer physical;

    /**
     * 
     */
    private Integer strength;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}