package com.southwind.springboottest.dao;


import com.southwind.springboottest.entity.Certificate;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CertMapper {

    @Select("select certificate_binary from certificate where certificate_no=#{certNo}")
    String findByNo(String certNo);

    @Insert("insert into certificate (certificate_no,certificate_binary,hash) " +
            "values (#{certificate_no},#{certificate_binary},#{hash})")
    int save(Certificate certificate);

    @Select("select * from certificate where certificate_no=#{certNo}")
    Certificate findCerByNo(String certNo);
}
