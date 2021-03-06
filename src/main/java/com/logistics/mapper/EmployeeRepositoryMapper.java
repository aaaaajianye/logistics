package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.entity.EmployeeRepository;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 员工和公司的关系接口
 *
 */
@Mapper
public interface EmployeeRepositoryMapper extends BaseMapper<EmployeeRepository> {

}