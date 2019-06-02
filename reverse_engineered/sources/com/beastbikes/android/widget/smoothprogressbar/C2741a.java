package com.beastbikes.android.widget.smoothprogressbar;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.shapes.Shape;

/* compiled from: ColorsShape */
/* renamed from: com.beastbikes.android.widget.smoothprogressbar.a */
public class C2741a extends Shape {
    /* renamed from: a */
    private float f12823a;
    /* renamed from: b */
    private int[] f12824b;

    public C2741a(float f, int[] iArr) {
        this.f12823a = f;
        this.f12824b = iArr;
    }

    public void draw(Canvas canvas, Paint paint) {
        int i = 0;
        float length = 1.0f / ((float) this.f12824b.length);
        paint.setStrokeWidth(this.f12823a);
        int[] iArr = this.f12824b;
        int length2 = iArr.length;
        int i2 = 0;
        while (i2 < length2) {
            paint.setColor(iArr[i2]);
            int i3 = i + 1;
            canvas.drawLine((((float) i) * length) * getWidth(), getHeight() / 2.0f, getWidth() * (((float) i3) * length), getHeight() / 2.0f, paint);
            i2++;
            i = i3;
        }
    }
}
