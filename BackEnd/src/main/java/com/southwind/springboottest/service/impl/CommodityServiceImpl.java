package com.southwind.springboottest.service.impl;

import com.southwind.springboottest.dao.CommodityMapper;
import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.fabric.Invoke;
import com.southwind.springboottest.fabric.Query;
import com.southwind.springboottest.http.HttpResult;
import com.southwind.springboottest.page.MybatisPageHelper;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;
import com.southwind.springboottest.service.CommodityService;
import com.southwind.springboottest.utils.Tra_codeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Resource
    private CommodityMapper commodityMapper;
    @Autowired
    private Invoke invoke;
    @Autowired
    private Query query;

    public static long code;

    @Override
    public int save(Commodity commodity) {
        code=Tra_codeUtils.code();
        commodity.setTraCode(code);
        if(commodity.getOriginPlace()==null)
        {
            return 0;
        }
        if(commodity.getName()==null)
        {
            return 0;
        }
        if(commodity.getEnterpCode()==null)
        {
            return 0;
        }
        if(commodity.getMFD()==null)
        {
            return 0;
        }
        if(commodity.getAddress()==null)
        {
            return 0;
        }
        return commodityMapper.save(commodity);
    }

    @Override
    public Commodity findById(long id) {
        Commodity commodity = commodityMapper.findById(id);
        return commodity;
    }

    @Override
    public List<Commodity> findAll() {
        List<Commodity> commodities = commodityMapper.findAll();
        return commodities;
    }

    @Override
    public int deleteById(long id) {
        return commodityMapper.deleteById(id);
    }

    @Override
    public int Update(Commodity commodity) {
        Commodity update = commodityMapper.findById(commodity.getId());
        if(commodity.getName()!=null)
        {
            update.setName(commodity.getName());
        }
        if(commodity.getAddress()!=null)
        {
            update.setAddress(commodity.getAddress());
        }
        if(commodity.getEnterpCode()!=null)
        {
            update.setEnterpCode(commodity.getEnterpCode());
        }
        if(commodity.getMFD()!=null)
        {
            update.setMFD(commodity.getMFD());
        }
        if(commodity.getOriginPlace()!=null)
        {
            update.setOriginPlace(commodity.getOriginPlace());
        }
        return commodityMapper.Update(update);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if(label != null) {
            return MybatisPageHelper.findPage(pageRequest, commodityMapper,"findPageByLabel", label);
        }
        return MybatisPageHelper.findPage(pageRequest, commodityMapper);
    }

    @Override
    public Commodity findByTraCode(long tra_code) {
        Commodity commodity = commodityMapper.findByTraCode(tra_code);
//        try {
//            query.query(commodity);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return commodity;
    }
}
