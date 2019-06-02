package com.github.mikephil.charting.components;

import com.github.mikephil.charting.p183g.C3283i;

public class XAxis extends C1476a {
    /* renamed from: B */
    public int f13936B;
    /* renamed from: C */
    public int f13937C;
    /* renamed from: D */
    public int f13938D;
    /* renamed from: E */
    public int f13939E;
    /* renamed from: F */
    protected float f13940F;
    /* renamed from: G */
    private boolean f13941G;
    /* renamed from: H */
    private XAxisPosition f13942H;

    public enum XAxisPosition {
        TOP,
        BOTTOM,
        BOTH_SIDED,
        TOP_INSIDE,
        BOTTOM_INSIDE
    }

    public XAxis() {
        this.f13936B = 1;
        this.f13937C = 1;
        this.f13938D = 1;
        this.f13939E = 1;
        this.f13940F = 0.0f;
        this.f13941G = false;
        this.f13942H = XAxisPosition.TOP;
        this.x = C3283i.m15928a(4.0f);
    }

    /* renamed from: B */
    public XAxisPosition m15381B() {
        return this.f13942H;
    }

    /* renamed from: a */
    public void m15384a(XAxisPosition xAxisPosition) {
        this.f13942H = xAxisPosition;
    }

    /* renamed from: C */
    public float m15382C() {
        return this.f13940F;
    }

    /* renamed from: D */
    public boolean m15383D() {
        return this.f13941G;
    }
}
