package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import org.xclcharts.renderer.IRender;
import org.xclcharts.renderer.XEnum.Direction;

public class PlotAreaRender extends PlotArea implements IRender {
    protected void drawPlotBackground(Canvas canvas) {
        if (canvas != null && getBackgroundColorVisible()) {
            if (getApplayGradient()) {
                Shader linearGradient;
                if (getGradientDirection() == Direction.VERTICAL) {
                    linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, getBottom() - getTop(), getBeginColor(), getEndColor(), getGradientMode());
                } else {
                    linearGradient = new LinearGradient(getLeft(), getBottom(), getRight(), getTop(), getBeginColor(), getEndColor(), getGradientMode());
                }
                getBackgroundPaint().setShader(linearGradient);
            } else {
                getBackgroundPaint().setShader(null);
            }
            canvas.drawRect(this.mLeft, this.mTop, this.mRight, this.mBottom, getBackgroundPaint());
        }
    }

    public float getCenterX() {
        return Math.abs(this.mLeft + ((this.mRight - this.mLeft) / 2.0f));
    }

    public float getCenterY() {
        return Math.abs(this.mBottom - ((this.mBottom - this.mTop) / 2.0f));
    }

    public void setLeft(float f) {
        this.mLeft = f;
    }

    public void setTop(float f) {
        this.mTop = f;
    }

    public void setRight(float f) {
        this.mRight = f;
    }

    public void setBottom(float f) {
        this.mBottom = f;
    }

    public float getPlotRight() {
        return this.mRight + getExtWidth();
    }

    public boolean render(Canvas canvas) throws Exception {
        if (canvas != null) {
            try {
                drawPlotBackground(canvas);
            } catch (Exception e) {
                throw e;
            }
        }
        return false;
    }
}
