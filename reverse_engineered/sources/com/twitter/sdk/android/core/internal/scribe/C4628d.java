package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4586l;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.services.common.C4877i;
import io.fabric.sdk.android.services.common.C4883l;
import io.fabric.sdk.android.services.common.C4891p;
import io.fabric.sdk.android.services.p094c.C4861a;
import io.fabric.sdk.android.services.p202b.C4632i;
import io.fabric.sdk.android.services.p202b.C4853a;
import io.fabric.sdk.android.services.p202b.C4859l;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: ScribeClient */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.d */
public class C4628d {
    /* renamed from: a */
    final ConcurrentHashMap<Long, C4642i> f16311a = new ConcurrentHashMap(2);
    /* renamed from: b */
    private final C1468h f16312b;
    /* renamed from: c */
    private final ScheduledExecutorService f16313c;
    /* renamed from: d */
    private final C4635e f16314d;
    /* renamed from: e */
    private final f$a f16315e;
    /* renamed from: f */
    private final TwitterAuthConfig f16316f;
    /* renamed from: g */
    private final List<C4586l<? extends C1469k>> f16317g;
    /* renamed from: h */
    private final SSLSocketFactory f16318h;
    /* renamed from: i */
    private final C4883l f16319i;

    public C4628d(C1468h c1468h, ScheduledExecutorService scheduledExecutorService, C4635e c4635e, f$a f_a, TwitterAuthConfig twitterAuthConfig, List<C4586l<? extends C1469k>> list, SSLSocketFactory sSLSocketFactory, C4883l c4883l) {
        this.f16312b = c1468h;
        this.f16313c = scheduledExecutorService;
        this.f16314d = c4635e;
        this.f16315e = f_a;
        this.f16316f = twitterAuthConfig;
        this.f16317g = list;
        this.f16318h = sSLSocketFactory;
        this.f16319i = c4883l;
    }

    /* renamed from: a */
    public boolean m18311a(C1503f c1503f, long j) {
        try {
            m18309a(j).m18367a(c1503f);
            return true;
        } catch (Throwable e) {
            C4877i.m19158a(this.f16312b.q(), "Failed to scribe event", e);
            return false;
        }
    }

    /* renamed from: a */
    C4642i m18309a(long j) throws IOException {
        if (!this.f16311a.containsKey(Long.valueOf(j))) {
            this.f16311a.putIfAbsent(Long.valueOf(j), m18308d(j));
        }
        return (C4642i) this.f16311a.get(Long.valueOf(j));
    }

    /* renamed from: d */
    private C4642i m18308d(long j) throws IOException {
        Context q = this.f16312b.q();
        C4639h c4639h = new C4639h(q, this.f16315e, new C4891p(), new C4859l(q, new C4861a(this.f16312b).m19105a(), m18312b(j), m18313c(j)), this.f16314d.f16342g);
        return new C4642i(q, m18310a(j, c4639h), c4639h, this.f16313c);
    }

    /* renamed from: a */
    C4632i<C1503f> m18310a(long j, C4639h c4639h) {
        Context q = this.f16312b.q();
        if (this.f16314d.f16336a) {
            C4877i.m19157a(q, "Scribe enabled");
            return new C4634b(q, this.f16313c, c4639h, this.f16314d, new ScribeFilesSender(q, this.f16314d, j, this.f16316f, this.f16317g, this.f16318h, this.f16313c, this.f16319i));
        }
        C4877i.m19157a(q, "Scribe disabled");
        return new C4853a();
    }

    /* renamed from: b */
    String m18312b(long j) {
        return j + "_se.tap";
    }

    /* renamed from: c */
    String m18313c(long j) {
        return j + "_se_to_send";
    }
}
