package com.southwind.springboottest.dao;

import com.southwind.springboottest.entity.Commodity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CommodityMapper {

    @Select("select * from commodity")
    @Results(id="CommodityMap", value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "Name"),
            @Result(column = "mfd",property = "MFD"),
            @Result(column = "originplace",property = "OriginPlace"),
            @Result(column = "enterpcode",property = "EnterpCode"),
            @Result(column = "address",property = "Address"),
            @Result(column = "tra_code",property = "TraCode"),
            @Result(column = "enterpcode",property = "MaterialInfo",
                    many=@Many(
                            select = "com.southwind.springboottest.dao.MaterialMapper.findByEnterpCode",
                            fetchType = FetchType.EAGER))
    })
    List<Commodity> findAll();

    @Select("select * from commodity where id=#{id}")
    @ResultMap("CommodityMap")
    Commodity findById(long id);

    @Select("select * from commodity where enterpcode=#{enterpcode}")
    @ResultMap("CommodityMap")
    Commodity findByEnterpCode(String enterpcode);

    @Select("select * from commodity where tra_code=#{traCode}")
    @ResultMap("CommodityMap")
    Commodity findByTraCode(long traCode);

    @Insert("insert into commodity (name,mfd,originplace,enterpcode,address,tra_code) " +
            "values (#{Name},#{MFD},#{OriginPlace},#{EnterpCode},#{Address},#{TraCode})")
    int save(Commodity commodity);

    @Delete("delete from commodity where id=#{id}")
    int deleteById(long id);

    @Update("update commodity set " +
            "name=#{Name}," +
            "mfd=#{MFD}," +
            "originplace=#{OriginPlace}," +
            "enterpcode=#{EnterpCode}," +
            "address=#{Address}," +
            "tra_code=#{TraCode} " +
            "where id=#{id}" )
    int Update(Commodity commodity);

    @Select("select * from commodity")
    List<Commodity> findPage();

    /*@Update("update commodity set " +
            "name=#{name}," +
            "distribution=#{distribution}," +
            "amount=#{amount}," +
            "In_time=#{In_time} " +
            "where id=#{id}")
    int InUpdate(Commodity commodity);

    @Update("update commodity set " +
            "name=#{name}," +
            "distribution=#{distribution}," +
            "amount=#{amount}," +
            "Out_time=#{Out_time} " +
            "where id=#{id}")
    int OutUpdate(Commodity commodity);*/

}
