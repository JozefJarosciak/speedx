package com.beastbikes.android.ble.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

public class VerticalDashedLine extends View {
    /* renamed from: a */
    private Paint f8063a;
    /* renamed from: b */
    private float f8064b;

    public VerticalDashedLine(Context context) {
        super(context);
        m9374a(context);
    }

    public VerticalDashedLine(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m9374a(context);
    }

    public VerticalDashedLine(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m9374a(context);
    }

    /* renamed from: a */
    private void m9374a(Context context) {
        this.f8063a = new Paint();
        this.f8063a.setColor(Color.parseColor("#bdbdbd"));
        this.f8063a.setStyle(Style.STROKE);
        this.f8063a.setPathEffect(new DashPathEffect(new float[]{5.0f, 5.0f}, 1.0f));
        this.f8064b = (float) ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
        setLayerType(1, null);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(0.0f, 0.0f, 0.0f, this.f8064b, this.f8063a);
    }
}
