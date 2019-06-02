package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.q */
public final class C0382q extends C4076h {
    /* renamed from: a */
    private static final C0382q f360a;
    /* renamed from: b */
    private int f361b;
    /* renamed from: c */
    private long f362c;
    /* renamed from: d */
    private int f363d;
    /* renamed from: e */
    private List<Long> f364e;
    /* renamed from: f */
    private byte f365f;
    /* renamed from: g */
    private int f366g;

    static {
        C0382q c0382q = new C0382q();
        f360a = c0382q;
        c0382q.f362c = 0;
        c0382q.f363d = 0;
        c0382q.f364e = Collections.emptyList();
    }

    private C0382q() {
        this.f365f = (byte) -1;
        this.f366g = -1;
    }

    private C0382q(C0383r c0383r) {
        super((byte) 0);
        this.f365f = (byte) -1;
        this.f366g = -1;
    }

    /* renamed from: a */
    public static C0382q m868a() {
        return f360a;
    }

    /* renamed from: a */
    public static C0383r m869a(C0382q c0382q) {
        return new C0383r().m888a(c0382q);
    }

    /* renamed from: h */
    public static C0383r m873h() {
        return new C0383r();
    }

    /* renamed from: a */
    public final void m874a(C4073e c4073e) {
        m876c();
        if ((this.f361b & 1) == 1) {
            c4073e.a(1, this.f362c);
        }
        if ((this.f361b & 2) == 2) {
            c4073e.a(2, this.f363d);
        }
        for (int i = 0; i < this.f364e.size(); i++) {
            c4073e.a(3, ((Long) this.f364e.get(i)).longValue());
        }
    }

    /* renamed from: b */
    public final boolean m875b() {
        return (this.f361b & 1) == 1;
    }

    /* renamed from: c */
    public final int m876c() {
        int i = 0;
        int i2 = this.f366g;
        if (i2 != -1) {
            return i2;
        }
        i2 = (this.f361b & 1) == 1 ? C4073e.b(1, this.f362c) + 0 : 0;
        int c = (this.f361b & 2) == 2 ? i2 + C4073e.c(2, this.f363d) : i2;
        int i3 = 0;
        while (i < this.f364e.size()) {
            i++;
            i3 = C4073e.a(((Long) this.f364e.get(i)).longValue()) + i3;
        }
        i2 = (c + i3) + (this.f364e.size() * 1);
        this.f366g = i2;
        return i2;
    }

    /* renamed from: d */
    public final long m877d() {
        return this.f362c;
    }

    /* renamed from: e */
    public final boolean m878e() {
        return (this.f361b & 2) == 2;
    }

    /* renamed from: f */
    public final int m879f() {
        return this.f363d;
    }

    /* renamed from: g */
    public final boolean m880g() {
        byte b = this.f365f;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f365f = (byte) 1;
            return true;
        }
    }
}
