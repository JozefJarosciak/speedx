package cn.jpush.android.data;

/* renamed from: cn.jpush.android.data.q */
public final class C0445q {
    /* renamed from: z */
    private static final String[] f729z;
    /* renamed from: a */
    private String f730a = "";
    /* renamed from: b */
    private String f731b = "";
    /* renamed from: c */
    private String f732c = "";
    /* renamed from: d */
    private String f733d = "";
    /* renamed from: e */
    private String f734e = "";
    /* renamed from: f */
    private int f735f = 0;
    /* renamed from: g */
    private int f736g = 0;
    /* renamed from: h */
    private int f737h = 0;
    /* renamed from: i */
    private int f738i = 0;
    /* renamed from: j */
    private int f739j = 0;
    /* renamed from: k */
    private int f740k = 0;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 11;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "J,j\u0018!\bxVD\u000bW<4";
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
            case 0: goto L_0x0097;
            case 1: goto L_0x009a;
            case 2: goto L_0x009d;
            case 3: goto L_0x00a0;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 84;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "5xh\u0003=\u0015x`\u0014'\"NM\u0016 \u0007,R\u0004 9f\u0005 [";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "J,o\u0016=\nimJ";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "J,z\u0003\u000b\u0005cg\u0019\u000b\u000f|4";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "J,j\u0018!\bxVFi";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "J,z\u0003\u000b\bi}J";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "J,}\u0018 \u0007`4";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "J,z\u0003\u000b\ncj\u001689hg\u0004i";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "J,j\u0018!\bxVF\u000bU1";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "J,j\u0018!\bxVFd[";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "J,z\u0003\u000b\u0015c|\u00057\u00031";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        f729z = r4;
        return;
    L_0x0097:
        r9 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        goto L_0x0020;
    L_0x009a:
        r9 = 12;
        goto L_0x0020;
    L_0x009d:
        r9 = 9;
        goto L_0x0020;
    L_0x00a0:
        r9 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.data.q.<clinit>():void");
    }

    /* renamed from: a */
    public final String m1332a() {
        return this.f730a;
    }

    /* renamed from: a */
    public final void m1333a(int i) {
        this.f735f = i;
    }

    /* renamed from: a */
    public final void m1334a(String str) {
        this.f730a = str;
    }

    /* renamed from: b */
    public final String m1335b() {
        return this.f731b;
    }

    /* renamed from: b */
    public final void m1336b(int i) {
        this.f736g = i;
    }

    /* renamed from: b */
    public final void m1337b(String str) {
        this.f731b = str;
    }

    /* renamed from: c */
    public final String m1338c() {
        return this.f732c;
    }

    /* renamed from: c */
    public final void m1339c(int i) {
        this.f737h = i;
    }

    /* renamed from: c */
    public final void m1340c(String str) {
        this.f732c = str;
    }

    /* renamed from: d */
    public final String m1341d() {
        return this.f733d;
    }

    /* renamed from: d */
    public final void m1342d(int i) {
        this.f738i = i;
    }

    /* renamed from: d */
    public final void m1343d(String str) {
        this.f733d = str;
    }

    /* renamed from: e */
    public final String m1344e() {
        return this.f734e;
    }

    /* renamed from: e */
    public final void m1345e(int i) {
        this.f739j = i;
    }

    /* renamed from: e */
    public final void m1346e(String str) {
        this.f734e = str;
    }

    /* renamed from: f */
    public final int m1347f() {
        return this.f735f;
    }

    /* renamed from: f */
    public final void m1348f(int i) {
        this.f740k = i;
    }

    /* renamed from: g */
    public final int m1349g() {
        return this.f736g;
    }

    /* renamed from: h */
    public final int m1350h() {
        return this.f737h;
    }

    /* renamed from: i */
    public final int m1351i() {
        return this.f738i;
    }

    /* renamed from: j */
    public final int m1352j() {
        return this.f739j;
    }

    /* renamed from: k */
    public final int m1353k() {
        return this.f740k;
    }

    public final String toString() {
        return new StringBuilder(f729z[1]).append(this.f730a).append(f729z[5]).append(this.f731b).append(f729z[3]).append(this.f732c).append(f729z[7]).append(this.f733d).append(f729z[10]).append(this.f734e).append(f729z[2]).append(this.f735f).append(f729z[6]).append(this.f736g).append(f729z[4]).append(this.f737h).append(f729z[8]).append(this.f738i).append(f729z[0]).append(this.f739j).append(f729z[9]).append(this.f740k).append("]").toString();
    }
}
