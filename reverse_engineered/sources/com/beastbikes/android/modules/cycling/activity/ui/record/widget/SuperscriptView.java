package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.R$styleable;
import com.google.common.primitives.Ints;

public class SuperscriptView extends View {
    /* renamed from: a */
    private Paint f9070a;
    /* renamed from: b */
    private float f9071b;

    public SuperscriptView(Context context) {
        super(context);
        m10390a(context, null, 0);
    }

    public SuperscriptView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        m10390a(context, attributeSet, 0);
    }

    public SuperscriptView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10390a(context, attributeSet, i);
    }

    /* renamed from: a */
    private void m10390a(Context context, @Nullable AttributeSet attributeSet, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.Superscript, i, 0);
        int color = obtainStyledAttributes.getColor(0, -1);
        this.f9071b = obtainStyledAttributes.getFloat(1, getResources().getDimension(C1373R.dimen.dimen_12));
        float f = obtainStyledAttributes.getFloat(2, getResources().getDimension(C1373R.dimen.margin_3dp));
        obtainStyledAttributes.recycle();
        this.f9070a = new Paint();
        this.f9070a.setColor(color);
        this.f9070a.setStrokeWidth(f);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0.0f, 0.0f, this.f9071b, 0.0f, this.f9070a);
        canvas.drawLine(0.0f, 0.0f, 0.0f, this.f9071b, this.f9070a);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(m10389a(i), m10389a(i2));
    }

    public void setColor(int i) {
        this.f9070a.setColor(i);
        invalidate();
    }

    /* renamed from: a */
    private int m10389a(int i) {
        return MeasureSpec.getMode(i) == Ints.MAX_POWER_OF_TWO ? MeasureSpec.getSize(i) : (int) this.f9071b;
    }
}
