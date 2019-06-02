package com.qiniu.android.p189c;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: StringMap */
/* renamed from: com.qiniu.android.c.c */
public final class C4337c {
    /* renamed from: a */
    private Map<String, Object> f15102a;

    /* compiled from: StringMap */
    /* renamed from: com.qiniu.android.c.c$a */
    public interface C4336a {
        /* renamed from: a */
        void mo6028a(String str, Object obj);
    }

    public C4337c() {
        this(new HashMap());
    }

    public C4337c(Map<String, Object> map) {
        this.f15102a = map;
    }

    /* renamed from: a */
    public C4337c m17122a(String str, Object obj) {
        this.f15102a.put(str, obj);
        return this;
    }

    /* renamed from: a */
    public C4337c m17123a(Map<String, String> map) {
        this.f15102a.putAll(map);
        return this;
    }

    /* renamed from: a */
    public void m17124a(C4336a c4336a) {
        for (Entry entry : this.f15102a.entrySet()) {
            c4336a.mo6028a((String) entry.getKey(), entry.getValue());
        }
    }
}
