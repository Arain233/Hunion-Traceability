package com.southwind.springboottest.repository;

import com.southwind.springboottest.entity.Dealer;
import com.southwind.springboottest.entity.Material;
import com.southwind.springboottest.service.DealerService;
import com.southwind.springboottest.service.MaterialService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.DateFormat;
import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DealerTest {

    @Autowired
    DealerService dealerService;

    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
    String formattedDate = dateFormat.format(date);

    @Test
    public void saveTest()
    {
        Dealer dealer1=new Dealer
                ("asdasd", "CN123073","海南大学6栋","1");
        Dealer dealer2=new Dealer
                ("zxczxc", "CN754233","海南大学405","1");
        dealerService.save(dealer1);
        dealerService.save(dealer2);
    }

    @Test
    public void findByIdTest()
    {
        System.out.println(dealerService.findById(1));
    }

    @Test
    public void findAllTest()
    {
        System.out.println(dealerService.findAll());
    }

    @Test
    public void DelTest()
    {
        System.out.println(String.valueOf(dealerService.findById(1).getId()));;
    }
}
