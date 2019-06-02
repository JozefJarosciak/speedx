package org.xclcharts.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.common.primitives.Ints;
import org.xclcharts.common.SysinfoHelper;

@SuppressLint({"NewApi"})
public abstract class GraphicalView extends View {
    private String TAG = "GraphicalView";

    public abstract void render(Canvas canvas);

    public GraphicalView(Context context) {
        super(context);
        initChartView();
    }

    public GraphicalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initChartView();
    }

    public GraphicalView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initChartView();
    }

    protected void initChartView() {
        disableHardwareAccelerated();
    }

    public void refreshChart() {
        invalidate();
    }

    public void onDraw(Canvas canvas) {
        try {
            render(canvas);
        } catch (Exception e) {
            Log.e(this.TAG, e.toString());
        }
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        setMeasuredDimension(measureWidth(i), measureHeight(i2));
    }

    private int measureWidth(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == Ints.MAX_POWER_OF_TWO) {
            return size;
        }
        return mode == Integer.MIN_VALUE ? Math.min(100, size) : 100;
    }

    private int measureHeight(int i) {
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        if (mode == Ints.MAX_POWER_OF_TWO) {
            return size;
        }
        return mode == Integer.MIN_VALUE ? Math.min(100, size) : 100;
    }

    protected void disableHardwareAccelerated() {
        if (SysinfoHelper.getInstance().supportHardwareAccelerated() && !isHardwareAccelerated()) {
            setLayerType(1, null);
        }
    }
}
