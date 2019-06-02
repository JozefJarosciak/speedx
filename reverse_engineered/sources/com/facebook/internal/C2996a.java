package com.facebook.internal;

import android.content.Intent;
import java.util.UUID;

/* compiled from: AppCall */
/* renamed from: com.facebook.internal.a */
public class C2996a {
    /* renamed from: a */
    private static C2996a f13529a;
    /* renamed from: b */
    private UUID f13530b;
    /* renamed from: c */
    private Intent f13531c;
    /* renamed from: d */
    private int f13532d;

    /* renamed from: a */
    public static C2996a m14531a() {
        return f13529a;
    }

    /* renamed from: a */
    private static synchronized boolean m14532a(C2996a c2996a) {
        boolean z;
        synchronized (C2996a.class) {
            C2996a a = C2996a.m14531a();
            f13529a = c2996a;
            z = a != null;
        }
        return z;
    }

    public C2996a(int i) {
        this(i, UUID.randomUUID());
    }

    public C2996a(int i, UUID uuid) {
        this.f13530b = uuid;
        this.f13532d = i;
    }

    /* renamed from: b */
    public Intent m14534b() {
        return this.f13531c;
    }

    /* renamed from: c */
    public UUID m14535c() {
        return this.f13530b;
    }

    /* renamed from: d */
    public int m14536d() {
        return this.f13532d;
    }

    /* renamed from: a */
    public void m14533a(Intent intent) {
        this.f13531c = intent;
    }

    /* renamed from: e */
    public boolean m14537e() {
        return C2996a.m14532a(this);
    }
}
