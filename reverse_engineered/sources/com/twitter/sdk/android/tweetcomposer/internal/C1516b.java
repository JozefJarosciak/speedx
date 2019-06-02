package com.twitter.sdk.android.tweetcomposer.internal;

import com.google.gson.annotations.SerializedName;

/* compiled from: CardData */
/* renamed from: com.twitter.sdk.android.tweetcomposer.internal.b */
public class C1516b {
    /* renamed from: m */
    private static b$b f7122m;
    @SerializedName("twitter:card")
    /* renamed from: a */
    public final String f7123a;
    @SerializedName("twitter:image")
    /* renamed from: b */
    public final String f7124b;
    @SerializedName("twitter:site")
    /* renamed from: c */
    public final String f7125c;
    @SerializedName("twitter:description")
    /* renamed from: d */
    public final String f7126d;
    @SerializedName("twitter:card_data")
    /* renamed from: e */
    public final String f7127e;
    @SerializedName("twitter:text:cta")
    /* renamed from: f */
    public final String f7128f;
    @SerializedName("twitter:cta_key")
    /* renamed from: g */
    public final String f7129g;
    @SerializedName("twitter:text:did_value")
    /* renamed from: h */
    public final String f7130h;
    @SerializedName("twitter:app:id:iphone")
    /* renamed from: i */
    public final String f7131i;
    @SerializedName("twitter:app:id:ipad")
    /* renamed from: j */
    public final String f7132j;
    @SerializedName("twitter:app:id:googleplay")
    /* renamed from: k */
    public final String f7133k;
    @SerializedName("twitter:app:country")
    /* renamed from: l */
    public final String f7134l;

    private C1516b(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.f7123a = str;
        this.f7124b = str2;
        this.f7125c = str3;
        this.f7126d = str4;
        this.f7127e = str5;
        this.f7128f = str6;
        this.f7129g = str7;
        this.f7130h = str8;
        this.f7131i = str9;
        this.f7132j = str10;
        this.f7133k = str11;
        this.f7134l = str12;
    }

    /* renamed from: a */
    b$b m8312a() {
        if (f7122m == null) {
            f7122m = new b$b();
        }
        return f7122m;
    }

    public String toString() {
        return m8312a().a(this);
    }
}
