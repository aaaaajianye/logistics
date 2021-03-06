package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.logistics.entity.EmployeeRepository;
import com.logistics.entity.Repository;
import com.logistics.mapper.EmployeeRepositoryMapper;
import com.logistics.mapper.RepositoryMapper;
import com.logistics.service.IEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 员工 - 公司关系 实现接口
 *
 * @author shiwen
 * @date 2020/6/25
 */
@Service
public class EmployeeRepositoryImpl implements IEmployeeRepository {

    @Resource
    private EmployeeRepositoryMapper employeeRepositoryMapper;

    @Resource
    private RepositoryMapper repositoryMapper;

    /**
     * 根据员工id查询工厂所属的工厂id
     *
     * @return
     */
    @Override
    public EmployeeRepository getEmployeeRepository(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int employeeId  = (int) session.getAttribute("employeeId");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("employee_id", employeeId);
        // 根据员工id查询当前的员工所属的公司id
        return employeeRepositoryMapper.selectOne(wrapper);
    }

    // 根据员工id获取公司对象
    @Override
    public Repository getRepositoryById(HttpServletRequest request) {
        EmployeeRepository employeeRepository = getEmployeeRepository(request);
        // 获取公司的id
        Integer repositoryId = employeeRepository.getRepositoryId();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", repositoryId);
        return repositoryMapper.selectOne(wrapper);
    }

}