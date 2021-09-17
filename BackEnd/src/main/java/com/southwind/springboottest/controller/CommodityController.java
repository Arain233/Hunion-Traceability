package com.southwind.springboottest.controller;


import com.southwind.springboottest.fabric.Invoke;
import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.fabric.Query;
import com.southwind.springboottest.http.HttpResult;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.service.CommodityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;


@Api(value="/api/commodity",tags = "商品相关操作")
@RestController
@RequestMapping("/api/commodity")
public class CommodityController {

    @Autowired
    private CommodityService commodityService;

    private int flag;

    @ApiOperation(value = "入库相关信息")
    @PostMapping("/save")
    public HttpResult save(@RequestBody Commodity commodity){
        flag=commodityService.save(commodity);
        if(flag==0)
        {
            return HttpResult.error();
        }
        return HttpResult.ok();
    }

    @ApiOperation(value = "查找全部商品数据")
    @GetMapping("/findAll")
    public HttpResult findAll(){
        List<Commodity> commodities=commodityService.findAll();
        if(commodities==null)
        {
            return HttpResult.error();
        }
        return HttpResult.ok(commodities);
    }

    @ApiOperation(value = "根据ID查找商品")
    @GetMapping("/findById/{id}")
    public HttpResult findById(@PathVariable("id") long id){
        Commodity commodity=commodityService.findById(id);
        if(commodity==null)
        {
            return HttpResult.error();
        }
        return HttpResult.ok(commodity);
    }

    @ApiOperation(value = "根据溯源码查找商品")
    @GetMapping("/findByTra_code/{tra_code}")
    public HttpResult findByCode(@PathVariable("tra_code") long tra_code){
        Commodity commodity=commodityService.findByTraCode(tra_code);
        if(commodity==null)
        {
            return HttpResult.error();
        }
        return HttpResult.ok(commodity);
    }

    @ApiOperation(
            value = "商品数据修改",
            notes = "传入需要修改的商品ID及需要修改的数据，不修改的数据置空值"
    )
    @PutMapping("/update")
    public HttpResult update(@RequestBody Commodity commodity){
        flag=commodityService.Update(commodity);
        if(flag==0)
        {
            return HttpResult.error();
        }
        return HttpResult.ok();
    }

    @ApiOperation(value = "根据商品ID删除数据")
    @DeleteMapping("/deleteById/{id}")
    public HttpResult deleteById(@PathVariable("id") long id){
        flag=commodityService.deleteById(id);
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
        return HttpResult.ok(commodityService.findPage(pageRequest));
    }
}
