package com.logistics.service;

import com.logistics.entity.PageListVo;
import com.logistics.entity.Repository;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shiwen
 * @date 2020/6/11
 */
public interface IRepositoryService {

    /**
     * 新增分公司仓库（通过公司名进行新增）
     *
     * @param repositoryName
     * @return
     */
    String insert(String repositoryName, String address);

    /**
     * 修改公司的公司名/地址
     *
     * @param id
     * @param repositoryName
     * @param address
     * @return
     */
    String update(Integer id, String repositoryName, String address);

    /**
     *
     *
     * @param currentPage
     * @param repositoryName
     * @return
     */
    PageListVo selectByPage(Integer currentPage, String repositoryName);

    /**
     * 出仓操作
     */
    void chucang(String orderId);

    // 获取session中的id进行展示操作
    Repository getRepositoryBySessionId(HttpServletRequest request);

}