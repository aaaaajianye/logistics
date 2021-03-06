package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.entity.Goods;
import com.logistics.entity.PageListVo;
import com.logistics.mapper.GoodsMapper;
import com.logistics.service.IGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shiwen
 * @date 2020/6/10
 */
@Service
public class GoodsServiceImpl implements IGoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    /**
     * 新增货物信息
     *
     * @param ordersKey
     * @param totalWeight
     * @param totalPrice
     * @param startPoint
     * @param endPoint
     * @return
     */
    @Override
    public String insert(String ordersKey, String totalWeight, String totalPrice, String startPoint, String endPoint) {
        // 初始化一个goods对象，并带有数据
        Goods goods = new Goods(ordersKey, totalWeight, totalPrice, startPoint, endPoint);
        Integer count = goodsMapper.insert(goods);
        if (count > 0) {
            return "货物新增成功";
        } else {
            // 22 23 24
            return "货物新增失败";
        }
    }

    /**
     * 查询货物信息
     *
     * @param currentPage
     * @param ordersKey
     * @return
     */
    @Override
    public PageListVo selectByKey(Integer currentPage, String ordersKey) {
        // 分页数据，每页10条，指定页面进行分页查询，如果有模糊查询的条件就直接模糊查询
        Page<Goods> page = new Page<>(currentPage, 10);
        QueryWrapper wrapper = new QueryWrapper();
        if (ordersKey != null && !"".equals(ordersKey)) {
            wrapper.like("orders_key", ordersKey);
        }
        // 分页查询对象
        goodsMapper.selectPage(page, wrapper);
        // 获取集合内容
        List<Goods> records = page.getRecords();
        Integer totalCount = goodsMapper.selectCount(wrapper);
        return new PageListVo(currentPage, 10, totalCount, records);
    }

}