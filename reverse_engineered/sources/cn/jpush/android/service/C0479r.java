package cn.jpush.android.service;

import android.text.TextUtils;
import cn.jpush.android.C0404a;
import cn.jpush.android.util.ac;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.service.r */
public final class C0479r {
    /* renamed from: a */
    private static final HashMap<Integer, String> f895a;
    /* renamed from: b */
    private static final HashMap<Integer, String> f896b;
    /* renamed from: c */
    private static long f897c = 0;
    /* renamed from: z */
    private static final String[] f898z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 9;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u001e/\u0017\u0010N</\\\u001bS9.\u000e^B$%\u0019^\fk";
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
            case 0: goto L_0x00b3;
            case 1: goto L_0x00b7;
            case 2: goto L_0x00bb;
            case 3: goto L_0x00bf;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 33;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "*1\f!@/%#\fL=";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\"5\u0015\u0013D";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "?8\f\u001b";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "*1\f\u0017E";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "*\"\b\u0017N%";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "*%\u0018";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "9,\n";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u001e/\u0017\u0010N</\\\fD;.\u000e\n\u0001(.\u0018\u001b\u0001fa";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        f898z = r4;
        r3 = new java.util.HashMap;
        r3.<init>();
        f895a = r3;
        r0 = 0;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0004\n";
        r0 = -1;
    L_0x0090:
        r1 = r1.toCharArray();
        r4 = r1.length;
        r5 = 0;
        r6 = 1;
        if (r4 > r6) goto L_0x00d1;
    L_0x0099:
        r6 = r1;
        r7 = r5;
        r11 = r4;
        r4 = r1;
        r1 = r11;
    L_0x009e:
        r9 = r4[r5];
        r8 = r7 % 5;
        switch(r8) {
            case 0: goto L_0x00c3;
            case 1: goto L_0x00c6;
            case 2: goto L_0x00c9;
            case 3: goto L_0x00cc;
            default: goto L_0x00a5;
        };
    L_0x00a5:
        r8 = 33;
    L_0x00a7:
        r8 = r8 ^ r9;
        r8 = (char) r8;
        r4[r5] = r8;
        r5 = r7 + 1;
        if (r1 != 0) goto L_0x00cf;
    L_0x00af:
        r4 = r6;
        r7 = r5;
        r5 = r1;
        goto L_0x009e;
    L_0x00b3:
        r9 = 75;
        goto L_0x0020;
    L_0x00b7:
        r9 = 65;
        goto L_0x0020;
    L_0x00bb:
        r9 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        goto L_0x0020;
    L_0x00bf:
        r9 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        goto L_0x0020;
    L_0x00c3:
        r8 = 75;
        goto L_0x00a7;
    L_0x00c6:
        r8 = 65;
        goto L_0x00a7;
    L_0x00c9:
        r8 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        goto L_0x00a7;
    L_0x00cc:
        r8 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        goto L_0x00a7;
    L_0x00cf:
        r4 = r1;
        r1 = r6;
    L_0x00d1:
        if (r4 > r5) goto L_0x0099;
    L_0x00d3:
        r4 = new java.lang.String;
        r4.<init>(r1);
        r1 = r4.intern();
        switch(r0) {
            case 0: goto L_0x00ee;
            case 1: goto L_0x00fd;
            case 2: goto L_0x010c;
            case 3: goto L_0x011c;
            case 4: goto L_0x012c;
            case 5: goto L_0x013c;
            case 6: goto L_0x014c;
            case 7: goto L_0x015c;
            case 8: goto L_0x016d;
            case 9: goto L_0x017e;
            case 10: goto L_0x018f;
            case 11: goto L_0x01a0;
            case 12: goto L_0x01b1;
            case 13: goto L_0x01c7;
            case 14: goto L_0x01d8;
            case 15: goto L_0x01e9;
            case 16: goto L_0x01fa;
            case 17: goto L_0x020b;
            case 18: goto L_0x021c;
            case 19: goto L_0x022d;
            case 20: goto L_0x023e;
            case 21: goto L_0x024f;
            case 22: goto L_0x0260;
            case 23: goto L_0x0271;
            case 24: goto L_0x0282;
            case 25: goto L_0x0293;
            case 26: goto L_0x02a4;
            case 27: goto L_0x02b5;
            case 28: goto L_0x02c6;
            case 29: goto L_0x02d7;
            case 30: goto L_0x02e8;
            case 31: goto L_0x02f9;
            case 32: goto L_0x030a;
            case 33: goto L_0x031b;
            case 34: goto L_0x032c;
            case 35: goto L_0x033d;
            case 36: goto L_0x034e;
            case 37: goto L_0x035f;
            case 38: goto L_0x0370;
            case 39: goto L_0x0381;
            case 40: goto L_0x0392;
            case 41: goto L_0x03a3;
            default: goto L_0x00df;
        };
    L_0x00df:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = -1001; // 0xfffffffffffffc17 float:NaN double:NaN;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u000e9\u001f\u001bD/a\u001e\u000bG-$\u000e^R\";\u0019P\u0001\u001b-\u0019\u001fR.a\u001f\u0011O? \u001f\n\u000184\f\u000eN95R";
        r0 = 0;
        goto L_0x0090;
    L_0x00ee:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = -1000; // 0xfffffffffffffc18 float:NaN double:NaN;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\b.\u0012\u0010D(5\u0015\u0011Ok'\u001d\u0017M.%R^q'$\u001d\rDk\"\u0014\u001bB a\u0005\u0011T9a\u001f\u0011O%$\u001f\nH$/\\\u001fO/a\u000e\u001bU98\\\u0012@?$\u000e_";
        r0 = 1;
        goto L_0x0090;
    L_0x00fd:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = -998; // 0xfffffffffffffc1a float:NaN double:NaN;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0018$\u0012\u001aH%&\\\u0018@\"-\u0019\u001a\u0001$3\\\nH&$\u0013\u000bUea,\u0012D*2\u0019^s.5\u000e\u0007\u0001' \b\u001bSj";
        r0 = 2;
        goto L_0x0090;
    L_0x010c:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = -997; // 0xfffffffffffffc1b float:NaN double:NaN;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0019$\u001f\u001bH=(\u0012\u0019\u0001- \u0015\u0012D/a\u0013\f\u0001?(\u0011\u001bN>5R^q'$\u001d\rDk\u0013\u0019\nS2a\u0010\u001fU.3]";
        r0 = 3;
        goto L_0x0090;
    L_0x011c:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = -996; // 0xfffffffffffffc1c float:NaN double:NaN;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\b.\u0012\u0010D(5\u0015\u0011Ok(\u000f^B'.\u000f\u001bEea,\u0012D*2\u0019^s.5\u000e\u0007\u0001' \b\u001bSj";
        r0 = 4;
        goto L_0x0090;
    L_0x012c:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = -994; // 0xfffffffffffffc1e float:NaN double:NaN;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0019$\u000f\u000eN%2\u0019^U\",\u0019\u0011T?o\\.M. \u000f\u001b\u0001\u0019$\b\fXk-\u001d\nD9`";
        r0 = 5;
        goto L_0x0090;
    L_0x013c:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = -993; // 0xfffffffffffffc1f float:NaN double:NaN;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0002/\n\u001fM\"%\\\rN(*\u0019\n\u000fk\u0011\u0010\u001b@8$\\,D?3\u0005^M*5\u0019\f\u0000";
        r0 = 6;
        goto L_0x0090;
    L_0x014c:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = 11;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\r \u0015\u0012D/a\b\u0011\u00019$\u001b\u0017R?$\u000e_";
        r0 = 7;
        goto L_0x0090;
    L_0x015c:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = 1005; // 0x3ed float:1.408E-42 double:4.965E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0012.\t\f\u0001*1\f5D2a\u001d\u0010Ek \u0012\u001aS$(\u0018^Q*\"\u0017\u001fF.a\u0012\u001fL.a\u001d\fDk/\u0013\n\u0001& \b\u001dI.%R^q'$\u001d\rDk%\u0013\u000bC'$\\\u001dI.\"\u0017^U#$\u0011^@(\"\u0013\fE\"/\u001b^U$a=\u000eQ'(\u001f\u001fU\".\u0012^X$4\\\u001dS. \b\u001bEk.\u0012^q$3\b\u001fMe";
        r0 = 8;
        goto L_0x0090;
    L_0x016d:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = 1006; // 0x3ee float:1.41E-42 double:4.97E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0012.\t^@%%\u000e\u0011H/a\f\u001fB  \u001b\u001b\u0001% \u0011\u001b\u0001\"2\\\u0010N?a\u0019\u0006H85P^q'$\u001d\rDk3\u0019\u0019H85\u0019\f\u00012.\t\f\u0001; \u001f\u001fJ,$\\\u0010@&$\\\u0017Ok\u0011\u0013\fU*-R";
        r0 = 9;
        goto L_0x0090;
    L_0x017e:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = 1007; // 0x3ef float:1.411E-42 double:4.975E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0002/\n\u001fM\"%\\7L.(P^s.&\u0015\rU.3\\\u001fF*(\u0012P";
        r0 = 10;
        goto L_0x0090;
    L_0x018f:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = 1009; // 0x3f1 float:1.414E-42 double:4.985E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0012.\t\f\u0001*1\f5D2a\u0015\r\u00019$\u0010\u001fU.%\\\nNk \\\u0010N%l=\u0010E9.\u0015\u001a\u0001\n1\fPq'$\u001d\rDk4\u000f\u001b\u00012.\t\f\u0001\n/\u0018\fN\"%\\?Q;f\u000f^@;17\u001bXga\u0013\f\u0001*%\u0018^@%a=\u0010E9.\u0015\u001a\u0001*1\f^G$3\\\nI\"2\\\u001fQ;\n\u0019\u0007\u000f";
        r0 = 11;
        goto L_0x0090;
    L_0x01a0:
        r3.put(r2, r1);
        r3 = f895a;
        r0 = 10000; // 0x2710 float:1.4013E-41 double:4.9407E-320;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0019$\u001f\u001bH=$\u000e^E*5\u001d^Q*3\u000f\u001b\u0001.3\u000e\u0011S";
        r0 = 12;
        goto L_0x0090;
    L_0x01b1:
        r3.put(r2, r1);
        r3 = new java.util.HashMap;
        r3.<init>();
        f896b = r3;
        r0 = 995; // 0x3e3 float:1.394E-42 double:4.916E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0006$\u000f\r@,$\\4r\u0004\u000f\\\u000e@92\u0015\u0010Fk2\t\u001dB.$\u0018";
        r0 = 13;
        goto L_0x0090;
    L_0x01c7:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 996; // 0x3e4 float:1.396E-42 double:4.92E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0006$\u000f\r@,$\\4r\u0004\u000f\\\u000e@92\u0015\u0010Fk'\u001d\u0017M.%";
        r0 = 14;
        goto L_0x0090;
    L_0x01d8:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 997; // 0x3e5 float:1.397E-42 double:4.926E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0006$\u000f\r@,$\\\u001fM9$\u001d\u001aXk3\u0019\u001dD\"7\u0019\u001a\rk&\u0015\bDk4\f";
        r0 = 15;
        goto L_0x0090;
    L_0x01e9:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 998; // 0x3e6 float:1.398E-42 double:4.93E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0006$\u000f\r@,$\\\u001fM9$\u001d\u001aXk3\u0019\u001dD\"7\u0019\u001a\rk2\b\u0017M'a\f\fN($\u000f\r";
        r0 = 16;
        goto L_0x0090;
    L_0x01fa:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001e2\u0019\f\u0001(-\u0015\u001dJ.%\\\u001fO/a\u0013\u000eD%$\u0018^U#$\\3D82\u001d\u0019D";
        r0 = 17;
        goto L_0x0090;
    L_0x020b:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1001; // 0x3e9 float:1.403E-42 double:4.946E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0006$\u000f\r@,$\\\u001aN</\u0010\u0011@/a\u000f\u000bB($\u0019\u001a";
        r0 = 18;
        goto L_0x0090;
    L_0x021c:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1002; // 0x3ea float:1.404E-42 double:4.95E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0006$\u000f\r@,$\\\fD($\u0015\bD/a\u000f\u000bB($\u0019\u001a";
        r0 = 19;
        goto L_0x0090;
    L_0x022d:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1003; // 0x3eb float:1.406E-42 double:4.955E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0006$\u000f\r@,$\\\rH'$\u0012\u001dDk%\u0013\tO'.\u001d\u001a\u000184\u001f\u001dD.%";
        r0 = 20;
        goto L_0x0090;
    L_0x023e:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1004; // 0x3ec float:1.407E-42 double:4.96E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001d(\u0018\u001bNk2\u0015\u0012D%\"\u0019^E$6\u0012\u0012@$%\\\rT(\"\u0019\u001bE";
        r0 = 21;
        goto L_0x0090;
    L_0x024f:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1005; // 0x3ed float:1.408E-42 double:4.965E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001e2\u0019\f\u0001(-\u0015\u001dJ.%\\\bH/$\u0013^@%%\\\u0014T&1\u0019\u001a\u0001?.\\\u000bS'a1\u001bR8 \u001b\u001b\u0001c#\u000e\u0011V8$\u000eW";
        r0 = 22;
        goto L_0x0090;
    L_0x0260:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1008; // 0x3f0 float:1.413E-42 double:4.98E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001d(\u0018\u001bNk(\u000f^G$3\u001f\u001b\u0001(-\u0013\rD/a\u001e\u0007\u0001>2\u0019\f";
        r0 = 23;
        goto L_0x0090;
    L_0x0271:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1007; // 0x3ef float:1.411E-42 double:4.975E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001e2\u0019\f\u0001(-\u0015\u001dJ.%\\Yn\u0000f";
        r0 = 24;
        goto L_0x0090;
    L_0x0282:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1006; // 0x3ee float:1.41E-42 double:4.97E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001e2\u0019\f\u0001(-\u0015\u001dJ.%\\Yb*/\u001f\u001bMl";
        r0 = 25;
        goto L_0x0090;
    L_0x0293:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1011; // 0x3f3 float:1.417E-42 double:4.995E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u000f.\u000b\u0010M$ \u0018^G*(\u0010\u001bE";
        r0 = 26;
        goto L_0x0090;
    L_0x02a4:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1012; // 0x3f4 float:1.418E-42 double:5.0E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001e2\u0019\f\u0001(-\u0015\u001dJ.%\\\nNk%\u0013\tO'.\u001d\u001a\u0001*&\u001d\u0017O";
        r0 = 27;
        goto L_0x0090;
    L_0x02b5:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1013; // 0x3f5 float:1.42E-42 double:5.005E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001f)\u0019^G\"-\u0019^@'3\u0019\u001fE2a\u0019\u0006H85\\\u001fO/a\u000f\u001fL.a\u000f\u0017[.o\\:N%f\b^E$6\u0012\u0012N*%\\\u001fF*(\u0012P";
        r0 = 28;
        goto L_0x0090;
    L_0x02c6:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1100; // 0x44c float:1.541E-42 double:5.435E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0002/\n\u001fM\"%\\\u000e@9 \u0011^N9a\t\u0010D31\u0019\u001dU.%\\\fD84\u0010\n\u000f";
        r0 = 29;
        goto L_0x0090;
    L_0x02d7:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1014; // 0x3f6 float:1.421E-42 double:5.01E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\r \u0015\u0012D/a\b\u0011\u0001;3\u0019\u0012N*%\\\fD:4\u0015\fD/a\u000e\u001bR$4\u000e\u001dD";
        r0 = 30;
        goto L_0x0090;
    L_0x02e8:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1015; // 0x3f7 float:1.422E-42 double:5.015E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001e2\u0019\f\u0001(-\u0015\u001dJ.%\\\u0017O85\u001d\u0012Mk \u0010\u001bS?a\u0013\u0010\u000185\u001d\nT8a\u001e\u001fSk \u001a\nD9a\u0018\u0011V%-\u0013\u001fE\"/\u001b^G\"/\u0015\rI.%R";
        r0 = 31;
        goto L_0x0090;
    L_0x02f9:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1016; // 0x3f8 float:1.424E-42 double:5.02E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001e2\u0019\f\u0001(-\u0015\u001dJ.%\\\nI.a\u000b\u001bC=(\u0019\t\u00068a\t\fM";
        r0 = 32;
        goto L_0x0090;
    L_0x030a:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1017; // 0x3f9 float:1.425E-42 double:5.025E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001e2\u0019\f\u0001(-\u0015\u001dJ.%\\\u001d@'-\\\u001fB?(\u0013\u0010";
        r0 = 33;
        goto L_0x0090;
    L_0x031b:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1018; // 0x3fa float:1.427E-42 double:5.03E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u001f)\u0019^l.2\u000f\u001fF.a\u000f\u0016N<a\u0015\u0010\u0001?)\u0019^R? \b\u000bRk#\u001d\f";
        r0 = 34;
        goto L_0x0090;
    L_0x032c:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1019; // 0x3fb float:1.428E-42 double:5.035E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\b-\u0015\u001dJk \f\u000eM\"2\b^@%%\\\rI$6\\\nI.a1\u001bR8 \u001b\u001b";
        r0 = 35;
        goto L_0x0090;
    L_0x033d:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1020; // 0x3fc float:1.43E-42 double:5.04E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u000f.\u000b\u0010\u0001\",\u001d\u0019Dk'\u001d\u0017M.%";
        r0 = 36;
        goto L_0x0090;
    L_0x034e:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1021; // 0x3fd float:1.431E-42 double:5.044E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u000f.\u000b\u0010\u0001#5\u0011\u0012\u0001- \u0015\u0012D/";
        r0 = 37;
        goto L_0x0090;
    L_0x035f:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1022; // 0x3fe float:1.432E-42 double:5.05E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u000f.\u000b\u0010\u0001\u0006$\u000f\r@,$\\\u0018@\"-\u0019\u001a";
        r0 = 38;
        goto L_0x0090;
    L_0x0370:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1030; // 0x406 float:1.443E-42 double:5.09E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u000f(\u000f\u001d@9%\\\nI.a\u0011\u001bR8 \u001b\u001b\u0001)$\u001f\u001fT8$\\\u0017Uk(\u000f^O$5\\\u0017Ok5\u0014\u001b\u0001;4\u000f\u0016\u0001?(\u0011\u001b";
        r0 = 39;
        goto L_0x0090;
    L_0x0381:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1031; // 0x407 float:1.445E-42 double:5.094E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u00185\u0013\u000e\u0001;4\u000f\u0016\u00018$\u000e\bH($";
        r0 = 40;
        goto L_0x0090;
    L_0x0392:
        r3.put(r2, r1);
        r3 = f896b;
        r0 = 1032; // 0x408 float:1.446E-42 double:5.1E-321;
        r2 = java.lang.Integer.valueOf(r0);
        r1 = "\u0019$\u000f\u000bL.a\f\u000bR#a\u000f\u001bS=(\u001f\u001b";
        r0 = 41;
        goto L_0x0090;
    L_0x03a3:
        r3.put(r2, r1);
        r0 = 0;
        f897c = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.r.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1532a(int i) {
        if (f895a.containsKey(Integer.valueOf(i))) {
            return (String) f895a.get(Integer.valueOf(i));
        }
        new StringBuilder(f898z[0]).append(i);
        ac.m1581b();
        return null;
    }

    /* renamed from: a */
    public static JSONObject m1533a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f898z[5], f898z[6]);
            jSONObject.put(f898z[4], str);
            jSONObject.put(f898z[2], C0404a.m1126m());
            jSONObject.put(f898z[3], f898z[1]);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: b */
    public static String m1534b(int i) {
        if (f896b.containsKey(Integer.valueOf(i))) {
            return (String) f896b.get(Integer.valueOf(i));
        }
        new StringBuilder(f898z[8]).append(i);
        ac.m1581b();
        return "";
    }

    /* renamed from: b */
    public static JSONObject m1535b(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f898z[5], f898z[7]);
            jSONObject.put(f898z[4], str);
            jSONObject.put(f898z[2], C0404a.m1126m());
            jSONObject.put(f898z[3], f898z[1]);
            return jSONObject;
        } catch (JSONException e) {
            return null;
        }
    }
}
