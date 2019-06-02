package cn.jpush.p005b.p006a.p008b;

import cn.jpush.p000a.p001a.C0380o;
import cn.jpush.p000a.p001a.C0381p;
import com.google.gson.jpush.annotations.C1479a;
import com.google.protobuf.jpush.C4071c;

/* renamed from: cn.jpush.b.a.b.g */
public class C0534g extends C0527q {
    @C1479a
    /* renamed from: a */
    String f1130a;
    @C1479a
    /* renamed from: b */
    String f1131b;
    @C1479a
    /* renamed from: c */
    int f1132c;
    @C1479a
    /* renamed from: d */
    int f1133d;

    /* renamed from: a */
    protected final C0543p mo2235a(long j, String str) {
        C0381p a = C0380o.m838n().m861b(this.f1132c).m856a(this.f1133d);
        if (this.f1130a != null) {
            a.m858a(C4071c.a(this.f1130a));
        }
        if (this.f1131b != null) {
            a.m862b(C4071c.a(this.f1131b));
        }
        return new C0543p(8, 1, j, str, a.m855a());
    }
}
