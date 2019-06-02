package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.al;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.util.Locale;
import java.util.StringTokenizer;

/* renamed from: com.google.gson.jpush.internal.a.ar */
final class ar extends al<Locale> {
    ar() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            return null;
        }
        StringTokenizer stringTokenizer = new StringTokenizer(c3976a.mo5685h(), "_");
        String nextToken = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
        String nextToken2 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
        String nextToken3 = stringTokenizer.hasMoreElements() ? stringTokenizer.nextToken() : null;
        return (nextToken2 == null && nextToken3 == null) ? new Locale(nextToken) : nextToken3 == null ? new Locale(nextToken, nextToken2) : new Locale(nextToken, nextToken2, nextToken3);
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        Locale locale = (Locale) obj;
        c3980d.mo5700b(locale == null ? null : locale.toString());
    }
}
