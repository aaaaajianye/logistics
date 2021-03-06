package com.logistics.common;

import com.logistics.utils.CommonUtils;
import com.logistics.utils.OSSClientUtils;
import com.logistics.utils.ResultJson;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(description = "图片/文件上传的接口")
@RestController
@RequestMapping("/upload")
public class UploadController {

    /**
     * 图片上传接口
     *
     * @return
     */
    @ApiOperation(value = "图片/文件 上传接口", httpMethod = "POST")
    @PostMapping("/uploadFile")
    public ResultJson uploadFile(@RequestParam("upload") MultipartFile upload){
        String path = CommonUtils.uploadFile(upload);
        System.out.println("图片url路径：" + path + "");
        return ResultJson.ok(path);
    }

}