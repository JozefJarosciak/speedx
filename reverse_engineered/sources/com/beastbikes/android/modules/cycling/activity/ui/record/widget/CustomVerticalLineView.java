package com.beastbikes.android.modules.cycling.activity.ui.record.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

public class CustomVerticalLineView extends View {
    /* renamed from: a */
    private float f9007a;
    /* renamed from: b */
    private float f9008b;
    /* renamed from: c */
    private float f9009c;
    /* renamed from: d */
    private float f9010d;
    /* renamed from: e */
    private Paint f9011e;

    public CustomVerticalLineView(Context context) {
        super(context);
        m10325a(context);
    }

    public CustomVerticalLineView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m10325a(context);
    }

    public CustomVerticalLineView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m10325a(context);
    }

    /* renamed from: a */
    private void m10325a(Context context) {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        float width = (float) windowManager.getDefaultDisplay().getWidth();
        float height = (float) windowManager.getDefaultDisplay().getHeight();
        this.f9007a = 0.0f;
        this.f9009c = 0.0f;
        this.f9008b = 0.0f;
        this.f9010d = height;
        this.f9011e = new Paint();
        this.f9011e.setColor(-1644826);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(this.f9007a, this.f9008b, this.f9009c, this.f9010d, this.f9011e);
    }

    /* renamed from: a */
    public void m10326a(float f) {
        this.f9007a = f;
        this.f9009c = f;
        invalidate();
    }
}
