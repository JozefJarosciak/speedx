package com.alipay.sdk.protocol;

import android.text.TextUtils;

/* renamed from: com.alipay.sdk.protocol.a */
public enum C0867a {
    None("none"),
    WapPay("js://wappay"),
    Update("js://update");
    
    /* renamed from: d */
    private String f2156d;

    private C0867a(String str) {
        this.f2156d = str;
    }

    /* renamed from: a */
    public static C0867a m3370a(String str) {
        if (TextUtils.isEmpty(str)) {
            return None;
        }
        C0867a c0867a = None;
        for (C0867a c0867a2 : C0867a.values()) {
            if (str.startsWith(c0867a2.f2156d)) {
                return c0867a2;
            }
        }
        return c0867a;
    }
}
