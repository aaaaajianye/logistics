package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.entity.PageListVo;
import com.logistics.entity.Repository;
import com.logistics.mapper.RepositoryMapper;
import com.logistics.service.IOrdersService;
import com.logistics.service.IRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author shiwen
 * @date 2020/6/11
 */
@Service
public class RepositoryServiceImpl implements IRepositoryService {

    @Resource
    private RepositoryMapper repositoryMapper;

    @Autowired
    private IOrdersService ordersService;

    /**
     * 新增一个公司（按公司名进行新增）
     *
     * @param repositoryName
     * @return
     */
    @Override
    public String insert(String repositoryName, String address) {
        if (repositoryName != null && !"".equals(repositoryName)) {
            Repository repository = new Repository(repositoryName, address);
            Integer count = repositoryMapper.insert(repository);
            if (count > 0) {
                return "新增子公司成功";
            } else {
                return "新增子公司失败";
            }
        }
        return "子公司名不能为空";
    }

    /**
     * 更新公司名/地址
     *
     * @param repositoryName
     * @param address
     * @return
     */
    @Override
    public String update(Integer id, String repositoryName, String address) {
        Repository repository = repositoryMapper.selectById(id);
        if (repository != null) {
            if (repositoryName != null && !"".equals(repositoryName)) {
                repository.setRepositoryName(repositoryName);
            }
            if (address != null && !"".equals(address)) {
                repository.setAddress(address);
            }
            Integer count = repositoryMapper.updateById(repository);
            if (count > 0) {
                return "更新成功";
            } else {
                return "更新失败";
            }
        } else {
            return null;
        }
    }

    /**
     * 根据指定的当前页 / 公司名进行分页条件查询
     *
     * @param currentPage
     * @param repositoryName
     * @return
     */
    @Override
    public PageListVo selectByPage(Integer currentPage, String repositoryName) {
        // 分页对象
        Page<Repository> page = new Page(currentPage, 10);
        QueryWrapper wrapper = new QueryWrapper();
        if (repositoryName != null && !"".equals(repositoryName)) {
            wrapper.like("repository_name", repositoryName);
        }
        repositoryMapper.selectPage(page, wrapper);
        // 获取所有的记录
        List<Repository> records = page.getRecords();
        // 获取总记录数
        Integer totalCount = repositoryMapper.selectCount(wrapper);
        // 返回一个分页对象
        return new PageListVo(currentPage, 10, totalCount, records);
    }

    /**
     * 根据订单号进行出仓
     *
     * @param orderId
     */
    @Override
    public void chucang(String orderId) {
        // 出仓操作修改订单的状态为2
        ordersService.updateOrderStatus(orderId, 2);
    }

    /**
     * 获取存储在session中的工厂id
     *
     * @return
     */
    @Override
    public Repository getRepositoryBySessionId(HttpServletRequest request) {
        HttpSession session = request.getSession();
        // 获取存储在session中的工厂id
        int repositoryId = (int) session.getAttribute("repository_id");
        QueryWrapper wrapper = new QueryWrapper();
        // 根据工厂id查询工厂对象
        wrapper.eq("id", repositoryId);
        // 返回一个工厂对象
        return repositoryMapper.selectOne(wrapper);
    }

}