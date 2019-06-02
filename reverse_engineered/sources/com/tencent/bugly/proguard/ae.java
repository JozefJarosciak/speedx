package com.tencent.bugly.proguard;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/* compiled from: BUGLY */
public final class ae implements af {
    /* renamed from: a */
    private String f15534a = null;

    /* renamed from: a */
    public final byte[] mo6068a(byte[] bArr) throws Exception {
        if (this.f15534a == null || bArr == null) {
            return null;
        }
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(2, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.f15534a.getBytes("UTF-8"))), new IvParameterSpec(this.f15534a.getBytes("UTF-8")));
        return instance.doFinal(bArr);
    }

    /* renamed from: b */
    public final byte[] mo6069b(byte[] bArr) throws Exception, NoSuchAlgorithmException {
        if (this.f15534a == null || bArr == null) {
            return null;
        }
        Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
        instance.init(1, SecretKeyFactory.getInstance("DES").generateSecret(new DESKeySpec(this.f15534a.getBytes("UTF-8"))), new IvParameterSpec(this.f15534a.getBytes("UTF-8")));
        return instance.doFinal(bArr);
    }

    /* renamed from: a */
    public final void mo6067a(String str) {
        if (str != null) {
            this.f15534a = str;
        }
    }
}
