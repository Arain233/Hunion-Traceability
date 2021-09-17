package com.southwind.springboottest.utils;

import com.southwind.springboottest.SM3.SM3Encoder;
import com.southwind.springboottest.entity.Retailer;
import com.southwind.springboottest.service.RetailerService;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class DigestUtils extends IoHandlerAdapter {

    @Autowired
    protected RetailerService retailerService;

    public static DigestUtils digestUtils;

    @PostConstruct
    public void init() {
        digestUtils = this;
        digestUtils.retailerService = this.retailerService;
    }

    public static String DigestProducer(Retailer retailer)
    {
        String data= digestUtils.retailerService.findById(retailer.getId()).toString();
        try {
            return SM3Encoder.byteArrayToHexString(SM3Encoder.hash(data.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
