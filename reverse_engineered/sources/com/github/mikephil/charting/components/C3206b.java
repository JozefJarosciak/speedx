package com.github.mikephil.charting.components;

import android.graphics.Typeface;
import android.support.v4.view.ViewCompat;
import com.github.mikephil.charting.p183g.C3283i;

/* compiled from: ComponentBase */
/* renamed from: com.github.mikephil.charting.components.b */
public abstract class C3206b {
    /* renamed from: A */
    protected int f13898A = ViewCompat.MEASURED_STATE_MASK;
    /* renamed from: v */
    protected boolean f13899v = true;
    /* renamed from: w */
    protected float f13900w = 5.0f;
    /* renamed from: x */
    protected float f13901x = 5.0f;
    /* renamed from: y */
    protected Typeface f13902y = null;
    /* renamed from: z */
    protected float f13903z = C3283i.m15928a(10.0f);

    /* renamed from: v */
    public float m15341v() {
        return this.f13900w;
    }

    /* renamed from: w */
    public float m15342w() {
        return this.f13901x;
    }

    /* renamed from: j */
    public void m15339j(float f) {
        this.f13901x = C3283i.m15928a(f);
    }

    /* renamed from: x */
    public Typeface m15343x() {
        return this.f13902y;
    }

    /* renamed from: k */
    public void m15340k(float f) {
        float f2 = 24.0f;
        float f3 = 6.0f;
        if (f <= 24.0f) {
            f2 = f;
        }
        if (f2 >= 6.0f) {
            f3 = f2;
        }
        this.f13903z = C3283i.m15928a(f3);
    }

    /* renamed from: y */
    public float m15344y() {
        return this.f13903z;
    }

    /* renamed from: e */
    public void m15337e(int i) {
        this.f13898A = i;
    }

    /* renamed from: z */
    public int m15345z() {
        return this.f13898A;
    }

    /* renamed from: e */
    public void m15338e(boolean z) {
        this.f13899v = z;
    }

    /* renamed from: A */
    public boolean m15336A() {
        return this.f13899v;
    }
}
