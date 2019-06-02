package com.tencent.bugly.proguard;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* compiled from: BUGLY */
public final class ad implements af {
    /* renamed from: a */
    private String f15533a = null;

    /* renamed from: a */
    public final byte[] mo6068a(byte[] bArr) throws Exception {
        int i = 0;
        if (this.f15533a == null || bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(b + " ");
        }
        Key secretKeySpec = new SecretKeySpec(this.f15533a.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(2, secretKeySpec, new IvParameterSpec(this.f15533a.getBytes()));
        byte[] doFinal = instance.doFinal(bArr);
        stringBuffer = new StringBuffer();
        int length = doFinal.length;
        while (i < length) {
            stringBuffer.append(doFinal[i] + " ");
            i++;
        }
        return doFinal;
    }

    /* renamed from: b */
    public final byte[] mo6069b(byte[] bArr) throws Exception, NoSuchAlgorithmException {
        int i = 0;
        if (this.f15533a == null || bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (byte b : bArr) {
            stringBuffer.append(b + " ");
        }
        Key secretKeySpec = new SecretKeySpec(this.f15533a.getBytes(), "AES");
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS5Padding");
        instance.init(1, secretKeySpec, new IvParameterSpec(this.f15533a.getBytes()));
        byte[] doFinal = instance.doFinal(bArr);
        stringBuffer = new StringBuffer();
        int length = doFinal.length;
        while (i < length) {
            stringBuffer.append(doFinal[i] + " ");
            i++;
        }
        return doFinal;
    }

    /* renamed from: a */
    public final void mo6067a(String str) {
        if (str != null) {
            for (int length = str.length(); length < 16; length++) {
                str = str + "0";
            }
            this.f15533a = str.substring(0, 16);
        }
    }
}
