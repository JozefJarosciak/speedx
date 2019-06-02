package com.mapbox.mapboxsdk.annotations;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.mapbox.mapboxsdk.C1485R;

class InfoWindowView extends RelativeLayout {
    private InfoWindowTipView mTipView;

    public InfoWindowView(Context context) {
        this(context, null);
    }

    public InfoWindowView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public InfoWindowView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initialize(context);
    }

    private void initialize(Context context) {
        LayoutInflater.from(context).inflate(C1485R.layout.infowindow_content, this);
        this.mTipView = (InfoWindowTipView) findViewById(C1485R.id.infowindow_tipview);
    }

    void setTipViewMarginLeft(int i) {
        LayoutParams layoutParams = (LayoutParams) this.mTipView.getLayoutParams();
        layoutParams.leftMargin = i;
        layoutParams.topMargin = (int) getResources().getDimension(C1485R.dimen.infowindow_offset);
    }
}
