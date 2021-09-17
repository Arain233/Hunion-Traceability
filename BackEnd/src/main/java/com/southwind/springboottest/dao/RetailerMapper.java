package com.southwind.springboottest.dao;

import com.southwind.springboottest.entity.Dealer;
import com.southwind.springboottest.entity.Retailer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RetailerMapper {

    @Select("select * from retailer")
    @Results(id="RetailerMap", value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "Name"),
            @Result(column = "enterpcode",property = "EnterpCode"),
            @Result(column = "address",property = "Address"),
            @Result(column = "tra_code",property = "TraCode"),
            @Result(column = "dealer_code",property = "dealer_code"),
            @Result(column = "dealer_code",property = "DealerInfo",
                    one=@One(
                            select = "com.southwind.springboottest.dao.DealerMapper.findByEnterpCode",
                            fetchType = FetchType.EAGER))
    })
    List<Retailer> findAll();

    @Select("select * from retailer where id=#{id}")
    @ResultMap("RetailerMap")
    Retailer findById(long id);

    @Select("select * from retailer where tra_code=#{traCode}")
    @ResultMap("RetailerMap")
    Retailer findByTraCode(long traCode);

    @Insert("insert into retailer (name,enterpcode,address,tra_code,dealer_code) " +
            "values (#{Name},#{EnterpCode},#{Address},#{TraCode},#{dealer_code})")
    int save(Retailer retailer);

    @Delete("delete from retailer where id=#{id}")
    int deleteById(long id);

    @Update("update retailer set " +
            "name=#{Name}," +
            "enterpcode=#{EnterpCode}," +
            "address=#{Address},"+
            "tra_code=#{TraCode} " +
            "where id=#{id}")
    int Update(Retailer retailer);

    @Select("select * from retailer")
    List<Retailer> findPage();
}
