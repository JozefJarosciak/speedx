package com.facebook.appevents.p178a;

import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: ActivityLifecycleTracker */
/* renamed from: com.facebook.appevents.a.a */
public class C2963a {
    /* renamed from: a */
    private static final String f13441a = C2963a.class.getCanonicalName();
    /* renamed from: b */
    private static final ScheduledExecutorService f13442b = Executors.newSingleThreadScheduledExecutor();
    /* renamed from: c */
    private static AtomicInteger f13443c = new AtomicInteger(0);
    /* renamed from: d */
    private static volatile C2965c f13444d;
    /* renamed from: e */
    private static AtomicBoolean f13445e = new AtomicBoolean(false);

    /* renamed from: a */
    public static UUID m14418a() {
        return f13444d != null ? f13444d.m14420a() : null;
    }
}
