package com.qiniu.android.p110b;

import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/* compiled from: UploadOptions */
/* renamed from: com.qiniu.android.b.k */
public final class C4333k {
    /* renamed from: a */
    final Map<String, String> f15097a;
    /* renamed from: b */
    final String f15098b;
    /* renamed from: c */
    final boolean f15099c;
    /* renamed from: d */
    final C1876h f15100d;
    /* renamed from: e */
    final C4326f f15101e;

    /* compiled from: UploadOptions */
    /* renamed from: com.qiniu.android.b.k$1 */
    class C43311 implements C1876h {
        /* renamed from: a */
        final /* synthetic */ C4333k f15095a;

        C43311(C4333k c4333k) {
            this.f15095a = c4333k;
        }

        /* renamed from: a */
        public void mo3262a(String str, double d) {
            Log.d("qiniu up progress", "" + d);
        }
    }

    /* compiled from: UploadOptions */
    /* renamed from: com.qiniu.android.b.k$2 */
    class C43322 implements C4326f {
        /* renamed from: a */
        final /* synthetic */ C4333k f15096a;

        C43322(C4333k c4333k) {
            this.f15096a = c4333k;
        }

        /* renamed from: a */
        public boolean mo6025a() {
            return false;
        }
    }

    public C4333k(Map<String, String> map, String str, boolean z, C1876h c1876h, C4326f c4326f) {
        this.f15097a = C4333k.m17116a((Map) map);
        this.f15098b = C4333k.m17115a(str);
        this.f15099c = z;
        if (c1876h == null) {
            c1876h = new C43311(this);
        }
        this.f15100d = c1876h;
        if (c4326f == null) {
            c4326f = new C43322(this);
        }
        this.f15101e = c4326f;
    }

    /* renamed from: a */
    private static Map<String, String> m17116a(Map<String, String> map) {
        Map<String, String> hashMap = new HashMap();
        if (map == null) {
            return hashMap;
        }
        for (Entry entry : map.entrySet()) {
            if (!(!((String) entry.getKey()).startsWith("x:") || entry.getValue() == null || ((String) entry.getValue()).equals(""))) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    static C4333k m17114a() {
        return new C4333k(null, null, false, null, null);
    }

    /* renamed from: a */
    private static String m17115a(String str) {
        if (str == null || str.equals("")) {
            return "application/octet-stream";
        }
        return str;
    }
}
