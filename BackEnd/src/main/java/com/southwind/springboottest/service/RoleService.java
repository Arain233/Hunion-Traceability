package com.southwind.springboottest.service;

import com.southwind.springboottest.entity.Role;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;

import java.util.List;

public interface RoleService {

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Role findById(Integer id);

    /**
     * 查询所有
     * @return
     */
    List<Role> findAll();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 进行多字段数据修改
     * @param role
     * @return
     */
    int Update(Role role);

    /**
     * 分页查找
     * @return
     */
    PageResult findPage(PageRequest pageRequest);
}
