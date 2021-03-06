package com.logistics.service;

import com.logistics.entity.PageListVo;


/**
 * 新增用户接口
 */
public interface IEmployeeTypeService {

    /**
     * 新增员工工种类型
     *
     * @param employeeType
     */
    String insert(String employeeType);

    /**
     * 更新员工类型
     *
     * @param id
     * @param employeeType
     * @return
     */
    String update(Integer id, String employeeType);

    /**
     * 根据指定页数进行分页查询（每页10个数据），也可以模糊查询
     *
     * @param currentPage
     * @return
     */
    PageListVo selectByPage(Integer currentPage, String typeName);

}