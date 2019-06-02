package com.facebook.login;

public enum LoginBehavior {
    NATIVE_WITH_FALLBACK(true, true, false, true, true),
    NATIVE_ONLY(true, false, false, false, true),
    WEB_ONLY(false, true, false, true, false),
    WEB_VIEW_ONLY(false, true, false, false, false),
    DEVICE_AUTH(false, false, true, false, false);
    
    private final boolean allowsCustomTabAuth;
    private final boolean allowsDeviceAuth;
    private final boolean allowsFacebookLiteAuth;
    private final boolean allowsKatanaAuth;
    private final boolean allowsWebViewAuth;

    private LoginBehavior(boolean z, boolean z2, boolean z3, boolean z4, boolean z5) {
        this.allowsKatanaAuth = z;
        this.allowsWebViewAuth = z2;
        this.allowsDeviceAuth = z3;
        this.allowsCustomTabAuth = z4;
        this.allowsFacebookLiteAuth = z5;
    }

    /* renamed from: a */
    boolean m14913a() {
        return this.allowsKatanaAuth;
    }

    /* renamed from: b */
    boolean m14914b() {
        return this.allowsWebViewAuth;
    }

    /* renamed from: c */
    boolean m14915c() {
        return this.allowsDeviceAuth;
    }

    /* renamed from: d */
    boolean m14916d() {
        return this.allowsCustomTabAuth;
    }

    /* renamed from: e */
    boolean m14917e() {
        return this.allowsFacebookLiteAuth;
    }
}
