package com.southwind.springboottest.service.impl;

import com.southwind.springboottest.dao.CommodityMapper;
import com.southwind.springboottest.dao.DealerMapper;
import com.southwind.springboottest.dao.RetailerMapper;

import com.southwind.springboottest.entity.Retailer;
import com.southwind.springboottest.page.MybatisPageHelper;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;
import com.southwind.springboottest.service.RetailerService;
import com.southwind.springboottest.utils.Tra_codeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public class RetailerServiceImpl implements RetailerService {

    @Autowired
    RetailerMapper retailerMapper;

    @Autowired
    DealerMapper dealerMapper;

    @Autowired
    CommodityMapper commodityMapper;

    public static long code;

    @Override
    public int save(Retailer retailer) {
        if(retailer.getName()==null)
        {
            return 0;
        }
        if(retailer.getAddress()==null)
        {
            return 0;
        }
        if(retailer.getEnterpCode()==null)
        {
            return 0;
        }
        if(retailer.getDealer_code()==null)
        {
            return 0;
        }
        code=Tra_codeUtils.code();
        retailer.setTraCode(code);
        return retailerMapper.save(retailer);
    }

    @Override
    public Retailer findById(long id) {
        return retailerMapper.findById(id);
    }

    @Override
    public List<Retailer> findAll() {
        return retailerMapper.findAll();
    }

    @Override
    public int deleteById(long id) {
        return retailerMapper.deleteById(id);
    }

    @Override
    public int Update(Retailer retailer) {
        Retailer update = retailerMapper.findById(retailer.getId());
        if(retailer.getName()!=null)
        {
            update.setName(retailer.getName());
        }
        if(retailer.getAddress()!=null)
        {
            update.setAddress(retailer.getAddress());
        }
        if(retailer.getEnterpCode()!=null)
        {
            update.setEnterpCode(retailer.getEnterpCode());
        }
        return retailerMapper.Update(update);
    }

    @Override
    public Retailer findByTraCode(long traCode) {
        return retailerMapper.findByTraCode(traCode);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if(label != null) {
            return MybatisPageHelper.findPage(pageRequest, retailerMapper,"findPageByLabel", label);
        }
        return MybatisPageHelper.findPage(pageRequest, retailerMapper);
    }
}
