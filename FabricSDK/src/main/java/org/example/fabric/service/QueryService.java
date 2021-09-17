package org.example.fabric.service;

import org.example.fabric.Invoke;
import org.example.fabric.Query;
import org.springframework.stereotype.Service;

@Service
public class QueryService {
    public String query(String id) throws Exception {
        Query.main(new String[]{id});
        return new String(Query.result);
    }
}
