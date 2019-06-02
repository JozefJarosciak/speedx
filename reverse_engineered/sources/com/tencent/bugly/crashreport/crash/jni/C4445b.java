package com.tencent.bugly.crashreport.crash.jni;

import android.content.Context;
import android.support.v4.os.EnvironmentCompat;
import ch.qos.logback.core.CoreConstants;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.tencent.bugly.crashreport.common.info.C4417a;
import com.tencent.bugly.crashreport.crash.CrashDetailBean;
import com.tencent.bugly.proguard.C4447j;
import com.tencent.bugly.proguard.C4448b;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: BUGLY */
/* renamed from: com.tencent.bugly.crashreport.crash.jni.b */
public class C4445b {
    /* renamed from: a */
    private StringBuilder f15527a;
    /* renamed from: b */
    private int f15528b = 0;

    /* renamed from: d */
    private void m17501d(String str) {
        for (int i = 0; i < this.f15528b; i++) {
            this.f15527a.append('\t');
        }
        if (str != null) {
            this.f15527a.append(str).append(": ");
        }
    }

    public C4445b(StringBuilder stringBuilder, int i) {
        this.f15527a = stringBuilder;
        this.f15528b = i;
    }

    /* renamed from: a */
    public C4445b m17513a(boolean z, String str) {
        m17501d(str);
        this.f15527a.append(z ? 'T' : 'F').append('\n');
        return this;
    }

    /* renamed from: c */
    private static Map<String, Integer> m17499c(String str) {
        if (str == null) {
            return null;
        }
        try {
            Map<String, Integer> hashMap = new HashMap();
            for (String split : str.split(",")) {
                String[] split2 = split.split(":");
                if (split2.length != 2) {
                    C4475w.m17753e("error format at %s", split);
                    return null;
                }
                hashMap.put(split2[0], Integer.valueOf(Integer.parseInt(split2[1])));
            }
            return hashMap;
        } catch (Exception e) {
            C4475w.m17753e("error format intStateStr %s", str);
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public C4445b m17502a(byte b, String str) {
        m17501d(str);
        this.f15527a.append(b).append('\n');
        return this;
    }

    /* renamed from: a */
    public C4445b m17503a(char c, String str) {
        m17501d(str);
        this.f15527a.append(c).append('\n');
        return this;
    }

    /* renamed from: a */
    public C4445b m17512a(short s, String str) {
        m17501d(str);
        this.f15527a.append(s).append('\n');
        return this;
    }

    /* renamed from: a */
    public C4445b m17506a(int i, String str) {
        m17501d(str);
        this.f15527a.append(i).append('\n');
        return this;
    }

    /* renamed from: a */
    protected static String m17495a(String str) {
        if (str == null) {
            return "";
        }
        String[] split = str.split("\n");
        if (split == null || split.length == 0) {
            return str;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String str2 : split) {
            if (!str2.contains("java.lang.Thread.getStackTrace(")) {
                stringBuilder.append(str2).append("\n");
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public C4445b m17507a(long j, String str) {
        m17501d(str);
        this.f15527a.append(j).append('\n');
        return this;
    }

    /* renamed from: a */
    public C4445b m17505a(float f, String str) {
        m17501d(str);
        this.f15527a.append(f).append('\n');
        return this;
    }

    /* renamed from: a */
    public C4445b m17504a(double d, String str) {
        m17501d(str);
        this.f15527a.append(d).append('\n');
        return this;
    }

    /* renamed from: a */
    private static CrashDetailBean m17493a(Context context, Map<String, String> map, NativeExceptionHandler nativeExceptionHandler) {
        if (map == null) {
            return null;
        }
        if (C4417a.m17303a(context) == null) {
            C4475w.m17753e("abnormal com info not created", new Object[0]);
            return null;
        }
        String str = (String) map.get("intStateStr");
        if (str == null || str.trim().length() <= 0) {
            C4475w.m17753e("no intStateStr", new Object[0]);
            return null;
        }
        Map c = C4445b.m17499c(str);
        if (c == null) {
            C4475w.m17753e("parse intSateMap fail", Integer.valueOf(map.size()));
            return null;
        }
        try {
            ((Integer) c.get("sino")).intValue();
            ((Integer) c.get("sud")).intValue();
            String str2 = (String) map.get("soVersion");
            if (str2 == null) {
                C4475w.m17753e("error format at version", new Object[0]);
                return null;
            }
            String str3;
            String str4;
            String str5;
            String str6;
            String str7;
            String str8;
            String str9;
            str = (String) map.get("errorAddr");
            String str10 = str == null ? EnvironmentCompat.MEDIA_UNKNOWN : str;
            str = (String) map.get("codeMsg");
            if (str == null) {
                str3 = EnvironmentCompat.MEDIA_UNKNOWN;
            } else {
                str3 = str;
            }
            str = (String) map.get("tombPath");
            if (str == null) {
                str4 = EnvironmentCompat.MEDIA_UNKNOWN;
            } else {
                str4 = str;
            }
            str = (String) map.get("signalName");
            if (str == null) {
                str5 = EnvironmentCompat.MEDIA_UNKNOWN;
            } else {
                str5 = str;
            }
            map.get("errnoMsg");
            str = (String) map.get("stack");
            if (str == null) {
                str6 = EnvironmentCompat.MEDIA_UNKNOWN;
            } else {
                str6 = str;
            }
            str = (String) map.get("jstack");
            if (str != null) {
                str6 = str6 + "java:\n" + str;
            }
            Integer num = (Integer) c.get("sico");
            if (num != null && num.intValue() > 0) {
                str5 = str5 + "(" + str3 + ")";
                str3 = "KERNEL";
            }
            str = (String) map.get("nativeLog");
            byte[] bArr = null;
            if (!(str == null || str.isEmpty())) {
                bArr = C4479y.m17796a(null, str);
            }
            str = (String) map.get("sendingProcess");
            if (str == null) {
                str7 = EnvironmentCompat.MEDIA_UNKNOWN;
            } else {
                str7 = str;
            }
            num = (Integer) c.get("spd");
            if (num != null) {
                str7 = str7 + "(" + num + ")";
            }
            str = (String) map.get("threadName");
            if (str == null) {
                str8 = EnvironmentCompat.MEDIA_UNKNOWN;
            } else {
                str8 = str;
            }
            num = (Integer) c.get("et");
            if (num != null) {
                str8 = str8 + "(" + num + ")";
            }
            str = (String) map.get("processName");
            if (str == null) {
                str9 = EnvironmentCompat.MEDIA_UNKNOWN;
            } else {
                str9 = str;
            }
            num = (Integer) c.get("ep");
            if (num != null) {
                str9 = str9 + "(" + num + ")";
            }
            Map map2 = null;
            str = (String) map.get("key-value");
            if (str != null) {
                map2 = new HashMap();
                for (String split : str.split("\n")) {
                    String[] split2 = split.split(SimpleComparison.EQUAL_TO_OPERATION);
                    if (split2.length == 2) {
                        map2.put(split2[0], split2[1]);
                    }
                }
            }
            CrashDetailBean packageCrashDatas = nativeExceptionHandler.packageCrashDatas(str9, str8, (((long) ((Integer) c.get("etms")).intValue()) / 1000) + (((long) ((Integer) c.get("ets")).intValue()) * 1000), str5, str10, C4445b.m17495a(str6), str3, str7, str4, str2, bArr, map2, false);
            if (packageCrashDatas != null) {
                str = (String) map.get("userId");
                if (str != null) {
                    C4475w.m17751c("[Native record info] userId: %s", str);
                    packageCrashDatas.f15386m = str;
                }
                str = (String) map.get("sysLog");
                if (str != null) {
                    packageCrashDatas.f15396w = str;
                }
                str = (String) map.get("appVersion");
                if (str != null) {
                    C4475w.m17751c("[Native record info] appVersion: %s", str);
                    packageCrashDatas.f15379f = str;
                }
                str = (String) map.get("isAppForeground");
                if (str != null) {
                    C4475w.m17751c("[Native record info] isAppForeground: %s", str);
                    packageCrashDatas.f15364M = str.equalsIgnoreCase("true");
                }
                str = (String) map.get("launchTime");
                if (str != null) {
                    C4475w.m17751c("[Native record info] launchTime: %s", str);
                    packageCrashDatas.f15363L = Long.parseLong(str);
                }
                packageCrashDatas.f15398y = null;
                packageCrashDatas.f15384k = true;
            }
            return packageCrashDatas;
        } catch (Throwable e) {
            if (!C4475w.m17748a(e)) {
                e.printStackTrace();
            }
        } catch (Throwable e2) {
            C4475w.m17753e("error format", new Object[0]);
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public C4445b m17521b(String str, String str2) {
        m17501d(str2);
        if (str == null) {
            this.f15527a.append("null\n");
        } else {
            this.f15527a.append(str).append('\n');
        }
        return this;
    }

    /* renamed from: a */
    public C4445b m17514a(byte[] bArr, String str) {
        m17501d(str);
        if (bArr == null) {
            this.f15527a.append("null\n");
        } else if (bArr.length == 0) {
            this.f15527a.append(bArr.length).append(", []\n");
        } else {
            this.f15527a.append(bArr.length).append(", [\n");
            C4445b c4445b = new C4445b(this.f15527a, this.f15528b + 1);
            for (byte a : bArr) {
                c4445b.m17502a(a, null);
            }
            m17503a(']', null);
        }
        return this;
    }

    /* renamed from: a */
    public C4445b m17520a(short[] sArr, String str) {
        m17501d(str);
        if (sArr == null) {
            this.f15527a.append("null\n");
        } else if (sArr.length == 0) {
            this.f15527a.append(sArr.length).append(", []\n");
        } else {
            this.f15527a.append(sArr.length).append(", [\n");
            C4445b c4445b = new C4445b(this.f15527a, this.f15528b + 1);
            for (short a : sArr) {
                c4445b.m17512a(a, null);
            }
            m17503a(']', null);
        }
        return this;
    }

    /* renamed from: a */
    public C4445b m17517a(int[] iArr, String str) {
        m17501d(str);
        if (iArr == null) {
            this.f15527a.append("null\n");
        } else if (iArr.length == 0) {
            this.f15527a.append(iArr.length).append(", []\n");
        } else {
            this.f15527a.append(iArr.length).append(", [\n");
            C4445b c4445b = new C4445b(this.f15527a, this.f15528b + 1);
            for (int a : iArr) {
                c4445b.m17506a(a, null);
            }
            m17503a(']', null);
        }
        return this;
    }

    /* renamed from: a */
    public C4445b m17518a(long[] jArr, String str) {
        m17501d(str);
        if (jArr == null) {
            this.f15527a.append("null\n");
        } else if (jArr.length == 0) {
            this.f15527a.append(jArr.length).append(", []\n");
        } else {
            this.f15527a.append(jArr.length).append(", [\n");
            C4445b c4445b = new C4445b(this.f15527a, this.f15528b + 1);
            for (long a : jArr) {
                c4445b.m17507a(a, null);
            }
            m17503a(']', null);
        }
        return this;
    }

    /* renamed from: a */
    public C4445b m17516a(float[] fArr, String str) {
        m17501d(str);
        if (fArr == null) {
            this.f15527a.append("null\n");
        } else if (fArr.length == 0) {
            this.f15527a.append(fArr.length).append(", []\n");
        } else {
            this.f15527a.append(fArr.length).append(", [\n");
            C4445b c4445b = new C4445b(this.f15527a, this.f15528b + 1);
            for (float a : fArr) {
                c4445b.m17505a(a, null);
            }
            m17503a(']', null);
        }
        return this;
    }

    /* renamed from: a */
    public C4445b m17515a(double[] dArr, String str) {
        m17501d(str);
        if (dArr == null) {
            this.f15527a.append("null\n");
        } else if (dArr.length == 0) {
            this.f15527a.append(dArr.length).append(", []\n");
        } else {
            this.f15527a.append(dArr.length).append(", [\n");
            C4445b c4445b = new C4445b(this.f15527a, this.f15528b + 1);
            for (double a : dArr) {
                c4445b.m17504a(a, null);
            }
            m17503a(']', null);
        }
        return this;
    }

    /* renamed from: a */
    public <K, V> C4445b m17511a(Map<K, V> map, String str) {
        m17501d(str);
        if (map == null) {
            this.f15527a.append("null\n");
        } else if (map.isEmpty()) {
            this.f15527a.append(map.size()).append(", {}\n");
        } else {
            this.f15527a.append(map.size()).append(", {\n");
            C4445b c4445b = new C4445b(this.f15527a, this.f15528b + 1);
            C4445b c4445b2 = new C4445b(this.f15527a, this.f15528b + 2);
            for (Entry entry : map.entrySet()) {
                c4445b.m17503a((char) CoreConstants.LEFT_PARENTHESIS_CHAR, null);
                c4445b2.m17509a(entry.getKey(), null);
                c4445b2.m17509a(entry.getValue(), null);
                c4445b.m17503a((char) CoreConstants.RIGHT_PARENTHESIS_CHAR, null);
            }
            m17503a((char) CoreConstants.CURLY_RIGHT, null);
        }
        return this;
    }

    /* renamed from: a */
    private static String m17494a(BufferedInputStream bufferedInputStream) throws IOException {
        if (bufferedInputStream == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        while (true) {
            int read = bufferedInputStream.read();
            if (read == -1) {
                return null;
            }
            if (read == 0) {
                return stringBuilder.toString();
            }
            stringBuilder.append((char) read);
        }
    }

    /* renamed from: a */
    public <T> C4445b m17519a(T[] tArr, String str) {
        m17501d(str);
        if (tArr == null) {
            this.f15527a.append("null\n");
        } else if (tArr.length == 0) {
            this.f15527a.append(tArr.length).append(", []\n");
        } else {
            this.f15527a.append(tArr.length).append(", [\n");
            C4445b c4445b = new C4445b(this.f15527a, this.f15528b + 1);
            for (Object a : tArr) {
                c4445b.m17509a(a, null);
            }
            m17503a(']', null);
        }
        return this;
    }

    /* renamed from: a */
    public <T> C4445b m17510a(Collection<T> collection, String str) {
        if (collection != null) {
            return m17519a(collection.toArray(), str);
        }
        m17501d(str);
        this.f15527a.append("null\t");
        return this;
    }

    /* renamed from: a */
    public static CrashDetailBean m17492a(Context context, String str, NativeExceptionHandler nativeExceptionHandler) {
        BufferedInputStream bufferedInputStream;
        IOException e;
        Throwable th;
        CrashDetailBean crashDetailBean = null;
        if (context == null || str == null || nativeExceptionHandler == null) {
            C4475w.m17753e("get eup record file args error", new Object[0]);
        } else {
            File file = new File(str, "rqd_record.eup");
            if (file.exists() && file.canRead()) {
                try {
                    bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
                    try {
                        String a = C4445b.m17494a(bufferedInputStream);
                        if (a == null || !a.equals("NATIVE_RQD_REPORT")) {
                            C4475w.m17753e("record read fail! %s", a);
                            try {
                                bufferedInputStream.close();
                            } catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        } else {
                            Map hashMap = new HashMap();
                            Object obj = crashDetailBean;
                            while (true) {
                                String a2 = C4445b.m17494a(bufferedInputStream);
                                if (a2 == null) {
                                    break;
                                } else if (obj == null) {
                                    obj = a2;
                                } else {
                                    hashMap.put(obj, a2);
                                    obj = crashDetailBean;
                                }
                            }
                            if (obj != null) {
                                C4475w.m17753e("record not pair! drop! %s", obj);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e22) {
                                    e22.printStackTrace();
                                }
                            } else {
                                crashDetailBean = C4445b.m17493a(context, hashMap, nativeExceptionHandler);
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e222) {
                                    e222.printStackTrace();
                                }
                            }
                        }
                    } catch (IOException e3) {
                        e222 = e3;
                        try {
                            e222.printStackTrace();
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e2222) {
                                    e2222.printStackTrace();
                                }
                            }
                            return crashDetailBean;
                        } catch (Throwable th2) {
                            th = th2;
                            if (bufferedInputStream != null) {
                                try {
                                    bufferedInputStream.close();
                                } catch (IOException e22222) {
                                    e22222.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    }
                } catch (IOException e4) {
                    e22222 = e4;
                    bufferedInputStream = crashDetailBean;
                    e22222.printStackTrace();
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    return crashDetailBean;
                } catch (Throwable th3) {
                    bufferedInputStream = crashDetailBean;
                    th = th3;
                    if (bufferedInputStream != null) {
                        bufferedInputStream.close();
                    }
                    throw th;
                }
            }
        }
        return crashDetailBean;
    }

    /* renamed from: a */
    public <T> C4445b m17509a(T t, String str) {
        if (t == null) {
            this.f15527a.append("null\n");
        } else if (t instanceof Byte) {
            m17502a(((Byte) t).byteValue(), str);
        } else if (t instanceof Boolean) {
            m17513a(((Boolean) t).booleanValue(), str);
        } else if (t instanceof Short) {
            m17512a(((Short) t).shortValue(), str);
        } else if (t instanceof Integer) {
            m17506a(((Integer) t).intValue(), str);
        } else if (t instanceof Long) {
            m17507a(((Long) t).longValue(), str);
        } else if (t instanceof Float) {
            m17505a(((Float) t).floatValue(), str);
        } else if (t instanceof Double) {
            m17504a(((Double) t).doubleValue(), str);
        } else if (t instanceof String) {
            m17521b((String) t, str);
        } else if (t instanceof Map) {
            m17511a((Map) t, str);
        } else if (t instanceof List) {
            m17510a((List) t, str);
        } else if (t instanceof C4447j) {
            m17508a((C4447j) t, str);
        } else if (t instanceof byte[]) {
            m17514a((byte[]) t, str);
        } else if (t instanceof boolean[]) {
            m17509a((boolean[]) t, str);
        } else if (t instanceof short[]) {
            m17520a((short[]) t, str);
        } else if (t instanceof int[]) {
            m17517a((int[]) t, str);
        } else if (t instanceof long[]) {
            m17518a((long[]) t, str);
        } else if (t instanceof float[]) {
            m17516a((float[]) t, str);
        } else if (t instanceof double[]) {
            m17515a((double[]) t, str);
        } else if (t.getClass().isArray()) {
            m17519a((Object[]) t, str);
        } else {
            throw new C4448b("write object error: unsupport type.");
        }
        return this;
    }

    /* renamed from: c */
    private static String m17498c(String str, String str2) {
        String str3 = null;
        BufferedReader a = C4479y.m17774a(str, "reg_record.txt");
        if (a != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                String readLine = a.readLine();
                if (readLine != null && readLine.startsWith(str2)) {
                    String str4 = "                ";
                    int i = 0;
                    int i2 = 18;
                    int i3 = 0;
                    while (true) {
                        String readLine2 = a.readLine();
                        if (readLine2 == null) {
                            break;
                        }
                        if (i3 % 4 == 0) {
                            if (i3 > 0) {
                                stringBuilder.append("\n");
                            }
                            stringBuilder.append("  ");
                        } else {
                            if (readLine2.length() > 16) {
                                i2 = 28;
                            }
                            stringBuilder.append(str4.substring(0, i2 - i));
                        }
                        i = readLine2.length();
                        stringBuilder.append(readLine2);
                        i3++;
                    }
                    stringBuilder.append("\n");
                    str3 = stringBuilder.toString();
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e) {
                            C4475w.m17748a(e);
                        }
                    }
                } else if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e2) {
                        C4475w.m17748a(e2);
                    }
                }
            } catch (Throwable th) {
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e22) {
                        C4475w.m17748a(e22);
                    }
                }
            }
        }
        return str3;
    }

    /* renamed from: a */
    public C4445b m17508a(C4447j c4447j, String str) {
        m17503a((char) CoreConstants.CURLY_LEFT, str);
        if (c4447j == null) {
            this.f15527a.append('\t').append("null");
        } else {
            c4447j.mo6072a(this.f15527a, this.f15528b + 1);
        }
        m17503a((char) CoreConstants.CURLY_RIGHT, null);
        return this;
    }

    /* renamed from: d */
    private static String m17500d(String str, String str2) {
        String str3 = null;
        BufferedReader a = C4479y.m17774a(str, "map_record.txt");
        if (a != null) {
            try {
                StringBuilder stringBuilder = new StringBuilder();
                String readLine = a.readLine();
                if (readLine != null && readLine.startsWith(str2)) {
                    while (true) {
                        readLine = a.readLine();
                        if (readLine == null) {
                            break;
                        }
                        stringBuilder.append("  ");
                        stringBuilder.append(readLine);
                        stringBuilder.append("\n");
                    }
                    str3 = stringBuilder.toString();
                    if (a != null) {
                        try {
                            a.close();
                        } catch (Throwable e) {
                            C4475w.m17748a(e);
                        }
                    }
                } else if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e2) {
                        C4475w.m17748a(e2);
                    }
                }
            } catch (Throwable th) {
                if (a != null) {
                    try {
                        a.close();
                    } catch (Throwable e22) {
                        C4475w.m17748a(e22);
                    }
                }
            }
        }
        return str3;
    }

    /* renamed from: a */
    public static String m17496a(String str, String str2) {
        if (str == null || str2 == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        String c = C4445b.m17498c(str, str2);
        if (!(c == null || c.isEmpty())) {
            stringBuilder.append("Register infos:\n");
            stringBuilder.append(c);
        }
        c = C4445b.m17500d(str, str2);
        if (!(c == null || c.isEmpty())) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("\n");
            }
            stringBuilder.append("System SO infos:\n");
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    public static void m17497b(String str) {
        File file = new File(str, "rqd_record.eup");
        if (file.exists() && file.canWrite()) {
            file.delete();
            C4475w.m17751c("delete record file %s", file.getAbsoluteFile());
        }
        file = new File(str, "reg_record.txt");
        if (file.exists() && file.canWrite()) {
            file.delete();
            C4475w.m17751c("delete record file %s", file.getAbsoluteFile());
        }
        file = new File(str, "map_record.txt");
        if (file.exists() && file.canWrite()) {
            file.delete();
            C4475w.m17751c("delete record file %s", file.getAbsoluteFile());
        }
        file = new File(str, "backup_record.txt");
        if (file.exists() && file.canWrite()) {
            file.delete();
            C4475w.m17751c("delete record file %s", file.getAbsoluteFile());
        }
    }
}
