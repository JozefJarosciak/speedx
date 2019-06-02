package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.al;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3980d;
import java.sql.Timestamp;
import java.util.Date;

/* renamed from: com.google.gson.jpush.internal.a.ap */
final class ap extends al<Timestamp> {
    /* renamed from: a */
    final /* synthetic */ al f14361a;
    /* renamed from: b */
    final /* synthetic */ ao f14362b;

    ap(ao aoVar, al alVar) {
        this.f14362b = aoVar;
        this.f14361a = alVar;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        Date date = (Date) this.f14361a.mo5672a(c3976a);
        return date != null ? new Timestamp(date.getTime()) : null;
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        this.f14361a.mo5673a(c3980d, (Timestamp) obj);
    }
}
