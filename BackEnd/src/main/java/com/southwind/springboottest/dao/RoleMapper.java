package com.southwind.springboottest.dao;

import com.southwind.springboottest.entity.Role;
import com.southwind.springboottest.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RoleMapper {

    @Select("select * from role")
    @Results(id="roleMap", value = {
            @Result(id = true,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "id",property = "users",
                    many=@Many(
                            select = "com.southwind.springboottest.dao.UserMapper.findByRoleId",
                            fetchType = FetchType.LAZY))
    })
    List<Role> findAll();

    @Select("select * from role where id=#{id}")
    @ResultMap(value = "roleMap")
    Role findById(Integer id);

    @Select("select * from role where id=#{id}")
    Role findByRoleId(Integer roleid);

    @Insert("insert into role (name) " +
            "values (#{name})")
    int save(Role role);

    @Delete("delete from role where id=#{id}")
    int deleteById(Integer id);

    @Update("update role set " +
            "name=#{name} " +
            "where id=#{id}")
    int Update(Role role);

    @Select("select * from role")
    List<Role> findPage();
}
