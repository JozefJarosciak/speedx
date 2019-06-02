package com.baidu.platform.comjni.map.commonmemcache;

/* renamed from: com.baidu.platform.comjni.map.commonmemcache.a */
public class C1287a {
    /* renamed from: a */
    private long f3938a;
    /* renamed from: b */
    private JNICommonMemCache f3939b;

    public C1287a() {
        this.f3938a = 0;
        this.f3939b = null;
        this.f3939b = new JNICommonMemCache();
    }

    /* renamed from: a */
    public long m4947a() {
        this.f3938a = this.f3939b.Create();
        return this.f3938a;
    }

    /* renamed from: b */
    public void m4948b() {
        if (this.f3938a != 0) {
            this.f3939b.Init(this.f3938a);
        }
    }
}
