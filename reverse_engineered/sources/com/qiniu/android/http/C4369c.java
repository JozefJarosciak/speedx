package com.qiniu.android.http;

import com.qiniu.android.http.CancellationHandler.CancellationException;
import com.qiniu.android.p189c.C4334a;
import java.io.IOException;
import okhttp3.C5602x;
import okhttp3.C5608s;
import okio.C5492r;
import okio.C5635d;
import okio.C5637c;
import okio.C5639g;
import okio.C5647m;

/* compiled from: CountingRequestBody */
/* renamed from: com.qiniu.android.http.c */
public class C4369c extends C5602x {
    /* renamed from: a */
    private final C5602x f15160a;
    /* renamed from: b */
    private final C4314e f15161b;
    /* renamed from: c */
    private final CancellationHandler f15162c;

    /* compiled from: CountingRequestBody */
    /* renamed from: com.qiniu.android.http.c$a */
    protected final class C4368a extends C5639g {
        /* renamed from: a */
        final /* synthetic */ C4369c f15158a;
        /* renamed from: b */
        private int f15159b = 0;

        /* compiled from: CountingRequestBody */
        /* renamed from: com.qiniu.android.http.c$a$1 */
        class C43671 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C4368a f15157a;

            C43671(C4368a c4368a) {
                this.f15157a = c4368a;
            }

            public void run() {
                try {
                    this.f15157a.f15158a.f15161b.mo6023a(this.f15157a.f15159b, (int) this.f15157a.f15158a.contentLength());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public C4368a(C4369c c4369c, C5492r c5492r) {
            this.f15158a = c4369c;
            super(c5492r);
        }

        /* renamed from: a */
        public void m17190a(C5637c c5637c, long j) throws IOException {
            if (this.f15158a.f15162c == null && this.f15158a.f15161b == null) {
                super.a(c5637c, j);
            } else if (this.f15158a.f15162c == null || !this.f15158a.f15162c.mo6025a()) {
                super.a(c5637c, j);
                this.f15159b = (int) (((long) this.f15159b) + j);
                if (this.f15158a.f15161b != null) {
                    C4334a.m17117a(new C43671(this));
                }
            } else {
                throw new CancellationException();
            }
        }
    }

    public C4369c(C5602x c5602x, C4314e c4314e, CancellationHandler cancellationHandler) {
        this.f15160a = c5602x;
        this.f15161b = c4314e;
        this.f15162c = cancellationHandler;
    }

    public long contentLength() throws IOException {
        return this.f15160a.contentLength();
    }

    public C5608s contentType() {
        return this.f15160a.contentType();
    }

    public void writeTo(C5635d c5635d) throws IOException {
        C5635d a = C5647m.a(new C4368a(this, c5635d));
        this.f15160a.writeTo(a);
        a.flush();
    }
}
