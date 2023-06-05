package com.example.demo.dao;

import com.example.demo.pojo.po.MyUserPo;
import com.example.demo.pojo.po.MyUserPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface MyUserPoMapper {
    long countByExample(MyUserPoExample example);

    int deleteByExample(MyUserPoExample example);

    int insert(MyUserPo record);

    int insertSelective(MyUserPo record);

    List<MyUserPo> selectByExample(MyUserPoExample example);

    int updateByExampleSelective(@Param("record") MyUserPo record, @Param("example") MyUserPoExample example);

    int updateByExample(@Param("record") MyUserPo record, @Param("example") MyUserPoExample example);
}