package com.twitter.sdk.android.core;

import io.fabric.sdk.android.services.common.C4878j;
import io.fabric.sdk.android.services.common.C4891p;
import java.util.List;
import retrofit.client.Header;

/* compiled from: TwitterRateLimit */
/* renamed from: com.twitter.sdk.android.core.p */
class C4658p {
    /* renamed from: a */
    private final long f16409a;
    /* renamed from: b */
    private int f16410b;
    /* renamed from: c */
    private int f16411c;
    /* renamed from: d */
    private long f16412d;

    C4658p(List<Header> list) {
        this(list, new C4891p());
    }

    C4658p(List<Header> list, C4878j c4878j) {
        if (list == null) {
            throw new IllegalArgumentException("headers must not be null");
        }
        this.f16409a = c4878j.mo6251a() / 1000;
        for (Header header : list) {
            if ("x-rate-limit-limit".equals(header.getName())) {
                this.f16410b = Integer.valueOf(header.getValue()).intValue();
            } else if ("x-rate-limit-remaining".equals(header.getName())) {
                this.f16411c = Integer.valueOf(header.getValue()).intValue();
            } else if ("x-rate-limit-reset".equals(header.getName())) {
                this.f16412d = Long.valueOf(header.getValue()).longValue();
            }
        }
    }
}
