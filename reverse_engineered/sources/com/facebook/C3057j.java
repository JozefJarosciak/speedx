package com.facebook;

import android.os.Handler;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

/* compiled from: ProgressNoopOutputStream */
/* renamed from: com.facebook.j */
class C3057j extends OutputStream implements C3056l {
    /* renamed from: a */
    private final Map<GraphRequest, C3087m> f13655a = new HashMap();
    /* renamed from: b */
    private final Handler f13656b;
    /* renamed from: c */
    private GraphRequest f13657c;
    /* renamed from: d */
    private C3087m f13658d;
    /* renamed from: e */
    private int f13659e;

    C3057j(Handler handler) {
        this.f13656b = handler;
    }

    /* renamed from: a */
    public void mo3698a(GraphRequest graphRequest) {
        this.f13657c = graphRequest;
        this.f13658d = graphRequest != null ? (C3087m) this.f13655a.get(graphRequest) : null;
    }

    /* renamed from: a */
    int m14810a() {
        return this.f13659e;
    }

    /* renamed from: b */
    Map<GraphRequest, C3087m> m14813b() {
        return this.f13655a;
    }

    /* renamed from: a */
    void m14811a(long j) {
        if (this.f13658d == null) {
            this.f13658d = new C3087m(this.f13656b, this.f13657c);
            this.f13655a.put(this.f13657c, this.f13658d);
        }
        this.f13658d.m15002b(j);
        this.f13659e = (int) (((long) this.f13659e) + j);
    }

    public void write(byte[] bArr) {
        m14811a((long) bArr.length);
    }

    public void write(byte[] bArr, int i, int i2) {
        m14811a((long) i2);
    }

    public void write(int i) {
        m14811a(1);
    }
}
