package com.alipay.p029b.p030a.p031a.p032a;

import android.os.Environment;
import android.util.Base64;
import com.alipay.p029b.p030a.p031a.p032a.p033a.C0786a;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

/* renamed from: com.alipay.b.a.a.a.a */
public final class C0789a {
    /* renamed from: a */
    public static File m3017a() {
        try {
            return (File) Environment.class.getMethod(new String(C0786a.m3010a("Z2V0RXh0ZXJuYWxTdG9yYWdlRGlyZWN0b3J5")), new Class[0]).invoke(null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m3018a(Throwable th) {
        Writer stringWriter = new StringWriter();
        th.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /* renamed from: a */
    public static String m3019a(Map<String, String> map, String str, String str2) {
        if (map == null) {
            return str2;
        }
        String str3 = (String) map.get(str);
        return str3 != null ? str3 : str2;
    }

    /* renamed from: a */
    public static boolean m3020a(String str) {
        if (str != null) {
            int length = str.length();
            if (length != 0) {
                for (int i = 0; i < length; i++) {
                    if (!Character.isWhitespace(str.charAt(i))) {
                        return false;
                    }
                }
                return true;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m3021a(String str, String str2) {
        return str == null ? str2 == null : str.equals(str2);
    }

    /* renamed from: b */
    public static String m3022b(String str, String str2) {
        try {
            return (String) Class.forName("android.os.SystemProperties").getMethod("get", new Class[]{String.class, String.class}).invoke(null, new Object[]{str, str2});
        } catch (Exception e) {
            return str2;
        }
    }

    /* renamed from: b */
    public static boolean m3023b(String str) {
        return !C0789a.m3020a(str);
    }

    /* renamed from: c */
    public static String m3024c(String str) {
        return str == null ? "" : str;
    }

    /* renamed from: d */
    public static String m3025d(String str) {
        String str2 = null;
        try {
            if (!C0789a.m3020a(str)) {
                MessageDigest instance = MessageDigest.getInstance("SHA-1");
                instance.update(str.getBytes("UTF-8"));
                byte[] digest = instance.digest();
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < digest.length; i++) {
                    stringBuilder.append(String.format("%02x", new Object[]{Byte.valueOf(digest[i])}));
                }
                str2 = stringBuilder.toString();
            }
        } catch (Exception e) {
        }
        return str2;
    }

    /* renamed from: e */
    public static String m3026e(String str) {
        try {
            Object array = ByteBuffer.allocate(4).order(ByteOrder.LITTLE_ENDIAN).putInt(str.length()).array();
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes("UTF-8"));
            gZIPOutputStream.close();
            byteArrayOutputStream.close();
            Object obj = new byte[(byteArrayOutputStream.toByteArray().length + 4)];
            System.arraycopy(array, 0, obj, 0, 4);
            System.arraycopy(byteArrayOutputStream.toByteArray(), 0, obj, 4, byteArrayOutputStream.toByteArray().length);
            return Base64.encodeToString(obj, 8);
        } catch (Exception e) {
            return "";
        }
    }

    /* renamed from: f */
    public static String m3027f(String str) {
        String str2 = "";
        if (C0789a.m3020a(str)) {
            return "";
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(str.getBytes("utf-8"));
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = byteArrayInputStream.read(bArr, 0, 1024);
                if (read != -1) {
                    gZIPOutputStream.write(bArr, 0, read);
                } else {
                    gZIPOutputStream.flush();
                    gZIPOutputStream.close();
                    byte[] toByteArray = byteArrayOutputStream.toByteArray();
                    byteArrayOutputStream.flush();
                    byteArrayOutputStream.close();
                    byteArrayInputStream.close();
                    return new String(Base64.encode(toByteArray, 2));
                }
            }
        } catch (Exception e) {
            return str2;
        }
    }
}
