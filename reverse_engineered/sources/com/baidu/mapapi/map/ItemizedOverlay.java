package com.baidu.mapapi.map;

import android.graphics.drawable.Drawable;
import com.baidu.platform.comapi.map.C1252h;

public class ItemizedOverlay extends Overlay {
    /* renamed from: a */
    MapView f2959a;

    public ItemizedOverlay(Drawable drawable, MapView mapView) {
        this.type = C1252h.marker;
        this.f2959a = mapView;
    }

    public void addItem(OverlayOptions overlayOptions) {
        if (overlayOptions != null && overlayOptions != null) {
            this.f2959a.getMap().addOverlay(overlayOptions);
        }
    }

    public void reAddAll() {
    }

    public void removeAll() {
        this.f2959a.getMap().clear();
    }
}
