package com.beastbikes.android.utils;

import android.util.Base64;
import ch.qos.logback.core.CoreConstants;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;

/* compiled from: RSAUtils */
/* renamed from: com.beastbikes.android.utils.r */
public final class C2573r {
    /* renamed from: a */
    private static String f12049a = "RSA";

    /* renamed from: a */
    public static byte[] m12890a(byte[] bArr, PublicKey publicKey) {
        try {
            Cipher instance = Cipher.getInstance(f12049a);
            instance.init(1, publicKey);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m12889a(byte[] bArr, PrivateKey privateKey) {
        try {
            Cipher instance = Cipher.getInstance(f12049a);
            instance.init(2, privateKey);
            return instance.doFinal(bArr);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static PublicKey m12888a(String str) throws Exception {
        try {
            return (RSAPublicKey) KeyFactory.getInstance(f12049a).generatePublic(new X509EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("公钥非法");
        } catch (NullPointerException e3) {
            throw new Exception("公钥数据为空");
        }
    }

    /* renamed from: b */
    public static PrivateKey m12892b(String str) throws Exception {
        try {
            return (RSAPrivateKey) KeyFactory.getInstance(f12049a).generatePrivate(new PKCS8EncodedKeySpec(Base64.decode(str, 0)));
        } catch (NoSuchAlgorithmException e) {
            throw new Exception("无此算法");
        } catch (InvalidKeySpecException e2) {
            throw new Exception("私钥非法");
        } catch (NullPointerException e3) {
            throw new Exception("私钥数据为空");
        }
    }

    /* renamed from: a */
    public static PublicKey m12887a(InputStream inputStream) throws Exception {
        try {
            return C2573r.m12888a(C2573r.m12891b(inputStream));
        } catch (IOException e) {
            throw new Exception("公钥数据流读取错误");
        } catch (NullPointerException e2) {
            throw new Exception("公钥输入流为空");
        }
    }

    /* renamed from: b */
    private static String m12891b(InputStream inputStream) throws IOException {
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
}
