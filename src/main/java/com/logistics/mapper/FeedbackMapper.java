package com.logistics.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.logistics.entity.Feedback;
import org.apache.ibatis.annotations.Mapper;

/**
 * 反馈dao层接口
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {

}