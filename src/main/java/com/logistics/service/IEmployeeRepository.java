package com.logistics.service;

import com.logistics.entity.EmployeeRepository;
import com.logistics.entity.Repository;

import javax.servlet.http.HttpServletRequest;

/**
 * @author shiwen
 * @date 2020/6/25
 */
public interface IEmployeeRepository {

    /**
     * 根据id查询员工-公司关系对象
     */
    EmployeeRepository getEmployeeRepository(HttpServletRequest request);

    /**
     * 根据公司的主键id查询公司对象
     */
    Repository getRepositoryById(HttpServletRequest request);

}