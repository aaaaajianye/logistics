package com.logistics.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 自动填充
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 创建数据的时候就执行一次更新（主要是设置更新的时间） - 插入时的填充策略
     *
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //创建的时候设置create_time字段的值，第一个值指定实体类的属性，第二个值指定数据，第三个值指定metaObject
        this.setFieldValByName("created", new Date(), metaObject);
    }

    /**
     * 更新数据的时候就执行一次更新（主要是更新最新的操作时间） - 更新时的更新策略
     *
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        //更新的时候只更新update_time字段的值
        this.setFieldValByName("updated", new Date(), metaObject);
    }

}