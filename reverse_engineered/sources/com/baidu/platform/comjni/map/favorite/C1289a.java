package com.baidu.platform.comjni.map.favorite;

import android.os.Bundle;

/* renamed from: com.baidu.platform.comjni.map.favorite.a */
public class C1289a {
    /* renamed from: a */
    private long f3941a;
    /* renamed from: b */
    private JNIFavorite f3942b;

    /* renamed from: com.baidu.platform.comjni.map.favorite.a$a */
    public static class C1288a {
        /* renamed from: a */
        public static boolean f3940a = false;

        /* renamed from: b */
        private static void m4950b() {
            f3940a = true;
        }
    }

    public C1289a() {
        this.f3941a = 0;
        this.f3942b = null;
        this.f3942b = new JNIFavorite();
    }

    /* renamed from: a */
    public int m4951a(Bundle bundle) {
        try {
            return this.f3942b.GetAll(this.f3941a, bundle);
        } catch (Throwable th) {
            return 0;
        }
    }

    /* renamed from: a */
    public long m4952a() {
        this.f3941a = this.f3942b.Create();
        return this.f3941a;
    }

    /* renamed from: a */
    public boolean m4953a(int i) {
        return this.f3942b.SetType(this.f3941a, i);
    }

    /* renamed from: a */
    public boolean m4954a(String str) {
        return this.f3942b.Remove(this.f3941a, str);
    }

    /* renamed from: a */
    public boolean m4955a(String str, String str2) {
        C1288a.m4950b();
        return this.f3942b.Add(this.f3941a, str, str2);
    }

    /* renamed from: a */
    public boolean m4956a(String str, String str2, String str3, int i, int i2, int i3) {
        return this.f3942b.Load(this.f3941a, str, str2, str3, i, i2, i3);
    }

    /* renamed from: b */
    public int m4957b() {
        return this.f3942b.Release(this.f3941a);
    }

    /* renamed from: b */
    public String m4958b(String str) {
        try {
            return this.f3942b.GetValue(this.f3941a, str);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: b */
    public boolean m4959b(String str, String str2) {
        C1288a.m4950b();
        return this.f3942b.Update(this.f3941a, str, str2);
    }

    /* renamed from: c */
    public boolean m4960c() {
        return this.f3942b.Clear(this.f3941a);
    }

    /* renamed from: c */
    public boolean m4961c(String str) {
        try {
            return this.f3942b.IsExist(this.f3941a, str);
        } catch (Throwable th) {
            return false;
        }
    }

    /* renamed from: d */
    public boolean m4962d() {
        return this.f3942b.SaveCache(this.f3941a);
    }
}
