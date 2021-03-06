package com.logistics.service;

import com.logistics.entity.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();

    void insert(String username, String password);

    void update(Integer id, String username);

}