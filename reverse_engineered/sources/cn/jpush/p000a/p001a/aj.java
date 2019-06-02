package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.aj */
public final class aj extends C4077i<ai, aj> {
    /* renamed from: a */
    private int f107a;
    /* renamed from: b */
    private int f108b;
    /* renamed from: c */
    private long f109c;

    private aj() {
    }

    /* renamed from: c */
    private aj m224c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f107a |= 1;
                    this.f108b = c4072d.c();
                    continue;
                case 16:
                    this.f107a |= 2;
                    this.f109c = c4072d.b();
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

    /* renamed from: f */
    private aj m226f() {
        return new aj().m228a(m230b());
    }

    /* renamed from: a */
    public final ai m227a() {
        ai b = m230b();
        if (b.m223g()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final aj m228a(ai aiVar) {
        if (aiVar != ai.m213a()) {
            if (aiVar.m218b()) {
                int d = aiVar.m220d();
                this.f107a |= 1;
                this.f108b = d;
            }
            if (aiVar.m221e()) {
                long f = aiVar.m222f();
                this.f107a |= 2;
                this.f109c = f;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m229a(C4072d c4072d, C4075g c4075g) {
        return m224c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final ai m230b() {
        int i = 1;
        ai aiVar = new ai();
        int i2 = this.f107a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        aiVar.f103c = this.f108b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        aiVar.f104d = this.f109c;
        aiVar.f102b = i;
        return aiVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m231b(C4072d c4072d, C4075g c4075g) {
        return m224c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m232c() {
        return m226f();
    }

    public final /* synthetic */ Object clone() {
        return m226f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m233d() {
        return m226f();
    }
}
