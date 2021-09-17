package com.southwind.springboottest.repository;

import com.southwind.springboottest.entity.Material;
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
public class MaterialTest {

    @Autowired
    MaterialService materialService;

    Date date = new Date();
    DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);
    String formattedDate = dateFormat.format(date);

    @Test
    public void saveTest()
    {
        Material material1=new Material
                ("OLED屏幕",formattedDate,"韩国",
                        "S134073A-K","韩国首尔瑞草区三星电子","1");
        materialService.save(material1);

    }

    @Test
    public void findByIdTest()
    {
        Material material= materialService.findById(6);
        System.out.println(material.getCommodity_code());

    }

    @Test
    public void findAllTest()
    {
        System.out.println(materialService.findAll());
    }

    @Test
    public void DelTest()
    {
        materialService.deleteById(2);
    }
}
