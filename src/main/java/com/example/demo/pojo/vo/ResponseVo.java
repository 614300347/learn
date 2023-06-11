package com.example.demo.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.poi.ss.formula.functions.T;

import java.util.Date;

/**
 * @ClassName:ResponseDataVo
 * @Author:LiGou
 * @Date:2023/5/5 14:11
 * @Version:1.0
 * @Description:
 */
@Data
@ApiModel("接口统一返回结果")
@AllArgsConstructor
@NoArgsConstructor
public class ResponseVo<T> {

    @ApiModelProperty("消息")
    private String mes;
    @ApiModelProperty("状态码")
    private Integer code;

    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("返回时间")
    private Date returnDate;

    @ApiModelProperty("实体类")
    private T data;

}
