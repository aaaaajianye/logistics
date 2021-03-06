package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.entity.EmployeeType;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工种类的接口
 */
@Mapper
public interface EmployeeTypeMapper extends BaseMapper<EmployeeType> {

}