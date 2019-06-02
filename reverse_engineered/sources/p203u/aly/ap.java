package p203u.aly;

import com.j256.ormlite.stmt.query.SimpleComparison;

/* compiled from: TField */
/* renamed from: u.aly.ap */
public class ap {
    /* renamed from: a */
    public final String f18594a;
    /* renamed from: b */
    public final byte f18595b;
    /* renamed from: c */
    public final short f18596c;

    public ap() {
        this("", (byte) 0, (short) 0);
    }

    public ap(String str, byte b, short s) {
        this.f18594a = str;
        this.f18595b = b;
        this.f18596c = s;
    }

    public String toString() {
        return "<TField name:'" + this.f18594a + "' type:" + this.f18595b + " field-id:" + this.f18596c + SimpleComparison.GREATER_THAN_OPERATION;
    }
}
