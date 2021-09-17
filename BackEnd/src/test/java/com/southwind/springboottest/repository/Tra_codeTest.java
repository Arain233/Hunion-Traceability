package com.southwind.springboottest.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Tra_codeTest {

    @Test
    public void test()
    {
        long code=System.currentTimeMillis();
        System.out.println(code);
    }
}
