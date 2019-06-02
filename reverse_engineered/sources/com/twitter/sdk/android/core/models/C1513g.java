package com.twitter.sdk.android.core.models;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* compiled from: Tweet */
/* renamed from: com.twitter.sdk.android.core.models.g */
public class C1513g {
    @SerializedName("withheld_copyright")
    /* renamed from: A */
    public final boolean f7091A;
    @SerializedName("withheld_in_countries")
    /* renamed from: B */
    public final List<String> f7092B;
    @SerializedName("withheld_scope")
    /* renamed from: C */
    public final String f7093C;
    @SerializedName("coordinates")
    /* renamed from: a */
    public final C4646b f7094a;
    @SerializedName("created_at")
    /* renamed from: b */
    public final String f7095b;
    @SerializedName("current_user_retweet")
    /* renamed from: c */
    public final Object f7096c;
    @SerializedName("entities")
    /* renamed from: d */
    public final C4653i f7097d;
    @SerializedName("extended_entities")
    /* renamed from: e */
    public final C4653i f7098e;
    @SerializedName("favorite_count")
    /* renamed from: f */
    public final Integer f7099f;
    @SerializedName("favorited")
    /* renamed from: g */
    public final boolean f7100g;
    @SerializedName("filter_level")
    /* renamed from: h */
    public final String f7101h;
    @SerializedName("id")
    /* renamed from: i */
    public final long f7102i;
    @SerializedName("id_str")
    /* renamed from: j */
    public final String f7103j;
    @SerializedName("in_reply_to_screen_name")
    /* renamed from: k */
    public final String f7104k;
    @SerializedName("in_reply_to_status_id")
    /* renamed from: l */
    public final long f7105l;
    @SerializedName("in_reply_to_status_id_str")
    /* renamed from: m */
    public final String f7106m;
    @SerializedName("in_reply_to_user_id")
    /* renamed from: n */
    public final long f7107n;
    @SerializedName("in_reply_to_user_id_str")
    /* renamed from: o */
    public final String f7108o;
    @SerializedName("lang")
    /* renamed from: p */
    public final String f7109p;
    @SerializedName("place")
    /* renamed from: q */
    public final C4647d f7110q;
    @SerializedName("possibly_sensitive")
    /* renamed from: r */
    public final boolean f7111r;
    @SerializedName("scopes")
    /* renamed from: s */
    public final Object f7112s;
    @SerializedName("retweet_count")
    /* renamed from: t */
    public final int f7113t;
    @SerializedName("retweeted")
    /* renamed from: u */
    public final boolean f7114u;
    @SerializedName("retweeted_status")
    /* renamed from: v */
    public final C1513g f7115v;
    @SerializedName("source")
    /* renamed from: w */
    public final String f7116w;
    @SerializedName("text")
    /* renamed from: x */
    public final String f7117x;
    @SerializedName("truncated")
    /* renamed from: y */
    public final boolean f7118y;
    @SerializedName("user")
    /* renamed from: z */
    public final User f7119z;

    public C1513g(C4646b c4646b, String str, Object obj, C4653i c4653i, C4653i c4653i2, Integer num, boolean z, String str2, long j, String str3, String str4, long j2, String str5, long j3, String str6, String str7, C4647d c4647d, boolean z2, Object obj2, int i, boolean z3, C1513g c1513g, String str8, String str9, boolean z4, User user, boolean z5, List<String> list, String str10) {
        this.f7094a = c4646b;
        this.f7095b = str;
        this.f7096c = obj;
        this.f7097d = c4653i;
        this.f7098e = c4653i2;
        this.f7099f = num;
        this.f7100g = z;
        this.f7101h = str2;
        this.f7102i = j;
        this.f7103j = str3;
        this.f7104k = str4;
        this.f7105l = j2;
        this.f7106m = str5;
        this.f7107n = j3;
        this.f7108o = str6;
        this.f7109p = str7;
        this.f7110q = c4647d;
        this.f7111r = z2;
        this.f7112s = obj2;
        this.f7113t = i;
        this.f7114u = z3;
        this.f7115v = c1513g;
        this.f7116w = str8;
        this.f7117x = str9;
        this.f7118y = z4;
        this.f7119z = user;
        this.f7091A = z5;
        this.f7092B = list;
        this.f7093C = str10;
    }

    /* renamed from: a */
    public long m8310a() {
        return this.f7102i;
    }

    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof C1513g)) {
            return false;
        }
        if (this.f7102i == ((C1513g) obj).f7102i) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (int) this.f7102i;
    }
}
