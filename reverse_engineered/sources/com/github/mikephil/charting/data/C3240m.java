package com.github.mikephil.charting.data;

import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import com.avos.avoscloud.AVException;
import com.github.mikephil.charting.p089e.p090b.C3239g;
import com.github.mikephil.charting.p183g.C3283i;
import java.util.List;

/* compiled from: LineRadarDataSet */
/* renamed from: com.github.mikephil.charting.data.m */
public abstract class C3240m<T extends Entry> extends C3234n<T> implements C3239g<T> {
    /* renamed from: m */
    protected Drawable f14063m;
    /* renamed from: n */
    private int f14064n = Color.rgb(AVException.EXCEEDED_QUOTA, 234, 255);
    /* renamed from: o */
    private int f14065o = 85;
    /* renamed from: p */
    private float f14066p = 2.5f;
    /* renamed from: q */
    private boolean f14067q = false;

    public C3240m(List<T> list, String str) {
        super(list, str);
    }

    /* renamed from: O */
    public int mo3988O() {
        return this.f14064n;
    }

    /* renamed from: j */
    public void m15687j(int i) {
        this.f14064n = i;
        this.f14063m = null;
    }

    /* renamed from: P */
    public Drawable mo3989P() {
        return this.f14063m;
    }

    @TargetApi(18)
    /* renamed from: a */
    public void m15684a(Drawable drawable) {
        this.f14063m = drawable;
    }

    /* renamed from: Q */
    public int mo3990Q() {
        return this.f14065o;
    }

    /* renamed from: k */
    public void m15688k(int i) {
        this.f14065o = i;
    }

    /* renamed from: e */
    public void m15685e(float f) {
        float f2 = 10.0f;
        float f3 = 0.0f;
        if (f >= 0.0f) {
            f3 = f;
        }
        if (f3 <= 10.0f) {
            f2 = f3;
        }
        this.f14066p = C3283i.m15928a(f2);
    }

    /* renamed from: R */
    public float mo3991R() {
        return this.f14066p;
    }

    /* renamed from: e */
    public void m15686e(boolean z) {
        this.f14067q = z;
    }

    /* renamed from: S */
    public boolean mo3992S() {
        return this.f14067q;
    }
}
