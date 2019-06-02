package com.beastbikes.android.ble.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import com.beastbikes.android.R$styleable;

public class DividerView extends View {
    /* renamed from: a */
    public static int f8021a = 0;
    /* renamed from: b */
    public static int f8022b = 1;
    /* renamed from: c */
    private Paint f8023c;
    /* renamed from: d */
    private int f8024d;

    public DividerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.DividerView, 0, 0);
        int i = 5;
        try {
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(2, 5);
            i = obtainStyledAttributes.getDimensionPixelSize(1, 5);
            int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(3, 3);
            int color = obtainStyledAttributes.getColor(0, ViewCompat.MEASURED_STATE_MASK);
            this.f8024d = obtainStyledAttributes.getInt(4, f8022b);
            this.f8023c = new Paint();
            this.f8023c.setAntiAlias(true);
            this.f8023c.setColor(color);
            this.f8023c.setStyle(Style.STROKE);
            this.f8023c.setStrokeWidth((float) dimensionPixelSize2);
            this.f8023c.setPathEffect(new DashPathEffect(new float[]{(float) i, (float) dimensionPixelSize}, 0.0f));
        } finally {
            obtainStyledAttributes.recycle();
        }
    }

    public DividerView(Context context) {
        this(context, null);
    }

    protected void onDraw(Canvas canvas) {
        if (this.f8024d == f8021a) {
            float height = ((float) getHeight()) * 0.5f;
            canvas.drawLine(0.0f, height, (float) getWidth(), height, this.f8023c);
            return;
        }
        float width = 0.5f * ((float) getWidth());
        canvas.drawLine(width, 0.0f, width, (float) getHeight(), this.f8023c);
    }
}
