package com.twitter.sdk.android.core.internal;

import android.app.Activity;
import com.twitter.sdk.android.core.C1469k;
import com.twitter.sdk.android.core.C4586l;
import io.fabric.sdk.android.C4834a;
import io.fabric.sdk.android.C4834a.C4604b;
import io.fabric.sdk.android.services.common.C4891p;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;

/* compiled from: SessionMonitor */
/* renamed from: com.twitter.sdk.android.core.internal.c */
public class C4608c<T extends C1469k> {
    /* renamed from: a */
    protected final C4607a f16256a;
    /* renamed from: b */
    private final C4891p f16257b;
    /* renamed from: c */
    private final C4586l<T> f16258c;
    /* renamed from: d */
    private final ExecutorService f16259d;
    /* renamed from: e */
    private final C4610e f16260e;

    /* compiled from: SessionMonitor */
    /* renamed from: com.twitter.sdk.android.core.internal.c$1 */
    class C46051 extends C4604b {
        /* renamed from: a */
        final /* synthetic */ C4608c f16251a;

        C46051(C4608c c4608c) {
            this.f16251a = c4608c;
        }

        /* renamed from: a */
        public void mo6138a(Activity activity) {
            this.f16251a.m18241a();
        }
    }

    /* compiled from: SessionMonitor */
    /* renamed from: com.twitter.sdk.android.core.internal.c$2 */
    class C46062 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C4608c f16252a;

        C46062(C4608c c4608c) {
            this.f16252a = c4608c;
        }

        public void run() {
            this.f16252a.m18243b();
        }
    }

    /* compiled from: SessionMonitor */
    /* renamed from: com.twitter.sdk.android.core.internal.c$a */
    protected static class C4607a {
        /* renamed from: a */
        public boolean f16253a;
        /* renamed from: b */
        public long f16254b;
        /* renamed from: c */
        private final Calendar f16255c = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

        /* renamed from: a */
        public synchronized boolean m18239a(long j) {
            boolean z = true;
            synchronized (this) {
                boolean z2 = j - this.f16254b > 21600000;
                boolean z3;
                if (m18238a(j, this.f16254b)) {
                    z3 = false;
                } else {
                    z3 = true;
                }
                if (this.f16253a || !(z2 || r2)) {
                    z = false;
                } else {
                    this.f16253a = true;
                }
            }
            return z;
        }

        /* renamed from: b */
        public synchronized void m18240b(long j) {
            this.f16253a = false;
            this.f16254b = j;
        }

        /* renamed from: a */
        private boolean m18238a(long j, long j2) {
            this.f16255c.setTimeInMillis(j);
            int i = this.f16255c.get(6);
            int i2 = this.f16255c.get(1);
            this.f16255c.setTimeInMillis(j2);
            int i3 = this.f16255c.get(6);
            int i4 = this.f16255c.get(1);
            if (i == i3 && i2 == i4) {
                return true;
            }
            return false;
        }
    }

    public C4608c(C4586l<T> c4586l, ExecutorService executorService, C4610e c4610e) {
        this(c4586l, new C4891p(), executorService, new C4607a(), c4610e);
    }

    C4608c(C4586l<T> c4586l, C4891p c4891p, ExecutorService executorService, C4607a c4607a, C4610e c4610e) {
        this.f16257b = c4891p;
        this.f16258c = c4586l;
        this.f16259d = executorService;
        this.f16256a = c4607a;
        this.f16260e = c4610e;
    }

    /* renamed from: a */
    public void m18242a(C4834a c4834a) {
        c4834a.m18983a(new C46051(this));
    }

    /* renamed from: a */
    public void m18241a() {
        Object obj = (this.f16258c.mo6132b() == null || !this.f16256a.m18239a(this.f16257b.mo6251a())) ? null : 1;
        if (obj != null) {
            this.f16259d.submit(new C46062(this));
        }
    }

    /* renamed from: b */
    protected void m18243b() {
        for (C1469k a : this.f16258c.mo6133c().values()) {
            this.f16260e.mo6139a(a);
        }
        this.f16256a.m18240b(this.f16257b.mo6251a());
    }
}
