package io.rong.imageloader.cache.disc.naming;

import io.rong.imageloader.utils.C1523L;
import java.math.BigInteger;
import java.security.MessageDigest;

public class Md5FileNameGenerator implements FileNameGenerator {
    private static final String HASH_ALGORITHM = "MD5";
    private static final int RADIX = 36;

    public String generate(String str) {
        return new BigInteger(getMD5(str.getBytes())).abs().toString(36);
    }

    private byte[] getMD5(byte[] bArr) {
        byte[] bArr2 = null;
        try {
            MessageDigest instance = MessageDigest.getInstance(HASH_ALGORITHM);
            instance.update(bArr);
            bArr2 = instance.digest();
        } catch (Throwable e) {
            C1523L.e(e);
        }
        return bArr2;
    }
}
