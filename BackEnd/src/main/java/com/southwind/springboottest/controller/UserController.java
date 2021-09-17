package com.southwind.springboottest.controller;

import com.southwind.springboottest.entity.User;
import com.southwind.springboottest.http.HttpResult;
import com.southwind.springboottest.service.UserService;
import com.southwind.springboottest.utils.JwtTokenUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Api(value="/user",tags = "用户相关操作")
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "根据令牌查找用户数据")
    @GetMapping("/findByTkn")
    public HttpResult findByToken(HttpServletRequest request){
        String token = request.getHeader("token");
        if(token!=null)
        {
            return HttpResult.ok(userService.findByName(JwtTokenUtils.getUsernameFromToken(token)));
        }
        return HttpResult.error("令牌为空");
    }

    @ApiOperation(value = "查找全部用户数据")
    @GetMapping("/findAll")
    public HttpResult findAll(){
        return HttpResult.ok(userService.findAll());
    }

    @ApiOperation(value = "根据ID查找用户")
    @GetMapping("/findById/{id}")
    public HttpResult findById(@PathVariable("id") Integer id){
        return HttpResult.ok(userService.findById(id));
    }

    @ApiOperation(
            value = "用户数据修改",
            notes = "传入需要修改的用户ID及需要修改的数据，不修改的数据置空值"
    )
    @PutMapping("/update")
    public HttpResult update(@RequestBody User user){
        return HttpResult.ok(userService.Update(user));
    }

    @ApiOperation(value = "根据用户ID删除数据")
    @DeleteMapping("/deleteById/{id}")
    public HttpResult deleteById(@PathVariable("id") Integer id){
        return HttpResult.ok(userService.deleteById(id));
    }



}
