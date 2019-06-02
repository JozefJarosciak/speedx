package com.github.mikephil.charting.data;

import android.graphics.Color;
import android.support.v4.view.ViewCompat;
import com.avos.avoscloud.AVException;
import com.github.mikephil.charting.p089e.p090b.C3229a;
import java.util.List;

/* compiled from: BarDataSet */
/* renamed from: com.github.mikephil.charting.data.b */
public class C3230b extends C3228d<BarEntry> implements C3229a {
    /* renamed from: m */
    private int f14037m;
    /* renamed from: n */
    private int f14038n;
    /* renamed from: o */
    private float f14039o;
    /* renamed from: p */
    private int f14040p;
    /* renamed from: q */
    private int f14041q;
    /* renamed from: r */
    private int f14042r;
    /* renamed from: x */
    private String[] f14043x;

    public C3230b(List<BarEntry> list, String str) {
        super(list, str);
        this.f14037m = 1;
        this.f14038n = Color.rgb(AVException.USER_MOBILEPHONE_NOT_VERIFIED, AVException.USER_MOBILEPHONE_NOT_VERIFIED, AVException.USER_MOBILEPHONE_NOT_VERIFIED);
        this.f14039o = 0.0f;
        this.f14040p = ViewCompat.MEASURED_STATE_MASK;
        this.f14041q = 120;
        this.f14042r = 0;
        this.f14043x = new String[]{"Stack"};
        this.a = Color.rgb(0, 0, 0);
        m15612d(list);
        m15611c(list);
    }

    /* renamed from: c */
    private void m15611c(List<BarEntry> list) {
        this.f14042r = 0;
        for (int i = 0; i < list.size(); i++) {
            float[] a = ((BarEntry) list.get(i)).m15451a();
            if (a == null) {
                this.f14042r++;
            } else {
                this.f14042r = a.length + this.f14042r;
            }
        }
    }

    /* renamed from: d */
    private void m15612d(List<BarEntry> list) {
        for (int i = 0; i < list.size(); i++) {
            float[] a = ((BarEntry) list.get(i)).m15451a();
            if (a != null && a.length > this.f14037m) {
                this.f14037m = a.length;
            }
        }
    }

    /* renamed from: a */
    protected void m15614a(BarEntry barEntry) {
        if (barEntry != null && !Float.isNaN(barEntry.mo3912b())) {
            if (barEntry.m15451a() == null) {
                if (barEntry.mo3912b() < this.u) {
                    this.u = barEntry.mo3912b();
                }
                if (barEntry.mo3912b() > this.t) {
                    this.t = barEntry.mo3912b();
                }
            } else {
                if ((-barEntry.m15456f()) < this.u) {
                    this.u = -barEntry.m15456f();
                }
                if (barEntry.m15455e() > this.t) {
                    this.t = barEntry.m15455e();
                }
            }
            m15543c(barEntry);
        }
    }

    /* renamed from: a */
    public int mo3962a() {
        return this.f14037m;
    }

    /* renamed from: b */
    public boolean mo3963b() {
        return this.f14037m > 1;
    }

    /* renamed from: c */
    public int mo3964c() {
        return this.f14038n;
    }

    /* renamed from: d */
    public float mo3965d() {
        return this.f14039o;
    }

    /* renamed from: e */
    public int mo3966e() {
        return this.f14040p;
    }

    /* renamed from: f */
    public int mo3967f() {
        return this.f14041q;
    }

    /* renamed from: g */
    public String[] mo3968g() {
        return this.f14043x;
    }
}
