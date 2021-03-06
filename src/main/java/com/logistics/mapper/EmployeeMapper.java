package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.entity.Employee;
import com.logistics.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;

/**
 * 员工dao层接口
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

}