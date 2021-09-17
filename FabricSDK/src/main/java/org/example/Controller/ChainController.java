package org.example.Controller;

import org.example.fabric.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
public class ChainController {

    @Autowired
    InvokeService invokeService;

    @Autowired
    QueryService queryService;

    @Autowired
    InitService initService;

    @Autowired
    UpdateService updateService;

    @Autowired
    DeleteService deleteService;


    @PostMapping(value = "/invoke")
    public String invoke(@RequestParam("id") String id, @RequestParam("digest")String digest) throws Exception {
        invokeService.invoke(id,digest);
        return "invoke success";
    }

    @GetMapping(value = "/query/{id}")
    public String query(@PathVariable String id) throws Exception {
        return queryService.query(id);
    }

    @PostMapping(value = "/init")
    public String init() throws Exception {
        initService.Init();
        return "init successfully";
    }

    @PostMapping(value = "/update")
    public String update(@RequestParam("id") String id, @RequestParam("digest")String digest) throws Exception {
        updateService.update(id,digest);
        return "update success";
    }
    @GetMapping(value = "/delete/{id}")
    public String delete(@PathVariable String id) throws Exception {
        deleteService.delete(id);
        return "delete success";
    }
}
