package com.journeyapps.barcodescanner.camera;

public class CameraSettings {
    /* renamed from: a */
    private int f14705a = -1;
    /* renamed from: b */
    private boolean f14706b = false;
    /* renamed from: c */
    private boolean f14707c = false;
    /* renamed from: d */
    private boolean f14708d = false;
    /* renamed from: e */
    private boolean f14709e = true;
    /* renamed from: f */
    private boolean f14710f = false;
    /* renamed from: g */
    private boolean f14711g = false;
    /* renamed from: h */
    private boolean f14712h = false;
    /* renamed from: i */
    private FocusMode f14713i = FocusMode.AUTO;

    public enum FocusMode {
        AUTO,
        CONTINUOUS,
        INFINITY,
        MACRO
    }

    /* renamed from: a */
    public int m16553a() {
        return this.f14705a;
    }

    /* renamed from: a */
    public void m16554a(int i) {
        this.f14705a = i;
    }

    /* renamed from: b */
    public boolean m16555b() {
        return this.f14706b;
    }

    /* renamed from: c */
    public boolean m16556c() {
        return this.f14707c;
    }

    /* renamed from: d */
    public boolean m16557d() {
        return this.f14711g;
    }

    /* renamed from: e */
    public boolean m16558e() {
        return this.f14708d;
    }

    /* renamed from: f */
    public boolean m16559f() {
        return this.f14709e;
    }

    /* renamed from: g */
    public FocusMode m16560g() {
        return this.f14713i;
    }

    /* renamed from: h */
    public boolean m16561h() {
        return this.f14712h;
    }
}
