package cn.sharesdk.framework.utils;

import java.io.IOException;

/* compiled from: UnicodeEscaper */
/* renamed from: cn.sharesdk.framework.utils.e */
public abstract class C0617e implements Escaper {
    /* renamed from: a */
    private static final ThreadLocal<char[]> f1397a = new C06232();

    /* compiled from: UnicodeEscaper */
    /* renamed from: cn.sharesdk.framework.utils.e$2 */
    static class C06232 extends ThreadLocal<char[]> {
        C06232() {
        }

        protected /* synthetic */ Object initialValue() {
            return m2282a();
        }

        /* renamed from: a */
        protected char[] m2282a() {
            return new char[1024];
        }
    }

    /* renamed from: a */
    protected abstract char[] mo2290a(int i);

    /* renamed from: a */
    protected int mo2289a(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int b = C0617e.m2271b(charSequence, i, i2);
            if (b < 0 || mo2290a(b) != null) {
                break;
            }
            i += Character.isSupplementaryCodePoint(b) ? 2 : 1;
        }
        return i;
    }

    public String escape(String str) {
        int length = str.length();
        int a = mo2289a((CharSequence) str, 0, length);
        return a == length ? str : m2273a(str, a);
    }

    /* renamed from: a */
    protected final String m2273a(String str, int i) {
        int b;
        int length = str.length();
        int i2 = 0;
        char[] cArr = (char[]) f1397a.get();
        int i3 = 0;
        while (i < length) {
            b = C0617e.m2271b(str, i, length);
            if (b < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            Object a = mo2290a(b);
            if (a != null) {
                int i4 = i - i2;
                int length2 = (i3 + i4) + a.length;
                if (cArr.length < length2) {
                    cArr = C0617e.m2270a(cArr, i3, (length2 + (length - i)) + 32);
                }
                if (i4 > 0) {
                    str.getChars(i2, i, cArr, i3);
                    i3 += i4;
                }
                if (a.length > 0) {
                    System.arraycopy(a, 0, cArr, i3, a.length);
                    i3 += a.length;
                }
            }
            b = (Character.isSupplementaryCodePoint(b) ? 2 : 1) + i;
            i = mo2289a((CharSequence) str, b, length);
            i2 = b;
        }
        b = length - i2;
        if (b > 0) {
            b += i3;
            if (cArr.length < b) {
                cArr = C0617e.m2270a(cArr, i3, b);
            }
            str.getChars(i2, length, cArr, i3);
            i3 = b;
        }
        return new String(cArr, 0, i3);
    }

    public Appendable escape(final Appendable appendable) {
        C0619c.m2278a(appendable);
        return new Appendable(this) {
            /* renamed from: a */
            int f1405a = -1;
            /* renamed from: b */
            char[] f1406b = new char[2];
            /* renamed from: d */
            final /* synthetic */ C0617e f1408d;

            public Appendable append(CharSequence charSequence) throws IOException {
                return append(charSequence, 0, charSequence.length());
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Appendable append(java.lang.CharSequence r6, int r7, int r8) throws java.io.IOException {
                /*
                r5 = this;
                r4 = -1;
                if (r7 >= r8) goto L_0x0052;
            L_0x0003:
                r0 = r5.f1405a;
                if (r0 == r4) goto L_0x008c;
            L_0x0007:
                r0 = r7 + 1;
                r1 = r6.charAt(r7);
                r2 = java.lang.Character.isLowSurrogate(r1);
                if (r2 != 0) goto L_0x002c;
            L_0x0013:
                r0 = new java.lang.IllegalArgumentException;
                r2 = new java.lang.StringBuilder;
                r2.<init>();
                r3 = "Expected low surrogate character but got ";
                r2 = r2.append(r3);
                r1 = r2.append(r1);
                r1 = r1.toString();
                r0.<init>(r1);
                throw r0;
            L_0x002c:
                r2 = r5.f1408d;
                r3 = r5.f1405a;
                r3 = (char) r3;
                r1 = java.lang.Character.toCodePoint(r3, r1);
                r1 = r2.mo2290a(r1);
                if (r1 == 0) goto L_0x0053;
            L_0x003b:
                r2 = r1.length;
                r5.m2281a(r1, r2);
                r7 = r7 + 1;
            L_0x0041:
                r5.f1405a = r4;
            L_0x0043:
                r1 = r5.f1408d;
                r1 = r1.mo2289a(r6, r0, r8);
                if (r1 <= r7) goto L_0x0050;
            L_0x004b:
                r0 = r2;
                r0.append(r6, r7, r1);
            L_0x0050:
                if (r1 != r8) goto L_0x005c;
            L_0x0052:
                return r5;
            L_0x0053:
                r1 = r2;
                r2 = r5.f1405a;
                r2 = (char) r2;
                r1.append(r2);
                goto L_0x0041;
            L_0x005c:
                r0 = cn.sharesdk.framework.utils.C0617e.m2271b(r6, r1, r8);
                if (r0 >= 0) goto L_0x0066;
            L_0x0062:
                r0 = -r0;
                r5.f1405a = r0;
                goto L_0x0052;
            L_0x0066:
                r2 = r5.f1408d;
                r2 = r2.mo2290a(r0);
                if (r2 == 0) goto L_0x007d;
            L_0x006e:
                r3 = r2.length;
                r5.m2281a(r2, r3);
            L_0x0072:
                r0 = java.lang.Character.isSupplementaryCodePoint(r0);
                if (r0 == 0) goto L_0x008a;
            L_0x0078:
                r0 = 2;
            L_0x0079:
                r7 = r1 + r0;
                r0 = r7;
                goto L_0x0043;
            L_0x007d:
                r2 = r5.f1406b;
                r3 = 0;
                r2 = java.lang.Character.toChars(r0, r2, r3);
                r3 = r5.f1406b;
                r5.m2281a(r3, r2);
                goto L_0x0072;
            L_0x008a:
                r0 = 1;
                goto L_0x0079;
            L_0x008c:
                r0 = r7;
                goto L_0x0043;
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.framework.utils.e.1.append(java.lang.CharSequence, int, int):java.lang.Appendable");
            }

            public Appendable append(char c) throws IOException {
                char[] a;
                if (this.f1405a != -1) {
                    if (Character.isLowSurrogate(c)) {
                        a = this.f1408d.mo2290a(Character.toCodePoint((char) this.f1405a, c));
                        if (a != null) {
                            m2281a(a, a.length);
                        } else {
                            appendable.append((char) this.f1405a);
                            appendable.append(c);
                        }
                        this.f1405a = -1;
                    } else {
                        throw new IllegalArgumentException("Expected low surrogate character but got '" + c + "' with value " + c);
                    }
                } else if (Character.isHighSurrogate(c)) {
                    this.f1405a = c;
                } else if (Character.isLowSurrogate(c)) {
                    throw new IllegalArgumentException("Unexpected low surrogate character '" + c + "' with value " + c);
                } else {
                    a = this.f1408d.mo2290a(c);
                    if (a != null) {
                        m2281a(a, a.length);
                    } else {
                        appendable.append(c);
                    }
                }
                return this;
            }

            /* renamed from: a */
            private void m2281a(char[] cArr, int i) throws IOException {
                for (int i2 = 0; i2 < i; i2++) {
                    appendable.append(cArr[i2]);
                }
            }
        };
    }

    /* renamed from: b */
    protected static final int m2271b(CharSequence charSequence, int i, int i2) {
        if (i < i2) {
            int i3 = i + 1;
            int charAt = charSequence.charAt(i);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                throw new IllegalArgumentException("Unexpected low surrogate character '" + charAt + "' with value " + charAt + " at index " + (i3 - 1));
            } else if (i3 == i2) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i3);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                throw new IllegalArgumentException("Expected low surrogate but got char '" + charAt2 + "' with value " + charAt2 + " at index " + i3);
            }
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    /* renamed from: a */
    private static final char[] m2270a(char[] cArr, int i, int i2) {
        Object obj = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, obj, 0, i);
        }
        return obj;
    }
}
