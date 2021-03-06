package com.logistics.service.impl;

import com.logistics.service.IMapApiSerevice;
import com.logistics.utils.LocationUtils;
import org.springframework.stereotype.Service;

/**
 * 百度地图接口
 *
 * @author shiwen
 * @date 2020/6/10
 */
@Service
public class MapApiServiceImpl implements IMapApiSerevice {

    /**
     * 根据两个地址返回两个地址的直线距离/公里（两个地点经纬度计算出来的直线距离）
     *
     * @param address
     * @param otherAddress
     * @return
     */
    @Override
    public Double getDistanceByTwoPlace(String address, String otherAddress) {
        // 判空，要求我们的地址参数不能为空
        if (address != null && !"".equals(address) && otherAddress != null && !"".equals(otherAddress)) {
            // 返回最终地址 / 公里
            return LocationUtils.getDistance(address, otherAddress) > 0 ? LocationUtils.getDistance(address, otherAddress) : 0.0;
        } else {
            return 0.0;
        }
    }
}