package com.p014a.p015a.p016a.p017a;

import android.content.Context;
import android.view.MotionEvent;

/* compiled from: RotateGestureDetector */
/* renamed from: com.a.a.a.a.b */
public class C0708b extends C0707d {
    /* renamed from: l */
    private final C0705a f1707l;
    /* renamed from: m */
    private boolean f1708m;

    /* compiled from: RotateGestureDetector */
    /* renamed from: com.a.a.a.a.b$a */
    public interface C0705a {
        boolean onRotate(C0708b c0708b);

        boolean onRotateBegin(C0708b c0708b);

        void onRotateEnd(C0708b c0708b);
    }

    /* compiled from: RotateGestureDetector */
    /* renamed from: com.a.a.a.a.b$b */
    public static class C0706b implements C0705a {
        public boolean onRotate(C0708b c0708b) {
            return false;
        }

        public boolean onRotateBegin(C0708b c0708b) {
            return true;
        }

        public void onRotateEnd(C0708b c0708b) {
        }
    }

    public C0708b(Context context, C0705a c0705a) {
        super(context);
        this.f1707l = c0705a;
    }

    /* renamed from: a */
    protected void mo2311a(int i, MotionEvent motionEvent) {
        switch (i) {
            case 2:
                if (this.f1708m) {
                    this.f1708m = mo2315c(motionEvent);
                    if (!this.f1708m) {
                        this.b = this.f1707l.onRotateBegin(this);
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
                this.f1708m = mo2315c(motionEvent);
                if (!this.f1708m) {
                    this.b = this.f1707l.onRotateBegin(this);
                    return;
                }
                return;
            case 6:
                if (!this.f1708m) {
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
                if (this.e / this.f > 0.67f && this.f1707l.onRotate(this)) {
                    this.c.recycle();
                    this.c = MotionEvent.obtain(motionEvent);
                    return;
                }
                return;
            case 3:
                if (!this.f1708m) {
                    this.f1707l.onRotateEnd(this);
                }
                mo2314a();
                return;
            case 6:
                mo2313b(motionEvent);
                if (!this.f1708m) {
                    this.f1707l.onRotateEnd(this);
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
        this.f1708m = false;
    }

    /* renamed from: d */
    public float m2780d() {
        return (float) (((Math.atan2((double) this.i, (double) this.h) - Math.atan2((double) this.k, (double) this.j)) * 180.0d) / 3.141592653589793d);
    }
}
