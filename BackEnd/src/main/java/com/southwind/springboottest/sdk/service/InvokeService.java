package com.southwind.springboottest.sdk.service;


import com.southwind.springboottest.sdk.Invoke;
import org.springframework.stereotype.Service;

@Service
public class InvokeService {
    public void invoke(String id,String digest) throws Exception {
        Invoke.Initial(id,digest);
        Invoke.main(null);
    }
}
