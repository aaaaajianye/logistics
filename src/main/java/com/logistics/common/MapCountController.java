package com.logistics.common;

import com.logistics.service.IMapApiSerevice;
import com.logistics.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Api(description = "调用百度地图计算距离的接口")
@RestController
@RequestMapping("/countMap")
public class MapCountController {

    @Autowired
    private IMapApiSerevice mapApiSerevice;


    @ApiOperation(value = "根据两个地址获取他们的距离/公里", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType="query", name = "address", value = "输入起始地址", required = true, dataType = "string"),
            @ApiImplicitParam(paramType="query", name = "otherAddress", value = "输入另外一个地址", required = true, dataType = "string")
    })
    @GetMapping("/getLatAndLngByAddress")
    public ResultJson getLatAndLngByAddress(String address, String otherAddress) {
        return ResultJson.ok(mapApiSerevice.getDistanceByTwoPlace(address, otherAddress));
    }

}