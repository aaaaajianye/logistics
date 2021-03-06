package com.logistics.controller;

import com.logistics.service.ITruckRepositoryService;
import com.logistics.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@Api(description = "公司货车管理接口")
@RestController
@RequestMapping("/truckRepository")
public class TruckRepositoryController {

    @Autowired
    private ITruckRepositoryService iTruckRepositoryService;

    /**
     * 新增
     *
     * @param carNo
     * @param repositoryId
     * @param isTransport
     * @return
     */
    @ApiOperation(value = "公司新增货车", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "carNo", value = "货车车牌号", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "repositoryId", value = "公司id", dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "isTransport", value = "是否正在运输", dataType = "int")
    })
    @PostMapping("/insert")
    public ResultJson insert(String carNo, Integer repositoryId, Integer isTransport){
        return ResultJson.ok(iTruckRepositoryService.insert(carNo, repositoryId, isTransport));
    }

    /**
     * 根据货车id更新货车的运输状态
     *
     * @param id
     * @param isTransport
     * @return
     */
    @ApiOperation(value = "根据货车id更新货车运输状态", httpMethod = "PUT")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "货车id", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "isTransport", value = "货车是否正在运输:[0]未运输 [1]正在运输", required = true, dataType = "int")
    })
    @PutMapping("/update")
    public ResultJson update(Integer id, Integer isTransport){
        return ResultJson.ok(iTruckRepositoryService.update(id, isTransport));
    }

    /**
     * 根据货车id删除公司的货车
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据货车id删除公司的货车", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "id", value = "货车id", required = true, dataType = "int")
    })
    @DeleteMapping("/delete")
    public ResultJson update(Integer id) {
        return ResultJson.ok(iTruckRepositoryService.delete(id));
    }

    /**
     * 根据指定的条件进行分页查询的操作
     *
     * @param currentPage
     * @param carNo
     * @param repositoryId
     * @return
     */
    @ApiOperation(value = "根据指定的条件进行分页查询的操作", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "currentPage", value = "指定页", required = true, dataType = "int"),
            @ApiImplicitParam(paramType="query", name = "carNo", value = "货车车牌号", dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "repositoryId", value = "公司仓库id", dataType = "int")
    })
    @GetMapping("/selectByPage")
    public ResultJson selectByPage(Integer currentPage, String carNo, Integer repositoryId){
        return ResultJson.ok(iTruckRepositoryService.selectByPage(currentPage, carNo, repositoryId));
    }

}