package com.southwind.springboottest.controller;


import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.entity.Dealer;
import com.southwind.springboottest.http.HttpResult;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.service.DealerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value="/api/dealer",tags = "经销商相关操作")
@RestController
@RequestMapping("/api/dealer")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    private int flag;

    @ApiOperation(value = "入库相关信息")
    @PostMapping("/save")
    public HttpResult save(@RequestBody Dealer dealer){
        flag=dealerService.save(dealer);
        if(flag==0)
        {
            return HttpResult.error();
        }
        return HttpResult.ok();
    }

    @ApiOperation(value = "查找全部经销商数据")
    @GetMapping("/findAll")
    public HttpResult findAll(){
        return HttpResult.ok(dealerService.findAll());
    }

    @ApiOperation(value = "根据ID查找经销商")
    @GetMapping("/findById/{id}")
    public HttpResult findById(@PathVariable("id") long id){
        List<Dealer> dealers=dealerService.findAll();
        if(dealers==null)
        {
            return HttpResult.error();
        }
        return HttpResult.ok(dealers);
    }
    

    @ApiOperation(
            value = "经销商数据修改",
            notes = "传入需要修改的经销商ID及需要修改的数据，不修改的数据置空值"
    )
    @PutMapping("/update")
    public HttpResult update(@RequestBody Dealer dealer){
        flag=dealerService.Update(dealer);
        if(flag==0)
        {
            return HttpResult.error();
        }
        return HttpResult.ok();
    }

    @ApiOperation(value = "根据经销商ID删除数据")
    @DeleteMapping("/deleteById/{id}")
    public HttpResult deleteById(@PathVariable("id") long id){
        flag=dealerService.deleteById(id);
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
        return HttpResult.ok(dealerService.findPage(pageRequest));
    }
}
