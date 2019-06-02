package com.alipay.sdk.auth;

public class APAuthInfo {
    /* renamed from: a */
    private String f1987a;
    /* renamed from: b */
    private String f1988b;
    /* renamed from: c */
    private String f1989c;
    /* renamed from: d */
    private String f1990d;

    public APAuthInfo(String str, String str2, String str3) {
        this(str, str2, str3, null);
    }

    public APAuthInfo(String str, String str2, String str3, String str4) {
        this.f1987a = str;
        this.f1988b = str2;
        this.f1990d = str3;
        this.f1989c = str4;
    }

    public String getAppId() {
        return this.f1987a;
    }

    public String getProductId() {
        return this.f1988b;
    }

    public String getPid() {
        return this.f1989c;
    }

    public String getRedirectUri() {
        return this.f1990d;
    }
}
