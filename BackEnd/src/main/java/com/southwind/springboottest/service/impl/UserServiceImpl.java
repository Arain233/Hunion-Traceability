package com.southwind.springboottest.service.impl;

import com.southwind.springboottest.dao.UserMapper;
import com.southwind.springboottest.entity.User;
import com.southwind.springboottest.page.MybatisPageHelper;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;
import com.southwind.springboottest.service.RoleService;
import com.southwind.springboottest.service.UserService;
import com.southwind.springboottest.utils.DigestUtils;
import com.southwind.springboottest.utils.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    
    @Resource
    UserMapper userMapper;

    @Resource
    RoleService roleService;

    public byte[] salt;

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public List<User> findAll() {
        return userMapper.findAll();
    }

    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }

    @Override
    public String findRoleByName(String name) {
        return roleService.findById(userMapper.findByName(name).getRole_id()).getName();
    }

    @Override
    public int save(User user) {
        if(userMapper.findByName(user.getName())!=null)
        {
            return 0;
        }
        byte[] salt = new byte[48];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        user.setSalt(Base64.getEncoder().encodeToString(salt));
        user.setPassword(PasswordEncoder.Encrypt(user.getPassword(),user.getSalt()));
        return userMapper.save(user);
    }

    @Override
    public int Update(User user) {
        User update = userMapper.findById(user.getId());
        byte[] salt = new byte[48];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        update.setSalt(Base64.getEncoder().encodeToString(salt));
        if(user.getName()!=null)
        {
            update.setName(user.getName());
        }
        if(user.getNickName()!=null)
        {
            update.setNickName(user.getNickName());
        }
        if(user.getRole_id()!=null)
        {
            update.setRole_id(user.getRole_id());
        }
        if(user.getPassword()!=null)
        {
            update.setPassword(PasswordEncoder.Encrypt(user.getPassword(),update.getSalt()));
        }
        if(user.getMobile()!=null)
        {
            update.setMobile(user.getMobile());
        }
        if(user.getEmail()!=null)
        {
            update.setEmail(user.getEmail());
        }
        return userMapper.Update(update);
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        Object label = pageRequest.getParam("label");
        if(label != null) {
            return MybatisPageHelper.findPage(pageRequest,userMapper,"findPageByLabel", label);
        }
        return MybatisPageHelper.findPage(pageRequest,userMapper);
    }

}
