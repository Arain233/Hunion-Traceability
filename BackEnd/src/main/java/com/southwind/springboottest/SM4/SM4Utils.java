package com.southwind.springboottest.SM4;

import com.southwind.springboottest.utils.GMBaseUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

public class SM4Utils extends GMBaseUtil {

    public static final String ALGORITHM_NAME = "SM4";

    public static final String ALGORITHM_NAME_CBC= "SM4/CBC/PKCS7Padding";

    public static final int KEY_SIZE = 128;

    public static String generateKey() throws NoSuchAlgorithmException,NoSuchProviderException
    {
        KeyGenerator kg = null;
        kg = KeyGenerator.getInstance(ALGORITHM_NAME, BouncyCastleProvider.PROVIDER_NAME);
        kg.init(KEY_SIZE, new SecureRandom());
        String keyHex=ByteUtils.toHexString(kg.generateKey().getEncoded());
        return keyHex;
    }

    public static String Encrypt_CBC_Padding(String message,String keyHex, String ivHex)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchProviderException,
            NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException,
            InvalidAlgorithmParameterException
    {
        byte[] key=ByteUtils.fromHexString(keyHex);
        byte[] iv=ByteUtils.fromHexString(ivHex);
        byte[] data=message.getBytes();
        Cipher cipher = generateCBCCipher(ALGORITHM_NAME_CBC, Cipher.ENCRYPT_MODE, key, iv);
        String cipherHex=ByteUtils.toHexString(cipher.doFinal(data));
        return cipherHex;
    }

    public static String Decrypt_CBC_Padding(String cipherTextHex, String keyHex, String ivHex)
            throws IllegalBlockSizeException, BadPaddingException, InvalidKeyException,
            NoSuchAlgorithmException, NoSuchProviderException, NoSuchPaddingException,
            InvalidAlgorithmParameterException
    {
        byte[] key=ByteUtils.fromHexString(keyHex);
        byte[] iv=ByteUtils.fromHexString(ivHex);
        byte[]cipherText= ByteUtils.fromHexString(cipherTextHex);
        Cipher cipher = generateCBCCipher(ALGORITHM_NAME_CBC, Cipher.DECRYPT_MODE, key, iv);
        String message=new String(cipher.doFinal(cipherText));
        return message;
    }

    private static Cipher generateCBCCipher(String algorithmName, int mode, byte[] key, byte[] iv)
            throws InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException,
            NoSuchProviderException, NoSuchPaddingException
    {
        Cipher cipher = Cipher.getInstance(algorithmName, BouncyCastleProvider.PROVIDER_NAME);
        Key sm4Key = new SecretKeySpec(key, ALGORITHM_NAME);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        cipher.init(mode, sm4Key, ivParameterSpec);
        return cipher;
    }
}
