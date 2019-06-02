package org.xclcharts.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import org.xclcharts.event.touch.ChartTouch;
import org.xclcharts.renderer.XChart;

public abstract class ChartView extends GraphicalView {
    private List<ChartTouch> mTouch = new ArrayList();

    public ChartView(Context context) {
        super(context);
    }

    public ChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChartView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void render(Canvas canvas) {
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        touchEvent(motionEvent);
        return true;
    }

    public void bindTouch(View view, XChart xChart) {
        this.mTouch.add(new ChartTouch(this, xChart));
    }

    public void bindTouch(View view, XChart xChart, float f) {
        this.mTouch.add(new ChartTouch(this, xChart, f));
    }

    public void restTouchBind() {
        this.mTouch.clear();
    }

    private boolean touchEvent(MotionEvent motionEvent) {
        for (ChartTouch handleTouch : this.mTouch) {
            handleTouch.handleTouch(motionEvent);
        }
        return true;
    }
}
