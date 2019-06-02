package org.json.alipay;

import ch.qos.logback.core.CoreConstants;
import com.avos.avoscloud.AVException;
import java.io.BufferedReader;
import java.io.Reader;
import java.io.StringReader;

/* renamed from: org.json.alipay.c */
public final class C5667c {
    /* renamed from: a */
    private int f18256a;
    /* renamed from: b */
    private Reader f18257b;
    /* renamed from: c */
    private char f18258c;
    /* renamed from: d */
    private boolean f18259d;

    private C5667c(Reader reader) {
        if (!reader.markSupported()) {
            reader = new BufferedReader(reader);
        }
        this.f18257b = reader;
        this.f18259d = false;
        this.f18256a = 0;
    }

    public C5667c(String str) {
        this(new StringReader(str));
    }

    /* renamed from: a */
    private String m20782a(int i) {
        int i2 = 0;
        if (i == 0) {
            return "";
        }
        char[] cArr = new char[i];
        if (this.f18259d) {
            this.f18259d = false;
            cArr[0] = this.f18258c;
            i2 = 1;
        }
        while (i2 < i) {
            try {
                int read = this.f18257b.read(cArr, i2, i - i2);
                if (read == -1) {
                    break;
                }
                i2 += read;
            } catch (Throwable e) {
                throw new JSONException(e);
            }
        }
        this.f18256a += i2;
        if (i2 < i) {
            throw m20783a("Substring bounds error");
        }
        this.f18258c = cArr[i - 1];
        return new String(cArr);
    }

    /* renamed from: a */
    public final JSONException m20783a(String str) {
        return new JSONException(str + toString());
    }

    /* renamed from: a */
    public final void m20784a() {
        if (this.f18259d || this.f18256a <= 0) {
            throw new JSONException("Stepping back two steps is not supported");
        }
        this.f18256a--;
        this.f18259d = true;
    }

    /* renamed from: b */
    public final char m20785b() {
        if (this.f18259d) {
            this.f18259d = false;
            if (this.f18258c != '\u0000') {
                this.f18256a++;
            }
            return this.f18258c;
        }
        try {
            int read = this.f18257b.read();
            if (read <= 0) {
                this.f18258c = '\u0000';
                return '\u0000';
            }
            this.f18256a++;
            this.f18258c = (char) read;
            return this.f18258c;
        } catch (Throwable e) {
            throw new JSONException(e);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: c */
    public final char m20786c() {
        /*
        r5 = this;
        r4 = 13;
        r3 = 10;
        r0 = 47;
    L_0x0006:
        r1 = r5.m20785b();
        if (r1 != r0) goto L_0x003c;
    L_0x000c:
        r1 = r5.m20785b();
        switch(r1) {
            case 42: goto L_0x002f;
            case 47: goto L_0x0017;
            default: goto L_0x0013;
        };
    L_0x0013:
        r5.m20784a();
    L_0x0016:
        return r0;
    L_0x0017:
        r1 = r5.m20785b();
        if (r1 == r3) goto L_0x0006;
    L_0x001d:
        if (r1 == r4) goto L_0x0006;
    L_0x001f:
        if (r1 != 0) goto L_0x0017;
    L_0x0021:
        goto L_0x0006;
    L_0x0022:
        r2 = 42;
        if (r1 != r2) goto L_0x002f;
    L_0x0026:
        r1 = r5.m20785b();
        if (r1 == r0) goto L_0x0006;
    L_0x002c:
        r5.m20784a();
    L_0x002f:
        r1 = r5.m20785b();
        if (r1 != 0) goto L_0x0022;
    L_0x0035:
        r0 = "Unclosed comment";
        r0 = r5.m20783a(r0);
        throw r0;
    L_0x003c:
        r2 = 35;
        if (r1 != r2) goto L_0x004b;
    L_0x0040:
        r1 = r5.m20785b();
        if (r1 == r3) goto L_0x0006;
    L_0x0046:
        if (r1 == r4) goto L_0x0006;
    L_0x0048:
        if (r1 != 0) goto L_0x0040;
    L_0x004a:
        goto L_0x0006;
    L_0x004b:
        if (r1 == 0) goto L_0x0051;
    L_0x004d:
        r2 = 32;
        if (r1 <= r2) goto L_0x0006;
    L_0x0051:
        r0 = r1;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.json.alipay.c.c():char");
    }

    /* renamed from: d */
    public final Object m20787d() {
        char c = m20786c();
        switch (c) {
            case '\"':
            case '\'':
                StringBuffer stringBuffer = new StringBuffer();
                while (true) {
                    char b = m20785b();
                    switch (b) {
                        case '\u0000':
                        case '\n':
                        case '\r':
                            throw m20783a("Unterminated string");
                        case '\\':
                            b = m20785b();
                            switch (b) {
                                case 'b':
                                    stringBuffer.append('\b');
                                    break;
                                case 'f':
                                    stringBuffer.append('\f');
                                    break;
                                case 'n':
                                    stringBuffer.append('\n');
                                    break;
                                case 'r':
                                    stringBuffer.append('\r');
                                    break;
                                case AVException.OBJECT_TOO_LARGE /*116*/:
                                    stringBuffer.append('\t');
                                    break;
                                case 'u':
                                    stringBuffer.append((char) Integer.parseInt(m20782a(4), 16));
                                    break;
                                case 'x':
                                    stringBuffer.append((char) Integer.parseInt(m20782a(2), 16));
                                    break;
                                default:
                                    stringBuffer.append(b);
                                    break;
                            }
                        default:
                            if (b != c) {
                                stringBuffer.append(b);
                                break;
                            }
                            return stringBuffer.toString();
                    }
                }
            case '(':
            case '[':
                m20784a();
                return new C5664a(this);
            case AVException.INVALID_ACL /*123*/:
                m20784a();
                return new C5666b(this);
            default:
                StringBuffer stringBuffer2 = new StringBuffer();
                char c2 = c;
                while (c2 >= ' ' && ",:]}/\\\"[{;=#".indexOf(c2) < 0) {
                    stringBuffer2.append(c2);
                    c2 = m20785b();
                }
                m20784a();
                String trim = stringBuffer2.toString().trim();
                if (trim.equals("")) {
                    throw m20783a("Missing value");
                } else if (trim.equalsIgnoreCase("true")) {
                    return Boolean.TRUE;
                } else {
                    if (trim.equalsIgnoreCase("false")) {
                        return Boolean.FALSE;
                    }
                    if (trim.equalsIgnoreCase("null")) {
                        return C5666b.f18254a;
                    }
                    if ((c < '0' || c > '9') && c != CoreConstants.DOT && c != CoreConstants.DASH_CHAR && c != '+') {
                        return trim;
                    }
                    if (c == '0') {
                        if (trim.length() <= 2 || !(trim.charAt(1) == 'x' || trim.charAt(1) == 'X')) {
                            try {
                                return new Integer(Integer.parseInt(trim, 8));
                            } catch (Exception e) {
                            }
                        } else {
                            try {
                                return new Integer(Integer.parseInt(trim.substring(2), 16));
                            } catch (Exception e2) {
                            }
                        }
                    }
                    try {
                        return new Integer(trim);
                    } catch (Exception e3) {
                        try {
                            return new Long(trim);
                        } catch (Exception e4) {
                            try {
                                return new Double(trim);
                            } catch (Exception e5) {
                                return trim;
                            }
                        }
                    }
                }
        }
    }

    public final String toString() {
        return " at character " + this.f18256a;
    }
}
