package p203u.aly;

import com.google.common.base.Ascii;
import java.io.Serializable;

/* compiled from: FieldValueMetaData */
/* renamed from: u.aly.cc */
public class cc implements Serializable {
    /* renamed from: a */
    private final boolean f18966a;
    /* renamed from: b */
    public final byte f18967b;
    /* renamed from: c */
    private final String f18968c;
    /* renamed from: d */
    private final boolean f18969d;

    public cc(byte b, boolean z) {
        this.f18967b = b;
        this.f18966a = false;
        this.f18968c = null;
        this.f18969d = z;
    }

    public cc(byte b) {
        this(b, false);
    }

    public cc(byte b, String str) {
        this.f18967b = b;
        this.f18966a = true;
        this.f18968c = str;
        this.f18969d = false;
    }

    /* renamed from: a */
    public boolean m21818a() {
        return this.f18966a;
    }

    /* renamed from: b */
    public String m21819b() {
        return this.f18968c;
    }

    /* renamed from: c */
    public boolean m21820c() {
        return this.f18967b == Ascii.FF;
    }

    /* renamed from: d */
    public boolean m21821d() {
        return this.f18967b == Ascii.SI || this.f18967b == (byte) 13 || this.f18967b == Ascii.SO;
    }

    /* renamed from: e */
    public boolean m21822e() {
        return this.f18969d;
    }
}
