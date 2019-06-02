package com.baidu.platform.comapi.util;

import android.content.Context;
import android.os.Environment;
import java.io.File;

/* renamed from: com.baidu.platform.comapi.util.c */
public final class C1279c {
    /* renamed from: a */
    private final boolean f3876a;
    /* renamed from: b */
    private final String f3877b;
    /* renamed from: c */
    private final String f3878c;
    /* renamed from: d */
    private final String f3879d;
    /* renamed from: e */
    private final String f3880e;
    /* renamed from: f */
    private final String f3881f;

    C1279c(Context context) {
        this.f3876a = false;
        this.f3877b = Environment.getExternalStorageDirectory().getAbsolutePath();
        this.f3878c = this.f3877b + File.separator + "BaiduMapSDKNew";
        this.f3879d = context.getCacheDir().getAbsolutePath();
        this.f3880e = "";
        this.f3881f = "";
    }

    C1279c(String str, boolean z, String str2, Context context) {
        this.f3876a = z;
        this.f3877b = str;
        this.f3878c = this.f3877b + File.separator + "BaiduMapSDKNew";
        this.f3879d = this.f3878c + File.separator + "cache";
        this.f3880e = context.getCacheDir().getAbsolutePath();
        this.f3881f = str2;
    }

    /* renamed from: a */
    public String m4834a() {
        return this.f3877b;
    }

    /* renamed from: b */
    public String m4835b() {
        return this.f3877b + File.separator + "BaiduMapSDKNew";
    }

    /* renamed from: c */
    public String m4836c() {
        return this.f3879d;
    }

    /* renamed from: d */
    public String m4837d() {
        return this.f3880e;
    }

    public boolean equals(Object obj) {
        if (obj == null || !C1279c.class.isInstance(obj)) {
            return false;
        }
        return this.f3877b.equals(((C1279c) obj).f3877b);
    }
}
