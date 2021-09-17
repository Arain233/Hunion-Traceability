package com.southwind.springboottest.dao;

import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.entity.Material;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Mapper
public interface MaterialMapper {

    @Select("select * from material ")
    List<Material> findAll();

    @Select("select * from material where id=#{id}")
    Material findById(long id);

    @Insert("insert into material (name,mfd,originplace,enterpcode,address,commodity_code) " +
            "values (#{Name},#{MFD},#{OriginPlace},#{EnterpCode},#{Address},#{commodity_code})")
    int save(Material material);

    @Delete("delete from material where id=#{id}")
    int deleteById(long id);

    @Update("update material set " +
            "name=#{Name}," +
            "mfd=#{MFD}," +
            "originplace=#{OriginPlace}," +
            "enterpcode=#{EnterpCode}," +
            "address=#{Address}," +
            "commodity_code=#{commodity_code} " +
            "where id=#{id}" )
    int Update(Material material);

    @Select("select * from material where commodity_code=#{enterpcode}")
    List<Material> findByEnterpCode(String enterpcode);

//    @Select("select * from material where commodity_id=#{commodityId}")
//    Material findByCommId(long commodityId);

    @Select("select * from material")
    List<Material> findPage();
}
