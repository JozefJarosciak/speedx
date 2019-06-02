package com.github.mikephil.charting.components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import com.github.mikephil.charting.charts.C1475c;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.p181d.C3213d;
import com.github.mikephil.charting.p183g.C3274b;
import com.github.mikephil.charting.p183g.C3279e;
import java.lang.ref.WeakReference;

/* compiled from: MarkerImage */
/* renamed from: com.github.mikephil.charting.components.f */
public class C1984f implements C1981d {
    /* renamed from: a */
    private Context f8920a;
    /* renamed from: b */
    private Drawable f8921b;
    /* renamed from: c */
    private C3279e f8922c = new C3279e();
    /* renamed from: d */
    private C3279e f8923d = new C3279e();
    /* renamed from: e */
    private WeakReference<C1475c> f8924e;
    /* renamed from: f */
    private C3274b f8925f = new C3274b();
    /* renamed from: g */
    private Rect f8926g = new Rect();

    public C1984f(Context context, int i) {
        this.f8920a = context;
        if (VERSION.SDK_INT >= 21) {
            this.f8921b = this.f8920a.getResources().getDrawable(i, null);
        } else {
            this.f8921b = this.f8920a.getResources().getDrawable(i);
        }
    }

    /* renamed from: a */
    public C3279e mo3338a() {
        return this.f8922c;
    }

    /* renamed from: b */
    public C1475c m10219b() {
        return this.f8924e == null ? null : (C1475c) this.f8924e.get();
    }

    /* renamed from: a */
    public C3279e m10216a(float f, float f2) {
        C3279e a = mo3338a();
        this.f8923d.f14200a = a.f14200a;
        this.f8923d.f14201b = a.f14201b;
        C1475c b = m10219b();
        float f3 = this.f8925f.f14176a;
        float f4 = this.f8925f.f14177b;
        if (f3 == 0.0f && this.f8921b != null) {
            f3 = (float) this.f8921b.getIntrinsicWidth();
        }
        if (f4 == 0.0f && this.f8921b != null) {
            f4 = (float) this.f8921b.getIntrinsicHeight();
        }
        if (this.f8923d.f14200a + f < 0.0f) {
            this.f8923d.f14200a = -f;
        } else if (b != null && (f + f3) + this.f8923d.f14200a > ((float) b.getWidth())) {
            this.f8923d.f14200a = (((float) b.getWidth()) - f) - f3;
        }
        if (this.f8923d.f14201b + f2 < 0.0f) {
            this.f8923d.f14201b = -f2;
        } else if (b != null && (f2 + f4) + this.f8923d.f14201b > ((float) b.getHeight())) {
            this.f8923d.f14201b = (((float) b.getHeight()) - f2) - f4;
        }
        return this.f8923d;
    }

    /* renamed from: a */
    public void mo3336a(Entry entry, C3213d c3213d) {
    }

    /* renamed from: a */
    public void mo3335a(Canvas canvas, float f, float f2) {
        if (this.f8921b != null) {
            C3279e a = m10216a(f, f2);
            float f3 = this.f8925f.f14176a;
            float f4 = this.f8925f.f14177b;
            if (f3 == 0.0f && this.f8921b != null) {
                f3 = (float) this.f8921b.getIntrinsicWidth();
            }
            if (f4 == 0.0f && this.f8921b != null) {
                f4 = (float) this.f8921b.getIntrinsicHeight();
            }
            this.f8921b.copyBounds(this.f8926g);
            this.f8921b.setBounds(this.f8926g.left, this.f8926g.top, ((int) f3) + this.f8926g.left, ((int) f4) + this.f8926g.top);
            int save = canvas.save();
            canvas.translate(a.f14200a + f, a.f14201b + f2);
            this.f8921b.draw(canvas);
            canvas.restoreToCount(save);
            this.f8921b.setBounds(this.f8926g);
        }
    }
}
