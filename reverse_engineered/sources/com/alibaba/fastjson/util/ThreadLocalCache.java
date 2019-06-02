package com.alibaba.fastjson.util;

import java.lang.ref.SoftReference;
import java.nio.charset.CharsetDecoder;

public class ThreadLocalCache {
    public static final int BYTES_CACH_INIT_SIZE = 1024;
    public static final int BYTeS_CACH_MAX_SIZE = 131072;
    public static final int CHARS_CACH_INIT_SIZE = 1024;
    public static final int CHARS_CACH_MAX_SIZE = 131072;
    private static final ThreadLocal<SoftReference<byte[]>> bytesBufLocal = new ThreadLocal();
    private static final ThreadLocal<SoftReference<char[]>> charsBufLocal = new ThreadLocal();
    private static final ThreadLocal<CharsetDecoder> decoderLocal = new ThreadLocal();

    public static CharsetDecoder getUTF8Decoder() {
        CharsetDecoder charsetDecoder = (CharsetDecoder) decoderLocal.get();
        if (charsetDecoder != null) {
            return charsetDecoder;
        }
        charsetDecoder = new UTF8Decoder();
        decoderLocal.set(charsetDecoder);
        return charsetDecoder;
    }

    public static void clearChars() {
        charsBufLocal.set(null);
    }

    public static char[] getChars(int i) {
        SoftReference softReference = (SoftReference) charsBufLocal.get();
        if (softReference == null) {
            return allocate(i);
        }
        char[] cArr = (char[]) softReference.get();
        if (cArr == null) {
            return allocate(i);
        }
        if (cArr.length < i) {
            return allocate(i);
        }
        return cArr;
    }

    private static char[] allocate(int i) {
        int allocateLength = getAllocateLength(1024, 131072, i);
        if (allocateLength > 131072) {
            return new char[i];
        }
        Object obj = new char[allocateLength];
        charsBufLocal.set(new SoftReference(obj));
        return obj;
    }

    private static int getAllocateLength(int i, int i2, int i3) {
        int i4 = i;
        while (i4 < i3) {
            i4 *= 2;
            if (i4 > i2) {
                return i3;
            }
        }
        return i4;
    }

    public static void clearBytes() {
        bytesBufLocal.set(null);
    }

    public static byte[] getBytes(int i) {
        SoftReference softReference = (SoftReference) bytesBufLocal.get();
        if (softReference == null) {
            return allocateBytes(i);
        }
        byte[] bArr = (byte[]) softReference.get();
        if (bArr == null) {
            return allocateBytes(i);
        }
        if (bArr.length < i) {
            return allocateBytes(i);
        }
        return bArr;
    }

    private static byte[] allocateBytes(int i) {
        int allocateLength = getAllocateLength(1024, 131072, i);
        if (allocateLength > 131072) {
            return new byte[i];
        }
        Object obj = new byte[allocateLength];
        bytesBufLocal.set(new SoftReference(obj));
        return obj;
    }
}
