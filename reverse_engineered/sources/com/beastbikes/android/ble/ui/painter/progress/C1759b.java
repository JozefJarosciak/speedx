package com.beastbikes.android.ble.ui.painter.progress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import com.beastbikes.android.ble.ui.p100c.C1748a;

/* compiled from: ProgressPowerPainterImp */
/* renamed from: com.beastbikes.android.ble.ui.painter.progress.b */
public class C1759b implements C1758a {
    /* renamed from: a */
    protected Paint f8008a;
    /* renamed from: b */
    private RectF f8009b;
    /* renamed from: c */
    private int f8010c;
    /* renamed from: d */
    private int f8011d = 90;
    /* renamed from: e */
    private int f8012e;
    /* renamed from: f */
    private int f8013f;
    /* renamed from: g */
    private float f8014g = 0.0f;
    /* renamed from: h */
    private float f8015h;
    /* renamed from: i */
    private int f8016i;
    /* renamed from: j */
    private int f8017j;
    /* renamed from: k */
    private int f8018k;
    /* renamed from: l */
    private int f8019l;
    /* renamed from: m */
    private Context f8020m;

    public C1759b(int i, float f, int i2, Context context, int i3) {
        this.f8010c = i;
        this.f8015h = f;
        this.f8017j = i2;
        this.f8020m = context;
        this.f8016i = i3;
        m9353a();
        m9354b();
    }

    /* renamed from: a */
    private void m9353a() {
        this.f8018k = C1748a.m9316a(2.0f, this.f8020m);
        this.f8019l = -C1748a.m9316a(4.0f, this.f8020m);
    }

    /* renamed from: b */
    private void m9354b() {
        m9355c();
    }

    /* renamed from: c */
    private void m9355c() {
        this.f8008a = new Paint();
        this.f8008a.setAntiAlias(true);
        this.f8008a.setAntiAlias(true);
        this.f8008a.setStrokeWidth((float) this.f8016i);
        this.f8008a.setColor(this.f8010c);
        this.f8008a.setStyle(Style.STROKE);
        this.f8008a.setPathEffect(new DashPathEffect(new float[]{(float) this.f8018k, (float) this.f8019l}, 0.0f));
    }

    /* renamed from: d */
    private void m9356d() {
        int i = (this.f8016i / 2) + this.f8017j;
        this.f8009b = new RectF();
        this.f8009b.set((float) i, (float) i, (float) (this.f8012e - i), (float) (this.f8013f - i));
    }

    /* renamed from: a */
    public void mo3232a(Canvas canvas) {
        canvas.drawArc(this.f8009b, (float) this.f8011d, this.f8014g, false, this.f8008a);
    }

    /* renamed from: a */
    public void mo3230a(int i) {
        this.f8010c = i;
        this.f8008a.setColor(i);
    }

    /* renamed from: a */
    public void mo3231a(int i, int i2) {
        this.f8012e = i2;
        this.f8013f = i;
        m9356d();
    }

    /* renamed from: a */
    public void mo3236a(float f) {
        this.f8014g = (360.0f * f) / this.f8015h;
    }
}
