package com.beastbikes.android.widget.p168e;

import com.beastbikes.android.widget.p168e.p169a.C2640a;
import com.beastbikes.android.widget.p168e.p169a.C2641b;
import com.beastbikes.android.widget.p168e.p169a.C2642c;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;

/* compiled from: CommonShareHandle */
/* renamed from: com.beastbikes.android.widget.e.a */
public class C2643a {
    /* renamed from: a */
    private C2648b f12362a;
    /* renamed from: b */
    private C2653c f12363b;

    public C2643a(BaseFragmentActivity baseFragmentActivity, C2640a c2640a) {
        if (c2640a instanceof C2641b) {
            this.f12362a = new C2648b(baseFragmentActivity, (C2641b) c2640a);
        } else if (c2640a instanceof C2642c) {
            this.f12363b = new C2653c(baseFragmentActivity, (C2642c) c2640a);
        }
    }

    /* renamed from: a */
    public void m13168a(C2640a c2640a) {
        if (c2640a instanceof C2641b) {
            this.f12362a.m13179e();
        } else {
            this.f12363b.m13187e();
        }
    }

    /* renamed from: b */
    public void m13169b(C2640a c2640a) {
        if (c2640a instanceof C2641b) {
            this.f12362a.m13180f();
        } else {
            this.f12363b.m13188f();
        }
    }

    /* renamed from: c */
    public void m13170c(C2640a c2640a) {
        if (c2640a instanceof C2641b) {
            this.f12362a.m13175a();
        } else {
            this.f12363b.m13183a();
        }
    }

    /* renamed from: d */
    public void m13171d(C2640a c2640a) {
        if (c2640a instanceof C2641b) {
            this.f12362a.m13176b();
        } else {
            this.f12363b.m13184b();
        }
    }

    /* renamed from: e */
    public void m13172e(C2640a c2640a) {
        if (c2640a instanceof C2641b) {
            this.f12362a.m13177c();
        } else {
            this.f12363b.m13185c();
        }
    }

    /* renamed from: f */
    public void m13173f(C2640a c2640a) {
        if (c2640a instanceof C2641b) {
            this.f12362a.m13178d();
        } else {
            this.f12363b.m13186d();
        }
    }

    /* renamed from: a */
    public void m13167a() {
        this.f12362a.m13181g();
    }
}
