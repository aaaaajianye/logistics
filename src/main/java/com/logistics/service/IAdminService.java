package com.logistics.service;

import com.logistics.entity.Admin;

import javax.servlet.http.HttpServletRequest;

/**
 * 管理员账号管理接口
 */
public interface IAdminService {

    /**
     * 管理员登录方法
     *
     * @param username
     * @param password
     * @return
     */
    Admin login(String username, String password, HttpServletRequest request);

    /**
     * 新增管理员 - 超级管理员权限
     *
     * @param username
     * @param password
     * @param phone
     * @return
     */
    String insert(String username, String password, String name, String phone);

    /**
     * 修改管理员账号内容(根据username来进行修改，所以username是不动的)
     *
     * @param username
     * @param password
     * @param name
     * @param phone
     * @return
     */
    String update(String username, String password, String name, String phone);

    /**
     * 查询已经登录的admin对象
     *
     * @param request
     * @return
     */
    Admin getAdmin(HttpServletRequest request);

}