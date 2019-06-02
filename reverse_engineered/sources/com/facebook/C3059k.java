package com.facebook;

import android.os.Handler;
import com.facebook.C2986e.C2980a;
import com.facebook.C2986e.C2985b;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/* compiled from: ProgressOutputStream */
/* renamed from: com.facebook.k */
class C3059k extends FilterOutputStream implements C3056l {
    /* renamed from: a */
    private final Map<GraphRequest, C3087m> f13662a;
    /* renamed from: b */
    private final C2986e f13663b;
    /* renamed from: c */
    private final long f13664c = C1472c.h();
    /* renamed from: d */
    private long f13665d;
    /* renamed from: e */
    private long f13666e;
    /* renamed from: f */
    private long f13667f;
    /* renamed from: g */
    private C3087m f13668g;

    C3059k(OutputStream outputStream, C2986e c2986e, Map<GraphRequest, C3087m> map, long j) {
        super(outputStream);
        this.f13663b = c2986e;
        this.f13662a = map;
        this.f13667f = j;
    }

    /* renamed from: a */
    private void m14816a(long j) {
        if (this.f13668g != null) {
            this.f13668g.m15001a(j);
        }
        this.f13665d += j;
        if (this.f13665d >= this.f13666e + this.f13664c || this.f13665d >= this.f13667f) {
            m14815a();
        }
    }

    /* renamed from: a */
    private void m14815a() {
        if (this.f13665d > this.f13666e) {
            for (C2980a c2980a : this.f13663b.m14487e()) {
                if (c2980a instanceof C2985b) {
                    Handler c = this.f13663b.m14485c();
                    final C2985b c2985b = (C2985b) c2980a;
                    if (c == null) {
                        c2985b.m14475a(this.f13663b, this.f13665d, this.f13667f);
                    } else {
                        c.post(new Runnable(this) {
                            /* renamed from: b */
                            final /* synthetic */ C3059k f13661b;

                            public void run() {
                                c2985b.m14475a(this.f13661b.f13663b, this.f13661b.f13665d, this.f13661b.f13667f);
                            }
                        });
                    }
                }
            }
            this.f13666e = this.f13665d;
        }
    }

    /* renamed from: a */
    public void mo3698a(GraphRequest graphRequest) {
        this.f13668g = graphRequest != null ? (C3087m) this.f13662a.get(graphRequest) : null;
    }

    public void write(byte[] bArr) throws IOException {
        this.out.write(bArr);
        m14816a((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) throws IOException {
        this.out.write(bArr, i, i2);
        m14816a((long) i2);
    }

    public void write(int i) throws IOException {
        this.out.write(i);
        m14816a(1);
    }

    public void close() throws IOException {
        super.close();
        for (C3087m a : this.f13662a.values()) {
            a.m15000a();
        }
        m14815a();
    }
}
