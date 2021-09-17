package com.southwind.springboottest.service;

import com.southwind.springboottest.entity.Material;
import com.southwind.springboottest.entity.Material;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MaterialService {
    
    /**
     * 原料信息保存
     * @param material
     * @return
     */
    int save(Material material);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Material findById(long id);

    /**
     * 查询所有
     * @return
     */
    List<Material> findAll();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 进行多字段数据修改
     * @param material
     * @return
     */
    int Update(Material material);

    /**
     * 分页查找
     * @return
     */
    PageResult findPage(PageRequest pageRequest);
}
