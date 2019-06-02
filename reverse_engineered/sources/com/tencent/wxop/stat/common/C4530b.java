package com.tencent.wxop.stat.common;

import android.content.Context;
import com.tencent.wxop.stat.C4525a;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

/* renamed from: com.tencent.wxop.stat.common.b */
public class C4530b {
    /* renamed from: a */
    static C4532d f16048a;
    /* renamed from: d */
    private static StatLogger f16049d = C4539k.m18052b();
    /* renamed from: e */
    private static JSONObject f16050e = new JSONObject();
    /* renamed from: b */
    Integer f16051b = null;
    /* renamed from: c */
    String f16052c = null;

    public C4530b(Context context) {
        try {
            C4530b.m18020a(context);
            this.f16051b = C4539k.m18071m(context.getApplicationContext());
            this.f16052c = C4525a.m17934a(context).m17943b();
        } catch (Throwable th) {
            f16049d.m18011e(th);
        }
    }

    /* renamed from: a */
    static synchronized C4532d m18020a(Context context) {
        C4532d c4532d;
        synchronized (C4530b.class) {
            if (f16048a == null) {
                f16048a = new C4532d(context.getApplicationContext());
            }
            c4532d = f16048a;
        }
        return c4532d;
    }

    /* renamed from: a */
    public static void m18021a(Context context, Map<String, String> map) {
        if (map != null) {
            for (Entry entry : new HashMap(map).entrySet()) {
                f16050e.put((String) entry.getKey(), entry.getValue());
            }
        }
    }

    /* renamed from: a */
    public void m18022a(JSONObject jSONObject, Thread thread) {
        JSONObject jSONObject2 = new JSONObject();
        try {
            if (f16048a != null) {
                f16048a.m18023a(jSONObject2, thread);
            }
            C4545q.m18100a(jSONObject2, "cn", this.f16052c);
            if (this.f16051b != null) {
                jSONObject2.put("tn", this.f16051b);
            }
            if (thread == null) {
                jSONObject.put("ev", jSONObject2);
            } else {
                jSONObject.put("errkv", jSONObject2.toString());
            }
            if (f16050e != null && f16050e.length() > 0) {
                jSONObject.put("eva", f16050e);
            }
        } catch (Throwable th) {
            f16049d.m18011e(th);
        }
    }
}
