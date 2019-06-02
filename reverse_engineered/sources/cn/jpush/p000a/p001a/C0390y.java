package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.y */
public final class C0390y extends C4077i<C0389x, C0390y> {
    /* renamed from: a */
    private int f393a;
    /* renamed from: b */
    private List<Integer> f394b = Collections.emptyList();

    private C0390y() {
    }

    /* renamed from: c */
    private C0390y m953c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    m955f();
                    this.f394b.add(Integer.valueOf(c4072d.c()));
                    continue;
                case 10:
                    a = c4072d.c(c4072d.f());
                    while (c4072d.g() > 0) {
                        int c = c4072d.c();
                        m955f();
                        this.f394b.add(Integer.valueOf(c));
                    }
                    c4072d.d(a);
                    continue;
                default:
                    if (!c4072d.b(a)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    /* renamed from: e */
    private C0390y m954e() {
        return new C0390y().m957a(m956a());
    }

    /* renamed from: f */
    private void m955f() {
        if ((this.f393a & 1) != 1) {
            this.f394b = new ArrayList(this.f394b);
            this.f393a |= 1;
        }
    }

    /* renamed from: a */
    public final C0389x m956a() {
        C0389x c0389x = new C0389x();
        if ((this.f393a & 1) == 1) {
            this.f394b = Collections.unmodifiableList(this.f394b);
            this.f393a &= -2;
        }
        c0389x.f390b = this.f394b;
        return c0389x;
    }

    /* renamed from: a */
    public final C0390y m957a(C0389x c0389x) {
        if (!(c0389x == C0389x.m945a() || c0389x.f390b.isEmpty())) {
            if (this.f394b.isEmpty()) {
                this.f394b = c0389x.f390b;
                this.f393a &= -2;
            } else {
                m955f();
                this.f394b.addAll(c0389x.f390b);
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m958a(C4072d c4072d, C4075g c4075g) {
        return m953c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m959b(C4072d c4072d, C4075g c4075g) {
        return m953c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m960c() {
        return m954e();
    }

    public final /* synthetic */ Object clone() {
        return m954e();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m961d() {
        return m954e();
    }
}
