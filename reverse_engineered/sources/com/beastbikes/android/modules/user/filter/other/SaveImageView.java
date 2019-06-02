package com.beastbikes.android.modules.user.filter.other;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.ImageView;

public class SaveImageView extends ImageView {
    /* renamed from: a */
    private Paint f11492a = new Paint();
    /* renamed from: b */
    private int f11493b = 0;

    public SaveImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onDraw(Canvas canvas) {
        try {
            super.onDraw(canvas);
        } catch (Exception e) {
        }
    }

    public int getOffset() {
        return this.f11493b;
    }

    public void setOffset(int i) {
        this.f11493b = i;
    }
}
