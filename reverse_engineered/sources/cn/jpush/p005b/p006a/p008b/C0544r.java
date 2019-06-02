package cn.jpush.p005b.p006a.p008b;

import cn.jpush.p000a.p001a.bl;
import cn.jpush.p000a.p001a.bm;
import com.google.gson.jpush.annotations.C1479a;
import com.google.protobuf.jpush.C4071c;

/* renamed from: cn.jpush.b.a.b.r */
public class C0544r extends C0527q {
    @C1479a
    /* renamed from: a */
    String f1152a;
    @C1479a
    /* renamed from: b */
    String f1153b;
    @C1479a
    /* renamed from: c */
    int f1154c;
    @C1479a
    /* renamed from: d */
    String f1155d;

    /* renamed from: a */
    final C0543p mo2235a(long j, String str) {
        bm a = bl.m614l().m630a(this.f1154c);
        if (this.f1152a != null) {
            a.m632a(C4071c.a(this.f1152a));
        }
        if (this.f1153b != null) {
            a.m635b(C4071c.a(this.f1153b));
        }
        if (this.f1155d != null) {
            a.m637c(C4071c.a(this.f1155d));
        }
        return new C0543p(1, 1, j, str, a.m629a());
    }
}
