package com.google.gson.jpush.internal;

import ch.qos.logback.core.net.SyslogConstants;
import com.google.gson.jpush.C3982b;
import com.google.gson.jpush.C3983c;
import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.annotations.C1479a;
import com.google.gson.jpush.annotations.C1482d;
import com.google.gson.jpush.annotations.Until;
import com.google.gson.jpush.p184a.C3972a;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;

/* renamed from: com.google.gson.jpush.internal.s */
public final class C4035s implements am, Cloneable {
    /* renamed from: a */
    public static final C4035s f14542a = new C4035s();
    /* renamed from: b */
    private double f14543b = -1.0d;
    /* renamed from: c */
    private int f14544c = SyslogConstants.LOG_LOCAL1;
    /* renamed from: d */
    private boolean f14545d = true;
    /* renamed from: e */
    private boolean f14546e;
    /* renamed from: f */
    private List<C3982b> f14547f = Collections.emptyList();
    /* renamed from: g */
    private List<C3982b> f14548g = Collections.emptyList();

    /* renamed from: a */
    private boolean m16356a(C1482d c1482d, Until until) {
        boolean z = c1482d == null || c1482d.a() <= this.f14543b;
        if (z) {
            z = until == null || until.value() > this.f14543b;
            if (z) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static boolean m16357a(Class<?> cls) {
        return !Enum.class.isAssignableFrom(cls) && (cls.isAnonymousClass() || cls.isLocalClass());
    }

    /* renamed from: b */
    private C4035s m16358b() {
        try {
            return (C4035s) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: b */
    private boolean m16359b(Class<?> cls) {
        if (cls.isMemberClass()) {
            if (!((cls.getModifiers() & 8) != 0)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public final <T> al<T> mo5674a(C4042k c4042k, C3972a<T> c3972a) {
        Class a = c3972a.m16057a();
        boolean a2 = m16362a(a, true);
        boolean a3 = m16362a(a, false);
        return (a2 || a3) ? new C4036t(this, a3, a2, c4042k, c3972a) : null;
    }

    /* renamed from: a */
    public final C4035s m16361a() {
        C4035s b = m16358b();
        b.f14546e = true;
        return b;
    }

    /* renamed from: a */
    public final boolean m16362a(Class<?> cls, boolean z) {
        if (this.f14543b != -1.0d && !m16356a((C1482d) cls.getAnnotation(C1482d.class), (Until) cls.getAnnotation(Until.class))) {
            return true;
        }
        if (!this.f14545d && m16359b(cls)) {
            return true;
        }
        if (C4035s.m16357a(cls)) {
            return true;
        }
        for (C3982b b : z ? this.f14547f : this.f14548g) {
            if (b.m16163b()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public final boolean m16363a(Field field, boolean z) {
        if ((this.f14544c & field.getModifiers()) != 0) {
            return true;
        }
        if (this.f14543b != -1.0d && !m16356a((C1482d) field.getAnnotation(C1482d.class), (Until) field.getAnnotation(Until.class))) {
            return true;
        }
        if (field.isSynthetic()) {
            return true;
        }
        if (this.f14546e) {
            C1479a c1479a = (C1479a) field.getAnnotation(C1479a.class);
            if (c1479a == null || (z ? !c1479a.a() : !c1479a.b())) {
                return true;
            }
        }
        if (!this.f14545d && m16359b(field.getType())) {
            return true;
        }
        if (C4035s.m16357a(field.getType())) {
            return true;
        }
        List<C3982b> list = z ? this.f14547f : this.f14548g;
        if (!list.isEmpty()) {
            C3983c c3983c = new C3983c(field);
            for (C3982b a : list) {
                if (a.m16162a()) {
                    return true;
                }
            }
        }
        return false;
    }

    protected final /* synthetic */ Object clone() {
        return m16358b();
    }
}
