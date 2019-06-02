package com.github.mikephil.charting.data;

import android.graphics.drawable.Drawable;

/* compiled from: BaseEntry */
/* renamed from: com.github.mikephil.charting.data.f */
public abstract class C3219f {
    /* renamed from: a */
    private float f13981a;
    /* renamed from: b */
    private Object f13982b;
    /* renamed from: c */
    private Drawable f13983c;

    public C3219f() {
        this.f13981a = 0.0f;
        this.f13982b = null;
        this.f13983c = null;
    }

    public C3219f(float f) {
        this.f13981a = 0.0f;
        this.f13982b = null;
        this.f13983c = null;
        this.f13981a = f;
    }

    public C3219f(float f, Object obj) {
        this(f);
        this.f13982b = obj;
    }

    /* renamed from: b */
    public float mo3912b() {
        return this.f13981a;
    }

    /* renamed from: g */
    public Drawable m15448g() {
        return this.f13983c;
    }

    /* renamed from: a */
    public void m15445a(float f) {
        this.f13981a = f;
    }

    /* renamed from: h */
    public Object m15449h() {
        return this.f13982b;
    }

    /* renamed from: a */
    public void m15446a(Object obj) {
        this.f13982b = obj;
    }
}
