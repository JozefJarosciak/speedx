package com.mapbox.services.commons.turf.models;

public class LineIntersectsResult {
    private boolean onLine1 = false;
    private boolean onLine2 = false;
    /* renamed from: x */
    private Double f14840x;
    /* renamed from: y */
    private Double f14841y;

    public Double getX() {
        return this.f14840x;
    }

    public void setX(Double d) {
        this.f14840x = d;
    }

    public Double getY() {
        return this.f14841y;
    }

    public void setY(Double d) {
        this.f14841y = d;
    }

    public boolean isOnLine1() {
        return this.onLine1;
    }

    public void setOnLine1(boolean z) {
        this.onLine1 = z;
    }

    public boolean isOnLine2() {
        return this.onLine2;
    }

    public void setOnLine2(boolean z) {
        this.onLine2 = z;
    }
}
