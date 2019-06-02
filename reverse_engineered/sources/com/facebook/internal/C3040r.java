package com.facebook.internal;

import com.facebook.C1472c;
import java.util.Collection;

/* compiled from: ServerProtocol */
/* renamed from: com.facebook.internal.r */
public final class C3040r {
    /* renamed from: a */
    public static final Collection<String> f13611a = C3048s.m14740a("service_disabled", "AndroidAuthKillSwitchException");
    /* renamed from: b */
    public static final Collection<String> f13612b = C3048s.m14740a("access_denied", "OAuthAccessDeniedException");
    /* renamed from: c */
    private static final String f13613c = C3040r.class.getName();

    /* renamed from: a */
    public static final String m14704a() {
        return String.format("m.%s", new Object[]{C1472c.e()});
    }

    /* renamed from: b */
    public static final String m14705b() {
        return String.format("https://graph.%s", new Object[]{C1472c.e()});
    }

    /* renamed from: c */
    public static final String m14706c() {
        return String.format("https://graph-video.%s", new Object[]{C1472c.e()});
    }

    /* renamed from: d */
    public static final String m14707d() {
        return "v2.7";
    }
}
