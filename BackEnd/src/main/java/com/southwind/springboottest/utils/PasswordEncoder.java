package com.southwind.springboottest.utils;


import com.southwind.springboottest.SM3.SM3Encoder;

import java.io.IOException;

public class PasswordEncoder {
    public static String Encrypt(String password,String salt)
    {

        try {
            return SM3Encoder.byteArrayToHexString(SM3Encoder.hash((password+salt).getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "error";
    }
}
