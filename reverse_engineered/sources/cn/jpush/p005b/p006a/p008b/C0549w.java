package cn.jpush.p005b.p006a.p008b;

import cn.jpush.p000a.p001a.C0386u;
import cn.jpush.p000a.p001a.C0387v;
import com.google.gson.jpush.annotations.C1479a;
import com.google.protobuf.jpush.C4071c;

/* renamed from: cn.jpush.b.a.b.w */
public class C0549w extends C0527q {
    @C1479a
    /* renamed from: a */
    long f1162a;
    @C1479a
    /* renamed from: b */
    String f1163b;
    @C1479a
    /* renamed from: c */
    String f1164c;

    /* renamed from: a */
    final C0543p mo2235a(long j, String str) {
        C0387v a = C0386u.m922j().m936a(this.f1162a);
        if (this.f1163b != null) {
            a.m938a(C4071c.a(this.f1163b));
        }
        if (this.f1164c != null) {
            a.m941b(C4071c.a(this.f1164c));
        }
        return new C0543p(12, 1, j, str, a.m935a());
    }
}
