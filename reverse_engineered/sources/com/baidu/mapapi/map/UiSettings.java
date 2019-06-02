package com.baidu.mapapi.map;

import com.baidu.platform.comapi.map.C1249e;

public final class UiSettings {
    /* renamed from: a */
    private C1249e f3199a;

    UiSettings(C1249e c1249e) {
        this.f3199a = c1249e;
    }

    public boolean isCompassEnabled() {
        return this.f3199a.m4739q();
    }

    public boolean isOverlookingGesturesEnabled() {
        return this.f3199a.m4748y();
    }

    public boolean isRotateGesturesEnabled() {
        return this.f3199a.m4747x();
    }

    public boolean isScrollGesturesEnabled() {
        return this.f3199a.m4745v();
    }

    public boolean isZoomGesturesEnabled() {
        return this.f3199a.m4746w();
    }

    public void setAllGesturesEnabled(boolean z) {
        setRotateGesturesEnabled(z);
        setScrollGesturesEnabled(z);
        setOverlookingGesturesEnabled(z);
        setZoomGesturesEnabled(z);
    }

    public void setCompassEnabled(boolean z) {
        this.f3199a.m4721h(z);
    }

    public void setOverlookingGesturesEnabled(boolean z) {
        this.f3199a.m4736p(z);
    }

    public void setRotateGesturesEnabled(boolean z) {
        this.f3199a.m4735o(z);
    }

    public void setScrollGesturesEnabled(boolean z) {
        this.f3199a.m4731m(z);
    }

    public void setZoomGesturesEnabled(boolean z) {
        this.f3199a.m4733n(z);
    }
}
