package com.southwind.springboottest.repository;

import com.southwind.springboottest.dao.RoleMapper;
import com.southwind.springboottest.dao.UserMapper;
import com.southwind.springboottest.entity.Role;
import com.southwind.springboottest.entity.User;
import com.southwind.springboottest.service.UserService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.GsonTester;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Table;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@SpringBootTest
@RunWith(SpringRunner.class)
public class User_RoleTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserService userService;

    @Autowired
    RoleMapper roleMapper;



    @Test
    public void UserTest()
    {
        Logger logger = Logger.getLogger(User_RoleTest.class);
        logger.debug("13123");

    }

    @Test
    public void RoleTest()
    {
        roleMapper.save(new Role("管理员"));
        roleMapper.save(new Role("普通用户"));
    }

    @Test
    public void User_Role()
    {
        System.out.println(roleMapper.findAll().toString());
        System.out.println(userMapper.findAll().toString());
        System.out.println(userMapper.findById(1));
        System.out.println(roleMapper.findById(1));
        userMapper.findByName("admin");
    }

    @Test
    public void UserUpdate()
    {
        User user=userMapper.findById(13);
        user.setMobile("10086");
        user.setPassword("10101010");
        user.setRole_id(2);
        userService.Update(user);
    }

    @Test
    public void DeleteById()
    {
        userMapper.deleteById(1);
    }

    @Test
    public void RoleUpdate()
    {
        Role role=roleMapper.findById(1);
        role.setName("超级管理员");
        roleMapper.Update(role);
    }

    @Test
    public void saltTest()
    {
        byte[] salt = new byte[48];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        Base64.getEncoder().encodeToString(salt);
        System.out.println(Arrays.toString(salt));
        System.out.println(Base64.getEncoder().encodeToString(salt));
    }
}
