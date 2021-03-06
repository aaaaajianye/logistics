package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.logistics.entity.PageListVo;
import com.logistics.entity.TruckRepository;
import com.logistics.mapper.TruckRepositoryMapper;
import com.logistics.service.ITruckRepositoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author shiwen
 * @date 2020/6/12
 */
@Service
public class TruckRepositoryServiceImpl implements ITruckRepositoryService {

    @Resource
    private TruckRepositoryMapper truckRepositoryMapper;

    /**
     * 新增卡车
     *
     * @param carNo
     * @param repositoryId
     * @param isTransport
     * @return
     */
    @Override
    public String insert(String carNo, Integer repositoryId, Integer isTransport) {
        // 初始化一个对象
        TruckRepository truckRepository = new TruckRepository(carNo, repositoryId, isTransport);
        // 新增一个卡车对象到数据库
        Integer count = truckRepositoryMapper.insert(truckRepository);
        if (count > 0) {
            return "卡车新增成功";
        } else {
            return "卡车新增失败";
        }
    }

    /**
     * 更新卡车
     *
     * @param id
     * @param isTransport
     * @return
     */
    @Override
    public String update(Integer id, Integer isTransport) {
        TruckRepository truckRepository = truckRepositoryMapper.selectById(id);
        // 判断数据库中是否存在这个对象
        if (truckRepository.getId() != 0 && truckRepository != null) {
            truckRepository.setIsTransport(isTransport);
        }
        Integer count = truckRepositoryMapper.updateById(truckRepository);
        if (count > 0) {
            return "卡车更新成功";
        } else {
            return "卡车更新失败";
        }
    }

    /**
     * 删除卡车
     *
     * @param id
     * @return
     */
    @Override
    public String delete(Integer id) {
        // 根据id进行删除
        Integer count = truckRepositoryMapper.deleteById(id);
        if (count > 0) {
            return "卡车删除成功";
        } else {
            return "卡车删除失败";
        }
    }

    /**
     * 根据条件进行分页查询
     *
     * @param currentPage
     * @param carNo
     * @param repositoryId
     * @return
     */
    @Override
    public PageListVo selectByPage(Integer currentPage, String carNo, Integer repositoryId) {
        // 指定页数进行分页，每页10条数据
        Page<TruckRepository> page = new Page<>(currentPage, 10);
        QueryWrapper wrapper = new QueryWrapper();
        // 判断车牌号是否为空
        if (carNo != null && !"".equals(carNo)) {
            wrapper.like("car_no", carNo);
        }
        // 判断公司id是否为空
        if (repositoryId != null && !"".equals(repositoryId)) {
            wrapper.eq("repository_id", repositoryId);
        }
        // 进行查询的操作
        truckRepositoryMapper.selectPage(page, wrapper);
        List<TruckRepository> records = page.getRecords();
        Integer totalCount = truckRepositoryMapper.selectCount(wrapper);
        return new PageListVo(currentPage, 10, totalCount, records);
    }

}