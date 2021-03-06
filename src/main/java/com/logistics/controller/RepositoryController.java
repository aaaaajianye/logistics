package com.logistics.controller;

import com.logistics.service.IEmployeeRepository;
import com.logistics.service.IRepositoryService;
import com.logistics.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(description = "公司管理接口")
@RestController
@RequestMapping("/repository")
public class RepositoryController {

    @Autowired
    private IRepositoryService repositoryService;

    /**
     * 新增公司
     *
     * @param address
     * @param repositoryName
     * @return
     */
    @ApiOperation(value = "新增公司接口", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "repositoryName", value = "公司名",required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "address", value = "地址",required = true, dataType = "string")
    })
    @GetMapping("/insertRepository")
    public ResultJson insert(String repositoryName, String address){
        // 新增公司 - 通过公司名的方式进行新增
        return ResultJson.ok(repositoryService.insert(repositoryName, address));
    }

    /**
     * 更新公司信息接口
     *
     * @param id
     * @param repositoryName
     * @param address
     * @return
     */
    @ApiOperation(value = "更新公司信息接口", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "公司名",required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "repositoryName", value = "公司名", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "address", value = "公司地址", dataType = "string")
    })
    @PutMapping("/updateRepository")
    public ResultJson update(Integer id, String repositoryName, String address) {
        return ResultJson.ok(repositoryService.update(id, repositoryName, address));
    }

    /**
     * 分页查询公司，有公司名就按照公司名进行条件分页
     *
     * @param currentPage
     * @param repositoryName
     * @return
     */
    @ApiOperation(value = "分页查询公司，有公司名就按照公司名进行条件分页", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "currentPage", value = "当前页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "repositoryName", value = "公司名", dataType = "string")
    })
    @GetMapping("/selectByPage")
    public ResultJson selectByPage(@RequestParam(value = "currentPage",defaultValue = "1") Integer currentPage, String repositoryName){
        return ResultJson.ok(repositoryService.selectByPage(currentPage, repositoryName));
    }

    @ApiOperation(value = "出仓操作", httpMethod = "GET")
    @GetMapping("/chucang")
    public ResultJson chucang(String orderId){
        // 更新产品的配送状态
        repositoryService.chucang(orderId);
        return ResultJson.ok();
    }

    @ApiOperation(value = "根据存储在session中的工厂id查询工厂", httpMethod = "GET")
    @GetMapping("/getRepositoryBySessionId")
    public ResultJson getRepositoryBySessionId(HttpServletRequest request){
        return ResultJson.ok(repositoryService.getRepositoryBySessionId(request));
    }

}