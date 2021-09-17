package com.southwind.springboottest.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginBean {

    private String name;

    private String password;

 //   private String kapt;

}
