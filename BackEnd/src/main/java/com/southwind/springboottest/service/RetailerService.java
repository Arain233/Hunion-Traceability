package com.southwind.springboottest.service;

import com.southwind.springboottest.entity.Retailer;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
@Service
public interface RetailerService {

    /**
     * 零售商信息保存
     * @param retailer
     * @return
     */
    int save(Retailer retailer);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    Retailer findById(long id);

    /**
     * 根据溯源码查询
     * @param traCode
     * @return
     */
    Retailer findByTraCode(long traCode);

    /**
     * 查询所有
     * @return
     */
    List<Retailer> findAll();

    /**
     * 根据id删除
     * @param id
     * @return
     */
    int deleteById(long id);

    /**
     * 进行多字段数据修改
     * @param retailer
     * @return
     */
    int Update(Retailer retailer);

    /**
     * 分页查找
     * @return
     */
    PageResult findPage(PageRequest pageRequest);
}
