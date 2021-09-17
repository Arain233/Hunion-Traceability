package org.example.fabric.service;


import org.example.fabric.Update;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {

    public void update(String id,String digest) throws Exception {
        Update.Initial(id,digest);
        Update.main(null);
    }
}
