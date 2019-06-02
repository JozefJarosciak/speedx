package com.google.gson.jpush.internal;

import java.math.BigDecimal;

/* renamed from: com.google.gson.jpush.internal.v */
public final class C4037v extends Number {
    /* renamed from: a */
    private final String f14555a;

    public C4037v(String str) {
        this.f14555a = str;
    }

    public final double doubleValue() {
        return Double.parseDouble(this.f14555a);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C4037v)) {
            return false;
        }
        C4037v c4037v = (C4037v) obj;
        return this.f14555a == c4037v.f14555a || this.f14555a.equals(c4037v.f14555a);
    }

    public final float floatValue() {
        return Float.parseFloat(this.f14555a);
    }

    public final int hashCode() {
        return this.f14555a.hashCode();
    }

    public final int intValue() {
        try {
            return Integer.parseInt(this.f14555a);
        } catch (NumberFormatException e) {
            try {
                return (int) Long.parseLong(this.f14555a);
            } catch (NumberFormatException e2) {
                return new BigDecimal(this.f14555a).intValue();
            }
        }
    }

    public final long longValue() {
        try {
            return Long.parseLong(this.f14555a);
        } catch (NumberFormatException e) {
            return new BigDecimal(this.f14555a).longValue();
        }
    }

    public final String toString() {
        return this.f14555a;
    }
}
