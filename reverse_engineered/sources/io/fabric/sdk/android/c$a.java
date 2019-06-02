package io.fabric.sdk.android;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import io.fabric.sdk.android.services.common.C4883l;
import io.fabric.sdk.android.services.concurrency.C4907g;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/* compiled from: Fabric */
public class c$a {
    /* renamed from: a */
    private final Context f17077a;
    /* renamed from: b */
    private C1468h[] f17078b;
    /* renamed from: c */
    private C4907g f17079c;
    /* renamed from: d */
    private Handler f17080d;
    /* renamed from: e */
    private C4835k f17081e;
    /* renamed from: f */
    private boolean f17082f;
    /* renamed from: g */
    private String f17083g;
    /* renamed from: h */
    private String f17084h;
    /* renamed from: i */
    private C4837f<C1520c> f17085i;

    public c$a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Context must not be null.");
        }
        this.f17077a = context.getApplicationContext();
    }

    /* renamed from: a */
    public c$a m19011a(C1468h... c1468hArr) {
        if (this.f17078b != null) {
            throw new IllegalStateException("Kits already set.");
        }
        this.f17078b = c1468hArr;
        return this;
    }

    /* renamed from: a */
    public C1520c m19012a() {
        Map hashMap;
        if (this.f17079c == null) {
            this.f17079c = C4907g.m19255a();
        }
        if (this.f17080d == null) {
            this.f17080d = new Handler(Looper.getMainLooper());
        }
        if (this.f17081e == null) {
            if (this.f17082f) {
                this.f17081e = new C4836b(3);
            } else {
                this.f17081e = new C4836b();
            }
        }
        if (this.f17084h == null) {
            this.f17084h = this.f17077a.getPackageName();
        }
        if (this.f17085i == null) {
            this.f17085i = C4837f.f17073d;
        }
        if (this.f17078b == null) {
            hashMap = new HashMap();
        } else {
            hashMap = C1520c.a(Arrays.asList(this.f17078b));
        }
        return new C1520c(this.f17077a, hashMap, this.f17079c, this.f17080d, this.f17081e, this.f17082f, this.f17085i, new C4883l(this.f17077a, this.f17084h, this.f17083g, hashMap.values()));
    }
}
