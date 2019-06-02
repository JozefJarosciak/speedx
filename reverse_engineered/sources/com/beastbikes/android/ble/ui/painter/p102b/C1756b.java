package com.beastbikes.android.ble.ui.painter.p102b;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import com.beastbikes.android.ble.ui.p100c.C1748a;

/* compiled from: InternalPowerPainterImp */
/* renamed from: com.beastbikes.android.ble.ui.painter.b.b */
public class C1756b implements C1755a {
    /* renamed from: a */
    private Paint f7970a;
    /* renamed from: b */
    private RectF f7971b;
    /* renamed from: c */
    private Context f7972c;
    /* renamed from: d */
    private int f7973d;
    /* renamed from: e */
    private int f7974e;
    /* renamed from: f */
    private float f7975f = 0.0f;
    /* renamed from: g */
    private float f7976g = 360.0f;
    /* renamed from: h */
    private int f7977h;
    /* renamed from: i */
    private int f7978i;
    /* renamed from: j */
    private int f7979j;
    /* renamed from: k */
    private int f7980k;
    /* renamed from: l */
    private int f7981l;
    /* renamed from: m */
    private float f7982m = 100.0f;

    public C1756b(int i, int i2, Context context, int i3) {
        this.f7978i = i2;
        this.f7972c = context;
        this.f7981l = i;
        this.f7977h = i3;
        m9343a();
        m9344b();
    }

    /* renamed from: a */
    private void m9343a() {
        this.f7979j = C1748a.m9316a(2.0f, this.f7972c);
        this.f7980k = -C1748a.m9316a(4.0f, this.f7972c);
    }

    /* renamed from: b */
    private void m9344b() {
        this.f7970a = new Paint();
        this.f7970a.setAntiAlias(true);
        this.f7970a.setStrokeWidth((float) this.f7977h);
        this.f7970a.setColor(this.f7981l);
        this.f7970a.setStyle(Style.STROKE);
        this.f7970a.setPathEffect(new DashPathEffect(new float[]{(float) this.f7979j, (float) this.f7980k}, 0.0f));
    }

    /* renamed from: c */
    private void m9345c() {
        int i = (this.f7977h / 2) + this.f7978i;
        this.f7971b = new RectF();
        this.f7971b.set((float) i, (float) i, (float) (this.f7973d - i), (float) (this.f7974e - i));
    }

    /* renamed from: a */
    public void mo3232a(Canvas canvas) {
        canvas.drawArc(this.f7971b, this.f7975f, this.f7976g, false, this.f7970a);
    }

    /* renamed from: a */
    public void mo3230a(int i) {
        this.f7981l = i;
    }

    /* renamed from: a */
    public void mo3231a(int i, int i2) {
        this.f7973d = i2;
        this.f7974e = i;
        m9345c();
    }

    /* renamed from: a */
    public void mo3235a(float f) {
        float f2 = (360.0f * f) / this.f7982m;
        this.f7975f = 93.0f + f2;
        this.f7976g = 354.0f - f2;
    }
}
