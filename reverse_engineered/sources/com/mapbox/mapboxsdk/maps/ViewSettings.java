package com.mapbox.mapboxsdk.maps;

class ViewSettings {
    private boolean enabled;
    private int gravity;
    private int[] margins = new int[4];
    private int tintColor;

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean z) {
        this.enabled = z;
    }

    public int getGravity() {
        return this.gravity;
    }

    public void setGravity(int i) {
        this.gravity = i;
    }

    public int[] getMargins() {
        return this.margins;
    }

    public void setMargins(int[] iArr) {
        this.margins = iArr;
    }

    public int getTintColor() {
        return this.tintColor;
    }

    public void setTintColor(int i) {
        this.tintColor = i;
    }
}
