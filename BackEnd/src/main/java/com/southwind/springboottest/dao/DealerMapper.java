package com.southwind.springboottest.dao;

import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.entity.Dealer;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface DealerMapper {

    @Select("select * from dealer")
    @Results(id="DealerMap", value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "Name"),
            @Result(column = "enterpcode",property = "EnterpCode"),
            @Result(column = "address",property = "Address"),
            @Result(column = "commodity_code",property = "commodity_code"),
            @Result(column = "commodity_code",property = "CommodityInfo",
                    one=@One(
                            select = "com.southwind.springboottest.dao.CommodityMapper.findByEnterpCode",
                            fetchType = FetchType.EAGER))
    })
    List<Dealer> findAll();

    @Select("select * from dealer where id=#{id}")
    @ResultMap("DealerMap")
    Dealer findById(long id);

    @Select("select * from dealer where enterpcode=#{enterpcode}")
    @ResultMap("DealerMap")
    Dealer findByEnterpCode(String enterpcode);

    @Select("select * from dealer where tra_code=#{traCode}")
    Dealer findByTraCode(long traCode);

    @Insert("insert into dealer (name,enterpcode,address,commodity_code) " +
            "values (#{Name},#{EnterpCode},#{Address},#{commodity_code})")
    int save(Dealer dealer);

    @Delete("delete from dealer where id=#{id}")
    int deleteById(long id);

    @Update("update dealer set " +
            "name=#{Name}," +
            "enterpcode=#{EnterpCode}," +
            "address=#{Address} " +
            "where id=#{id}")
    int Update(Dealer dealer);

    @Select("select * from dealer")
    List<Dealer> findPage();
}
