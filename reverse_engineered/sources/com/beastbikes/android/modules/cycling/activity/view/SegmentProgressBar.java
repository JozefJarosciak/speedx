package com.beastbikes.android.modules.cycling.activity.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.beastbikes.framework.android.p088g.C2801d;

public class SegmentProgressBar extends View {
    /* renamed from: a */
    private int f9315a;
    /* renamed from: b */
    private int f9316b;
    /* renamed from: c */
    private int f9317c;
    /* renamed from: d */
    private int f9318d;
    /* renamed from: e */
    private int f9319e;
    /* renamed from: f */
    private int f9320f;
    /* renamed from: g */
    private int f9321g;
    /* renamed from: h */
    private int f9322h;
    /* renamed from: i */
    private int f9323i;
    /* renamed from: j */
    private int f9324j;
    /* renamed from: k */
    private Paint f9325k;

    public SegmentProgressBar(Context context) {
        this(context, null);
    }

    public SegmentProgressBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public SegmentProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f9315a = Color.parseColor("#222222");
        this.f9316b = Color.parseColor("#999999");
        this.f9317c = Color.parseColor("#ff002a");
        this.f9318d = Color.parseColor("#ffffff");
        this.f9319e = 2;
        this.f9320f = 0;
        this.f9324j = 50;
        m10525b();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f9325k.setColor(this.f9315a);
        this.f9325k.setStrokeWidth(20.0f);
        canvas.drawRect(0.0f, 0.0f, (float) this.f9323i, (float) this.f9324j, this.f9325k);
        for (int i = 1; i <= this.f9321g; i++) {
            if (i < this.f9321g) {
                this.f9325k.setColor(this.f9316b);
                this.f9325k.setStrokeWidth((float) this.f9319e);
                int i2 = (this.f9320f * i) + (this.f9319e * (i - 1));
                canvas.drawLine((float) i2, 0.0f, (float) i2, (float) this.f9324j, this.f9325k);
            }
            if (i <= this.f9322h + 1) {
                this.f9325k.setStrokeWidth(20.0f);
                if (i == this.f9322h + 1) {
                    this.f9325k.setColor(this.f9318d);
                } else {
                    this.f9325k.setColor(this.f9317c);
                }
                canvas.drawRect((float) ((this.f9320f * (i - 1)) + (this.f9319e * (i - 1))), 0.0f, (float) ((this.f9320f * i) + (this.f9319e * (i - 1))), (float) this.f9324j, this.f9325k);
            }
        }
    }

    /* renamed from: b */
    private void m10525b() {
        this.f9325k = new Paint();
        this.f9323i = C2801d.m13755a(getContext());
        this.f9324j = C2801d.m13756a(getContext(), 10.0f);
    }

    public void setProgressNum(int i) {
        this.f9321g = i;
        this.f9320f = (this.f9323i - ((i - 1) * this.f9319e)) / i;
        invalidate();
    }

    /* renamed from: a */
    public void m10526a() {
        this.f9322h++;
        invalidate();
    }

    public void setProgress(int i) {
        if (this.f9322h != i) {
            this.f9322h = i;
            invalidate();
        }
    }
}
