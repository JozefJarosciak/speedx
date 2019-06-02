package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4069l;
import com.google.protobuf.jpush.C4070b;
import com.google.protobuf.jpush.C4072d;
import com.google.protobuf.jpush.C4075g;
import com.google.protobuf.jpush.C4077i;
import com.google.protobuf.jpush.C4079m;

/* renamed from: cn.jpush.a.a.ax */
public final class ax extends C4077i<aw, ax> {
    /* renamed from: a */
    private int f177a;
    /* renamed from: b */
    private long f178b;
    /* renamed from: c */
    private long f179c;

    private ax() {
    }

    /* renamed from: c */
    private ax m403c(C4072d c4072d, C4075g c4075g) {
        while (true) {
            int a = c4072d.a();
            switch (a) {
                case 0:
                    break;
                case 8:
                    this.f177a |= 1;
                    this.f178b = c4072d.b();
                    continue;
                case 16:
                    this.f177a |= 2;
                    this.f179c = c4072d.b();
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
    private ax m405f() {
        return new ax().m408a(m410b());
    }

    /* renamed from: a */
    public final aw m406a() {
        aw b = m410b();
        if (b.m402g()) {
            return b;
        }
        throw new C4079m();
    }

    /* renamed from: a */
    public final ax m407a(long j) {
        this.f177a |= 1;
        this.f178b = j;
        return this;
    }

    /* renamed from: a */
    public final ax m408a(aw awVar) {
        if (awVar != aw.m392a()) {
            if (awVar.m397b()) {
                m407a(awVar.m399d());
            }
            if (awVar.m400e()) {
                long f = awVar.m401f();
                this.f177a |= 2;
                this.f179c = f;
            }
        }
        return this;
    }

    /* renamed from: a */
    public final /* synthetic */ C4070b m409a(C4072d c4072d, C4075g c4075g) {
        return m403c(c4072d, c4075g);
    }

    /* renamed from: b */
    public final aw m410b() {
        int i = 1;
        aw awVar = new aw();
        int i2 = this.f177a;
        if ((i2 & 1) != 1) {
            i = 0;
        }
        awVar.f173c = this.f178b;
        if ((i2 & 2) == 2) {
            i |= 2;
        }
        awVar.f174d = this.f179c;
        awVar.f172b = i;
        return awVar;
    }

    /* renamed from: b */
    public final /* synthetic */ C4069l m411b(C4072d c4072d, C4075g c4075g) {
        return m403c(c4072d, c4075g);
    }

    /* renamed from: c */
    public final /* synthetic */ C4077i m412c() {
        return m405f();
    }

    public final /* synthetic */ Object clone() {
        return m405f();
    }

    /* renamed from: d */
    public final /* synthetic */ C4070b m413d() {
        return m405f();
    }
}
