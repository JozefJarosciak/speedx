package cn.jpush.p005b.p006a.p008b;

import cn.jpush.p000a.p001a.bg;
import cn.jpush.p000a.p001a.bi;
import cn.jpush.p000a.p001a.bj;
import com.google.gson.jpush.annotations.C1479a;
import com.google.protobuf.jpush.C4071c;

/* renamed from: cn.jpush.b.a.b.v */
public class C0548v extends C0527q {
    /* renamed from: a */
    public static final String f1159a = C0548v.class.getName();
    @C1479a
    /* renamed from: b */
    long f1160b;
    @C1479a
    /* renamed from: c */
    String f1161c;

    /* renamed from: a */
    final C0543p mo2235a(long j, String str) {
        bj a = bi.m585j().m599a(this.f1160b);
        if (this.f1161c != null) {
            a.m600a(bg.m558j().m573a(C4071c.a(this.f1161c)).m571a());
        }
        return new C0543p(3, 1, j, str, a.m598a());
    }
}
