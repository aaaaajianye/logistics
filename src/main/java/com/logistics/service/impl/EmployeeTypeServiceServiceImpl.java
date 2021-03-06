package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.entity.EmployeeType;
import com.logistics.entity.PageListVo;
import com.logistics.mapper.EmployeeTypeMapper;
import com.logistics.service.IEmployeeTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工种类管理接口
 */

@Service
public class EmployeeTypeServiceServiceImpl implements IEmployeeTypeService {

    @Resource
    private EmployeeTypeMapper employeeTypeMapper;

    /**
     * 设置新员工工种类型进行插入操作
     *
     * @param employeeType
     */
    @Override
    public String insert(String employeeType) {
        EmployeeType current_employeeType = new EmployeeType();
        current_employeeType.setTypeName(employeeType);
        // 插入对象进数据库
        Integer count = employeeTypeMapper.insert(current_employeeType);
        if (count > 0) {
            return "新增员工种类成功";
        } else {
            return "新增员工种类失败";
        }
    }

    /**
     * 更新员工种类
     *
     * @param id
     * @param employeeType
     * @return
     */
    @Override
    public String update(Integer id, String employeeType) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        EmployeeType current_employeeType = employeeTypeMapper.selectOne(wrapper);
        if (current_employeeType != null) {
            // 更新员工的种类名
            current_employeeType.setTypeName(employeeType);
            Integer count = employeeTypeMapper.updateById(current_employeeType);
            // 判断是否更新成功
            if (count > 0) {
                return "更新员工种类成功";
            } else {
                return "更新员工种类失败";
            }
        } else {
            return "此员工种类不存在";
        }
    }

    /**
     * 根据指定的页数进行分页查询（每页10条），也可以模糊查询
     *
     * @param currentPage
     * @return
     */
    @Override
    public PageListVo selectByPage(Integer currentPage, String typeName) {
        // 根据我们的指定页数，并且每页展示10个数据进行展示
        Page<EmployeeType> page = new Page<>(currentPage, 10);
        // 模糊条件查询
        QueryWrapper wrapper = new QueryWrapper();
        // 这里要记得给我们的typeName进行判空的操作
        if (typeName != null && !"".equals(typeName)) {
            wrapper.like("type_name", typeName);
        }
        // 分页查询对象
        employeeTypeMapper.selectPage(page, wrapper);
        // 获取集合内容
        List<EmployeeType> records = page.getRecords();
        // 获取总数据数totalCount
        Integer totalCount = employeeTypeMapper.selectCount(wrapper);
        return new PageListVo(currentPage, 10, totalCount, records);
    }

}