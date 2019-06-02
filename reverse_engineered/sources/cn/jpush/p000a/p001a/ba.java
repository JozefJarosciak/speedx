package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.ba */
public final class ba extends C4076h {
    /* renamed from: a */
    private static final ba f195a;
    /* renamed from: b */
    private int f196b;
    /* renamed from: c */
    private long f197c;
    /* renamed from: d */
    private int f198d;
    /* renamed from: e */
    private long f199e;
    /* renamed from: f */
    private long f200f;
    /* renamed from: g */
    private List<Long> f201g;
    /* renamed from: h */
    private C4071c f202h;
    /* renamed from: i */
    private int f203i;
    /* renamed from: j */
    private int f204j;
    /* renamed from: k */
    private int f205k;
    /* renamed from: l */
    private byte f206l;
    /* renamed from: m */
    private int f207m;

    static {
        ba baVar = new ba();
        f195a = baVar;
        baVar.f197c = 0;
        baVar.f198d = 0;
        baVar.f199e = 0;
        baVar.f200f = 0;
        baVar.f201g = Collections.emptyList();
        baVar.f202h = C4071c.f14609a;
        baVar.f203i = 0;
        baVar.f204j = 0;
        baVar.f205k = 0;
    }

    private ba() {
        this.f206l = (byte) -1;
        this.f207m = -1;
    }

    private ba(bb bbVar) {
        super((byte) 0);
        this.f206l = (byte) -1;
        this.f207m = -1;
    }

    /* renamed from: a */
    public static ba m448a() {
        return f195a;
    }

    /* renamed from: a */
    public static bb m449a(ba baVar) {
        return new bb().m486a(baVar);
    }

    /* renamed from: t */
    public static bb m459t() {
        return new bb();
    }

    /* renamed from: a */
    public final void m460a(C4073e c4073e) {
        m462c();
        if ((this.f196b & 1) == 1) {
            c4073e.a(1, this.f197c);
        }
        if ((this.f196b & 2) == 2) {
            c4073e.a(2, this.f198d);
        }
        if ((this.f196b & 4) == 4) {
            c4073e.a(3, this.f199e);
        }
        if ((this.f196b & 8) == 8) {
            c4073e.a(4, this.f200f);
        }
        for (int i = 0; i < this.f201g.size(); i++) {
            c4073e.a(5, ((Long) this.f201g.get(i)).longValue());
        }
        if ((this.f196b & 16) == 16) {
            c4073e.a(6, this.f202h);
        }
        if ((this.f196b & 32) == 32) {
            c4073e.b(7, this.f203i);
        }
        if ((this.f196b & 64) == 64) {
            c4073e.a(8, this.f204j);
        }
        if ((this.f196b & 128) == 128) {
            c4073e.a(9, this.f205k);
        }
    }

    /* renamed from: b */
    public final boolean m461b() {
        return (this.f196b & 1) == 1;
    }

    /* renamed from: c */
    public final int m462c() {
        int i = 0;
        int i2 = this.f207m;
        if (i2 == -1) {
            i2 = (this.f196b & 1) == 1 ? C4073e.b(1, this.f197c) + 0 : 0;
            if ((this.f196b & 2) == 2) {
                i2 += C4073e.c(2, this.f198d);
            }
            if ((this.f196b & 4) == 4) {
                i2 += C4073e.b(3, this.f199e);
            }
            int b = (this.f196b & 8) == 8 ? i2 + C4073e.b(4, this.f200f) : i2;
            int i3 = 0;
            while (i < this.f201g.size()) {
                i++;
                i3 = C4073e.a(((Long) this.f201g.get(i)).longValue()) + i3;
            }
            i2 = (b + i3) + (this.f201g.size() * 1);
            if ((this.f196b & 16) == 16) {
                i2 += C4073e.b(6, this.f202h);
            }
            if ((this.f196b & 32) == 32) {
                i2 += C4073e.d(7, this.f203i);
            }
            if ((this.f196b & 64) == 64) {
                i2 += C4073e.c(8, this.f204j);
            }
            if ((this.f196b & 128) == 128) {
                i2 += C4073e.c(9, this.f205k);
            }
            this.f207m = i2;
        }
        return i2;
    }

    /* renamed from: d */
    public final long m463d() {
        return this.f197c;
    }

    /* renamed from: e */
    public final boolean m464e() {
        return (this.f196b & 2) == 2;
    }

    /* renamed from: f */
    public final int m465f() {
        return this.f198d;
    }

    /* renamed from: g */
    public final boolean m466g() {
        return (this.f196b & 4) == 4;
    }

    /* renamed from: h */
    public final long m467h() {
        return this.f199e;
    }

    /* renamed from: i */
    public final boolean m468i() {
        return (this.f196b & 8) == 8;
    }

    /* renamed from: j */
    public final long m469j() {
        return this.f200f;
    }

    /* renamed from: k */
    public final boolean m470k() {
        return (this.f196b & 16) == 16;
    }

    /* renamed from: l */
    public final C4071c m471l() {
        return this.f202h;
    }

    /* renamed from: m */
    public final boolean m472m() {
        return (this.f196b & 32) == 32;
    }

    /* renamed from: n */
    public final int m473n() {
        return this.f203i;
    }

    /* renamed from: o */
    public final boolean m474o() {
        return (this.f196b & 64) == 64;
    }

    /* renamed from: p */
    public final int m475p() {
        return this.f204j;
    }

    /* renamed from: q */
    public final boolean m476q() {
        return (this.f196b & 128) == 128;
    }

    /* renamed from: r */
    public final int m477r() {
        return this.f205k;
    }

    /* renamed from: s */
    public final boolean m478s() {
        byte b = this.f206l;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f206l = (byte) 1;
            return true;
        }
    }
}
