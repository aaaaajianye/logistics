package com.logistics.controller;

import com.logistics.service.IEmployeeTypeService;
import com.logistics.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 员工种类管理接口
 */

@Api(description = "员工工种管理接口")
@RestController
@RequestMapping("/employeeType")
public class EmployeeTypeController {

    @Autowired
    private IEmployeeTypeService employeeType;

    /**
     * 新增员工工种的接口
     *
     * @param employeeTypeName
     * @return
     */
    @ApiOperation(value = "新增员工种类接口", httpMethod = "POST")
    @PostMapping("/insertEmployeeType")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "employeeTypeName", value = "员工的工种名", required = true, dataType = "string")
    })
    public ResultJson insertEmployeeType(String employeeTypeName){
        return ResultJson.ok(employeeType.insert(employeeTypeName));
    }

    /**
     * 更新员工工种的接口
     *
     * @param id
     * @param employeeTypeName
     * @return
     */
    @ApiOperation(value = "根据id更新员工种类", httpMethod = "PUT")
    @PutMapping("/updateEmployeeType")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "员工工种的主键id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "employeeTypeName", value = "员工的工种名", required = true, dataType = "string")
    })
    public ResultJson updateEmployeeType(Integer id, String employeeTypeName){
        return ResultJson.ok(employeeType.update(id, employeeTypeName));
    }

    /**
     * 根据指定页面/工种名分页查找所有的工种
     *
     * @param currentPage
     * @return
     */
    @ApiOperation(value = "根据指定页面/工种名分页查找所有的工种", httpMethod = "GET")
    @GetMapping("/selectByPage")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "currentPage", value = "指定页面进行展示", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "typeName", value = "员工工种名", dataType = "string")
    })
    public ResultJson selectByPage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage, String typeName){
        return ResultJson.ok(employeeType.selectByPage(currentPage, typeName));
    }

}