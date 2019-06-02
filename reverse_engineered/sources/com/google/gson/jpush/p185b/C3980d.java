package com.google.gson.jpush.p185b;

import ch.qos.logback.core.CoreConstants;
import com.alipay.sdk.util.C0880h;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.io.Writer;

/* renamed from: com.google.gson.jpush.b.d */
public class C3980d implements Closeable, Flushable {
    /* renamed from: a */
    private static final String[] f14332a = new String[128];
    /* renamed from: b */
    private static final String[] f14333b;
    /* renamed from: z */
    private static final String[] f14334z;
    /* renamed from: c */
    private final Writer f14335c;
    /* renamed from: d */
    private int[] f14336d = new int[32];
    /* renamed from: e */
    private int f14337e = 0;
    /* renamed from: f */
    private String f14338f;
    /* renamed from: g */
    private String f14339g;
    /* renamed from: h */
    private boolean f14340h;
    /* renamed from: i */
    private boolean f14341i;
    /* renamed from: j */
    private String f14342j;
    /* renamed from: k */
    private boolean f14343k;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 18;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "475\u000b";
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
            case 0: goto L_0x0118;
            case 1: goto L_0x011c;
            case 2: goto L_0x0120;
            case 3: goto L_0x0124;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0014'*\u0013\u001f4%y\u0017\u00045 5\u0002\u001bt";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0010\u0011\u0016)V77*\u0013V2#/\u0002V5,5\u001eV5,<G\u000252t\u000b\u0013,'5G\u0000;.,\u0002X";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0010\u0011\u0016)V77*\u0013V)68\u0015\u0002z50\u0013\u001ez#7G\u0017(08\u001eV50y\u0006\u0018z-;\r\u001396w";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u001016\t!(+-\u0002\u0004z+*G\u00156-*\u0002\u0012t";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u001e#7\u0000\u001a3,>G\u0018;/<]V";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0013,:\b\u001b*.<\u0013\u0013z&6\u0004\u00037'7\u0013";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "w\u000b7\u0001\u001f4+-\u001e";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u001474\u0002\u00043!y\u0011\u001767<\u0014V77*\u0013V8'y\u0001\u001f4+-\u0002Zz ,\u0013V-#*G";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0013,?\u000e\u001836 ";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0014#\u0017";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "`b";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = ".0,\u0002";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "<#5\u0014\u0013";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "57-GKgb7\u0012\u001a6";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\u00067kWDc";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u00067kWDb";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "4#4\u0002Vgy\t\u00036.";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        f14334z = r4;
        r0 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r0 = new java.lang.String[r0];
        f14332a = r0;
        r3 = 0;
    L_0x00ea:
        r0 = 31;
        if (r3 > r0) goto L_0x0157;
    L_0x00ee:
        r2 = f14332a;
        r1 = "\u00067|WB\"";
        r0 = -1;
        r4 = r2;
        r2 = r3;
    L_0x00f5:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0136;
    L_0x00fe:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0103:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0128;
            case 1: goto L_0x012b;
            case 2: goto L_0x012e;
            case 3: goto L_0x0131;
            default: goto L_0x010a;
        };
    L_0x010a:
        r9 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
    L_0x010c:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x0134;
    L_0x0114:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0103;
    L_0x0118:
        r9 = 90;
        goto L_0x0020;
    L_0x011c:
        r9 = 66;
        goto L_0x0020;
    L_0x0120:
        r9 = 89;
        goto L_0x0020;
    L_0x0124:
        r9 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x0020;
    L_0x0128:
        r9 = 90;
        goto L_0x010c;
    L_0x012b:
        r9 = 66;
        goto L_0x010c;
    L_0x012e:
        r9 = 89;
        goto L_0x010c;
    L_0x0131:
        r9 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x010c;
    L_0x0134:
        r5 = r1;
        r1 = r7;
    L_0x0136:
        if (r5 > r6) goto L_0x00fe;
    L_0x0138:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x015f;
            case 1: goto L_0x0169;
            case 2: goto L_0x0173;
            case 3: goto L_0x017e;
            case 4: goto L_0x0189;
            case 5: goto L_0x0194;
            case 6: goto L_0x019f;
            case 7: goto L_0x01b7;
            case 8: goto L_0x01c3;
            case 9: goto L_0x01cf;
            case 10: goto L_0x01db;
            case 11: goto L_0x01e7;
            default: goto L_0x0144;
        };
    L_0x0144:
        r0 = 1;
        r0 = new java.lang.Object[r0];
        r5 = 0;
        r6 = java.lang.Integer.valueOf(r3);
        r0[r5] = r6;
        r0 = java.lang.String.format(r1, r0);
        r4[r2] = r0;
        r3 = r3 + 1;
        goto L_0x00ea;
    L_0x0157:
        r4 = f14332a;
        r2 = 34;
        r1 = "\u0006`";
        r0 = 0;
        goto L_0x00f5;
    L_0x015f:
        r4[r2] = r1;
        r4 = f14332a;
        r2 = 92;
        r1 = "\u0006\u001e";
        r0 = 1;
        goto L_0x00f5;
    L_0x0169:
        r4[r2] = r1;
        r4 = f14332a;
        r2 = 9;
        r1 = "\u00066";
        r0 = 2;
        goto L_0x00f5;
    L_0x0173:
        r4[r2] = r1;
        r4 = f14332a;
        r2 = 8;
        r1 = "\u0006 ";
        r0 = 3;
        goto L_0x00f5;
    L_0x017e:
        r4[r2] = r1;
        r4 = f14332a;
        r2 = 10;
        r1 = "\u0006,";
        r0 = 4;
        goto L_0x00f5;
    L_0x0189:
        r4[r2] = r1;
        r4 = f14332a;
        r2 = 13;
        r1 = "\u00060";
        r0 = 5;
        goto L_0x00f5;
    L_0x0194:
        r4[r2] = r1;
        r4 = f14332a;
        r2 = 12;
        r1 = "\u0006$";
        r0 = 6;
        goto L_0x00f5;
    L_0x019f:
        r4[r2] = r1;
        r0 = f14332a;
        r0 = r0.clone();
        r0 = (java.lang.String[]) r0;
        f14333b = r0;
        r4 = 60;
        r2 = "\u00067iWE9";
        r1 = 7;
        r11 = r1;
        r1 = r2;
        r2 = r4;
        r4 = r0;
        r0 = r11;
        goto L_0x00f5;
    L_0x01b7:
        r4[r2] = r1;
        r4 = f14333b;
        r2 = 62;
        r1 = "\u00067iWE?";
        r0 = 8;
        goto L_0x00f5;
    L_0x01c3:
        r4[r2] = r1;
        r4 = f14333b;
        r2 = 38;
        r1 = "\u00067iWDl";
        r0 = 9;
        goto L_0x00f5;
    L_0x01cf:
        r4[r2] = r1;
        r4 = f14333b;
        r2 = 61;
        r1 = "\u00067iWE>";
        r0 = 10;
        goto L_0x00f5;
    L_0x01db:
        r4[r2] = r1;
        r4 = f14333b;
        r2 = 39;
        r1 = "\u00067iWDm";
        r0 = 11;
        goto L_0x00f5;
    L_0x01e7:
        r4[r2] = r1;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.b.d.<clinit>():void");
    }

    public C3980d(Writer writer) {
        m16139a(6);
        this.f14339g = ":";
        this.f14343k = true;
        if (writer == null) {
            throw new NullPointerException(f14334z[14]);
        }
        this.f14335c = writer;
    }

    /* renamed from: a */
    private int mo5698a() {
        if (this.f14337e != 0) {
            return this.f14336d[this.f14337e - 1];
        }
        throw new IllegalStateException(f14334z[4]);
    }

    /* renamed from: a */
    private C3980d m16137a(int i, int i2, String str) {
        int a = mo5698a();
        if (a != i2 && a != i) {
            throw new IllegalStateException(f14334z[1]);
        } else if (this.f14342j != null) {
            throw new IllegalStateException(new StringBuilder(f14334z[5]).append(this.f14342j).toString());
        } else {
            this.f14337e--;
            if (a == i2) {
                m16144k();
            }
            this.f14335c.write(str);
            return this;
        }
    }

    /* renamed from: a */
    private C3980d m16138a(int i, String str) {
        m16142e(true);
        m16139a(i);
        this.f14335c.write(str);
        return this;
    }

    /* renamed from: a */
    private void m16139a(int i) {
        if (this.f14337e == this.f14336d.length) {
            Object obj = new int[(this.f14337e * 2)];
            System.arraycopy(this.f14336d, 0, obj, 0, this.f14337e);
            this.f14336d = obj;
        }
        int[] iArr = this.f14336d;
        int i2 = this.f14337e;
        this.f14337e = i2 + 1;
        iArr[i2] = i;
    }

    /* renamed from: b */
    private void m16140b(int i) {
        this.f14336d[this.f14337e - 1] = i;
    }

    /* renamed from: d */
    private void m16141d(String str) {
        int i = 0;
        String[] strArr = this.f14341i ? f14333b : f14332a;
        this.f14335c.write("\"");
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            String str2;
            if (charAt < '') {
                str2 = strArr[charAt];
                if (str2 == null) {
                }
                if (i < i2) {
                    this.f14335c.write(str, i, i2 - i);
                }
                this.f14335c.write(str2);
                i = i2 + 1;
            } else {
                if (charAt == ' ') {
                    str2 = f14334z[16];
                } else if (charAt == ' ') {
                    str2 = f14334z[15];
                }
                if (i < i2) {
                    this.f14335c.write(str, i, i2 - i);
                }
                this.f14335c.write(str2);
                i = i2 + 1;
            }
        }
        if (i < length) {
            this.f14335c.write(str, i, length - i);
        }
        this.f14335c.write("\"");
    }

    /* renamed from: e */
    private void m16142e(boolean z) {
        switch (mo5698a()) {
            case 1:
                m16140b(2);
                m16144k();
                return;
            case 2:
                this.f14335c.append(CoreConstants.COMMA_CHAR);
                m16144k();
                return;
            case 4:
                this.f14335c.append(this.f14339g);
                m16140b(5);
                return;
            case 6:
                break;
            case 7:
                if (!this.f14340h) {
                    throw new IllegalStateException(f14334z[2]);
                }
                break;
            default:
                throw new IllegalStateException(f14334z[1]);
        }
        if (this.f14340h || z) {
            m16140b(7);
            return;
        }
        throw new IllegalStateException(f14334z[3]);
    }

    /* renamed from: j */
    private void m16143j() {
        if (this.f14342j != null) {
            int a = mo5698a();
            if (a == 5) {
                this.f14335c.write(44);
            } else if (a != 3) {
                throw new IllegalStateException(f14334z[1]);
            }
            m16144k();
            m16140b(4);
            m16141d(this.f14342j);
            this.f14342j = null;
        }
    }

    /* renamed from: k */
    private void m16144k() {
        if (this.f14338f != null) {
            this.f14335c.write("\n");
            int i = this.f14337e;
            for (int i2 = 1; i2 < i; i2++) {
                this.f14335c.write(this.f14338f);
            }
        }
    }

    /* renamed from: a */
    public C3980d mo5694a(long j) {
        m16143j();
        m16142e(false);
        this.f14335c.write(Long.toString(j));
        return this;
    }

    /* renamed from: a */
    public C3980d mo5695a(Number number) {
        if (number == null) {
            return mo5705f();
        }
        m16143j();
        CharSequence obj = number.toString();
        if (this.f14340h || !(obj.equals(f14334z[7]) || obj.equals(f14334z[9]) || obj.equals(f14334z[10]))) {
            m16142e(false);
            this.f14335c.append(obj);
            return this;
        }
        throw new IllegalArgumentException(new StringBuilder(f14334z[8]).append(number).toString());
    }

    /* renamed from: a */
    public C3980d mo5696a(String str) {
        if (str == null) {
            throw new NullPointerException(f14334z[17]);
        } else if (this.f14342j != null) {
            throw new IllegalStateException();
        } else if (this.f14337e == 0) {
            throw new IllegalStateException(f14334z[4]);
        } else {
            this.f14342j = str;
            return this;
        }
    }

    /* renamed from: a */
    public C3980d mo5697a(boolean z) {
        m16143j();
        m16142e(false);
        this.f14335c.write(z ? f14334z[12] : f14334z[13]);
        return this;
    }

    /* renamed from: b */
    public C3980d mo5699b() {
        m16143j();
        return m16138a(1, "[");
    }

    /* renamed from: b */
    public C3980d mo5700b(String str) {
        if (str == null) {
            return mo5705f();
        }
        m16143j();
        m16142e(false);
        m16141d(str);
        return this;
    }

    /* renamed from: b */
    public final void m16151b(boolean z) {
        this.f14340h = z;
    }

    /* renamed from: c */
    public C3980d mo5701c() {
        return m16137a(1, 2, "]");
    }

    /* renamed from: c */
    public final void m16153c(String str) {
        if (str.length() == 0) {
            this.f14338f = null;
            this.f14339g = ":";
            return;
        }
        this.f14338f = str;
        this.f14339g = f14334z[11];
    }

    /* renamed from: c */
    public final void m16154c(boolean z) {
        this.f14341i = z;
    }

    public void close() {
        this.f14335c.close();
        int i = this.f14337e;
        if (i > 1 || (i == 1 && this.f14336d[i - 1] != 7)) {
            throw new IOException(f14334z[6]);
        }
        this.f14337e = 0;
    }

    /* renamed from: d */
    public C3980d mo5703d() {
        m16143j();
        return m16138a(3, "{");
    }

    /* renamed from: d */
    public final void m16156d(boolean z) {
        this.f14343k = z;
    }

    /* renamed from: e */
    public C3980d mo5704e() {
        return m16137a(3, 5, C0880h.f2222d);
    }

    /* renamed from: f */
    public C3980d mo5705f() {
        if (this.f14342j != null) {
            if (this.f14343k) {
                m16143j();
            } else {
                this.f14342j = null;
                return this;
            }
        }
        m16142e(false);
        this.f14335c.write(f14334z[0]);
        return this;
    }

    public void flush() {
        if (this.f14337e == 0) {
            throw new IllegalStateException(f14334z[4]);
        }
        this.f14335c.flush();
    }

    /* renamed from: g */
    public final boolean m16159g() {
        return this.f14340h;
    }

    /* renamed from: h */
    public final boolean m16160h() {
        return this.f14341i;
    }

    /* renamed from: i */
    public final boolean m16161i() {
        return this.f14343k;
    }
}
