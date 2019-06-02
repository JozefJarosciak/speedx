package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.x */
public final class C0389x extends C4076h {
    /* renamed from: a */
    private static final C0389x f389a;
    /* renamed from: b */
    private List<Integer> f390b;
    /* renamed from: c */
    private byte f391c;
    /* renamed from: d */
    private int f392d;

    static {
        C0389x c0389x = new C0389x();
        f389a = c0389x;
        c0389x.f390b = Collections.emptyList();
    }

    private C0389x() {
        this.f391c = (byte) -1;
        this.f392d = -1;
    }

    private C0389x(C0390y c0390y) {
        super((byte) 0);
        this.f391c = (byte) -1;
        this.f392d = -1;
    }

    /* renamed from: a */
    public static C0389x m945a() {
        return f389a;
    }

    /* renamed from: a */
    public static C0390y m946a(C0389x c0389x) {
        return new C0390y().m957a(c0389x);
    }

    /* renamed from: b */
    public static C0390y m948b() {
        return new C0390y();
    }

    /* renamed from: a */
    public final void m950a(C4073e c4073e) {
        m951c();
        for (int i = 0; i < this.f390b.size(); i++) {
            c4073e.a(1, ((Integer) this.f390b.get(i)).intValue());
        }
    }

    /* renamed from: c */
    public final int m951c() {
        int i = this.f392d;
        if (i != -1) {
            return i;
        }
        int i2 = 0;
        for (i = 0; i < this.f390b.size(); i++) {
            i2 += C4073e.a(((Integer) this.f390b.get(i)).intValue());
        }
        int size = (i2 + 0) + (this.f390b.size() * 1);
        this.f392d = size;
        return size;
    }
}
