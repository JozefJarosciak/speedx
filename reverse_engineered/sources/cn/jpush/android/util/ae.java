package cn.jpush.android.util;

import cn.jpush.android.api.C0408d;
import java.util.Set;
import java.util.regex.Pattern;

public final class ae {
    /* renamed from: a */
    public static final Pattern f952a;
    /* renamed from: b */
    public static final Pattern f953b;
    /* renamed from: c */
    public static final Pattern f954c;
    /* renamed from: d */
    public static final Pattern f955d;
    /* renamed from: e */
    public static final Pattern f956e;
    /* renamed from: f */
    public static final Pattern f957f;
    /* renamed from: z */
    private static final String[] f958z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 3;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "A@乧2鿚/6^~ReZJE >[D;Y50Z1\u0003￺¾:dO3/Wb[";
        r0 = -1;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0075;
            case 1: goto L_0x0078;
            case 2: goto L_0x007b;
            case 3: goto L_0x007e;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0011;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "A@乧2鿚/6^~ReZJE >[D;Y50Z1\u0003￺¾:4[";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "73O7$~6\u001d^RE+J&ß2ퟤ不2ﶰ﷯6ﾈB$~6\u001d^RE+J&ß2ퟤ不2ﶰ﷯6ﾈCRB1N5$~6\u001d^RE+J&ß2ퟤ不2ﶰ﷯6ﾈB#12L";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        f958z = r4;
        r1 = "73\u0006z\rpg\u0006m\u000f~g\u0006l\u0016~g\u0006D\u001c{~\u0001x\u0016sv\tp\u000emh\u0013j\bga:6\u00037y\u000ee\u0003}@\u0006}\u001bz}\u0000w\u0016uv\tp\rlo\u0011h\u0006eFNcW|z\u0013c\u001cpv\u001b|\u0010pk\u001b|$~x\u0003y\u0018wr\fs\u0012qt\u0015j\tgb\u001dBVc<z\u0015tv\be\"c3\u0002{\nc~<|\u001axi\u0014k\nB2\u001by$vq\fr\u0010mF\u001b7\u0018pm\u001bx$~y\u0003z\u0019xs\u000es\u0012qk\u0016m\fkn\u0010f\"6g\u000fD\u0014ru\u0015k\nBgOv\u0011yt\u001bv\u0011kg\u000eD\u001bzw\nq\u0010ni\u0014k\"6gOu\u0010}h\u001bu$zv\bo\"6g\fD\u001axs\u000er\u0011oi\u0010f\u0005Bg\u000bD\u001e}x\u000et\rlo\u0012i\u0006BgOr\u0016sg\np\u001dvg\nj\fzn\nc\u0012Dz\u0004{\u001axs\fs\u0012qt\u0017n\rlo\u0012i\bgb\u001dBVc3\t~\u0012zg\tz\u000bcu<~\u001cz}\u0000v\u0013pk\u0015j\u0005B2\u001b7\u0010m|\u001bp\u00126gOo\rpg\u0017D\u001ez}\u0000w\u0014sv\tm\fkl\u001eBVcj\u0006c\rD~\bl\nhF\u001bl$~y\u0004{\u001axs\u000eu\u0014sv\tp\rkn\u0011f\u0005BgOk\u001asg\u0013m\u001ei~\u000bc\u000bDx\u0003y\u0018wq\fs\u0012qt\u0017m\u000bil\u001dBVcn<~\u0018th\u001ee\"cm<~\u001cz|\u000eq\nBg\u0010D\u0019lF\u001b7\u0007qGJCR/a\u0010rJ)\u001bg\u0011C6;2N.yR}\f,z^~\u0015)|\u001bg\u0011C6;2G/z\fw\u001dfp\tuKyg\u001fq#2GJ&\u000b+yV.\u0006v.\u0006c\u0007qGJCR{~\u0005~O~\u001bg\u0011C6;2\u0018)lU*N{g\u001fq#2GJw\u0018}pQ~\u0015(}R,\u001d}z\u001bg\u0011C6;2\u0017sx\r)\u001efz^z\f|,\u0006c\u0007qGJCRuc\u0006s\u000f{w\u0017c\u0007qGJCRt|\u0005z\u001cwo\u0011c\u0007qGJCRex\fe\u001ew2\u001bf$zo\u0012B\u0003e@\u0006r\bB2";
        r0 = -1;
    L_0x0052:
        r1 = r1.toCharArray();
        r2 = r1.length;
        r3 = 0;
        r4 = 1;
        if (r2 > r4) goto L_0x0106;
    L_0x005b:
        r4 = r1;
        r5 = r3;
        r11 = r2;
        r2 = r1;
        r1 = r11;
    L_0x0060:
        r7 = r2[r3];
        r6 = r5 % 5;
        switch(r6) {
            case 0: goto L_0x00f4;
            case 1: goto L_0x00f8;
            case 2: goto L_0x00fc;
            case 3: goto L_0x0100;
            default: goto L_0x0067;
        };
    L_0x0067:
        r6 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
    L_0x0069:
        r6 = r6 ^ r7;
        r6 = (char) r6;
        r2[r3] = r6;
        r3 = r5 + 1;
        if (r1 != 0) goto L_0x0104;
    L_0x0071:
        r2 = r4;
        r5 = r3;
        r3 = r1;
        goto L_0x0060;
    L_0x0075:
        r9 = 31;
        goto L_0x001f;
    L_0x0078:
        r9 = 27;
        goto L_0x001f;
    L_0x007b:
        r9 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x001f;
    L_0x007e:
        r9 = 31;
        goto L_0x001f;
    L_0x0081:
        r0 = java.util.regex.Pattern.compile(r1);
        f954c = r0;
        r0 = new java.lang.StringBuilder;
        r1 = f958z;
        r2 = 2;
        r1 = r1[r2];
        r0.<init>(r1);
        r1 = f952a;
        r6 = r0.append(r1);
        r0 = "6g";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x00ca;
    L_0x00a2:
        r3 = r0;
        r4 = r2;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x00a7:
        r7 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x00bc;
            case 1: goto L_0x00bf;
            case 2: goto L_0x00c2;
            case 3: goto L_0x00c5;
            default: goto L_0x00ae;
        };
    L_0x00ae:
        r5 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
    L_0x00b0:
        r5 = r5 ^ r7;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x00c8;
    L_0x00b8:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x00a7;
    L_0x00bc:
        r5 = 31;
        goto L_0x00b0;
    L_0x00bf:
        r5 = 27;
        goto L_0x00b0;
    L_0x00c2:
        r5 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x00b0;
    L_0x00c5:
        r5 = 31;
        goto L_0x00b0;
    L_0x00c8:
        r1 = r0;
        r0 = r3;
    L_0x00ca:
        if (r1 > r2) goto L_0x00a2;
    L_0x00cc:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        r0 = r6.append(r0);
        r1 = f954c;
        r0 = r0.append(r1);
        r1 = ")";
        r0 = r0.append(r1);
        r0 = r0.toString();
        r0 = java.util.regex.Pattern.compile(r0);
        f955d = r0;
        r1 = "DzJe>2AW2FC0;1#@GBCRC0:dN3)R)\u0002C[<~ReZJEO2\":D\u001e2a&2%/6^CRB`W3I+fOCQDzJe>2AW2FB@\u00062\u0005^6=/R&GJB\u0004/7U*\u000260";
        r0 = 2;
        goto L_0x0052;
    L_0x00f4:
        r6 = 31;
        goto L_0x0069;
    L_0x00f8:
        r6 = 27;
        goto L_0x0069;
    L_0x00fc:
        r6 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        goto L_0x0069;
    L_0x0100:
        r6 = 31;
        goto L_0x0069;
    L_0x0104:
        r2 = r1;
        r1 = r4;
    L_0x0106:
        if (r2 > r3) goto L_0x005b;
    L_0x0108:
        r2 = new java.lang.String;
        r2.<init>(r1);
        r1 = r2.intern();
        switch(r0) {
            case 0: goto L_0x011f;
            case 1: goto L_0x0081;
            case 2: goto L_0x012a;
            case 3: goto L_0x0135;
            default: goto L_0x0114;
        };
    L_0x0114:
        r0 = java.util.regex.Pattern.compile(r1);
        f952a = r0;
        r1 = "73X%Wwo\u0013o\u0003wo\u0013o\fcS\u0013k\u000fcS\u0013k\u000flg\u0015k\fog5k\fo2]CPC4O E7$]D\u001e2a&2%/6^C[C6;@#1GLC^C1;8#7GNCSC ; #9GZB\u00037$]CZDzJy>2]W2FB`UbV6`V3I+fO EC!O EDzJe>2AW2FC?;2#@GICTC:;5#8GOCVC7;$# GACBBgO EC><~RyZJYO2\":dMb2NdN3)RbV G'6@6$O7@%3X%$~6\u001d^RE+J&ß2ퟤ不2ﶰ﷯6ﾈB$~6\u001d^RE+J&ß2ퟤ不2ﶰ﷯6ﾈCRB`W3I+f;1V43X%W !\u0006z\rpg\u0006m\u000f~g\u0006l\u0016~g\u0006D\u001c{~\u0001x\u0016sv\tp\u000emh\u0013j\bga:6\u00037$]}\u0016eg\u0005D\u001e}\u0002y\u0018wr\rr\u0011pi\u0014k\thb\u001dBVc3X%\u001c~o\u001b|\u0010rg\u0004p\u0010og\u0004D\u001e|\u0001x\u0017vp\u000br\u0011pi\u0012i\u0007fa:6\u0003{@\u0002u\u0014rt\u001dB\u00037$]z\u001bjg\u0002D\u001cz|\u0015l\u000bjFNc\u0019Dr\rt\u0012pi:cW !\u0000p\tc|<~\u001d{~\u0001x\u0017vw\nq\u000fni\u0014k\nhb:6\u0003w@\fr\u0011mo\u0012B\u00037$]v\u0011yt\u001bv\u0011kg\u000eD\u001bzw\nq\u0010ni\u0014k\"6gO Eut\u0005l\u0003u@\u0002r\u0010oFNc\u0014D~\u0000w\u0016ru\u0017m\bfa:c\u0013Dz\u0005|\u0016ti\u0014k\nib:cW !\nv\u0013cv\b}\u0016cv\u0012l\u001ajv\u001br$~x\u0003z\u0018wp\u000br\u0011pk\u0016m\fkn\u0011h\u0007fa:6\u00037$]q\u001er~\u001bq\u001akg\tD\u001e|~\u0001x\u0016st\u0017m\neFNcW !\bm\u0018ct\n6\u00037$]o\rpg\u0017D\u001ez}\u0000w\u0014sv\tm\fkl\u001eBVcj\u0006c\rD~\bl\nhF\u001bl$~y\u0004{\u001axs\u000eu\u0014sv\tp\rkn\u0011f\u0005BgO Ek~\u000bc\u000bmz\u0011z\u0013co<|\u001by|\u000fu\u0014sv\tp\u000fmo\u0011h\u0005B2\u001bj$~|\fl\u0006eF\u001bi$~x\u0002x\u0016qn:c\bD}\u0014B\u00037$]g\u0011C6;2Oel\n*I{g\u001fq#2GJ.N}.\u0005lL~\"\u0006uIxg\u001fq#2GJ'O~p\u000f}\u0006tu\r+\u0019cc\tCRC6^kK}*Vf\u0016*z\u001bg\u0011C6;2\u001bzy\u0006/\u001e{g\u001fq#2GJxIh)R.\u001bcc\tCRC6\u000fx\u001dt-\u0006uHy.T}\u001d~g\u001fq#2GJw\u0013|qQ~\u0006~\"\u0002l\u001c(z\u001bg\u0011C6;2\u0015gz\u000bo\u001bsk\u001bg\u0011C6;2\u0014xy\u0002|\u0017km\u001bg\u0011C6;2\u0005|p\u001d~\u00176g\u001eD\u001akn:c\u0005Dz\nh\"62\u001b7@%3X%M*@W2JBgUDO2/:DO2\":c$/6VB$/6^B\u0004-f\u001bDN2\":DO2\":c$.6^BVC5O E-.</R*F\u001b-$/6SB$/6^B\u0003D+J.\"D+J&\"d)\u001ac$.6^B$/6^B\u0003D*J&\"c+NCQ7$]-JD+J*\"c)</R+F</R&F\u001bDO2*:DO2\":dMbg<.R&F</R&F\u001bDN2\":cO6GI7@%)RDO2.:cMD+J+\"D+J&\"c@W2NB@W2FB`Ub\u0003D*J&\"D+J&\"c@W2FB2N6W !;%#{`V3Jb2X6WC4O E7$]D\u001e2a&2%/6^¿Rퟠ亂J﷐ﶏ2￴;$#0GXCEC[;9#\"GDC\u0001C6;1#4GFCUC<;7#6GKC B2\u001b7@%GBD\u001e2}&29/6^B\u0004-fN6U6$O ECy\u001b;V";
        r0 = 0;
        goto L_0x0052;
    L_0x011f:
        r0 = java.util.regex.Pattern.compile(r1);
        f953b = r0;
        r1 = "73U*$/6RB\u0003-@W2KB@W2FBg</R.F</R&F\u001c-\u0002c@V2FB@W2FBg<.R&FNCQ7)RDO2.:cMD+J+\"D+J&\"c@W2NB@W2FB`Ub\u0003D*J&\"D+J&\"c@V2FBgW6#13U*$/6RB\u0003-@W2KB@W2FBg</R.F</R&F\u001c-\u0002c@V2FB@W2FBg<.R&F\u001b/VC5O-JD+J*\"c)</R+F</R&F\u001bDO2*:DO2\":dMbg<.R&F</R&F\u001bDO2\":6V";
        r0 = 1;
        goto L_0x0052;
    L_0x012a:
        r0 = java.util.regex.Pattern.compile(r1);
        f956e = r0;
        r1 = "7GLDO2\":4$C6GCQB1N WC3</R&FLCVDGJ?#1FM6@7@W2FB@W2FC6GCQB@W2FC6GCQB0</R&FN";
        r0 = 3;
        goto L_0x0052;
    L_0x0135:
        r0 = java.util.regex.Pattern.compile(r1);
        f957f = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.ae.<clinit>():void");
    }

    /* renamed from: a */
    public static int m1594a(Set<String> set) {
        if (set == null || set.isEmpty()) {
            return 0;
        }
        if (set.size() > 1000) {
            return C0408d.f521g;
        }
        for (String str : set) {
            if (str == null) {
                return C0408d.f519e;
            }
            if (str.getBytes().length > 40) {
                return C0408d.f520f;
            }
            if (!Pattern.compile(f958z[1]).matcher(str).matches()) {
                return C0408d.f519e;
            }
        }
        return 0;
    }

    /* renamed from: a */
    public static boolean m1595a(String str) {
        return str == null ? false : Pattern.compile(f958z[0]).matcher(str).matches();
    }

    /* renamed from: b */
    public static int m1596b(String str) {
        return (str == null || an.m1657a(str)) ? 0 : str.getBytes().length > 40 ? C0408d.f518d : !Pattern.compile(f958z[1]).matcher(str).matches() ? C0408d.f517c : 0;
    }
}
