package com.alipay.apmobilesecuritysdk.p025d;

import android.content.Context;
import com.alipay.p029b.p030a.p031a.p032a.p033a.C0787b;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.alipay.apmobilesecuritysdk.d.e */
public final class C0771e {
    /* renamed from: a */
    private static Map<String, String> f1816a = null;
    /* renamed from: b */
    private static final String[] f1817b = new String[]{"AD1", "AD2", "AD3", "AD5", "AD6", "AD7", "AD8", "AD9", "AD10", "AD11", "AD12", "AD13", "AD14", "AD15", "AD16", "AD18", "AD20", "AD21", "AD23", "AD24", "AD26", "AD27", "AD28", "AD29", "AD30", "AD31", "AD32", "AD34", "AA1", "AA2", "AA3", "AA4", "AA5", "AC4", "AE1", "AE2", "AE3", "AE4", "AE5", "AE6", "AE7", "AE8", "AE9", "AE10", "AE11", "AE12", "AE13", "AE14", "AE15"};

    /* renamed from: a */
    private static String m2931a(Map<String, String> map) {
        StringBuffer stringBuffer = new StringBuffer();
        List arrayList = new ArrayList(map.keySet());
        Collections.sort(arrayList);
        int i = 0;
        while (i < arrayList.size()) {
            String str = (String) arrayList.get(i);
            String str2 = (String) map.get(str);
            if (str2 == null) {
                str2 = "";
            }
            stringBuffer.append((i == 0 ? "" : C0869a.f2161b) + str + SimpleComparison.EQUAL_TO_OPERATION + str2);
            i++;
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public static synchronized Map<String, String> m2932a(Context context, Map<String, String> map) {
        Map<String, String> map2;
        synchronized (C0771e.class) {
            if (f1816a == null) {
                C0771e.m2935c(context, map);
            }
            map2 = f1816a;
        }
        return map2;
    }

    /* renamed from: a */
    public static synchronized void m2933a() {
        synchronized (C0771e.class) {
            f1816a = null;
        }
    }

    /* renamed from: b */
    public static synchronized String m2934b(Context context, Map<String, String> map) {
        Map treeMap;
        synchronized (C0771e.class) {
            C0771e.m2932a(context, map);
            treeMap = new TreeMap();
            for (Object obj : f1817b) {
                if (f1816a.containsKey(obj)) {
                    treeMap.put(obj, f1816a.get(obj));
                }
            }
        }
        return C0787b.m3011a(C0771e.m2931a(treeMap));
    }

    /* renamed from: c */
    private static synchronized void m2935c(Context context, Map<String, String> map) {
        synchronized (C0771e.class) {
            Map treeMap = new TreeMap();
            f1816a = treeMap;
            treeMap.putAll(C0768b.m2928a(context, map));
            f1816a.putAll(C0770d.m2930a(context));
            f1816a.putAll(C0769c.m2929a(context));
            f1816a.putAll(C0767a.m2927a(context, map));
        }
    }
}
