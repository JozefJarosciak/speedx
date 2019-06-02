package io.fabric.sdk.android;

import java.util.concurrent.CountDownLatch;

/* compiled from: Fabric */
class c$2 implements C4837f {
    /* renamed from: a */
    final CountDownLatch f17074a = new CountDownLatch(this.f17075b);
    /* renamed from: b */
    final /* synthetic */ int f17075b;
    /* renamed from: c */
    final /* synthetic */ C1520c f17076c;

    c$2(C1520c c1520c, int i) {
        this.f17076c = c1520c;
        this.f17075b = i;
    }

    /* renamed from: a */
    public void mo6226a(Object obj) {
        this.f17074a.countDown();
        if (this.f17074a.getCount() == 0) {
            C1520c.a(this.f17076c).set(true);
            C1520c.b(this.f17076c).mo6226a(this.f17076c);
        }
    }

    /* renamed from: a */
    public void mo6225a(Exception exception) {
        C1520c.b(this.f17076c).mo6225a(exception);
    }
}
