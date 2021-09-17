package com.southwind.springboottest;


import com.southwind.springboottest.sdk.startFabric;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringboottestApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringboottestApplication.class, args);
        startFabric.startFabric();
    }

}
