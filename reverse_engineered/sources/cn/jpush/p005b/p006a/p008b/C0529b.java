package cn.jpush.p005b.p006a.p008b;

import cn.jpush.p000a.p001a.C0369d;
import cn.jpush.p000a.p001a.C0370e;
import com.google.gson.jpush.annotations.C1479a;
import com.google.protobuf.jpush.C4071c;

/* renamed from: cn.jpush.b.a.b.b */
public class C0529b extends C0527q {
    @C1479a
    /* renamed from: a */
    long f1121a;
    @C1479a
    /* renamed from: b */
    String f1122b;
    @C1479a
    /* renamed from: c */
    String f1123c;
    @C1479a
    /* renamed from: d */
    int f1124d;
    @C1479a
    /* renamed from: e */
    String f1125e;

    /* renamed from: a */
    final C0543p mo2235a(long j, String str) {
        C0370e a = C0369d.m702n().m721a(this.f1121a).m720a(this.f1124d);
        if (this.f1122b != null) {
            a.m723a(C4071c.a(this.f1122b));
        }
        if (this.f1123c != null) {
            a.m726b(C4071c.a(this.f1123c));
        }
        if (this.f1125e != null) {
            a.m728c(C4071c.a(this.f1125e));
        }
        return new C0543p(5, 1, j, str, a.m719a());
    }
}
