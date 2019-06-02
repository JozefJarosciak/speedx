package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.al;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.net.InetAddress;

/* renamed from: com.google.gson.jpush.internal.a.am */
final class am extends al<InetAddress> {
    am() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() != C3979c.f14328i) {
            return InetAddress.getByName(c3976a.mo5685h());
        }
        c3976a.mo5687j();
        return null;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        InetAddress inetAddress = (InetAddress) obj;
        c3980d.mo5700b(inetAddress == null ? null : inetAddress.getHostAddress());
    }
}
