package com.example.demo.dao;

import com.example.demo.pojo.po.RolePO;
import com.example.demo.pojo.po.RolePOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RolePOMapper {
    long countByExample(RolePOExample example);

    int deleteByExample(RolePOExample example);

    int insert(RolePO record);

    int insertSelective(RolePO record);

    List<RolePO> selectByExample(RolePOExample example);

    int updateByExampleSelective(@Param("record") RolePO record, @Param("example") RolePOExample example);

    int updateByExample(@Param("record") RolePO record, @Param("example") RolePOExample example);
}