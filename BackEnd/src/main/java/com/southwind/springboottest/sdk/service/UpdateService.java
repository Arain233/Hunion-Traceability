package com.southwind.springboottest.sdk.service;



import com.southwind.springboottest.sdk.Update;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {

    public void update(String id,String digest) throws Exception {
        Update.Initial(id,digest);
        Update.main(null);
    }
}
