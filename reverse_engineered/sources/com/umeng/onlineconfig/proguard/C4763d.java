package com.umeng.onlineconfig.proguard;

import org.json.JSONObject;

/* compiled from: OnlineConfigURequest */
/* renamed from: com.umeng.onlineconfig.proguard.d */
public abstract class C4763d {
    /* renamed from: b */
    protected static String f16683b = "POST";
    /* renamed from: c */
    protected static String f16684c = "GET";
    /* renamed from: d */
    protected String f16685d;

    /* renamed from: a */
    public abstract JSONObject mo6182a();

    /* renamed from: b */
    public abstract String mo6183b();

    /* renamed from: c */
    protected String m18708c() {
        return f16683b;
    }

    public C4763d(String str) {
        this.f16685d = str;
    }

    /* renamed from: a */
    public void m18706a(String str) {
        this.f16685d = str;
    }

    /* renamed from: d */
    public String m18709d() {
        return this.f16685d;
    }
}
