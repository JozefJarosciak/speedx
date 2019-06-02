package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.al;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.util.UUID;

/* renamed from: com.google.gson.jpush.internal.a.an */
final class an extends al<UUID> {
    an() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() != C3979c.f14328i) {
            return UUID.fromString(c3976a.mo5685h());
        }
        c3976a.mo5687j();
        return null;
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        UUID uuid = (UUID) obj;
        c3980d.mo5700b(uuid == null ? null : uuid.toString());
    }
}
