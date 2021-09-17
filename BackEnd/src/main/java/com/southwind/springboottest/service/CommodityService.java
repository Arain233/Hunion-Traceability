package com.southwind.springboottest.service;

import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.page.PageRequest;
import com.southwind.springboottest.page.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommodityService {

     /**
      * 商品信息保存
      * @param commodity
      * @return
      */
     int save(Commodity commodity);
     /**
      * 根据id查询
      * @param id
      * @return
      */
     Commodity findById(long id);

     /**
      * 根据溯源码查询
      * @param tra_code
      * @return
      */
     Commodity findByTraCode(long tra_code);

     /**
      * 查询所有
      * @return
      */
     List<Commodity> findAll();

     /**
      * 根据id删除
      * @param id
      * @return
      */
     int deleteById(long id);

     /**
      * 进行多字段数据修改
      * @param commodity
      * @return
      */
     int Update(Commodity commodity);

     /**
      * 分页查找
      * @return
      */
     PageResult findPage(PageRequest pageRequest);


}
