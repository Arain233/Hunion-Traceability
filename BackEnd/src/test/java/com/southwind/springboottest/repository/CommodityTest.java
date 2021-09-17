package com.southwind.springboottest.repository;

import com.southwind.springboottest.dao.CommodityMapper;
import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.service.CommodityService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommodityTest {

    @Autowired
    CommodityService commodityService;

    @Autowired
    CommodityMapper commodityMapper;

    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
    String formattedDate = dateFormat.format(date);

    @Test
    public void saveTest()
    {
        Commodity commodity1=new Commodity
                ("手机",formattedDate,"中国",
                        "CN123073","海南大学6栋");
        Commodity commodity2=new Commodity
                ("电脑",formattedDate,"中国",
                        "CN754233","海南大学405");
        commodityService.save(commodity1);
        commodityService.save(commodity2);
    }

    @Test
    public void findByIdTest()
    {
        System.out.println(commodityService.findById(5));
    }

    @Test
    public void findAllTest()
    {
        System.out.println(commodityService.findAll());
    }

    @Test
    public void findByTraCodeTest()
    {
        System.out.println(commodityService.findByTraCode(1617456146442L));
    }

    @Test
    public void DelTest()
    {
        commodityService.deleteById(2);
    }

    @Test
    public void UpdateTest()
    {
        Commodity commodity=commodityService.findById(3);
        commodity.setOriginPlace("1非洲");
        commodity.setAddress("快乐老家");
        commodityMapper.Update(commodity);
    }
}
