package org.xclcharts.renderer.info;

import android.graphics.Canvas;

public class ToolTipRender extends ToolTip {
    public void renderInfo(Canvas canvas) {
        drawInfo(canvas);
        clear();
    }
}
