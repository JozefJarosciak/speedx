package com.wdullaer.materialdatetimepicker.date;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;

/* compiled from: SimpleMonthView */
/* renamed from: com.wdullaer.materialdatetimepicker.date.h */
public class C4800h extends C4797e {
    public C4800h(Context context, AttributeSet attributeSet, C4784a c4784a) {
        super(context, attributeSet, c4784a);
    }

    /* renamed from: a */
    public void mo6201a(Canvas canvas, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (this.x == i3) {
            canvas.drawCircle((float) i4, (float) (i5 - (d / 3)), (float) h, this.n);
        }
        if (m18853a(i, i2, i3)) {
            this.l.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
        } else {
            this.l.setTypeface(Typeface.create(Typeface.DEFAULT, 0));
        }
        if (this.j.mo6190b(i, i2, i3)) {
            this.l.setColor(this.M);
        } else if (this.x == i3) {
            this.l.setTypeface(Typeface.create(Typeface.DEFAULT, 1));
            this.l.setColor(this.I);
        } else if (this.w && this.y == i3) {
            this.l.setColor(this.K);
        } else {
            this.l.setColor(m18853a(i, i2, i3) ? this.L : this.H);
        }
        canvas.drawText(String.format("%d", new Object[]{Integer.valueOf(i3)}), (float) i4, (float) i5, this.l);
    }
}
