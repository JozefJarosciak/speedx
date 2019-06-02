package com.mapbox.mapboxsdk.annotations;

import android.support.v4.view.ViewCompat;
import com.mapbox.mapboxsdk.maps.MapboxMap;

public final class Polygon extends MultiPoint {
    private int fillColor = ViewCompat.MEASURED_STATE_MASK;
    private int strokeColor = ViewCompat.MEASURED_STATE_MASK;

    Polygon() {
    }

    public int getFillColor() {
        return this.fillColor;
    }

    public int getStrokeColor() {
        return this.strokeColor;
    }

    public void setFillColor(int i) {
        this.fillColor = i;
        update();
    }

    public void setStrokeColor(int i) {
        this.strokeColor = i;
        update();
    }

    void update() {
        MapboxMap mapboxMap = getMapboxMap();
        if (mapboxMap != null) {
            mapboxMap.updatePolygon(this);
        }
    }
}
