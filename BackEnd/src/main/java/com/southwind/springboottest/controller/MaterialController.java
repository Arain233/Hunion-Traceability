package com.southwind.springboottest.controller;


import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.entity.Material;
import com.southwind.springboottest.http.HttpResult;
import com.southwind.springboottest.page.PageRequest;

import com.southwind.springboottest.service.MaterialService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api(value="/api/material",tags = "原料相关操作")
@RestController
@RequestMapping("/api/material")
public class MaterialController {

    @Autowired
    private MaterialService materialService;

    private int flag;

    @ApiOperation(value = "入库相关信息")
    @PostMapping("/save")
    public HttpResult save(@RequestBody Material material){
        flag=materialService.save(material);
        if(flag==0)
        {
            return HttpResult.error();
        }
        return HttpResult.ok();
    }

    @ApiOperation(value = "查找全部原料数据")
    @GetMapping("/findAll")
    public HttpResult findAll(){
        List<Material> materials=materialService.findAll();
        if(materials==null)
        {
            return HttpResult.error();
        }
        return HttpResult.ok(materials);
    }

    @ApiOperation(value = "根据ID查找原料")
    @GetMapping("/findById/{id}")
    public HttpResult findById(@PathVariable("id") long id){
        Material material=materialService.findById(id);
        if(material==null)
        {
            return HttpResult.error();
        }
        return HttpResult.ok(material);
    }

    @ApiOperation(
            value = "原料数据修改",
            notes = "传入需要修改的原料ID及需要修改的数据，不修改的数据置空值"
    )
    @PutMapping("/update")
    public HttpResult update(@RequestBody Material material){
        flag=materialService.Update(material);
        if(flag==0)
        {
            return HttpResult.error();
        }
        return HttpResult.ok();
    }

    @ApiOperation(value = "根据原料ID删除数据")
    @DeleteMapping("/deleteById/{id}")
    public HttpResult deleteById(@PathVariable("id") long id){
        flag=materialService.deleteById(id);
        if(flag==0)
        {
            return HttpResult.error();
        }
        return HttpResult.ok();
    }

    /**
     * 分页查找
     * @param pageRequest
     * @return
     */
    @ApiOperation(
            value = "分页查找",
            notes = "传入页码与每页数量"
    )
    @PostMapping(value="/findPage")
    public HttpResult findPage(@RequestBody PageRequest pageRequest) {
        return HttpResult.ok(materialService.findPage(pageRequest));
    }
}
