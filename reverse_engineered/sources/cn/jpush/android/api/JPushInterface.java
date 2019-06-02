package cn.jpush.android.api;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build.VERSION;
import android.os.Bundle;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import cn.jpush.android.C0404a;
import cn.jpush.android.C0448e;
import cn.jpush.android.data.JPushLocalNotification;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.service.C0462a;
import cn.jpush.android.service.C0469h;
import cn.jpush.android.service.PushService;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.C0403i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ae;
import cn.jpush.android.util.an;
import com.google.android.gms.location.places.Place;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;

public class JPushInterface {
    public static final String ACTION_ACTIVITY_OPENDED;
    public static final String ACTION_CONNECTION_CHANGE;
    public static final String ACTION_MESSAGE_RECEIVED;
    public static final String ACTION_NOTIFICATION_OPENED;
    public static final String ACTION_NOTIFICATION_RECEIVED;
    public static final String ACTION_NOTIFICATION_RECEIVED_PROXY;
    public static final String ACTION_REGISTRATION_ID;
    public static final String ACTION_RESTOREPUSH;
    public static final String ACTION_RICHPUSH_CALLBACK;
    public static final String ACTION_STATUS;
    public static final String ACTION_STOPPUSH;
    public static final String EXTRA_ACTIVITY_PARAM;
    public static final String EXTRA_ALERT;
    public static final String EXTRA_APP_KEY;
    public static final String EXTRA_CONNECTION_CHANGE;
    public static final String EXTRA_CONTENT_TYPE;
    public static final String EXTRA_EXTRA;
    public static final String EXTRA_MESSAGE;
    public static final String EXTRA_MSG_ID;
    public static final String EXTRA_NOTIFICATION_DEVELOPER_ARG0;
    public static final String EXTRA_NOTIFICATION_ID;
    public static final String EXTRA_NOTIFICATION_TITLE;
    public static final String EXTRA_NOTI_TYPE;
    public static final String EXTRA_PUSH_ID;
    public static final String EXTRA_REGISTRATION_ID;
    public static final String EXTRA_RICHPUSH_FILE_PATH;
    public static final String EXTRA_RICHPUSH_FILE_TYPE;
    public static final String EXTRA_RICHPUSH_HTML_PATH;
    public static final String EXTRA_RICHPUSH_HTML_RES;
    public static final String EXTRA_STATUS;
    public static final String EXTRA_TITLE;
    /* renamed from: a */
    private static final Integer f500a = Integer.valueOf(0);
    /* renamed from: b */
    private static C0409e f501b = C0409e.m1183b();
    /* renamed from: c */
    private static ConcurrentLinkedQueue<Long> f502c = new ConcurrentLinkedQueue();
    /* renamed from: z */
    private static final String[] f503z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 66;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u00191G?ov";
        r0 = 65;
        r4 = r3;
    L_0x000a:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x0119;
    L_0x0013:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0018:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x03fc;
            case 1: goto L_0x0400;
            case 2: goto L_0x0404;
            case 3: goto L_0x0408;
            default: goto L_0x001f;
        };
    L_0x001f:
        r9 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
    L_0x0021:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x0117;
    L_0x0029:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0018;
    L_0x002d:
        EXTRA_EXTRA = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u000018iv J8";
        r0 = 66;
        goto L_0x000a;
    L_0x0034:
        ACTION_STATUS = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u00001%rc=Y\"~v V$sh;O.sr0";
        r0 = 67;
        goto L_0x000a;
    L_0x003b:
        ACTION_NOTIFICATION_OPENED = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019:P?tq=\\*i~;Q4~x:K.sc+K\"i{1";
        r0 = 68;
        goto L_0x000a;
    L_0x0042:
        EXTRA_NOTIFICATION_TITLE = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019&Z,td M*i~;Q4ts";
        r0 = 69;
        goto L_0x000a;
    L_0x0049:
        EXTRA_REGISTRATION_ID = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019<K&qh&Z8";
        r0 = 70;
        goto L_0x000a;
    L_0x0050:
        EXTRA_RICHPUSH_HTML_RES = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u000019xd P9xg!L#";
        r0 = 71;
        goto L_0x000a;
    L_0x0057:
        ACTION_RESTOREPUSH = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u00001*~c=P%bv7K\"k~ F4rg1Q/xs";
        r0 = 72;
        goto L_0x000a;
    L_0x005e:
        ACTION_ACTIVITY_OPENDED = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u00192V'xh F;x";
        r0 = 73;
        goto L_0x000a;
    L_0x0065:
        EXTRA_RICHPUSH_FILE_TYPE = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u00001&xd'^,xh&Z(x~\"Z/";
        r0 = 74;
        goto L_0x000a;
    L_0x006c:
        ACTION_MESSAGE_RECEIVED = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019:P?tq=\\*i~;Q4ts";
        r0 = 75;
        goto L_0x000a;
    L_0x0073:
        EXTRA_NOTIFICATION_ID = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u00199L,b~0";
        r0 = 76;
        goto L_0x000a;
    L_0x007a:
        EXTRA_MSG_ID = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u00195\\?ta=K2bg5M*p";
        r0 = 77;
        goto L_0x000a;
    L_0x0081:
        EXTRA_ACTIVITY_PARAM = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u00001(ry:Z(i~;Q";
        r0 = 78;
        goto L_0x000a;
    L_0x0088:
        ACTION_CONNECTION_CHANGE = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019:P?tq=\\*i~;Q4yr\"Z'rg1M4|e3/";
        r0 = 79;
        goto L_0x000a;
    L_0x0090:
        EXTRA_NOTIFICATION_DEVELOPER_ARG0 = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u00195O;vr-";
        r0 = 80;
        goto L_0x000a;
    L_0x0098:
        EXTRA_APP_KEY = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019 V?qr";
        r0 = 81;
        goto L_0x000a;
    L_0x00a0:
        EXTRA_TITLE = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019'K*ib'";
        r0 = 82;
        goto L_0x000a;
    L_0x00a8:
        EXTRA_STATUS = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u00197P%ir:K4in$Z";
        r0 = 83;
        goto L_0x000a;
    L_0x00b0:
        EXTRA_CONTENT_TYPE = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u000019xp=L?ov V$s";
        r0 = 84;
        goto L_0x000a;
    L_0x00b8:
        ACTION_REGISTRATION_ID = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019<K&qh$^?u";
        r0 = 85;
        goto L_0x000a;
    L_0x00c0:
        EXTRA_RICHPUSH_HTML_PATH = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u00192V'xh$^?u";
        r0 = 86;
        goto L_0x000a;
    L_0x00c8:
        EXTRA_RICHPUSH_FILE_PATH = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u00195S.oc";
        r0 = 87;
        goto L_0x000a;
    L_0x00d0:
        EXTRA_ALERT = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019$J8uh=[";
        r0 = 88;
        goto L_0x000a;
    L_0x00d8:
        EXTRA_PUSH_ID = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u00001%rc=Y\"~v V$sh&Z(x~\"Z/bg&P3d";
        r0 = 89;
        goto L_0x000a;
    L_0x00e0:
        ACTION_NOTIFICATION_RECEIVED_PROXY = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u00199Z8nv3Z";
        r0 = 90;
        goto L_0x000a;
    L_0x00e8:
        EXTRA_MESSAGE = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u000018ix$O>n";
        r0 = 91;
        goto L_0x000a;
    L_0x00f0:
        ACTION_STOPPUSH = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u00001%rc=Y\"~v V$sh&Z(x~\"Z/";
        r0 = 92;
        goto L_0x000a;
    L_0x00f8:
        ACTION_NOTIFICATION_RECEIVED = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u00001*~c=P%be=\\#mb'W4~v8S)|t?";
        r0 = 93;
        goto L_0x000a;
    L_0x0100:
        ACTION_RICHPUSH_CALLBACK = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u00197P%sr7K\"ry+\\#|y3Z";
        r0 = 94;
        goto L_0x000a;
    L_0x0108:
        EXTRA_CONNECTION_CHANGE = r1;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019:P?tq=\\*i~;Q4in$Z";
        r0 = 95;
        goto L_0x000a;
    L_0x0110:
        EXTRA_NOTI_TYPE = r1;
        r1 = "tY\u0002~\u0007TST{\nD\u0017\u0012p\u0019PV\u0000?F\u001d";
        r0 = -1;
        goto L_0x000a;
    L_0x0117:
        r5 = r1;
        r1 = r7;
    L_0x0119:
        if (r5 > r6) goto L_0x0013;
    L_0x011b:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0130;
            case 1: goto L_0x0139;
            case 2: goto L_0x0142;
            case 3: goto L_0x014b;
            case 4: goto L_0x0154;
            case 5: goto L_0x015d;
            case 6: goto L_0x0166;
            case 7: goto L_0x0170;
            case 8: goto L_0x017b;
            case 9: goto L_0x0186;
            case 10: goto L_0x0191;
            case 11: goto L_0x019c;
            case 12: goto L_0x01a7;
            case 13: goto L_0x01b2;
            case 14: goto L_0x01bd;
            case 15: goto L_0x01c8;
            case 16: goto L_0x01d3;
            case 17: goto L_0x01de;
            case 18: goto L_0x01e9;
            case 19: goto L_0x01f4;
            case 20: goto L_0x01ff;
            case 21: goto L_0x020a;
            case 22: goto L_0x0215;
            case 23: goto L_0x0220;
            case 24: goto L_0x022b;
            case 25: goto L_0x0236;
            case 26: goto L_0x0241;
            case 27: goto L_0x024c;
            case 28: goto L_0x0257;
            case 29: goto L_0x0262;
            case 30: goto L_0x026d;
            case 31: goto L_0x0278;
            case 32: goto L_0x0283;
            case 33: goto L_0x028e;
            case 34: goto L_0x0299;
            case 35: goto L_0x02a4;
            case 36: goto L_0x02af;
            case 37: goto L_0x02ba;
            case 38: goto L_0x02c5;
            case 39: goto L_0x02d0;
            case 40: goto L_0x02db;
            case 41: goto L_0x02e6;
            case 42: goto L_0x02f1;
            case 43: goto L_0x02fc;
            case 44: goto L_0x0307;
            case 45: goto L_0x0312;
            case 46: goto L_0x031d;
            case 47: goto L_0x0328;
            case 48: goto L_0x0333;
            case 49: goto L_0x033e;
            case 50: goto L_0x0349;
            case 51: goto L_0x0354;
            case 52: goto L_0x035f;
            case 53: goto L_0x036a;
            case 54: goto L_0x0375;
            case 55: goto L_0x0380;
            case 56: goto L_0x038b;
            case 57: goto L_0x0396;
            case 58: goto L_0x03a1;
            case 59: goto L_0x03ac;
            case 60: goto L_0x03b7;
            case 61: goto L_0x03c2;
            case 62: goto L_0x03cd;
            case 63: goto L_0x03d8;
            case 64: goto L_0x03e3;
            case 65: goto L_0x002d;
            case 66: goto L_0x0034;
            case 67: goto L_0x003b;
            case 68: goto L_0x0042;
            case 69: goto L_0x0049;
            case 70: goto L_0x0050;
            case 71: goto L_0x0057;
            case 72: goto L_0x005e;
            case 73: goto L_0x0065;
            case 74: goto L_0x006c;
            case 75: goto L_0x0073;
            case 76: goto L_0x007a;
            case 77: goto L_0x0081;
            case 78: goto L_0x0088;
            case 79: goto L_0x0090;
            case 80: goto L_0x0098;
            case 81: goto L_0x00a0;
            case 82: goto L_0x00a8;
            case 83: goto L_0x00b0;
            case 84: goto L_0x00b8;
            case 85: goto L_0x00c0;
            case 86: goto L_0x00c8;
            case 87: goto L_0x00d0;
            case 88: goto L_0x00d8;
            case 89: goto L_0x00e0;
            case 90: goto L_0x00e8;
            case 91: goto L_0x00f0;
            case 92: goto L_0x00f8;
            case 93: goto L_0x0100;
            case 94: goto L_0x0108;
            case 95: goto L_0x0110;
            default: goto L_0x0127;
        };
    L_0x0127:
        r3[r2] = r1;
        r2 = 1;
        r1 = "wg\u0001l\u0003tY\u0000z\u0019[V\u0017z";
        r0 = 0;
        r3 = r4;
        goto L_0x000a;
    L_0x0130:
        r3[r2] = r1;
        r2 = 2;
        r1 = "tY\u0002~\u0007TSTk\u0002PRTy\u0004OZ\u0015kK\u0010\u0017\u0007k\nOC<p\u001eO\u0017\u0007w\u0004H[\u0010?\u0007XD\u0007?\u001fUV\u001a?\u000eSS<p\u001eO";
        r0 = 1;
        r3 = r4;
        goto L_0x000a;
    L_0x0139:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\r\u0006F,_\b\u0001+/5\u000f\u0004";
        r0 = 2;
        r3 = r4;
        goto L_0x000a;
    L_0x0142:
        r3[r2] = r1;
        r2 = 4;
        r1 = "棽浼剄彌刦沜朾缥练ど歙功伨尙坃朴罦纨早膁劕绐纙扸蠧〿";
        r0 = 3;
        r3 = r4;
        goto L_0x000a;
    L_0x014b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\\T\u0000v\u0004S\r\u0006z\u0018HZ\u0011O\u001eN_";
        r0 = 4;
        r3 = r4;
        goto L_0x000a;
    L_0x0154:
        r3[r2] = r1;
        r2 = 6;
        r1 = "sb8SKSX\u0000v\rTT\u0015k\u0002RY";
        r0 = 5;
        r3 = r4;
        goto L_0x000a;
    L_0x015d:
        r3[r2] = r1;
        r2 = 7;
        r1 = "PB\u0018k\u0002bC\ro\u000e";
        r0 = 6;
        r3 = r4;
        goto L_0x000a;
    L_0x0166:
        r3[r2] = r1;
        r2 = 8;
        r1 = "QX\u0017~\u0007bY\u001bk\u0002[^\u0017~\u001fTX\u001a@\u0002Y";
        r0 = 7;
        r3 = r4;
        goto L_0x000a;
    L_0x0170:
        r3[r2] = r1;
        r2 = 9;
        r1 = "^YZu\u001bHD\u001c1\nSS\u0006p\u0002Y\u0019\u001dq\u001fXY\u00001&h{ V4me;\\.nd";
        r0 = 8;
        r3 = r4;
        goto L_0x000a;
    L_0x017b:
        r3[r2] = r1;
        r2 = 10;
        r1 = "nN\u0007k\u000eP\u0019\u0018p\nY{\u001d}\u0019\\E\r%QWG\u0001l\u0003\u000f\u0006C";
        r0 = 9;
        r3 = r4;
        goto L_0x000a;
    L_0x0186:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\\T\u0000v\u0004S\r\u001dq\u0002I\u0017Y?\u0018Y\\\"z\u0019N^\u001bqQ";
        r0 = 10;
        r3 = r4;
        goto L_0x000a;
    L_0x0191:
        r3[r2] = r1;
        r2 = 12;
        r1 = "WG\u0001l\u0003\u000f\u0006C";
        r0 = 11;
        r3 = r4;
        goto L_0x000a;
    L_0x019c:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u0011\u0017\u0016j\u0002QS={Q\u000e\u0007M";
        r0 = 12;
        r3 = r4;
        goto L_0x000a;
    L_0x01a7:
        r3[r2] = r1;
        r2 = 14;
        r1 = "tY\u0002~\u0007TSTk\nZ\rT";
        r0 = 13;
        r3 = r4;
        goto L_0x000a;
    L_0x01b2:
        r3[r2] = r1;
        r2 = 15;
        r1 = "sb8SK^X\u001ak\u000eEC";
        r0 = 14;
        r3 = r4;
        goto L_0x000a;
    L_0x01bd:
        r3[r2] = r1;
        r2 = 16;
        r1 = "QX\u0017~\u0007bY\u001bk\u0002[^\u0017~\u001fTX\u001a";
        r0 = 15;
        r3 = r4;
        goto L_0x000a;
    L_0x01c8:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\\T\u0000v\u0004S\r\u0013z\u001fmB\u0007w%RC\u001dy\u0002^V\u0000v\u0004Su\u0001v\u0007YR\u0006?Q\u001d";
        r0 = 16;
        r3 = r4;
        goto L_0x000a;
    L_0x01d3:
        r3[r2] = r1;
        r2 = 18;
        r1 = "NR\u0007l\u000eTX\u001a?\u001fTZ\u0011p\u001eI\u0017\u0018z\u0018N\u0017\u0000w\nS\u0017E/\u0018";
        r0 = 17;
        r3 = r4;
        goto L_0x000a;
    L_0x01de:
        r3[r2] = r1;
        r2 = 19;
        r1 = "NR\u0007l\u000eTX\u001a?\u001fTZ\u0011p\u001eI\u0017\u0018~\u0019ZR\u0006?\u001fUV\u001a?ZYV\r";
        r0 = 18;
        r3 = r4;
        goto L_0x000a;
    L_0x01e9:
        r3[r2] = r1;
        r2 = 20;
        r1 = "TSTl\u0003RB\u0018{K_RTs\nOP\u0011mKI_\u0015qK\r";
        r0 = 19;
        r3 = r4;
        goto L_0x000a;
    L_0x01f4:
        r3[r2] = r1;
        r2 = 21;
        r1 = "sb8SKMB\u0007w%RC\u001dy\u0002^V\u0000v\u0004Su\u0001v\u0007YR\u0006";
        r0 = 20;
        r3 = r4;
        goto L_0x000a;
    L_0x01ff:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\\T\u0000v\u0004S\r\u0007z\u001fmB\u0007w%RC\u001dy\u0002^V\u0000v\u0004Su\u0001v\u0007YR\u0006?F\u001d^\u0010%";
        r0 = 21;
        r3 = r4;
        goto L_0x000a;
    L_0x020a:
        r3[r2] = r1;
        r2 = 23;
        r1 = "oR\u0019p\u001dX\u0017\u0000w\u000e\u001dD\u001ds\u000eST\u0011?\u001fTZ\u0011>";
        r0 = 22;
        r3 = r4;
        goto L_0x000a;
    L_0x0215:
        r3[r2] = r1;
        r2 = 24;
        r1 = "nR\u0000?8T[\u0011q\bX\u0017$j\u0018Uc\u001dr\u000e\u001d\u001aT";
        r0 = 23;
        r3 = r4;
        goto L_0x000a;
    L_0x0220:
        r3[r2] = r1;
        r2 = 25;
        r1 = "tY\u0002~\u0007TSTo\nOV\u0019z\u001fXETy\u0004OZ\u0015kG\u001dD\u0000~\u0019I\u001bj\u0019\u001dV\u001a{KXY\u0010W\u0004HETl\u0003RB\u0018{K_R\u0000h\u000eXYT/KC\u0017F,G\u001dD\u0000~\u0019Iz\u001dq\u0018\u001dV\u001a{KXY\u0010R\u0002SDTl\u0003RB\u0018{K_R\u0000h\u000eXYT/KC\u0017A&E\u001d";
        r0 = 24;
        r3 = r4;
        goto L_0x000a;
    L_0x022b:
        r3[r2] = r1;
        r2 = 26;
        r1 = "\u001d\u001aY?";
        r0 = 25;
        r3 = r4;
        goto L_0x000a;
    L_0x0236:
        r3[r2] = r1;
        r2 = 27;
        r1 = "\u001d\rT";
        r0 = 26;
        r3 = r4;
        goto L_0x000a;
    L_0x0241:
        r3[r2] = r1;
        r2 = 28;
        r1 = "nR\u0000?8T[\u0011q\bX\u0017$j\u0018Uc\u001dr\u000e\u001dq\u0015v\u0007XS";
        r0 = 27;
        r3 = r4;
        goto L_0x000a;
    L_0x024c:
        r3[r2] = r1;
        r2 = 29;
        r1 = "tY\u0002~\u0007TSTq\u0004I^\u0012v\b\\C\u001dp\u0005tSX?,TA\u0011?\u001eM\u0017\u0015|\u001fTX\u001a1E";
        r0 = 28;
        r3 = r4;
        goto L_0x000a;
    L_0x0257:
        r3[r2] = r1;
        r2 = 30;
        r1 = "SX\u0000v\rTT\u0015k\u0002RY";
        r0 = 29;
        r3 = r4;
        goto L_0x000a;
    L_0x0262:
        r3[r2] = r1;
        r2 = 31;
        r1 = "PV\fQ\u001eP\u0017\u0007w\u0004H[\u0010?U\u001d\u0007X?,TA\u0011?\u001eM\u0017\u0015|\u001fTX\u001a1E";
        r0 = 30;
        r3 = r4;
        goto L_0x000a;
    L_0x026d:
        r3[r2] = r1;
        r2 = 32;
        r1 = "\\T\u0000v\u0004S\r\u0007z\u001fqV\u0000z\u0018Iy\u001bk\u0002[^\u0017~\u001fTX\u001aQ\u001ePU\u0011mK\u0007\u0017";
        r0 = 31;
        r3 = r4;
        goto L_0x000a;
    L_0x0278:
        r3[r2] = r1;
        r2 = 33;
        r1 = "\\T\u0000v\u0004S\r\u0007z\u001f|[\u001d~\u0018|Y\u0010K\nZDT2K\\[\u001d~\u0018\u0007";
        r0 = 32;
        r3 = r4;
        goto L_0x000a;
    L_0x0283:
        r3[r2] = r1;
        r2 = 34;
        r1 = "tY\u0002~\u0007TSTk\nZDX?\u001cT[\u0018?\u0005RCTl\u000eI\u0017\u0000~\fN\u0017\u0000w\u0002N\u0017\u0000v\u0006X\u0019";
        r0 = 33;
        r3 = r4;
        goto L_0x000a;
    L_0x028e:
        r3[r2] = r1;
        r2 = 35;
        r1 = "\u0011\u0017\u0003v\u0007Q\u0017\u001ap\u001f\u001dD\u0011kK\\[\u001d~\u0018\u001dC\u001cv\u0018\u001dC\u001dr\u000e\u0013";
        r0 = 34;
        r3 = r4;
        goto L_0x000a;
    L_0x0299:
        r3[r2] = r1;
        r2 = 36;
        r1 = "IV\u0013lKQR\u001ax\u001fU\r";
        r0 = 35;
        r3 = r4;
        goto L_0x000a;
    L_0x02a4:
        r3[r2] = r1;
        r2 = 37;
        r1 = "tY\u0002~\u0007TST~\u0007TV\u0007%K";
        r0 = 36;
        r3 = r4;
        goto L_0x000a;
    L_0x02af:
        r3[r2] = r1;
        r2 = 38;
        r1 = "\u0011\u0017\u0000~\fN\r";
        r0 = 37;
        r3 = r4;
        goto L_0x000a;
    L_0x02ba:
        r3[r2] = r1;
        r2 = 39;
        r1 = "sb8SK\\[\u001d~\u0018\u001dV\u001a{KIV\u0013lE\u001dp\u001di\u000e\u001dB\u0004?\n^C\u001dp\u0005\u0013";
        r0 = 38;
        r3 = r4;
        goto L_0x000a;
    L_0x02c5:
        r3[r2] = r1;
        r2 = 40;
        r1 = "i_\u0011?\u0007XY\u0013k\u0003\u001dX\u0012?\u001f\\P\u0007?\u0018UX\u0001s\u000f\u001dU\u0011?\u0007XD\u0007?\u001fUV\u001a?\\\r\u0007D?\tDC\u0011lE";
        r0 = 39;
        r3 = r4;
        goto L_0x000a;
    L_0x02d0:
        r3[r2] = r1;
        r2 = 41;
        r1 = "tY\u0002~\u0007TSTk\nZ\u0017N?";
        r0 = 40;
        r3 = r4;
        goto L_0x000a;
    L_0x02db:
        r3[r2] = r1;
        r2 = 42;
        r1 = "i_\u0011?\u0007XY\u0013w\u001f\u001dX\u0012?\u001f\\P\u0007?\u0006\\N\u0016zKPX\u0006zKI_\u0015qK\f\u0007D/E";
        r0 = 41;
        r3 = r4;
        goto L_0x000a;
    L_0x02e6:
        r3[r2] = r1;
        r2 = 43;
        r1 = "\\T\u0000v\u0004S\r\u0007k\u0004Mg\u0001l\u0003";
        r0 = 42;
        r3 = r4;
        goto L_0x000a;
    L_0x02f1:
        r3[r2] = r1;
        r2 = 44;
        r1 = "i_\u0011?\u0006NP={KTDTq\u0004I\u0017\u0002~\u0007TST2K";
        r0 = 43;
        r3 = r4;
        goto L_0x000a;
    L_0x02fc:
        r3[r2] = r1;
        r2 = 45;
        r1 = "i_\u0011?\tH^\u0018{\u000eO\u0017\u0003v\u001fU\u0017\u001d{Q";
        r0 = 44;
        r3 = r4;
        goto L_0x000a;
    L_0x0307:
        r3[r2] = r1;
        r2 = 46;
        r1 = "\u001d_\u0015lKSX\u0000?\tXR\u001a?\u0018XCTv\u0005\u001dN\u001bj\u0019\u001dV\u0004oG\u001dB\u0007zKYR\u0012~\u001eQCT}\u001eT[\u0010z\u0019\u001c";
        r0 = 45;
        r3 = r4;
        goto L_0x000a;
    L_0x0312:
        r3[r2] = r1;
        r2 = 47;
        r1 = "\\Y\u0010m\u0004TSZp\u0018\u0013u\u0001v\u0007Y\u0019\"Z9n~;QEns?@\"scH-X";
        r0 = 46;
        r3 = r4;
        goto L_0x000a;
    L_0x031d:
        r3[r2] = r1;
        r2 = 48;
        r1 = "OR\u0005j\u000eNC$z\u0019P^\u0007l\u0002RY\u0007";
        r0 = 47;
        r3 = r4;
        goto L_0x000a;
    L_0x0328:
        r3[r2] = r1;
        r2 = 49;
        r1 = "\\Y\u0010m\u0004TSZo\u000eOZ\u001dl\u0018TX\u001a19xv0@;ux:Z4nc5K.";
        r0 = 48;
        r3 = r4;
        goto L_0x000a;
    L_0x0333:
        r3[r2] = r1;
        r2 = 50;
        r1 = "\\Y\u0010m\u0004TSZo\u000eOZ\u001dl\u0018TX\u001a1*~t1L8bq=Q.b{;\\*i~;Q";
        r0 = 49;
        r3 = r4;
        goto L_0x000a;
    L_0x033e:
        r3[r2] = r1;
        r2 = 51;
        r1 = "\\Y\u0010m\u0004TSZo\u000eOZ\u001dl\u0018TX\u001a1<o~ Z4xo Z9sv8@8ix&^,x";
        r0 = 50;
        r3 = r4;
        goto L_0x000a;
    L_0x0349:
        r3[r2] = r1;
        r2 = 52;
        r1 = "\\Y\u0010m\u0004TSZ~\u001bM\u00195|\u001fTA\u001dk\u0012";
        r0 = 51;
        r3 = r4;
        goto L_0x000a;
    L_0x0354:
        r3[r2] = r1;
        r2 = 53;
        r1 = "\\T\u0000v\u0004S\r\u0007z\u001fmB\u0007w?TZ\u0011?F\u001dT\u0018p\u0018XS";
        r0 = 52;
        r3 = r4;
        goto L_0x000a;
    L_0x035f:
        r3[r2] = r1;
        r2 = 54;
        r1 = "|[\u0006z\nYNTL\u000eIg\u0001l\u0003i^\u0019zG\u001dP\u001di\u000e\u001dB\u0004?F\u001d";
        r0 = 53;
        r3 = r4;
        goto L_0x000a;
    L_0x036a:
        r3[r2] = r1;
        r2 = 55;
        r1 = "\u0015lD2]`LD3\\@\u001e+7C";
        r0 = 54;
        r3 = r4;
        goto L_0x000a;
    L_0x0375:
        r3[r2] = r1;
        r2 = 56;
        r1 = "\u0014\u001e";
        r0 = 55;
        r3 = r4;
        goto L_0x000a;
    L_0x0380:
        r3[r2] = r1;
        r2 = 57;
        r1 = "\u0011\u0017\u0004j\u0018Uc\u001dr\u000e\u0007";
        r0 = 56;
        r3 = r4;
        goto L_0x000a;
    L_0x038b:
        r3[r2] = r1;
        r2 = 58;
        r1 = "tY\u0002~\u0007TSTk\u0002PRTy\u0004OZ\u0015kK\u0010\u0017";
        r0 = 57;
        r3 = r4;
        goto L_0x000a;
    L_0x0396:
        r3[r2] = r1;
        r2 = 59;
        r1 = "\u0015lD2R`KED[\u0010\u000e)cYf\u0007Y,6\u0014k*70\r\u001aMB\u0017\flD2R`KFD[\u0010\u0004)6";
        r0 = 58;
        r3 = r4;
        goto L_0x000a;
    L_0x03a1:
        r3[r2] = r1;
        r2 = 60;
        r1 = "XY\u0015}\u0007Xh\u0004j\u0018Uh\u0000v\u0006X";
        r0 = 59;
        r3 = r4;
        goto L_0x000a;
    L_0x03ac:
        r3[r2] = r1;
        r2 = 61;
        r1 = "\u0014K\\";
        r0 = 60;
        r3 = r4;
        goto L_0x000a;
    L_0x03b7:
        r3[r2] = r1;
        r2 = 62;
        r1 = "\u0010\u001e_7";
        r0 = 61;
        r3 = r4;
        goto L_0x000a;
    L_0x03c2:
        r3[r2] = r1;
        r2 = 63;
        r1 = "\\T\u0000v\u0004S\r\u0007z\u001fmB\u0007w?TZ\u0011?F\u001dR\u001a~\tQR\u0010%";
        r0 = 62;
        r3 = r4;
        goto L_0x000a;
    L_0x03cd:
        r3[r2] = r1;
        r2 = 64;
        r1 = "ZR\u0000M\u000e^X\u0006{;HD\u001cQ\u0004I^\u0012v\b\\C\u001dp\u0005B\u001ds\u000fXET2K";
        r0 = 63;
        r3 = r4;
        goto L_0x000a;
    L_0x03d8:
        r3[r2] = r1;
        r2 = 65;
        r1 = "~B\u0007k\u0004P^\u000ez\u000f\u001dU\u0001v\u0007YR\u0006?\rOX\u0019?\u0018\\A\u0011{KME\u0011y\u000eOR\u001a|\u000e\u001d\u001aT";
        r0 = 64;
        r3 = r4;
        goto L_0x000a;
    L_0x03e3:
        r3[r2] = r1;
        f503z = r4;
        r0 = 0;
        r0 = java.lang.Integer.valueOf(r0);
        f500a = r0;
        r0 = cn.jpush.android.api.C0409e.m1183b();
        f501b = r0;
        r0 = new java.util.concurrent.ConcurrentLinkedQueue;
        r0.<init>();
        f502c = r0;
        return;
    L_0x03fc:
        r9 = 61;
        goto L_0x0021;
    L_0x0400:
        r9 = 55;
        goto L_0x0021;
    L_0x0404:
        r9 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x0021;
    L_0x0408:
        r9 = 31;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.api.JPushInterface.<clinit>():void");
    }

    /* renamed from: a */
    private static PushNotificationBuilder m1163a(String str) {
        new StringBuilder(f503z[64]).append(str);
        ac.m1576a();
        String e = C0404a.m1097e(C0448e.f753e, str);
        if (an.m1657a(e)) {
            return null;
        }
        new StringBuilder(f503z[65]).append(e);
        ac.m1576a();
        return BasicPushNotificationBuilder.m1154a(e);
    }

    /* renamed from: a */
    private static void m1164a(Context context) {
        if (context == null) {
            throw new IllegalArgumentException(f503z[15]);
        }
        C0403i.m1045q(context);
    }

    /* renamed from: a */
    private static void m1165a(Context context, String str, Set<String> set, TagAliasCallback tagAliasCallback, boolean z) {
        int i = 0;
        if (context == null) {
            throw new IllegalArgumentException(f503z[15]);
        }
        int b;
        Set filterValidTags;
        if (C0448e.f749a && !C0490b.m1691b(context)) {
            ac.m1582b(f503z[1], f503z[4]);
        }
        if (str != null) {
            b = ae.m1596b(str);
            if (b != 0) {
                ac.m1582b(f503z[1], new StringBuilder(f503z[37]).append(str).append(f503z[35]).toString());
                if (tagAliasCallback != null) {
                    tagAliasCallback.gotResult(b, str, set);
                    return;
                }
                return;
            }
        }
        if (tagAliasCallback == null && !z) {
            filterValidTags = filterValidTags(set);
        }
        if (filterValidTags != null) {
            b = ae.m1594a(filterValidTags);
            if (b != 0) {
                ac.m1582b(f503z[1], f503z[34]);
                if (tagAliasCallback != null) {
                    tagAliasCallback.gotResult(b, str, filterValidTags);
                    return;
                }
                return;
            }
        }
        String stringTags = getStringTags(filterValidTags);
        if (str == null && stringTags == null) {
            ac.m1589e(f503z[1], f503z[39]);
            if (tagAliasCallback != null) {
                tagAliasCallback.gotResult(C0408d.f515a, str, filterValidTags);
                return;
            }
            return;
        }
        if (stringTags != null) {
            String replaceAll = stringTags.replaceAll(",", "");
            b = !an.m1657a(replaceAll) ? replaceAll.getBytes().length + 0 : 0;
            new StringBuilder(f503z[36]).append(b);
            ac.m1576a();
            if (b <= 7000) {
                i = 1;
            }
            if (i == 0) {
                if (tagAliasCallback != null) {
                    tagAliasCallback.gotResult(C0408d.f522h, str, filterValidTags);
                }
                ac.m1589e(f503z[1], f503z[40]);
                return;
            }
        }
        ac.m1582b(f503z[1], new StringBuilder(f503z[33]).append(str).append(f503z[38]).append(stringTags).toString());
        ServiceInterface.m1464a(context, str, stringTags, new C0406b(str, filterValidTags, tagAliasCallback));
    }

    /* renamed from: a */
    private static void m1166a(Context context, boolean z, String str) {
        ac.m1581b();
        C0404a.m1078b(context, z);
        if (z) {
            String str2 = f503z[59];
            if (Pattern.compile(new StringBuilder(f503z[55]).append(str2).append(f503z[61]).append(str2).append(f503z[62]).append(str2).append(f503z[56]).toString()).matcher(str).matches()) {
                str2 = C0404a.m1101f(context);
                if (str.equals(str2)) {
                    ac.m1582b(f503z[1], new StringBuilder(f503z[54]).append(str2).toString());
                    return;
                }
                ac.m1582b(f503z[1], new StringBuilder(f503z[63]).append(z).append(f503z[57]).append(str).toString());
                if (ServiceInterface.m1471b() || !C0448e.f762n) {
                    C0404a.m1077b(context, str);
                    return;
                }
                Intent intent = new Intent(context, PushService.class);
                intent.setAction(f503z[9]);
                Bundle bundle = new Bundle();
                bundle.putInt(f503z[7], 3);
                bundle.putString(f503z[60], str);
                intent.putExtras(bundle);
                context.startService(intent);
                return;
            }
            ac.m1589e(f503z[1], new StringBuilder(f503z[58]).append(str).toString());
            return;
        }
        ac.m1582b(f503z[1], f503z[53]);
    }

    /* renamed from: a */
    static boolean m1167a(int i) {
        if (i <= 0) {
            return false;
        }
        if (m1163a(i) != null) {
            return true;
        }
        ac.m1587d(f503z[1], new StringBuilder(f503z[45]).append(i).append(f503z[46]).toString());
        return false;
    }

    public static void addLocalNotification(Context context, JPushLocalNotification jPushLocalNotification) {
        m1164a(context);
        if (C0448e.f762n) {
            Intent intent = new Intent(context, PushService.class);
            Bundle bundle = new Bundle();
            bundle.putInt(f503z[7], 6);
            bundle.putSerializable(f503z[16], jPushLocalNotification);
            intent.putExtras(bundle);
            intent.setAction(f503z[9]);
            context.startService(intent);
            return;
        }
        C0469h.m1498a(context).m1507a(context, jPushLocalNotification);
    }

    /* renamed from: b */
    static PushNotificationBuilder m1168b(int i) {
        ac.m1578a(f503z[1], new StringBuilder(f503z[17]).append(i).toString());
        if (i <= 0) {
            i = f500a.intValue();
        }
        PushNotificationBuilder pushNotificationBuilder = null;
        try {
            pushNotificationBuilder = m1163a(i);
        } catch (Exception e) {
            ac.m1591g();
        }
        if (pushNotificationBuilder != null) {
            return pushNotificationBuilder;
        }
        ac.m1581b();
        return new DefaultPushNotificationBuilder();
    }

    public static void clearAllNotifications(Context context) {
        m1164a(context);
        ServiceInterface.m1472c(context);
    }

    public static void clearLocalNotifications(Context context) {
        m1164a(context);
        if (C0448e.f762n) {
            Intent intent = new Intent(context, PushService.class);
            Bundle bundle = new Bundle();
            bundle.putInt(f503z[7], 8);
            intent.putExtras(bundle);
            intent.setAction(f503z[9]);
            context.startService(intent);
            return;
        }
        C0469h.m1498a(context).m1508b(context);
    }

    public static void clearNotificationById(Context context, int i) {
        m1164a(context);
        if (i <= 0) {
            ac.m1589e(f503z[1], f503z[29]);
        } else {
            ((NotificationManager) context.getSystemService(f503z[30])).cancel(i);
        }
    }

    public static Set<String> filterValidTags(Set<String> set) {
        if (set == null) {
            return null;
        }
        if (set.isEmpty()) {
            return set;
        }
        Set<String> linkedHashSet = new LinkedHashSet();
        int i = 0;
        for (String str : set) {
            int i2;
            if (an.m1657a(str) || !ae.m1595a(str)) {
                ac.m1589e(f503z[1], new StringBuilder(f503z[41]).append(str).toString());
                i2 = i;
            } else {
                linkedHashSet.add(str);
                i2 = i + 1;
                if (i2 >= 1000) {
                    ac.m1587d(f503z[1], f503z[42]);
                    return linkedHashSet;
                }
            }
            i = i2;
        }
        return linkedHashSet;
    }

    public static boolean getConnectionState(Context context) {
        m1164a(context);
        return C0462a.f830a == C0404a.m1057a(context);
    }

    public static String getRegistrationID(Context context) {
        m1164a(context);
        return C0404a.m1047B();
    }

    public static String getStringTags(Set<String> set) {
        String str = null;
        if (set == null) {
            return null;
        }
        if (set.isEmpty()) {
            return "";
        }
        int i = 0;
        for (String str2 : set) {
            int i2;
            String str3;
            if (an.m1657a(str2) || !ae.m1595a(str2)) {
                ac.m1589e(f503z[1], new StringBuilder(f503z[14]).append(str2).toString());
                i2 = i;
                str3 = str;
            } else {
                str = str == null ? str2 : str + "," + str2;
                i2 = i + 1;
                if (i2 >= 1000) {
                    return str;
                }
                str3 = str;
            }
            str = str3;
            i = i2;
        }
        return str;
    }

    public static String getUdid(Context context) {
        m1164a(context);
        return C0490b.m1718j(context);
    }

    public static void init(Context context) {
        ac.m1582b(f503z[1], new StringBuilder(f503z[11]).append(ServiceInterface.m1458a()).append(f503z[13]).toString());
        try {
            System.loadLibrary(f503z[12]);
        } catch (Throwable th) {
            ac.m1589e(f503z[1], new StringBuilder(f503z[10]).append(th).toString());
            th.printStackTrace();
        }
        m1164a(context);
        if (C0448e.f749a && !C0490b.m1691b(context)) {
            ac.m1582b(f503z[1], f503z[4]);
        }
        if (C0448e.m1359a(context)) {
            if (C0404a.m1073b(context) == -1) {
                setLatestNotificationNumber(context, 5);
            }
            ServiceInterface.m1459a(context);
        }
    }

    public static void initCrashHandler(Context context) {
        m1164a(context);
        C0407c.m1169a().m1176a(context);
    }

    public static boolean isPushStopped(Context context) {
        m1164a(context);
        return ServiceInterface.m1475d(context);
    }

    public static void onFragmentPause(Context context, String str) {
        m1164a(context);
        f501b.m1193b(context, str);
    }

    public static void onFragmentResume(Context context, String str) {
        m1164a(context);
        f501b.m1189a(context, str);
    }

    public static void onKillProcess(Context context) {
        f501b.m1194c(context);
    }

    public static void onPause(Context context) {
        m1164a(context);
        f501b.m1192b(context);
    }

    public static void onResume(Context context) {
        m1164a(context);
        f501b.m1188a(context);
    }

    public static void removeLocalNotification(Context context, long j) {
        m1164a(context);
        if (C0448e.f762n) {
            Intent intent = new Intent(context, PushService.class);
            Bundle bundle = new Bundle();
            bundle.putInt(f503z[7], 7);
            bundle.putLong(f503z[8], j);
            intent.putExtras(bundle);
            intent.setAction(f503z[9]);
            context.startService(intent);
            return;
        }
        C0469h.m1498a(context).m1506a(context, j);
    }

    public static void reportNotificationOpened(Context context, String str) {
        m1164a(context);
        if (an.m1657a(str)) {
            ac.m1589e(f503z[1], new StringBuilder(f503z[44]).append(str).toString());
        }
        C0459i.m1415a(str, Place.TYPE_SUBPREMISE, context);
    }

    public static void requestPermission(Context context) {
        if (VERSION.SDK_INT < 23 || !(context instanceof Activity)) {
            ac.m1582b(f503z[1], f503z[47]);
            return;
        }
        String[] strArr = new String[]{f503z[51], f503z[50], f503z[49]};
        String str = f503z[51];
        String str2 = f503z[50];
        String str3 = f503z[49];
        boolean c = C0490b.m1697c(context, str);
        boolean c2 = C0490b.m1697c(context, str2);
        boolean c3 = C0490b.m1697c(context, str3);
        if (!c || !c2 || !c3) {
            try {
                Class.forName(f503z[52]).getDeclaredMethod(f503z[48], new Class[]{String[].class, Integer.TYPE}).invoke(context, new Object[]{strArr, Integer.valueOf(1)});
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void resumePush(Context context) {
        ac.m1582b(f503z[1], f503z[5]);
        m1164a(context);
        if (C0404a.m1131o()) {
            ac.m1581b();
            ServiceInterface.m1465a(context, false);
            return;
        }
        ServiceInterface.m1468b(context, 1);
    }

    public static void setAlias(Context context, String str, TagAliasCallback tagAliasCallback) {
        m1164a(context);
        setAliasAndTags(context, str, null, tagAliasCallback);
    }

    @Deprecated
    public static void setAliasAndTags(Context context, String str, Set<String> set) {
        m1164a(context);
        m1165a(context, str, set, null, false);
    }

    public static void setAliasAndTags(Context context, String str, Set<String> set, TagAliasCallback tagAliasCallback) {
        boolean z;
        m1164a(context);
        long currentTimeMillis = System.currentTimeMillis();
        if (f502c.size() < 10) {
            f502c.offer(Long.valueOf(currentTimeMillis));
            z = true;
        } else if (currentTimeMillis - ((Long) f502c.element()).longValue() > AbstractComponentTracker.LINGERING_TIMEOUT) {
            while (f502c.size() >= 10) {
                f502c.poll();
            }
            f502c.offer(Long.valueOf(currentTimeMillis));
            z = true;
        } else {
            z = false;
        }
        if (z) {
            m1165a(context, str, set, tagAliasCallback, true);
            return;
        }
        ac.m1586d();
        if (tagAliasCallback != null) {
            tagAliasCallback.gotResult(C0408d.f525k, str, set);
        }
    }

    public static void setDebugMode(boolean z) {
        C0448e.f749a = z;
    }

    public static void setDefaultPushNotificationBuilder(BasicPushNotificationBuilder basicPushNotificationBuilder) {
        if (basicPushNotificationBuilder == null) {
            throw new IllegalArgumentException(f503z[6]);
        }
        ServiceInterface.m1462a(C0448e.f753e, f500a, basicPushNotificationBuilder);
    }

    public static void setLatestNotificationNumber(Context context, int i) {
        m1164a(context);
        ac.m1582b(f503z[1], new StringBuilder(f503z[32]).append(i).toString());
        if (i <= 0) {
            ac.m1589e(f503z[1], f503z[31]);
        } else {
            ServiceInterface.m1474d(context, i);
        }
    }

    public static void setPushNotificationBuilder(Integer num, BasicPushNotificationBuilder basicPushNotificationBuilder) {
        ac.m1578a(f503z[1], new StringBuilder(f503z[22]).append(num).toString());
        if (basicPushNotificationBuilder == null) {
            throw new IllegalArgumentException(f503z[21]);
        } else if (num.intValue() <= 0) {
            ac.m1589e(f503z[1], f503z[20]);
        } else {
            ServiceInterface.m1462a(C0448e.f753e, num, basicPushNotificationBuilder);
        }
    }

    public static void setPushTime(Context context, Set<Integer> set, int i, int i2) {
        m1164a(context);
        if (C0448e.f749a && !C0490b.m1691b(context)) {
            ac.m1582b(f503z[1], f503z[4]);
        }
        if (set == null) {
            m1166a(context, true, f503z[3]);
        } else if (set.size() == 0 || set.isEmpty()) {
            m1166a(context, false, f503z[3]);
        } else if (i > i2) {
            ac.m1589e(f503z[1], f503z[2]);
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            for (Integer num : set) {
                if (num.intValue() > 6 || num.intValue() < 0) {
                    ac.m1589e(f503z[1], new StringBuilder(f503z[0]).append(num).toString());
                    return;
                }
                stringBuilder.append(num);
            }
            stringBuilder.append("_");
            stringBuilder.append(i);
            stringBuilder.append("^");
            stringBuilder.append(i2);
            m1166a(context, true, stringBuilder.toString());
        }
    }

    public static void setSilenceTime(Context context, int i, int i2, int i3, int i4) {
        m1164a(context);
        if (i < 0 || i2 < 0 || i3 < 0 || i4 < 0 || i2 > 59 || i4 > 59 || i3 > 23 || i > 23) {
            ac.m1589e(f503z[1], f503z[25]);
        } else if (i == 0 && i2 == 0 && i3 == 0 && i4 == 0) {
            ServiceInterface.m1463a(context, "");
            ac.m1582b(f503z[1], f503z[23]);
        } else if (ServiceInterface.m1466a(context, i, i2, i3, i4)) {
            ac.m1582b(f503z[1], new StringBuilder(f503z[24]).append(i).append(f503z[27]).append(i2).append(f503z[26]).append(i3).append(f503z[27]).append(i4).toString());
        } else {
            ac.m1589e(f503z[1], f503z[28]);
        }
    }

    public static void setStatisticsEnable(boolean z) {
        f501b.m1190a(z);
    }

    public static void setStatisticsSessionTimeout(long j) {
        if (j < 10) {
            ac.m1587d(f503z[1], f503z[18]);
        } else if (j > 86400) {
            ac.m1587d(f503z[1], f503z[19]);
        } else {
            f501b.m1187a(j);
        }
    }

    public static void setTags(Context context, Set<String> set, TagAliasCallback tagAliasCallback) {
        m1164a(context);
        setAliasAndTags(context, null, set, tagAliasCallback);
    }

    public static void stopPush(Context context) {
        ac.m1582b(f503z[1], f503z[43]);
        m1164a(context);
        if (C0404a.m1131o()) {
            ac.m1581b();
            ServiceInterface.m1465a(context, true);
            return;
        }
        ServiceInterface.m1460a(context, 1);
    }
}
