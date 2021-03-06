package com.logistics.controller;

import com.logistics.service.IEmployeeRepository;
import com.logistics.service.IEmployeeService;
import com.logistics.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(description = "员工管理接口")
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IEmployeeRepository employeeRepository;

    /**
     * 员工登录接口
     *
     * @param username
     * @param password
     * @return
     */
    @ApiOperation(value = "员工登录接口", httpMethod = "GET")
    @GetMapping("/login")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "username", value = "员工账号", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "password", value = "员工密码", required = true, dataType = "string")
    })
    public ResultJson login(String username, String password, HttpServletRequest request){
        return ResultJson.ok(employeeService.login(username, password, request));
    }

    /**
     * 新增员工的接口
     *
     * @param username
     * @param password
     * @param name
     * @param typeName
     * @param phone
     * @return
     */
    @ApiOperation(value = "新增员工接口", httpMethod = "POST")
    @PostMapping("/insert")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "username", value = "员工的用户名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "password", value = "员工的密码", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "name", value = "员工的姓名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "typeName", value = "员工的工种名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "员工的电话", required = true, dataType = "string")
    })
    public ResultJson insert(String username, String password, String name, String typeName, String phone){
        return ResultJson.ok(employeeService.insert(username, password, name, typeName, phone));
    }

    /**
     * 更新员工的接口
     *
     * @param username
     * @param password
     * @param name
     * @param typeName
     * @param phone
     * @return
     */
    @ApiOperation(value = "更新员工接口", httpMethod = "PUT")
    @PutMapping("/update")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "username", value = "员工的用户名", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "password", value = "员工的密码", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "name", value = "员工的姓名", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "typeName", value = "员工的工种名", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "phone", value = "员工的电话", dataType = "string")
    })
    public ResultJson update(String username, String password, String name, String typeName, String phone){
        return ResultJson.ok(employeeService.update(username, password, name, typeName, phone));
    }

    /**
     * 删除员工的接口
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "删除员工的接口", httpMethod = "DELETE")
    @GetMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "员工id", required = true, dataType = "int")
    })
    public ResultJson delete(Integer id){
        return ResultJson.ok(employeeService.deletedById(id));
    }

    /**
     * 分页查询员工的接口（有分页就分页,有姓名就模糊查询）
     *
     * @param currentPage
     * @param name
     * @return
     */
    @ApiOperation(value = "分页查询员工的接口（有分页就分页,有姓名就模糊查询）", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "currentPage", value = "指定当前页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "name", value = "模糊查询的员工姓名", dataType = "string")
    })
    @GetMapping("/selectByPage")
    public ResultJson selectByPage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage, String name){
        // 根据指定的姓名进行模糊查询，并进行分页
        return ResultJson.ok(employeeService.selectByPage(currentPage, name));
    }

    /**
     * 查询员工对象
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "登录员工信息查询接口", httpMethod = "GET")
    @GetMapping("/getEmployeeById")
    public ResultJson getEmployeeById(HttpServletRequest request) {
        return ResultJson.ok(employeeService.getById(request));
    }

    /**
     * 根据员工id查询公司对象
     *
     * @param request
     * @return
     */
    @ApiOperation(value = "根据员工id查询公司", httpMethod = "GET")
    @GetMapping("/getRepositoryById")
    public ResultJson getRepositoryById(HttpServletRequest request) {
        return ResultJson.ok(employeeRepository.getRepositoryById(request));
    }

}