package com.umeng.analytics;

import android.content.Context;
import com.google.common.base.Ascii;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.Random;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p203u.aly.ah;
import p203u.aly.bq;

/* compiled from: DataHelper */
/* renamed from: com.umeng.analytics.d */
public class C4744d {
    /* renamed from: a */
    private static final byte[] f16641a = new byte[]{(byte) 10, (byte) 1, Ascii.VT, (byte) 5, (byte) 4, Ascii.SI, (byte) 7, (byte) 9, Ascii.ETB, (byte) 3, (byte) 1, (byte) 6, (byte) 8, Ascii.FF, (byte) 13, (byte) 91};

    /* renamed from: a */
    public static byte[] m18642a(String str) {
        byte[] bArr = null;
        if (str != null) {
            int length = str.length();
            if (length % 2 == 0) {
                bArr = new byte[(length / 2)];
                for (int i = 0; i < length; i += 2) {
                    bArr[i / 2] = (byte) Integer.valueOf(str.substring(i, i + 2), 16).intValue();
                }
            }
        }
        return bArr;
    }

    /* renamed from: a */
    public static boolean m18641a(Context context, byte[] bArr) {
        long length = (long) bArr.length;
        if (length <= C4743c.f16640e) {
            return false;
        }
        C4762j.m18682a(context).m18702f();
        bq.m21732a(context).m21752a(length, System.currentTimeMillis(), "__data_size_of");
        return true;
    }

    /* renamed from: a */
    public static String m18640a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < bArr.length; i++) {
            stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(bArr[i])}));
        }
        return stringBuffer.toString().toLowerCase(Locale.US);
    }

    /* renamed from: b */
    public static byte[] m18644b(byte[] bArr) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.reset();
            instance.update(bArr);
            return instance.digest();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static byte[] m18643a(byte[] bArr, byte[] bArr2) throws Exception {
        Cipher instance = Cipher.getInstance("AES/CBC/PKCS7Padding");
        instance.init(1, new SecretKeySpec(bArr2, "AES"), new IvParameterSpec(f16641a));
        return instance.doFinal(bArr);
    }

    /* renamed from: a */
    public static int m18638a(int i, String str) {
        int i2 = 0;
        if (((double) new Random().nextFloat()) < 0.001d) {
            if (str == null) {
                ah.m21161b("--->", "null signature..");
            }
            try {
                i2 = Integer.parseInt(str.substring(9, 11), 16);
            } catch (Exception e) {
            }
            return (i2 | 128) * 1000;
        }
        i2 = new Random().nextInt(i);
        if (i2 > 255000 || i2 < 128000) {
            return i2;
        }
        return 127000;
    }

    /* renamed from: a */
    public static String m18639a(Throwable th) {
        String str = null;
        if (th != null) {
            try {
                Writer stringWriter = new StringWriter();
                PrintWriter printWriter = new PrintWriter(stringWriter);
                th.printStackTrace(printWriter);
                for (Throwable cause = th.getCause(); cause != null; cause = cause.getCause()) {
                    cause.printStackTrace(printWriter);
                }
                str = stringWriter.toString();
                printWriter.close();
                stringWriter.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return str;
    }
}
