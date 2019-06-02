package okhttp3.internal.framed;

import okhttp3.internal.C5586l;
import okio.ByteString;

/* compiled from: Header */
/* renamed from: okhttp3.internal.framed.e */
public final class C5526e {
    /* renamed from: a */
    public static final ByteString f17805a = ByteString.encodeUtf8(":status");
    /* renamed from: b */
    public static final ByteString f17806b = ByteString.encodeUtf8(":method");
    /* renamed from: c */
    public static final ByteString f17807c = ByteString.encodeUtf8(":path");
    /* renamed from: d */
    public static final ByteString f17808d = ByteString.encodeUtf8(":scheme");
    /* renamed from: e */
    public static final ByteString f17809e = ByteString.encodeUtf8(":authority");
    /* renamed from: f */
    public static final ByteString f17810f = ByteString.encodeUtf8(":host");
    /* renamed from: g */
    public static final ByteString f17811g = ByteString.encodeUtf8(":version");
    /* renamed from: h */
    public final ByteString f17812h;
    /* renamed from: i */
    public final ByteString f17813i;
    /* renamed from: j */
    final int f17814j;

    public C5526e(String str, String str2) {
        this(ByteString.encodeUtf8(str), ByteString.encodeUtf8(str2));
    }

    public C5526e(ByteString byteString, String str) {
        this(byteString, ByteString.encodeUtf8(str));
    }

    public C5526e(ByteString byteString, ByteString byteString2) {
        this.f17812h = byteString;
        this.f17813i = byteString2;
        this.f17814j = (byteString.size() + 32) + byteString2.size();
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C5526e)) {
            return false;
        }
        C5526e c5526e = (C5526e) obj;
        if (this.f17812h.equals(c5526e.f17812h) && this.f17813i.equals(c5526e.f17813i)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((this.f17812h.hashCode() + 527) * 31) + this.f17813i.hashCode();
    }

    public String toString() {
        return C5586l.m20319a("%s: %s", this.f17812h.utf8(), this.f17813i.utf8());
    }
}
