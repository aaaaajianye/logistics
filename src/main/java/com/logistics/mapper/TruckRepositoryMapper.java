package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.entity.TruckRepository;
import org.apache.ibatis.annotations.Mapper;

/**
 * 货车 - 公司接口
 *
 */
@Mapper
public interface TruckRepositoryMapper extends BaseMapper<TruckRepository> {

}