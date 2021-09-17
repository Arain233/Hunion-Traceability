package com.southwind.springboottest.dao;

import com.southwind.springboottest.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface UserMapper {

    @Select("select * from user")
    @Results(id="userMap", value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "nikeName",property = "nikeName"),
            @Result(column = "email",property = "email"),
            @Result(column = "mobile",property = "mobile"),
            @Result(column = "role_id",property = "role_id"),
            @Result(column = "role_id",property = "role",
                    one=@One(
                            select = "com.southwind.springboottest.dao.RoleMapper.findByRoleId",
                            fetchType = FetchType.EAGER))
            })
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    User findByUserId(Integer userid);

    @Select("select * from user where id=#{id}")
    @ResultMap(value = "userMap")
    User findById(Integer id);

    @Select("select * from user where name=#{name}")
    @ResultMap(value = "userMap")
    User findByName(String name);

    @Select("select * from user where role_id=#{role_id}")
    User findByRoleId(Integer role_id);

    @Insert("insert into user (name,nickName,password,salt,email,mobile,role_id) " +
            "values (#{name},#{nickName},#{password},#{salt},#{email},#{mobile},#{role_id})")
    int save(User user);

    @Delete("delete from user where id=#{id}")
    int deleteById(Integer id);

    @Update("update user set " +
            "name=#{name}," +
            "nickName=#{nickName}," +
            "password=#{password}," +
            "salt=#{salt}," +
            "email=#{email}," +
            "mobile=#{mobile}," +
            "role_id=#{role_id} " +
            "where id=#{id}")
    int Update(User user);

    @Select("select * from user")
    @ResultMap(value = "userMap")
    List<User> findPage();
}
