package com.google.protobuf.jpush;

import java.io.IOException;

/* renamed from: com.google.protobuf.jpush.j */
public final class C4078j extends IOException {
    /* renamed from: z */
    private static final String[] f14634z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 8;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = ":\u0005e\u0006yM\u001dm\u0018o\u0004\u0003kJ}M\u001d~\u0005h\u0002\u000ec\u0006<\u0000\b\u0019}\n\b Jh\u0005\b,\u0003r\u001d\u0018xJy\u0003\ti\u000e<\u0018\u0003i\u0012l\b\u000ex\u000fx\u0001\u0014,\u0003rM\u0019d\u000f<\u0000\u0004h\u000ep\bMc\f<\fMj\u0003y\u0001\t\"J<9\u0005e\u0019<\u000e\u0002y\u0006xM\u0000i\u000brM\be\u001et\b\u001f,\u001et\f\u0003,\u001et\bMe\u0004l\u0018\u0019,\u0002}\u001eMn\u000fy\u0003Mx\u0018i\u0003\u000em\u001ey\tMc\u0018<\u0019\u0005m\u001e<\f\u0003,\u000fq\u000f\bh\u000ey\tMa\u000fo\u001e\fk\u000f<\u0000\u0004\u0018y\u001d\u0002~\u001ey\tMe\u001eoM\u0002{\u0004<\u0001\bb\rh\u0005C";
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
            case 0: goto L_0x0079;
            case 1: goto L_0x007c;
            case 2: goto L_0x007f;
            case 3: goto L_0x0082;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 28;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "=\u001fc\u001es\u000e\u0002`Jq\b\u001e\u000b{\bMo\u0005r\u0019\fe\u0004y\tMm\u0004<\u0004\u0003z\u000bp\u0004\t,\u001e}\nM$\u0010y\u001f\u0002%D";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "=\u001fc\u001es\u000e\u0002`Jq\b\u001e\u000b{\bM{\u000boM\u0019c\u0005<\u0001\f~\ryCM,'}\u0014Mn\u000f<\u0000\f`\u0003\u0004\u0002y\u00192MMY\u0019yM.c\u000ey\t$b\u001ai\u0019>x\u0018y\f\u0000\"\u0019y\u0019>e\u0010y!\u0004a\u0003hED,\u001esM\u0004b\tn\b\f\u000f<\u0019\u0005iJo\u0004\u0017iJp\u0004\u0000e\u001e2";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "=\u001fc\u001es\u000e\u0002`Jq\b\u001e\u000b{\bMi\u0004x@\n~\u0005i\u001dMx\u000b{M\te\u000e<\u0003\u0002xJq\f\u0019o\u0002<\b\u0015|\u000f\u0019\bhJh\f\n\"";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = ".\u0002h\u000fx$\u0003|\u001fh>\u0019~\u000f}\u0000Mi\u0004\u0002\u0018b\u001ey\u001f\bhJ}\u0003Mi\u0007~\b\th\u000fxM\u001ex\u0018u\u0003\n,\u0005nM\u0000i\u0019o\f\niJk\u0005\u0004o\u0002<\u000e\u0001m\u0003q\b\t,\u001esM\u0005m\u001cyM\u0003i\r}\u0019\u0004z\u000f<\u001e\u0004v\u000f2";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "=\u001fc\u001es\u000e\u0002`Jq\b\u001e\u000b{\bMd\u000bxM\u0019c\u0005<\u0000\fb\u0013<\u0001\bz\u000fp\u001eMc\f<\u0003\b\u001eu\u0003\n\"J< \fuJ~\bMa\u000bp\u0004\u000ee\u0005i\u001eC,JI\u001e\b,)s\t\bh#r\u001d\u0018x9h\u001f\bm\u00072\u001e\bx8y\u000e\u0018~\u0019u\u0002\u0003@\u0003q\u0004\u0019$C<\u0019\u0002,\u0003r\u000e\u001fi\u000bo\bMx\u0002yM\ti\u001ah\u0005M`\u0003q\u0004\u0019\"";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = ".\u0002h\u000fx$\u0003|\u001fh>\u0019~\u000f}\u0000Mi\u0004\u0002\u0018b\u001ey\u001f\bhJ}M\u0000m\u0006z\u0002\u001fa\u000fxM\u001bm\u0018u\u0003\u0019\"";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "=\u001fc\u001es\u000e\u0002`Jq\b\u001e\u000b{\bMx\u000b{M\u0005m\u000e<\u0004\u0003z\u000bp\u0004\t,\u001du\u001f\b,\u001ee\u001d\b\"";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        f14634z = r4;
        return;
    L_0x0079:
        r9 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        goto L_0x0020;
    L_0x007c:
        r9 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        goto L_0x0020;
    L_0x007f:
        r9 = 12;
        goto L_0x0020;
    L_0x0082:
        r9 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.jpush.j.<clinit>():void");
    }

    public C4078j(String str) {
        super(str);
    }

    /* renamed from: a */
    static C4078j m16468a() {
        return new C4078j(f14634z[0]);
    }

    /* renamed from: b */
    static C4078j m16469b() {
        return new C4078j(f14634z[4]);
    }

    /* renamed from: c */
    static C4078j m16470c() {
        return new C4078j(f14634z[6]);
    }

    /* renamed from: d */
    static C4078j m16471d() {
        return new C4078j(f14634z[1]);
    }

    /* renamed from: e */
    static C4078j m16472e() {
        return new C4078j(f14634z[3]);
    }

    /* renamed from: f */
    static C4078j m16473f() {
        return new C4078j(f14634z[7]);
    }

    /* renamed from: g */
    static C4078j m16474g() {
        return new C4078j(f14634z[5]);
    }

    /* renamed from: h */
    static C4078j m16475h() {
        return new C4078j(f14634z[2]);
    }
}
