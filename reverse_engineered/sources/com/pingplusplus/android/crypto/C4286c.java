package com.pingplusplus.android.crypto;

import ch.qos.logback.core.CoreConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* renamed from: com.pingplusplus.android.crypto.c */
public class C4286c {
    /* renamed from: a */
    private static String f14959a = "RSA";
    /* renamed from: b */
    private static String f14960b = "RSA/ECB/PKCS1Padding";

    /* renamed from: a */
    public static PublicKey m16985a(InputStream inputStream) {
        try {
            return C4286c.m16986a(C4286c.m16988b(inputStream));
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e2) {
            throw new Exception("公钥输入流为空");
        }
    }

    /* renamed from: a */
    public static PublicKey m16986a(String str) {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(f14959a).generatePublic(new X509EncodedKeySpec(C4285b.m16983a(str)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e3) {
            throw new Exception("公钥数据为空");
        }
    }

    /* renamed from: a */
    public static byte[] m16987a(byte[] bArr, PublicKey publicKey) {
        try {
            Cipher instance = Cipher.getInstance(f14960b);
            instance.init(1, publicKey);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    private static String m16988b(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            String readLine = bufferedReader.readLine();
            if (readLine == null) {
                return stringBuilder.toString();
            }
            if (readLine.charAt(0) != CoreConstants.DASH_CHAR) {
                stringBuilder.append(readLine);
                stringBuilder.append('\r');
            }
        }
    }

    /* renamed from: b */
    public static byte[] m16989b(byte[] bArr, PublicKey publicKey) {
        try {
            Cipher instance = Cipher.getInstance(f14960b);
            instance.init(2, publicKey);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
