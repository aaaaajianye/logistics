package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.entity.Repository;
import org.apache.ibatis.annotations.Mapper;

/**
 * 分公司仓库dao层接口
 */
@Mapper
public interface RepositoryMapper extends BaseMapper<Repository> {

}