package com.southwind.springboottest.service;

import com.southwind.springboottest.entity.User;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    User findById(Integer id);

    /**
     * 根据name查询
     * @param name
     * @return
     */
    User findByName(String name);


    /**
     * 根据name查询 rolename
     * @param name
     * @return
     */
    String findRoleByName(String name);

    /**
     * 查询所有
     * @return
     */
    List<User> findAll();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 新增用户
     * @param user
     * @return
     */
    int save(User user);

    /**
     * 进行多字段数据修改
     * @param user
     * @return
     */
    int Update(User user);

    /**
     * 分页查找
     * @return
     */
    PageResult findPage(PageRequest pageRequest);


}
