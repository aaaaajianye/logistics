package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.entity.Employee;
import com.logistics.entity.PageListVo;
import com.logistics.mapper.EmployeeMapper;
import com.logistics.service.IEmployeeService;
import com.logistics.utils.Md5Utils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 员工管理接口
 */

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;

    /**
     * 新增员工的方法
     *
     * @param username
     * @param password
     * @param name
     * @param typeName
     * @param phone
     * @return
     */
    @Override
    public String insert(String username, String password, String name, String typeName, String phone) {
        Employee employee = new Employee(username, Md5Utils.setMD5(password), name, typeName, phone, null);
        Integer count = employeeMapper.insert(employee);
        if (count > 0) {
            return "新增员工成功";
        } else {
            return "新增员工失败";
        }
    }

    /**
     * 更新员工信息的方法
     *
     * @param username
     * @param password
     * @param name
     * @param typeName
     * @param phone
     * @return
     */
    @Override
    public String update(String username, String password, String name, String typeName, String phone) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        Employee employee = employeeMapper.selectOne(wrapper);
        if (employee != null) {
            // 如果密码不为空的话就可以直接设置密码
            if (password != null && !"".equals(password)) {
                employee.setPassword(Md5Utils.setMD5(password));
            }
            // 如果用户姓名不为空的话就直接设置用户名
            if (name != null && !"".equals(name)) {
                employee.setName(name);
            }
            // 如果工种类型不为空的话就直接设置工种类型名
            if (typeName != null && !"".equals(typeName)) {
                employee.setTypeName(typeName);
            }
            // 如果用户电话不为空的话就直接设置用户的电话
            if (phone != null && !"".equals(phone)) {
                employee.setPhone(phone);
            }
            Integer count = employeeMapper.updateById(employee);
            if (count > 0) {
                return "员工更新成功";
            } else {
                return "员工更新失败";
            }
        } else {
            return "此员工账号不存在";
        }
    }

    /**
     * 根据员工id进行删除的方法
     *
     * @param id
     * @return
     */
    @Override
    public String deletedById(Integer id) {
        Integer count = 0;
        if (id != null && !"".equals(id)) {
            count = employeeMapper.deleteById(id);
        }
        if (count > 0) {
            return "删除成功";
        } else {
            return "删除失败";
        }
    }

    /**
     * 员工登录方法
     *
     * @param username
     * @param password
     * @return
     */
    @Override
    public Employee login(String username, String password, HttpServletRequest request) {
        HttpSession session = request.getSession();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("username", username);
        wrapper.eq("password", Md5Utils.setMD5(password));
        Employee employee = employeeMapper.selectOne(wrapper);
        if (employee != null) {
            session.setAttribute("employeeId", employee.getId());
            return employee;
        } else {
            return null;
        }
    }

    /**
     * 指定当前页进行分页查询，如果有name的话就通过name进行模糊查询
     *
     * @param currentPage
     * @param name
     * @return
     */
    @Override
    public PageListVo selectByPage(Integer currentPage, String name) {
        Page<Employee> page = new Page<>(currentPage, 10);
        QueryWrapper wrapper = new QueryWrapper();
        // 如果有输入员工的名字的话，同时加个员工名的模糊查询
        if (name != null && !"".equals(name)) {
            wrapper.like("name", name);
        }
        // 根据条件进行分页模糊查询
        employeeMapper.selectPage(page, wrapper);
        List<Employee> records = page.getRecords();
        Integer totalCount = employeeMapper.selectCount(wrapper);
        // 最后返回一个分页对象
        return new PageListVo(currentPage, 10, totalCount, records);
    }

    /**
     * 根据员工id获取员工对象
     */
    @Override
    public Employee getById(HttpServletRequest request) {
        // 获取存储在session中的id
        HttpSession session = request.getSession();
        int employeeId = (int) session.getAttribute("employeeId");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", employeeId);
        return employeeMapper.selectOne(wrapper);
    }

}