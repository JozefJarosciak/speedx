package com.facebook;

import android.os.Handler;
import com.facebook.GraphRequest.C2942b;
import com.facebook.GraphRequest.C2951e;

/* compiled from: RequestProgress */
/* renamed from: com.facebook.m */
class C3087m {
    /* renamed from: a */
    private final GraphRequest f13745a;
    /* renamed from: b */
    private final Handler f13746b;
    /* renamed from: c */
    private final long f13747c = C1472c.h();
    /* renamed from: d */
    private long f13748d;
    /* renamed from: e */
    private long f13749e;
    /* renamed from: f */
    private long f13750f;

    C3087m(Handler handler, GraphRequest graphRequest) {
        this.f13745a = graphRequest;
        this.f13746b = handler;
    }

    /* renamed from: a */
    void m15001a(long j) {
        this.f13748d += j;
        if (this.f13748d >= this.f13749e + this.f13747c || this.f13748d >= this.f13750f) {
            m15000a();
        }
    }

    /* renamed from: b */
    void m15002b(long j) {
        this.f13750f += j;
    }

    /* renamed from: a */
    void m15000a() {
        if (this.f13748d > this.f13749e) {
            C2942b g = this.f13745a.m14382g();
            if (this.f13750f > 0 && (g instanceof C2951e)) {
                final long j = this.f13748d;
                final long j2 = this.f13750f;
                final C2951e c2951e = (C2951e) g;
                if (this.f13746b == null) {
                    c2951e.m14319a(j, j2);
                } else {
                    this.f13746b.post(new Runnable(this) {
                        /* renamed from: d */
                        final /* synthetic */ C3087m f13744d;

                        public void run() {
                            c2951e.m14319a(j, j2);
                        }
                    });
                }
                this.f13749e = this.f13748d;
            }
        }
    }
}
