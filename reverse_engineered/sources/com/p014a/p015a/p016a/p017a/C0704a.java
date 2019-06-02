package com.p014a.p015a.p016a.p017a;

import android.content.Context;
import android.view.MotionEvent;

/* compiled from: BaseGestureDetector */
/* renamed from: com.a.a.a.a.a */
public abstract class C0704a {
    /* renamed from: a */
    protected final Context f1692a;
    /* renamed from: b */
    protected boolean f1693b;
    /* renamed from: c */
    protected MotionEvent f1694c;
    /* renamed from: d */
    protected MotionEvent f1695d;
    /* renamed from: e */
    protected float f1696e;
    /* renamed from: f */
    protected float f1697f;
    /* renamed from: g */
    protected long f1698g;

    /* renamed from: a */
    protected abstract void mo2311a(int i, MotionEvent motionEvent);

    /* renamed from: b */
    protected abstract void mo2312b(int i, MotionEvent motionEvent);

    public C0704a(Context context) {
        this.f1692a = context;
    }

    /* renamed from: a */
    public boolean m2763a(MotionEvent motionEvent) {
        int action = motionEvent.getAction() & 255;
        if (this.f1693b) {
            mo2312b(action, motionEvent);
        } else {
            mo2311a(action, motionEvent);
        }
        return true;
    }

    /* renamed from: b */
    protected void mo2313b(MotionEvent motionEvent) {
        MotionEvent motionEvent2 = this.f1694c;
        if (this.f1695d != null) {
            this.f1695d.recycle();
            this.f1695d = null;
        }
        this.f1695d = MotionEvent.obtain(motionEvent);
        this.f1698g = motionEvent.getEventTime() - motionEvent2.getEventTime();
        this.f1696e = motionEvent.getPressure(motionEvent.getActionIndex());
        this.f1697f = motionEvent2.getPressure(motionEvent2.getActionIndex());
    }

    /* renamed from: a */
    protected void mo2314a() {
        if (this.f1694c != null) {
            this.f1694c.recycle();
            this.f1694c = null;
        }
        if (this.f1695d != null) {
            this.f1695d.recycle();
            this.f1695d = null;
        }
        this.f1693b = false;
    }

    /* renamed from: b */
    public boolean m2766b() {
        return this.f1693b;
    }

    /* renamed from: c */
    public long m2767c() {
        return this.f1695d.getEventTime();
    }
}
