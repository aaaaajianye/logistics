package com.logistics.service;

/**
 * @author
 * @date 2020/6/10
 */
public interface IMapApiSerevice {

    /**
     * 根据两个地址获取公里数的接口
     *
     * @param address
     * @param otherAddress
     * @return
     */
    Double getDistanceByTwoPlace(String address, String otherAddress);


}