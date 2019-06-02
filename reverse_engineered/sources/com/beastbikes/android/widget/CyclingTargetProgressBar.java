package com.beastbikes.android.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import com.avos.avoscloud.AVException;
import com.beastbikes.framework.android.p088g.C2801d;

public class CyclingTargetProgressBar extends View {
    /* renamed from: a */
    private int f12070a;
    /* renamed from: b */
    private int f12071b;
    /* renamed from: c */
    private int f12072c;
    /* renamed from: d */
    private int f12073d;
    /* renamed from: e */
    private int f12074e;
    /* renamed from: f */
    private int f12075f;
    /* renamed from: g */
    private float f12076g;
    /* renamed from: h */
    private float f12077h;
    /* renamed from: i */
    private float f12078i;
    /* renamed from: j */
    private Paint f12079j;

    public CyclingTargetProgressBar(Context context) {
        this(context, null);
    }

    public CyclingTargetProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CyclingTargetProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f12070a = Color.rgb(AVException.FILE_DOWNLOAD_INCONSISTENT_FAILURE, 160, 49);
        this.f12071b = Color.rgb(255, 95, 12);
        this.f12072c = Color.parseColor("#ff2d04");
        this.f12073d = Color.parseColor("#ff002a");
        this.f12074e = 100;
        m12920a();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f12079j.setColor(Color.parseColor("#303030"));
        this.f12079j.setStrokeWidth(this.f12077h);
        float f = this.f12077h / 2.0f;
        canvas.drawRect(f, 0.0f, this.f12076g - f, this.f12077h, this.f12079j);
        this.f12079j.setStrokeWidth(2.0f);
        if (this.f12075f > 0) {
            this.f12079j.setColor(this.f12070a);
        } else {
            this.f12079j.setColor(Color.parseColor("#303030"));
        }
        canvas.drawCircle(f, f, f, this.f12079j);
        if (this.f12075f >= 100) {
            this.f12079j.setColor(this.f12073d);
        } else {
            this.f12079j.setColor(Color.parseColor("#303030"));
        }
        canvas.drawCircle(this.f12076g - f, f, f, this.f12079j);
        this.f12079j.setColor(this.f12070a);
        this.f12079j.setStrokeWidth(this.f12077h);
        float f2 = ((float) this.f12075f) * this.f12078i;
        if (f2 > this.f12076g - f) {
            f2 = this.f12076g - f;
        }
        if (this.f12075f > 0) {
            canvas.drawRect(f, 0.0f, f2, this.f12077h, this.f12079j);
        }
        if (this.f12075f > 25) {
            this.f12079j.setColor(this.f12071b);
            canvas.drawRect(this.f12078i * 25.0f, 0.0f, f2, this.f12077h, this.f12079j);
        }
        if (this.f12075f > 50) {
            this.f12079j.setColor(this.f12072c);
            canvas.drawRect(this.f12078i * 50.0f, 0.0f, f2, this.f12077h, this.f12079j);
        }
        if (this.f12075f > 75) {
            this.f12079j.setColor(this.f12073d);
            canvas.drawRect(this.f12078i * 75.0f, 0.0f, f2 - f, this.f12077h, this.f12079j);
        }
    }

    /* renamed from: a */
    private void m12920a() {
        this.f12076g = (float) (C2801d.m13755a(getContext()) - C2801d.m13756a(getContext(), 40.0f));
        this.f12077h = (float) C2801d.m13756a(getContext(), 6.0f);
        this.f12078i = this.f12076g / ((float) this.f12074e);
        this.f12079j = new Paint();
        this.f12079j.setStrokeWidth(this.f12077h);
        this.f12079j.setAntiAlias(true);
        this.f12079j.setStyle(Style.FILL);
        this.f12079j.setStrokeCap(Cap.ROUND);
        this.f12079j.setColor(Color.parseColor("#303030"));
    }

    public void setProgress(int i) {
        if (i > 100) {
            this.f12075f = 100;
        }
        this.f12075f = i;
        invalidate();
    }

    public int getProgress() {
        return this.f12075f;
    }
}
