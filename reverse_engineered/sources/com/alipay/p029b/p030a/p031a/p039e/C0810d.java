package com.alipay.p029b.p030a.p031a.p039e;

import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.alipay.b.a.a.e.d */
public final class C0810d {
    /* renamed from: a */
    private static String f1896a = "";
    /* renamed from: b */
    private static String f1897b = "";
    /* renamed from: c */
    private static String f1898c = "";

    /* renamed from: a */
    public static synchronized void m3135a(String str) {
        synchronized (C0810d.class) {
            List arrayList = new ArrayList();
            arrayList.add(str);
            C0810d.m3138a(arrayList);
        }
    }

    /* renamed from: a */
    public static synchronized void m3136a(String str, String str2, String str3) {
        synchronized (C0810d.class) {
            f1896a = str;
            f1897b = str2;
            f1898c = str3;
        }
    }

    /* renamed from: a */
    public static synchronized void m3137a(Throwable th) {
        synchronized (C0810d.class) {
            Object obj;
            List arrayList = new ArrayList();
            if (th != null) {
                Writer stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                obj = stringWriter.toString();
            } else {
                obj = "";
            }
            arrayList.add(obj);
            C0810d.m3138a(arrayList);
        }
    }

    /* renamed from: a */
    private static synchronized void m3138a(List<String> list) {
        synchronized (C0810d.class) {
            if (!(C0789a.m3020a(f1897b) || C0789a.m3020a(f1898c))) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(f1898c);
                for (String str : list) {
                    stringBuffer.append(", " + str);
                }
                stringBuffer.append("\n");
                try {
                    File file = new File(f1896a);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(f1896a, f1897b);
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileWriter fileWriter = file2.length() + ((long) stringBuffer.length()) <= 51200 ? new FileWriter(file2, true) : new FileWriter(file2);
                    fileWriter.write(stringBuffer.toString());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (Exception e) {
                }
            }
        }
    }
}
