package com.southwind.springboottest.repository;


import com.southwind.springboottest.utils.CmdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CmdTest {

    @Test
    public void encodeTest()
    {
        String cmd= CmdUtil.cmdGenerate("1.jpg","new1.jpg","e");
        //String cmd= CmdUtil.cmdGenerate("new1.jpg","new2.jpg","d");
        System.out.println(cmd);
        CmdUtil.exeCmd(cmd);
    }

    @Test
    public void fileTest()
    {

    }
}
