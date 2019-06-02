package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.f */
public final class C0371f extends C4076h {
    /* renamed from: a */
    private static final C0371f f310a;
    /* renamed from: b */
    private List<Long> f311b;
    /* renamed from: c */
    private byte f312c;
    /* renamed from: d */
    private int f313d;

    static {
        C0371f c0371f = new C0371f();
        f310a = c0371f;
        c0371f.f311b = Collections.emptyList();
    }

    private C0371f() {
        this.f312c = (byte) -1;
        this.f313d = -1;
    }

    private C0371f(C0372g c0372g) {
        super((byte) 0);
        this.f312c = (byte) -1;
        this.f313d = -1;
    }

    /* renamed from: a */
    public static C0371f m731a() {
        return f310a;
    }

    /* renamed from: a */
    public static C0372g m732a(C0371f c0371f) {
        return new C0372g().m744a(c0371f);
    }

    /* renamed from: d */
    public static C0372g m735d() {
        return new C0372g();
    }

    /* renamed from: a */
    public final void m736a(C4073e c4073e) {
        m738c();
        for (int i = 0; i < this.f311b.size(); i++) {
            c4073e.a(1, ((Long) this.f311b.get(i)).longValue());
        }
    }

    /* renamed from: b */
    public final boolean m737b() {
        byte b = this.f312c;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f312c = (byte) 1;
            return true;
        }
    }

    /* renamed from: c */
    public final int m738c() {
        int i = this.f313d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (i = 0; i < this.f311b.size(); i++) {
            i2 += C4073e.a(((Long) this.f311b.get(i)).longValue());
        }
        int size = (i2 + 0) + (this.f311b.size() * 1);
        this.f313d = size;
        return size;
    }
}
