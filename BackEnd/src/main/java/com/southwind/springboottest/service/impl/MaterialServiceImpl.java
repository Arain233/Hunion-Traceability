package com.southwind.springboottest.service.impl;

import com.southwind.springboottest.dao.MaterialMapper;
import com.southwind.springboottest.entity.Material;
import com.southwind.springboottest.page.MybatisPageHelper;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;
import com.southwind.springboottest.service.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialServiceImpl implements MaterialService {
    
    @Autowired
    MaterialMapper materialMapper;

    @Override
    public int save(Material material) {

        if(material.getName()==null)
        {
            return 0;
        }
        if(material.getAddress()==null)
        {
            return 0;
        }
        if(material.getEnterpCode()==null)
        {
            return 0;
        }
        if(material.getMFD()==null)
        {
            return 0;
        }
        if(material.getOriginPlace()==null)
        {
            return 0;
        }
        if(material.getCommodity_code()==null)
        {
            return 0;
        }
        return materialMapper.save(material);
    }

    @Override
    public Material findById(long id) {
        return materialMapper.findById(id);
    }

    @Override
    public List<Material> findAll() {
        return materialMapper.findAll();
    }

    @Override
    public int deleteById(long id) {
        return materialMapper.deleteById(id);
    }

    @Override
    public int Update(Material material) {
        Material update = materialMapper.findById(material.getId());
        if(material.getName()!=null)
        {
            update.setName(material.getName());
        }
        if(material.getAddress()!=null)
        {
            update.setAddress(material.getAddress());
        }
        if(material.getEnterpCode()!=null)
        {
            update.setEnterpCode(material.getEnterpCode());
        }
        if(material.getMFD()!=null)
        {
            update.setMFD(material.getMFD());
        }
        if(material.getOriginPlace()!=null)
        {
            update.setOriginPlace(material.getOriginPlace());
        }
        return materialMapper.Update(update);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if(label != null) {
            return MybatisPageHelper.findPage(pageRequest, materialMapper,"findPageByLabel", label);
        }
        return MybatisPageHelper.findPage(pageRequest, materialMapper);
    }
}
