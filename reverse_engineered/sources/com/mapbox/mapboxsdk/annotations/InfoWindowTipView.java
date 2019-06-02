package com.mapbox.mapboxsdk.annotations;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import com.mapbox.mapboxsdk.C1485R;

final class InfoWindowTipView extends View {
    private int mLineWidth;
    private Paint mPaint;
    private Path mPath = new Path();

    public InfoWindowTipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mLineWidth = (int) context.getResources().getDimension(C1485R.dimen.infowindow_line_width);
        this.mPaint = new Paint();
        this.mPaint.setColor(-1);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(0.0f);
        this.mPaint.setStyle(Style.FILL);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredHeight = getMeasuredHeight();
        int measuredWidth = getMeasuredWidth();
        this.mPath.rewind();
        this.mPaint.setColor(-1);
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth(0.0f);
        this.mPaint.setStyle(Style.FILL);
        this.mPath.moveTo(0.0f, 0.0f);
        this.mPath.lineTo((float) measuredWidth, 0.0f);
        this.mPath.lineTo((float) (measuredWidth / 2), (float) measuredHeight);
        this.mPath.lineTo(0.0f, 0.0f);
        canvas.drawPath(this.mPath, this.mPaint);
        this.mPath.rewind();
        this.mPaint.setColor(Color.parseColor("#C2C2C2"));
        this.mPaint.setAntiAlias(true);
        this.mPaint.setStrokeWidth((float) this.mLineWidth);
        this.mPaint.setStyle(Style.STROKE);
        this.mPath.moveTo(0.0f, 0.0f);
        this.mPath.lineTo((float) (measuredWidth / 2), (float) measuredHeight);
        this.mPath.lineTo((float) measuredWidth, 0.0f);
        canvas.drawPath(this.mPath, this.mPaint);
    }
}
