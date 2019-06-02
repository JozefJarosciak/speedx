package cn.jpush.android.util;

import android.content.Context;
import android.text.TextUtils;
import cn.jpush.android.C0404a;
import cn.jpush.android.helpers.C0459i;
import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONException;
import org.json.JSONObject;

public final class af extends Thread {
    /* renamed from: a */
    private static ExecutorService f959a = Executors.newSingleThreadExecutor();
    /* renamed from: b */
    private static Object f960b = new Object();
    /* renamed from: c */
    private static AtomicInteger f961c = new AtomicInteger();
    /* renamed from: j */
    private static CookieManager f962j;
    /* renamed from: z */
    private static final String[] f963z;
    /* renamed from: d */
    private String f964d;
    /* renamed from: e */
    private String f965e;
    /* renamed from: f */
    private String f966f;
    /* renamed from: g */
    private Context f967g;
    /* renamed from: h */
    private int f968h = 0;
    /* renamed from: i */
    private String f969i;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 92;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "(F\u0001\u0010r";
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
            case 0: goto L_0x0426;
            case 1: goto L_0x042a;
            case 2: goto L_0x042e;
            case 3: goto L_0x0432;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 66;
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
            case 49: goto L_0x024a;
            case 50: goto L_0x0255;
            case 51: goto L_0x0260;
            case 52: goto L_0x026b;
            case 53: goto L_0x0276;
            case 54: goto L_0x0281;
            case 55: goto L_0x028c;
            case 56: goto L_0x0297;
            case 57: goto L_0x02a2;
            case 58: goto L_0x02ad;
            case 59: goto L_0x02b8;
            case 60: goto L_0x02c3;
            case 61: goto L_0x02ce;
            case 62: goto L_0x02d9;
            case 63: goto L_0x02e4;
            case 64: goto L_0x02ef;
            case 65: goto L_0x02fa;
            case 66: goto L_0x0305;
            case 67: goto L_0x0310;
            case 68: goto L_0x031b;
            case 69: goto L_0x0326;
            case 70: goto L_0x0331;
            case 71: goto L_0x033c;
            case 72: goto L_0x0347;
            case 73: goto L_0x0352;
            case 74: goto L_0x035d;
            case 75: goto L_0x0368;
            case 76: goto L_0x0373;
            case 77: goto L_0x037e;
            case 78: goto L_0x0389;
            case 79: goto L_0x0394;
            case 80: goto L_0x039f;
            case 81: goto L_0x03aa;
            case 82: goto L_0x03b5;
            case 83: goto L_0x03c0;
            case 84: goto L_0x03cb;
            case 85: goto L_0x03d6;
            case 86: goto L_0x03e1;
            case 87: goto L_0x03ec;
            case 88: goto L_0x03f7;
            case 89: goto L_0x0402;
            case 90: goto L_0x040d;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "(F\u0001\u0010p";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "(F\u0001\u0010q";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "l\u0002^V+x\u0015CS\u000br\u0014TXx";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "(F\u0001\u0010s";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "o\u0019VNx";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "}\u0000As'\u0002TTx";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "h\u0002TE\u000f}\u0000gA.i\u0015\u000b";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "n\u0015@6u\u001dT";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "o\u0004PT+o\u0004XCmm\u0005TR;#";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "}\u0000A+x";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "<\u0003EA6i\u0003rO&yJ";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "i\u0002]\u001a";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "]\u0013EI-rP\u001c\u0000%y\u0004aH-r\u0015U/~\u0015C\u0000+q\u0015X\u001a";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "u\u001dBI";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "<\u0002BP\u0006}\u0004P\u001a";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "u\u0013RI&";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "o\u0019VN";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "n\u0015BPln\u0011F";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "u\u001dTI";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "r\u0005\\";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "<\u0019RC+xJ";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "<\u0019\\S+&";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "I$w\rz";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "n\u0015BPlf\u0019A";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u001fUE";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "j\u0015CS+s\u001e";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "]\u0013EO,<]\u0011O,X\u0015EC*L\u0018^N'R\u0005\\B'nPAH-r\u0015U/~\u0015C\u001a";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "y\u001erR2h\trO,h\u0015_Tx";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "\u001f_T'r\u0004";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "h\tAE";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "n\u0015AR-hPRO,h\u0015_Tx";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "r\u0012";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "u\u0004XM'";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "~\u0011BEbi\u0002]\u001a";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = "\u0011_\u00076<\u001fAE,<";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        r2 = 36;
        r1 = "Z\u0019]E2}\u0004Y\u0000'n\u0002^Rbs\u0016\u0011{";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01b0:
        r3[r2] = r1;
        r2 = 37;
        r1 = "<\u001fDT2i\u0004bT0y\u0011\\\fb{\u0019GEbi\u0000\u0011S#j\u0015\u0011\u001a";
        r0 = 36;
        r3 = r4;
        goto L_0x0009;
    L_0x01bb:
        r3[r2] = r1;
        r2 = 38;
        r1 = "<\\\u0011G+j\u0015\u0011U2<\u0003PV'<J";
        r0 = 37;
        r3 = r4;
        goto L_0x0009;
    L_0x01c6:
        r3[r2] = r1;
        r2 = 39;
        r1 = "o\u0011GEbp\u001fV\u0000+rPFR+h\u0015yI1h\u001fCY\u000es\u0017\u000b*";
        r0 = 38;
        r3 = r4;
        goto L_0x0009;
    L_0x01d1:
        r3[r2] = r1;
        r2 = 40;
        r1 = "0P";
        r0 = 39;
        r3 = r4;
        goto L_0x0009;
    L_0x01dc:
        r3[r2] = r1;
        r2 = 41;
        r1 = "AP\u001d\u0000%u\u0006T\u00007lPBA4yP\u000b";
        r0 = 40;
        r3 = r4;
        goto L_0x0009;
    L_0x01e7:
        r3[r2] = r1;
        r2 = 42;
        r1 = "\u0011_\u00076<\u0007CI6yP";
        r0 = 41;
        r3 = r4;
        goto L_0x0009;
    L_0x01f2:
        r3[r2] = r1;
        r2 = 43;
        r1 = "\u0011_\u00076<\u0015_C-x\u0019_Gb";
        r0 = 42;
        r3 = r4;
        goto L_0x0009;
    L_0x01fd:
        r3[r2] = r1;
        r2 = 44;
        r1 = "\u0011z<*";
        r0 = 43;
        r3 = r4;
        goto L_0x0009;
    L_0x0208:
        r3[r2] = r1;
        r2 = 45;
        r1 = "&P";
        r0 = 44;
        r3 = r4;
        goto L_0x0009;
    L_0x0213:
        r3[r2] = r1;
        r2 = 46;
        r1 = "O\u0015E\r\u0001s\u001fZI'";
        r0 = 45;
        r3 = r4;
        goto L_0x0009;
    L_0x021e:
        r3[r2] = r1;
        r2 = 47;
        r1 = "_\u0018XN#Q\u001fSI.y";
        r0 = 46;
        r3 = r4;
        goto L_0x0009;
    L_0x0229:
        r3[r2] = r1;
        r2 = 48;
        r1 = "_\u0018XN#H\u0015]E!s\u001d";
        r0 = 47;
        r3 = r4;
        goto L_0x0009;
    L_0x0234:
        r3[r2] = r1;
        r2 = 49;
        r1 = "_\u0018XN#I\u001eXC-q";
        r0 = 48;
        r3 = r4;
        goto L_0x0009;
    L_0x023f:
        r3[r2] = r1;
        r2 = 50;
        r1 = "n\u0015E\u001a";
        r0 = 49;
        r3 = r4;
        goto L_0x0009;
    L_0x024a:
        r3[r2] = r1;
        r2 = 51;
        r1 = "]\u0013EI-rP\u001c\u0000%y\u0004aH-r\u0015U/~\u0015Cw+h\u0018pN#p\tBI1I\u0002]\u00007n\u001c\u000b";
        r0 = 50;
        r3 = r4;
        goto L_0x0009;
    L_0x0255:
        r3[r2] = r1;
        r2 = 52;
        r1 = "n\u0015BP\u001dx\u0011EA";
        r0 = 51;
        r3 = r4;
        goto L_0x0009;
    L_0x0260:
        r3[r2] = r1;
        r2 = 53;
        r1 = "x\u0015RO&y YO,y>DM y\u0002\u0011D'\u0002HP6<\u0014PT#&";
        r0 = 52;
        r3 = r4;
        goto L_0x0009;
    L_0x026b:
        r3[r2] = r1;
        r2 = 54;
        r1 = "I\u001eTX2y\u0013EE&<]\u0011F#u\u001cTDbh\u001f\u0011a\u0007OPUE!n\tATlyJ";
        r0 = 53;
        r3 = r4;
        goto L_0x0009;
    L_0x0276:
        r3[r2] = r1;
        r2 = 55;
        r1 = "<\u001bTY1y\u0004\u000b";
        r0 = 54;
        r3 = r4;
        goto L_0x0009;
    L_0x0281:
        r3[r2] = r1;
        r2 = 56;
        r1 = "1]";
        r0 = 55;
        r3 = r4;
        goto L_0x0009;
    L_0x028c:
        r3[r2] = r1;
        r2 = 57;
        r1 = "z\u0019]Ex";
        r0 = 56;
        r3 = r4;
        goto L_0x0009;
    L_0x0297:
        r3[r2] = r1;
        r2 = 58;
        r1 = "Q\u001fKI.p\u0011\u001e\u0015l,P\u0019l+r\u0005I\u001bb]\u001eUR-u\u0014\u0011\u0015l-^\u0000\u001bbR\u0015IU1<F\u0011b7u\u001cU\u000f\u000eE*\u0003\u0018\u00075PpP2p\u0015fE W\u0019E\u000fw/G\u001f\u0013t<Xzh\u0016Q<\u001d\u0000.u\u001bT\u0000\u0005y\u0013ZOk<3YR-q\u0015\u001e\u0014z2@\u001f\u0012w*D\u001f\u0012q<=^B+p\u0015\u0011s#z\u0011CIm)C\u0006\u000eq*";
        r0 = 57;
        r3 = r4;
        goto L_0x0009;
    L_0x02a2:
        r3[r2] = r1;
        r2 = 59;
        r1 = "n\u0015@U'o\u0004\u0011T+q\u0015^U6&D\u0001\u0018b1P";
        r0 = 58;
        r3 = r4;
        goto L_0x0009;
    L_0x02ad:
        r3[r2] = r1;
        r2 = 60;
        r1 = " LWA+p\u0015U5u\u0004Y0y\u0004CI'oN\u000f";
        r0 = 59;
        r3 = r4;
        goto L_0x0009;
    L_0x02b8:
        r3[r2] = r1;
        r2 = 61;
        r1 = "_\u0018PR1y\u0004";
        r0 = 60;
        r3 = r4;
        goto L_0x0009;
    L_0x02c3:
        r3[r2] = r1;
        r2 = 62;
        r1 = "n\u0003Ac-x\u0015\u000b";
        r0 = 61;
        r3 = r4;
        goto L_0x0009;
    L_0x02ce:
        r3[r2] = r1;
        r2 = 63;
        r1 = "'\u0012^U,x\u0011CY";
        r0 = 62;
        r3 = r4;
        goto L_0x0009;
    L_0x02d9:
        r3[r2] = r1;
        r2 = 64;
        r1 = "<PWI.y\u001ePM'&";
        r0 = 63;
        r3 = r4;
        goto L_0x0009;
    L_0x02e4:
        r3[r2] = r1;
        r2 = 65;
        r1 = "z\u0011]S'";
        r0 = 64;
        r3 = r4;
        goto L_0x0009;
    L_0x02ef:
        r3[r2] = r1;
        r2 = 66;
        r1 = "\u0011z";
        r0 = 65;
        r3 = r4;
        goto L_0x0009;
    L_0x02fa:
        r3[r2] = r1;
        r2 = 67;
        r1 = "\u0016PWI.yJ";
        r0 = 66;
        r3 = r4;
        goto L_0x0009;
    L_0x0305:
        r3[r2] = r1;
        r2 = 68;
        r1 = "_\u001f_T'r\u0004\u001cd+o\u0000^S+h\u0019^Nx¼\u0016^R/1\u0014PT#'Ð_A/yM\u0013";
        r0 = 67;
        r3 = r4;
        goto L_0x0009;
    L_0x0310:
        r3[r2] = r1;
        r2 = 69;
        r1 = "P\u001fRA6u\u001f_";
        r0 = 68;
        r3 = r4;
        goto L_0x0009;
    L_0x031b:
        r3[r2] = r1;
        r2 = 70;
        r1 = "r\u001fE\u0000#\u0013TP6}\u0012]Ex(@\u0007\u0000o<";
        r0 = 69;
        r3 = r4;
        goto L_0x0009;
    L_0x0326:
        r3[r2] = r1;
        r2 = 71;
        r1 = "0PDR.&";
        r0 = 70;
        r3 = r4;
        goto L_0x0009;
    L_0x0331:
        r3[r2] = r1;
        r2 = 72;
        r1 = "_\u0011EC*<1BS'n\u0004XO,Y\u0002CO0<\u0004^\u0000#j\u001fXDbt\u0004EPb\u001c^S'<\u0013CA1tP\u001c\u0000";
        r0 = 71;
        r3 = r4;
        goto L_0x0009;
    L_0x033c:
        r3[r2] = r1;
        r2 = 73;
        r1 = "_\u001f_T'r\u0004\u001ct;l\u0015";
        r0 = 72;
        r3 = r4;
        goto L_0x0009;
    L_0x0347:
        r3[r2] = r1;
        r2 = 74;
        r1 = " LTR0s\u0002\u000f\u001e";
        r0 = 73;
        r3 = r4;
        goto L_0x0009;
    L_0x0352:
        r3[r2] = r1;
        r2 = 75;
        r1 = "_\u001f^K+y";
        r0 = 74;
        r3 = r4;
        goto L_0x0009;
    L_0x035d:
        r3[r2] = r1;
        r2 = 76;
        r1 = "I\u0003TRo]\u0017TN6";
        r0 = 75;
        r3 = r4;
        goto L_0x0009;
    L_0x0368:
        r3[r2] = r1;
        r2 = 77;
        r1 = "q\u0005]T+l\u0011CTmz\u001fCMox\u0011EA";
        r0 = 76;
        r3 = r4;
        goto L_0x0009;
    L_0x0373:
        r3[r2] = r1;
        r2 = 78;
        r1 = "_\u001f_T'r\u0004\u001ct;l\u0015\u000b#l\u0000]I!}\u0004XO,3\u001fRT'h]BT0y\u0011\\\u001bâ\u0018PR1y\u0004\fu\u0016Z]\t";
        r0 = 77;
        r3 = r4;
        goto L_0x0009;
    L_0x037e:
        r3[r2] = r1;
        r2 = 79;
        r1 = "i\u0002]Sx";
        r0 = 78;
        r3 = r4;
        goto L_0x0009;
    L_0x0389:
        r3[r2] = r1;
        r2 = 80;
        r1 = "t\u0004EPlw\u0015TP\u0003p\u0019GE";
        r0 = 79;
        r3 = r4;
        goto L_0x0009;
    L_0x0394:
        r3[r2] = r1;
        r2 = 81;
        r1 = "S\u0004YE0<\u0007CO,{PCE1l\u001f_S'<\u0003EA6i\u0003\u0011\rb";
        r0 = 80;
        r3 = r4;
        goto L_0x0009;
    L_0x039f:
        r3[r2] = r1;
        r2 = 82;
        r1 = "N\u0015@U'o\u0004\u0011N-hPPU6t\u001fCI8y\u0014\u000b\u0014r-P\u001c\u0000";
        r0 = 81;
        r3 = r4;
        goto L_0x0009;
    L_0x03aa:
        r3[r2] = r1;
        r2 = 83;
        r1 = "p\u001fRA6u\u001f_\u001a";
        r0 = 82;
        r3 = r4;
        goto L_0x0009;
    L_0x03b5:
        r3[r2] = r1;
        r2 = 84;
        r1 = ">KF+p\u0015_A/yM\u0013";
        r0 = 83;
        r3 = r4;
        goto L_0x0009;
    L_0x03c0:
        r3[r2] = r1;
        r2 = 85;
        r1 = "\u001f_F.u\u0013E\u001av,I\u0011\rb";
        r0 = 84;
        r3 = r4;
        goto L_0x0009;
    L_0x03cb:
        r3[r2] = r1;
        r2 = 86;
        r1 = "o\u0015CV'nPWA+p";
        r0 = 85;
        r3 = r4;
        goto L_0x0009;
    L_0x03d6:
        r3[r2] = r1;
        r2 = 87;
        r1 = "N\u0015@U'o\u0004\u0011P#h\u0018\u0011D-y\u0003\u0011N-hPTX+o\u0004\u000b\u0000v,D\u0011\rb";
        r0 = 86;
        r3 = r4;
        goto L_0x0009;
    L_0x03e1:
        r3[r2] = r1;
        r2 = 88;
        r1 = "<\u0019Bt'q\u0000^R#n\tcE&u\u0002TC6&";
        r0 = 87;
        r3 = r4;
        goto L_0x0009;
    L_0x03ec:
        r3[r2] = r1;
        r2 = 89;
        r1 = "O\u0015CV'nPTR0s\u0002\u0011\rb";
        r0 = 88;
        r3 = r4;
        goto L_0x0009;
    L_0x03f7:
        r3[r2] = r1;
        r2 = 90;
        r1 = "L?bt";
        r0 = 89;
        r3 = r4;
        goto L_0x0009;
    L_0x0402:
        r3[r2] = r1;
        r2 = 91;
        r1 = "O\u0015CV'nPCE1l\u001f_S'<\u0016PI.i\u0002T\u001av,@\u0011\rb";
        r0 = 90;
        r3 = r4;
        goto L_0x0009;
    L_0x040d:
        r3[r2] = r1;
        f963z = r4;
        r0 = java.util.concurrent.Executors.newSingleThreadExecutor();
        f959a = r0;
        r0 = new java.lang.Object;
        r0.<init>();
        f960b = r0;
        r0 = new java.util.concurrent.atomic.AtomicInteger;
        r0.<init>();
        f961c = r0;
        return;
    L_0x0426:
        r9 = 28;
        goto L_0x0020;
    L_0x042a:
        r9 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        goto L_0x0020;
    L_0x042e:
        r9 = 49;
        goto L_0x0020;
    L_0x0432:
        r9 = 32;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.af.<clinit>():void");
    }

    private af() {
    }

    private af(Context context) {
        String j = C0490b.m1719j(context, this.f964d);
        String i = C0490b.m1717i(context, this.f965e);
        String k = C0490b.m1720k(context, this.f966f);
        CookieManager cookieManager = new CookieManager();
        f962j = cookieManager;
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(f962j);
        this.f967g = context;
        this.f964d = j;
        this.f965e = i;
        this.f966f = k;
    }

    /* renamed from: a */
    public static int m1597a(String str) {
        return an.m1657a(str) ? -1 : str.equalsIgnoreCase(f963z[48]) ? 2 : str.equalsIgnoreCase(f963z[47]) ? 0 : str.equalsIgnoreCase(f963z[49]) ? 1 : -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    private cn.jpush.android.util.ag m1598a(android.content.Context r23, java.lang.String r24, int r25, long r26, boolean r28, java.io.File r29, java.lang.String r30) {
        /*
        r22 = this;
        cn.jpush.android.util.ac.m1576a();
        r2 = new java.lang.StringBuilder;
        r3 = f963z;
        r4 = 79;
        r3 = r3[r4];
        r2.<init>(r3);
        r0 = r24;
        r2 = r2.append(r0);
        r3 = f963z;
        r4 = 67;
        r3 = r3[r4];
        r3 = r2.append(r3);
        if (r29 == 0) goto L_0x0216;
    L_0x0020:
        r2 = r29.getAbsolutePath();
    L_0x0024:
        r2 = r3.append(r2);
        r3 = f963z;
        r4 = 55;
        r3 = r3[r4];
        r2 = r2.append(r3);
        r0 = r30;
        r2 = r2.append(r0);
        r3 = f963z;
        r4 = 64;
        r3 = r3[r4];
        r3 = r2.append(r3);
        if (r29 == 0) goto L_0x021a;
    L_0x0044:
        r2 = r29.getName();
    L_0x0048:
        r2 = r3.append(r2);
        r3 = f963z;
        r4 = 88;
        r3 = r3[r4];
        r2 = r2.append(r3);
        r0 = r28;
        r2.append(r0);
        cn.jpush.android.util.ac.m1576a();
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        r2 = (r26 > r2 ? 1 : (r26 == r2 ? 0 : -1));
        if (r2 < 0) goto L_0x006b;
    L_0x0064:
        r2 = 60000; // 0xea60 float:8.4078E-41 double:2.9644E-319;
        r2 = (r26 > r2 ? 1 : (r26 == r2 ? 0 : -1));
        if (r2 <= 0) goto L_0x006d;
    L_0x006b:
        r26 = 2000; // 0x7d0 float:2.803E-42 double:9.88E-321;
    L_0x006d:
        r2 = java.util.UUID.randomUUID();
        r17 = r2.toString();
        r2 = f963z;
        r3 = 56;
        r18 = r2[r3];
        r2 = f963z;
        r3 = 66;
        r19 = r2[r3];
        r2 = f963z;
        r3 = 77;
        r20 = r2[r3];
        r4 = 0;
        r14 = 0;
        r13 = 0;
        r11 = 0;
        r3 = 0;
        r2 = -1;
        r5 = f962j;
        if (r5 != 0) goto L_0x0098;
    L_0x0091:
        r5 = new java.net.CookieManager;
        r5.<init>();
        f962j = r5;
    L_0x0098:
        r16 = r3;
        r3 = r4;
    L_0x009b:
        r15 = cn.jpush.android.util.C0506s.m1798a(r23, r24);	 Catch:{ SSLPeerUnverifiedException -> 0x04fd, Exception -> 0x04e3, AssertionError -> 0x04d1, all -> 0x04cd }
        r3 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r15.setConnectTimeout(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = 30000; // 0x7530 float:4.2039E-41 double:1.4822E-319;
        r15.setReadTimeout(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = 1;
        r15.setDoInput(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = 1;
        r15.setDoOutput(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = 0;
        r15.setUseCaches(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = 90;
        r3 = r3[r4];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r15.setRequestMethod(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = 61;
        r3 = r3[r4];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = 23;
        r4 = r4[r5];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r15.setRequestProperty(r3, r4);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = 76;
        r3 = r3[r4];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = 58;
        r4 = r4[r5];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r15.addRequestProperty(r3, r4);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = android.os.Build.VERSION.SDK;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = 8;
        if (r3 >= r4) goto L_0x00f5;
    L_0x00e6:
        r3 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = 80;
        r3 = r3[r4];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = 65;
        r4 = r4[r5];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        java.lang.System.setProperty(r3, r4);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
    L_0x00f5:
        r3 = f962j;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = r3.getCookieStore();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = r3.getCookies();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = r3.size();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        if (r3 <= 0) goto L_0x011e;
    L_0x0105:
        r3 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = 75;
        r3 = r3[r4];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = ";";
        r5 = f962j;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = r5.getCookieStore();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = r5.getCookies();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = android.text.TextUtils.join(r4, r5);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r15.setRequestProperty(r3, r4);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
    L_0x011e:
        r0 = r28;
        r15.setInstanceFollowRedirects(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = 57;
        r4 = r4[r5];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3.<init>(r4);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r29;
        r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        cn.jpush.android.util.ac.m1576a();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        if (r29 == 0) goto L_0x0256;
    L_0x0138:
        r3 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = 73;
        r3 = r3[r4];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4.<init>();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r20;
        r4 = r4.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r6 = 63;
        r5 = r5[r6];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = r4.append(r5);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r17;
        r4 = r4.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = r4.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r15.setRequestProperty(r3, r4);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = r15.getOutputStream();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4 = new java.io.DataOutputStream;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4.<init>(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = new java.lang.StringBuffer;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3.<init>();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r18;
        r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r17;
        r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r19;
        r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r6 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r7 = 68;
        r6 = r6[r7];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5.<init>(r6);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r30;
        r5 = r5.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r6 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r7 = 84;
        r6 = r6[r7];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = r5.append(r6);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r6 = r29.getName();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = r5.append(r6);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r6 = "\"";
        r5 = r5.append(r6);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r19;
        r5 = r5.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = r5.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3.append(r5);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r6 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r7 = 78;
        r6 = r6[r7];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5.<init>(r6);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r19;
        r5 = r5.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = r5.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3.append(r5);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r19;
        r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = r3.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = r3.getBytes();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4.write(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = new java.io.FileInputStream;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r29;
        r3.<init>(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r5 = 1024; // 0x400 float:1.435E-42 double:5.06E-321;
        r5 = new byte[r5];	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
    L_0x01e6:
        r6 = r3.read(r5);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r7 = -1;
        if (r6 == r7) goto L_0x021e;
    L_0x01ed:
        r7 = 0;
        r4.write(r5, r7, r6);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        goto L_0x01e6;
    L_0x01f2:
        r3 = move-exception;
        r3 = r13;
        r4 = r14;
        r5 = r15;
    L_0x01f6:
        cn.jpush.android.util.ac.m1588e();	 Catch:{ all -> 0x04c5 }
        if (r4 == 0) goto L_0x01fe;
    L_0x01fb:
        r4.close();	 Catch:{ IOException -> 0x031e }
    L_0x01fe:
        if (r5 == 0) goto L_0x051b;
    L_0x0200:
        r5.disconnect();
        r13 = r3;
        r14 = r4;
        r4 = r5;
    L_0x0206:
        r3 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r2 == r3) goto L_0x0214;
    L_0x020a:
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r2 == r3) goto L_0x037a;
    L_0x020e:
        r3 = cn.jpush.android.util.C0490b.m1691b(r23);
        if (r3 != 0) goto L_0x037a;
    L_0x0214:
        r2 = 0;
    L_0x0215:
        return r2;
    L_0x0216:
        r2 = r29;
        goto L_0x0024;
    L_0x021a:
        r2 = r29;
        goto L_0x0048;
    L_0x021e:
        r3.close();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = r19.getBytes();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4.write(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3.<init>();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r18;
        r3 = r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r17;
        r3 = r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r18;
        r3 = r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r0 = r19;
        r3 = r3.append(r0);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = r3.toString();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r3 = r3.getBytes();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4.write(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4.flush();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r4.close();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
    L_0x0256:
        r12 = r15.getResponseCode();	 Catch:{ SSLPeerUnverifiedException -> 0x01f2, Exception -> 0x04e7, AssertionError -> 0x04d7 }
        r2 = r15.getHeaderFields();	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        m1603a(r2);	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r2 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r3 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r4 = 62;
        r3 = r3[r4];	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r2.<init>(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r2.append(r12);	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        cn.jpush.android.util.ac.m1576a();	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r2 = 302; // 0x12e float:4.23E-43 double:1.49E-321;
        if (r12 != r2) goto L_0x02c6;
    L_0x0276:
        r2 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r3 = 69;
        r2 = r2[r3];	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r4 = r15.getHeaderField(r2);	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r2 = new java.lang.StringBuilder;	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r3 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r5 = 83;
        r3 = r3[r5];	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r2.<init>(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r2.append(r4);	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        cn.jpush.android.util.ac.m1576a();	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        if (r25 < 0) goto L_0x02b4;
    L_0x0293:
        r5 = r25 + -1;
        r6 = 0;
        r9 = 0;
        r10 = 0;
        r2 = r22;
        r3 = r23;
        r8 = r28;
        r2 = r2.m1598a(r3, r4, r5, r6, r8, r9, r10);	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        if (r14 == 0) goto L_0x02a8;
    L_0x02a5:
        r14.close();	 Catch:{ IOException -> 0x02af }
    L_0x02a8:
        if (r15 == 0) goto L_0x0215;
    L_0x02aa:
        r15.disconnect();
        goto L_0x0215;
    L_0x02af:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x02a8;
    L_0x02b4:
        if (r14 == 0) goto L_0x02b9;
    L_0x02b6:
        r14.close();	 Catch:{ IOException -> 0x02c1 }
    L_0x02b9:
        if (r15 == 0) goto L_0x02be;
    L_0x02bb:
        r15.disconnect();
    L_0x02be:
        r2 = 0;
        goto L_0x0215;
    L_0x02c1:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x02b9;
    L_0x02c6:
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r12 != r2) goto L_0x030b;
    L_0x02ca:
        r3 = r15.getInputStream();	 Catch:{ SSLPeerUnverifiedException -> 0x0503, Exception -> 0x04ea, AssertionError -> 0x04dc }
        r4 = r15.getHeaderFields();	 Catch:{ SSLPeerUnverifiedException -> 0x050a, Exception -> 0x04ee, AssertionError -> 0x04df, all -> 0x0368 }
        r2 = new java.lang.String;	 Catch:{ SSLPeerUnverifiedException -> 0x04f3, Exception -> 0x0324, AssertionError -> 0x033d, all -> 0x0368 }
        r5 = cn.jpush.android.util.am.m1656a(r3);	 Catch:{ SSLPeerUnverifiedException -> 0x04f3, Exception -> 0x0324, AssertionError -> 0x033d, all -> 0x0368 }
        r6 = f963z;	 Catch:{ SSLPeerUnverifiedException -> 0x04f3, Exception -> 0x0324, AssertionError -> 0x033d, all -> 0x0368 }
        r7 = 23;
        r6 = r6[r7];	 Catch:{ SSLPeerUnverifiedException -> 0x04f3, Exception -> 0x0324, AssertionError -> 0x033d, all -> 0x0368 }
        r2.<init>(r5, r6);	 Catch:{ SSLPeerUnverifiedException -> 0x04f3, Exception -> 0x0324, AssertionError -> 0x033d, all -> 0x0368 }
        if (r3 == 0) goto L_0x02e6;
    L_0x02e3:
        r3.close();	 Catch:{ IOException -> 0x0306 }
    L_0x02e6:
        if (r15 == 0) goto L_0x02eb;
    L_0x02e8:
        r15.disconnect();
    L_0x02eb:
        r3 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r12 < r3) goto L_0x03a9;
    L_0x02ef:
        r3 = 300; // 0x12c float:4.2E-43 double:1.48E-321;
        if (r12 >= r3) goto L_0x03a9;
    L_0x02f3:
        if (r2 != 0) goto L_0x02fe;
    L_0x02f5:
        cn.jpush.android.util.ac.m1591g();	 Catch:{ Exception -> 0x039d }
        r2 = f963z;	 Catch:{ Exception -> 0x039d }
        r3 = 74;
        r2 = r2[r3];	 Catch:{ Exception -> 0x039d }
    L_0x02fe:
        r3 = new cn.jpush.android.util.ag;
        r3.<init>(r12, r4, r2);
        r2 = r3;
        goto L_0x0215;
    L_0x0306:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x02e6;
    L_0x030b:
        if (r14 == 0) goto L_0x0310;
    L_0x030d:
        r14.close();	 Catch:{ IOException -> 0x0319 }
    L_0x0310:
        if (r15 == 0) goto L_0x0514;
    L_0x0312:
        r15.disconnect();
        r2 = r12;
        r4 = r15;
        goto L_0x0206;
    L_0x0319:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x0310;
    L_0x031e:
        r6 = move-exception;
        r6.printStackTrace();
        goto L_0x01fe;
    L_0x0324:
        r2 = move-exception;
        r2 = r12;
        r13 = r4;
        r14 = r3;
    L_0x0328:
        cn.jpush.android.util.ac.m1591g();	 Catch:{ all -> 0x04ca }
        if (r14 == 0) goto L_0x0330;
    L_0x032d:
        r14.close();	 Catch:{ IOException -> 0x0338 }
    L_0x0330:
        if (r15 == 0) goto L_0x0518;
    L_0x0332:
        r15.disconnect();
        r4 = r15;
        goto L_0x0206;
    L_0x0338:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x0330;
    L_0x033d:
        r2 = move-exception;
        r13 = r4;
        r14 = r3;
    L_0x0340:
        r3 = new java.lang.StringBuilder;	 Catch:{ all -> 0x04ca }
        r4 = f963z;	 Catch:{ all -> 0x04ca }
        r5 = 72;
        r4 = r4[r5];	 Catch:{ all -> 0x04ca }
        r3.<init>(r4);	 Catch:{ all -> 0x04ca }
        r2 = r2.toString();	 Catch:{ all -> 0x04ca }
        r3.append(r2);	 Catch:{ all -> 0x04ca }
        cn.jpush.android.util.ac.m1588e();	 Catch:{ all -> 0x04ca }
        if (r14 == 0) goto L_0x035a;
    L_0x0357:
        r14.close();	 Catch:{ IOException -> 0x0363 }
    L_0x035a:
        if (r15 == 0) goto L_0x0514;
    L_0x035c:
        r15.disconnect();
        r2 = r12;
        r4 = r15;
        goto L_0x0206;
    L_0x0363:
        r2 = move-exception;
        r2.printStackTrace();
        goto L_0x035a;
    L_0x0368:
        r2 = move-exception;
        r14 = r3;
    L_0x036a:
        if (r14 == 0) goto L_0x036f;
    L_0x036c:
        r14.close();	 Catch:{ IOException -> 0x0375 }
    L_0x036f:
        if (r15 == 0) goto L_0x0374;
    L_0x0371:
        r15.disconnect();
    L_0x0374:
        throw r2;
    L_0x0375:
        r3 = move-exception;
        r3.printStackTrace();
        goto L_0x036f;
    L_0x037a:
        r3 = 3;
        r0 = r16;
        if (r0 < r3) goto L_0x038d;
    L_0x037f:
        r2 = new cn.jpush.android.util.ag;
        r3 = -1;
        r4 = f963z;
        r5 = 60;
        r4 = r4[r5];
        r2.<init>(r3, r13, r4);
        goto L_0x0215;
    L_0x038d:
        r3 = r16 + 1;
        java.lang.Thread.sleep(r26);	 Catch:{ InterruptedException -> 0x0397 }
        r16 = r3;
        r3 = r4;
        goto L_0x009b;
    L_0x0397:
        r5 = move-exception;
        r16 = r3;
        r3 = r4;
        goto L_0x009b;
    L_0x039d:
        r2 = move-exception;
        cn.jpush.android.util.ac.m1590f();
        r2 = f963z;
        r3 = 74;
        r2 = r2[r3];
        goto L_0x02fe;
    L_0x03a9:
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r12 < r2) goto L_0x046b;
    L_0x03ad:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r12 >= r2) goto L_0x046b;
    L_0x03b1:
        r2 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        if (r2 != r12) goto L_0x03d0;
    L_0x03b5:
        r2 = new java.lang.StringBuilder;
        r3 = f963z;
        r5 = 91;
        r3 = r3[r5];
        r2.<init>(r3);
        r0 = r24;
        r2.append(r0);
        cn.jpush.android.util.ac.m1581b();
        r2 = f963z;
        r3 = 86;
        r2 = r2[r3];
        goto L_0x02fe;
    L_0x03d0:
        r2 = 401; // 0x191 float:5.62E-43 double:1.98E-321;
        if (r2 != r12) goto L_0x03ef;
    L_0x03d4:
        r2 = new java.lang.StringBuilder;
        r3 = f963z;
        r5 = 82;
        r3 = r3[r5];
        r2.<init>(r3);
        r0 = r24;
        r2.append(r0);
        cn.jpush.android.util.ac.m1581b();
        r2 = f963z;
        r3 = 74;
        r2 = r2[r3];
        goto L_0x02fe;
    L_0x03ef:
        r2 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r2 != r12) goto L_0x040e;
    L_0x03f3:
        r2 = new java.lang.StringBuilder;
        r3 = f963z;
        r5 = 87;
        r3 = r3[r5];
        r2.<init>(r3);
        r0 = r24;
        r2.append(r0);
        cn.jpush.android.util.ac.m1581b();
        r2 = f963z;
        r3 = 74;
        r2 = r2[r3];
        goto L_0x02fe;
    L_0x040e:
        r2 = 406; // 0x196 float:5.69E-43 double:2.006E-321;
        if (r2 != r12) goto L_0x042d;
    L_0x0412:
        r2 = new java.lang.StringBuilder;
        r3 = f963z;
        r5 = 70;
        r3 = r3[r5];
        r2.<init>(r3);
        r0 = r24;
        r2.append(r0);
        cn.jpush.android.util.ac.m1581b();
        r2 = f963z;
        r3 = 74;
        r2 = r2[r3];
        goto L_0x02fe;
    L_0x042d:
        r2 = 408; // 0x198 float:5.72E-43 double:2.016E-321;
        if (r2 != r12) goto L_0x044c;
    L_0x0431:
        r2 = new java.lang.StringBuilder;
        r3 = f963z;
        r5 = 59;
        r3 = r3[r5];
        r2.<init>(r3);
        r0 = r24;
        r2.append(r0);
        cn.jpush.android.util.ac.m1581b();
        r2 = f963z;
        r3 = 74;
        r2 = r2[r3];
        goto L_0x02fe;
    L_0x044c:
        r2 = 409; // 0x199 float:5.73E-43 double:2.02E-321;
        if (r2 != r12) goto L_0x0511;
    L_0x0450:
        r2 = new java.lang.StringBuilder;
        r3 = f963z;
        r5 = 85;
        r3 = r3[r5];
        r2.<init>(r3);
        r0 = r24;
        r2.append(r0);
        cn.jpush.android.util.ac.m1581b();
        r2 = f963z;
        r3 = 74;
        r2 = r2[r3];
        goto L_0x02fe;
    L_0x046b:
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r12 < r2) goto L_0x049c;
    L_0x046f:
        r2 = 600; // 0x258 float:8.41E-43 double:2.964E-321;
        if (r12 >= r2) goto L_0x049c;
    L_0x0473:
        r2 = new java.lang.StringBuilder;
        r3 = f963z;
        r5 = 89;
        r3 = r3[r5];
        r2.<init>(r3);
        r2 = r2.append(r12);
        r3 = f963z;
        r5 = 71;
        r3 = r3[r5];
        r2 = r2.append(r3);
        r0 = r24;
        r2.append(r0);
        cn.jpush.android.util.ac.m1581b();
        r2 = f963z;
        r3 = 74;
        r2 = r2[r3];
        goto L_0x02fe;
    L_0x049c:
        r2 = new java.lang.StringBuilder;
        r3 = f963z;
        r5 = 81;
        r3 = r3[r5];
        r2.<init>(r3);
        r2 = r2.append(r12);
        r3 = f963z;
        r5 = 71;
        r3 = r3[r5];
        r2 = r2.append(r3);
        r0 = r24;
        r2.append(r0);
        cn.jpush.android.util.ac.m1581b();
        r2 = f963z;
        r3 = 74;
        r2 = r2[r3];
        goto L_0x02fe;
    L_0x04c5:
        r2 = move-exception;
        r14 = r4;
        r15 = r5;
        goto L_0x036a;
    L_0x04ca:
        r2 = move-exception;
        goto L_0x036a;
    L_0x04cd:
        r2 = move-exception;
        r15 = r3;
        goto L_0x036a;
    L_0x04d1:
        r4 = move-exception;
        r12 = r2;
        r15 = r3;
        r2 = r4;
        goto L_0x0340;
    L_0x04d7:
        r3 = move-exception;
        r12 = r2;
        r2 = r3;
        goto L_0x0340;
    L_0x04dc:
        r2 = move-exception;
        goto L_0x0340;
    L_0x04df:
        r2 = move-exception;
        r14 = r3;
        goto L_0x0340;
    L_0x04e3:
        r4 = move-exception;
        r15 = r3;
        goto L_0x0328;
    L_0x04e7:
        r3 = move-exception;
        goto L_0x0328;
    L_0x04ea:
        r2 = move-exception;
        r2 = r12;
        goto L_0x0328;
    L_0x04ee:
        r2 = move-exception;
        r2 = r12;
        r14 = r3;
        goto L_0x0328;
    L_0x04f3:
        r2 = move-exception;
        r2 = r12;
        r5 = r15;
        r21 = r3;
        r3 = r4;
        r4 = r21;
        goto L_0x01f6;
    L_0x04fd:
        r4 = move-exception;
        r4 = r14;
        r5 = r3;
        r3 = r13;
        goto L_0x01f6;
    L_0x0503:
        r2 = move-exception;
        r2 = r12;
        r3 = r13;
        r4 = r14;
        r5 = r15;
        goto L_0x01f6;
    L_0x050a:
        r2 = move-exception;
        r2 = r12;
        r4 = r3;
        r5 = r15;
        r3 = r13;
        goto L_0x01f6;
    L_0x0511:
        r2 = r11;
        goto L_0x02fe;
    L_0x0514:
        r2 = r12;
        r4 = r15;
        goto L_0x0206;
    L_0x0518:
        r4 = r15;
        goto L_0x0206;
    L_0x051b:
        r13 = r3;
        r14 = r4;
        r4 = r5;
        goto L_0x0206;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.af.a(android.content.Context, java.lang.String, int, long, boolean, java.io.File, java.lang.String):cn.jpush.android.util.ag");
    }

    /* renamed from: a */
    private String m1599a(int i) {
        this.f969i = C0404a.m1089d(this.f967g, i);
        new StringBuilder(f963z[34]).append(this.f969i);
        ac.m1576a();
        return this.f969i;
    }

    /* renamed from: a */
    private String m1600a(String str, ag agVar) {
        return m1604a(this.f967g, agVar) ? m1609d(str) : null;
    }

    /* renamed from: a */
    private String m1601a(TreeMap<String, String> treeMap) {
        if (treeMap.size() == 0) {
            ac.m1586d();
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry value : treeMap.entrySet()) {
            stringBuilder.append(value.getValue());
        }
        String m = C0404a.m1127m(this.f967g);
        new StringBuilder(f963z[6]).append(m);
        ac.m1576a();
        m = C0490b.m1671a(stringBuilder.toString() + m).toUpperCase();
        new StringBuilder(f963z[7]).append(stringBuilder);
        ac.m1576a();
        new StringBuilder(f963z[5]).append(m);
        ac.m1576a();
        return m;
    }

    /* renamed from: a */
    public static void m1602a(Context context) {
        ac.m1576a();
        if (f961c.get() >= 2) {
            ac.m1576a();
        } else {
            f959a.execute(new af(context));
        }
    }

    /* renamed from: a */
    private static void m1603a(Map<String, List<String>> map) {
        List<String> list = (List) map.get(f963z[46]);
        if (list != null) {
            for (String parse : list) {
                f962j.getCookieStore().add(null, (HttpCookie) HttpCookie.parse(parse).get(0));
            }
        }
    }

    /* renamed from: a */
    private static boolean m1604a(Context context, ag agVar) {
        Closeable openFileOutput;
        IOException e;
        FileNotFoundException e2;
        Throwable th;
        UnsupportedEncodingException e3;
        NullPointerException e4;
        if (context == null) {
            ac.m1586d();
            return false;
        }
        if (agVar != null) {
            try {
                new StringBuilder(f963z[39]).append(agVar);
                ac.m1576a();
            } catch (Exception e5) {
                ac.m1592h();
            }
        }
        String str = f963z[18];
        StringBuilder stringBuilder = new StringBuilder("");
        if (agVar.f972c != null && agVar.f972c.size() > 0) {
            for (Entry entry : agVar.f972c.entrySet()) {
                if (entry.getKey() != null) {
                    stringBuilder.append((String) entry.getKey()).append(f963z[45]);
                }
                Iterator it = ((List) entry.getValue()).iterator();
                if (it.hasNext()) {
                    stringBuilder.append((String) it.next());
                    while (it.hasNext()) {
                        stringBuilder.append(f963z[40]).append((String) it.next());
                    }
                }
                stringBuilder.append("\n");
            }
        }
        stringBuilder.append(f963z[44]);
        if (!TextUtils.isEmpty(agVar.f971b)) {
            stringBuilder.append(agVar.f971b);
        }
        try {
            context.deleteFile(str);
            openFileOutput = context.openFileOutput(str, 0);
            try {
                boolean z;
                openFileOutput.write(stringBuilder.toString().getBytes(f963z[23]));
                ah.m1624a(openFileOutput);
                try {
                    String str2 = f963z[24];
                    context.deleteFile(str2);
                    String str3 = context.getFilesDir() + File.separator;
                    Collection arrayList = new ArrayList();
                    arrayList.add(new File(str3 + f963z[18]));
                    C0505r.m1791a(arrayList, new File(str3 + str2));
                    z = true;
                } catch (IOException e6) {
                    e6.printStackTrace();
                    z = false;
                }
                return z;
            } catch (FileNotFoundException e7) {
                e2 = e7;
                try {
                    new StringBuilder(f963z[35]).append(str).append(f963z[37]).append(e2.getMessage());
                    ac.m1581b();
                    ah.m1624a(openFileOutput);
                    return false;
                } catch (Throwable th2) {
                    th = th2;
                    ah.m1624a(openFileOutput);
                    throw th;
                }
            } catch (UnsupportedEncodingException e8) {
                e3 = e8;
                new StringBuilder(f963z[43]).append(str).append(f963z[38]).append(e3.getMessage());
                ac.m1581b();
                ah.m1624a(openFileOutput);
                return false;
            } catch (IOException e9) {
                e6 = e9;
                new StringBuilder(f963z[42]).append(str).append(f963z[38]).append(e6.getMessage());
                ac.m1581b();
                ah.m1624a(openFileOutput);
                return false;
            } catch (NullPointerException e10) {
                e4 = e10;
                new StringBuilder(f963z[36]).append(str).append(f963z[41]).append(e4.getMessage());
                ac.m1581b();
                ah.m1624a(openFileOutput);
                return false;
            }
        } catch (FileNotFoundException e11) {
            e2 = e11;
            openFileOutput = null;
            new StringBuilder(f963z[35]).append(str).append(f963z[37]).append(e2.getMessage());
            ac.m1581b();
            ah.m1624a(openFileOutput);
            return false;
        } catch (UnsupportedEncodingException e12) {
            e3 = e12;
            openFileOutput = null;
            new StringBuilder(f963z[43]).append(str).append(f963z[38]).append(e3.getMessage());
            ac.m1581b();
            ah.m1624a(openFileOutput);
            return false;
        } catch (IOException e13) {
            e6 = e13;
            openFileOutput = null;
            new StringBuilder(f963z[42]).append(str).append(f963z[38]).append(e6.getMessage());
            ac.m1581b();
            ah.m1624a(openFileOutput);
            return false;
        } catch (NullPointerException e14) {
            e4 = e14;
            openFileOutput = null;
            new StringBuilder(f963z[36]).append(str).append(f963z[41]).append(e4.getMessage());
            ac.m1581b();
            ah.m1624a(openFileOutput);
            return false;
        } catch (Throwable th3) {
            th = th3;
            openFileOutput = null;
            ah.m1624a(openFileOutput);
            throw th;
        }
    }

    /* renamed from: a */
    private boolean m1605a(String str, String str2, String str3) {
        new StringBuilder(f963z[13]).append(str).append(f963z[21]).append(str2).append(f963z[22]).append(str3);
        ac.m1576a();
        TreeMap treeMap = new TreeMap();
        if (!an.m1657a(str)) {
            treeMap.put(f963z[19], str);
        }
        if (!an.m1657a(str2)) {
            treeMap.put(f963z[16], str2);
        }
        if (!an.m1657a(str3)) {
            treeMap.put(f963z[14], str3);
        }
        treeMap.put(f963z[26], C0404a.m1120k(this.f967g));
        treeMap.put(f963z[10], C0404a.m1124l(this.f967g));
        treeMap.put(f963z[8], C0500m.m1765b());
        treeMap.put(f963z[17], m1601a(treeMap));
        String str4 = "";
        for (Entry entry : treeMap.entrySet()) {
            try {
                str4 = str4 + C0869a.f2161b + ((String) entry.getKey()) + SimpleComparison.EQUAL_TO_OPERATION + URLEncoder.encode((String) entry.getValue(), f963z[23]);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        new StringBuilder(f963z[12]).append(str4);
        ac.m1576a();
        try {
            ag a = m1598a(this.f967g, this.f969i + f963z[9] + str4, 10, 30000, false, null, null);
            new StringBuilder(f963z[11]).append(a.f970a).append(f963z[15]).append(a.f971b);
            ac.m1576a();
            if (a.f970a != 200) {
                return false;
            }
            Object b;
            JSONObject c = m1608c(a.f971b);
            if (c != null) {
                if (c.optInt(f963z[25], -1) != 200) {
                    return false;
                }
                b = m1607b(c.optString(f963z[20]));
            } else if (a.f972c == null && TextUtils.isEmpty(a.f971b)) {
                b = null;
            } else {
                synchronized (f960b) {
                    this.f968h = 0;
                    try {
                        b = m1600a(str4, a);
                    } catch (Throwable th) {
                        b = null;
                    }
                    this.f967g.deleteFile(f963z[18]);
                    this.f967g.deleteFile(f963z[24]);
                }
            }
            if (TextUtils.isEmpty(b)) {
                return false;
            }
            m1610e(b);
            return true;
        } catch (Throwable th2) {
            return false;
        }
    }

    /* renamed from: b */
    public static String m1606b(Context context) {
        if (context == null) {
            ac.m1586d();
            return "";
        }
        String j = C0490b.m1719j(context, "");
        String i = C0490b.m1717i(context, "");
        j = C0490b.m1671a(j + i + C0490b.m1720k(context, ""));
        new StringBuilder(f963z[50]).append(j);
        ac.m1576a();
        return j;
    }

    /* renamed from: b */
    private String m1607b(String str) {
        CharSequence f = m1611f(str);
        new StringBuilder(f963z[53]).append(f);
        ac.m1576a();
        if (an.m1657a(f)) {
            ac.m1586d();
            return null;
        } else if (ae.f957f.matcher(f).matches()) {
            return f;
        } else {
            ac.m1586d();
            return null;
        }
    }

    /* renamed from: c */
    private static JSONObject m1608c(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    private String m1609d(String str) {
        new StringBuilder(f963z[51]).append(str);
        ac.m1581b();
        if (TextUtils.isEmpty(str)) {
            ac.m1586d();
            return null;
        }
        try {
            ag a = m1598a(this.f967g, this.f969i + f963z[9] + str, 10, 30000, false, new File(this.f967g.getFilesDir() + File.separator + f963z[24]), f963z[52]);
            new StringBuilder(f963z[11]).append(a.f970a).append(f963z[15]).append(a.f971b);
            ac.m1576a();
            if (a.f970a != 200) {
                return null;
            }
            String a2;
            JSONObject c = m1608c(a.f971b);
            if (c == null) {
                if (!(a.f972c == null && TextUtils.isEmpty(a.f971b))) {
                    if (this.f968h > 4) {
                        return null;
                    }
                    this.f968h++;
                    try {
                        a2 = m1600a(str, a);
                    } catch (Throwable th) {
                    }
                }
                a2 = null;
            } else if (c.optInt(f963z[25], -1) != 200) {
                return null;
            } else {
                a2 = m1607b(c.optString(f963z[20]));
            }
            return a2;
        } catch (Throwable th2) {
            return null;
        }
    }

    /* renamed from: e */
    private void m1610e(String str) {
        new StringBuilder(f963z[27]).append(str);
        ac.m1576a();
        C0404a.m1115i(this.f967g, str);
        try {
            String o;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f963z[20], str);
            if (!an.m1657a(this.f964d)) {
                jSONObject.put(f963z[19], this.f964d);
            }
            if (!an.m1657a(this.f966f)) {
                jSONObject.put(f963z[14], this.f966f);
            }
            if (!an.m1657a(this.f965e)) {
                jSONObject.put(f963z[16], this.f965e);
            }
            try {
                o = C0403i.m1044o(jSONObject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (!an.m1657a(o)) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put(f963z[29], o);
                jSONObject2.put(f963z[33], C0404a.m1126m());
                jSONObject2.put(f963z[30], f963z[32]);
                new StringBuilder(f963z[28]).append(o);
                ac.m1576a();
                new StringBuilder(f963z[31]).append(jSONObject2.toString());
                ac.m1576a();
                C0459i.m1413a(this.f967g, jSONObject2);
                C0404a.m1092d(this.f967g, false);
            }
        } catch (JSONException e2) {
            ac.m1593i();
        }
    }

    /* renamed from: f */
    private String m1611f(String str) {
        try {
            return C0489a.m1575a(str, C0404a.m1127m(this.f967g).substring(0, 16));
        } catch (Exception e) {
            new StringBuilder(f963z[54]).append(e.getMessage());
            ac.m1586d();
            return null;
        }
    }

    public final void run() {
        int i = 1;
        int i2 = 0;
        f961c.incrementAndGet();
        try {
            if (an.m1657a(this.f964d) && an.m1657a(this.f965e) && an.m1657a(this.f966f)) {
                ac.m1586d();
                return;
            }
            String n = C0404a.m1130n(this.f967g);
            if (an.m1657a(n)) {
                String str;
                C0404a.m1055J();
                n = this.f966f;
                if (!an.m1657a(n)) {
                    if (n.startsWith(f963z[0]) || n.startsWith(f963z[1])) {
                        i = 0;
                        new StringBuilder(f963z[3]).append(i);
                        ac.m1576a();
                        if (i != -1) {
                            m1599a(i);
                            if (!an.m1657a(this.f969i)) {
                                m1605a(this.f964d, this.f965e, this.f966f);
                            }
                        } else {
                            str = "";
                            while (i2 < 3) {
                                m1599a(i2);
                                new StringBuilder(f963z[3]).append(i2);
                                ac.m1576a();
                                i2++;
                                if (!an.m1657a(this.f969i) || r0.equals(this.f969i)) {
                                    ac.m1576a();
                                } else {
                                    str = this.f969i;
                                    if (m1605a(this.f964d, this.f965e, this.f966f)) {
                                        break;
                                    }
                                }
                            }
                        }
                    } else {
                        if (!n.startsWith(f963z[4])) {
                            if (n.startsWith(f963z[2])) {
                                i = 2;
                            }
                        }
                        new StringBuilder(f963z[3]).append(i);
                        ac.m1576a();
                        if (i != -1) {
                            str = "";
                            while (i2 < 3) {
                                m1599a(i2);
                                new StringBuilder(f963z[3]).append(i2);
                                ac.m1576a();
                                i2++;
                                if (an.m1657a(this.f969i)) {
                                }
                                ac.m1576a();
                            }
                        } else {
                            m1599a(i);
                            if (an.m1657a(this.f969i)) {
                                m1605a(this.f964d, this.f965e, this.f966f);
                            }
                        }
                    }
                }
                i = -1;
                new StringBuilder(f963z[3]).append(i);
                ac.m1576a();
                if (i != -1) {
                    m1599a(i);
                    if (an.m1657a(this.f969i)) {
                        m1605a(this.f964d, this.f965e, this.f966f);
                    }
                } else {
                    str = "";
                    while (i2 < 3) {
                        m1599a(i2);
                        new StringBuilder(f963z[3]).append(i2);
                        ac.m1576a();
                        i2++;
                        if (an.m1657a(this.f969i)) {
                        }
                        ac.m1576a();
                    }
                }
            } else {
                m1610e(n);
            }
            f961c.decrementAndGet();
        } catch (Exception e) {
        }
    }
}
