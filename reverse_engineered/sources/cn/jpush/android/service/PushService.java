package cn.jpush.android.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Process;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import cn.jpush.android.C0404a;
import cn.jpush.android.C0427c;
import cn.jpush.android.C0448e;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.data.JPushLocalNotification;
import cn.jpush.android.helpers.C0452b;
import cn.jpush.android.helpers.C0454d;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.helpers.ConnectingHelper;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0502o;
import cn.jpush.android.util.C0507u;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.af;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.an;
import cn.jpush.p005b.p006a.p007a.C0517h;
import cn.jpush.p005b.p006a.p007a.C0525m;
import cn.jpush.p005b.p006a.p008b.C0527q;
import com.google.android.gms.location.places.Place;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;

public class PushService extends Service {
    /* renamed from: a */
    public static long f807a;
    /* renamed from: b */
    public static String f808b;
    /* renamed from: c */
    public static boolean f809c = false;
    /* renamed from: d */
    public static boolean f810d = false;
    /* renamed from: z */
    private static final String[] f811z;
    /* renamed from: e */
    private boolean f812e = true;
    /* renamed from: f */
    private C0475n f813f;
    /* renamed from: g */
    private ExecutorService f814g;
    /* renamed from: h */
    private C0472k f815h;
    /* renamed from: i */
    private C0474m f816i;
    /* renamed from: j */
    private boolean f817j = false;
    /* renamed from: k */
    private int f818k = 0;
    /* renamed from: l */
    private int f819l = 0;
    /* renamed from: m */
    private long f820m = 0;
    /* renamed from: n */
    private C0427c f821n = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 76;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "QE&/(dB#.\u0018d";
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
            case 0: goto L_0x0386;
            case 1: goto L_0x0389;
            case 2: goto L_0x038d;
            case 3: goto L_0x0391;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "n^\u0019(\u001ch^\u0013&\u0012mU1gV!S:)\u0015dS!.\u0014o\n";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "-\u0010'\"\bqs:#\u001e;";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "@S!.\u0014o\u0010xg\u0014ox0&\tuR0&\u000fRE6$\u001edTuj[b_;)\u001ebD<(\u0015;";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "iU45\u000fcU43[,\u0010?2\u0012e\n";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "RU;#[iU45\u000f!R0&\u000f";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "-\u00103+\u001af\n";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "@S!.\u0014o\u0010xg\u0014ot<4\u0018n^;\"\u0018uU1gV!S:)\u0015dS!.\u0014o\n";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "O_u)\u001euG:5\u0010!S:)\u0015dS!.\u0014o\u001eu\u0000\u0012wUu2\u000b!D:g\buQ'3[b_;)\u001ebD<(\u0015!D=5\u001e`T{";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "@S!.\u0014o\u0010xg\tdC!&\tu~03\fnB>.\u0015fs9.\u001eoDyg\u000bhTo";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "@S!.\u0014o\u0010xg\u0014ox0&\tuR0&\u000fUY8\"\u0014tDuj[uY8\"\u0014tD\u0001.\u0016dCo";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "@\\'\"\u001aeIu+\u0014fW0#[h^{g<hF0g\u000eq\u0010!([sU!5\u0002/";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "HCu$\u0014o^0$\u000fh^2g\u0015nG{g<hF0g\u000eq\u0010!([sU!5\u0002/";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "<\rhzF<\rhzF<\rhzF<\rhzF<\rhzF<\rhzF<\rhzF<\rhzF<\rhzF<\rhzF<\rhzF<\rhzF";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "n^\u0011\"\buB:>[,\u0010%5\u0014bU&42e\n";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "RU'1\u0012bUu%\u000eoT9\"[,\u0010";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "eU65\u001e`C0\t\u0014uY3.\u0018`D<(\u0015;";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "b^{-\u000btC=i\u001aoT'(\u0012e\u001e<)\u000fd^!i8N~\u001b\u00028Uy\u0003\u000e/Xo\u0016\u000f:Ow\u0010";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "`@%";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "h]\n5\u001epE04\u000f^Z&(\u0015";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "m_6&\u0017^^:3\u0012gY6&\u000fh_;";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "b^{-\u000btC=i\u001aoT'(\u0012e\u001e<)\u000fd^!i)Dc\u0001\b)D`\u0000\u00143";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "d^4%\u0017do%2\bio!.\u0016d";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "-\u00106(\u0015oU63\u0012n^o";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "bX4)\u001cdo%&\u0018jQ2\"\u0015`]0";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "b^{-\u000btC=i\u001aoT'(\u0012e\u001e<)\u000fd^!i(U\u0005\u0017.Rx";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "m_6&\u0017^^:3\u0012gY6&\u000fh_;\u0018\u0012e";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "rD:7$uX'\"\u001ae";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "sD6";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "b^{-\u000btC=i\u001aoT'(\u0012e\u001e<)\u000fd^!i:My\u0014\u0014$Uq\u0012\u0014";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "-\u0010%,\u001c;";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "uQ24";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "b^{-\u000btC=i\u001aoT'(\u0012e\u001e<)\u000fd^!i.Ru\u0007\u0018<S\u0000\t?";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "n^\u00063\u001asD\u0016(\u0016lQ;#[,\u0010<)\u000fd^!}";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "T^=&\u0015e\\0#[rU'1\u0012bUu&\u0018uY:)[,\u0010";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = "b^{-\u000btC=i\u001aoT'(\u0012e\u001e<)\u000fd^!i)Us";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        r2 = 36;
        r1 = "o_!.\u001dhS43\u0012n^\n%\u000ehT9\"\t^Y1";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01b0:
        r3[r2] = r1;
        r2 = 37;
        r1 = "IQ;#\u0017d\u00104$\u000fh_;g\u001dnBu*\u000emD<g\u000fx@0gA!";
        r0 = 36;
        r3 = r4;
        goto L_0x0009;
    L_0x01bb:
        r3[r2] = r1;
        r2 = 38;
        r1 = "sD6\u0018\u001fd\\4>";
        r0 = 37;
        r3 = r4;
        goto L_0x0009;
    L_0x01c6:
        r3[r2] = r1;
        r2 = 39;
        r1 = "qE&/$rD:7\u000bdT";
        r0 = 38;
        r3 = r4;
        goto L_0x0009;
    L_0x01d1:
        r3[r2] = r1;
        r2 = 40;
        r1 = "b^{-\u000btC=i\u001aoT'(\u0012e\u001e<)\u000fd^!i6T|\u0001\u000e$Qb\u001a\u0004>Rc";
        r0 = 39;
        r3 = r4;
        goto L_0x0009;
    L_0x01dc:
        r3[r2] = r1;
        r2 = 41;
        r1 = "`\\<&\b";
        r0 = 40;
        r3 = r4;
        goto L_0x0009;
    L_0x01e7:
        r3[r2] = r1;
        r2 = 42;
        r1 = "uQ2&\u0017hQ&g\u001eyS07\u000fh_;}";
        r0 = 41;
        r3 = r4;
        goto L_0x0009;
    L_0x01f2:
        r3[r2] = r1;
        r2 = 43;
        r1 = "tC05$fB:2\u0015e";
        r0 = 42;
        r3 = r4;
        goto L_0x0009;
    L_0x01fd:
        r3[r2] = r1;
        r2 = 44;
        r1 = "T^0?\u000bdS!\"\u001f!\u001du\r+tC=g\u0012oY!g\u001d`Y9\"\u001f-\u0010&3\u0014q\u0010&\"\twY6\"[r_:)";
        r0 = 43;
        r3 = r4;
        goto L_0x0009;
    L_0x0208:
        r3[r2] = r1;
        r2 = 45;
        r1 = "o_!.\u001dhS43\u0012n^\n*\u001ay^ *";
        r0 = 44;
        r3 = r4;
        goto L_0x0009;
    L_0x0213:
        r3[r2] = r1;
        r2 = 46;
        r1 = "@S!.\u0014o\u0010xg\u0013`^1+\u001eRU'1\u0012bU\u0014$\u000fh_;gV!Q63\u0012n^o";
        r0 = 45;
        r3 = r4;
        goto L_0x0009;
    L_0x021e:
        r3[r2] = r1;
        r2 = 47;
        r1 = "b^{-\u000btC=i\u001aoT'(\u0012e\u001e<)\u000fd^!i)D`\u001a\u0015/";
        r0 = 46;
        r3 = r4;
        goto L_0x0009;
    L_0x0229:
        r3[r2] = r1;
        r2 = 48;
        r1 = "sU%(\tu";
        r0 = 47;
        r3 = r4;
        goto L_0x0009;
    L_0x0234:
        r3[r2] = r1;
        r2 = 49;
        r1 = "b^{-\u000btC=i\u0012l\u001e4)\u001fs_<#U`S!.\u0014o\u001e\u001c\n$Su\u0004\u0012>Rd";
        r0 = 48;
        r3 = r4;
        goto L_0x0009;
    L_0x023f:
        r3[r2] = r1;
        r2 = 50;
        r1 = "rU$\u0018\u0012e";
        r0 = 49;
        r3 = r4;
        goto L_0x0009;
    L_0x024a:
        r3[r2] = r1;
        r2 = 51;
        r1 = "b^{-\u000btC=i\u001aoT'(\u0012e\u001e<)\u000fd^!i)Dw\u001c\u0014/Db";
        r0 = 50;
        r3 = r4;
        goto L_0x0009;
    L_0x0255:
        r3[r2] = r1;
        r2 = 52;
        r1 = "lE93\u0012^D,7\u001e";
        r0 = 51;
        r3 = r4;
        goto L_0x0009;
    L_0x0260:
        r3[r2] = r1;
        r2 = 53;
        r1 = "rY9\"\u0015bU\n7\u000erX\n3\u0012lU";
        r0 = 52;
        r3 = r4;
        goto L_0x0009;
    L_0x026b:
        r3[r2] = r1;
        r2 = 54;
        r1 = "h]\n$\u0016e";
        r0 = 53;
        r3 = r4;
        goto L_0x0009;
    L_0x0276:
        r3[r2] = r1;
        r2 = 55;
        r1 = "o_!.\u001dhS43\u0012n^\n%\u000ehT9\"\t";
        r0 = 54;
        r3 = r4;
        goto L_0x0009;
    L_0x0281:
        r3[r2] = r1;
        r2 = 56;
        r1 = "T^0?\u000bdS!\"\u001f!\u001du.\u0017mU2&\u0017!y\u0018g\tdA \"\bu\u001e";
        r0 = 55;
        r3 = r4;
        goto L_0x0009;
    L_0x028c:
        r3[r2] = r1;
        r2 = 57;
        r1 = "b^{-\u000btC=i\u001aoT'(\u0012e\u001e<)\u000fd^!i2Oy\u0001";
        r0 = 56;
        r3 = r4;
        goto L_0x0009;
    L_0x0297:
        r3[r2] = r1;
        r2 = 58;
        r1 = "n^\u00063\u001asD\u0016(\u0016lQ;#[,\u0010;(\u000f!F4+\u0012e\u0010\u001f\u0017\u000erXu5\u000eo^<)\u001c!\u001du\u0014\u0013nE9#[o_!g\u0019d\u0010=\"\td\u001e";
        r0 = 57;
        r3 = r4;
        goto L_0x0009;
    L_0x02a2:
        r3[r2] = r1;
        r2 = 59;
        r1 = "o_!.\u001dhS43\u0012n^\n.\u001f";
        r0 = 58;
        r3 = r4;
        goto L_0x0009;
    L_0x02ad:
        r3[r2] = r1;
        r2 = 60;
        r1 = "b_;)\u001ebD<(\u0015,C!&\u000fd";
        r0 = 59;
        r3 = r4;
        goto L_0x0009;
    L_0x02b8:
        r3[r2] = r1;
        r2 = 61;
        r1 = "n^\u0011.\bb_;)\u001ebD0#[`^1g\tdD'>[sU&3\u001asDu$\u0014o^uj[eU9&\u0002;";
        r0 = 60;
        r3 = r4;
        goto L_0x0009;
    L_0x02c3:
        r3[r2] = r1;
        r2 = 62;
        r1 = "@S!.\u0014o\u0010xg\tdD'>8n^;\"\u0018u\u0010xg\u001fhC6(\u0015oU63\u001eed<*\u001er\n";
        r0 = 61;
        r3 = r4;
        goto L_0x0009;
    L_0x02ce:
        r3[r2] = r1;
        r2 = 63;
        r1 = "`\\45\u0016";
        r0 = 62;
        r3 = r4;
        goto L_0x0009;
    L_0x02d9:
        r3[r2] = r1;
        r2 = 64;
        r1 = "VUu!\u0014t^1g\u000fiUu&\u000bq{0>[hCu$\u0013`^2\"\u001f/\u0010\u0002.\u0017m\u0010'\"VsU2.\buU'i";
        r0 = 63;
        r3 = r4;
        goto L_0x0009;
    L_0x02e4:
        r3[r2] = r1;
        r2 = 65;
        r1 = "RU'1\u0012bU\u001d\"\u0017qU'";
        r0 = 64;
        r3 = r4;
        goto L_0x0009;
    L_0x02ef:
        r3[r2] = r1;
        r2 = 66;
        r1 = "@S!.\u0014o\nu.\u0015hDu\u0017\u000erX\u0006\"\twY6\"";
        r0 = 65;
        r3 = r4;
        goto L_0x0009;
    L_0x02fa:
        r3[r2] = r1;
        r2 = 67;
        r1 = "RU'1\u0012bUu*\u001ah^u3\u0013sU4#[,\u0010!/\tdQ1\u000e\u001f;";
        r0 = 66;
        r3 = r4;
        goto L_0x0009;
    L_0x0305:
        r3[r2] = r1;
        r2 = 68;
        r1 = "oE9+";
        r0 = 67;
        r3 = r4;
        goto L_0x0009;
    L_0x0310:
        r3[r2] = r1;
        r2 = 69;
        r1 = "K` 4\u0013!B )\u0015h^2g\u0018iU6,\u001ee\u00103&\u0012mU1f";
        r0 = 68;
        r3 = r4;
        goto L_0x0009;
    L_0x031b:
        r3[r2] = r1;
        r2 = 70;
        r1 = "@S!.\u0014o\u0010xg\u0014o|: \u001cdT\u001c)[,\u00106(\u0015oU63\u0012n^o";
        r0 = 69;
        r3 = r4;
        goto L_0x0009;
    L_0x0326:
        r3[r2] = r1;
        r2 = 71;
        r1 = "HCu$\u0014o^0$\u000fh^2g\u0015nG{g<hF0g\u000eq\u0010!([sU&3\u001asD{";
        r0 = 70;
        r3 = r4;
        goto L_0x0009;
    L_0x0331:
        r3[r2] = r1;
        r2 = 72;
        r1 = "@\\'\"\u001aeIu+\u0014fW0#[h^{g<hF0g\u000eq\u0010!([sU&3\u001asD{";
        r0 = 71;
        r3 = r4;
        goto L_0x0009;
    L_0x033c:
        r3[r2] = r1;
        r2 = 73;
        r1 = "@S!.\u0014o\u0010xg\tdC!&\tud=\"\u0015IU45\u000fcU43";
        r0 = 72;
        r3 = r4;
        goto L_0x0009;
    L_0x0347:
        r3[r2] = r1;
        r2 = 74;
        r1 = "@S!.\u0014o\nu/\u001aoT9\"(u_%\u0017\u000erXuj[`@%\f\u001ex\n";
        r0 = 73;
        r3 = r4;
        goto L_0x0009;
    L_0x0352:
        r3[r2] = r1;
        r2 = 75;
        r1 = "iQ;#\u0017dc!(\u000bQE&/[`@%k[rU;#\u001es\u001005\tnBog^r\u0010p4";
        r0 = 74;
        r3 = r4;
        goto L_0x0009;
    L_0x035d:
        r3[r2] = r1;
        f811z = r4;
        r0 = "`\\9";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x03a2;
    L_0x036c:
        r3 = r0;
        r4 = r2;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x0371:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0395;
            case 1: goto L_0x0397;
            case 2: goto L_0x039a;
            case 3: goto L_0x039d;
            default: goto L_0x0378;
        };
    L_0x0378:
        r5 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
    L_0x037a:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x03a0;
    L_0x0382:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0371;
    L_0x0386:
        r9 = 1;
        goto L_0x0020;
    L_0x0389:
        r9 = 48;
        goto L_0x0020;
    L_0x038d:
        r9 = 85;
        goto L_0x0020;
    L_0x0391:
        r9 = 71;
        goto L_0x0020;
    L_0x0395:
        r5 = 1;
        goto L_0x037a;
    L_0x0397:
        r5 = 48;
        goto L_0x037a;
    L_0x039a:
        r5 = 85;
        goto L_0x037a;
    L_0x039d:
        r5 = 71;
        goto L_0x037a;
    L_0x03a0:
        r1 = r0;
        r0 = r3;
    L_0x03a2:
        if (r1 > r2) goto L_0x036c;
    L_0x03a4:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f808b = r0;
        r0 = 0;
        f809c = r0;
        r0 = 0;
        f810d = r0;
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.PushService.<clinit>():void");
    }

    /* renamed from: a */
    private void m1435a() {
        ac.m1578a(f811z[0], f811z[73]);
        if (m1451e()) {
            ac.m1582b(f811z[0], f811z[71]);
        } else if (!this.f817j || m1450d()) {
            this.f816i.removeMessages(1011);
            this.f816i.removeMessages(1005);
            m1443b();
        } else {
            ac.m1582b(f811z[0], f811z[72]);
        }
    }

    /* renamed from: a */
    private void m1436a(long j) {
        ac.m1581b();
        new Thread(new C0473l(this, j)).start();
    }

    /* renamed from: a */
    static /* synthetic */ void m1437a(PushService pushService, long j) {
        ac.m1582b(f811z[0], new StringBuilder(f811z[70]).append(j).toString());
        pushService.f817j = true;
        pushService.f818k = 0;
        pushService.f819l = 0;
        ConnectingHelper.sendConnectionChanged(pushService.getApplicationContext(), C0462a.f830a);
        pushService.f816i.sendEmptyMessageDelayed(1005, 15000);
        pushService.f813f.m1527a();
    }

    /* renamed from: a */
    static /* synthetic */ void m1440a(PushService pushService, boolean z) {
        if (z || System.currentTimeMillis() - pushService.f820m >= 30000) {
            ac.m1582b(f811z[0], f811z[5]);
            pushService.f816i.removeMessages(1005);
            if (!C0472k.f866b.get() && pushService.f817j) {
                Long valueOf = Long.valueOf(C0404a.m1129n());
                int l = C0404a.m1123l();
                long x = C0404a.m1144x();
                short iMLoginFlag = ConnectingHelper.getIMLoginFlag();
                new StringBuilder(f811z[4]).append(x).append(f811z[6]).append(iMLoginFlag);
                ac.m1581b();
                PushProtocol.HbJPush(C0472k.f865a.get(), valueOf.longValue(), l, x, iMLoginFlag);
                if (!pushService.f816i.hasMessages(Place.TYPE_SUBLOCALITY)) {
                    pushService.f816i.sendEmptyMessageDelayed(Place.TYPE_SUBLOCALITY, AbstractComponentTracker.LINGERING_TIMEOUT);
                    return;
                }
                return;
            }
        }
        ac.m1576a();
    }

    /* renamed from: a */
    private void m1441a(String str, String str2) {
        try {
            new StringBuilder(f811z[74]).append(str2);
            ac.m1581b();
            if (an.m1657a(str) || an.m1657a(str2)) {
                String.format(f811z[75], new Object[]{str, str2});
                ac.m1588e();
                return;
            }
            if (this.f816i.hasMessages(1005)) {
                ac.m1576a();
                this.f816i.removeMessages(1005);
            }
            ConnectingHelper.sendConnectionChanged(getApplicationContext(), C0462a.f831b);
            if (this.f815h == null) {
                ac.m1586d();
            } else {
                this.f815h.m1512a();
            }
            if (this.f813f != null) {
                this.f813f.m1530b();
            }
            stopSelf();
        } catch (Exception e) {
        } finally {
            if (this.f816i.hasMessages(1005)) {
                ac.m1576a();
                this.f816i.removeMessages(1005);
            }
            ConnectingHelper.sendConnectionChanged(getApplicationContext(), C0462a.f831b);
            if (this.f815h == null) {
                ac.m1586d();
            } else {
                this.f815h.m1512a();
            }
            if (this.f813f != null) {
                this.f813f.m1530b();
            }
            stopSelf();
        }
    }

    /* renamed from: a */
    private static void m1442a(ExecutorService executorService) {
        ac.m1576a();
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                    executorService.shutdownNow();
                    if (!executorService.awaitTermination(100, TimeUnit.MILLISECONDS)) {
                        ac.m1576a();
                    }
                }
            } catch (InterruptedException e) {
                executorService.shutdownNow();
                ac.m1576a();
                Thread.currentThread().interrupt();
            }
            ac.m1576a();
        }
    }

    /* renamed from: b */
    private synchronized void m1443b() {
        ac.m1582b(f811z[0], new StringBuilder(f811z[9]).append(Process.myPid()).toString());
        if (C0490b.m1691b(getApplicationContext())) {
            if (!(this.f814g == null || this.f814g.isShutdown())) {
                ac.m1581b();
                m1442a(this.f814g);
            }
            this.f814g = Executors.newSingleThreadExecutor();
            this.f815h = new C0472k(getApplicationContext(), this.f816i, false);
            this.f814g.execute(this.f815h);
        } else {
            ac.m1585c(f811z[0], f811z[8]);
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m1445b(PushService pushService, long j) {
        ac.m1582b(f811z[0], new StringBuilder(f811z[7]).append(j).toString());
        boolean z = C0404a.m1082c(pushService.getApplicationContext()) > 0;
        if (C0472k.f865a.get() == 0 && z) {
            ac.m1581b();
            return;
        }
        if (pushService.f813f != null) {
            pushService.f813f.m1530b();
        }
        pushService.f817j = false;
        pushService.f819l = 0;
        ConnectingHelper.sendConnectionChanged(pushService.getApplicationContext(), C0462a.f831b);
        m1442a(pushService.f814g);
        if (C0490b.m1691b(pushService.getApplicationContext())) {
            pushService.m1447c();
        }
        pushService.f818k++;
    }

    /* renamed from: c */
    private void m1447c() {
        ac.m1582b(f811z[0], new StringBuilder(f811z[62]).append(this.f818k).toString());
        int f = C0490b.m1707f(getApplicationContext());
        int pow = (int) ((Math.pow(2.0d, (double) this.f818k) * 3.0d) * 1000.0d);
        int q = C0404a.m1136q();
        if (pow > (q * 1000) / 2) {
            pow = (q * 1000) / 2;
        }
        if (this.f818k >= 5 && f != 1) {
            ac.m1581b();
        } else if (this.f816i.hasMessages(1011)) {
            ac.m1581b();
        } else {
            ac.m1582b(f811z[0], new StringBuilder(f811z[61]).append(pow).toString());
            this.f816i.sendEmptyMessageDelayed(1011, (long) pow);
        }
    }

    /* renamed from: c */
    static /* synthetic */ void m1448c(PushService pushService, long j) {
        ac.m1582b(f811z[0], new StringBuilder(f811z[3]).append(j).toString());
        if (j != C0472k.f865a.get()) {
            ac.m1581b();
            return;
        }
        if (pushService.f816i.hasMessages(Place.TYPE_SUBLOCALITY)) {
            pushService.f816i.removeMessages(Place.TYPE_SUBLOCALITY);
        }
        pushService.f820m = System.currentTimeMillis();
        pushService.f819l = 0;
        C0469h.m1498a(pushService.getApplicationContext()).m1510d(pushService.getApplicationContext());
        if (C0404a.m1071a()) {
            C0502o.m1776b(pushService.getApplicationContext());
        }
        if (C0404a.m1104f()) {
            C0507u.m1804a(pushService.getApplicationContext());
        }
        if (C0404a.m1119j(pushService.getApplicationContext()) && C0404a.m1079b()) {
            C0459i.m1414a(pushService.getApplicationContext(), true, f808b, f810d, f809c);
        }
        if (C0404a.m1094d()) {
            C0459i.m1412a(pushService.getApplicationContext(), null);
        }
        if (C0404a.m1132o(pushService.getApplicationContext())) {
            af.m1602a(pushService.getApplicationContext());
        }
        if (C0448e.f761m) {
            pushService.m1436a(3600);
        }
    }

    /* renamed from: d */
    static /* synthetic */ void m1449d(PushService pushService) {
        pushService.f819l++;
        ac.m1582b(f811z[0], new StringBuilder(f811z[10]).append(pushService.f819l).toString());
        ac.m1578a(f811z[0], f811z[13]);
        if (pushService.m1451e()) {
            ac.m1582b(f811z[0], f811z[12]);
            pushService.f816i.sendEmptyMessageDelayed(1005, AbstractComponentTracker.LINGERING_TIMEOUT);
        } else if (!pushService.f817j || pushService.m1450d()) {
            if (pushService.f815h != null) {
                pushService.f815h.m1512a();
            }
            pushService.m1447c();
        } else {
            ac.m1582b(f811z[0], f811z[11]);
            pushService.f816i.sendEmptyMessageDelayed(1005, 5000);
        }
    }

    /* renamed from: d */
    private boolean m1450d() {
        return this.f819l > 1;
    }

    /* renamed from: e */
    private boolean m1451e() {
        return (C0472k.f865a.get() == 0 || this.f817j) ? false : true;
    }

    public IBinder onBind(Intent intent) {
        ac.m1581b();
        return this.f821n;
    }

    public void onCreate() {
        ac.m1584c();
        new StringBuilder(f811z[67]).append(Thread.currentThread().getId());
        ac.m1576a();
        C0448e.f760l = true;
        this.f821n = new C0454d(this);
        f807a = Thread.currentThread().getId();
        ServiceInterface.m1470b(getApplicationContext(), true);
        super.onCreate();
        this.f816i = new C0474m(this);
        ac.m1582b(f811z[0], f811z[66]);
        if (C0448e.m1359a(getApplicationContext())) {
            this.f812e = C0490b.m1729o(getApplicationContext());
            if (this.f812e) {
                this.f813f = new C0475n(getApplicationContext(), this.f816i);
                Context applicationContext = getApplicationContext();
                String C = C0404a.m1048C();
                if (!(an.m1657a(C) || f811z[68].equals(C) || C0448e.f754f.equalsIgnoreCase(C))) {
                    ac.m1582b(f811z[65], f811z[64]);
                    C0404a.m1146z();
                    ah.m1618a(applicationContext);
                }
                C0490b.m1736s(getApplicationContext());
            } else {
                ac.m1587d(f811z[0], f811z[69]);
            }
        }
        ac.m1576a();
        long q = 1000 * ((long) C0404a.m1136q());
        ((AlarmManager) getSystemService(f811z[63])).setInexactRepeating(0, System.currentTimeMillis() + q, q, PendingIntent.getBroadcast(this, 0, new Intent(this, AlarmReceiver.class), 0));
        if (C0448e.f761m) {
            m1436a(0);
        }
    }

    public void onDestroy() {
        super.onDestroy();
        new StringBuilder(f811z[14]).append(Process.myPid());
        ac.m1581b();
        C0448e.f760l = false;
        if (this.f816i != null) {
            this.f816i.removeCallbacksAndMessages(null);
        }
        C0490b.m1731p(getApplicationContext());
        if (this.f813f != null) {
            C0475n c0475n = this.f813f;
            if (VERSION.SDK_INT >= 18) {
                c0475n.quitSafely();
            } else {
                c0475n.quit();
            }
        }
        if (!(this.f815h == null || C0472k.f865a.get() == 0)) {
            this.f815h.m1512a();
        }
        if (this.f814g != null && !this.f814g.isShutdown()) {
            m1442a(this.f814g);
        }
    }

    public int onStartCommand(Intent intent, int i, int i2) {
        ac.m1582b(f811z[0], new StringBuilder(f811z[33]).append(intent).append(f811z[30]).append(C0448e.f751c).append(f811z[23]).append(C0472k.f865a.get()).toString());
        if (this.f812e) {
            Bundle bundle = null;
            String str = null;
            if (intent != null) {
                str = intent.getAction();
                bundle = intent.getExtras();
            }
            if (bundle != null) {
                new StringBuilder(f811z[15]).append(bundle.toString());
                ac.m1576a();
            }
            if (!(str == null || bundle == null)) {
                ac.m1582b(f811z[0], new StringBuilder(f811z[46]).append(str).toString());
                int i3;
                int i4;
                if (f811z[49].equals(str)) {
                    str = bundle.getString(f811z[19]);
                    i3 = bundle.getInt(f811z[54]);
                    if (str == null || i3 == 0) {
                        ac.m1589e(f811z[0], f811z[56]);
                    } else {
                        C0517h a = C0527q.m1871a(str, i3).m1872a(C0448e.f754f);
                        i4 = CoreConstants.MILLIS_IN_ONE_MINUTE;
                        if (i3 == 3 || i3 == 4) {
                            i4 = 300000;
                        }
                        if (i3 == 1) {
                            if (ServiceInterface.m1475d(getApplicationContext())) {
                                C0404a.m1067a(getApplicationContext(), true);
                                C0404a.m1076b(getApplicationContext(), 0);
                                ServiceInterface.m1470b(getApplicationContext(), true);
                                m1443b();
                            } else {
                                C0404a.m1067a(getApplicationContext(), false);
                            }
                        }
                        if (this.f813f == null) {
                            ac.m1589e(f811z[0], f811z[44]);
                            this.f816i.sendEmptyMessageDelayed(1003, 100);
                        } else {
                            this.f813f.m1529a(a, i4);
                        }
                    }
                } else if (f811z[35].equals(str)) {
                    C0490b.m1721k(this);
                    if (C0472k.f865a.get() == 0) {
                        m1435a();
                    } else {
                        i4 = bundle.getInt(f811z[38], 0);
                        if (an.m1657a(bundle.getString(f811z[28]))) {
                            this.f816i.sendEmptyMessage(1005);
                        } else if (i4 == 0) {
                            this.f816i.removeMessages(1005);
                            if (!this.f816i.hasMessages(1004)) {
                                this.f816i.sendEmptyMessage(1005);
                            }
                        } else {
                            this.f816i.removeMessages(1005);
                            this.f816i.removeMessages(1004);
                            this.f816i.sendEmptyMessageDelayed(1004, (long) i4);
                        }
                    }
                    C0490b.m1687b();
                } else if (f811z[17].equals(str)) {
                    C0490b.m1721k(this);
                    str = bundle.getString(f811z[60]);
                    if (!an.m1657a(str)) {
                        if (str.equals(C0462a.f830a.name())) {
                            ac.m1581b();
                            if (C0472k.f865a.get() == 0) {
                                m1435a();
                            } else {
                                this.f816i.sendEmptyMessage(1006);
                            }
                            C0490b.m1687b();
                        } else {
                            str.equals(C0462a.f831b.name());
                        }
                    }
                    ac.m1581b();
                    C0490b.m1687b();
                } else if (f811z[57].equals(str)) {
                    C0404a.m1076b(getApplicationContext(), 0);
                    if (C0472k.f865a.get() == 0) {
                        m1443b();
                    } else {
                        ac.m1581b();
                    }
                } else if (f811z[25].equals(str)) {
                    C0404a.m1076b(getApplicationContext(), 1);
                    m1441a(bundle.getString(f811z[18]), C0448e.f754f);
                } else if (f811z[47].equals(str)) {
                    bundle.getString(f811z[48]);
                } else if (f811z[32].equals(str)) {
                    if (bundle.getInt(f811z[43], -1) != -1) {
                        ac.m1581b();
                    }
                } else if (f811z[29].equals(str)) {
                    if (C0472k.f865a.get() == 0) {
                        m1443b();
                    }
                    str = bundle.getString(f811z[41]);
                    String string = bundle.getString(f811z[31]);
                    long j = bundle.getLong(f811z[50], 0);
                    if (!(str == null && string == null)) {
                        JSONObject jSONObject = new JSONObject();
                        if (str != null) {
                            try {
                                jSONObject.put(f811z[41], str);
                            } catch (JSONException e) {
                                new StringBuilder(f811z[42]).append(e.getMessage());
                                ac.m1586d();
                            }
                        }
                        if (string != null) {
                            jSONObject.put(f811z[31], string);
                        }
                        this.f813f.m1529a(new C0525m(j, C0448e.f754f, jSONObject.toString()), 20000);
                    }
                } else if (f811z[21].equals(str)) {
                    C0404a.m1076b(getApplicationContext(), 0);
                    if (C0472k.f865a.get() == 0) {
                        m1443b();
                    } else {
                        ac.m1581b();
                    }
                } else if (!(f811z[27].equals(str) || f811z[51].equals(str))) {
                    if (f811z[40].equals(str)) {
                        i4 = bundle.getInt(f811z[52]);
                        new StringBuilder(f811z[37]).append(i4);
                        ac.m1576a();
                        switch (i4) {
                            case 1:
                                C0404a.m1066a(getApplicationContext(), bundle.getString(f811z[36]), bundle.getString(f811z[55]));
                                break;
                            case 2:
                                i4 = bundle.getInt(f811z[45]);
                                i3 = C0452b.m1370b();
                                if (i4 < i3) {
                                    i3 -= i4;
                                    new StringBuilder(f811z[16]).append(i3);
                                    ac.m1576a();
                                    C0417m.m1213a(getApplicationContext(), i3);
                                }
                                C0404a.m1061a(getApplicationContext(), i4);
                                break;
                            case 3:
                                C0404a.m1077b(getApplicationContext(), bundle.getString(f811z[22]));
                                break;
                            case 4:
                                C0404a.m1065a(getApplicationContext(), bundle.getString(f811z[53]));
                                break;
                            case 5:
                                C0404a.m1067a(getApplicationContext(), bundle.getBoolean(f811z[39]));
                                break;
                            case 6:
                                C0469h.m1498a(getApplicationContext()).m1507a(getApplicationContext(), (JPushLocalNotification) bundle.getSerializable(f811z[20]));
                                break;
                            case 7:
                                C0469h.m1498a(getApplicationContext()).m1506a(getApplicationContext(), bundle.getLong(f811z[26]));
                                break;
                            case 8:
                                C0469h.m1498a(getApplicationContext()).m1508b(getApplicationContext());
                                break;
                            case 9:
                                i4 = bundle.getInt(f811z[59]);
                                if (!C0452b.m1371b(i4)) {
                                    C0452b.m1369a(i4);
                                }
                                if (C0452b.m1370b() > C0404a.m1073b(getApplicationContext())) {
                                    i4 = C0452b.m1368a();
                                    if (i4 != 0) {
                                        C0417m.m1220b(getApplicationContext(), i4);
                                        break;
                                    }
                                }
                                break;
                            case 10:
                                C0417m.m1212a(getApplicationContext());
                                break;
                            case 11:
                                C0459i.m1412a(getApplicationContext(), bundle.getString(f811z[24]));
                                break;
                            default:
                                break;
                        }
                    }
                    new StringBuilder(f811z[34]).append(str);
                    ac.m1586d();
                }
            }
        } else {
            ac.m1582b(f811z[0], f811z[58]);
            this.f816i.sendEmptyMessageDelayed(1003, 100);
        }
        return 1;
    }

    public boolean onUnbind(Intent intent) {
        ac.m1588e();
        return super.onUnbind(intent);
    }
}
