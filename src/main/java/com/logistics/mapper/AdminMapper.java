package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理员dao层接口
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}