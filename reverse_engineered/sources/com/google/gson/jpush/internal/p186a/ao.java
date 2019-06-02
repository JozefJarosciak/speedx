package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.p184a.C3972a;
import java.sql.Timestamp;
import java.util.Date;

/* renamed from: com.google.gson.jpush.internal.a.ao */
final class ao implements am {
    ao() {
    }

    /* renamed from: a */
    public final <T> al<T> mo5674a(C4042k c4042k, C3972a<T> c3972a) {
        return c3972a.m16057a() != Timestamp.class ? null : new ap(this, c4042k.m16382a(Date.class));
    }
}
