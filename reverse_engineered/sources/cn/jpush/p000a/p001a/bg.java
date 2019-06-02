package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.bg */
public final class bg extends C4076h {
    /* renamed from: a */
    private static final bg f244a;
    /* renamed from: b */
    private int f245b;
    /* renamed from: c */
    private C4071c f246c;
    /* renamed from: d */
    private C4071c f247d;
    /* renamed from: e */
    private C4071c f248e;
    /* renamed from: f */
    private byte f249f;
    /* renamed from: g */
    private int f250g;

    static {
        bg bgVar = new bg();
        f244a = bgVar;
        bgVar.f246c = C4071c.f14609a;
        bgVar.f247d = C4071c.f14609a;
        bgVar.f248e = C4071c.f14609a;
    }

    private bg() {
        this.f249f = (byte) -1;
        this.f250g = -1;
    }

    private bg(bh bhVar) {
        super((byte) 0);
        this.f249f = (byte) -1;
        this.f250g = -1;
    }

    /* renamed from: a */
    public static bg m553a() {
        return f244a;
    }

    /* renamed from: a */
    public static bh m554a(bg bgVar) {
        return new bh().m572a(bgVar);
    }

    /* renamed from: j */
    public static bh m558j() {
        return new bh();
    }

    /* renamed from: a */
    public final void m559a(C4073e c4073e) {
        m561c();
        if ((this.f245b & 1) == 1) {
            c4073e.a(1, this.f246c);
        }
        if ((this.f245b & 2) == 2) {
            c4073e.a(2, this.f247d);
        }
        if ((this.f245b & 4) == 4) {
            c4073e.a(3, this.f248e);
        }
    }

    /* renamed from: b */
    public final boolean m560b() {
        return (this.f245b & 1) == 1;
    }

    /* renamed from: c */
    public final int m561c() {
        int i = this.f250g;
        if (i == -1) {
            i = 0;
            if ((this.f245b & 1) == 1) {
                i = C4073e.b(1, this.f246c) + 0;
            }
            if ((this.f245b & 2) == 2) {
                i += C4073e.b(2, this.f247d);
            }
            if ((this.f245b & 4) == 4) {
                i += C4073e.b(3, this.f248e);
            }
            this.f250g = i;
        }
        return i;
    }

    /* renamed from: d */
    public final C4071c m562d() {
        return this.f246c;
    }

    /* renamed from: e */
    public final boolean m563e() {
        return (this.f245b & 2) == 2;
    }

    /* renamed from: f */
    public final C4071c m564f() {
        return this.f247d;
    }

    /* renamed from: g */
    public final boolean m565g() {
        return (this.f245b & 4) == 4;
    }

    /* renamed from: h */
    public final C4071c m566h() {
        return this.f248e;
    }

    /* renamed from: i */
    public final boolean m567i() {
        byte b = this.f249f;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f249f = (byte) 1;
            return true;
        }
    }
}
