package com.southwind.springboottest.service.impl;

import com.southwind.springboottest.dao.RoleMapper;
import com.southwind.springboottest.entity.Role;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;
import com.southwind.springboottest.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    RoleMapper roleMapper;
    @Override
    public Role findById(Integer id) {
        return roleMapper.findById(id);
    }

    @Override
    public List<Role> findAll() {
        return roleMapper.findAll();
    }

    @Override
    public int deleteById(Integer id) {
        return roleMapper.deleteById(id);
    }

    @Override
    public int Update(Role role) {
        if(role.getName()!=null&&roleMapper.findById(role.getId())!=null)
        {
            return roleMapper.Update(role);
        }
        return 0;
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return null;
    }
}
