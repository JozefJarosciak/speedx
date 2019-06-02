package org.xclcharts.renderer.plot;

import android.graphics.Paint;
import android.support.v4.view.ViewCompat;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.VerticalAlign;

public class PlotTitle {
    private String mChartTitle = "";
    private HorizontalAlign mChartTitleAlign = HorizontalAlign.CENTER;
    private Paint mPaintSubtitle = null;
    private Paint mPaintTitle = null;
    private String mSubtitle = "";
    private VerticalAlign mTitlePosition = VerticalAlign.MIDDLE;

    public void setTitle(String str) {
        this.mChartTitle = str;
    }

    public String getTitle() {
        return this.mChartTitle;
    }

    public void setSubtitle(String str) {
        this.mSubtitle = str;
    }

    public String getSubtitle() {
        return this.mSubtitle;
    }

    public Paint getTitlePaint() {
        if (this.mPaintTitle == null) {
            this.mPaintTitle = new Paint();
            this.mPaintTitle.setTextSize(32.0f);
            this.mPaintTitle.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintTitle.setAntiAlias(true);
        }
        return this.mPaintTitle;
    }

    public Paint getSubtitlePaint() {
        if (this.mPaintSubtitle == null) {
            this.mPaintSubtitle = new Paint();
            this.mPaintSubtitle.setTextSize(22.0f);
            this.mPaintSubtitle.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintSubtitle.setAntiAlias(true);
        }
        return this.mPaintSubtitle;
    }

    public void setTitleAlign(HorizontalAlign horizontalAlign) {
        this.mChartTitleAlign = horizontalAlign;
    }

    public HorizontalAlign getTitleAlign() {
        return this.mChartTitleAlign;
    }

    public void setVerticalAlign(VerticalAlign verticalAlign) {
        this.mTitlePosition = verticalAlign;
    }

    public VerticalAlign getVerticalAlign() {
        return this.mTitlePosition;
    }
}
