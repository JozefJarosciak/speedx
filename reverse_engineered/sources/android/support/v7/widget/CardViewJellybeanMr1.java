package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

class CardViewJellybeanMr1 extends CardViewEclairMr1 {

    /* renamed from: android.support.v7.widget.CardViewJellybeanMr1$1 */
    class C02821 implements RoundRectHelper {
        C02821() {
        }

        public void drawRoundRect(Canvas canvas, RectF rectF, float f, Paint paint) {
            canvas.drawRoundRect(rectF, f, f, paint);
        }
    }

    CardViewJellybeanMr1() {
    }

    public void initStatic() {
        RoundRectDrawableWithShadow.sRoundRectHelper = new C02821();
    }
}
