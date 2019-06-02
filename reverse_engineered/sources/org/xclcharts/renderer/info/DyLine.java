package org.xclcharts.renderer.info;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import com.avos.avoscloud.AVException;
import org.xclcharts.renderer.XEnum.DyLineStyle;
import org.xclcharts.renderer.XEnum.LineStyle;

public class DyLine {
    protected PointF mCenterXY = null;
    private DyLineStyle mDyLineStyle = DyLineStyle.Cross;
    private LineStyle mLineDrawStyle = LineStyle.SOLID;
    private float mOldX = 0.0f;
    private float mOldY = 0.0f;
    private Paint mPaintLine = null;

    public Paint getLinePaint() {
        if (this.mPaintLine == null) {
            this.mPaintLine = new Paint(1);
            this.mPaintLine.setColor(Color.rgb(AVException.USER_MOBILEPHONE_NOT_VERIFIED, 10, 10));
        }
        return this.mPaintLine;
    }

    public void setCurrentXY(float f, float f2) {
        if (this.mCenterXY == null) {
            this.mCenterXY = new PointF();
        }
        this.mCenterXY.x = f;
        this.mCenterXY.y = f2;
    }

    public boolean isInvalidate() {
        if (this.mCenterXY == null) {
            return false;
        }
        if (Float.compare(Math.abs(this.mCenterXY.x - this.mOldX), 5.0f) != 1 && Float.compare(Math.abs(this.mCenterXY.y - this.mOldY), 5.0f) != 1) {
            return false;
        }
        this.mOldX = this.mCenterXY.x;
        this.mOldY = this.mCenterXY.y;
        return true;
    }

    public void setDyLineStyle(DyLineStyle dyLineStyle) {
        this.mDyLineStyle = dyLineStyle;
    }

    public DyLineStyle getDyLineStyle() {
        return this.mDyLineStyle;
    }

    public void setLineDrawStyle(LineStyle lineStyle) {
        this.mLineDrawStyle = lineStyle;
    }

    public LineStyle getLineDrawStyle() {
        return this.mLineDrawStyle;
    }
}
