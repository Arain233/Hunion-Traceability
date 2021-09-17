package org.example.fabric.service;

import org.example.fabric.Invoke;
import org.springframework.stereotype.Service;

@Service
public class InvokeService {
    public void invoke(String id,String digest) throws Exception {
        Invoke.Initial(id,digest);
        Invoke.main(null);
    }
}
