package com.mapbox.mapboxsdk.maps;

import android.graphics.PointF;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;

public class UiSettings {
    private ViewSettings attributionSettings;
    private CompassViewSettings compassSettings;
    private boolean deselectMarkersOnTap = true;
    private PointF focalPoint;
    private ViewSettings logoSettings;
    private MapView mapView;
    private boolean rotateGestureChangeAllowed = true;
    private boolean rotateGesturesEnabled = true;
    private boolean scrollGestureChangeAllowed = true;
    private boolean scrollGesturesEnabled = true;
    private boolean tiltGestureChangeAllowed = true;
    private boolean tiltGesturesEnabled = true;
    private boolean zoomControlsEnabled;
    private boolean zoomGestureChangeAllowed = true;
    private boolean zoomGesturesEnabled = true;

    UiSettings(@NonNull MapView mapView) {
        this.mapView = mapView;
        this.compassSettings = new CompassViewSettings();
        this.logoSettings = new ViewSettings();
        this.attributionSettings = new ViewSettings();
    }

    public void setCompassEnabled(boolean z) {
        this.compassSettings.setEnabled(z);
        this.mapView.setCompassEnabled(z);
    }

    public boolean isCompassEnabled() {
        return this.compassSettings.isEnabled();
    }

    @UiThread
    public void setCompassGravity(int i) {
        this.compassSettings.setGravity(i);
        this.mapView.setCompassGravity(i);
    }

    public void setCompassFadeFacingNorth(boolean z) {
        this.compassSettings.setFadeFacingNorth(z);
        this.mapView.setCompassFadeFacingNorth(z);
    }

    public boolean isCompassFadeWhenFacingNorth() {
        return this.compassSettings.isFadeFacingNorth();
    }

    public int getCompassGravity() {
        return this.compassSettings.getGravity();
    }

    @UiThread
    public void setCompassMargins(int i, int i2, int i3, int i4) {
        this.compassSettings.setMargins(new int[]{i, i2, i3, i4});
        this.mapView.setCompassMargins(i, i2, i3, i4);
    }

    public int getCompassMarginLeft() {
        return this.compassSettings.getMargins()[0];
    }

    public int getCompassMarginTop() {
        return this.compassSettings.getMargins()[1];
    }

    public int getCompassMarginRight() {
        return this.compassSettings.getMargins()[2];
    }

    public int getCompassMarginBottom() {
        return this.compassSettings.getMargins()[3];
    }

    public void setLogoEnabled(boolean z) {
        this.logoSettings.setEnabled(z);
        this.mapView.setLogoEnabled(z);
    }

    public boolean isLogoEnabled() {
        return this.logoSettings.isEnabled();
    }

    public void setLogoGravity(int i) {
        this.logoSettings.setGravity(i);
        this.mapView.setLogoGravity(i);
    }

    public int getLogoGravity() {
        return this.logoSettings.getGravity();
    }

    public void setLogoMargins(int i, int i2, int i3, int i4) {
        this.logoSettings.setMargins(new int[]{i, i2, i3, i4});
        this.mapView.setLogoMargins(i, i2, i3, i4);
    }

    public int getLogoMarginLeft() {
        return this.logoSettings.getMargins()[0];
    }

    public int getLogoMarginTop() {
        return this.logoSettings.getMargins()[1];
    }

    public int getLogoMarginRight() {
        return this.logoSettings.getMargins()[2];
    }

    public int getLogoMarginBottom() {
        return this.logoSettings.getMargins()[3];
    }

    public void setAttributionEnabled(boolean z) {
        this.attributionSettings.setEnabled(z);
        this.mapView.setAttributionEnabled(z ? 0 : 8);
    }

    public boolean isAttributionEnabled() {
        return this.attributionSettings.isEnabled();
    }

    public void setAttributionGravity(int i) {
        this.attributionSettings.setGravity(i);
        this.mapView.setAttributionGravity(i);
    }

    public int getAttributionGravity() {
        return this.attributionSettings.getGravity();
    }

    public void setAttributionMargins(int i, int i2, int i3, int i4) {
        this.attributionSettings.setMargins(new int[]{i, i2, i3, i4});
        this.mapView.setAttributionMargins(i, i2, i3, i4);
    }

    public void setAttributionTintColor(@ColorInt int i) {
        this.attributionSettings.setTintColor(i);
        this.mapView.setAtttibutionTintColor(i);
    }

    public int getAttributionTintColor() {
        return this.attributionSettings.getTintColor();
    }

    public int getAttributionMarginLeft() {
        return this.attributionSettings.getMargins()[0];
    }

    public int getAttributionMarginTop() {
        return this.attributionSettings.getMargins()[1];
    }

    public int getAttributionMarginRight() {
        return this.attributionSettings.getMargins()[2];
    }

    public int getAttributionMarginBottom() {
        return this.attributionSettings.getMargins()[3];
    }

    public void setRotateGesturesEnabled(boolean z) {
        if (this.rotateGestureChangeAllowed) {
            this.rotateGesturesEnabled = z;
        }
    }

    public boolean isRotateGesturesEnabled() {
        return this.rotateGesturesEnabled;
    }

    void setRotateGestureChangeAllowed(boolean z) {
        this.rotateGestureChangeAllowed = z;
    }

    boolean isRotateGestureChangeAllowed() {
        return this.rotateGestureChangeAllowed;
    }

    public void setTiltGesturesEnabled(boolean z) {
        if (this.tiltGestureChangeAllowed) {
            this.tiltGesturesEnabled = z;
        }
    }

    public boolean isTiltGesturesEnabled() {
        return this.tiltGesturesEnabled;
    }

    void setTiltGestureChangeAllowed(boolean z) {
        this.tiltGestureChangeAllowed = z;
    }

    boolean isTiltGestureChangeAllowed() {
        return this.tiltGestureChangeAllowed;
    }

    public void setZoomGesturesEnabled(boolean z) {
        if (this.zoomGestureChangeAllowed) {
            this.zoomGesturesEnabled = z;
        }
    }

    public boolean isZoomGesturesEnabled() {
        return this.zoomGesturesEnabled;
    }

    void setZoomGestureChangeAllowed(boolean z) {
        this.zoomGestureChangeAllowed = z;
    }

    boolean isZoomGestureChangeAllowed() {
        return this.zoomGestureChangeAllowed;
    }

    public void setZoomControlsEnabled(boolean z) {
        this.zoomControlsEnabled = z;
    }

    public boolean isZoomControlsEnabled() {
        return this.zoomControlsEnabled;
    }

    public boolean isDeselectMarkersOnTap() {
        return this.deselectMarkersOnTap;
    }

    public void setDeselectMarkersOnTap(boolean z) {
        this.deselectMarkersOnTap = z;
    }

    public void setScrollGesturesEnabled(boolean z) {
        if (this.scrollGestureChangeAllowed) {
            this.scrollGesturesEnabled = z;
        }
    }

    public boolean isScrollGesturesEnabled() {
        return this.scrollGesturesEnabled;
    }

    void setScrollGestureChangeAllowed(boolean z) {
        this.scrollGestureChangeAllowed = z;
    }

    boolean isScrollGestureChangeAllowed() {
        return this.scrollGestureChangeAllowed;
    }

    public void setAllGesturesEnabled(boolean z) {
        setScrollGesturesEnabled(z);
        setRotateGesturesEnabled(z);
        setTiltGesturesEnabled(z);
        setZoomGesturesEnabled(z);
    }

    public void setFocalPoint(@Nullable PointF pointF) {
        this.focalPoint = pointF;
        this.mapView.setFocalPoint(pointF);
    }

    public PointF getFocalPoint() {
        return this.focalPoint;
    }

    public float getHeight() {
        return (float) this.mapView.getMeasuredHeight();
    }

    public float getWidth() {
        return (float) this.mapView.getMeasuredWidth();
    }

    public void invalidate() {
        this.mapView.setLogoMargins(getLogoMarginLeft(), getLogoMarginTop(), getLogoMarginRight(), getLogoMarginBottom());
        this.mapView.setCompassMargins(getCompassMarginLeft(), getCompassMarginTop(), getCompassMarginRight(), getCompassMarginBottom());
        this.mapView.setAttributionMargins(getAttributionMarginLeft(), getAttributionMarginTop(), getAttributionMarginRight(), getAttributionMarginBottom());
    }
}
