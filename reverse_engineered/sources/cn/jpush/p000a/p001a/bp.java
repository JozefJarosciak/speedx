package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4071c;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;

/* renamed from: cn.jpush.a.a.bp */
public final class bp extends C4076h {
    /* renamed from: a */
    private static final bp f286a;
    /* renamed from: b */
    private int f287b;
    /* renamed from: c */
    private C4071c f288c;
    /* renamed from: d */
    private byte f289d;
    /* renamed from: e */
    private int f290e;

    static {
        bp bpVar = new bp();
        f286a = bpVar;
        bpVar.f288c = C4071c.f14609a;
    }

    private bp() {
        this.f289d = (byte) -1;
        this.f290e = -1;
    }

    private bp(bq bqVar) {
        super((byte) 0);
        this.f289d = (byte) -1;
        this.f290e = -1;
    }

    /* renamed from: a */
    public static bp m662a() {
        return f286a;
    }

    /* renamed from: a */
    public static bq m663a(bp bpVar) {
        return new bq().m675a(bpVar);
    }

    /* renamed from: f */
    public static bq m665f() {
        return new bq();
    }

    /* renamed from: a */
    public final void m666a(C4073e c4073e) {
        m668c();
        if ((this.f287b & 1) == 1) {
            c4073e.a(1, this.f288c);
        }
    }

    /* renamed from: b */
    public final boolean m667b() {
        return (this.f287b & 1) == 1;
    }

    /* renamed from: c */
    public final int m668c() {
        int i = this.f290e;
        if (i == -1) {
            i = 0;
            if ((this.f287b & 1) == 1) {
                i = C4073e.b(1, this.f288c) + 0;
            }
            this.f290e = i;
        }
        return i;
    }

    /* renamed from: d */
    public final C4071c m669d() {
        return this.f288c;
    }

    /* renamed from: e */
    public final boolean m670e() {
        byte b = this.f289d;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f289d = (byte) 1;
            return true;
        }
    }
}
