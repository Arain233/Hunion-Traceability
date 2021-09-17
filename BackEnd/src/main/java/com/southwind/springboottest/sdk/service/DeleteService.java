package com.southwind.springboottest.sdk.service;

import com.southwind.springboottest.sdk.Delete;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    public void delete(String id) throws Exception {
        Delete.main(new String[]{id});
    }
}
