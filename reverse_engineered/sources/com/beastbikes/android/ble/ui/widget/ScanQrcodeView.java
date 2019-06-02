package com.beastbikes.android.ble.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.google.zxing.ResultPoint;
import com.journeyapps.barcodescanner.ViewfinderView;
import java.util.ArrayList;
import java.util.List;

public class ScanQrcodeView extends ViewfinderView {
    public ScanQrcodeView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void onDraw(Canvas canvas) {
        m9362a();
        if (this.m != null && this.n != null) {
            Rect rect = this.m;
            Rect rect2 = this.n;
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            this.c.setColor(this.d != null ? this.f : this.e);
            canvas.drawRect(0.0f, 0.0f, (float) width, (float) rect.top, this.c);
            canvas.drawRect(0.0f, (float) rect.top, (float) rect.left, (float) (rect.bottom + 1), this.c);
            canvas.drawRect((float) (rect.right + 1), (float) rect.top, (float) width, (float) (rect.bottom + 1), this.c);
            canvas.drawRect(0.0f, (float) (rect.bottom + 1), (float) width, (float) height, this.c);
            if (this.d != null) {
                this.c.setAlpha(160);
                canvas.drawBitmap(this.d, null, rect, this.c);
                return;
            }
            float width2 = ((float) rect.width()) / ((float) rect2.width());
            float height2 = ((float) rect.height()) / ((float) rect2.height());
            List<ResultPoint> list = this.j;
            List<ResultPoint> list2 = this.k;
            int i = rect.left;
            int i2 = rect.top;
            if (list.isEmpty()) {
                this.k = null;
            } else {
                this.j = new ArrayList(5);
                this.k = list;
                this.c.setAlpha(160);
                this.c.setColor(this.h);
                for (ResultPoint resultPoint : list) {
                    canvas.drawCircle((float) (((int) (resultPoint.getX() * width2)) + i), (float) (((int) (resultPoint.getY() * height2)) + i2), 6.0f, this.c);
                }
            }
            if (list2 != null) {
                this.c.setAlpha(80);
                this.c.setColor(this.h);
                for (ResultPoint resultPoint2 : list2) {
                    canvas.drawCircle((float) (((int) (resultPoint2.getX() * width2)) + i), (float) (((int) (resultPoint2.getY() * height2)) + i2), 3.0f, this.c);
                }
            }
            postInvalidateDelayed(80, rect.left - 6, rect.top - 6, rect.right + 6, rect.bottom + 6);
        }
    }
}
