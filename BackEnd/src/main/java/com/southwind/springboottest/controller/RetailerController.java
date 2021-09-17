package com.southwind.springboottest.controller;


import com.southwind.springboottest.IPFS.IPFSUtils;
import com.southwind.springboottest.dao.CertMapper;
import com.southwind.springboottest.entity.Certificate;
import com.southwind.springboottest.entity.Retailer;
import com.southwind.springboottest.fabric.Invoke;
import com.southwind.springboottest.fabric.Query;
import com.southwind.springboottest.http.HttpResult;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.service.RetailerService;
import com.southwind.springboottest.service.impl.RetailerServiceImpl;
import com.southwind.springboottest.utils.CertProducer;
import com.southwind.springboottest.utils.DigestUtils;
import com.southwind.springboottest.utils.PdfUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;


@Api(value="/api/retailer",tags = "零售商相关操作")
@RestController
@RequestMapping("/api/retailer")
public class RetailerController {

    @Autowired
    private RetailerService retailerService;

    @Autowired
    private CertMapper certMapper;

    @Autowired
    private Query query;

    @Autowired
    private Invoke invoke;

    private int flag;

    @ApiOperation(value = "入库相关信息")
    @PostMapping("/save")
    public HttpResult save(@RequestBody Retailer retailer) throws Exception{
//        return HttpResult.ok(retailerService.save(retailer));
        flag=retailerService.save(retailer);
        if(flag==0)
        {
            return HttpResult.error();
        }
        Retailer r=retailerService.findByTraCode(RetailerServiceImpl.code);
        return HttpResult.ok(invoke.invoke(r));
    }

    @ApiOperation(value = "查找全部零售商数据")
    @GetMapping("/findAll")
    public HttpResult findAll(){
        List<Retailer> retailers=retailerService.findAll();
        if(retailers==null)
        {
            return HttpResult.error();
        }
        return HttpResult.ok(retailers);
    }

    @ApiOperation(value = "根据ID查找零售商")
    @GetMapping("/findById/{id}")
    public HttpResult findById(@PathVariable("id") long id){
        Retailer retailer=retailerService.findById(id);
        if(retailer==null)
        {
            return HttpResult.error();
        }
        return HttpResult.ok(retailer);
    }

    @ApiOperation(value = "根据溯源码查找零售商")
    @GetMapping("/findByTra_code/{tra_code}")
    public HttpResult findByCode(@PathVariable("tra_code") long tra_code){
        Retailer retailer=retailerService.findByTraCode(tra_code);
        if(retailer==null)
        {
            return HttpResult.error();
        }
        String digest= String.valueOf(query.query(retailer));
        if(Objects.equals(digest, DigestUtils.DigestProducer(retailer)))
        {
            return HttpResult.ok(retailer);
        }
        return HttpResult.error();
    }

    @ApiOperation(value = "生成商品身份证,并返回二进制流")
    @GetMapping("/cert/{id}")
    public HttpResult certProduce(@PathVariable("id") long id) throws IOException {
        Retailer retailer=retailerService.findById(id);
        CertProducer.certProduce(retailer);
        Certificate certificate=new Certificate(PdfUtils.getPDFBinary(CertProducer.newPDFPath),retailer.getTraCode());
        File file=new File(CertProducer.newPDFPath);
        String hash= IPFSUtils.upload(file);
        certificate.setHash(hash);
        certMapper.save(certificate);
        Object o=certificate.getCertificate_binary();
        return HttpResult.ok(o);
    }

    @ApiOperation(
            value = "零售商数据修改",
            notes = "传入需要修改的零售商ID及需要修改的数据，不修改的数据置空值"
    )
    @PutMapping("/update")
    public HttpResult update(@RequestBody Retailer retailer){
        flag=retailerService.Update(retailer);
        if(flag==0)
        {
            return HttpResult.error();
        }
        return HttpResult.ok(invoke.invoke(retailerService.findById(retailer.getId())));
    }

    @ApiOperation(value = "根据零售商ID删除数据")
    @DeleteMapping("/deleteById/{id}")
    public HttpResult deleteById(@PathVariable("id") long id){
        flag=retailerService.deleteById(id);
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
        return HttpResult.ok(retailerService.findPage(pageRequest));
    }
}
