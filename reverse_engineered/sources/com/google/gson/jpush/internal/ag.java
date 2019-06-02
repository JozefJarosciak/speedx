package com.google.gson.jpush.internal;

import com.google.gson.jpush.C1483y;
import com.google.gson.jpush.C3975w;
import com.google.gson.jpush.C4053x;
import com.google.gson.jpush.af;
import com.google.gson.jpush.internal.p186a.C4016z;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3980d;
import java.io.Writer;

public final class ag {
    /* renamed from: a */
    public static C3975w m16326a(C3976a c3976a) {
        Object obj = 1;
        try {
            c3976a.mo5683f();
            obj = null;
            return (C3975w) C4016z.f14452P.mo5672a(c3976a);
        } catch (Throwable e) {
            if (obj != null) {
                return C1483y.f7007a;
            }
            throw new af(e);
        } catch (Throwable e2) {
            throw new af(e2);
        } catch (Throwable e22) {
            throw new C4053x(e22);
        } catch (Throwable e222) {
            throw new af(e222);
        }
    }

    /* renamed from: a */
    public static Writer m16327a(Appendable appendable) {
        return appendable instanceof Writer ? (Writer) appendable : new ah(appendable, (byte) 0);
    }

    /* renamed from: a */
    public static void m16328a(C3975w c3975w, C3980d c3980d) {
        C4016z.f14452P.mo5673a(c3980d, c3975w);
    }
}
