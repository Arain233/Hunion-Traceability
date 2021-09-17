package org.example.fabric.service;

import org.example.fabric.EnrollAdmin;
import org.example.fabric.InitLedger;
import org.example.fabric.RegisterUser;
import org.springframework.stereotype.Service;

@Service
public class InitService {
    public void Init() throws Exception {
        EnrollAdmin.main(null);
        RegisterUser.main(null);
        InitLedger.main(null);
    }
}
