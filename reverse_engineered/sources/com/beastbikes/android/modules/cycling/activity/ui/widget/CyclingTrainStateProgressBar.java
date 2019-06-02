package com.beastbikes.android.modules.cycling.activity.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.View;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class CyclingTrainStateProgressBar extends View {
    /* renamed from: a */
    private Paint f9260a;
    /* renamed from: b */
    private int f9261b;
    /* renamed from: c */
    private float f9262c = 100.0f;
    /* renamed from: d */
    private int f9263d;
    /* renamed from: e */
    private int f9264e;
    /* renamed from: f */
    private int f9265f = 0;
    /* renamed from: g */
    private int f9266g = 0;
    /* renamed from: h */
    private Path f9267h = new Path();
    /* renamed from: i */
    private DashPathEffect f9268i = new DashPathEffect(new float[]{5.0f, 5.0f}, 0.0f);
    /* renamed from: j */
    private int f9269j;

    public CyclingTrainStateProgressBar(Context context) {
        super(context);
        m10496a(context, null, 0);
    }

    public CyclingTrainStateProgressBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m10496a(context, attributeSet, 0);
    }

    public CyclingTrainStateProgressBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10496a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m10496a(Context context, AttributeSet attributeSet, int i) {
        this.f9260a = new Paint();
        this.f9260a.setAntiAlias(true);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.CyclingTrainStateProgressBar, i, 0);
        this.f9269j = obtainStyledAttributes.getColor(0, SupportMenu.CATEGORY_MASK);
        obtainStyledAttributes.recycle();
        this.f9261b = 0;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.f9263d = getMeasuredWidth() - 1;
        this.f9264e = getMeasuredHeight() - 1;
    }

    protected void onDraw(Canvas canvas) {
        this.f9260a.reset();
        this.f9260a.setColor(this.f9269j);
        canvas.drawRect(0.0f, ((float) this.f9264e) - ((((float) this.f9261b) / this.f9262c) * ((float) this.f9264e)), (float) this.f9263d, (float) this.f9264e, this.f9260a);
        this.f9260a.setColor(-1);
        this.f9260a.setTextSize((float) getContext().getResources().getDimensionPixelSize(C1373R.dimen.font_20));
        if (this.f9265f > 0) {
            int[] a = m10497a(this.f9265f + "");
            canvas.drawText(this.f9265f + "", (float) ((this.f9263d - a[0]) / 2), (float) ((this.f9264e / 3) - a[1]), this.f9260a);
            a = m10497a(this.f9266g + "");
            canvas.drawText(this.f9266g + "", (float) ((this.f9263d - a[0]) / 2), (float) (((this.f9264e / 3) * 2) - a[1]), this.f9260a);
        }
        this.f9260a.setStyle(Style.STROKE);
        this.f9260a.setPathEffect(this.f9268i);
        this.f9260a.setStrokeWidth(1.0f);
        this.f9267h.moveTo(0.0f, (float) (this.f9264e / 3));
        this.f9267h.lineTo((float) this.f9263d, (float) (this.f9264e / 3));
        canvas.drawPath(this.f9267h, this.f9260a);
        this.f9267h.moveTo(0.0f, (float) ((this.f9264e / 3) * 2));
        this.f9267h.lineTo((float) this.f9263d, (float) ((this.f9264e / 3) * 2));
        canvas.drawPath(this.f9267h, this.f9260a);
    }

    public void setProgressColor(int i) {
        this.f9269j = i;
        postInvalidate();
    }

    /* renamed from: a */
    private int[] m10497a(String str) {
        this.f9260a.getTextBounds(str, 0, str.length(), new Rect());
        return new int[]{r0.width(), r0.height()};
    }

    public void setProgress(int i) {
        if (i <= 0) {
            this.f9261b = 0;
            postInvalidate();
            return;
        }
        int i2;
        if (i < this.f9266g) {
            i2 = (int) (((this.f9262c / 3.0f) / ((float) this.f9266g)) * ((float) i));
        } else if (i > this.f9265f) {
            i2 = ((i - this.f9265f) * 2) / 3;
        } else {
            i2 = (int) ((((float) (((this.f9265f + i) - (this.f9266g * 2)) / (this.f9265f - this.f9266g))) * this.f9262c) / 3.0f);
        }
        this.f9261b = i2;
        postInvalidate();
    }

    /* renamed from: a */
    public void m10498a(int i, int i2) {
        this.f9265f = i;
        this.f9266g = i2;
        this.f9262c = (float) (this.f9265f * 2);
        postInvalidate();
    }
}
