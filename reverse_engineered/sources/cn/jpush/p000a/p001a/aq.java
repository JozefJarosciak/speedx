package cn.jpush.p000a.p001a;

import com.google.protobuf.jpush.C4067k;
import com.google.protobuf.jpush.C4073e;
import com.google.protobuf.jpush.C4076h;
import java.util.Collections;
import java.util.List;

/* renamed from: cn.jpush.a.a.aq */
public final class aq extends C4076h {
    /* renamed from: a */
    private static final aq f147a;
    /* renamed from: b */
    private List<ao> f148b;
    /* renamed from: c */
    private byte f149c;
    /* renamed from: d */
    private int f150d;

    static {
        aq aqVar = new aq();
        f147a = aqVar;
        aqVar.f148b = Collections.emptyList();
    }

    private aq() {
        this.f149c = (byte) -1;
        this.f150d = -1;
    }

    private aq(ar arVar) {
        super((byte) 0);
        this.f149c = (byte) -1;
        this.f150d = -1;
    }

    /* renamed from: a */
    public static aq m322a() {
        return f147a;
    }

    /* renamed from: a */
    public static ar m323a(aq aqVar) {
        return new ar().m337a(aqVar);
    }

    /* renamed from: e */
    public static ar m326e() {
        return new ar();
    }

    /* renamed from: a */
    public final void m327a(C4073e c4073e) {
        m329c();
        for (int i = 0; i < this.f148b.size(); i++) {
            c4073e.a(1, (C4067k) this.f148b.get(i));
        }
    }

    /* renamed from: b */
    public final List<ao> m328b() {
        return this.f148b;
    }

    /* renamed from: c */
    public final int m329c() {
        int i = this.f150d;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.f148b.size(); i2++) {
                i += C4073e.b(1, (C4067k) this.f148b.get(i2));
            }
            this.f150d = i;
        }
        return i;
    }

    /* renamed from: d */
    public final boolean m330d() {
        byte b = this.f149c;
        if (b != (byte) -1) {
            return b == (byte) 1;
        } else {
            this.f149c = (byte) 1;
            return true;
        }
    }
}
