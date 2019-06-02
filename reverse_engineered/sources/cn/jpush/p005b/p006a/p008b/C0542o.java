package cn.jpush.p005b.p006a.p008b;

import cn.jpush.p000a.p001a.be;
import cn.jpush.p000a.p001a.bf;
import cn.jpush.p000a.p001a.bg;
import com.google.gson.jpush.annotations.C1479a;
import com.google.protobuf.jpush.C4071c;

/* renamed from: cn.jpush.b.a.b.o */
public class C0542o extends C0527q {
    @C1479a
    /* renamed from: a */
    long f1141a;
    @C1479a
    /* renamed from: b */
    String f1142b;

    /* renamed from: a */
    final C0543p mo2235a(long j, String str) {
        bf a = be.m530j().m544a(this.f1141a);
        if (this.f1142b != null) {
            a.m546a(bg.m558j().m573a(C4071c.a(this.f1142b)).m571a());
        }
        return new C0543p(4, 1, j, str, a.m543a());
    }
}
