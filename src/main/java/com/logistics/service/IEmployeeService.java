package com.logistics.service;

import com.logistics.entity.Employee;
import com.logistics.entity.PageListVo;

import javax.servlet.http.HttpServletRequest;

/**
 * 员工接口
 */
public interface IEmployeeService {

    /**
     * 新增员工的接口
     *
     * @param username
     * @param password
     * @param name
     * @param typeName
     * @param phone
     * @return
     */
    String insert(String username, String password, String name, String typeName, String phone);

    /**
     *
     *
     * @param username
     * @param password
     * @param name
     * @param typeName
     * @param phone
     * @return
     */
    String update(String username, String password, String name, String typeName, String phone);

    /**
     * 根据id进行删除
     *
     * @param id
     * @return
     */
    String deletedById(Integer id);

    /**
     * 员工登录方法
     *
     * @param username
     * @param password
     * @return
     */
    Employee login(String username, String password, HttpServletRequest request);

    /**
     *
     *
     * @param currentPage
     * @param name
     * @return
     */
    PageListVo selectByPage(Integer currentPage, String name);

    /**
     * 根据员工主键id查询员工
     */
    Employee getById(HttpServletRequest request);

}