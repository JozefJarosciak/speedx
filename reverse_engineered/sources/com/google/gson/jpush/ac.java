package com.google.gson.jpush;

import com.google.gson.jpush.internal.C4037v;
import java.math.BigInteger;

public final class ac extends C3975w {
    /* renamed from: a */
    private static final Class<?>[] f14284a = new Class[]{Integer.TYPE, Long.TYPE, Short.TYPE, Float.TYPE, Double.TYPE, Byte.TYPE, Boolean.TYPE, Character.TYPE, Integer.class, Long.class, Short.class, Float.class, Double.class, Byte.class, Boolean.class, Character.class};
    /* renamed from: b */
    private Object f14285b;

    public ac(Boolean bool) {
        m16077a((Object) bool);
    }

    public ac(Number number) {
        m16077a((Object) number);
    }

    public ac(String str) {
        m16077a((Object) str);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    private void m16077a(java.lang.Object r8) {
        /*
        r7 = this;
        r1 = 1;
        r0 = 0;
        r2 = r8 instanceof java.lang.Character;
        if (r2 == 0) goto L_0x0013;
    L_0x0006:
        r8 = (java.lang.Character) r8;
        r0 = r8.charValue();
        r0 = java.lang.String.valueOf(r0);
        r7.f14285b = r0;
    L_0x0012:
        return;
    L_0x0013:
        r2 = r8 instanceof java.lang.Number;
        if (r2 != 0) goto L_0x001e;
    L_0x0017:
        r2 = r8 instanceof java.lang.String;
        if (r2 == 0) goto L_0x0025;
    L_0x001b:
        r2 = r1;
    L_0x001c:
        if (r2 == 0) goto L_0x001f;
    L_0x001e:
        r0 = r1;
    L_0x001f:
        com.google.gson.jpush.internal.C4017a.m16320a(r0);
        r7.f14285b = r8;
        goto L_0x0012;
    L_0x0025:
        r3 = r8.getClass();
        r4 = f14284a;
        r5 = r4.length;
        r2 = r0;
    L_0x002d:
        if (r2 >= r5) goto L_0x003c;
    L_0x002f:
        r6 = r4[r2];
        r6 = r6.isAssignableFrom(r3);
        if (r6 == 0) goto L_0x0039;
    L_0x0037:
        r2 = r1;
        goto L_0x001c;
    L_0x0039:
        r2 = r2 + 1;
        goto L_0x002d;
    L_0x003c:
        r2 = r0;
        goto L_0x001c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.ac.a(java.lang.Object):void");
    }

    /* renamed from: a */
    private static boolean m16078a(ac acVar) {
        if (!(acVar.f14285b instanceof Number)) {
            return false;
        }
        Number number = (Number) acVar.f14285b;
        return (number instanceof BigInteger) || (number instanceof Long) || (number instanceof Integer) || (number instanceof Short) || (number instanceof Byte);
    }

    /* renamed from: a */
    public final boolean m16079a() {
        return this.f14285b instanceof Boolean;
    }

    /* renamed from: b */
    public final Number mo5666b() {
        return this.f14285b instanceof String ? new C4037v((String) this.f14285b) : (Number) this.f14285b;
    }

    /* renamed from: c */
    public final String mo5667c() {
        return this.f14285b instanceof Number ? mo5666b().toString() : this.f14285b instanceof Boolean ? ((Boolean) this.f14285b).toString() : (String) this.f14285b;
    }

    /* renamed from: d */
    public final double mo5668d() {
        return this.f14285b instanceof Number ? mo5666b().doubleValue() : Double.parseDouble(mo5667c());
    }

    /* renamed from: e */
    public final long mo5669e() {
        return this.f14285b instanceof Number ? mo5666b().longValue() : Long.parseLong(mo5667c());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ac acVar = (ac) obj;
        if (this.f14285b == null) {
            return acVar.f14285b == null;
        } else {
            if (m16078a(this) && m16078a(acVar)) {
                return mo5666b().longValue() == acVar.mo5666b().longValue();
            } else {
                if (!(this.f14285b instanceof Number) || !(acVar.f14285b instanceof Number)) {
                    return this.f14285b.equals(acVar.f14285b);
                }
                double doubleValue = mo5666b().doubleValue();
                double doubleValue2 = acVar.mo5666b().doubleValue();
                return doubleValue != doubleValue2 ? Double.isNaN(doubleValue) && Double.isNaN(doubleValue2) : true;
            }
        }
    }

    /* renamed from: f */
    public final int mo5670f() {
        return this.f14285b instanceof Number ? mo5666b().intValue() : Integer.parseInt(mo5667c());
    }

    /* renamed from: g */
    public final boolean mo5671g() {
        return this.f14285b instanceof Boolean ? ((Boolean) this.f14285b).booleanValue() : Boolean.parseBoolean(mo5667c());
    }

    public final int hashCode() {
        if (this.f14285b == null) {
            return 31;
        }
        long longValue;
        if (m16078a(this)) {
            longValue = mo5666b().longValue();
            return (int) (longValue ^ (longValue >>> 32));
        } else if (!(this.f14285b instanceof Number)) {
            return this.f14285b.hashCode();
        } else {
            longValue = Double.doubleToLongBits(mo5666b().doubleValue());
            return (int) (longValue ^ (longValue >>> 32));
        }
    }

    /* renamed from: k */
    public final boolean m16086k() {
        return this.f14285b instanceof Number;
    }

    /* renamed from: l */
    public final boolean m16087l() {
        return this.f14285b instanceof String;
    }
}
