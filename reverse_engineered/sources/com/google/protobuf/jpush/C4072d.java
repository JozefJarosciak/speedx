package com.google.protobuf.jpush;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.protobuf.jpush.d */
public final class C4072d {
    /* renamed from: z */
    private static final String[] f14613z;
    /* renamed from: a */
    private final byte[] f14614a;
    /* renamed from: b */
    private int f14615b;
    /* renamed from: c */
    private int f14616c;
    /* renamed from: d */
    private int f14617d;
    /* renamed from: e */
    private final InputStream f14618e;
    /* renamed from: f */
    private int f14619f;
    /* renamed from: g */
    private int f14620g;
    /* renamed from: h */
    private int f14621h = Integer.MAX_VALUE;
    /* renamed from: i */
    private int f14622i;
    /* renamed from: j */
    private int f14623j = 64;
    /* renamed from: k */
    private int f14624k = 67108864;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r12 = 41;
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "\u0003t\t@E\u001dS\u001aOO\u0014cG\u0000\t\u0012p\u0003EL\u00151\u0018AL\u001f1\r\\O\u0017t\u001d\t^\u0010b\u0001\u000e]Qt\u0002Y]\b?";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000d:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
    L_0x0014:
        r8 = r1;
    L_0x0015:
        r9 = r3;
        r10 = r8;
        r14 = r7;
        r7 = r3;
        r3 = r14;
    L_0x001a:
        r13 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0056;
            case 1: goto L_0x0059;
            case 2: goto L_0x005c;
            case 3: goto L_0x005f;
            default: goto L_0x0021;
        };
    L_0x0021:
        r11 = r12;
    L_0x0022:
        r11 = r11 ^ r13;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002e;
    L_0x002a:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x001a;
    L_0x002e:
        r7 = r3;
        r3 = r9;
    L_0x0030:
        if (r7 > r8) goto L_0x0015;
    L_0x0032:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0051;
            default: goto L_0x003e;
        };
    L_0x003e:
        r5[r4] = r3;
        r0 = "8\u001f\\]\"e\u001dLH\u001c2\u001dLH\u00159\rP]\u0014J2\u0000\t\u0003t\u001b\\[\u001ft\u000b\t@\u001fg\u000eE@\u00151\u001dLZ\u0004}\u001b\u0013\t";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000d;
    L_0x0047:
        r5[r4] = r3;
        r3 = 2;
        r0 = "{E\u0007L\t8\u001f\\]\"e\u001dLH\u001c1\u0006DY\u001dt\u0002LG\u0005p\u001b@F\u001f1\u0006Z\t\u0013d\bNP_";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000d;
    L_0x0051:
        r5[r4] = r3;
        f14613z = r6;
        return;
    L_0x0056:
        r11 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x0022;
    L_0x0059:
        r11 = 17;
        goto L_0x0022;
    L_0x005c:
        r11 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x0022;
    L_0x005f:
        r11 = r12;
        goto L_0x0022;
    L_0x0061:
        r8 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.jpush.d.<clinit>():void");
    }

    private C4072d(byte[] bArr, int i, int i2) {
        this.f14614a = bArr;
        this.f14615b = i + i2;
        this.f14617d = i;
        this.f14620g = -i;
        this.f14618e = null;
    }

    /* renamed from: a */
    public static C4072d m16427a(byte[] bArr, int i, int i2) {
        C4072d c4072d = new C4072d(bArr, i, i2);
        try {
            c4072d.m16438c(i2);
            return c4072d;
        } catch (Throwable e) {
            throw new IllegalArgumentException(e);
        }
    }

    /* renamed from: a */
    private boolean m16428a(boolean z) {
        if (this.f14617d < this.f14615b) {
            throw new IllegalStateException(f14613z[0]);
        } else if (this.f14620g + this.f14615b != this.f14621h) {
            this.f14620g += this.f14615b;
            this.f14617d = 0;
            this.f14615b = this.f14618e == null ? -1 : this.f14618e.read(this.f14614a);
            if (this.f14615b == 0 || this.f14615b < -1) {
                throw new IllegalStateException(new StringBuilder(f14613z[1]).append(this.f14615b).append(f14613z[2]).toString());
            } else if (this.f14615b == -1) {
                this.f14615b = 0;
                if (!z) {
                    return false;
                }
                throw C4078j.m16468a();
            } else {
                m16430h();
                int i = (this.f14620g + this.f14615b) + this.f14616c;
                if (i <= this.f14624k && i >= 0) {
                    return true;
                }
                throw C4078j.m16475h();
            }
        } else if (!z) {
            return false;
        } else {
            throw C4078j.m16468a();
        }
    }

    /* renamed from: e */
    private void m16429e(int i) {
        if (i < 0) {
            throw C4078j.m16469b();
        } else if ((this.f14620g + this.f14617d) + i > this.f14621h) {
            m16429e((this.f14621h - this.f14620g) - this.f14617d);
            throw C4078j.m16468a();
        } else if (i <= this.f14615b - this.f14617d) {
            this.f14617d += i;
        } else {
            int i2 = this.f14615b - this.f14617d;
            this.f14617d = this.f14615b;
            m16428a(true);
            while (i - i2 > this.f14615b) {
                i2 += this.f14615b;
                this.f14617d = this.f14615b;
                m16428a(true);
            }
            this.f14617d = i - i2;
        }
    }

    /* renamed from: h */
    private void m16430h() {
        this.f14615b += this.f14616c;
        int i = this.f14620g + this.f14615b;
        if (i > this.f14621h) {
            this.f14616c = i - this.f14621h;
            this.f14615b -= this.f14616c;
            return;
        }
        this.f14616c = 0;
    }

    /* renamed from: i */
    private byte m16431i() {
        if (this.f14617d == this.f14615b) {
            m16428a(true);
        }
        byte[] bArr = this.f14614a;
        int i = this.f14617d;
        this.f14617d = i + 1;
        return bArr[i];
    }

    /* renamed from: a */
    public final int m16432a() {
        boolean z = this.f14617d == this.f14615b && !m16428a(false);
        if (z) {
            this.f14619f = 0;
            return 0;
        }
        this.f14619f = m16442f();
        if (C4080n.m16478b(this.f14619f) != 0) {
            return this.f14619f;
        }
        throw C4078j.m16471d();
    }

    /* renamed from: a */
    public final void m16433a(int i) {
        if (this.f14619f != i) {
            throw C4078j.m16472e();
        }
    }

    /* renamed from: a */
    public final void m16434a(C4069l c4069l, C4075g c4075g) {
        int f = m16442f();
        if (this.f14622i >= this.f14623j) {
            throw C4078j.m16474g();
        }
        f = m16438c(f);
        this.f14622i++;
        c4069l.mo5719b(this, c4075g);
        m16433a(0);
        this.f14622i--;
        m16440d(f);
    }

    /* renamed from: b */
    public final long m16435b() {
        long j = 0;
        for (int i = 0; i < 64; i += 7) {
            byte i2 = m16431i();
            j |= ((long) (i2 & 127)) << i;
            if ((i2 & 128) == 0) {
                return j;
            }
        }
        throw C4078j.m16470c();
    }

    /* renamed from: b */
    public final boolean m16436b(int i) {
        switch (C4080n.m16476a(i)) {
            case 0:
                m16442f();
                return true;
            case 1:
                byte i2 = m16431i();
                byte i3 = m16431i();
                long j = (((long) i3) & 255) << 8;
                j = ((((((j | (((long) i2) & 255)) | ((((long) m16431i()) & 255) << 16)) | ((((long) m16431i()) & 255) << 24)) | ((((long) m16431i()) & 255) << 32)) | ((((long) m16431i()) & 255) << 40)) | ((((long) m16431i()) & 255) << 48)) | ((((long) m16431i()) & 255) << 56);
                return true;
            case 2:
                m16429e(m16442f());
                return true;
            case 3:
                break;
            case 4:
                return false;
            case 5:
                int i4 = (((m16431i() & 255) | ((m16431i() & 255) << 8)) | ((m16431i() & 255) << 16)) | ((m16431i() & 255) << 24);
                return true;
            default:
                throw C4078j.m16473f();
        }
        do {
            i4 = m16432a();
            if (i4 != 0) {
            }
            m16433a(C4080n.m16477a(C4080n.m16478b(i), 4));
            return true;
        } while (m16436b(i4));
        m16433a(C4080n.m16477a(C4080n.m16478b(i), 4));
        return true;
    }

    /* renamed from: c */
    public final int m16437c() {
        return m16442f();
    }

    /* renamed from: c */
    public final int m16438c(int i) {
        if (i < 0) {
            throw C4078j.m16469b();
        }
        int i2 = (this.f14620g + this.f14617d) + i;
        int i3 = this.f14621h;
        if (i2 > i3) {
            throw C4078j.m16468a();
        }
        this.f14621h = i2;
        m16430h();
        return i3;
    }

    /* renamed from: d */
    public final C4071c m16439d() {
        int f = m16442f();
        if (f == 0) {
            return C4071c.f14609a;
        }
        if (f <= this.f14615b - this.f14617d && f > 0) {
            C4071c a = C4071c.m16421a(this.f14614a, this.f14617d, f);
            this.f14617d += f;
            return a;
        } else if (f < 0) {
            throw C4078j.m16469b();
        } else if ((this.f14620g + this.f14617d) + f > this.f14621h) {
            m16429e((this.f14621h - this.f14620g) - this.f14617d);
            throw C4078j.m16468a();
        } else {
            byte[] bArr;
            if (f <= this.f14615b - this.f14617d) {
                bArr = new byte[f];
                System.arraycopy(this.f14614a, this.f14617d, bArr, 0, f);
                this.f14617d += f;
            } else if (f < 4096) {
                Object obj = new byte[f];
                r0 = this.f14615b - this.f14617d;
                System.arraycopy(this.f14614a, this.f14617d, obj, 0, r0);
                this.f14617d = this.f14615b;
                m16428a(true);
                while (f - r0 > this.f14615b) {
                    System.arraycopy(this.f14614a, 0, obj, r0, this.f14615b);
                    r0 += this.f14615b;
                    this.f14617d = this.f14615b;
                    m16428a(true);
                }
                System.arraycopy(this.f14614a, 0, obj, r0, f - r0);
                this.f14617d = f - r0;
                r0 = obj;
            } else {
                int read;
                int i = this.f14617d;
                int i2 = this.f14615b;
                this.f14620g += this.f14615b;
                this.f14617d = 0;
                this.f14615b = 0;
                r0 = f - (i2 - i);
                List<byte[]> arrayList = new ArrayList();
                int i3 = r0;
                while (i3 > 0) {
                    Object obj2 = new byte[Math.min(i3, 4096)];
                    r0 = 0;
                    while (r0 < obj2.length) {
                        read = this.f14618e == null ? -1 : this.f14618e.read(obj2, r0, obj2.length - r0);
                        if (read == -1) {
                            throw C4078j.m16468a();
                        }
                        this.f14620g += read;
                        r0 += read;
                    }
                    r0 = i3 - obj2.length;
                    arrayList.add(obj2);
                    i3 = r0;
                }
                Object obj3 = new byte[f];
                r0 = i2 - i;
                System.arraycopy(this.f14614a, i, obj3, 0, r0);
                read = r0;
                for (byte[] bArr2 : arrayList) {
                    System.arraycopy(bArr2, 0, obj3, read, bArr2.length);
                    read = bArr2.length + read;
                }
                r0 = obj3;
            }
            return C4071c.m16420a(bArr2);
        }
    }

    /* renamed from: d */
    public final void m16440d(int i) {
        this.f14621h = i;
        m16430h();
    }

    /* renamed from: e */
    public final int m16441e() {
        return m16442f();
    }

    /* renamed from: f */
    public final int m16442f() {
        byte i = m16431i();
        if (i >= (byte) 0) {
            return i;
        }
        int i2 = i & 127;
        byte i3 = m16431i();
        if (i3 >= (byte) 0) {
            return i2 | (i3 << 7);
        }
        i2 |= (i3 & 127) << 7;
        i3 = m16431i();
        if (i3 >= (byte) 0) {
            return i2 | (i3 << 14);
        }
        i2 |= (i3 & 127) << 14;
        i3 = m16431i();
        if (i3 >= (byte) 0) {
            return i2 | (i3 << 21);
        }
        i2 |= (i3 & 127) << 21;
        i3 = m16431i();
        i2 |= i3 << 28;
        if (i3 >= (byte) 0) {
            return i2;
        }
        for (int i4 = 0; i4 < 5; i4++) {
            if (m16431i() >= (byte) 0) {
                return i2;
            }
        }
        throw C4078j.m16470c();
    }

    /* renamed from: g */
    public final int m16443g() {
        if (this.f14621h == Integer.MAX_VALUE) {
            return -1;
        }
        return this.f14621h - (this.f14620g + this.f14617d);
    }
}
