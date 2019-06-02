package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import com.beastbikes.android.R$styleable;

public class ColorCircleView extends View {
    /* renamed from: a */
    private Paint f10031a;

    public ColorCircleView(Context context) {
        super(context);
    }

    public ColorCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m11025a(context, attributeSet, 0);
    }

    public ColorCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m11025a(context, attributeSet, 0);
    }

    /* renamed from: a */
    private void m11025a(Context context, AttributeSet attributeSet, int i) {
        int i2 = 0;
        TypedArray obtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R$styleable.ColorCircleView, i, 0);
        this.f10031a = new Paint();
        while (i2 < obtainStyledAttributes.getIndexCount()) {
            int index = obtainStyledAttributes.getIndex(i2);
            switch (index) {
                case 0:
                    this.f10031a.setColor(obtainStyledAttributes.getColor(index, ViewCompat.MEASURED_STATE_MASK));
                    break;
                default:
                    break;
            }
            this.f10031a.setAntiAlias(true);
            i2++;
        }
    }

    protected void onDraw(Canvas canvas) {
        int width = getWidth() / 2;
        canvas.drawCircle((float) width, (float) width, (float) width, this.f10031a);
        super.onDraw(canvas);
    }
}
