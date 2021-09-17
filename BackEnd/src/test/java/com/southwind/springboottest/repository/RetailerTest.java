package com.southwind.springboottest.repository;

import com.southwind.springboottest.entity.Commodity;
import com.southwind.springboottest.entity.Retailer;
import com.southwind.springboottest.service.CommodityService;
import com.southwind.springboottest.service.RetailerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RetailerTest {

    @Autowired
    RetailerService retailerService;

    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
    String formattedDate = dateFormat.format(date);

    @Test
    public void saveTest()
    {
        Retailer retailer1=new Retailer
                ("asd132", "CN123073","海南大学6栋","1");
        Retailer retailer2=new Retailer
                ("zxc123", "CN754233","海南大学405","1");
        retailerService.save(retailer1);
        retailerService.save(retailer2);
    }

    @Test
    public void findByIdTest()
    {
        System.out.println(retailerService.findById(1));
    }

    @Test
    public void findAllTest()
    {
        System.out.println(retailerService.findAll());
    }

    @Test
    public void findByTraCodeTest()
    {
        System.out.println(retailerService.findByTraCode(1617456146442L));
    }

    @Test
    public void DelTest()
    {
        retailerService.deleteById(2);
    }
}
