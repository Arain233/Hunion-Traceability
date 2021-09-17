package com.southwind.springboottest.sdk.service;


import com.southwind.springboottest.sdk.Query;
import org.springframework.stereotype.Service;

@Service
public class QueryService {
    public String query(String id) throws Exception {
        Query.main(new String[]{id});
        return Query.info;
    }
}
