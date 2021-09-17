package com.southwind.springboottest.service.impl;

import com.southwind.springboottest.dao.DealerMapper;
import com.southwind.springboottest.entity.Dealer;
import com.southwind.springboottest.page.MybatisPageHelper;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;
import com.southwind.springboottest.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealerServiceImpl implements DealerService {
    
    @Autowired
    DealerMapper dealerMapper;

    @Override
    public int save(Dealer dealer) {
        if(dealer.getName()==null)
        {
            return 0;
        }
        if(dealer.getAddress()==null)
        {
            return 0;
        }
        if(dealer.getEnterpCode()==null)
        {
            return 0;
        }
        if(dealer.getCommodity_code()==null)
        {
            return 0;
        }
        return dealerMapper.save(dealer);
    }

    @Override
    public Dealer findById(long id) {
        return dealerMapper.findById(id);
    }

    @Override
    public List<Dealer> findAll() {
        return dealerMapper.findAll();
    }

    @Override
    public int deleteById(long id) {
        return dealerMapper.deleteById(id);
    }

    @Override
    public int Update(Dealer dealer) {
        Dealer update = dealerMapper.findById(dealer.getId());
        if(dealer.getName()!=null)
        {
            update.setName(dealer.getName());
        }
        if(dealer.getAddress()!=null)
        {
            update.setAddress(dealer.getAddress());
        }
        if(dealer.getEnterpCode()!=null)
        {
            update.setEnterpCode(dealer.getEnterpCode());
        }
        return dealerMapper.Update(update);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if(label != null) {
            return MybatisPageHelper.findPage(pageRequest, dealerMapper,"findPageByLabel", label);
        }
        return MybatisPageHelper.findPage(pageRequest, dealerMapper);
    }
}
