package com.qiniu.android.p110b;

import com.qiniu.android.dns.C4345a;
import com.qiniu.android.dns.C4347c;
import com.qiniu.android.dns.NetworkInfo;
import com.qiniu.android.dns.local.C4351a;
import com.qiniu.android.dns.local.C4356e;
import com.qiniu.android.http.C4371f;
import com.qiniu.android.http.C4373h;
import com.qiniu.android.p188a.C4308a;
import com.qiniu.android.p188a.C4309b;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/* compiled from: Configuration */
/* renamed from: com.qiniu.android.b.a */
public final class C4313a {
    /* renamed from: a */
    public final C4308a f15034a;
    /* renamed from: b */
    public final C4308a f15035b;
    /* renamed from: c */
    public final C4320d f15036c;
    /* renamed from: d */
    public final C4310c f15037d;
    /* renamed from: e */
    public final C4371f f15038e;
    /* renamed from: f */
    public final int f15039f;
    /* renamed from: g */
    public final int f15040g;
    /* renamed from: h */
    public final int f15041h;
    /* renamed from: i */
    public final int f15042i;
    /* renamed from: j */
    public final int f15043j;
    /* renamed from: k */
    public C4373h f15044k;
    /* renamed from: l */
    public C4345a f15045l;

    /* compiled from: Configuration */
    /* renamed from: com.qiniu.android.b.a$1 */
    class C43111 implements C4310c {
        /* renamed from: a */
        final /* synthetic */ C4313a f15021a;

        C43111(C4313a c4313a) {
            this.f15021a = c4313a;
        }

        /* renamed from: a */
        public String mo6022a(String str, File file) {
            return str + "_._" + new StringBuffer(file.getAbsolutePath()).reverse();
        }
    }

    /* compiled from: Configuration */
    /* renamed from: com.qiniu.android.b.a$a */
    public static class C4312a {
        /* renamed from: a */
        private C4308a f15022a = C4309b.f15017a.f15019c;
        /* renamed from: b */
        private C4308a f15023b = C4309b.f15017a.f15020d;
        /* renamed from: c */
        private C4320d f15024c = null;
        /* renamed from: d */
        private C4310c f15025d = null;
        /* renamed from: e */
        private C4371f f15026e = null;
        /* renamed from: f */
        private int f15027f = 262144;
        /* renamed from: g */
        private int f15028g = 524288;
        /* renamed from: h */
        private int f15029h = 10;
        /* renamed from: i */
        private int f15030i = 60;
        /* renamed from: j */
        private int f15031j = 3;
        /* renamed from: k */
        private C4373h f15032k = null;
        /* renamed from: l */
        private C4345a f15033l = null;

        public C4312a() {
            C4356e c4356e;
            C4347c c = C4351a.m17158c();
            try {
                c4356e = new C4356e(InetAddress.getByName("119.29.29.29"));
            } catch (IOException e) {
                e.printStackTrace();
                c4356e = null;
            }
            this.f15033l = new C4345a(NetworkInfo.f15104b, new C4347c[]{c, c4356e});
        }

        /* renamed from: a */
        public C4313a m17060a() {
            return new C4313a();
        }
    }

    private C4313a(C4312a c4312a) {
        this.f15034a = c4312a.f15022a;
        this.f15035b = c4312a.f15023b == null ? c4312a.f15022a : c4312a.f15023b;
        this.f15039f = c4312a.f15027f;
        this.f15040g = c4312a.f15028g;
        this.f15041h = c4312a.f15029h;
        this.f15042i = c4312a.f15030i;
        this.f15036c = c4312a.f15024c;
        this.f15037d = m17061a(c4312a.f15025d);
        this.f15043j = c4312a.f15031j;
        this.f15038e = c4312a.f15026e;
        this.f15044k = c4312a.f15032k;
        this.f15045l = C4313a.m17062a(c4312a);
    }

    /* renamed from: a */
    private static C4345a m17062a(C4312a c4312a) {
        C4345a l = c4312a.f15033l;
        c4312a.f15022a.m17044a(l);
        if (c4312a.f15023b != null) {
            c4312a.f15023b.m17044a(l);
        }
        return l;
    }

    /* renamed from: a */
    private C4310c m17061a(C4310c c4310c) {
        if (c4310c == null) {
            return new C43111(this);
        }
        return c4310c;
    }
}
