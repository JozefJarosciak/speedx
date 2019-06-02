package com.mapbox.mapboxsdk.maps.widgets;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import com.mapbox.mapboxsdk.maps.MapView;

public class MyLocationViewSettings {
    private int accuracyAlpha;
    @ColorInt
    private int accuracyTintColor;
    private Drawable backgroundDrawable;
    private int[] backgroundOffset = new int[4];
    @ColorInt
    private int backgroundTintColor;
    private boolean enabled;
    private Drawable foregroundBearingDrawable;
    private Drawable foregroundDrawable;
    @ColorInt
    private int foregroundTintColor;
    private MapView mapView;
    private MyLocationView myLocationView;
    private int[] padding = new int[4];

    public MyLocationViewSettings(MapView mapView, MyLocationView myLocationView) {
        this.mapView = mapView;
        this.myLocationView = myLocationView;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
        this.myLocationView.setEnabled(z);
    }

    public void setForegroundDrawable(Drawable drawable, Drawable drawable2) {
        this.foregroundDrawable = drawable;
        this.foregroundBearingDrawable = drawable2;
        this.myLocationView.setForegroundDrawables(drawable, drawable2);
    }

    public Drawable getForegroundDrawable() {
        return this.foregroundDrawable;
    }

    public Drawable getForegroundBearingDrawable() {
        return this.foregroundBearingDrawable;
    }

    public void setForegroundTintColor(@ColorInt int i) {
        this.foregroundTintColor = i;
        this.myLocationView.setForegroundDrawableTint(i);
    }

    public int getForegroundTintColor() {
        return this.foregroundTintColor;
    }

    public void setBackgroundDrawable(Drawable drawable, int[] iArr) {
        this.backgroundDrawable = drawable;
        this.backgroundOffset = iArr;
        if (iArr == null || iArr.length != 4) {
            this.myLocationView.setShadowDrawable(drawable);
            return;
        }
        this.myLocationView.setShadowDrawable(drawable, iArr[0], iArr[1], iArr[2], iArr[3]);
    }

    public Drawable getBackgroundDrawable() {
        return this.backgroundDrawable;
    }

    public void setBackgroundTintColor(@ColorInt int i) {
        this.backgroundTintColor = i;
        this.myLocationView.setShadowDrawableTint(i);
    }

    public int getBackgroundTintColor() {
        return this.backgroundTintColor;
    }

    public int[] getBackgroundOffset() {
        return this.backgroundOffset;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.padding = new int[]{i, i2, i3, i4};
        this.myLocationView.setContentPadding(this.padding);
        this.mapView.invalidateContentPadding();
    }

    public int[] getPadding() {
        return this.padding;
    }

    public int getAccuracyAlpha() {
        return this.accuracyAlpha;
    }

    public void setAccuracyAlpha(@IntRange(from = 0, to = 255) int i) {
        this.accuracyAlpha = i;
        this.myLocationView.setAccuracyAlpha(i);
    }

    public int getAccuracyTintColor() {
        return this.accuracyTintColor;
    }

    public void setAccuracyTintColor(@ColorInt int i) {
        this.accuracyTintColor = i;
        this.myLocationView.setAccuracyTint(i);
    }
}
