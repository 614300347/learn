package com.example.demo.dao;

import com.example.demo.pojo.po.DepartmentPO;
import com.example.demo.pojo.po.DepartmentPOExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentPOMapper {
    long countByExample(DepartmentPOExample example);

    int deleteByExample(DepartmentPOExample example);

    int insert(DepartmentPO record);

    int insertSelective(DepartmentPO record);

    List<DepartmentPO> selectByExample(DepartmentPOExample example);

    int updateByExampleSelective(@Param("record") DepartmentPO record, @Param("example") DepartmentPOExample example);

    int updateByExample(@Param("record") DepartmentPO record, @Param("example") DepartmentPOExample example);
}