package com.tencent.p191a.p192a.p193a.p194a;

import android.content.Context;

/* renamed from: com.tencent.a.a.a.a.f */
public abstract class C4394f {
    /* renamed from: a */
    protected Context f15185a = null;

    protected C4394f(Context context) {
        this.f15185a = context;
    }

    /* renamed from: a */
    public final void m17216a(C4396c c4396c) {
        if (c4396c != null) {
            String c4396c2 = c4396c.toString();
            if (mo6051a()) {
                mo6050a(C4400h.m17245g(c4396c2));
            }
        }
    }

    /* renamed from: a */
    protected abstract void mo6050a(String str);

    /* renamed from: a */
    protected abstract boolean mo6051a();

    /* renamed from: b */
    protected abstract String mo6052b();

    /* renamed from: o */
    public final C4396c m17220o() {
        String f = mo6051a() ? C4400h.m17244f(mo6052b()) : null;
        return f != null ? C4396c.m17224e(f) : null;
    }
}
