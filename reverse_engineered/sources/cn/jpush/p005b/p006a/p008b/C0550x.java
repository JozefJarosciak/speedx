package cn.jpush.p005b.p006a.p008b;

import cn.jpush.p000a.p001a.C0375j;
import cn.jpush.p000a.p001a.C0376k;
import com.google.gson.jpush.annotations.C1479a;
import com.google.protobuf.jpush.C4071c;

/* renamed from: cn.jpush.b.a.b.x */
public class C0550x extends C0527q {
    @C1479a
    /* renamed from: a */
    long f1165a;
    @C1479a
    /* renamed from: b */
    String f1166b;
    @C1479a
    /* renamed from: c */
    String f1167c;

    /* renamed from: a */
    final C0543p mo2235a(long j, String str) {
        C0376k a = C0375j.m778j().m792a(this.f1165a);
        if (this.f1166b != null) {
            a.m794a(C4071c.a(this.f1166b));
        }
        if (this.f1167c != null) {
            a.m797b(C4071c.a(this.f1167c));
        }
        return new C0543p(7, 1, j, str, a.m791a());
    }
}
