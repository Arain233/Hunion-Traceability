package com.southwind.springboottest.repository;

import com.southwind.springboottest.SM4.SM4Utils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SM4Test {

    @Test
    public void keyGenerateTest()
    {
        String key=null;
        try {
            key = SM4Utils.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
        System.out.println(key);
    }

    @Test
    public void EDtest() throws Exception
    {
        String key="846344ce2c1cfaa1894aea0472defb3c";
        String iv=null;
        //key = SM4Utils.generateKey();
        //iv=SM4Utils.generateKey();
        iv="2aa94734bb2f22976582755656f1cae0";

        System.out.println("iv="+iv);
        String meassage="woxichaojidapanghu";
        String cipher="e0660d3dd50d355c6e06dd18f99337a90c05fd74acbe23b7f1ca426bc8a33171";
        String text=null;
        try {
            //cipher=SM4Utils.Encrypt_CBC_Padding(meassage,key,iv);
            //key = SM4Utils.generateKey();
            text=SM4Utils.Decrypt_CBC_Padding(cipher,key,iv);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }

        System.out.println("key=\n"+key);
        System.out.println("cipher=\n"+cipher);
        System.out.println("plaintext=\n"+text);
    }

}
