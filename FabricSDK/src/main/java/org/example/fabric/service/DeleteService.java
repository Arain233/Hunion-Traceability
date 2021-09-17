package org.example.fabric.service;

import org.example.fabric.Delete;
import org.example.fabric.Query;
import org.springframework.stereotype.Service;

@Service
public class DeleteService {
    public void delete(String id) throws Exception {
        Delete.main(new String[]{id});
    }
}
