package com.southwind.springboottest.SM2;


import org.bouncycastle.asn1.gm.GMNamedCurves;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.CipherParameters;
import org.bouncycastle.crypto.CryptoException;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.engines.SM2Engine;
import org.bouncycastle.crypto.generators.ECKeyPairGenerator;
import org.bouncycastle.crypto.params.*;
import org.bouncycastle.crypto.signers.SM2Signer;
import org.bouncycastle.math.ec.ECPoint;
import org.bouncycastle.pqc.math.linearalgebra.ByteUtils;
import org.bouncycastle.util.encoders.Hex;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 椭圆曲线公钥密码SM2的实现
 */
public class SM2Util {

    public static KeyWallet generateKeyPair()
    {

        /**
         * 密钥对及椭圆曲线点的生成
         */
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN());
        ECKeyPairGenerator keyPairGenerator = new ECKeyPairGenerator();
        try {
            keyPairGenerator.init(new ECKeyGenerationParameters(domainParameters, SecureRandom.getInstance("SHA1PRNG")));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        AsymmetricCipherKeyPair asymmetricCipherKeyPair = keyPairGenerator.generateKeyPair();

        BigInteger privatekey = ((ECPrivateKeyParameters) asymmetricCipherKeyPair.getPrivate()).getD();
        String privateKeyHex = privatekey.toString(16);
        ECPoint ecPoint = ((ECPublicKeyParameters) asymmetricCipherKeyPair.getPublic()).getQ();
        String publicKeyHex = Hex.toHexString(ecPoint.getEncoded(false));
        String pubX=ByteUtils.toHexString(ecPoint.getAffineXCoord().getEncoded()).toUpperCase();
        String pubY=ByteUtils.toHexString(ecPoint.getAffineYCoord().getEncoded()).toUpperCase();
        KeyWallet keyWallet=new KeyWallet(privateKeyHex,publicKeyHex,pubX,pubY,ecPoint);
        return keyWallet;
    }

    /**
     * 按C1C3C2模式加密
     * @param message
     * @param keyWallet
     * @return
     */
    public static String Encrypt(String message, KeyWallet keyWallet)
    {
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN(),sm2ECParameters.getH());
        //ECPublicKeyParameters publicKey = BCECUtil.createECPublicKeyParameters(keyWallet.getPubX(),keyWallet.getPubY(), sm2ECParameters.getCurve(), domainParameters);
        ECPublicKeyParameters publicKey=new ECPublicKeyParameters(keyWallet.getPubQ(), domainParameters);

        ParametersWithRandom pwr = new ParametersWithRandom(publicKey, new SecureRandom());
        Base64.Encoder encoder = Base64.getUrlEncoder();
        byte[]data=message.getBytes();
        data= encoder.encode(data);
        byte[]cipherDataByte=new byte[0];

        SM2Engine sm2Engine = new SM2Engine(SM2Engine.Mode.C1C3C2);
        sm2Engine.init(true, pwr);
        try {
            cipherDataByte = sm2Engine.processBlock(data, 0, data.length);
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }
        String cipher=ByteUtils.toHexString(cipherDataByte);

        return cipher;
    }

    /**
     * 按C1C3C2模式解密
     * @param cipherHex
     * @param priKey
     * @return
     */
    public static String Decrypt(String cipherHex,String priKey)
    {
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN());
        byte[]cipherByte=ByteUtils.fromHexString(cipherHex);
        BigInteger privateKeyD = new BigInteger(priKey, 16);
        ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(privateKeyD, domainParameters);
        SM2Engine sm2Engine = new SM2Engine(SM2Engine.Mode.C1C3C2);
        sm2Engine.init(false, privateKeyParameters);

        byte[] arrayOfBytes = new byte[0];
        try {
            arrayOfBytes =sm2Engine.processBlock(cipherByte, 0, cipherByte.length);
        } catch (InvalidCipherTextException e) {
            e.printStackTrace();
        }

        Base64.Decoder decoder = Base64.getUrlDecoder();
        arrayOfBytes=decoder.decode(arrayOfBytes);
        String message = new String(arrayOfBytes);

        return message;
    }

    /**
     * 数字签名
     * @param priKey
     * @param identity
     * @param message
     * @return
     * @throws CryptoException
     */
    public static String Sign(String priKey, String identity, String message) throws CryptoException
    {
        X9ECParameters sm2ECParameters = GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters domainParameters = new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN());
        BigInteger privateKeyD = new BigInteger(priKey, 16);
        ECPrivateKeyParameters privateKeyParameters = new ECPrivateKeyParameters(privateKeyD, domainParameters);
        SM2Signer signer = new SM2Signer();
        CipherParameters param = null;
        ParametersWithRandom pwr = new ParametersWithRandom(privateKeyParameters, new SecureRandom());

        if (identity != null)
        {
            param = new ParametersWithID(pwr, identity.getBytes(StandardCharsets.UTF_8));
        } else {
            param = pwr;
        }
        byte[] data=message.getBytes();

        signer.init(true, param);
        signer.update(data, 0, data.length);
        String signHex=ByteUtils.toHexString(signer.generateSignature());
        return signHex;
    }

    /**
     * 验证签名
     * @param keyWallet
     * @param identity
     * @param message`
     * @param signHex
     * @return
     */
    public static boolean verify(KeyWallet keyWallet , String identity, String message, String signHex) {
        X9ECParameters sm2ECParameters =GMNamedCurves.getByName("sm2p256v1");
        ECDomainParameters domainParameters =new ECDomainParameters(sm2ECParameters.getCurve(), sm2ECParameters.getG(), sm2ECParameters.getN(),sm2ECParameters.getH());
        ECPublicKeyParameters publicKey=new ECPublicKeyParameters(keyWallet.getPubQ(), domainParameters);
        SM2Signer signer =new SM2Signer();
        CipherParameters param =new ParametersWithID(publicKey, identity.getBytes(StandardCharsets.UTF_8));
        signer.init(false, param);
        byte[] signByte=ByteUtils.fromHexString(signHex);
        byte[] data=message.getBytes();
        signer.update(data, 0, data.length);
        return signer.verifySignature(signByte);
    }


}
