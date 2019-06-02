package com.beastbikes.android.widget.calendar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;

public class WeekBarView extends View {
    /* renamed from: a */
    private int f12278a;
    /* renamed from: b */
    private int f12279b;
    /* renamed from: c */
    private String[] f12280c;
    /* renamed from: d */
    private Paint f12281d;

    public WeekBarView(Context context) {
        this(context, null);
    }

    public WeekBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public WeekBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f12278a = Color.parseColor("#ffffff");
        this.f12279b = 13;
        m13086a(context, attributeSet);
        m13085a();
    }

    protected void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int mode = MeasureSpec.getMode(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode2 = MeasureSpec.getMode(i2);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        if (mode2 == Integer.MIN_VALUE) {
            size2 = displayMetrics.densityDpi * 30;
        }
        if (mode == Integer.MIN_VALUE) {
            size = displayMetrics.densityDpi * 300;
        }
        setMeasuredDimension(size, size2);
    }

    protected void onDraw(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        int i = width / 7;
        for (width = 0; width < this.f12280c.length; width++) {
            String str = this.f12280c[width];
            canvas.drawText(str, (float) (((i - ((int) this.f12281d.measureText(str))) / 2) + (i * width)), (float) ((int) (((float) (height / 2)) - ((this.f12281d.ascent() + this.f12281d.descent()) / 2.0f))), this.f12281d);
        }
    }

    /* renamed from: a */
    private void m13086a(Context context, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.WeekBarView);
        this.f12278a = obtainStyledAttributes.getColor(0, this.f12278a);
        this.f12279b = obtainStyledAttributes.getInteger(1, this.f12279b);
        obtainStyledAttributes.recycle();
        this.f12280c = context.getResources().getStringArray(C1373R.array.calendar_week);
    }

    /* renamed from: a */
    private void m13085a() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        this.f12281d = new Paint();
        this.f12281d.setColor(this.f12278a);
        this.f12281d.setAntiAlias(true);
        this.f12281d.setTextSize(displayMetrics.scaledDensity * ((float) this.f12279b));
    }
}
