package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import org.xclcharts.renderer.XEnum.Location;

public class PlotAttrInfoRender extends PlotAttrInfo {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Location;
    PointF mPosPintF = new PointF();

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Location() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Location;
        if (iArr == null) {
            iArr = new int[Location.values().length];
            try {
                iArr[Location.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Location.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Location.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Location.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Location = iArr;
        }
        return iArr;
    }

    public void renderAttrInfo(Canvas canvas, float f, float f2, float f3) {
        if (this.mAttrInfo != null && this.mAttrInfoLocation != null) {
            String str = "";
            int i = 0;
            while (i < this.mAttrInfo.size()) {
                String str2 = (String) this.mAttrInfo.get(i);
                if (!("" == str2 || this.mAttrInfoPostion == null || this.mAttrInfoPostion.size() < i || this.mAttrInfoPaint.get(i) == null || this.mAttrInfoPaint.size() < i)) {
                    this.mPosPintF.x = f;
                    this.mPosPintF.y = f2;
                    float floatValue = f3 * ((Float) this.mAttrInfoPostion.get(i)).floatValue();
                    switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Location()[((Location) this.mAttrInfoLocation.get(i)).ordinal()]) {
                        case 1:
                            this.mPosPintF.y = f2 - floatValue;
                            break;
                        case 2:
                            this.mPosPintF.y = floatValue + f2;
                            break;
                        case 3:
                            this.mPosPintF.x = f - floatValue;
                            break;
                        case 4:
                            this.mPosPintF.x = floatValue + f;
                            break;
                    }
                    canvas.drawText(str2, this.mPosPintF.x, this.mPosPintF.y, (Paint) this.mAttrInfoPaint.get(i));
                }
                i++;
            }
        }
    }
}
