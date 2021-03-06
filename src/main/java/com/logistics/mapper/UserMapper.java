package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户实体接口
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}