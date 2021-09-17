package com.southwind.springboottest.repository;


import com.southwind.springboottest.SM2.KeyWallet;
import com.southwind.springboottest.SM2.SM2Util;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class SM2Test {

    public KeyWallet keyWallet;

    @Test
    public void keyGenerate()
    {
        keyWallet=SM2Util.generateKeyPair();
    }

    @Test
    public void EDTest()
    {
        keyWallet=SM2Util.generateKeyPair();
        String cipher=SM2Util.Encrypt("zhazhquweihnxjkcnlziushdluotfiahwe;ourouwerawhergsdn12352ahui",keyWallet);
        System.out.println("cipher："+cipher);
        System.out.println();
        cipher="4ba4a6d8c2d2ce419c5f1baaff9f1bc31ee2dde930dd37b1a359d526c857023458";
        keyWallet.setPrivateKey("54232d8aaa3209ee123e07c34314e50e29fbb941496f92e219eb62c5bd40d968");
        String message=SM2Util.Decrypt(cipher,keyWallet.getPrivateKey());
        System.out.println(message);
        System.out.println(new String(message));
    }

    @Test
    public void signAndverifyTest()
    {
        keyWallet=SM2Util.generateKeyPair();
        System.out.println(keyWallet.getPublicKey());
        String sign=null;
        String identity="asdasdasd";
        String message="qwezxcawe";
        try {
            sign=SM2Util.Sign(keyWallet.getPrivateKey(),identity,message);
        } catch (CryptoException e) {
            e.printStackTrace();
        }
        System.out.println(sign);
        boolean flag=SM2Util.verify(keyWallet,identity,message,sign);
        if(flag)
        {
            System.out.println("验证签名成功");
        }
        else
        {
            System.out.println("验证签名失败");
        }
    }

}
