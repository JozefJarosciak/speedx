package com.baidu.mapapi.map;

import android.os.Bundle;
import com.mapbox.mapboxsdk.style.layers.Property;

public final class Stroke {
    public final int color;
    public final int strokeWidth;

    public Stroke(int i, int i2) {
        if (i <= 0) {
            i = 5;
        }
        this.strokeWidth = i;
        this.color = i2;
    }

    /* renamed from: a */
    Bundle m4169a(Bundle bundle) {
        bundle.putInt(Property.ICON_TEXT_FIT_WIDTH, this.strokeWidth);
        Overlay.m4045a(this.color, bundle);
        return bundle;
    }
}
