package com.logistics.controller;

import com.logistics.service.IUserService;
import com.logistics.utils.ResultJson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/getUsers")
    public ResultJson getUsers(){
        return ResultJson.ok(userService.getUsers());
    }

    @PutMapping("/insert")
    public ResultJson insertUser(String username, String password){
        userService.insert(username, password);
        return ResultJson.ok();
    }

    @PutMapping("/update")
    public ResultJson update(Integer id, String username){
        userService.update(id, username);
        return ResultJson.ok();
    }

}