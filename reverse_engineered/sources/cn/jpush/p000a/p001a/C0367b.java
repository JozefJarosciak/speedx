package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.b */
public final class C0367b extends C4076h {
    /* renamed from: a */
    private static final C0367b f191a;
    /* renamed from: b */
    private List<Long> f192b;
    /* renamed from: c */
    private byte f193c;
    /* renamed from: d */
    private int f194d;

    static {
        C0367b c0367b = new C0367b();
        f191a = c0367b;
        c0367b.f192b = Collections.emptyList();
    }

    private C0367b() {
        this.f193c = (byte) -1;
        this.f194d = -1;
    }

    private C0367b(C0368c c0368c) {
        super((byte) 0);
        this.f193c = (byte) -1;
        this.f194d = -1;
    }

    /* renamed from: a */
    public static C0367b m438a() {
        return f191a;
    }

    /* renamed from: a */
    public static C0368c m439a(C0367b c0367b) {
        return new C0368c().m687a(c0367b);
    }

    /* renamed from: d */
    public static C0368c m442d() {
        return new C0368c();
    }

    /* renamed from: a */
    public final void m443a(C4073e c4073e) {
        m445c();
        for (int i = 0; i < this.f192b.size(); i++) {
            c4073e.a(1, ((Long) this.f192b.get(i)).longValue());
        }
    }

    /* renamed from: b */
    public final boolean m444b() {
        byte b = this.f193c;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f193c = (byte) 1;
            return true;
        }
    }

    /* renamed from: c */
    public final int m445c() {
        int i = this.f194d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (i = 0; i < this.f192b.size(); i++) {
            i2 += C4073e.a(((Long) this.f192b.get(i)).longValue());
        }
        int size = (i2 + 0) + (this.f192b.size() * 1);
        this.f194d = size;
        return size;
    }
}
