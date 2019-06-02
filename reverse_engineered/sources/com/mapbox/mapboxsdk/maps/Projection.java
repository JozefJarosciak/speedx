package com.mapbox.mapboxsdk.maps;

import android.graphics.PointF;
import android.support.annotation.FloatRange;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds.Builder;
import com.mapbox.mapboxsdk.geometry.VisibleRegion;

public class Projection {
    private final MapView mapView;
    private final float screenDensity;
    private final PointF screenLocationPoint = new PointF();

    Projection(@NonNull MapView mapView) {
        this.mapView = mapView;
        this.screenDensity = mapView.getContext() != null ? mapView.getContext().getResources().getDisplayMetrics().density : 1.0f;
    }

    public double getMetersPerPixelAtLatitude(@FloatRange(from = -90.0d, to = 90.0d) double d) {
        return this.mapView.getMetersPerPixelAtLatitude(d);
    }

    public LatLng fromScreenLocation(PointF pointF) {
        this.screenLocationPoint.set(pointF.x / this.screenDensity, pointF.y / this.screenDensity);
        return this.mapView.fromNativeScreenLocation(this.screenLocationPoint);
    }

    public VisibleRegion getVisibleRegion() {
        Builder builder = new Builder();
        float contentPaddingLeft = (float) this.mapView.getContentPaddingLeft();
        float width = (float) (this.mapView.getWidth() - this.mapView.getContentPaddingRight());
        float contentPaddingTop = (float) this.mapView.getContentPaddingTop();
        float height = (float) (this.mapView.getHeight() - this.mapView.getContentPaddingBottom());
        LatLng fromScreenLocation = fromScreenLocation(new PointF(contentPaddingLeft, contentPaddingTop));
        LatLng fromScreenLocation2 = fromScreenLocation(new PointF(width, contentPaddingTop));
        LatLng fromScreenLocation3 = fromScreenLocation(new PointF(width, height));
        LatLng fromScreenLocation4 = fromScreenLocation(new PointF(contentPaddingLeft, height));
        builder.include(fromScreenLocation).include(fromScreenLocation2).include(fromScreenLocation3).include(fromScreenLocation4);
        return new VisibleRegion(fromScreenLocation, fromScreenLocation2, fromScreenLocation4, fromScreenLocation3, builder.build());
    }

    public PointF toScreenLocation(LatLng latLng) {
        PointF toNativeScreenLocation = this.mapView.toNativeScreenLocation(latLng);
        toNativeScreenLocation.set(toNativeScreenLocation.x * this.screenDensity, toNativeScreenLocation.y * this.screenDensity);
        return toNativeScreenLocation;
    }

    public double calculateZoom(float f) {
        return Math.log(this.mapView.getScale() * ((double) f)) / Math.log(2.0d);
    }
}
