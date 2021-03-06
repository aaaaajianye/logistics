package com.logistics.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.logistics.entity.User;
import com.logistics.mapper.UserMapper;
import com.logistics.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> getUsers() {
        List<User> list = userMapper.selectList(null);
        return list;
    }

    @Override
    public void insert(String username, String password) {
        User user = new User(username, password);
        userMapper.insert(user);
    }

    @Override
    public void update(Integer id, String username) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        User user = userMapper.selectOne(wrapper);
        user.setUsername(username);
        userMapper.updateById(user);
    }

}