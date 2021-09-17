package com.southwind.springboottest.SM2;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bouncycastle.math.ec.ECPoint;

@Data
@AllArgsConstructor
/**
 * 密钥及椭圆曲线点实体类
 */
public class KeyWallet {

    private String privateKey;

    private String publicKey;

    private String pubX;

    private String pubY;

    private ECPoint pubQ;
}
