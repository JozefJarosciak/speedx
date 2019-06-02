package com.alipay.sdk.protocol;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.cons.C0845b;
import com.alipay.sdk.sys.C0870b;
import com.alipay.sdk.tid.C0871a;
import com.alipay.sdk.tid.C0872b;
import com.alipay.sdk.util.C0873a;
import com.alipay.sdk.util.C0880h;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.protocol.b */
public final class C0868b {
    /* renamed from: a */
    public C0867a f2157a;
    /* renamed from: b */
    public String[] f2158b;
    /* renamed from: c */
    private String f2159c;

    private C0868b(String str) {
        this.f2159c = str;
    }

    private C0868b(String str, C0867a c0867a) {
        this.f2159c = str;
        this.f2157a = c0867a;
    }

    /* renamed from: a */
    private static void m3373a(C0868b c0868b) {
        String[] strArr = c0868b.f2158b;
        if (strArr.length == 3 && TextUtils.equals(C0845b.f2062c, strArr[0])) {
            Context context = C0870b.m3386a().f2177a;
            C0872b a = C0872b.m3405a();
            if (!TextUtils.isEmpty(strArr[1]) && !TextUtils.isEmpty(strArr[2])) {
                a.f2182a = strArr[1];
                a.f2183b = strArr[2];
                C0871a c0871a = new C0871a(context);
                try {
                    c0871a.m3403a(C0873a.m3414a(context).m3422a(), C0873a.m3414a(context).m3423b(), a.f2182a, a.f2183b);
                } catch (Exception e) {
                } finally {
                    c0871a.close();
                }
            }
        }
    }

    /* renamed from: a */
    public static List<C0868b> m3372a(JSONObject jSONObject) {
        List<C0868b> arrayList = new ArrayList();
        if (jSONObject == null) {
            return arrayList;
        }
        Object optString = jSONObject.optString("name", "");
        String[] strArr = null;
        if (!TextUtils.isEmpty(optString)) {
            strArr = optString.split(C0880h.f2220b);
        }
        for (int i = 0; i < strArr.length; i++) {
            C0867a a = C0867a.m3370a(strArr[i]);
            if (a != C0867a.None) {
                C0868b c0868b = new C0868b(strArr[i], a);
                c0868b.f2158b = C0868b.m3374a(strArr[i]);
                arrayList.add(c0868b);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private static String[] m3374a(String str) {
        List arrayList = new ArrayList();
        int indexOf = str.indexOf(40);
        int lastIndexOf = str.lastIndexOf(41);
        if (indexOf == -1 || lastIndexOf == -1 || lastIndexOf <= indexOf) {
            return null;
        }
        String[] split = str.substring(indexOf + 1, lastIndexOf).split(",");
        if (split != null) {
            for (indexOf = 0; indexOf < split.length; indexOf++) {
                if (!TextUtils.isEmpty(split[indexOf])) {
                    arrayList.add(split[indexOf].trim().replaceAll("'", "").replaceAll("\"", ""));
                }
            }
        }
        return (String[]) arrayList.toArray(new String[0]);
    }

    /* renamed from: b */
    private static String[] m3376b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return str.split(C0880h.f2220b);
    }

    /* renamed from: a */
    private String m3371a() {
        return this.f2159c;
    }

    /* renamed from: b */
    private C0867a m3375b() {
        return this.f2157a;
    }

    /* renamed from: c */
    private String[] m3377c() {
        return this.f2158b;
    }
}
