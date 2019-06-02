package com.digits.sdk.android;

import com.google.gson.annotations.SerializedName;

/* compiled from: DigitsSessionResponse */
class aq {
    @SerializedName("oauth_token")
    /* renamed from: a */
    public String f6879a;
    @SerializedName("oauth_token_secret")
    /* renamed from: b */
    public String f6880b;
    @SerializedName("screen_name")
    /* renamed from: c */
    public String f6881c;
    @SerializedName("user_id")
    /* renamed from: d */
    public long f6882d;

    aq() {
    }

    /* renamed from: a */
    public boolean m8112a() {
        return this.f6879a == null && this.f6880b == null && this.f6881c == null && this.f6882d == 0;
    }
}
