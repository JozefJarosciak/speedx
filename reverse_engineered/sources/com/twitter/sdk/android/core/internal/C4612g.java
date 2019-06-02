package com.twitter.sdk.android.core.internal;

import android.text.TextUtils;
import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: TwitterRequestHeaders */
/* renamed from: com.twitter.sdk.android.core.internal.g */
public class C4612g {
    /* renamed from: a */
    private final String f16263a;
    /* renamed from: b */
    private final String f16264b;
    /* renamed from: c */
    private final Map<String, String> f16265c;
    /* renamed from: d */
    private final TwitterAuthConfig f16266d;
    /* renamed from: e */
    private final C1469k f16267e;
    /* renamed from: f */
    private final String f16268f;

    public C4612g(String str, String str2, TwitterAuthConfig twitterAuthConfig, C1469k c1469k, String str3, Map<String, String> map) {
        this.f16263a = str;
        this.f16264b = str2;
        this.f16266d = twitterAuthConfig;
        this.f16267e = c1469k;
        this.f16268f = str3;
        this.f16265c = map;
    }

    /* renamed from: a */
    public final Map<String, String> m18250a() {
        Map hashMap = new HashMap();
        hashMap.putAll(m18251b());
        if (!TextUtils.isEmpty(this.f16268f)) {
            hashMap.put("User-Agent", this.f16268f);
        }
        hashMap.putAll(m18252c());
        return hashMap;
    }

    /* renamed from: b */
    protected Map<String, String> m18251b() {
        return Collections.emptyMap();
    }

    /* renamed from: c */
    public Map<String, String> m18252c() {
        if (this.f16267e == null || this.f16267e.d() == null) {
            return Collections.emptyMap();
        }
        return this.f16267e.d().a(this.f16266d, m18253d(), this.f16264b, m18254e());
    }

    /* renamed from: d */
    protected String m18253d() {
        return this.f16263a;
    }

    /* renamed from: e */
    protected Map<String, String> m18254e() {
        return this.f16265c;
    }
}
