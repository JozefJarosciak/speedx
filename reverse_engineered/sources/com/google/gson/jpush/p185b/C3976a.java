package com.google.gson.jpush.p185b;

import ch.qos.logback.core.CoreConstants;
import com.avos.avoscloud.AVException;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.Reader;

/* renamed from: com.google.gson.jpush.b.a */
public class C3976a implements Closeable {
    /* renamed from: a */
    private static final char[] f14301a;
    /* renamed from: z */
    private static final String[] f14302z;
    /* renamed from: b */
    private final Reader f14303b;
    /* renamed from: c */
    private boolean f14304c = false;
    /* renamed from: d */
    private final char[] f14305d = new char[1024];
    /* renamed from: e */
    private int f14306e = 0;
    /* renamed from: f */
    private int f14307f = 0;
    /* renamed from: g */
    private int f14308g = 0;
    /* renamed from: h */
    private int f14309h = 0;
    /* renamed from: i */
    private int f14310i = 0;
    /* renamed from: j */
    private long f14311j;
    /* renamed from: k */
    private int f14312k;
    /* renamed from: l */
    private String f14313l;
    /* renamed from: m */
    private int[] f14314m = new int[32];
    /* renamed from: n */
    private int f14315n = 0;
    /* renamed from: o */
    private String[] f14316o;
    /* renamed from: p */
    private int[] f14317p;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 36;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\"\u0012e\u0004k\r\u0000n\u000fKH\byJZ\u0004\u000ey\u000f]";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x01ce;
            case 1: goto L_0x01d2;
            case 2: goto L_0x01d6;
            case 3: goto L_0x01da;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 57;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            case 25: goto L_0x0142;
            case 26: goto L_0x014d;
            case 27: goto L_0x0158;
            case 28: goto L_0x0163;
            case 29: goto L_0x016e;
            case 30: goto L_0x0179;
            case 31: goto L_0x0184;
            case 32: goto L_0x018f;
            case 33: goto L_0x019a;
            case 34: goto L_0x01a5;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "=\u000f~\u000fK\u0005\bd\u000bM\r\u0005*\u0005[\u0002\u0004i\u001e";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJW\t\fo";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJ\u001eRF";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "=\u000f~\u000fK\u0005\bd\u000bM\r\u0005*\u000bK\u001a\u0000s";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "=\u000fo\u0012I\r\u0002~\u000f]H\u0017k\u0006L\r";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJO\t\r\u000f";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "H\u0002e\u0006L\u0005\u000f*";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "H\u0011k\u001eQH";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJ|&%U%{\"$I>\u0019\n\u0014~JN\t\u0012*";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "H\u0000~JU\u0001\u000foJ";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0001\u000f*W\u0004H\u000f\u0006U";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "=\u000f~\u000fK\u0005\bd\u000bM\r\u0005*\u0019M\u001a\bd\r";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJXH\u000fk\u0007\\H\u0003\u001e\u0019\u001f\u0000yJ";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\"2E$\u0019\u000e\u000ex\bP\f\u0012*$X&Ak\u0004]H\bd\fP\u0006\b~\u0003\\\u001b[*";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJXH\u0005e\u001f[\u0004\u0004*\bL\u001cA}\u000bJH";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJXH\re\u0004^H\u0003\u001e\u0019\u001f\u0000yJ";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJ{-&C$f'#@/z<Ah\u001fMH\u0016k\u0019\u0019";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJX\u0006Ac\u0004MH\u0003\u001e\u0019\u001f\u0000yJ";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "<3_/";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "\u0006\u0014f\u0006";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = ". F9|";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u001c\u0013\u000f";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "&4F&";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "\u000e\u0000f\u0019\\";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJ|&%U+k: SJ[\u001d\u0015*\u001dX\u001bA";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJ{-&C$f)3X+`H\u0003\u001e\u0019\u001f\u0000yJ";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "4\u0014";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "=\u000f~\u000fK\u0005\bd\u000bM\r\u0005*\u000fJ\u000b\u0000z\u000f\u0019\u001b\u0004{\u001f\\\u0006\u0002o";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJW\u001d\rfJ[\u001d\u0015*\u001dX\u001bA";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "=\u0012oJs\u001b\u000ed8\\\t\u0005o\u0018\u0017\u001b\u0004~&\\\u0006\bo\u0004M@\u0015x\u001f\\AA~\u0005\u0019\t\u0002i\u000fI\u001cAg\u000bU\u000e\u000ex\u0007\\\fA@9v&";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJXH\u0012~\u0018P\u0006\u0006*\bL\u001cA}\u000bJH";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "-\u000fnJV\u000eAc\u0004I\u001d\u0015*\u000bMH\rc\u0004\\H";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "=\u000f~\u000fK\u0005\bd\u000bM\r\u0005*\tV\u0005\fo\u0004M";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "BN";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = "-\u0019z\u000fZ\u001c\u0004nJXH\u0003e\u0005U\r\u0000dJ[\u001d\u0015*\u001dX\u001bA";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        f14302z = r4;
        r0 = "A<wM3";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x01ec;
    L_0x01b4:
        r3 = r0;
        r4 = r2;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x01b9:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x01de;
            case 1: goto L_0x01e1;
            case 2: goto L_0x01e4;
            case 3: goto L_0x01e7;
            default: goto L_0x01c0;
        };
    L_0x01c0:
        r5 = 57;
    L_0x01c2:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x01ea;
    L_0x01ca:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x01b9;
    L_0x01ce:
        r9 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        goto L_0x0020;
    L_0x01d2:
        r9 = 97;
        goto L_0x0020;
    L_0x01d6:
        r9 = 10;
        goto L_0x0020;
    L_0x01da:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x0020;
    L_0x01de:
        r5 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        goto L_0x01c2;
    L_0x01e1:
        r5 = 97;
        goto L_0x01c2;
    L_0x01e4:
        r5 = 10;
        goto L_0x01c2;
    L_0x01e7:
        r5 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x01c2;
    L_0x01ea:
        r1 = r0;
        r0 = r3;
    L_0x01ec:
        if (r1 > r2) goto L_0x01b4;
    L_0x01ee:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        r0 = r0.toCharArray();
        f14301a = r0;
        r0 = new com.google.gson.jpush.b.b;
        r0.<init>();
        com.google.gson.jpush.internal.C3977u.f14318a = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.b.a.<clinit>():void");
    }

    public C3976a(Reader reader) {
        int[] iArr = this.f14314m;
        int i = this.f14315n;
        this.f14315n = i + 1;
        iArr[i] = 6;
        this.f14316o = new String[32];
        this.f14317p = new int[32];
        if (reader == null) {
            throw new NullPointerException(f14302z[11]);
        }
        this.f14303b = reader;
    }

    /* renamed from: a */
    private IOException m16099a(String str) {
        throw new C3981e(str + f14302z[10] + (this.f14308g + 1) + f14302z[7] + m16113u() + f14302z[8] + m16133q());
    }

    /* renamed from: a */
    private void m16100a(int i) {
        if (this.f14315n == this.f14314m.length) {
            Object obj = new int[(this.f14315n * 2)];
            Object obj2 = new int[(this.f14315n * 2)];
            Object obj3 = new String[(this.f14315n * 2)];
            System.arraycopy(this.f14314m, 0, obj, 0, this.f14315n);
            System.arraycopy(this.f14317p, 0, obj2, 0, this.f14315n);
            System.arraycopy(this.f14316o, 0, obj3, 0, this.f14315n);
            this.f14314m = obj;
            this.f14317p = obj2;
            this.f14316o = obj3;
        }
        int[] iArr = this.f14314m;
        int i2 = this.f14315n;
        this.f14315n = i2 + 1;
        iArr[i2] = i;
    }

    /* renamed from: a */
    private boolean m16101a(char c) {
        switch (c) {
            case '\t':
            case '\n':
            case '\f':
            case '\r':
            case ' ':
            case ',':
            case ':':
            case '[':
            case ']':
            case AVException.INVALID_ACL /*123*/:
            case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                break;
            case '#':
            case '/':
            case ';':
            case '=':
            case '\\':
                m16114v();
                break;
            default:
                return true;
        }
        return false;
    }

    /* renamed from: b */
    private int m16103b(boolean z) {
        char[] cArr = this.f14305d;
        int i = this.f14306e;
        int i2 = this.f14307f;
        while (true) {
            if (i == i2) {
                this.f14306e = i;
                if (m16105b(1)) {
                    i = this.f14306e;
                    i2 = this.f14307f;
                } else if (!z) {
                    return -1;
                } else {
                    throw new EOFException(new StringBuilder(f14302z[32]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).toString());
                }
            }
            int i3 = i + 1;
            char c = cArr[i];
            if (c == '\n') {
                this.f14308g++;
                this.f14309h = i3;
                i = i3;
            } else if (c == ' ' || c == '\r' || c == '\t') {
                i = i3;
            } else if (c == '/') {
                this.f14306e = i3;
                if (i3 == i2) {
                    this.f14306e--;
                    boolean b = m16105b(2);
                    this.f14306e++;
                    if (!b) {
                        return c;
                    }
                }
                m16114v();
                switch (cArr[this.f14306e]) {
                    case '*':
                        this.f14306e++;
                        String str = f14302z[34];
                        while (true) {
                            if (this.f14306e + str.length() <= this.f14307f || m16105b(str.length())) {
                                if (this.f14305d[this.f14306e] == '\n') {
                                    this.f14308g++;
                                    this.f14309h = this.f14306e + 1;
                                } else {
                                    i2 = 0;
                                    while (i2 < str.length()) {
                                        if (this.f14305d[this.f14306e + i2] == str.charAt(i2)) {
                                            i2++;
                                        }
                                    }
                                    i2 = 1;
                                }
                                this.f14306e++;
                            } else {
                                i2 = 0;
                            }
                            if (i2 != 0) {
                                i = this.f14306e + 2;
                                i2 = this.f14307f;
                                break;
                            }
                            throw m16099a(f14302z[33]);
                        }
                    case '/':
                        this.f14306e++;
                        m16115w();
                        i = this.f14306e;
                        i2 = this.f14307f;
                        break;
                    default:
                        return c;
                }
            } else if (c == '#') {
                this.f14306e = i3;
                m16114v();
                m16115w();
                i = this.f14306e;
                i2 = this.f14307f;
            } else {
                this.f14306e = i3;
                return c;
            }
        }
    }

    /* renamed from: b */
    private String m16104b(char c) {
        char[] cArr = this.f14305d;
        StringBuilder stringBuilder = new StringBuilder();
        do {
            int i = this.f14306e;
            int i2 = this.f14307f;
            int i3 = i;
            while (i3 < i2) {
                int i4 = i3 + 1;
                char c2 = cArr[i3];
                if (c2 == c) {
                    this.f14306e = i4;
                    stringBuilder.append(cArr, i, (i4 - i) - 1);
                    return stringBuilder.toString();
                } else if (c2 == CoreConstants.ESCAPE_CHAR) {
                    this.f14306e = i4;
                    stringBuilder.append(cArr, i, (i4 - i) - 1);
                    stringBuilder.append(m16116x());
                    i = this.f14306e;
                    i2 = this.f14307f;
                    i3 = i;
                } else {
                    if (c2 == '\n') {
                        this.f14308g++;
                        this.f14309h = i4;
                    }
                    i3 = i4;
                }
            }
            stringBuilder.append(cArr, i, i3 - i);
            this.f14306e = i3;
        } while (m16105b(1));
        throw m16099a(f14302z[12]);
    }

    /* renamed from: b */
    private boolean m16105b(int i) {
        Object obj = this.f14305d;
        this.f14309h -= this.f14306e;
        if (this.f14307f != this.f14306e) {
            this.f14307f -= this.f14306e;
            System.arraycopy(obj, this.f14306e, obj, 0, this.f14307f);
        } else {
            this.f14307f = 0;
        }
        this.f14306e = 0;
        do {
            int read = this.f14303b.read(obj, this.f14307f, obj.length - this.f14307f);
            if (read == -1) {
                return false;
            }
            this.f14307f = read + this.f14307f;
            if (this.f14308g == 0 && this.f14309h == 0 && this.f14307f > 0 && obj[0] == '﻿') {
                this.f14306e++;
                this.f14309h++;
                i++;
            }
        } while (this.f14307f < i);
        return true;
    }

    /* renamed from: c */
    private void m16107c(char c) {
        char[] cArr = this.f14305d;
        do {
            int i = this.f14306e;
            int i2 = this.f14307f;
            while (i < i2) {
                int i3 = i + 1;
                char c2 = cArr[i];
                if (c2 == c) {
                    this.f14306e = i3;
                    return;
                } else if (c2 == CoreConstants.ESCAPE_CHAR) {
                    this.f14306e = i3;
                    m16116x();
                    i = this.f14306e;
                    i2 = this.f14307f;
                } else {
                    if (c2 == '\n') {
                        this.f14308g++;
                        this.f14309h = i3;
                    }
                    i = i3;
                }
            }
            this.f14306e = i;
        } while (m16105b(1));
        throw m16099a(f14302z[12]);
    }

    /* renamed from: o */
    private int mo5692o() {
        int i = 0;
        int i2 = this.f14314m[this.f14315n - 1];
        if (i2 == 1) {
            this.f14314m[this.f14315n - 1] = 2;
        } else if (i2 == 2) {
            switch (m16103b(true)) {
                case 44:
                    break;
                case 59:
                    m16114v();
                    break;
                case 93:
                    this.f14310i = 4;
                    return 4;
                default:
                    throw m16099a(f14302z[4]);
            }
        } else if (i2 == 3 || i2 == 5) {
            this.f14314m[this.f14315n - 1] = 4;
            if (i2 == 5) {
                switch (m16103b(true)) {
                    case 44:
                        break;
                    case 59:
                        m16114v();
                        break;
                    case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                        this.f14310i = 2;
                        return 2;
                    default:
                        throw m16099a(f14302z[1]);
                }
            }
            i = m16103b(true);
            switch (i) {
                case 34:
                    this.f14310i = 13;
                    return 13;
                case 39:
                    m16114v();
                    this.f14310i = 12;
                    return 12;
                case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                    if (i2 != 5) {
                        this.f14310i = 2;
                        return 2;
                    }
                    throw m16099a(f14302z[2]);
                default:
                    m16114v();
                    this.f14306e--;
                    if (m16101a((char) i)) {
                        this.f14310i = 14;
                        return 14;
                    }
                    throw m16099a(f14302z[2]);
            }
        } else if (i2 == 4) {
            this.f14314m[this.f14315n - 1] = 5;
            switch (m16103b(true)) {
                case 58:
                    break;
                case 61:
                    m16114v();
                    if ((this.f14306e < this.f14307f || m16105b(1)) && this.f14305d[this.f14306e] == '>') {
                        this.f14306e++;
                        break;
                    }
                default:
                    throw m16099a(f14302z[3]);
            }
        } else if (i2 == 6) {
            if (this.f14304c) {
                m16103b(true);
                this.f14306e--;
                if (this.f14306e + f14301a.length <= this.f14307f || m16105b(f14301a.length)) {
                    while (i < f14301a.length) {
                        if (this.f14305d[this.f14306e + i] != f14301a[i]) {
                            break;
                        }
                        i++;
                    }
                    this.f14306e += f14301a.length;
                }
            }
            this.f14314m[this.f14315n - 1] = 7;
        } else if (i2 == 7) {
            if (m16103b(false) == -1) {
                this.f14310i = 17;
                return 17;
            }
            m16114v();
            this.f14306e--;
        } else if (i2 == 8) {
            throw new IllegalStateException(f14302z[0]);
        }
        switch (m16103b(true)) {
            case 34:
                if (this.f14315n == 1) {
                    m16114v();
                }
                this.f14310i = 9;
                return 9;
            case 39:
                m16114v();
                this.f14310i = 8;
                return 8;
            case 44:
            case 59:
                break;
            case 91:
                this.f14310i = 3;
                return 3;
            case 93:
                if (i2 == 1) {
                    this.f14310i = 4;
                    return 4;
                }
                break;
            case AVException.INVALID_ACL /*123*/:
                this.f14310i = 1;
                return 1;
            default:
                this.f14306e--;
                if (this.f14315n == 1) {
                    m16114v();
                }
                i = m16110r();
                if (i != 0) {
                    return i;
                }
                i = m16111s();
                if (i != 0) {
                    return i;
                }
                if (m16101a(this.f14305d[this.f14306e])) {
                    m16114v();
                    this.f14310i = 10;
                    return 10;
                }
                throw m16099a(f14302z[6]);
        }
        if (i2 == 1 || i2 == 2) {
            m16114v();
            this.f14306e--;
            this.f14310i = 7;
            return 7;
        }
        throw m16099a(f14302z[5]);
    }

    /* renamed from: r */
    private int m16110r() {
        String str;
        int i;
        char c = this.f14305d[this.f14306e];
        String str2;
        if (c == 't' || c == 'T') {
            str = f14302z[22];
            str2 = f14302z[19];
            i = 5;
        } else if (c == 'f' || c == 'F') {
            str = f14302z[24];
            str2 = f14302z[21];
            i = 6;
        } else if (c != 'n' && c != 'N') {
            return 0;
        } else {
            str = f14302z[20];
            str2 = f14302z[23];
            i = 7;
        }
        int length = str.length();
        int i2 = 1;
        while (i2 < length) {
            if (this.f14306e + i2 >= this.f14307f && !m16105b(i2 + 1)) {
                return 0;
            }
            char c2 = this.f14305d[this.f14306e + i2];
            if (c2 != str.charAt(i2) && c2 != r1.charAt(i2)) {
                return 0;
            }
            i2++;
        }
        if ((this.f14306e + length < this.f14307f || m16105b(length + 1)) && m16101a(this.f14305d[this.f14306e + length])) {
            return 0;
        }
        this.f14306e += length;
        this.f14310i = i;
        return i;
    }

    /* renamed from: s */
    private int m16111s() {
        char[] cArr = this.f14305d;
        int i = this.f14306e;
        long j = 0;
        Object obj = null;
        int i2 = 1;
        int i3 = 0;
        int i4 = 0;
        int i5 = this.f14307f;
        int i6 = i;
        while (true) {
            Object obj2;
            if (i6 + i4 == i5) {
                if (i4 == cArr.length) {
                    return 0;
                }
                if (m16105b(i4 + 1)) {
                    i6 = this.f14306e;
                    i5 = this.f14307f;
                } else if (i3 != 2 && i2 != 0 && (j != Long.MIN_VALUE || obj != null)) {
                    if (obj == null) {
                        j = -j;
                    }
                    this.f14311j = j;
                    this.f14306e += i4;
                    this.f14310i = 15;
                    return 15;
                } else if (i3 == 2 && i3 != 4 && i3 != 7) {
                    return 0;
                } else {
                    this.f14312k = i4;
                    this.f14310i = 16;
                    return 16;
                }
            }
            char c = cArr[i6 + i4];
            int i7;
            switch (c) {
                case '+':
                    if (i3 != 5) {
                        return 0;
                    }
                    i = 6;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case '-':
                    if (i3 == 0) {
                        i = 1;
                        i7 = i2;
                        obj2 = 1;
                        i3 = i7;
                        continue;
                    } else if (i3 == 5) {
                        i = 6;
                        i3 = i2;
                        obj2 = obj;
                        break;
                    } else {
                        return 0;
                    }
                case '.':
                    if (i3 != 2) {
                        return 0;
                    }
                    i = 3;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                case 'E':
                case 'e':
                    if (i3 != 2 && i3 != 4) {
                        return 0;
                    }
                    i = 5;
                    i3 = i2;
                    obj2 = obj;
                    continue;
                default:
                    if (c >= '0' && c <= '9') {
                        if (i3 != 1 && i3 != 0) {
                            if (i3 != 2) {
                                if (i3 != 3) {
                                    if (i3 != 5 && i3 != 6) {
                                        i = i3;
                                        i3 = i2;
                                        obj2 = obj;
                                        break;
                                    }
                                    i = 7;
                                    i3 = i2;
                                    obj2 = obj;
                                    break;
                                }
                                i = 4;
                                i3 = i2;
                                obj2 = obj;
                                break;
                            } else if (j != 0) {
                                long j2 = (10 * j) - ((long) (c - 48));
                                i = (j > -922337203685477580L || (j == -922337203685477580L && j2 < j)) ? 1 : 0;
                                i &= i2;
                                obj2 = obj;
                                j = j2;
                                i7 = i3;
                                i3 = i;
                                i = i7;
                                break;
                            } else {
                                return 0;
                            }
                        }
                        j = (long) (-(c - 48));
                        i = 2;
                        i3 = i2;
                        obj2 = obj;
                        continue;
                    } else if (m16101a(c)) {
                        return 0;
                    }
                    break;
            }
            if (i3 != 2) {
            }
            if (i3 == 2) {
            }
            this.f14312k = i4;
            this.f14310i = 16;
            return 16;
            i4++;
            obj = obj2;
            i2 = i3;
            i3 = i;
        }
    }

    /* renamed from: t */
    private String m16112t() {
        StringBuilder stringBuilder = null;
        int i = 0;
        while (true) {
            String str;
            if (this.f14306e + i < this.f14307f) {
                switch (this.f14305d[this.f14306e + i]) {
                    case '\t':
                    case '\n':
                    case '\f':
                    case '\r':
                    case ' ':
                    case ',':
                    case ':':
                    case '[':
                    case ']':
                    case AVException.INVALID_ACL /*123*/:
                    case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                        break;
                    case '#':
                    case '/':
                    case ';':
                    case '=':
                    case '\\':
                        m16114v();
                        break;
                    default:
                        i++;
                        continue;
                }
            } else if (i >= this.f14305d.length) {
                if (stringBuilder == null) {
                    stringBuilder = new StringBuilder();
                }
                stringBuilder.append(this.f14305d, this.f14306e, i);
                this.f14306e = i + this.f14306e;
                i = !m16105b(1) ? 0 : 0;
            } else if (m16105b(i + 1)) {
            }
            if (stringBuilder == null) {
                str = new String(this.f14305d, this.f14306e, i);
            } else {
                stringBuilder.append(this.f14305d, this.f14306e, i);
                str = stringBuilder.toString();
            }
            this.f14306e = i + this.f14306e;
            return str;
        }
    }

    /* renamed from: u */
    private int m16113u() {
        return (this.f14306e - this.f14309h) + 1;
    }

    /* renamed from: v */
    private void m16114v() {
        if (!this.f14304c) {
            throw m16099a(f14302z[30]);
        }
    }

    /* renamed from: w */
    private void m16115w() {
        while (true) {
            if (this.f14306e < this.f14307f || m16105b(1)) {
                char[] cArr = this.f14305d;
                int i = this.f14306e;
                this.f14306e = i + 1;
                char c = cArr[i];
                if (c == '\n') {
                    this.f14308g++;
                    this.f14309h = this.f14306e;
                    return;
                } else if (c == '\r') {
                    return;
                }
            } else {
                return;
            }
        }
    }

    /* renamed from: x */
    private char m16116x() {
        if (this.f14306e != this.f14307f || m16105b(1)) {
            char[] cArr = this.f14305d;
            int i = this.f14306e;
            this.f14306e = i + 1;
            char c = cArr[i];
            switch (c) {
                case '\n':
                    this.f14308g++;
                    this.f14309h = this.f14306e;
                    return c;
                case 'b':
                    return '\b';
                case 'f':
                    return '\f';
                case 'n':
                    return '\n';
                case 'r':
                    return '\r';
                case AVException.OBJECT_TOO_LARGE /*116*/:
                    return '\t';
                case 'u':
                    if (this.f14306e + 4 <= this.f14307f || m16105b(4)) {
                        int i2 = this.f14306e;
                        int i3 = i2 + 4;
                        int i4 = i2;
                        c = '\u0000';
                        for (i = i4; i < i3; i++) {
                            char c2 = this.f14305d[i];
                            c = (char) (c << 4);
                            if (c2 >= '0' && c2 <= '9') {
                                c = (char) (c + (c2 - 48));
                            } else if (c2 >= 'a' && c2 <= 'f') {
                                c = (char) (c + ((c2 - 97) + 10));
                            } else if (c2 < 'A' || c2 > 'F') {
                                throw new NumberFormatException(new StringBuilder(f14302z[27]).append(new String(this.f14305d, this.f14306e, 4)).toString());
                            } else {
                                c = (char) (c + ((c2 - 65) + 10));
                            }
                        }
                        this.f14306e += 4;
                        return c;
                    }
                    throw m16099a(f14302z[28]);
                default:
                    return c;
            }
        }
        throw m16099a(f14302z[28]);
    }

    /* renamed from: a */
    public void mo5677a() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        if (i == 3) {
            m16100a(1);
            this.f14317p[this.f14315n - 1] = 0;
            this.f14310i = 0;
            return;
        }
        throw new IllegalStateException(new StringBuilder(f14302z[26]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
    }

    /* renamed from: a */
    public final void m16118a(boolean z) {
        this.f14304c = z;
    }

    /* renamed from: b */
    public void mo5678b() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        if (i == 4) {
            this.f14315n--;
            int[] iArr = this.f14317p;
            int i2 = this.f14315n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.f14310i = 0;
            return;
        }
        throw new IllegalStateException(new StringBuilder(f14302z[25]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
    }

    /* renamed from: c */
    public void mo5679c() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        if (i == 1) {
            m16100a(3);
            this.f14310i = 0;
            return;
        }
        throw new IllegalStateException(new StringBuilder(f14302z[17]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
    }

    public void close() {
        this.f14310i = 0;
        this.f14314m[0] = 8;
        this.f14315n = 1;
        this.f14303b.close();
    }

    /* renamed from: d */
    public void mo5681d() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        if (i == 2) {
            this.f14315n--;
            this.f14316o[this.f14315n] = null;
            int[] iArr = this.f14317p;
            int i2 = this.f14315n - 1;
            iArr[i2] = iArr[i2] + 1;
            this.f14310i = 0;
            return;
        }
        throw new IllegalStateException(new StringBuilder(f14302z[9]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
    }

    /* renamed from: e */
    public boolean mo5682e() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        return (i == 2 || i == 4) ? false : true;
    }

    /* renamed from: f */
    public C3979c mo5683f() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        switch (i) {
            case 1:
                return C3979c.f14322c;
            case 2:
                return C3979c.f14323d;
            case 3:
                return C3979c.f14320a;
            case 4:
                return C3979c.f14321b;
            case 5:
            case 6:
                return C3979c.f14327h;
            case 7:
                return C3979c.f14328i;
            case 8:
            case 9:
            case 10:
            case 11:
                return C3979c.f14325f;
            case 12:
            case 13:
            case 14:
                return C3979c.f14324e;
            case 15:
            case 16:
                return C3979c.f14326g;
            case 17:
                return C3979c.f14329j;
            default:
                throw new AssertionError();
        }
    }

    /* renamed from: g */
    public String mo5684g() {
        String t;
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        if (i == 14) {
            t = m16112t();
        } else if (i == 12) {
            t = m16104b((char) CoreConstants.SINGLE_QUOTE_CHAR);
        } else if (i == 13) {
            t = m16104b((char) CoreConstants.DOUBLE_QUOTE_CHAR);
        } else {
            throw new IllegalStateException(new StringBuilder(f14302z[13]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
        }
        this.f14310i = 0;
        this.f14316o[this.f14315n - 1] = t;
        return t;
    }

    /* renamed from: h */
    public String mo5685h() {
        String t;
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        if (i == 10) {
            t = m16112t();
        } else if (i == 8) {
            t = m16104b((char) CoreConstants.SINGLE_QUOTE_CHAR);
        } else if (i == 9) {
            t = m16104b((char) CoreConstants.DOUBLE_QUOTE_CHAR);
        } else if (i == 11) {
            t = this.f14313l;
            this.f14313l = null;
        } else if (i == 15) {
            t = Long.toString(this.f14311j);
        } else if (i == 16) {
            t = new String(this.f14305d, this.f14306e, this.f14312k);
            this.f14306e += this.f14312k;
        } else {
            throw new IllegalStateException(new StringBuilder(f14302z[31]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
        }
        this.f14310i = 0;
        int[] iArr = this.f14317p;
        int i2 = this.f14315n - 1;
        iArr[i2] = iArr[i2] + 1;
        return t;
    }

    /* renamed from: i */
    public boolean mo5686i() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        if (i == 5) {
            this.f14310i = 0;
            int[] iArr = this.f14317p;
            i = this.f14315n - 1;
            iArr[i] = iArr[i] + 1;
            return true;
        } else if (i == 6) {
            this.f14310i = 0;
            int[] iArr2 = this.f14317p;
            int i2 = this.f14315n - 1;
            iArr2[i2] = iArr2[i2] + 1;
            return false;
        } else {
            throw new IllegalStateException(new StringBuilder(f14302z[35]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
        }
    }

    /* renamed from: j */
    public void mo5687j() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        if (i == 7) {
            this.f14310i = 0;
            int[] iArr = this.f14317p;
            int i2 = this.f14315n - 1;
            iArr[i2] = iArr[i2] + 1;
            return;
        }
        throw new IllegalStateException(new StringBuilder(f14302z[29]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
    }

    /* renamed from: k */
    public double mo5688k() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        if (i == 15) {
            this.f14310i = 0;
            int[] iArr = this.f14317p;
            int i2 = this.f14315n - 1;
            iArr[i2] = iArr[i2] + 1;
            return (double) this.f14311j;
        }
        if (i == 16) {
            this.f14313l = new String(this.f14305d, this.f14306e, this.f14312k);
            this.f14306e += this.f14312k;
        } else if (i == 8 || i == 9) {
            this.f14313l = m16104b(i == 8 ? CoreConstants.SINGLE_QUOTE_CHAR : CoreConstants.DOUBLE_QUOTE_CHAR);
        } else if (i == 10) {
            this.f14313l = m16112t();
        } else if (i != 11) {
            throw new IllegalStateException(new StringBuilder(f14302z[15]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
        }
        this.f14310i = 11;
        double parseDouble = Double.parseDouble(this.f14313l);
        if (this.f14304c || !(Double.isNaN(parseDouble) || Double.isInfinite(parseDouble))) {
            this.f14313l = null;
            this.f14310i = 0;
            int[] iArr2 = this.f14317p;
            int i3 = this.f14315n - 1;
            iArr2[i3] = iArr2[i3] + 1;
            return parseDouble;
        }
        throw new C3981e(new StringBuilder(f14302z[14]).append(parseDouble).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
    }

    /* renamed from: l */
    public long mo5689l() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        if (i == 15) {
            this.f14310i = 0;
            int[] iArr = this.f14317p;
            int i2 = this.f14315n - 1;
            iArr[i2] = iArr[i2] + 1;
            return this.f14311j;
        }
        long parseLong;
        if (i == 16) {
            this.f14313l = new String(this.f14305d, this.f14306e, this.f14312k);
            this.f14306e += this.f14312k;
        } else if (i == 8 || i == 9) {
            this.f14313l = m16104b(i == 8 ? CoreConstants.SINGLE_QUOTE_CHAR : CoreConstants.DOUBLE_QUOTE_CHAR);
            try {
                parseLong = Long.parseLong(this.f14313l);
                this.f14310i = 0;
                int[] iArr2 = this.f14317p;
                int i3 = this.f14315n - 1;
                iArr2[i3] = iArr2[i3] + 1;
                return parseLong;
            } catch (NumberFormatException e) {
            }
        } else {
            throw new IllegalStateException(new StringBuilder(f14302z[16]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
        }
        this.f14310i = 11;
        double parseDouble = Double.parseDouble(this.f14313l);
        parseLong = (long) parseDouble;
        if (((double) parseLong) != parseDouble) {
            throw new NumberFormatException(new StringBuilder(f14302z[16]).append(this.f14313l).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
        }
        this.f14313l = null;
        this.f14310i = 0;
        iArr2 = this.f14317p;
        i3 = this.f14315n - 1;
        iArr2[i3] = iArr2[i3] + 1;
        return parseLong;
    }

    /* renamed from: m */
    public int mo5690m() {
        int i = this.f14310i;
        if (i == 0) {
            i = mo5692o();
        }
        int[] iArr;
        int i2;
        if (i == 15) {
            i = (int) this.f14311j;
            if (this.f14311j != ((long) i)) {
                throw new NumberFormatException(new StringBuilder(f14302z[18]).append(this.f14311j).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
            }
            this.f14310i = 0;
            iArr = this.f14317p;
            i2 = this.f14315n - 1;
            iArr[i2] = iArr[i2] + 1;
        } else {
            if (i == 16) {
                this.f14313l = new String(this.f14305d, this.f14306e, this.f14312k);
                this.f14306e += this.f14312k;
            } else if (i == 8 || i == 9) {
                this.f14313l = m16104b(i == 8 ? CoreConstants.SINGLE_QUOTE_CHAR : CoreConstants.DOUBLE_QUOTE_CHAR);
                try {
                    i = Integer.parseInt(this.f14313l);
                    this.f14310i = 0;
                    iArr = this.f14317p;
                    i2 = this.f14315n - 1;
                    iArr[i2] = iArr[i2] + 1;
                } catch (NumberFormatException e) {
                }
            } else {
                throw new IllegalStateException(new StringBuilder(f14302z[18]).append(mo5683f()).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
            }
            this.f14310i = 11;
            double parseDouble = Double.parseDouble(this.f14313l);
            i = (int) parseDouble;
            if (((double) i) != parseDouble) {
                throw new NumberFormatException(new StringBuilder(f14302z[18]).append(this.f14313l).append(f14302z[10]).append(this.f14308g + 1).append(f14302z[7]).append(m16113u()).append(f14302z[8]).append(m16133q()).toString());
            }
            this.f14313l = null;
            this.f14310i = 0;
            iArr = this.f14317p;
            i2 = this.f14315n - 1;
            iArr[i2] = iArr[i2] + 1;
        }
        return i;
    }

    /* renamed from: n */
    public void mo5691n() {
        int i = 0;
        do {
            int i2 = this.f14310i;
            if (i2 == 0) {
                i2 = mo5692o();
            }
            if (i2 == 3) {
                m16100a(1);
                i++;
            } else if (i2 == 1) {
                m16100a(3);
                i++;
            } else if (i2 == 4) {
                this.f14315n--;
                i--;
            } else if (i2 == 2) {
                this.f14315n--;
                i--;
            } else if (i2 == 14 || i2 == 10) {
                do {
                    i2 = 0;
                    while (this.f14306e + i2 < this.f14307f) {
                        switch (this.f14305d[this.f14306e + i2]) {
                            case '\t':
                            case '\n':
                            case '\f':
                            case '\r':
                            case ' ':
                            case ',':
                            case ':':
                            case '[':
                            case ']':
                            case AVException.INVALID_ACL /*123*/:
                            case AVException.INVALID_EMAIL_ADDRESS /*125*/:
                                break;
                            case '#':
                            case '/':
                            case ';':
                            case '=':
                            case '\\':
                                m16114v();
                                break;
                            default:
                                i2++;
                        }
                        this.f14306e = i2 + this.f14306e;
                    }
                    this.f14306e = i2 + this.f14306e;
                } while (m16105b(1));
            } else if (i2 == 8 || i2 == 12) {
                m16107c((char) CoreConstants.SINGLE_QUOTE_CHAR);
            } else if (i2 == 9 || i2 == 13) {
                m16107c((char) CoreConstants.DOUBLE_QUOTE_CHAR);
            } else if (i2 == 16) {
                this.f14306e += this.f14312k;
            }
            this.f14310i = 0;
        } while (i != 0);
        int[] iArr = this.f14317p;
        int i3 = this.f14315n - 1;
        iArr[i3] = iArr[i3] + 1;
        this.f14316o[this.f14315n - 1] = f14302z[20];
    }

    /* renamed from: p */
    public final boolean m16132p() {
        return this.f14304c;
    }

    /* renamed from: q */
    public final String m16133q() {
        StringBuilder stringBuilder = new StringBuilder("$");
        int i = this.f14315n;
        for (int i2 = 0; i2 < i; i2++) {
            switch (this.f14314m[i2]) {
                case 1:
                case 2:
                    stringBuilder.append('[').append(this.f14317p[i2]).append(']');
                    break;
                case 3:
                case 4:
                case 5:
                    stringBuilder.append(CoreConstants.DOT);
                    if (this.f14316o[i2] == null) {
                        break;
                    }
                    stringBuilder.append(this.f14316o[i2]);
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }

    public String toString() {
        return getClass().getSimpleName() + f14302z[10] + (this.f14308g + 1) + f14302z[7] + m16113u();
    }
}
