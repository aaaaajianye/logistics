package com.logistics.service;

import com.logistics.entity.PageListVo;

/**
 * 货车 - 公司接口
 *
 * @author shiwen
 * @date 2020/6/12
 */
public interface ITruckRepositoryService {

    /**
     * 新增货车
     *
     * @param carNo
     * @param repositoryId
     * @param isTransport
     * @return
     */
    String insert(String carNo, Integer repositoryId, Integer isTransport);

    /**
     * 根据id更改货车的运输状态:[0]未在运输 [1]正在运输
     *
     * @param id
     * @param isTransport
     * @return
     */
    String update(Integer id, Integer isTransport);

    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    String delete(Integer id);

    /**
     * 根据条件进行分页查询
     *
     * @param currentPage
     * @param carNo
     * @param repositoryId
     * @return
     */
    PageListVo selectByPage(Integer currentPage, String carNo, Integer repositoryId);

}