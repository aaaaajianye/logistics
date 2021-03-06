package com.logistics.controller;

import com.logistics.service.IAdminService;
import com.logistics.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(description = "管理员管理接口")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    /**
     * 管理员登录接口
     *
     * @param username
     * @param password
     * @param request
     * @return
     */
    @ApiOperation(value = "管理员登录接口", httpMethod = "GET")
    @GetMapping("/loginAdmin")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "username", value = "管理员的用户名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "password", value = "管理员的密码", required = true, dataType = "string"),
    })
    public ResultJson login(String username, String password, HttpServletRequest request){
        return ResultJson.ok(adminService.login(username, password, request));
    }

    /**
     * 新增管理员
     *
     * @param username
     *      * @param password
     *      * @param name
     *      * @param phone
     *      * @return
     */
    @ApiOperation(value = "新增管理员接口", httpMethod = "POST")
    @PostMapping("/insertAdmin")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "username", value = "新增管理员的用户名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "password", value = "新增管理员的密码", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "name", value = "新增管理员的姓名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "新增管理员的联系电话", required = true, dataType = "string")
    })
    public ResultJson insertAdmin(String username, String password, String name, String phone){
        return ResultJson.ok(adminService.insert(username, password, name, phone));
    }

    /**
     * 更新管理员
     *
     * @param username
     * @param password
     * @param name
     * @param phone
     * @return
     */
    @ApiOperation(value = "更新管理员接口", httpMethod = "PUT")
    @PutMapping("/updateAdmin")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "username", value = "要修改管理员的用户名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "password", value = "管理员新密码", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "name", value = "管理员新姓名", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "管理员新联系电话", dataType = "string")
    })
    public ResultJson updateAdmin(String username, String password, String name, String phone){
        return ResultJson.ok(adminService.update(username, password, name, phone));
    }

    /**
     * 获取已经登录的admin对象
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "获取管理员接口", httpMethod = "GET")
    @GetMapping("/getAdmin")
    public ResultJson getAdmin(HttpServletRequest request){
        return ResultJson.ok(adminService.getAdmin(request));
    }

}