package io.fabric.sdk.android.services.common;

import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.services.network.C4924c;
import io.fabric.sdk.android.services.network.HttpMethod;
import io.fabric.sdk.android.services.network.HttpRequest;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Pattern;

/* compiled from: AbstractSpiCall */
/* renamed from: io.fabric.sdk.android.services.common.a */
public abstract class C4864a {
    /* renamed from: b */
    private static final Pattern f17142b = Pattern.compile("http(s?)://[^\\/]+", 2);
    /* renamed from: a */
    protected final C1468h f17143a;
    /* renamed from: c */
    private final String f17144c;
    /* renamed from: d */
    private final C4924c f17145d;
    /* renamed from: e */
    private final HttpMethod f17146e;
    /* renamed from: f */
    private final String f17147f;

    public C4864a(C1468h c1468h, String str, String str2, C4924c c4924c, HttpMethod httpMethod) {
        if (str2 == null) {
            throw new IllegalArgumentException("url must not be null.");
        } else if (c4924c == null) {
            throw new IllegalArgumentException("requestFactory must not be null.");
        } else {
            this.f17143a = c1468h;
            this.f17147f = str;
            this.f17144c = m19112a(str2);
            this.f17145d = c4924c;
            this.f17146e = httpMethod;
        }
    }

    /* renamed from: a */
    protected String m19114a() {
        return this.f17144c;
    }

    /* renamed from: b */
    protected HttpRequest m19115b() {
        return m19113a(Collections.emptyMap());
    }

    /* renamed from: a */
    protected HttpRequest m19113a(Map<String, String> map) {
        return this.f17145d.mo6260a(this.f17146e, m19114a(), map).m19322a(false).m19314a(10000).m19317a("User-Agent", "Crashlytics Android SDK/" + this.f17143a.c()).m19317a("X-CRASHLYTICS-DEVELOPER-TOKEN", "470fa2b4ae81cd56ecbcda9735803434cec591fa");
    }

    /* renamed from: a */
    private String m19112a(String str) {
        if (C4877i.m19167b(this.f17147f)) {
            return str;
        }
        return f17142b.matcher(str).replaceFirst(this.f17147f);
    }
}
