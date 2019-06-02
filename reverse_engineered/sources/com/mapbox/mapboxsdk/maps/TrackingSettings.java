package com.mapbox.mapboxsdk.maps;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

public class TrackingSettings {
    private boolean dismissBearingTrackingOnGesture = true;
    private boolean dismissLocationTrackingOnGesture = true;
    private MapView mapView;
    private int myBearingTrackingMode;
    private int myLocationTrackingMode;
    private UiSettings uiSettings;

    TrackingSettings(@NonNull MapView mapView, UiSettings uiSettings) {
        this.mapView = mapView;
        this.uiSettings = uiSettings;
    }

    @UiThread
    public void setMyLocationTrackingMode(int i) {
        this.myLocationTrackingMode = i;
        this.mapView.setMyLocationTrackingMode(i);
    }

    @UiThread
    public int getMyLocationTrackingMode() {
        return this.myLocationTrackingMode;
    }

    @UiThread
    public void setMyBearingTrackingMode(int i) {
        this.myBearingTrackingMode = i;
        this.mapView.setMyBearingTrackingMode(i);
    }

    @UiThread
    public int getMyBearingTrackingMode() {
        return this.myBearingTrackingMode;
    }

    @Deprecated
    public boolean isDismissTrackingOnGesture() {
        return this.dismissLocationTrackingOnGesture && this.dismissBearingTrackingOnGesture;
    }

    public boolean isAllDismissTrackingOnGesture() {
        return this.dismissLocationTrackingOnGesture && this.dismissBearingTrackingOnGesture;
    }

    @Deprecated
    public void setDismissTrackingOnGesture(boolean z) {
        setDismissAllTrackingOnGesture(z);
    }

    public void setDismissAllTrackingOnGesture(boolean z) {
        this.dismissLocationTrackingOnGesture = z;
        this.dismissBearingTrackingOnGesture = z;
    }

    public void setDismissLocationTrackingOnGesture(boolean z) {
        this.dismissLocationTrackingOnGesture = z;
    }

    public boolean isDismissLocationTrackingOnGesture() {
        return this.dismissLocationTrackingOnGesture;
    }

    public void setDismissBearingTrackingOnGesture(boolean z) {
        this.dismissBearingTrackingOnGesture = z;
    }

    public boolean isDismissBearingTrackingOnGesture() {
        return this.dismissBearingTrackingOnGesture;
    }

    public boolean isLocationTrackingDisabled() {
        return this.myLocationTrackingMode == 0;
    }

    public boolean isBearingTrackingDisabled() {
        return this.myBearingTrackingMode == 0;
    }

    public boolean isRotateGestureCurrentlyEnabled() {
        return this.uiSettings.isRotateGesturesEnabled() && (this.dismissBearingTrackingOnGesture || this.myBearingTrackingMode == 0);
    }

    public boolean isScrollGestureCurrentlyEnabled() {
        return this.uiSettings.isScrollGesturesEnabled() && (this.dismissLocationTrackingOnGesture || this.myLocationTrackingMode == 0);
    }
}
