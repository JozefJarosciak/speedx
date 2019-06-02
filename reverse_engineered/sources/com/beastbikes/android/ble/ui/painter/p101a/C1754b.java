package com.beastbikes.android.ble.ui.painter.p101a;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import com.beastbikes.android.ble.ui.p100c.C1748a;

/* compiled from: DigitalImp */
/* renamed from: com.beastbikes.android.ble.ui.painter.a.b */
public class C1754b implements C1753a {
    /* renamed from: a */
    protected Paint f7954a;
    /* renamed from: b */
    protected Paint f7955b;
    /* renamed from: c */
    protected Paint f7956c;
    /* renamed from: d */
    private float f7957d;
    /* renamed from: e */
    private Context f7958e;
    /* renamed from: f */
    private float f7959f;
    /* renamed from: g */
    private float f7960g;
    /* renamed from: h */
    private int f7961h;
    /* renamed from: i */
    private int f7962i;
    /* renamed from: j */
    private float f7963j;
    /* renamed from: k */
    private float f7964k;
    /* renamed from: l */
    private float f7965l;
    /* renamed from: m */
    private String f7966m;
    /* renamed from: n */
    private String f7967n;
    /* renamed from: o */
    private String f7968o;
    /* renamed from: p */
    private int f7969p = Color.parseColor("#bbbbbb");

    public C1754b(int i, int i2, Context context, int i3, int i4, String str, int i5) {
        this.f7958e = context;
        this.f7962i = i;
        this.f7969p = i2;
        this.f7961h = i3;
        this.f7959f = (float) i4;
        this.f7960g = (float) i5;
        this.f7966m = str;
        m9334a();
        m9335b();
    }

    /* renamed from: a */
    private void m9334a() {
        this.f7954a = new Paint();
        this.f7954a.setAntiAlias(true);
        this.f7954a.setTextSize(this.f7959f);
        this.f7954a.setColor(this.f7962i);
        Typeface createFromAsset = Typeface.createFromAsset(this.f7958e.getAssets(), "fonts/BebasNeue.otf");
        if (createFromAsset == null) {
            createFromAsset = Typeface.DEFAULT;
        }
        this.f7954a.setTypeface(createFromAsset);
        this.f7954a.setTextAlign(Align.CENTER);
        this.f7955b = new TextPaint();
        this.f7955b.setAntiAlias(true);
        this.f7955b.setTextSize(this.f7960g);
        this.f7955b.setColor(this.f7969p);
        this.f7955b.setTypeface(createFromAsset);
        this.f7955b.setTextAlign(Align.CENTER);
        this.f7956c = new TextPaint();
        this.f7956c.setAntiAlias(true);
        this.f7956c.setTextSize((float) C1748a.m9316a(12.0f, this.f7958e));
        this.f7956c.setColor(this.f7969p);
        this.f7956c.setTypeface(Typeface.DEFAULT);
        this.f7956c.setTextAlign(Align.CENTER);
    }

    /* renamed from: b */
    private void m9335b() {
        this.f7965l = (float) C1748a.m9316a(10.0f, this.f7958e);
    }

    /* renamed from: a */
    public void mo3229a(float f) {
        this.f7957d = f;
    }

    /* renamed from: a */
    public void mo3232a(Canvas canvas) {
        if (!TextUtils.isEmpty(this.f7967n)) {
            canvas.drawText(this.f7967n, this.f7963j, (this.f7964k - ((float) this.f7961h)) - 20.0f, this.f7956c);
        }
        String format = String.format("%.0f", new Object[]{Float.valueOf(this.f7957d)});
        canvas.drawText(format, this.f7963j, (this.f7964k + ((float) (this.f7961h / 2))) + 10.0f, this.f7954a);
        float measureText = ((this.f7954a.measureText(format) * 9.0f) / 10.0f) + this.f7963j;
        if (this.f7957d < 10.0f) {
            measureText += measureText / 10.0f;
        } else if (this.f7957d > 99.0f) {
            measureText -= measureText / 10.0f;
        }
        canvas.drawText(this.f7966m, measureText, (this.f7964k + ((float) (this.f7961h / 2))) + 5.0f, this.f7955b);
        if (!TextUtils.isEmpty(this.f7968o)) {
            canvas.drawText(this.f7968o, this.f7963j, (this.f7964k + ((float) this.f7961h)) + 50.0f, this.f7955b);
        }
    }

    /* renamed from: a */
    public void mo3230a(int i) {
        this.f7962i = i;
    }

    /* renamed from: a */
    public void mo3231a(int i, int i2) {
        this.f7963j = (float) (i2 / 2);
        this.f7964k = (float) (i / 2);
    }

    /* renamed from: a */
    public void mo3233a(String str) {
        this.f7968o = str;
    }

    /* renamed from: b */
    public void mo3234b(String str) {
        this.f7967n = str;
    }
}
