package com.southwind.springboottest.sdk.service;

import com.southwind.springboottest.sdk.EnrollAdmin;
import com.southwind.springboottest.sdk.InitLedger;
import com.southwind.springboottest.sdk.RegisterUser;

import org.springframework.stereotype.Service;

@Service
public class InitService {
    public void Init() throws Exception {
        EnrollAdmin.main(null);
        RegisterUser.main(null);
        InitLedger.main(null);
    }
}
