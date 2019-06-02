package com.p014a.p015a.p016a.p017a;

import android.content.Context;
import android.view.MotionEvent;

/* compiled from: ShoveGestureDetector */
/* renamed from: com.a.a.a.a.c */
public class C0710c extends C0707d {
    /* renamed from: l */
    private float f1709l;
    /* renamed from: m */
    private float f1710m;
    /* renamed from: n */
    private final C0709a f1711n;
    /* renamed from: o */
    private boolean f1712o;

    /* compiled from: ShoveGestureDetector */
    /* renamed from: com.a.a.a.a.c$a */
    public interface C0709a {
        boolean onShove(C0710c c0710c);

        boolean onShoveBegin(C0710c c0710c);

        void onShoveEnd(C0710c c0710c);
    }

    public C0710c(Context context, C0709a c0709a) {
        super(context);
        this.f1711n = c0709a;
    }

    /* renamed from: a */
    protected void mo2311a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                if (this.f1712o) {
                    this.f1712o = mo2315c(motionEvent);
                    if (!this.f1712o) {
                        this.b = this.f1711n.onShoveBegin(this);
                        return;
                    }
                    return;
                }
                return;
            case 5:
                mo2314a();
                this.c = MotionEvent.obtain(motionEvent);
                this.g = 0;
                mo2313b(motionEvent);
                this.f1712o = mo2315c(motionEvent);
                if (!this.f1712o) {
                    this.b = this.f1711n.onShoveBegin(this);
                    return;
                }
                return;
            case 6:
                if (!this.f1712o) {
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* renamed from: b */
    protected void mo2312b(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                mo2313b(motionEvent);
                if (this.e / this.f > 0.67f && Math.abs(m2786d()) > 0.5f && this.f1711n.onShove(this)) {
                    this.c.recycle();
                    this.c = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            case 3:
                if (!this.f1712o) {
                    this.f1711n.onShoveEnd(this);
                }
                mo2314a();
                return;
            case 6:
                mo2313b(motionEvent);
                if (!this.f1712o) {
                    this.f1711n.onShoveEnd(this);
                }
                mo2314a();
                return;
            default:
                return;
        }
    }

    /* renamed from: a */
    protected void mo2314a() {
        super.mo2314a();
        this.f1712o = false;
        this.f1709l = 0.0f;
        this.f1710m = 0.0f;
    }

    /* renamed from: b */
    protected void mo2313b(MotionEvent motionEvent) {
        super.mo2313b(motionEvent);
        MotionEvent motionEvent2 = this.c;
        this.f1709l = (motionEvent2.getY(1) + motionEvent2.getY(0)) / 2.0f;
        this.f1710m = (motionEvent.getY(0) + motionEvent.getY(1)) / 2.0f;
    }

    /* renamed from: c */
    protected boolean mo2315c(MotionEvent motionEvent) {
        if (super.mo2315c(motionEvent)) {
            return true;
        }
        double abs = Math.abs(Math.atan2((double) this.k, (double) this.j));
        if ((0.0d >= abs || abs >= 0.3499999940395355d) && (2.7899999618530273d >= abs || abs >= 3.141592653589793d)) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    public float m2786d() {
        return this.f1710m - this.f1709l;
    }
}
