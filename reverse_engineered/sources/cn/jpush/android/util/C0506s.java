package cn.jpush.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build.VERSION;
import android.text.TextUtils;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.URL;
import javax.net.ssl.SSLPeerUnverifiedException;
import org.apache.http.HttpStatus;

/* renamed from: cn.jpush.android.util.s */
public final class C0506s {
    /* renamed from: a */
    public static boolean f1048a = false;
    /* renamed from: z */
    private static final String[] f1049z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 50;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0006=\u0013\r\u0010\u0006\n\u001d\u001d\u0000O";
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
            case 0: goto L_0x0247;
            case 1: goto L_0x024b;
            case 2: goto L_0x024f;
            case 3: goto L_0x0253;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
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
            case 35: goto L_0x01b0;
            case 36: goto L_0x01bb;
            case 37: goto L_0x01c6;
            case 38: goto L_0x01d1;
            case 39: goto L_0x01dc;
            case 40: goto L_0x01e7;
            case 41: goto L_0x01f2;
            case 42: goto L_0x01fd;
            case 43: goto L_0x0208;
            case 44: goto L_0x0213;
            case 45: goto L_0x021e;
            case 46: goto L_0x0229;
            case 47: goto L_0x0234;
            case 48: goto L_0x023f;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = ":=\u001a\u001c\u0017U>\u0000\u0016\u000b\u0012i\u0000\u001c\u0016\u0005&\u001c\n\u0000U:\u0006\u0018\u0011\u0000:RTE";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "6%\u001d\n\u0000";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "6(\u0006\u001a\rU\u001a!55\u0010,\u0000,\u000b\u0003,\u0000\u0010\u0003\u001c,\u0016<\u001d\u0016,\u0002\r\f\u001a'^Y\r\u0001=\u0002Y\u0006\u0019 \u0017\u0017\u0011U,\n\u001c\u0006\u0000=\u0017Y\u0000\u0007;\u001d\u000bD";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "==\u0006\t-\u0010%\u0002\u001c\u0017";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0014*\u0006\u0010\n\u001bs\u001a\r\u0011\u0005\u000e\u0017\rEXi";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "4*\u0011\u001c\u0015\u0001d7\u0017\u0006\u001a-\u001b\u0017\u0002";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u001c-\u0017\u0017\u0011\u001c=\u000b";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "6&\u001c\u0017\u0000\u0016=\u001b\u0016\u000b";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "Yi\u0007\u000b\tO";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0006,\u0000\u000f\u0000\u0007i\u0000\u001c\u0016\u0005&\u001c\n\u0000U/\u0013\u0010\t\u0000;\u0017YHU";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "',\u0003\f\u0000\u0006=R\t\u0004\u0001!R\u001d\n\u0010:R\u0017\n\u0001i\u0017\u0001\f\u0006=HYQE}RTE";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u001d=\u0006\tK\u001e,\u0017\t$\u0019 \u0004\u001c";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u0013(\u001e\n\u0000";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "%;\u001d\r\n\u0016&\u001e<\u001d\u0016,\u0002\r\f\u001a'H";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "6&\u001c\r\u0000\u001b=_<\u000b\u0016&\u0016\u0010\u000b\u0012";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "6&\u001c\r\u0000\u001b=_5\u0000\u001b.\u0006\u0011";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "<\u00067\u0001\u0006\u00109\u0006\u0010\n\u001bs\u0016\u001c\u0007\u0000.";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "4<\u0006\u0011\n\u0007 \b\u0018\u0011\u001c&\u001c";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u00123\u001b\t";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "7(\u0001\u0010\u0006U";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "-d3\t\u0015X\u0002\u0017\u0000";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u00149\u0002\u0015\f\u0016(\u0006\u0010\n\u001bf\u0018\u0018\u0016\u001a'";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "%\u0006!-";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "4*\u0011\u001c\u0015\u0001";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u0006=\u0013\r\u0010\u0006i\u0011\u0016\u0001\u0010s";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "6(\u0006\u001a\rU\b\u0001\n\u0000\u0007=\u001b\u0016\u000b0;\u0000\u0016\u0017U=\u001dY\u0004\u0003&\u001b\u001dE\u001d=\u0006\tE\u0016%\u001d\n\u0000U*\u0000\u0018\u0016\u001di_Y";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "6!\u0013\u000b\u0016\u0010=";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "\u0000;\u001eC";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = " \u001d4T]";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "Iu\u0014\u0018\f\u0019,\u0016G[";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "Iu\u001c\u001c\u0011\u0002&\u0000\u0012\u0000\u0007;\u001d\u000b[K";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "Iu\u0017\u000b\u0017\u001a;LG";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "Iu\u0014\u0018\f\u0019,\u0016&\u0012\u001c=\u001a&\u0017\u0010=\u0000\u0010\u0000\u0006wL";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "\u001b&\u0006Y\u0004\u0016*\u0017\t\u0011\u0014+\u001e\u001c_AyDYHU";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = "\u0014*\u0006\u0010\n\u001bs\u001a\r\u0011\u0005\u001a\u001b\u0014\u0015\u0019,5\u001c\u0011UdR";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        r2 = 36;
        r1 = "&,\u0000\u000f\u0000\u0007i\u0017\u000b\u0017\u001a;RTE";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01b0:
        r3[r2] = r1;
        r2 = 37;
        r1 = "&,\u0000\u000f\u0000\u0007i\u0000\u001c\u0016\u0005&\u001c\n\u0000U/\u0013\u0010\t\u0000;\u0017CQEyRTE";
        r0 = 36;
        r3 = r4;
        goto L_0x0009;
    L_0x01bb:
        r3[r2] = r1;
        r2 = 38;
        r1 = "',\u0003\f\u0000\u0006=R\u0017\n\u0001i\u0013\f\u0011\u001d&\u0000\u0010\u001f\u0010-HMUDi_Y";
        r0 = 37;
        r3 = r4;
        goto L_0x0009;
    L_0x01c6:
        r3[r2] = r1;
        r2 = 39;
        r1 = "\u0016&\u001c\u001f\t\u001c*\u0006CQEpRTE";
        r0 = 38;
        r3 = r4;
        goto L_0x0009;
    L_0x01d1:
        r3[r2] = r1;
        r2 = 40;
        r1 = "\u0007,\u0003\f\u0000\u0006=R\r\f\u0018,\u001d\f\u0011O}BAEXi";
        r0 = 39;
        r3 = r4;
        goto L_0x0009;
    L_0x01dc:
        r3[r2] = r1;
        r2 = 41;
        r1 = "Dy\\IKEgCNW";
        r0 = 40;
        r3 = r4;
        goto L_0x0009;
    L_0x01e7:
        r3[r2] = r1;
        r2 = 42;
        r1 = "\u0016$\u0005\u0018\u0015";
        r0 = 41;
        r3 = r4;
        goto L_0x0009;
    L_0x01f2:
        r3[r2] = r1;
        r2 = 43;
        r1 = "F.\u0005\u0018\u0015";
        r0 = 42;
        r3 = r4;
        goto L_0x0009;
    L_0x01fd:
        r3[r2] = r1;
        r2 = 44;
        r1 = "\u0000'\u001b\u000e\u0004\u0005";
        r0 = 43;
        r3 = r4;
        goto L_0x0009;
    L_0x0208:
        r3[r2] = r1;
        r2 = 45;
        r1 = "\u0014'\u0016\u000b\n\u001c-\\\t\u0000\u0007$\u001b\n\u0016\u001c&\u001cW$6\n7*6*\u00077-2:\u001b9&6!\b&<";
        r0 = 44;
        r3 = r4;
        goto L_0x0009;
    L_0x0213:
        r3[r2] = r1;
        r2 = 46;
        r1 = "\u0016&\u001c\u0017\u0000\u0016=\u001b\u000f\f\u00010";
        r0 = 45;
        r3 = r4;
        goto L_0x0009;
    L_0x021e:
        r3[r2] = r1;
        r2 = 47;
        r1 = " :\u0017\u000bH4.\u0017\u0017\u0011";
        r0 = 46;
        r3 = r4;
        goto L_0x0009;
    L_0x0229:
        r3[r2] = r1;
        r2 = 48;
        r1 = "?\u0019'*-X\u001a62";
        r0 = 47;
        r3 = r4;
        goto L_0x0009;
    L_0x0234:
        r3[r2] = r1;
        r2 = 49;
        r1 = "4*\u0011\u001c\u0015\u0001d1\u0011\u0004\u0007:\u0017\r";
        r0 = 48;
        r3 = r4;
        goto L_0x0009;
    L_0x023f:
        r3[r2] = r1;
        f1049z = r4;
        r0 = 0;
        f1048a = r0;
        return;
    L_0x0247:
        r9 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        goto L_0x0020;
    L_0x024b:
        r9 = 73;
        goto L_0x0020;
    L_0x024f:
        r9 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x0020;
    L_0x0253:
        r9 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.s.<clinit>():void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static int m1796a(android.content.Context r10, org.json.JSONObject r11, boolean r12) {
        /*
        r9 = 2;
        r2 = -1;
        r3 = -4;
        r5 = 1;
        r4 = 0;
        r1 = cn.jpush.android.util.ah.m1613a(r9);
        r0 = new java.lang.StringBuilder;
        r6 = f1049z;
        r7 = 28;
        r6 = r6[r7];
        r0.<init>(r6);
        r0.append(r1);
        cn.jpush.android.util.ac.m1576a();
        cn.jpush.android.util.ac.m1576a();
        r0 = cn.jpush.android.util.an.m1657a(r1);
        if (r0 == 0) goto L_0x0028;
    L_0x0023:
        r0 = r4;
    L_0x0024:
        if (r0 != 0) goto L_0x0048;
    L_0x0026:
        r0 = r2;
    L_0x0027:
        return r0;
    L_0x0028:
        r0 = cn.jpush.android.util.ah.m1631b(r1);
        if (r0 == 0) goto L_0x003b;
    L_0x002e:
        r0 = android.webkit.URLUtil.isHttpUrl(r1);
        if (r0 != 0) goto L_0x0039;
    L_0x0034:
        cn.jpush.android.util.ac.m1588e();
        r0 = r4;
        goto L_0x0024;
    L_0x0039:
        r0 = r5;
        goto L_0x0024;
    L_0x003b:
        r0 = android.webkit.URLUtil.isHttpsUrl(r1);
        if (r0 != 0) goto L_0x0046;
    L_0x0041:
        cn.jpush.android.util.ac.m1588e();
        r0 = r4;
        goto L_0x0024;
    L_0x0046:
        r0 = r5;
        goto L_0x0024;
    L_0x0048:
        r6 = cn.jpush.android.util.C0506s.m1798a(r10, r1);
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r6.setConnectTimeout(r0);
        r0 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r6.setReadTimeout(r0);
        r6.setDoOutput(r5);
        r6.setDoInput(r5);
        r6.setUseCaches(r4);
        r0 = android.os.Build.VERSION.SDK;
        r0 = java.lang.Integer.parseInt(r0);
        r1 = 8;
        if (r0 >= r1) goto L_0x0078;
    L_0x0069:
        r0 = f1049z;
        r1 = 12;
        r0 = r0[r1];
        r1 = f1049z;
        r7 = 13;
        r1 = r1[r7];
        java.lang.System.setProperty(r0, r1);
    L_0x0078:
        r0 = f1049z;	 Catch:{ ProtocolException -> 0x0094 }
        r1 = 23;
        r0 = r0[r1];	 Catch:{ ProtocolException -> 0x0094 }
        r6.setRequestMethod(r0);	 Catch:{ ProtocolException -> 0x0094 }
    L_0x0081:
        r0 = "";
        if (r11 == 0) goto L_0x0089;
    L_0x0085:
        r0 = r11.toString();
    L_0x0089:
        r1 = cn.jpush.android.util.an.m1657a(r0);
        if (r1 == 0) goto L_0x0099;
    L_0x008f:
        cn.jpush.android.util.ac.m1581b();
        r0 = -2;
        goto L_0x0027;
    L_0x0094:
        r0 = move-exception;
        r0.printStackTrace();
        goto L_0x0081;
    L_0x0099:
        if (r6 != 0) goto L_0x00a6;
    L_0x009b:
        cn.jpush.android.util.ac.m1588e();
        r1 = r4;
    L_0x009f:
        if (r1 != 0) goto L_0x0132;
    L_0x00a1:
        cn.jpush.android.util.ac.m1581b();
        r0 = -3;
        goto L_0x0027;
    L_0x00a6:
        r1 = f1049z;
        r7 = 24;
        r1 = r1[r7];
        r7 = f1049z;
        r8 = 22;
        r7 = r7[r8];
        r6.setRequestProperty(r1, r7);
        r1 = f1049z;
        r7 = 6;
        r1 = r1[r7];
        r7 = f1049z;
        r8 = 19;
        r7 = r7[r8];
        r6.setRequestProperty(r1, r7);
        r1 = f1049z;
        r7 = 15;
        r1 = r1[r7];
        r7 = f1049z;
        r8 = 19;
        r7 = r7[r8];
        r6.setRequestProperty(r1, r7);
        r1 = f1049z;
        r7 = 21;
        r1 = r1[r7];
        r7 = cn.jpush.android.util.C0490b.m1732q(r10);
        r6.setRequestProperty(r1, r7);
        if (r0 != 0) goto L_0x00f0;
    L_0x00e1:
        r1 = cn.jpush.android.util.ah.m1612a();
    L_0x00e5:
        r7 = cn.jpush.android.util.an.m1657a(r1);
        if (r7 == 0) goto L_0x00f5;
    L_0x00eb:
        cn.jpush.android.util.ac.m1581b();
        r1 = r4;
        goto L_0x009f;
    L_0x00f0:
        r1 = cn.jpush.android.util.ah.m1615a(r0, r9);
        goto L_0x00e5;
    L_0x00f5:
        r1 = cn.jpush.android.util.ah.m1632c(r1);
        r7 = cn.jpush.android.util.an.m1657a(r1);
        if (r7 == 0) goto L_0x0104;
    L_0x00ff:
        cn.jpush.android.util.ac.m1581b();
        r1 = r4;
        goto L_0x009f;
    L_0x0104:
        r4 = f1049z;
        r7 = 18;
        r4 = r4[r7];
        r7 = new java.lang.StringBuilder;
        r8 = f1049z;
        r9 = 20;
        r8 = r8[r9];
        r7.<init>(r8);
        r1 = r7.append(r1);
        r1 = r1.toString();
        r6.setRequestProperty(r4, r1);
        r1 = f1049z;
        r4 = 27;
        r1 = r1[r4];
        r4 = f1049z;
        r7 = 29;
        r4 = r4[r7];
        r6.setRequestProperty(r1, r4);
        r1 = r5;
        goto L_0x009f;
    L_0x0132:
        r1 = f1049z;	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r4 = 29;
        r1 = r1[r4];	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r0 = r0.getBytes(r1);	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r1 = new java.io.ByteArrayOutputStream;	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r1.<init>();	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r4 = new java.util.zip.GZIPOutputStream;	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r4.<init>(r1);	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r4.write(r0);	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r4.close();	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r0 = r1.toByteArray();	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r1.close();	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r1 = f1049z;	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r4 = 16;
        r1 = r1[r4];	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r4 = r0.length;	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r4 = java.lang.String.valueOf(r4);	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r6.setRequestProperty(r1, r4);	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r1 = r6.getOutputStream();	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r1.write(r0);	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r1.flush();	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r1.close();	 Catch:{ UnsupportedEncodingException -> 0x0194, IOException -> 0x019b }
        r0 = r6.getResponseCode();	 Catch:{ ProtocolException -> 0x01d1, IOException -> 0x01ef, AssertionError -> 0x020b }
        r1 = new java.lang.StringBuilder;	 Catch:{ ProtocolException -> 0x01d1, IOException -> 0x01ef, AssertionError -> 0x020b }
        r3 = f1049z;	 Catch:{ ProtocolException -> 0x01d1, IOException -> 0x01ef, AssertionError -> 0x020b }
        r4 = 25;
        r3 = r3[r4];	 Catch:{ ProtocolException -> 0x01d1, IOException -> 0x01ef, AssertionError -> 0x020b }
        r1.<init>(r3);	 Catch:{ ProtocolException -> 0x01d1, IOException -> 0x01ef, AssertionError -> 0x020b }
        r1.append(r0);	 Catch:{ ProtocolException -> 0x01d1, IOException -> 0x01ef, AssertionError -> 0x020b }
        cn.jpush.android.util.ac.m1581b();	 Catch:{ ProtocolException -> 0x01d1, IOException -> 0x01ef, AssertionError -> 0x020b }
        switch(r0) {
            case 200: goto L_0x01a2;
            case 401: goto L_0x01ab;
            case 404: goto L_0x01b7;
            case 429: goto L_0x01c0;
            default: goto L_0x0186;
        };	 Catch:{ ProtocolException -> 0x01d1, IOException -> 0x01ef, AssertionError -> 0x020b }
    L_0x0186:
        r0 = r0 / 100;
        r1 = 5;
        if (r0 != r1) goto L_0x01c9;
    L_0x018b:
        if (r6 == 0) goto L_0x0190;
    L_0x018d:
        r6.disconnect();
    L_0x0190:
        r0 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        goto L_0x0027;
    L_0x0194:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r3;
        goto L_0x0027;
    L_0x019b:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = r3;
        goto L_0x0027;
    L_0x01a2:
        if (r6 == 0) goto L_0x01a7;
    L_0x01a4:
        r6.disconnect();
    L_0x01a7:
        r0 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        goto L_0x0027;
    L_0x01ab:
        cn.jpush.android.util.ac.m1586d();	 Catch:{ ProtocolException -> 0x01d1, IOException -> 0x01ef, AssertionError -> 0x020b }
        if (r6 == 0) goto L_0x01b3;
    L_0x01b0:
        r6.disconnect();
    L_0x01b3:
        r0 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        goto L_0x0027;
    L_0x01b7:
        if (r6 == 0) goto L_0x01bc;
    L_0x01b9:
        r6.disconnect();
    L_0x01bc:
        r0 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        goto L_0x0027;
    L_0x01c0:
        if (r6 == 0) goto L_0x01c5;
    L_0x01c2:
        r6.disconnect();
    L_0x01c5:
        r0 = 429; // 0x1ad float:6.01E-43 double:2.12E-321;
        goto L_0x0027;
    L_0x01c9:
        if (r6 == 0) goto L_0x01ce;
    L_0x01cb:
        r6.disconnect();
    L_0x01ce:
        r0 = -5;
        goto L_0x0027;
    L_0x01d1:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0227 }
        r3 = f1049z;	 Catch:{ all -> 0x0227 }
        r4 = 14;
        r3 = r3[r4];	 Catch:{ all -> 0x0227 }
        r1.<init>(r3);	 Catch:{ all -> 0x0227 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0227 }
        r1.append(r0);	 Catch:{ all -> 0x0227 }
        cn.jpush.android.util.ac.m1588e();	 Catch:{ all -> 0x0227 }
        if (r6 == 0) goto L_0x01ec;
    L_0x01e9:
        r6.disconnect();
    L_0x01ec:
        r0 = r2;
        goto L_0x0027;
    L_0x01ef:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0227 }
        r3 = f1049z;	 Catch:{ all -> 0x0227 }
        r4 = 17;
        r3 = r3[r4];	 Catch:{ all -> 0x0227 }
        r1.<init>(r3);	 Catch:{ all -> 0x0227 }
        r0 = r0.getMessage();	 Catch:{ all -> 0x0227 }
        r1.append(r0);	 Catch:{ all -> 0x0227 }
        cn.jpush.android.util.ac.m1588e();	 Catch:{ all -> 0x0227 }
        if (r6 == 0) goto L_0x01ec;
    L_0x0207:
        r6.disconnect();
        goto L_0x01ec;
    L_0x020b:
        r0 = move-exception;
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0227 }
        r3 = f1049z;	 Catch:{ all -> 0x0227 }
        r4 = 26;
        r3 = r3[r4];	 Catch:{ all -> 0x0227 }
        r1.<init>(r3);	 Catch:{ all -> 0x0227 }
        r0 = r0.toString();	 Catch:{ all -> 0x0227 }
        r1.append(r0);	 Catch:{ all -> 0x0227 }
        cn.jpush.android.util.ac.m1588e();	 Catch:{ all -> 0x0227 }
        if (r6 == 0) goto L_0x01ec;
    L_0x0223:
        r6.disconnect();
        goto L_0x01ec;
    L_0x0227:
        r0 = move-exception;
        if (r6 == 0) goto L_0x022d;
    L_0x022a:
        r6.disconnect();
    L_0x022d:
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.s.a(android.content.Context, org.json.JSONObject, boolean):int");
    }

    /* renamed from: a */
    public static String m1797a(String str, int i, long j) {
        HttpURLConnection httpURLConnection;
        int responseCode;
        InputStream inputStream;
        HttpURLConnection httpURLConnection2;
        InputStream inputStream2;
        int i2;
        Throwable th;
        AssertionError assertionError;
        AssertionError assertionError2;
        new StringBuilder(f1049z[35]).append(str);
        ac.m1581b();
        if (j < 200 || j > ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
            j = 2000;
        }
        HttpURLConnection httpURLConnection3 = null;
        InputStream inputStream3 = null;
        int i3 = 0;
        while (true) {
            try {
                httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection.addRequestProperty(f1049z[8], f1049z[2]);
                    httpURLConnection.setRequestProperty(f1049z[6], f1049z[7]);
                    if (Integer.parseInt(VERSION.SDK) < 8) {
                        System.setProperty(f1049z[12], f1049z[13]);
                    }
                    responseCode = httpURLConnection.getResponseCode();
                    new StringBuilder(f1049z[0]).append(responseCode);
                    ac.m1576a();
                    if (responseCode == 200) {
                        inputStream = httpURLConnection.getInputStream();
                        try {
                            String str2 = new String(am.m1656a(inputStream), f1049z[29]);
                            break;
                        } catch (SSLPeerUnverifiedException e) {
                            httpURLConnection2 = httpURLConnection;
                            inputStream2 = inputStream;
                        } catch (Exception e2) {
                            inputStream3 = inputStream;
                            try {
                                ac.m1591g();
                                if (inputStream3 != null) {
                                    try {
                                        inputStream3.close();
                                    } catch (IOException e3) {
                                        e3.printStackTrace();
                                    }
                                }
                                if (httpURLConnection != null) {
                                    httpURLConnection.disconnect();
                                    httpURLConnection3 = httpURLConnection;
                                    if (i3 >= 5) {
                                        return f1049z[33];
                                    }
                                    i2 = i3 + 1;
                                    try {
                                        Thread.sleep(j);
                                        i3 = i2;
                                    } catch (InterruptedException e4) {
                                        i3 = i2;
                                    }
                                }
                                httpURLConnection3 = httpURLConnection;
                                if (i3 >= 5) {
                                    return f1049z[33];
                                }
                                i2 = i3 + 1;
                                Thread.sleep(j);
                                i3 = i2;
                            } catch (Throwable th2) {
                                httpURLConnection3 = httpURLConnection;
                                th = th2;
                            }
                        } catch (AssertionError e5) {
                            assertionError = e5;
                            inputStream3 = inputStream;
                            httpURLConnection3 = httpURLConnection;
                            assertionError2 = assertionError;
                            try {
                                new StringBuilder(f1049z[26]).append(assertionError2.toString());
                                ac.m1588e();
                                if (inputStream3 != null) {
                                    try {
                                        inputStream3.close();
                                    } catch (IOException e6) {
                                        e6.printStackTrace();
                                    }
                                }
                                if (httpURLConnection3 != null) {
                                    httpURLConnection3.disconnect();
                                }
                                if (i3 >= 5) {
                                    return f1049z[33];
                                }
                                i2 = i3 + 1;
                                Thread.sleep(j);
                                i3 = i2;
                            } catch (Throwable th3) {
                                th = th3;
                            }
                        } catch (Throwable th4) {
                            Throwable th5 = th4;
                            inputStream3 = inputStream;
                            httpURLConnection3 = httpURLConnection;
                            th = th5;
                        }
                    } else {
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (IOException e32) {
                                e32.printStackTrace();
                            }
                        }
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                            httpURLConnection3 = httpURLConnection;
                            if (i3 >= 5) {
                                return f1049z[33];
                            }
                            i2 = i3 + 1;
                            Thread.sleep(j);
                            i3 = i2;
                        }
                        httpURLConnection3 = httpURLConnection;
                        if (i3 >= 5) {
                            return f1049z[33];
                        }
                        i2 = i3 + 1;
                        Thread.sleep(j);
                        i3 = i2;
                    }
                } catch (SSLPeerUnverifiedException e7) {
                    InputStream inputStream4 = inputStream3;
                    httpURLConnection2 = httpURLConnection;
                    inputStream2 = inputStream4;
                    try {
                        ac.m1589e(f1049z[4], f1049z[3]);
                        if (inputStream2 != null) {
                            try {
                                inputStream2.close();
                            } catch (IOException e322) {
                                e322.printStackTrace();
                            }
                        }
                        if (httpURLConnection2 == null) {
                            httpURLConnection3 = httpURLConnection2;
                            inputStream3 = inputStream2;
                        } else {
                            httpURLConnection2.disconnect();
                            httpURLConnection3 = httpURLConnection2;
                            inputStream3 = inputStream2;
                        }
                        if (i3 >= 5) {
                            return f1049z[33];
                        }
                        i2 = i3 + 1;
                        Thread.sleep(j);
                        i3 = i2;
                    } catch (Throwable th22) {
                        httpURLConnection3 = httpURLConnection2;
                        inputStream3 = inputStream2;
                        th = th22;
                    }
                } catch (Exception e8) {
                    ac.m1591g();
                    if (inputStream3 != null) {
                        inputStream3.close();
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                        httpURLConnection3 = httpURLConnection;
                        if (i3 >= 5) {
                            return f1049z[33];
                        }
                        i2 = i3 + 1;
                        Thread.sleep(j);
                        i3 = i2;
                    }
                    httpURLConnection3 = httpURLConnection;
                    if (i3 >= 5) {
                        return f1049z[33];
                    }
                    i2 = i3 + 1;
                    Thread.sleep(j);
                    i3 = i2;
                } catch (AssertionError e9) {
                    assertionError = e9;
                    httpURLConnection3 = httpURLConnection;
                    assertionError2 = assertionError;
                    new StringBuilder(f1049z[26]).append(assertionError2.toString());
                    ac.m1588e();
                    if (inputStream3 != null) {
                        inputStream3.close();
                    }
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                    }
                    if (i3 >= 5) {
                        return f1049z[33];
                    }
                    i2 = i3 + 1;
                    Thread.sleep(j);
                    i3 = i2;
                }
            } catch (SSLPeerUnverifiedException e10) {
                inputStream2 = inputStream3;
                httpURLConnection2 = httpURLConnection3;
                ac.m1589e(f1049z[4], f1049z[3]);
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (httpURLConnection2 == null) {
                    httpURLConnection2.disconnect();
                    httpURLConnection3 = httpURLConnection2;
                    inputStream3 = inputStream2;
                } else {
                    httpURLConnection3 = httpURLConnection2;
                    inputStream3 = inputStream2;
                }
                if (i3 >= 5) {
                    return f1049z[33];
                }
                i2 = i3 + 1;
                Thread.sleep(j);
                i3 = i2;
            } catch (Exception e11) {
                httpURLConnection = httpURLConnection3;
                ac.m1591g();
                if (inputStream3 != null) {
                    inputStream3.close();
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                    httpURLConnection3 = httpURLConnection;
                    if (i3 >= 5) {
                        return f1049z[33];
                    }
                    i2 = i3 + 1;
                    Thread.sleep(j);
                    i3 = i2;
                }
                httpURLConnection3 = httpURLConnection;
                if (i3 >= 5) {
                    return f1049z[33];
                }
                i2 = i3 + 1;
                Thread.sleep(j);
                i3 = i2;
            } catch (AssertionError e12) {
                assertionError2 = e12;
                new StringBuilder(f1049z[26]).append(assertionError2.toString());
                ac.m1588e();
                if (inputStream3 != null) {
                    inputStream3.close();
                }
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                }
                if (i3 >= 5) {
                    return f1049z[33];
                }
                i2 = i3 + 1;
                Thread.sleep(j);
                i3 = i2;
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e3222) {
                e3222.printStackTrace();
            }
        }
        if (httpURLConnection != null) {
            httpURLConnection.disconnect();
        }
        if (responseCode < 200 || responseCode >= 300) {
            if (responseCode < HttpStatus.SC_BAD_REQUEST || responseCode >= 500) {
                if (responseCode < 500 || responseCode >= 600) {
                    new StringBuilder(f1049z[1]).append(responseCode).append(f1049z[9]).append(str);
                    ac.m1581b();
                    return f1049z[32];
                }
                new StringBuilder(f1049z[36]).append(responseCode).append(f1049z[9]).append(str);
                ac.m1581b();
                return f1049z[32];
            } else if (HttpStatus.SC_BAD_REQUEST == responseCode) {
                new StringBuilder(f1049z[37]).append(str);
                ac.m1581b();
                return f1049z[30];
            } else if (HttpStatus.SC_UNAUTHORIZED == responseCode) {
                new StringBuilder(f1049z[38]).append(str);
                ac.m1581b();
                return f1049z[32];
            } else if (404 == responseCode) {
                new StringBuilder(f1049z[11]).append(str);
                ac.m1581b();
                return f1049z[32];
            } else if (HttpStatus.SC_NOT_ACCEPTABLE == responseCode) {
                new StringBuilder(f1049z[34]).append(str);
                ac.m1581b();
                return f1049z[32];
            } else if (HttpStatus.SC_REQUEST_TIMEOUT == responseCode) {
                new StringBuilder(f1049z[40]).append(str);
                ac.m1581b();
                return f1049z[32];
            } else if (HttpStatus.SC_CONFLICT != responseCode) {
                return null;
            } else {
                new StringBuilder(f1049z[39]).append(str);
                ac.m1581b();
                return f1049z[32];
            }
        } else if (str2 != null) {
            return str2;
        } else {
            try {
                ac.m1591g();
                return f1049z[32];
            } catch (Exception e13) {
                ac.m1590f();
                return f1049z[32];
            }
        }
        if (inputStream3 != null) {
            try {
                inputStream3.close();
            } catch (IOException e14) {
                e14.printStackTrace();
            }
        }
        if (httpURLConnection3 != null) {
            httpURLConnection3.disconnect();
        }
        throw th;
        if (httpURLConnection3 != null) {
            httpURLConnection3.disconnect();
        }
        throw th;
    }

    /* renamed from: a */
    public static HttpURLConnection m1798a(Context context, String str) {
        try {
            URL url = new URL(str);
            if (context.getPackageManager().checkPermission(f1049z[45], context.getPackageName()) == 0) {
                NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(f1049z[46])).getActiveNetworkInfo();
                if (!(activeNetworkInfo == null || activeNetworkInfo.getType() == 1)) {
                    String extraInfo = activeNetworkInfo.getExtraInfo();
                    if (extraInfo != null && (extraInfo.equals(f1049z[42]) || extraInfo.equals(f1049z[43]) || extraInfo.equals(f1049z[44]))) {
                        return (HttpURLConnection) url.openConnection(new Proxy(Type.HTTP, new InetSocketAddress(f1049z[41], 80)));
                    }
                }
            }
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m1799a(HttpURLConnection httpURLConnection, boolean z) {
        if (httpURLConnection == null) {
            ac.m1588e();
            return;
        }
        if (z) {
            try {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                httpURLConnection.setRequestMethod(f1049z[23]);
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
        }
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setRequestProperty(f1049z[47], f1049z[48]);
        httpURLConnection.setRequestProperty(f1049z[49], f1049z[29]);
        httpURLConnection.setConnectTimeout(30000);
        httpURLConnection.setReadTimeout(30000);
    }

    /* renamed from: a */
    public static boolean m1800a(String str) {
        return TextUtils.isEmpty(str) || str.equals(f1049z[32]) || str.equals(f1049z[30]) || str.equals(f1049z[33]) || str.equals(f1049z[31]);
    }

    /* renamed from: a */
    public static byte[] m1801a(String str, int i, long j, int i2) {
        byte[] bArr = null;
        for (int i3 = 0; i3 < 4; i3++) {
            bArr = C0506s.m1802b(str, 5, 5000);
            if (bArr != null) {
                break;
            }
        }
        return bArr;
    }

    /* renamed from: b */
    private static byte[] m1802b(String str, int i, long j) {
        int contentLength;
        byte[] a;
        HttpURLConnection httpURLConnection;
        InputStream inputStream;
        int i2;
        Throwable th;
        if (i <= 0 || i > 10) {
            i = 1;
        }
        if (j < 200 || j > ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
            j = 2000;
        }
        new StringBuilder(f1049z[5]).append(str);
        ac.m1581b();
        int i3 = 0;
        InputStream inputStream2 = null;
        HttpURLConnection httpURLConnection2 = null;
        while (true) {
            HttpURLConnection httpURLConnection3;
            int responseCode;
            InputStream inputStream3;
            try {
                httpURLConnection3 = (HttpURLConnection) new URL(str).openConnection();
                try {
                    httpURLConnection3.setRequestProperty(f1049z[6], f1049z[7]);
                    httpURLConnection3.addRequestProperty(f1049z[8], f1049z[2]);
                    if (Integer.parseInt(VERSION.SDK) < 8) {
                        System.setProperty(f1049z[12], f1049z[13]);
                    }
                    responseCode = httpURLConnection3.getResponseCode();
                    new StringBuilder(f1049z[0]).append(responseCode);
                    ac.m1576a();
                    if (responseCode == 200) {
                        contentLength = httpURLConnection3.getContentLength();
                        inputStream3 = httpURLConnection3.getInputStream();
                        if (inputStream3 == null) {
                            break;
                        }
                        try {
                            a = am.m1656a(inputStream3);
                            break;
                        } catch (SSLPeerUnverifiedException e) {
                            httpURLConnection = httpURLConnection3;
                            inputStream = inputStream3;
                        } catch (Exception e2) {
                            inputStream2 = inputStream3;
                            try {
                                ac.m1591g();
                                if (inputStream2 != null) {
                                    try {
                                        inputStream2.close();
                                    } catch (IOException e3) {
                                    }
                                }
                                if (httpURLConnection3 != null) {
                                    httpURLConnection3.disconnect();
                                    httpURLConnection2 = httpURLConnection3;
                                    if (i3 >= i) {
                                        return null;
                                    }
                                    i2 = i3 + 1;
                                    try {
                                        Thread.sleep(((long) i2) * j);
                                        i3 = i2;
                                    } catch (InterruptedException e4) {
                                        i3 = i2;
                                    }
                                }
                                httpURLConnection2 = httpURLConnection3;
                                if (i3 >= i) {
                                    return null;
                                }
                                i2 = i3 + 1;
                                Thread.sleep(((long) i2) * j);
                                i3 = i2;
                            } catch (Throwable th2) {
                                httpURLConnection2 = httpURLConnection3;
                                th = th2;
                            }
                        } catch (Throwable th22) {
                            inputStream2 = inputStream3;
                            httpURLConnection2 = httpURLConnection3;
                            th = th22;
                        }
                        if (inputStream3 != null) {
                            try {
                                inputStream3.close();
                            } catch (IOException e5) {
                            }
                        }
                        if (httpURLConnection3 != null) {
                            httpURLConnection3.disconnect();
                        }
                        if (200 != responseCode) {
                            if (contentLength == 0) {
                                try {
                                    ac.m1581b();
                                    return null;
                                } catch (Exception e6) {
                                    ac.m1591g();
                                    return null;
                                }
                            } else if (a.length < contentLength) {
                                return a;
                            } else {
                                ac.m1581b();
                                return null;
                            }
                        } else if (HttpStatus.SC_BAD_REQUEST == responseCode) {
                            new StringBuilder(f1049z[10]).append(str);
                            ac.m1581b();
                            return null;
                        } else if (404 != responseCode) {
                            new StringBuilder(f1049z[11]).append(str);
                            ac.m1581b();
                            return null;
                        } else {
                            new StringBuilder(f1049z[1]).append(responseCode).append(f1049z[9]).append(str);
                            ac.m1581b();
                            return null;
                        }
                    }
                    if (inputStream2 != null) {
                        try {
                            inputStream2.close();
                        } catch (IOException e7) {
                        }
                    }
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                        httpURLConnection2 = httpURLConnection3;
                        if (i3 >= i) {
                            return null;
                        }
                        i2 = i3 + 1;
                        Thread.sleep(((long) i2) * j);
                        i3 = i2;
                    }
                    httpURLConnection2 = httpURLConnection3;
                    if (i3 >= i) {
                        return null;
                    }
                    i2 = i3 + 1;
                    Thread.sleep(((long) i2) * j);
                    i3 = i2;
                } catch (SSLPeerUnverifiedException e8) {
                    InputStream inputStream4 = inputStream2;
                    httpURLConnection = httpURLConnection3;
                    inputStream = inputStream4;
                    try {
                        ac.m1589e(f1049z[4], f1049z[3]);
                        if (inputStream != null) {
                            try {
                                inputStream.close();
                            } catch (IOException e9) {
                            }
                        }
                        if (httpURLConnection == null) {
                            httpURLConnection.disconnect();
                            httpURLConnection2 = httpURLConnection;
                            inputStream2 = inputStream;
                        } else {
                            httpURLConnection2 = httpURLConnection;
                            inputStream2 = inputStream;
                        }
                        if (i3 >= i) {
                            return null;
                        }
                        i2 = i3 + 1;
                        Thread.sleep(((long) i2) * j);
                        i3 = i2;
                    } catch (Throwable th222) {
                        httpURLConnection2 = httpURLConnection;
                        inputStream2 = inputStream;
                        th = th222;
                    }
                } catch (Exception e10) {
                    ac.m1591g();
                    if (inputStream2 != null) {
                        inputStream2.close();
                    }
                    if (httpURLConnection3 != null) {
                        httpURLConnection3.disconnect();
                        httpURLConnection2 = httpURLConnection3;
                        if (i3 >= i) {
                            return null;
                        }
                        i2 = i3 + 1;
                        Thread.sleep(((long) i2) * j);
                        i3 = i2;
                    }
                    httpURLConnection2 = httpURLConnection3;
                    if (i3 >= i) {
                        return null;
                    }
                    i2 = i3 + 1;
                    Thread.sleep(((long) i2) * j);
                    i3 = i2;
                }
            } catch (SSLPeerUnverifiedException e11) {
                inputStream = inputStream2;
                httpURLConnection = httpURLConnection2;
                ac.m1589e(f1049z[4], f1049z[3]);
                if (inputStream != null) {
                    inputStream.close();
                }
                if (httpURLConnection == null) {
                    httpURLConnection2 = httpURLConnection;
                    inputStream2 = inputStream;
                } else {
                    httpURLConnection.disconnect();
                    httpURLConnection2 = httpURLConnection;
                    inputStream2 = inputStream;
                }
                if (i3 >= i) {
                    return null;
                }
                i2 = i3 + 1;
                Thread.sleep(((long) i2) * j);
                i3 = i2;
            } catch (Exception e12) {
                httpURLConnection3 = httpURLConnection2;
                ac.m1591g();
                if (inputStream2 != null) {
                    inputStream2.close();
                }
                if (httpURLConnection3 != null) {
                    httpURLConnection3.disconnect();
                    httpURLConnection2 = httpURLConnection3;
                    if (i3 >= i) {
                        return null;
                    }
                    i2 = i3 + 1;
                    Thread.sleep(((long) i2) * j);
                    i3 = i2;
                }
                httpURLConnection2 = httpURLConnection3;
                if (i3 >= i) {
                    return null;
                }
                i2 = i3 + 1;
                Thread.sleep(((long) i2) * j);
                i3 = i2;
            } catch (Throwable th3) {
                th = th3;
            }
        }
        a = null;
        if (inputStream3 != null) {
            inputStream3.close();
        }
        if (httpURLConnection3 != null) {
            httpURLConnection3.disconnect();
        }
        if (200 != responseCode) {
            if (HttpStatus.SC_BAD_REQUEST == responseCode) {
                new StringBuilder(f1049z[10]).append(str);
                ac.m1581b();
                return null;
            } else if (404 != responseCode) {
                new StringBuilder(f1049z[1]).append(responseCode).append(f1049z[9]).append(str);
                ac.m1581b();
                return null;
            } else {
                new StringBuilder(f1049z[11]).append(str);
                ac.m1581b();
                return null;
            }
        } else if (contentLength == 0) {
            ac.m1581b();
            return null;
        } else if (a.length < contentLength) {
            return a;
        } else {
            ac.m1581b();
            return null;
        }
        if (inputStream2 != null) {
            try {
                inputStream2.close();
            } catch (IOException e13) {
            }
        }
        if (httpURLConnection2 != null) {
            httpURLConnection2.disconnect();
        }
        throw th;
        if (httpURLConnection2 != null) {
            httpURLConnection2.disconnect();
        }
        throw th;
    }
}
