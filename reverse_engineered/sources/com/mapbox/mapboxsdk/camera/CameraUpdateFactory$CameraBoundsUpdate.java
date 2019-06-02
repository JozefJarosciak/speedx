package com.mapbox.mapboxsdk.camera;

import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.camera.CameraPosition.Builder;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.Projection;
import com.mapbox.mapboxsdk.maps.UiSettings;
import com.mapbox.mapboxsdk.utils.MathUtils;

final class CameraUpdateFactory$CameraBoundsUpdate implements CameraUpdate {
    private LatLngBounds bounds;
    private RectF padding;

    CameraUpdateFactory$CameraBoundsUpdate(LatLngBounds latLngBounds, RectF rectF) {
        this.bounds = latLngBounds;
        this.padding = rectF;
    }

    CameraUpdateFactory$CameraBoundsUpdate(LatLngBounds latLngBounds, int[] iArr) {
        this(latLngBounds, new RectF((float) iArr[0], (float) iArr[1], (float) iArr[2], (float) iArr[3]));
    }

    CameraUpdateFactory$CameraBoundsUpdate(LatLngBounds latLngBounds, int i, int i2, int i3, int i4) {
        this(latLngBounds, new int[]{i, i2, i3, i4});
    }

    public LatLngBounds getBounds() {
        return this.bounds;
    }

    public RectF getPadding() {
        return this.padding;
    }

    public CameraPosition getCameraPosition(@NonNull MapboxMap mapboxMap) {
        PointF toScreenLocation;
        Projection projection = mapboxMap.getProjection();
        UiSettings uiSettings = mapboxMap.getUiSettings();
        int[] padding = mapboxMap.getPadding();
        RectF padding2 = getPadding();
        RectF rectF = new RectF(padding2.left + ((float) padding[0]), padding2.top + ((float) padding[1]), padding2.right + ((float) padding[2]), ((float) padding[3]) + padding2.bottom);
        PointF pointF = new PointF(-3.4028235E38f, -3.4028235E38f);
        PointF pointF2 = new PointF(Float.MAX_VALUE, Float.MAX_VALUE);
        float height = uiSettings.getHeight();
        for (LatLng toScreenLocation2 : getBounds().toLatLngs()) {
            toScreenLocation = projection.toScreenLocation(toScreenLocation2);
            pointF2.x = Math.min(pointF2.x, toScreenLocation.x);
            pointF.x = Math.max(pointF.x, toScreenLocation.x);
            pointF2.y = Math.min(pointF2.y, height - toScreenLocation.y);
            pointF.y = Math.max(pointF.y, height - toScreenLocation.y);
        }
        float f = pointF.x - pointF2.x;
        float f2 = pointF.y - pointF2.y;
        double d = 0.0d;
        float f3 = 1.0f;
        if (rectF != null) {
            float width = ((uiSettings.getWidth() - rectF.left) - rectF.right) / f;
            float height2 = ((uiSettings.getHeight() - rectF.top) - rectF.bottom) / f2;
            f3 = width < height2 ? width : height2;
            d = MathUtils.clamp(projection.calculateZoom(f3), (double) ((float) mapboxMap.getMinZoom()), (double) ((float) mapboxMap.getMaxZoom()));
        }
        PointF pointF3 = new PointF(pointF.x + (rectF.right / f3), pointF.y + (rectF.top / f3));
        PointF pointF4 = new PointF(pointF2.x - (rectF.left / f3), pointF2.y - (rectF.bottom / f3));
        toScreenLocation = new PointF((pointF3.x + pointF4.x) / 2.0f, (pointF3.y + pointF4.y) / 2.0f);
        toScreenLocation.y = height - toScreenLocation.y;
        return new Builder().target(projection.fromScreenLocation(toScreenLocation)).zoom((double) ((float) d)).tilt(0.0d).bearing(0.0d).build();
    }
}
