package cn.jpush.android.util;

import android.app.Activity;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.Intent.ShortcutIconResource;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.Signature;
import android.graphics.Point;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.provider.Settings.Secure;
import android.provider.Settings.System;
import android.support.v4.media.TransportMediator;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Display;
import android.webkit.WebSettings;
import ch.qos.logback.core.net.SyslogConstants;
import cn.jpush.android.C0404a;
import cn.jpush.android.C0448e;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.api.InstrumentedActivity;
import cn.jpush.android.api.InstrumentedListActivity;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.service.AlarmReceiver;
import cn.jpush.android.service.C0480s;
import cn.jpush.android.service.C0481t;
import cn.jpush.android.service.DaemonService;
import cn.jpush.android.service.DownloadService;
import cn.jpush.android.service.PushReceiver;
import cn.jpush.android.service.PushService;
import cn.jpush.android.ui.PopWinActivity;
import cn.jpush.android.ui.PushActivity;
import com.alibaba.fastjson.asm.Opcodes;
import com.alipay.sdk.util.C0880h;
import com.avos.avoscloud.AVException;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.security.auth.x500.X500Principal;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Marker;

/* renamed from: cn.jpush.android.util.b */
public final class C0490b {
    /* renamed from: a */
    public static int f999a = 1;
    /* renamed from: b */
    private static List<String> f1000b;
    /* renamed from: c */
    private static final X500Principal f1001c = new X500Principal(f1007z[175]);
    /* renamed from: d */
    private static long f1002d = 0;
    /* renamed from: e */
    private static final ArrayList<String> f1003e = new ArrayList();
    /* renamed from: f */
    private static final ArrayList<String> f1004f = new ArrayList();
    /* renamed from: g */
    private static final ArrayList<String> f1005g;
    /* renamed from: h */
    private static PushReceiver f1006h;
    /* renamed from: z */
    private static final String[] f1007z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 176; // 0xb0 float:2.47E-43 double:8.7E-322;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\n\u000b Q4\u001a\u0017";
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
            case 0: goto L_0x07dd;
            case 1: goto L_0x07e1;
            case 2: goto L_0x07e5;
            case 3: goto L_0x07e9;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 91;
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
            case 91: goto L_0x0418;
            case 92: goto L_0x0423;
            case 93: goto L_0x042e;
            case 94: goto L_0x0439;
            case 95: goto L_0x0444;
            case 96: goto L_0x044f;
            case 97: goto L_0x045a;
            case 98: goto L_0x0465;
            case 99: goto L_0x0470;
            case 100: goto L_0x047b;
            case 101: goto L_0x0486;
            case 102: goto L_0x0491;
            case 103: goto L_0x049c;
            case 104: goto L_0x04a7;
            case 105: goto L_0x04b2;
            case 106: goto L_0x04bd;
            case 107: goto L_0x04c8;
            case 108: goto L_0x04d3;
            case 109: goto L_0x04de;
            case 110: goto L_0x04e9;
            case 111: goto L_0x04f4;
            case 112: goto L_0x04ff;
            case 113: goto L_0x050a;
            case 114: goto L_0x0515;
            case 115: goto L_0x0520;
            case 116: goto L_0x052b;
            case 117: goto L_0x0536;
            case 118: goto L_0x0541;
            case 119: goto L_0x054c;
            case 120: goto L_0x0557;
            case 121: goto L_0x0562;
            case 122: goto L_0x056d;
            case 123: goto L_0x0578;
            case 124: goto L_0x0583;
            case 125: goto L_0x058e;
            case 126: goto L_0x0599;
            case 127: goto L_0x05a4;
            case 128: goto L_0x05af;
            case 129: goto L_0x05ba;
            case 130: goto L_0x05c5;
            case 131: goto L_0x05d0;
            case 132: goto L_0x05db;
            case 133: goto L_0x05e6;
            case 134: goto L_0x05f1;
            case 135: goto L_0x05fc;
            case 136: goto L_0x0607;
            case 137: goto L_0x0612;
            case 138: goto L_0x061d;
            case 139: goto L_0x0628;
            case 140: goto L_0x0633;
            case 141: goto L_0x063e;
            case 142: goto L_0x0649;
            case 143: goto L_0x0654;
            case 144: goto L_0x065f;
            case 145: goto L_0x066a;
            case 146: goto L_0x0675;
            case 147: goto L_0x0680;
            case 148: goto L_0x068b;
            case 149: goto L_0x0696;
            case 150: goto L_0x06a1;
            case 151: goto L_0x06ac;
            case 152: goto L_0x06b7;
            case 153: goto L_0x06c2;
            case 154: goto L_0x06cd;
            case 155: goto L_0x06d8;
            case 156: goto L_0x06e3;
            case 157: goto L_0x06ee;
            case 158: goto L_0x06f9;
            case 159: goto L_0x0704;
            case 160: goto L_0x070f;
            case 161: goto L_0x071a;
            case 162: goto L_0x0725;
            case 163: goto L_0x0730;
            case 164: goto L_0x073b;
            case 165: goto L_0x0746;
            case 166: goto L_0x0751;
            case 167: goto L_0x075c;
            case 168: goto L_0x0767;
            case 169: goto L_0x0772;
            case 170: goto L_0x077d;
            case 171: goto L_0x0788;
            case 172: goto L_0x0793;
            case 173: goto L_0x079e;
            case 174: goto L_0x07a9;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u001c\u0011%k/\"\f*j";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u001c\u0011%k/'\n1k";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "CE*v,\"\f*#";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0001\n3Q4\u001a\u0017~";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "O\u001bd";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = ".\u000b k4\u0006\u0001\u0011m2\u0003";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "CE!w?\"\f*#";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\n\u000b m\u0016\u0006\u000b7";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "CE7m:\u001d\u0011\fv.\u001d_";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = ",\u00106k>\u0001\u0011dm2\u0002\u0000dp(O\f*9/\u0007\u0000dk:\u0001\u0002!94\tE7p7\n\u000b'|{\u001b\f)|{BE";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "CE!w?'\n1ka";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "CE7m:\u001d\u0011\tp5U";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\f=,\u0010\\\u0004*=\u0010\\\t!$\bF\b;*\u0016X\u001c*";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\t*$\u0000F\u001e71\u0001K\u0015.)\u001bJ\u000f 7\u0005^\u001e";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\f\u0001)x";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\b\u0016)";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0003\u0011!";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u001a\u000b/w4\u0018\u000b";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0019\u00006F5\u000e\b!";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "\u0019\u00006F8\u0000\u0001!";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u0001\u0004)|";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u001f\u000e#";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "VRs-?ZS /c]\u0000q-b\f";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "\u001f\u00107q{\u001b\f)|{\u0006\u0016d＃";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "!\n0p=\u0006\u0006%m2\u0000\u000bdn:\u001cE p(\u000e\u0007(|?O\u0007=9\u0011?\u00107q\u0012\u0001\u0011!k=\u000e\u0006!7(\n\u0011\u0014l(\u00071-t>OD";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = ",\u00106k>\u0001\u0011dm2\u0002\u0000dp(O\n1m{\u0000\u0003dm3\nE4l(\u0007E0p6\nEi9";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "3;";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "%51j3掿祟～缣届纰评产砘";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "\t\u0004-u>\u000bE0v{\b\u000009:\u001f\u0015(p8\u000e\u0011-v5O\f*4O\u0004*}{\u0006\u0006+wu";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK-w/\n\u000b07\u0015 1\r_\u0012,$\u0010P\u0014!:\u000bI\u001e! \u0000F\u000b=*\u001c@";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "\u000b\u0000&l<0\u000b+m2\t\f'x/\u0006\n*";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "\u0005\u00151j30\u000b+m2\t\f'x/\u0006\n*F2\f\n*";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "\u001b\u001c4|";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "\u000e\u00060p-\u0006\u0011=";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = "梯洮剴雟手O6\u0000R{杅涞勤细诺二硤う炠冠枊睮订惜";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        r2 = 36;
        r1 = "讘剕dI4\u001d\u0011%u{乥菒厒悱盟卪周哈X+\u001f.!`席暛旕\u0005w?\u001d\n-}\u0016\u000e\u000b->\u001c\u0011皼庍嬌毚";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01b0:
        r3[r2] = r1;
        r2 = 37;
        r1 = "\u001b\n%j/;\u0000<m";
        r0 = 36;
        r3 = r4;
        goto L_0x0009;
    L_0x01bb:
        r3[r2] = r1;
        r2 = 38;
        r1 = "%51j3掿祟～匜呖哣$4i\u0010\n\u001c义匠鄖";
        r0 = 37;
        r3 = r4;
        goto L_0x0009;
    L_0x01c6:
        r3[r2] = r1;
        r2 = 39;
        r1 = "\u0001\n0p=\u0006\u0006%m2\u0000\u000b";
        r0 = 38;
        r3 = r4;
        goto L_0x0009;
    L_0x01d1:
        r3[r2] = r1;
        r2 = 40;
        r1 = "\u000b\u0017%n:\r\t!";
        r0 = 39;
        r3 = r4;
        goto L_0x0009;
    L_0x01dc:
        r3[r2] = r1;
        r2 = 41;
        r1 = "=\u0000(|:\u001c\u0000 9,\u000e\u000e!97\u0000\u0006/9vO\b-u7\u0006\u0016!z4\u0001\u00017#";
        r0 = 40;
        r3 = r4;
        goto L_0x0009;
    L_0x01e7:
        r3[r2] = r1;
        r2 = 42;
        r1 = "(\n09(\u000b\u0006%k?O\u0003-u>O\u0016%o>\u000bE1}2\u000bEi9";
        r0 = 41;
        r3 = r4;
        goto L_0x0009;
    L_0x01f2:
        r3[r2] = r1;
        r2 = 43;
        r1 = "A\u00151j30\u0010 p?";
        r0 = 42;
        r3 = r4;
        goto L_0x0009;
    L_0x01fd:
        r3[r2] = r1;
        r2 = 44;
        r1 = "%5\u0011J\u00130$\u0014I\u0010*<";
        r0 = 43;
        r3 = r4;
        goto L_0x0009;
    L_0x0208:
        r3[r2] = r1;
        r2 = 45;
        r1 = "\u001f\u00107q\u0004\u001b\n\u001bp60\u0001%m:";
        r0 = 44;
        r3 = r4;
        goto L_0x0009;
    L_0x0213:
        r3[r2] = r1;
        r2 = 46;
        r1 = "\u001f\u00107q\u0004\u0001\u00000n4\u001d\u000e\u001bz4\u0001\u000b!z/\n\u0001";
        r0 = 45;
        r3 = r4;
        goto L_0x0009;
    L_0x021e:
        r3[r2] = r1;
        r2 = 47;
        r1 = ".\u00060p4\u0001Ei9(\n\u000b W>\u001b\u0012+k0,\r%w<\n\u0001\u0010v\u0012\"";
        r0 = 46;
        r3 = r4;
        goto L_0x0009;
    L_0x0229:
        r3[r2] = r1;
        r2 = 48;
        r1 = "\f\u000bjs+\u001a\u0016,72\u0002K%w?\u001d\n-}u\u000e\u00060p4\u0001K\rT\u0004= \u0017I\u0014!6\u0001";
        r0 = 47;
        r3 = r4;
        goto L_0x0009;
    L_0x0234:
        r3[r2] = r1;
        r2 = 49;
        r1 = "\u0005\u0016+w\u001e\u0017\u0006!i/\u0006\n*9vO";
        r0 = 48;
        r3 = r4;
        goto L_0x0009;
    L_0x023f:
        r3[r2] = r1;
        r2 = 50;
        r1 = "A\u00151j30\u0001!o2\f\u0000-}";
        r0 = 49;
        r3 = r4;
        goto L_0x0009;
    L_0x024a:
        r3[r2] = r1;
        r2 = 51;
        r1 = "\b\u000009?\n\u0013-z>O\f 9{\u001c\u0001dz:\u001d\u0001d2\u0003\u0000di:\u001b\rd:\u0006\t";
        r0 = 50;
        r3 = r4;
        goto L_0x0009;
    L_0x0255:
        r3[r2] = r1;
        r2 = 52;
        r1 = "\r\n `";
        r0 = 51;
        r3 = r4;
        goto L_0x0009;
    L_0x0260:
        r3[r2] = r1;
        r2 = 53;
        r1 = "7Kq)b";
        r0 = 52;
        r3 = r4;
        goto L_0x0009;
    L_0x026b:
        r3[r2] = r1;
        r2 = 54;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\n\u001d0k:A\u0016,v)\u001b\u00061mu!$\t\\";
        r0 = 53;
        r3 = r4;
        goto L_0x0009;
    L_0x0276:
        r3[r2] = r1;
        r2 = 55;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\n\u001d0k:A\u0016,v)\u001b\u00061mu&&\u000bW\u0004= \u0017V\u000e=&\u0001";
        r0 = 54;
        r3 = r4;
        goto L_0x0009;
    L_0x0281:
        r3[r2] = r1;
        r2 = 56;
        r1 = "\f\n)7:\u0001\u00016v2\u000bK(x.\u0001\u0006,|)A\u0004'm2\u0000\u000bjP\u0015<1\u0005U\u001706\fV\t;&\u0011M";
        r0 = 55;
        r3 = r4;
        goto L_0x0009;
    L_0x028c:
        r3[r2] = r1;
        r2 = 57;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\u000e\u00060p4\u0001K\u0012P\u001e8";
        r0 = 56;
        r3 = r4;
        goto L_0x0009;
    L_0x0297:
        r3[r2] = r1;
        r2 = 58;
        r1 = "\u000b\u00104u2\f\u00040|";
        r0 = 57;
        r3 = r4;
        goto L_0x0009;
    L_0x02a2:
        r3[r2] = r1;
        r2 = 59;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\n\u001d0k:A\u0016,v)\u001b\u00061mu&+\u0010\\\u0015;";
        r0 = 58;
        r3 = r4;
        goto L_0x0009;
    L_0x02ad:
        r3[r2] = r1;
        r2 = 60;
        r1 = ":\u000b!a+\n\u00060|?UE-w-\u000e\t-}{\u001a\u0017(9vO";
        r0 = 59;
        r3 = r4;
        goto L_0x0009;
    L_0x02b8:
        r3[r2] = r1;
        r2 = 61;
        r1 = "(\n09(\u000b\u0006%k?O\u0003-u>O\u0016%o>\u000bE |-\u0006\u0006!P?OHd";
        r0 = 60;
        r3 = r4;
        goto L_0x0009;
    L_0x02c3:
        r3[r2] = r1;
        r2 = 62;
        r1 = "_Ot";
        r0 = 61;
        r3 = r4;
        goto L_0x0009;
    L_0x02ce:
        r3[r2] = r1;
        r2 = 63;
        r1 = ":\u000b/w4\u0018\u000b";
        r0 = 62;
        r3 = r4;
        goto L_0x0009;
    L_0x02d9:
        r3[r2] = r1;
        r2 = 64;
        r1 = "\f\n*w>\f\u0011-o2\u001b\u001c";
        r0 = 63;
        r3 = r4;
        goto L_0x0009;
    L_0x02e4:
        r3[r2] = r1;
        r2 = 65;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK\u0001A\u000f=$";
        r0 = 64;
        r3 = r4;
        goto L_0x0009;
    L_0x02ef:
        r3[r2] = r1;
        r2 = 66;
        r1 = "\"\u0004-w{\f\t%j(O\f79";
        r0 = 65;
        r3 = r4;
        goto L_0x0009;
    L_0x02fa:
        r3[r2] = r1;
        r2 = 67;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\f\u00040|<\u0000\u0017=7\u0017.0\nZ\u0013*7";
        r0 = 66;
        r3 = r4;
        goto L_0x0009;
    L_0x0305:
        r3[r2] = r1;
        r2 = 68;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\u000e\u00060p4\u0001K\tX\u0012!";
        r0 = 67;
        r3 = r4;
        goto L_0x0009;
    L_0x0310:
        r3[r2] = r1;
        r2 = 69;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\t*$\u0000F\u000b'*\n\\\u0004<1\u0005M\u001e";
        r0 = 68;
        r3 = r4;
        goto L_0x0009;
    L_0x031b:
        r3[r2] = r1;
        r2 = 70;
        r1 = "\u001f\r+w>";
        r0 = 69;
        r3 = r4;
        goto L_0x0009;
    L_0x0326:
        r3[r2] = r1;
        r2 = 71;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK-w/\n\u000b07\u001f\u000e\u0000)v5<\u00006o2\f\u0000";
        r0 = 70;
        r3 = r4;
        goto L_0x0009;
    L_0x0331:
        r3[r2] = r1;
        r2 = 72;
        r1 = ",\n)i4\u0001\u0000*m\u0012\u0001\u0003+b";
        r0 = 71;
        r3 = r4;
        goto L_0x0009;
    L_0x033c:
        r3[r2] = r1;
        r2 = 73;
        r1 = "\"!q";
        r0 = 72;
        r3 = r4;
        goto L_0x0009;
    L_0x0347:
        r3[r2] = r1;
        r2 = 74;
        r1 = "\"$\u00079:\u000b\u0001692\u0001\u0003+4vBHd";
        r0 = 73;
        r3 = r4;
        goto L_0x0009;
    L_0x0352:
        r3[r2] = r1;
        r2 = 75;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\u001a,&\u0001J\b02\r_\u001206\u0010X\u000f*";
        r0 = 74;
        r3 = r4;
        goto L_0x0009;
    L_0x035d:
        r3[r2] = r1;
        r2 = 76;
        r1 = "\u0002\n1w/\n\u0001";
        r0 = 75;
        r3 = r4;
        goto L_0x0009;
    L_0x0368:
        r3[r2] = r1;
        r2 = 77;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\u000e\u00060p4\u0001K\u0014X\u0018$$\u0003\\\u0004= \tV\r*!";
        r0 = 76;
        r3 = r4;
        goto L_0x0009;
    L_0x0373:
        r3[r2] = r1;
        r2 = 78;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\u000e\u00060p4\u0001K\u0011J\u001e=:\u0014K\u001e< \nM";
        r0 = 77;
        r3 = r4;
        goto L_0x0009;
    L_0x037e:
        r3[r2] = r1;
        r2 = 79;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK-w/\n\u000b07\u0015 1\r_\u0012,$\u0010P\u0014!:\u0016\\\u0018*,\u0012\\\u001f05\u0016V\u00036";
        r0 = 78;
        r3 = r4;
        goto L_0x0009;
    L_0x0389:
        r3[r2] = r1;
        r2 = 80;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\u000e\u00060p4\u0001K\u0014X\u0018$$\u0003\\\u0004.!\u0000\\\u001f";
        r0 = 79;
        r3 = r4;
        goto L_0x0009;
    L_0x0394:
        r3[r2] = r1;
        r2 = 81;
        r1 = "\u001f\u0004'r:\b\u0000";
        r0 = 80;
        r3 = r4;
        goto L_0x0009;
    L_0x039f:
        r3[r2] = r1;
        r2 = 82;
        r1 = "\u000e\u000b k4\u0006\u0001jw>\u001bK'v5\u0001K\u0007V\u0015! \u0007M\u00129,\u0010@\u0004,-\u0005W\u001c*";
        r0 = 81;
        r3 = r4;
        goto L_0x0009;
    L_0x03aa:
        r3[r2] = r1;
        r2 = 83;
        r1 = "\u001a\u0011\"4c";
        r0 = 82;
        r3 = r4;
        goto L_0x0009;
    L_0x03b5:
        r3[r2] = r1;
        r2 = 84;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK\u0010P\u000f# ";
        r0 = 83;
        r3 = r4;
        goto L_0x0009;
    L_0x03c0:
        r3[r2] = r1;
        r2 = 85;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK\u0007V\u0015; \nM\u0004;<\u0014\\";
        r0 = 84;
        r3 = r4;
        goto L_0x0009;
    L_0x03cb:
        r3[r2] = r1;
        r2 = 86;
        r1 = "<\u0000*}{\r\u0017+x?\f\u00047m{\u001b\ndx+\u001f_d";
        r0 = 85;
        r3 = r4;
        goto L_0x0009;
    L_0x03d6:
        r3[r2] = r1;
        r2 = 87;
        r1 = "J\u0016ji>\u001d\b-j(\u0006\n*7\u0011?0\u0017Q\u0004\" \u0017J\u001a( ";
        r0 = 86;
        r3 = r4;
        goto L_0x0009;
    L_0x03e1:
        r3[r2] = r1;
        r2 = 88;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK-w/\n\u000b07\u0016*6\u0017X\u001c*:\u0016\\\u0018*,\u0012\\\u001f";
        r0 = 87;
        r3 = r4;
        goto L_0x0009;
    L_0x03ec:
        r3[r2] = r1;
        r2 = 89;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK\tJ\u001c0,\u0000";
        r0 = 88;
        r3 = r4;
        goto L_0x0009;
    L_0x03f7:
        r3[r2] = r1;
        r2 = 90;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK\u0002P\u0017*:\u0014X\u000f'";
        r0 = 89;
        r3 = r4;
        goto L_0x0009;
    L_0x0402:
        r3[r2] = r1;
        r2 = 91;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK\u0005I\u000b$ \u001d";
        r0 = 90;
        r3 = r4;
        goto L_0x0009;
    L_0x040d:
        r3[r2] = r1;
        r2 = 92;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK\t\\\b<$\u0003\\";
        r0 = 91;
        r3 = r4;
        goto L_0x0009;
    L_0x0418:
        r3[r2] = r1;
        r2 = 93;
        r1 = "\u000b\u00002z2\n:-}\u0004\b\u0000*|)\u000e\u0011!}";
        r0 = 92;
        r3 = r4;
        goto L_0x0009;
    L_0x0423:
        r3[r2] = r1;
        r2 = 94;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\f=,\u0010\\\u0004< \u0010M\u0012!\"\u0017";
        r0 = 93;
        r3 = r4;
        goto L_0x0009;
    L_0x042e:
        r3[r2] = r1;
        r2 = 95;
        r1 = "3K";
        r0 = 94;
        r3 = r4;
        goto L_0x0009;
    L_0x0439:
        r3[r2] = r1;
        r2 = 96;
        r1 = "\u001c\u0011%m.\u001c";
        r0 = 95;
        r3 = r4;
        goto L_0x0009;
    L_0x0444:
        r3[r2] = r1;
        r2 = 97;
        r1 = "\u001f\t1~<\n\u0001";
        r0 = 96;
        r3 = r4;
        goto L_0x0009;
    L_0x044f:
        r3[r2] = r1;
        r2 = 98;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\u000e\u00060p4\u0001K\u0006X\u000f; \u0016@\u0004,-\u0005W\u001c*!";
        r0 = 97;
        r3 = r4;
        goto L_0x0009;
    L_0x045a:
        r3[r2] = r1;
        r2 = 99;
        r1 = "\n\b4m\"O\u0015%k:\u0002\u0016";
        r0 = 98;
        r3 = r4;
        goto L_0x0009;
    L_0x0465:
        r3[r2] = r1;
        r2 = 100;
        r1 = "\u001d\njt2\u001a\fjl2A\u0013!k(\u0006\n*75\u000e\b!";
        r0 = 99;
        r3 = r4;
        goto L_0x0009;
    L_0x0470:
        r3[r2] = r1;
        r2 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r1 = "\r\u0017%w?OXd";
        r0 = 100;
        r3 = r4;
        goto L_0x0009;
    L_0x047b:
        r3[r2] = r1;
        r2 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        r1 = "7\f%v6\u0006";
        r0 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0486:
        r3[r2] = r1;
        r2 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r1 = "9Rj(";
        r0 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0491:
        r3[r2] = r1;
        r2 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r1 = "\u001d\nji)\u0000\u00011z/A\u00076x5\u000b";
        r0 = 103; // 0x67 float:1.44E-43 double:5.1E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x049c:
        r3[r2] = r1;
        r2 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        r1 = "\u001d\nj{.\u0006\t 7-\n\u00177p4\u0001K-w8\u001d\u0000)|5\u001b\u0004(";
        r0 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x04a7:
        r3[r2] = r1;
        r2 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        r1 = "\f\u0004*95\u0000\u0011d2\u0001\u0001d";
        r0 = 105; // 0x69 float:1.47E-43 double:5.2E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x04b2:
        r3[r2] = r1;
        r2 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        r1 = "\u001f\u0017+z>\u001c\u0016dw:\u0002\u0000~";
        r0 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x04bd:
        r3[r2] = r1;
        r2 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        r1 = "-\u0010*}7\nE7q4\u001a\t 95\u0000\u0011d{>O\u000b1u7O\u0003+k{\u001c\u0000*}\u0019\u001d\n%}8\u000e\u001607";
        r0 = 107; // 0x6b float:1.5E-43 double:5.3E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x04c8:
        r3[r2] = r1;
        r2 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        r1 = "\u000b\u00040x";
        r0 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x04d3:
        r3[r2] = r1;
        r2 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        r1 = "\u0006\u0011-t>";
        r0 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x04de:
        r3[r2] = r1;
        r2 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        r1 = "\u0006\u0016\u0011i?\u000e\u0011!O>\u001d\u0016-v5";
        r0 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x04e9:
        r3[r2] = r1;
        r2 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK1pu?\u00107q\u001a\f\u0011-o2\u001b\u001c";
        r0 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x04f4:
        r3[r2] = r1;
        r2 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        r1 = "4\u0004ikB\\\u00054\u001d2\u001eu-&";
        r0 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x04ff:
        r3[r2] = r1;
        r2 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        r1 = "4Ui \u0006\u0014Tqd";
        r0 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x050a:
        r3[r2] = r1;
        r2 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        r1 = "4U\u00193";
        r0 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0515:
        r3[r2] = r1;
        r2 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        r1 = "\u001a\u0017(";
        r0 = 115; // 0x73 float:1.61E-43 double:5.7E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0520:
        r3[r2] = r1;
        r2 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        r1 = "\u0002\n |7";
        r0 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x052b:
        r3[r2] = r1;
        r2 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        r1 = "\r\u00047|9\u000e\u000b ";
        r0 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0536:
        r3[r2] = r1;
        r2 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        r1 = "\f\r%w5\n\t";
        r0 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0541:
        r3[r2] = r1;
        r2 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r1 = "\u000b\u00002p8\n";
        r0 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x054c:
        r3[r2] = r1;
        r2 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        r1 = "\b\u0016)7-\n\u00177p4\u0001K&x(\n\u0007%w?";
        r0 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0557:
        r3[r2] = r1;
        r2 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        r1 = "\u0001\u00000n4\u001d\u000e";
        r0 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0562:
        r3[r2] = r1;
        r2 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        r1 = "\u000e\u000b k4\u0006\u0001\u0017}09\u00006j2\u0000\u000b";
        r0 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x056d:
        r3[r2] = r1;
        r2 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r1 = "<\u00066|>\u0001E-w8\u0007\u000079aO";
        r0 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0578:
        r3[r2] = r1;
        r2 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r1 = "\u000b\bjn2\u000b\u0011,I2\u0017\u0000(j{UE";
        r0 = 124; // 0x7c float:1.74E-43 double:6.13E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0583:
        r3[r2] = r1;
        r2 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        r1 = "O\u0001)73\n\f#q/?\f<|7\u001c_";
        r0 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x058e:
        r3[r2] = r1;
        r2 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r1 = "\u001f\n-w/A\u001c";
        r0 = 126; // 0x7e float:1.77E-43 double:6.23E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0599:
        r3[r2] = r1;
        r2 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r1 = "O\u0001)7\"\u000b\u0015-#";
        r0 = 127; // 0x7f float:1.78E-43 double:6.27E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x05a4:
        r3[r2] = r1;
        r2 = 129; // 0x81 float:1.81E-43 double:6.37E-322;
        r1 = "\u000b\bja?\u001f\fd#{";
        r0 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x05af:
        r3[r2] = r1;
        r2 = 130; // 0x82 float:1.82E-43 double:6.4E-322;
        r1 = "\u001f\n-w/A\u001d~";
        r0 = 129; // 0x81 float:1.81E-43 double:6.37E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x05ba:
        r3[r2] = r1;
        r2 = 131; // 0x83 float:1.84E-43 double:6.47E-322;
        r1 = "\u0004\u0000=";
        r0 = 130; // 0x82 float:1.82E-43 double:6.4E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x05c5:
        r3[r2] = r1;
        r2 = 132; // 0x84 float:1.85E-43 double:6.5E-322;
        r1 = "?\u0017!()\f(|";
        r0 = 131; // 0x83 float:1.84E-43 double:6.47E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x05d0:
        r3[r2] = r1;
        r2 = 133; // 0x85 float:1.86E-43 double:6.57E-322;
        r1 = "+\u00002|7\u0000\u0015!k{\u001c\r+l7\u000bE7|/O$4i\u0010\n\u001cdp5O$*})\u0000\f T:\u0001\f\"|(\u001bK<t7";
        r0 = 132; // 0x84 float:1.85E-43 double:6.5E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x05db:
        r3[r2] = r1;
        r2 = 134; // 0x86 float:1.88E-43 double:6.6E-322;
        r1 = ".\u000b k4\u0006\u0001\tx5\u0006\u0003!j/A\u001d)u{\u0002\f7j2\u0001\u0002dk>\u001e\u0010-k>\u000bE-w/\n\u000b09=\u0006\t0|)O_dz5A\u000f4l(\u0007K%w?\u001d\n-}u\u0006\u000b0|5\u001bK\u0016\\\u000b 7\u0010";
        r0 = 133; // 0x85 float:1.86E-43 double:6.57E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x05e6:
        r3[r2] = r1;
        r2 = 135; // 0x87 float:1.89E-43 double:6.67E-322;
        r1 = ".\u000b k4\u0006\u0001\tx5\u0006\u0003!j/A\u001d)u{\u0002\f7j2\u0001\u0002dk>\u001e\u0010-k>\u000bE-w/\n\u000b09=\u0006\t0|)O\u0003+k{?\u00107q\b\n\u00172p8\n_dz5A\u000f4l(\u0007K%w?\u001d\n-}u\u0006\u000b0|5\u001bK\u0016\\\u001c&6\u0010\\\t";
        r0 = 134; // 0x86 float:1.88E-43 double:6.6E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x05f1:
        r3[r2] = r1;
        r2 = 136; // 0x88 float:1.9E-43 double:6.7E-322;
        r1 = "\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu\u000e\u00060p4\u0001K\u0006V\u0014;:\u0007V\u0016?)\u0001M\u001e+";
        r0 = 135; // 0x87 float:1.89E-43 double:6.67E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x05fc:
        r3[r2] = r1;
        r2 = 137; // 0x89 float:1.92E-43 double:6.77E-322;
        r1 = "?\u00107q\t\n\u0006!p-\n\u0017dj3\u0000\u0010(}{\u0001\n093\u000e\u0013!92\u0001\u0011!w/O\u0003-u/\n\u0017d4vO\u0004*})\u0000\f 72\u0001\u0011!w/A\u0004'm2\u0000\u000bj[\u0014 1\u001bZ\u0014\"5\b\\\u000f*!h9\u000b\u0003\u0000%j>O\u0017!t4\u0019\u0000dm3\nE-w/\n\u000b09=\u0006\t0|)O\f*9\u001a\u0001\u00016v2\u000b(%w2\t\u00007mu\u0017\b(";
        r0 = 136; // 0x88 float:1.9E-43 double:6.7E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0607:
        r3[r2] = r1;
        r2 = 138; // 0x8a float:1.93E-43 double:6.8E-322;
        r1 = ";\r!9+\n\u0017)p(\u001c\f+w{\u001c\r+l7\u000bE&|{\u000b\u0000\"p5\n\u0001d4{";
        r0 = 137; // 0x89 float:1.92E-43 double:6.77E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0612:
        r3[r2] = r1;
        r2 = 139; // 0x8b float:1.95E-43 double:6.87E-322;
        r1 = ".\u000b k4\u0006\u0001\tx5\u0006\u0003!j/A\u001d)u{\u0002\f7j2\u0001\u0002dk>\u001e\u0010-k>\u000bE7|)\u0019\f'|aO";
        r0 = 138; // 0x8a float:1.93E-43 double:6.8E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x061d:
        r3[r2] = r1;
        r2 = 140; // 0x8c float:1.96E-43 double:6.9E-322;
        r1 = ".\u000b k4\u0006\u0001\tx5\u0006\u0003!j/A\u001d)u{\u0002\f7j2\u0001\u0002dk>\u001e\u0010-k>\u000bE-w/\n\u000b09=\u0006\t0|)O\u0003+k{?\u00107q\t\n\u0006!p-\n\u0017~98\u0001K.i.\u001c\rjx5\u000b\u0017+p?A\f*m>\u0001\u0011jW\u0014;,\u0002P\u0018.1\rV\u001507\u0001Z\u001e&3\u0001]\u0004?7\u000bA\u0002";
        r0 = 139; // 0x8b float:1.95E-43 double:6.87E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0628:
        r3[r2] = r1;
        r2 = 141; // 0x8d float:1.98E-43 double:6.97E-322;
        r1 = ".\u000b k4\u0006\u0001\tx5\u0006\u0003!j/A\u001d)u{\u0002\f7j2\u0001\u0002dp5\u001b\u0000*m{\t\f(m>\u001dE\"v)O!%|6\u0000\u000b\u0017|)\u0019\f'|aO\u0006*71\u001f\u00107qu\u000e\u000b k4\u0006\u0001jp5\u001b\u0000*mu+\u0004!t4\u00016!k-\u0006\u0006!";
        r0 = 140; // 0x8c float:1.96E-43 double:6.9E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0633:
        r3[r2] = r1;
        r2 = 142; // 0x8e float:1.99E-43 double:7.0E-322;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK-w/\n\u000b07\t*5\u000bK\u000f";
        r0 = 141; // 0x8d float:1.98E-43 double:6.97E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x063e:
        r3[r2] = r1;
        r2 = 143; // 0x8f float:2.0E-43 double:7.07E-322;
        r1 = ";\r!9+\n\u0017)p(\u001c\n-w{\u0006\u0016dk>\u001e\u0010-k>\u000bEi9";
        r0 = 142; // 0x8e float:1.99E-43 double:7.0E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0649:
        r3[r2] = r1;
        r2 = 144; // 0x90 float:2.02E-43 double:7.1E-322;
        r1 = ";\r!9+\n\u0017)p(\u001c\n-w{\u0006\u0016dk>\u001e\u0010-k>\u000bEi9:\u0001\u00016v2\u000bK4|)\u0002\f7j2\u0000\u000bjN\t&1\u0001F\u001e71\u0001K\u0015.)\u001bJ\u000f 7\u0005^\u001e";
        r0 = 143; // 0x8f float:2.0E-43 double:7.07E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0654:
        r3[r2] = r1;
        r2 = 145; // 0x91 float:2.03E-43 double:7.16E-322;
        r1 = "A\u0015!k6\u0006\u00167p4\u0001K\u000eI\u000e<-\u001bT\u001e<6\u0005^\u001e";
        r0 = 144; // 0x90 float:2.02E-43 double:7.1E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x065f:
        r3[r2] = r1;
        r2 = 146; // 0x92 float:2.05E-43 double:7.2E-322;
        r1 = ".\u000b k4\u0006\u0001\tx5\u0006\u0003!j/A\u001d)u{\u0002\f7j2\u0001\u0002dk>\u001e\u0010-k>\u000bE%z/\u0006\u0013-m\"UE";
        r0 = 145; // 0x91 float:2.03E-43 double:7.16E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x066a:
        r3[r2] = r1;
        r2 = 147; // 0x93 float:2.06E-43 double:7.26E-322;
        r1 = "CE+m3\n\u00173p(\nE=v.O\u0006%w{\u0001\n097\u0000\u0006%m>O\u0011,|{\u000b\u00002p8\n\u0016j";
        r0 = 146; // 0x92 float:2.05E-43 double:7.2E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0675:
        r3[r2] = r1;
        r2 = 148; // 0x94 float:2.07E-43 double:7.3E-322;
        r1 = "8\u0000dk>\f\n)t>\u0001\u0001d`4\u001aE%}?O\u0011,|{\u001f\u00006t2\u001c\u0016-v5OHd";
        r0 = 147; // 0x93 float:2.06E-43 double:7.26E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0680:
        r3[r2] = r1;
        r2 = 149; // 0x95 float:2.09E-43 double:7.36E-322;
        r1 = ".\u000b k4\u0006\u0001\tx5\u0006\u0003!j/A\u001d)u{\u0002\f7j2\u0001\u0002dk>\u001e\u0010-k>\u000bE-w/\n\u000b09=\u0006\t0|)O\u0003+k{?\u00107q\u001a\f\u0011-o2\u001b\u001c~98\u0001K.i.\u001c\rjx5\u000b\u0017+p?A\u0010-7\u000b\u001a\u0016,X8\u001b\f2p/\u0016";
        r0 = 148; // 0x94 float:2.07E-43 double:7.3E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x068b:
        r3[r2] = r1;
        r2 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r1 = ".\u000b k4\u0006\u0001\tx5\u0006\u0003!j/A\u001d)u{\u0002\f7j2\u0001\u0002dk>\u001e\u0010-k>\u000bE6|8\n\f2|)UE";
        r0 = 149; // 0x95 float:2.09E-43 double:7.36E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0696:
        r3[r2] = r1;
        r2 = 151; // 0x97 float:2.12E-43 double:7.46E-322;
        r1 = "6\n19(\u0007\n1u?O\b%r>O\b%p5O\u0004'm2\u0019\f0`{\n\u001d0|5\u000b\u0016dP5\u001c\u00116l6\n\u000b0|?.\u00060p-\u0006\u0011=9s%51j3FIdv/\u0007\u00006n2\u001c\u0000d`4\u001aE3p7\u0003E*v/O\u0016!|{\u001a\u0016!k{\f\t-z0O\u0004*}{\u001a\u0016!k{\u000e\u00060p-\nE0p6\nE7m:\u001d\u0011dv5O\u0017!i4\u001d\u0011dp5O5+k/\u000e\tj9";
        r0 = 150; // 0x96 float:2.1E-43 double:7.4E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x06a1:
        r3[r2] = r1;
        r2 = 152; // 0x98 float:2.13E-43 double:7.5E-322;
        r1 = ";\r!9+\n\u0017)p(\u001c\n-w{\u0006\u0016dk>\u001e\u0010-k>\u000bEi9:\u0001\u00016v2\u000bK4|)\u0002\f7j2\u0000\u000bjN\t&1\u0001F\b*1\u0010P\u0015(6";
        r0 = 151; // 0x97 float:2.12E-43 double:7.46E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x06ac:
        r3[r2] = r1;
        r2 = 153; // 0x99 float:2.14E-43 double:7.56E-322;
        r1 = "\u000e\u00060p4\u0001_'q>\f\u000e\u0012x7\u0006\u0001\tx5\u0006\u0003!j/";
        r0 = 152; // 0x98 float:2.13E-43 double:7.5E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x06b7:
        r3[r2] = r1;
        r2 = 154; // 0x9a float:2.16E-43 double:7.6E-322;
        r1 = "\f\u000bjs+\u001a\u0016,7:\u0001\u00016v2\u000bK-w/\n\u000b07\t*\"\rJ\u000f*7";
        r0 = 153; // 0x99 float:2.14E-43 double:7.56E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x06c2:
        r3[r2] = r1;
        r2 = 155; // 0x9b float:2.17E-43 double:7.66E-322;
        r1 = "@\u0001%m:@";
        r0 = 154; // 0x9a float:2.16E-43 double:7.6E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x06cd:
        r3[r2] = r1;
        r2 = 156; // 0x9c float:2.19E-43 double:7.7E-322;
        r1 = "\u000e\u000b k4\u0006\u0001\u001bp?";
        r0 = 155; // 0x9b float:2.17E-43 double:7.66E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x06d8:
        r3[r2] = r1;
        r2 = 157; // 0x9d float:2.2E-43 double:7.76E-322;
        r1 = ":1\u00024c";
        r0 = 156; // 0x9c float:2.19E-43 double:7.7E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x06e3:
        r3[r2] = r1;
        r2 = 158; // 0x9e float:2.21E-43 double:7.8E-322;
        r1 = ",\u00106k>\u0001\u0011di0O\f*j/\u000e\t(|?O\u0015%m3UE";
        r0 = 157; // 0x9d float:2.2E-43 double:7.76E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x06ee:
        r3[r2] = r1;
        r2 = 159; // 0x9f float:2.23E-43 double:7.86E-322;
        r1 = "@\u0001%m:@\u00044it";
        r0 = 158; // 0x9e float:2.21E-43 double:7.8E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x06f9:
        r3[r2] = r1;
        r2 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r1 = "@\u0016=j/\n\bkx+\u001fJ";
        r0 = 159; // 0x9f float:2.23E-43 double:7.86E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0704:
        r3[r2] = r1;
        r2 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        r1 = "KA";
        r0 = 160; // 0xa0 float:2.24E-43 double:7.9E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x070f:
        r3[r2] = r1;
        r2 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        r1 = "\u000e\u00154u2\f\u00040p4\u0001J2w?A\u0004*})\u0000\f 7+\u000e\u0006/x<\nH%k8\u0007\f2|";
        r0 = 161; // 0xa1 float:2.26E-43 double:7.95E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x071a:
        r3[r2] = r1;
        r2 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        r1 = "\u001f\n3|)";
        r0 = 162; // 0xa2 float:2.27E-43 double:8.0E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0725:
        r3[r2] = r1;
        r2 = 164; // 0xa4 float:2.3E-43 double:8.1E-322;
        r1 = "0/\u0014l(\u0007";
        r0 = 163; // 0xa3 float:2.28E-43 double:8.05E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0730:
        r3[r2] = r1;
        r2 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        r1 = "(\f2|{\u001a\u0015dm4O\u00160x)\u001bE%i+O\u0003+k{\u0006\u000b2x7\u0006\u0001di:\u001d\u0004)j{BE4x8\u0004\u0004#|\u0015\u000e\b!#";
        r0 = 164; // 0xa4 float:2.3E-43 double:8.1E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x073b:
        r3[r2] = r1;
        r2 = 166; // 0xa6 float:2.33E-43 double:8.2E-322;
        r1 = "!0\bU{\f\n*m>\u0017\u0011";
        r0 = 165; // 0xa5 float:2.31E-43 double:8.15E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0746:
        r3[r2] = r1;
        r2 = 167; // 0xa7 float:2.34E-43 double:8.25E-322;
        r1 = "\u000e\u000b k4\u0006\u0001dt:\fE%}?\u001d\u00007ja";
        r0 = 166; // 0xa6 float:2.33E-43 double:8.2E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0751:
        r3[r2] = r1;
        r2 = 168; // 0xa8 float:2.35E-43 double:8.3E-322;
        r1 = "\f\u000409t\u001c\u001c768\u0003\u00047jt\u0001\u000006,\u0003\u0004*)t\u000e\u0001 k>\u001c\u0016";
        r0 = 167; // 0xa7 float:2.34E-43 double:8.25E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x075c:
        r3[r2] = r1;
        r2 = 169; // 0xa9 float:2.37E-43 double:8.35E-322;
        r1 = "\u0018\f\"p";
        r0 = 168; // 0xa8 float:2.35E-43 double:8.3E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0767:
        r3[r2] = r1;
        r2 = 170; // 0xaa float:2.38E-43 double:8.4E-322;
        r1 = "?\u0017+z>\u001c\u0016+k";
        r0 = 169; // 0xa9 float:2.37E-43 double:8.35E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0772:
        r3[r2] = r1;
        r2 = 171; // 0xab float:2.4E-43 double:8.45E-322;
        r1 = "@\u00156v8@\u00064l2\u0001\u0003+";
        r0 = 170; // 0xaa float:2.38E-43 double:8.4E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x077d:
        r3[r2] = r1;
        r2 = 172; // 0xac float:2.41E-43 double:8.5E-322;
        r1 = "]\u0002";
        r0 = 171; // 0xab float:2.4E-43 double:8.45E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0788:
        r3[r2] = r1;
        r2 = 173; // 0xad float:2.42E-43 double:8.55E-322;
        r1 = "\\\u0002";
        r0 = 172; // 0xac float:2.41E-43 double:8.5E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x0793:
        r3[r2] = r1;
        r2 = 174; // 0xae float:2.44E-43 double:8.6E-322;
        r1 = "[\u0002";
        r0 = 173; // 0xad float:2.42E-43 double:8.55E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x079e:
        r3[r2] = r1;
        r2 = 175; // 0xaf float:2.45E-43 double:8.65E-322;
        r1 = ",+yX5\u000b\u0017+p?O!!{.\bI\u000b$\u001a\u0001\u00016v2\u000bI\u0007$\u000e<";
        r0 = 174; // 0xae float:2.44E-43 double:8.6E-322;
        r3 = r4;
        goto L_0x0009;
    L_0x07a9:
        r3[r2] = r1;
        f1007z = r4;
        r0 = 1;
        f999a = r0;
        r2 = new java.util.ArrayList;
        r2.<init>();
        f1000b = r2;
        r1 = "\\P|/l\\Uu*lVP| n";
        r0 = -1;
    L_0x07ba:
        r1 = r1.toCharArray();
        r3 = r1.length;
        r4 = 0;
        r5 = 1;
        if (r3 > r5) goto L_0x07fb;
    L_0x07c3:
        r5 = r1;
        r6 = r4;
        r11 = r3;
        r3 = r1;
        r1 = r11;
    L_0x07c8:
        r8 = r3[r4];
        r7 = r6 % 5;
        switch(r7) {
            case 0: goto L_0x07ed;
            case 1: goto L_0x07f0;
            case 2: goto L_0x07f3;
            case 3: goto L_0x07f6;
            default: goto L_0x07cf;
        };
    L_0x07cf:
        r7 = 91;
    L_0x07d1:
        r7 = r7 ^ r8;
        r7 = (char) r7;
        r3[r4] = r7;
        r4 = r6 + 1;
        if (r1 != 0) goto L_0x07f9;
    L_0x07d9:
        r3 = r5;
        r6 = r4;
        r4 = r1;
        goto L_0x07c8;
    L_0x07dd:
        r9 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x0020;
    L_0x07e1:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0020;
    L_0x07e5:
        r9 = 68;
        goto L_0x0020;
    L_0x07e9:
        r9 = 25;
        goto L_0x0020;
    L_0x07ed:
        r7 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x07d1;
    L_0x07f0:
        r7 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x07d1;
    L_0x07f3:
        r7 = 68;
        goto L_0x07d1;
    L_0x07f6:
        r7 = 25;
        goto L_0x07d1;
    L_0x07f9:
        r3 = r1;
        r1 = r5;
    L_0x07fb:
        if (r3 > r4) goto L_0x07c3;
    L_0x07fd:
        r3 = new java.lang.String;
        r3.<init>(r1);
        r1 = r3.intern();
        switch(r0) {
            case 0: goto L_0x088b;
            case 1: goto L_0x0895;
            case 2: goto L_0x089f;
            case 3: goto L_0x08a9;
            case 4: goto L_0x08b3;
            case 5: goto L_0x08c2;
            case 6: goto L_0x08cc;
            case 7: goto L_0x08d6;
            default: goto L_0x0809;
        };
    L_0x0809:
        r2.add(r1);
        r2 = f1000b;
        r1 = "_Up bVUu)m[Ut)k";
        r0 = -1;
    L_0x0811:
        r1 = r1.toCharArray();
        r3 = r1.length;
        r4 = 0;
        r5 = 1;
        if (r3 > r5) goto L_0x0842;
    L_0x081a:
        r5 = r1;
        r6 = r4;
        r11 = r3;
        r3 = r1;
        r1 = r11;
    L_0x081f:
        r8 = r3[r4];
        r7 = r6 % 5;
        switch(r7) {
            case 0: goto L_0x0834;
            case 1: goto L_0x0837;
            case 2: goto L_0x083a;
            case 3: goto L_0x083d;
            default: goto L_0x0826;
        };
    L_0x0826:
        r7 = 91;
    L_0x0828:
        r7 = r7 ^ r8;
        r7 = (char) r7;
        r3[r4] = r7;
        r4 = r6 + 1;
        if (r1 != 0) goto L_0x0840;
    L_0x0830:
        r3 = r5;
        r6 = r4;
        r4 = r1;
        goto L_0x081f;
    L_0x0834:
        r7 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x0828;
    L_0x0837:
        r7 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0828;
    L_0x083a:
        r7 = 68;
        goto L_0x0828;
    L_0x083d:
        r7 = 25;
        goto L_0x0828;
    L_0x0840:
        r3 = r1;
        r1 = r5;
    L_0x0842:
        if (r3 > r4) goto L_0x081a;
    L_0x0844:
        r3 = new java.lang.String;
        r3.<init>(r1);
        r1 = r3.intern();
        switch(r0) {
            case 0: goto L_0x0859;
            case 1: goto L_0x0862;
            default: goto L_0x0850;
        };
    L_0x0850:
        r2.add(r1);
        r2 = f1000b;
        r1 = "_Ut)k_Ut)k_Ut)";
        r0 = 0;
        goto L_0x0811;
    L_0x0859:
        r2.add(r1);
        r2 = f1000b;
        r1 = "_Ut)k_Ut)k_Ut)k";
        r0 = 1;
        goto L_0x0811;
    L_0x0862:
        r2.add(r1);
        r0 = new javax.security.auth.x500.X500Principal;
        r1 = f1007z;
        r2 = 175; // 0xaf float:2.45E-43 double:8.65E-322;
        r1 = r1[r2];
        r0.<init>(r1);
        f1001c = r0;
        r0 = 0;
        f1002d = r0;
        r0 = new java.util.ArrayList;
        r0.<init>();
        f1003e = r0;
        r0 = new java.util.ArrayList;
        r0.<init>();
        f1004f = r0;
        r2 = f1003e;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\u0012!1\u0001K\u0015*1";
        r0 = 0;
        goto L_0x07ba;
    L_0x088b:
        r2.add(r1);
        r2 = f1003e;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\f..\u0001F\u0017 &\u000f";
        r0 = 1;
        goto L_0x07ba;
    L_0x0895:
        r2.add(r1);
        r2 = f1003e;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\u001a,&\u0001J\b0+\u0001M\f 7\u000fF\b;$\u0010\\";
        r0 = 2;
        goto L_0x07ba;
    L_0x089f:
        r2.add(r1);
        r2 = f1004f;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\r&'\u0016X\u000f*";
        r0 = 3;
        goto L_0x07ba;
    L_0x08a9:
        r2.add(r1);
        r2 = f1004f;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\u0018'$\n^\u001e02\r_\u001206\u0010X\u000f*";
        r0 = 4;
        goto L_0x07ba;
    L_0x08b3:
        r2.add(r1);
        r2 = new java.util.ArrayList;
        r2.<init>();
        f1005g = r2;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\u001a,&\u0001J\b0#\rW\u001e0)\u000bZ\u001a;,\u000bW";
        r0 = 5;
        goto L_0x07ba;
    L_0x08c2:
        r2.add(r1);
        r2 = f1005g;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\u001a,&\u0001J\b0&\u000bX\t< \u001bU\u0014,$\u0010P\u0014!";
        r0 = 6;
        goto L_0x07ba;
    L_0x08cc:
        r2.add(r1);
        r2 = f1005g;
        r1 = "\u000e\u000b k4\u0006\u0001ji>\u001d\b-j(\u0006\n*7\u001a,&\u0001J\b0)\u000bZ\u001a;,\u000bW\u0004*=\u0010K\u001a0&\u000bT\u0016.+\u0000J";
        r0 = 7;
        goto L_0x07ba;
    L_0x08d6:
        r2.add(r1);
        r0 = f1005g;
        r1 = f1007z;
        r2 = 75;
        r1 = r1[r2];
        r0.add(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.b.<clinit>():void");
    }

    /* renamed from: A */
    private static String m1662A(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(f1007z[Opcodes.IINC], 0);
        String string = sharedPreferences.getString(f1007z[131], null);
        if (string != null) {
            return string;
        }
        string = UUID.randomUUID().toString();
        Editor edit = sharedPreferences.edit();
        edit.putString(f1007z[131], string);
        edit.commit();
        return string;
    }

    /* renamed from: B */
    private static boolean m1663B(Context context) {
        try {
            Intent intent = new Intent(f1007z[68]);
            intent.setPackage(context.getPackageName());
            intent.addCategory(f1007z[67]);
            ActivityInfo activityInfo = context.getPackageManager().resolveActivity(intent, 0).activityInfo;
            if (activityInfo == null) {
                return false;
            }
            Class cls = Class.forName(activityInfo.name);
            return cls == null ? false : InstrumentedActivity.class.isAssignableFrom(cls) || InstrumentedListActivity.class.isAssignableFrom(cls);
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: C */
    private static void m1664C(Context context) {
        ac.m1584c();
        if (f1006h == null) {
            f1006h = new PushReceiver();
        }
        context.registerReceiver(f1006h, new IntentFilter(f1007z[78]));
        context.registerReceiver(f1006h, new IntentFilter(f1007z[82]));
        try {
            IntentFilter intentFilter = new IntentFilter(f1007z[80]);
            intentFilter.addDataScheme(f1007z[81]);
            IntentFilter intentFilter2 = new IntentFilter(f1007z[77]);
            intentFilter2.addDataScheme(f1007z[81]);
            IntentFilter intentFilter3 = new IntentFilter(f1007z[79]);
            intentFilter3.setPriority(1000);
            intentFilter3.addCategory(context.getPackageName());
            context.registerReceiver(f1006h, intentFilter);
            context.registerReceiver(f1006h, intentFilter2);
            context.registerReceiver(f1006h, intentFilter3);
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public static int m1665a(Context context, float f) {
        return (int) (context.getResources().getDisplayMetrics().density * f);
    }

    /* renamed from: a */
    public static Intent m1666a(Context context, C0429c c0429c) {
        Intent intent = new Intent(context, PopWinActivity.class);
        intent.putExtra(f1007z[52], c0429c);
        intent.addFlags(335544320);
        return intent;
    }

    /* renamed from: a */
    public static Intent m1667a(Context context, C0429c c0429c, boolean z) {
        Intent intent = new Intent();
        intent.putExtra(f1007z[111], z);
        intent.putExtra(f1007z[52], c0429c);
        intent.setAction(f1007z[112]);
        intent.addCategory(context.getPackageName());
        intent.addFlags(335544320);
        return intent;
    }

    /* renamed from: a */
    public static String m1668a(int i) {
        return (i == 1 || i == 2 || i == 8) ? f1007z[16] : (i == 4 || i == 7 || i == 5 || i == 6) ? f1007z[15] : i == 13 ? f1007z[17] : f1007z[18];
    }

    /* renamed from: a */
    public static String m1669a(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        if (displayMetrics == null) {
            return f1007z[62];
        }
        int i = displayMetrics.widthPixels;
        return i + Marker.ANY_MARKER + displayMetrics.heightPixels;
    }

    /* renamed from: a */
    public static String m1670a(Context context, String str) {
        Object obj;
        String str2 = VERSION.RELEASE + "," + Integer.toString(VERSION.SDK_INT);
        String str3 = Build.MODEL;
        String a = C0480s.m1537a(context, f1007z[AVException.INVALID_NESTED_KEY], f1007z[118]);
        String str4 = Build.DEVICE;
        String F = C0404a.m1051F();
        if (an.m1657a(F)) {
            F = " ";
        }
        StringBuilder append = new StringBuilder().append(str2).append(f1007z[161]).append(str3).append(f1007z[161]).append(a).append(f1007z[161]).append(str4).append(f1007z[161]).append(F).append(f1007z[161]).append(str).append(f1007z[161]);
        F = context.getApplicationInfo().sourceDir;
        if (an.m1657a(F)) {
            ac.m1588e();
            obj = null;
        } else {
            new StringBuilder(f1007z[Opcodes.IFLE]).append(F);
            ac.m1581b();
            if (F.startsWith(f1007z[160])) {
                int i = 1;
            } else if (F.startsWith(f1007z[Opcodes.IF_ICMPEQ])) {
                obj = null;
            } else {
                ac.m1584c();
                obj = null;
            }
        }
        return append.append(obj != null ? 1 : 0).append(f1007z[161]).append(C0490b.m1669a(context)).toString();
    }

    /* renamed from: a */
    public static String m1671a(String str) {
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance(f1007z[73]);
            char[] toCharArray = str.toCharArray();
            byte[] bArr = new byte[toCharArray.length];
            for (int i2 = 0; i2 < toCharArray.length; i2++) {
                bArr[i2] = (byte) toCharArray[i2];
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            while (i < digest.length) {
                int i3 = digest[i] & 255;
                if (i3 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i3));
                i++;
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            ac.m1581b();
            return "";
        }
    }

    /* renamed from: a */
    public static String m1672a(byte[] bArr) {
        try {
            byte[] digest = MessageDigest.getInstance(f1007z[73]).digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            ac.m1581b();
            return "";
        }
    }

    /* renamed from: a */
    public static JSONObject m1673a(String str, String str2) {
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f1007z[109], str2);
            jSONObject.put(f1007z[33], str);
            jSONObject.put(f1007z[110], C0404a.m1126m());
            return jSONObject;
        } catch (Exception e) {
            e.getMessage();
            ac.m1588e();
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m1674a(String str, JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() == 0) {
            return null;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f1007z[109], jSONArray);
            jSONObject.put(f1007z[33], str);
            jSONObject.put(f1007z[110], C0404a.m1126m());
            return jSONObject;
        } catch (Exception e) {
            e.getMessage();
            ac.m1588e();
            return null;
        }
    }

    /* renamed from: a */
    public static void m1675a(Context context, String str, Bundle bundle) {
        if (bundle == null) {
            ac.m1589e(f1007z[6], f1007z[108]);
            return;
        }
        Intent intent = new Intent(str);
        bundle.putString(f1007z[91], C0404a.m1050E());
        intent.putExtras(bundle);
        intent.addCategory(context.getPackageName());
        context.sendBroadcast(intent, String.format(f1007z[87], new Object[]{r1}));
    }

    /* renamed from: a */
    public static void m1676a(Context context, String str, String str2, int i) {
        Uri parse = Uri.parse(str2);
        if (parse == null) {
            new StringBuilder(f1007z[60]).append(str2);
            ac.m1581b();
            return;
        }
        Parcelable intent = new Intent(f1007z[57], parse);
        intent.setFlags(335544320);
        Parcelable fromContext = ShortcutIconResource.fromContext(context, i);
        Intent intent2 = new Intent(f1007z[56]);
        intent2.putExtra(f1007z[58], false);
        intent2.putExtra(f1007z[54], str);
        intent2.putExtra(f1007z[59], intent);
        intent2.putExtra(f1007z[55], fromContext);
        context.sendBroadcast(intent2);
    }

    /* renamed from: a */
    public static void m1677a(Context context, String str, String str2, String str3) {
        Bundle bundle = new Bundle();
        if (str2 != null) {
            bundle.putString(str2, str3);
        }
        C0490b.m1675a(context, str, bundle);
    }

    /* renamed from: a */
    public static void m1678a(Context context, String str, String str2, byte[] bArr) {
        Bundle bundle = new Bundle();
        if (!(str2 == null || bArr == null)) {
            bundle.putSerializable(str2, bArr);
        }
        C0490b.m1675a(context, str, bundle);
    }

    /* renamed from: a */
    public static void m1679a(Context context, boolean z) {
        ac.m1582b(f1007z[6], f1007z[47]);
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f1007z[46], z);
            bundle.putString(f1007z[45], jSONObject.toString());
            C0490b.m1675a(context, f1007z[48], bundle);
        } catch (JSONException e) {
            new StringBuilder(f1007z[49]).append(e.getMessage());
            ac.m1586d();
        }
    }

    /* renamed from: a */
    public static void m1680a(WebSettings webSettings) {
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDefaultTextEncodingName(f1007z[Opcodes.IFGT]);
        webSettings.setSupportZoom(true);
        webSettings.setCacheMode(2);
        webSettings.setSaveFormData(false);
        webSettings.setSavePassword(false);
    }

    /* renamed from: a */
    public static boolean m1681a() {
        boolean equals = Environment.getExternalStorageState().equals(f1007z[76]);
        if (!equals) {
            ac.m1581b();
        }
        return equals;
    }

    /* renamed from: a */
    private static boolean m1682a(Context context, Class<?> cls) {
        return !context.getPackageManager().queryBroadcastReceivers(new Intent(context, cls), 0).isEmpty();
    }

    /* renamed from: a */
    public static boolean m1683a(Context context, String str, String str2) {
        if (context == null) {
            throw new IllegalArgumentException(f1007z[Opcodes.IF_ACMPNE]);
        } else if (TextUtils.isEmpty(str)) {
            new StringBuilder(f1007z[Opcodes.IF_ACMPEQ]).append(str);
            ac.m1586d();
            return false;
        } else {
            Intent n = C0490b.m1726n(context, str);
            if (n == null) {
                try {
                    if (TextUtils.isEmpty(str2)) {
                        ac.m1581b();
                        return false;
                    }
                    n = new Intent();
                    n.setClassName(str, str2);
                    n.addCategory(f1007z[67]);
                } catch (Exception e) {
                    ac.m1591g();
                    return false;
                }
            }
            n.setFlags(268435456);
            context.startActivity(n);
            return true;
        }
    }

    /* renamed from: a */
    public static boolean m1684a(Context context, String str, boolean z) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        intent.addCategory(context.getPackageName());
        return !packageManager.queryBroadcastReceivers(intent, 0).isEmpty();
    }

    /* renamed from: b */
    public static String m1685b(Context context, String str) {
        String str2 = VERSION.RELEASE + "," + Integer.toString(VERSION.SDK_INT);
        String str3 = Build.MODEL;
        String a = C0480s.m1537a(context, f1007z[AVException.INVALID_NESTED_KEY], f1007z[118]);
        String str4 = Build.DEVICE;
        Object F = C0404a.m1051F();
        if (an.m1657a(F)) {
            F = " ";
        }
        String c = C0490b.m1696c(context);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f1007z[AVException.INVALID_ACL], str2);
            jSONObject.put(f1007z[117], str3);
            jSONObject.put(f1007z[118], a);
            jSONObject.put(f1007z[120], str4);
            jSONObject.put(f1007z[AVException.OPERATION_FORBIDDEN], F);
            jSONObject.put(f1007z[AVException.INVALID_FILE_NAME], c);
            jSONObject.put(f1007z[AVException.OBJECT_TOO_LARGE], str);
        } catch (JSONException e) {
        }
        return jSONObject.toString();
    }

    /* renamed from: b */
    public static String m1686b(String str) {
        try {
            byte[] digest = MessageDigest.getInstance(f1007z[73]).digest(str.getBytes(f1007z[83]));
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b : digest) {
                int i = b & 255;
                if (i < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            ac.m1581b();
            return "";
        }
    }

    /* renamed from: b */
    public static void m1687b() {
        try {
            WakeLock b = C0481t.m1538a().m1540b();
            if (b == null) {
                return;
            }
            if (b.isHeld()) {
                try {
                    b.release();
                    long currentTimeMillis = System.currentTimeMillis() - f1002d;
                    f1002d = 0;
                    new StringBuilder(f1007z[41]).append(currentTimeMillis);
                    ac.m1576a();
                    return;
                } catch (RuntimeException e) {
                    ac.m1592h();
                    return;
                }
            }
            ac.m1576a();
        } catch (IllegalStateException e2) {
            e2.printStackTrace();
            ac.m1581b();
        } catch (Exception e3) {
            e3.printStackTrace();
            ac.m1581b();
        }
    }

    /* renamed from: b */
    public static void m1688b(Context context, C0429c c0429c) {
        try {
            Intent intent = new Intent(f1007z[88]);
            intent.putExtra(f1007z[91], c0429c.f624n);
            intent.putExtra(f1007z[92], c0429c.f619i);
            intent.putExtra(f1007z[85], c0429c.f620j);
            intent.putExtra(f1007z[84], c0429c.f621k);
            intent.putExtra(f1007z[65], c0429c.f622l);
            intent.putExtra(f1007z[89], c0429c.f613c);
            if (c0429c.m1279e()) {
                intent.putExtra(f1007z[90], c0429c.f609C);
            }
            intent.addCategory(c0429c.f623m);
            context.sendBroadcast(intent, String.format(f1007z[87], new Object[]{c0429c.f623m}));
            new StringBuilder(f1007z[86]).append(String.format(f1007z[87], new Object[]{c0429c.f623m}));
            ac.m1584c();
            C0459i.m1415a(c0429c.f613c, 1018, context);
        } catch (Exception e) {
            e.getMessage();
            ac.m1588e();
        }
    }

    /* renamed from: b */
    public static void m1689b(Context context, String str, String str2) {
        C0490b.m1677a(context, str, f1007z[65], str2);
    }

    /* renamed from: b */
    public static void m1690b(Context context, String str, String str2, int i) {
        if (C0490b.m1713g(context)) {
            int i2;
            Object obj;
            Object obj2;
            Notification notification;
            ac.m1581b();
            Intent intent = new Intent(context, PushReceiver.class);
            intent.setAction(f1007z[30]);
            intent.putExtra(f1007z[31], true);
            if (-1 == i) {
                intent.putExtra(f1007z[37], str2);
            } else {
                intent.putExtra(f1007z[34], str2);
            }
            intent.putExtra(f1007z[33], i);
            PendingIntent broadcast = PendingIntent.getBroadcast(context, 0, intent, 134217728);
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(f1007z[39]);
            int identifier = context.getApplicationContext().getResources().getIdentifier(f1007z[32], f1007z[40], context.getApplicationContext().getPackageName());
            if (identifier == 0) {
                try {
                    i2 = context.getPackageManager().getApplicationInfo(context.getApplicationContext().getPackageName(), 0).icon;
                } catch (Throwable e) {
                    ac.m1583b(f1007z[6], f1007z[29], e);
                    i2 = 17301586;
                }
            } else {
                i2 = identifier;
            }
            if (-1 == i) {
                obj = f1007z[38];
                obj2 = f1007z[36];
            } else {
                obj = f1007z[28];
                obj2 = f1007z[35];
            }
            long currentTimeMillis = System.currentTimeMillis();
            if (VERSION.SDK_INT >= 11) {
                notification = new Builder(context.getApplicationContext()).setContentTitle(obj).setContentText(obj2).setContentIntent(broadcast).setSmallIcon(i2).setTicker(str).setWhen(currentTimeMillis).getNotification();
                notification.flags = 34;
            } else {
                Notification notification2 = new Notification(i2, str, currentTimeMillis);
                notification2.flags = 34;
                C0417m.m1211a(notification2, context, obj, obj2, broadcast);
                notification = notification2;
            }
            if (notification != null) {
                notificationManager.notify(str.hashCode(), notification);
            }
        }
    }

    /* renamed from: b */
    public static boolean m1691b(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(f1007z[64])).getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    private static boolean m1692b(Context context, Class<?> cls) {
        return !context.getPackageManager().queryIntentServices(new Intent(context, cls), 0).isEmpty();
    }

    /* renamed from: b */
    private static boolean m1693b(Context context, String str, boolean z) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str);
        if (z) {
            intent.addCategory(context.getPackageName());
        }
        return !packageManager.queryIntentServices(intent, 0).isEmpty();
    }

    /* renamed from: c */
    public static int m1694c(String str) {
        String[] split = str.split(f1007z[95]);
        return Integer.parseInt(split[2]) + ((Integer.parseInt(split[0]) << 16) + (Integer.parseInt(split[1]) << 8));
    }

    /* renamed from: c */
    public static String m1695c() {
        StringBuffer stringBuffer = new StringBuffer();
        if (new File(f1007z[171]).exists()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(f1007z[171])));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine == null) {
                        break;
                    } else if (readLine.contains(f1007z[170])) {
                        int indexOf = readLine.indexOf(":");
                        if (indexOf >= 0 && indexOf < readLine.length() - 1) {
                            stringBuffer.append(readLine.substring(indexOf + 1).trim());
                        }
                    }
                }
                bufferedReader.close();
            } catch (IOException e) {
            }
        }
        return stringBuffer.toString();
    }

    /* renamed from: c */
    public static String m1696c(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(f1007z[64])).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return f1007z[63];
            }
            String typeName = activeNetworkInfo.getTypeName();
            String subtypeName = activeNetworkInfo.getSubtypeName();
            return typeName == null ? f1007z[63] : !an.m1657a(subtypeName) ? typeName + "," + subtypeName : typeName;
        } catch (Exception e) {
            e.printStackTrace();
            return f1007z[63];
        }
    }

    /* renamed from: c */
    public static boolean m1697c(Context context, String str) {
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    if (context.getPackageManager().checkPermission(str, context.getPackageName()) == 0) {
                        return true;
                    }
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        throw new IllegalArgumentException(f1007z[99]);
    }

    /* renamed from: c */
    private static boolean m1698c(Context context, String str, String str2) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent(str2);
        intent.setPackage(context.getPackageName());
        for (ResolveInfo resolveInfo : packageManager.queryBroadcastReceivers(intent, 0)) {
            ActivityInfo activityInfo = resolveInfo.activityInfo;
            if (activityInfo != null && activityInfo.name.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: d */
    public static String m1699d() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
            ac.m1586d();
            e.printStackTrace();
        }
        return "";
    }

    /* renamed from: d */
    public static String m1700d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(f1007z[64])).getActiveNetworkInfo();
            return activeNetworkInfo == null ? "" : activeNetworkInfo.getTypeName().toUpperCase(Locale.getDefault());
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: d */
    public static boolean m1701d(Context context, String str) {
        try {
            context.getPackageManager().getReceiverInfo(new ComponentName(context.getPackageName(), str), 128);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /* renamed from: d */
    private static boolean m1702d(String str) {
        if (an.m1657a(str) || str.length() < 10) {
            return false;
        }
        int i = 0;
        while (i < f1000b.size()) {
            if (str.equals(f1000b.get(i)) || str.startsWith((String) f1000b.get(i))) {
                return false;
            }
            i++;
        }
        return true;
    }

    /* renamed from: e */
    private static String m1703e() {
        String str = null;
        try {
            str = Environment.getExternalStorageDirectory().getPath();
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } catch (Exception e2) {
        }
        return !an.m1657a(str) ? str + f1007z[Opcodes.IFLT] : str;
    }

    /* renamed from: e */
    private static String m1704e(String str) {
        FileOutputStream fileOutputStream;
        Throwable th;
        String e = C0490b.m1703e();
        if (an.m1657a(e)) {
            ac.m1588e();
            return null;
        }
        File file = new File(e);
        if (!file.exists()) {
            try {
                file.mkdir();
            } catch (Exception e2) {
                ac.m1593i();
            }
        }
        if (an.m1657a(C0490b.m1709f())) {
            ac.m1589e(f1007z[6], f1007z[51]);
            return null;
        }
        file = new File(e + f1007z[50]);
        if (file.exists()) {
            try {
                file.delete();
            } catch (SecurityException e3) {
                return null;
            }
        }
        try {
            file.createNewFile();
            try {
                fileOutputStream = new FileOutputStream(file);
                try {
                    fileOutputStream.write(str.getBytes());
                    fileOutputStream.flush();
                    ac.m1584c();
                    if (fileOutputStream == null) {
                        return str;
                    }
                    try {
                        fileOutputStream.close();
                        return str;
                    } catch (IOException e4) {
                        return str;
                    }
                } catch (IOException e5) {
                    try {
                        ac.m1593i();
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e6) {
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (fileOutputStream != null) {
                            try {
                                fileOutputStream.close();
                            } catch (IOException e7) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (IOException e8) {
                fileOutputStream = null;
                ac.m1593i();
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                return null;
            } catch (Throwable th3) {
                Throwable th4 = th3;
                fileOutputStream = null;
                th = th4;
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw th;
            }
        } catch (IOException e9) {
            ac.m1593i();
            return null;
        }
    }

    /* renamed from: e */
    public static void m1705e(Context context) {
        C0490b.m1715h(context, null);
    }

    /* renamed from: e */
    public static void m1706e(Context context, String str) {
        Intent intent = new Intent();
        intent.addFlags(268435456);
        intent.setAction(f1007z[57]);
        intent.setDataAndType(Uri.fromFile(new File(str)), f1007z[162]);
        context.startActivity(intent);
    }

    /* renamed from: f */
    public static int m1707f(Context context) {
        Intent intent = null;
        if (context == null) {
            return -1;
        }
        Intent registerReceiver;
        try {
            registerReceiver = context.registerReceiver(null, new IntentFilter(f1007z[98]));
        } catch (SecurityException e) {
            e.printStackTrace();
            registerReceiver = intent;
        } catch (Exception e2) {
            e2.printStackTrace();
            registerReceiver = intent;
        }
        if (registerReceiver == null) {
            return -1;
        }
        int intExtra = registerReceiver.getIntExtra(f1007z[96], -1);
        Object obj = (intExtra == 2 || intExtra == 5) ? 1 : null;
        return obj != null ? registerReceiver.getIntExtra(f1007z[97], -1) : -1;
    }

    /* renamed from: f */
    private static int m1708f(String str) {
        if (an.m1657a(str)) {
            ac.m1586d();
            return 0;
        } else if (Pattern.matches(f1007z[AVException.PUSH_MISCONFIGURED], str)) {
            ac.m1584c();
            return 0;
        } else if (Pattern.matches(f1007z[114], str)) {
            ac.m1584c();
            return 1;
        } else if (!Pattern.matches(f1007z[113], str)) {
            return 0;
        } else {
            ac.m1584c();
            return 2;
        }
    }

    /* renamed from: f */
    private static String m1709f() {
        String e = C0490b.m1703e();
        return e == null ? null : e + f1007z[50];
    }

    /* renamed from: f */
    public static boolean m1710f(Context context, String str) {
        try {
            context.getPackageManager().getApplicationInfo(str, 0);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /* renamed from: g */
    private static String m1711g() {
        String f = C0490b.m1709f();
        if (an.m1657a(f)) {
            ac.m1589e(f1007z[6], f1007z[51]);
            return null;
        }
        File file = new File(f);
        if (file.exists()) {
            try {
                ArrayList a = C0505r.m1788a(new FileInputStream(file));
                if (a.size() > 0) {
                    f = (String) a.get(0);
                    new StringBuilder(f1007z[61]).append(f);
                    ac.m1584c();
                    return f;
                }
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    /* renamed from: g */
    public static String m1712g(Context context, String str) {
        String str2;
        if (VERSION.SDK_INT >= 23) {
            str2 = "";
            try {
                Object readLine = new LineNumberReader(new InputStreamReader(Runtime.getRuntime().exec(f1007z[168]).getInputStream())).readLine();
                if (!TextUtils.isEmpty(readLine)) {
                    str2 = readLine.trim();
                    new StringBuilder(f1007z[167]).append(str2);
                    ac.m1581b();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str2;
        } else if (!C0490b.m1697c(context, f1007z[75])) {
            return str;
        } else {
            try {
                str2 = ((WifiManager) context.getSystemService(f1007z[Opcodes.RET])).getConnectionInfo().getMacAddress();
                return !an.m1657a(str2) ? str2 : str;
            } catch (Exception e2) {
                ac.m1593i();
                return str;
            }
        }
    }

    /* renamed from: g */
    public static boolean m1713g(Context context) {
        boolean z = false;
        try {
            Signature[] signatureArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 64).signatures;
            CertificateFactory instance = CertificateFactory.getInstance(f1007z[53]);
            int i = 0;
            while (i < signatureArr.length) {
                boolean equals = ((X509Certificate) instance.generateCertificate(new ByteArrayInputStream(signatureArr[i].toByteArray()))).getSubjectX500Principal().equals(f1001c);
                if (equals) {
                    return equals;
                }
                i++;
                z = equals;
            }
            return z;
        } catch (NameNotFoundException e) {
            return false;
        } catch (Exception e2) {
            return false;
        }
    }

    /* renamed from: h */
    public static String m1714h(Context context) {
        String i = C0404a.m1114i(context);
        if (!an.m1657a(i) && C0490b.m1702d(i)) {
            return i;
        }
        i = C0490b.m1741x(context);
        C0404a.m1090d(context, i);
        return i;
    }

    /* renamed from: h */
    public static void m1715h(Context context, String str) {
        Intent intent = new Intent(f1007z[68]);
        String packageName = context.getPackageName();
        intent.setPackage(packageName);
        if (!an.m1657a(str)) {
            intent.putExtra(f1007z[65], str);
        }
        intent.addCategory(f1007z[67]);
        ResolveInfo resolveActivity = context.getPackageManager().resolveActivity(intent, 0);
        new StringBuilder(f1007z[66]).append(resolveActivity.activityInfo.name);
        ac.m1584c();
        intent.setClassName(packageName, resolveActivity.activityInfo.name);
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    /* renamed from: i */
    public static String m1716i(Context context) {
        return Secure.getString(context.getContentResolver(), f1007z[Opcodes.IFGE]);
    }

    /* renamed from: i */
    public static String m1717i(Context context, String str) {
        try {
            if (C0490b.m1697c(context, f1007z[69])) {
                str = ((TelephonyManager) context.getSystemService(f1007z[70])).getSimSerialNumber();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /* renamed from: j */
    public static String m1718j(Context context) {
        String str = null;
        String D = C0404a.m1049D();
        if (an.m1657a(D)) {
            D = C0490b.m1730p(context, D);
            if (an.m1657a(D)) {
                if (!C0490b.m1681a()) {
                    ac.m1588e();
                } else if (C0490b.m1697c(context, f1007z[13]) && (VERSION.SDK_INT < 23 || (C0490b.m1697c(context, f1007z[13]) && C0490b.m1697c(context, f1007z[14])))) {
                    str = C0490b.m1711g();
                }
                if (an.m1657a(str)) {
                    str = VERSION.SDK_INT < 23 ? C0490b.m1719j(context, str) : "";
                    String i = C0490b.m1716i(context);
                    String g = C0490b.m1712g(context, "");
                    D = UUID.randomUUID().toString();
                    str = C0490b.m1671a(str + i + g + D);
                    if (an.m1657a(str)) {
                        str = D;
                    }
                    C0404a.m1107g(str);
                    f999a = C0491c.f1008a - 1;
                    C0490b.m1733q(context, str);
                    C0490b.m1728o(context, str);
                    return str;
                }
                f999a = C0491c.f1010c - 1;
                C0490b.m1733q(context, str);
                C0404a.m1107g(str);
                return str;
            }
            f999a = C0491c.f1009b - 1;
            C0490b.m1728o(context, D);
            C0404a.m1107g(D);
            return D;
        }
        f999a = C0491c.f1011d - 1;
        return D;
    }

    /* renamed from: j */
    public static String m1719j(Context context, String str) {
        try {
            if (C0490b.m1697c(context, f1007z[69])) {
                str = ((TelephonyManager) context.getSystemService(f1007z[70])).getDeviceId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /* renamed from: k */
    public static String m1720k(Context context, String str) {
        try {
            if (C0490b.m1697c(context, f1007z[69])) {
                str = ((TelephonyManager) context.getSystemService(f1007z[70])).getSubscriberId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /* renamed from: k */
    public static void m1721k(Context context) {
        try {
            WakeLock newWakeLock = ((PowerManager) context.getSystemService(f1007z[Opcodes.IF_ICMPGT])).newWakeLock(1, C0448e.f751c + f1007z[Opcodes.IF_ICMPLE]);
            newWakeLock.setReferenceCounted(false);
            C0481t.m1538a().m1539a(newWakeLock);
            if (C0481t.m1538a().m1540b().isHeld()) {
                ac.m1576a();
                return;
            }
            C0481t.m1538a().m1540b().acquire();
            f1002d = System.currentTimeMillis();
            ac.m1576a();
        } catch (IllegalStateException e) {
            e.printStackTrace();
            ac.m1581b();
        } catch (Exception e2) {
            e2.printStackTrace();
            ac.m1581b();
        }
    }

    /* renamed from: l */
    public static JSONArray m1722l(Context context) {
        ArrayList a = C0508v.m1809a(context, true);
        JSONArray jSONArray = new JSONArray();
        try {
            Iterator it = a.iterator();
            while (it.hasNext()) {
                ad adVar = (ad) it.next();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put(f1007z[21], adVar.f948a);
                jSONObject.put(f1007z[22], adVar.f949b);
                jSONObject.put(f1007z[19], adVar.f950c);
                jSONObject.put(f1007z[20], adVar.f951d);
                jSONArray.put(jSONObject);
            }
        } catch (JSONException e) {
        }
        return jSONArray;
    }

    /* renamed from: l */
    public static void m1723l(Context context, String str) {
        if (!an.m1657a(str)) {
            C0490b.m1733q(context, str);
            C0490b.m1728o(context, str);
            C0404a.m1107g(str);
        }
    }

    /* renamed from: m */
    public static boolean m1724m(Context context) {
        try {
            if (C0404a.m1108g(context)) {
                String f = C0404a.m1101f(context);
                if (an.m1657a(f)) {
                    ac.m1584c();
                    return true;
                }
                new StringBuilder(f1007z[24]).append(f);
                ac.m1584c();
                String[] split = f.split("_");
                String str = split[0];
                String str2 = split[1];
                char[] toCharArray = str.toCharArray();
                String[] split2 = str2.split(f1007z[27]);
                Calendar instance = Calendar.getInstance();
                int i = instance.get(7);
                int i2 = instance.get(11);
                for (char valueOf : toCharArray) {
                    if (i == Integer.valueOf(String.valueOf(valueOf)).intValue() + 1) {
                        int intValue = Integer.valueOf(split2[0]).intValue();
                        int intValue2 = Integer.valueOf(split2[1]).intValue();
                        if (i2 >= intValue && i2 <= intValue2) {
                            return true;
                        }
                    }
                }
                ac.m1585c(f1007z[6], new StringBuilder(f1007z[26]).append(f).toString());
                return false;
            }
            ac.m1585c(f1007z[6], f1007z[25]);
            return false;
        } catch (Exception e) {
            return true;
        }
    }

    /* renamed from: m */
    private static boolean m1725m(Context context, String str) {
        if (context == null || TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException(f1007z[99]);
        }
        try {
            context.getPackageManager().getPermissionInfo(str, 128);
            return true;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /* renamed from: n */
    private static Intent m1726n(Context context, String str) {
        try {
            PackageManager packageManager = context.getPackageManager();
            if (packageManager.getPackageInfo(str, 256) != null) {
                return packageManager.getLaunchIntentForPackage(str);
            }
        } catch (NameNotFoundException e) {
        }
        return null;
    }

    /* renamed from: n */
    public static boolean m1727n(Context context) {
        ac.m1581b();
        Object e = C0404a.m1096e(context);
        if (TextUtils.isEmpty(e)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(e);
            int optInt = jSONObject.optInt(f1007z[2], -1);
            int optInt2 = jSONObject.optInt(f1007z[1], -1);
            int optInt3 = jSONObject.optInt(f1007z[0], -1);
            int optInt4 = jSONObject.optInt(f1007z[8], -1);
            if (optInt < 0 || optInt2 < 0 || optInt3 < 0 || optInt4 < 0 || optInt2 > 59 || optInt4 > 59 || optInt3 > 23 || optInt > 23) {
                return false;
            }
            Calendar instance = Calendar.getInstance();
            int i = instance.get(11);
            int i2 = instance.get(12);
            new StringBuilder(f1007z[4]).append(i).append(f1007z[3]).append(i2).append(f1007z[9]).append(optInt).append(f1007z[12]).append(optInt2).append(f1007z[11]).append(optInt3).append(f1007z[7]).append(optInt4);
            ac.m1576a();
            if (optInt < optInt3) {
                if ((i <= optInt || i >= optInt3) && ((i != optInt || i2 < optInt2) && (i != optInt3 || i2 > optInt4))) {
                    return false;
                }
            } else if (optInt == optInt3) {
                if (optInt2 >= optInt4) {
                    if (i == optInt && i2 > optInt4 && i2 < optInt2) {
                        return false;
                    }
                } else if (i != optInt || i2 < optInt2) {
                    return false;
                } else {
                    if (i2 > optInt4) {
                        return false;
                    }
                }
            } else if (optInt <= optInt3) {
                return false;
            } else {
                if ((i <= optInt || i > 23) && ((i < 0 || i >= optInt3) && (i != optInt || i2 < optInt2))) {
                    if (i != optInt3) {
                        return false;
                    }
                    if (i2 > optInt4) {
                        return false;
                    }
                }
            }
            ac.m1585c(f1007z[6], new StringBuilder(f1007z[10]).append(optInt).append(":").append(optInt2).append(f1007z[5]).append(optInt3).append(":").append(optInt4).toString());
            return true;
        } catch (JSONException e2) {
            return false;
        }
    }

    /* renamed from: o */
    private static String m1728o(Context context, String str) {
        return (C0490b.m1681a() && C0490b.m1697c(context, f1007z[13])) ? VERSION.SDK_INT < 23 ? C0490b.m1704e(str) : (C0490b.m1697c(context, f1007z[13]) && C0490b.m1697c(context, f1007z[14])) ? C0490b.m1704e(str) : null : null;
    }

    /* renamed from: o */
    public static boolean m1729o(Context context) {
        boolean z;
        boolean z2;
        ac.m1582b(f1007z[6], f1007z[153]);
        if (C0490b.m1692b(context, PushService.class)) {
            if (C0490b.m1735r(context, PushService.class.getCanonicalName())) {
                ac.m1576a();
                C0448e.f762n = true;
            } else {
                ac.m1576a();
                C0448e.f762n = false;
            }
            if (!C0490b.m1693b(context, f1007z[Opcodes.IFNE], false)) {
                ac.m1589e(f1007z[6], f1007z[135]);
                z = false;
            } else if (C0490b.m1693b(context, f1007z[AVException.VALIDATION_ERROR], false)) {
                if (!C0490b.m1692b(context, DaemonService.class)) {
                    ac.m1587d(f1007z[6], new StringBuilder(f1007z[AVException.INVALID_ROLE_NAME]).append(DaemonService.class.getCanonicalName()).toString());
                    C0448e.f761m = false;
                } else if (C0490b.m1693b(context, f1007z[71], true)) {
                    C0448e.f761m = true;
                } else {
                    ac.m1587d(f1007z[6], f1007z[AVException.SCRIPT_ERROR]);
                    C0448e.f761m = false;
                }
                if (!C0490b.m1692b(context, DownloadService.class)) {
                    ac.m1587d(f1007z[6], new StringBuilder(f1007z[AVException.INVALID_ROLE_NAME]).append(DownloadService.class.getCanonicalName()).toString());
                }
                if (C0490b.m1682a(context, PushReceiver.class)) {
                    if (C0490b.m1698c(context, PushReceiver.class.getCanonicalName(), f1007z[SyslogConstants.LOG_LOCAL1])) {
                        ac.m1587d(f1007z[6], f1007z[AVException.DUPLICATE_VALUE]);
                    }
                    if (!C0490b.m1684a(context, f1007z[79], true)) {
                        ac.m1589e(f1007z[6], f1007z[AVException.EXCEEDED_QUOTA]);
                        z = false;
                    } else if (C0490b.m1682a(context, AlarmReceiver.class)) {
                        if (!context.getPackageManager().queryIntentActivities(new Intent(context, PushActivity.class), 0).isEmpty()) {
                            String str = f1007z[112];
                            PackageManager packageManager = context.getPackageManager();
                            Intent intent = new Intent(str);
                            intent.addCategory(context.getPackageName());
                            if (!packageManager.queryIntentActivities(intent, 0).isEmpty()) {
                                str = context.getPackageName() + f1007z[Opcodes.I2B];
                                if (C0490b.m1725m(context, str)) {
                                    f1003e.add(str);
                                    Iterator it = f1003e.iterator();
                                    while (it.hasNext()) {
                                        str = (String) it.next();
                                        if (!C0490b.m1697c(context.getApplicationContext(), str)) {
                                            ac.m1589e(f1007z[6], new StringBuilder(f1007z[143]).append(str).toString());
                                            z = false;
                                            break;
                                        }
                                    }
                                    if (VERSION.SDK_INT < 23) {
                                        if (!C0490b.m1697c(context.getApplicationContext(), f1007z[13])) {
                                            ac.m1589e(f1007z[6], f1007z[SyslogConstants.LOG_LOCAL2]);
                                            z = false;
                                        } else if (!C0490b.m1697c(context.getApplicationContext(), f1007z[94])) {
                                            ac.m1589e(f1007z[6], f1007z[152]);
                                            z = false;
                                        }
                                    }
                                    it = f1004f.iterator();
                                    while (it.hasNext()) {
                                        str = (String) it.next();
                                        if (!C0490b.m1697c(context.getApplicationContext(), str)) {
                                            new StringBuilder(f1007z[Opcodes.LCMP]).append(str);
                                            ac.m1586d();
                                        }
                                    }
                                    it = f1005g.iterator();
                                    while (it.hasNext()) {
                                        str = (String) it.next();
                                        if (!C0490b.m1697c(context.getApplicationContext(), str)) {
                                            new StringBuilder(f1007z[Opcodes.LCMP]).append(str).append(f1007z[Opcodes.I2S]);
                                            ac.m1586d();
                                        }
                                    }
                                } else {
                                    ac.m1589e(f1007z[6], new StringBuilder(f1007z[138]).append(str).toString());
                                    z = false;
                                }
                            } else {
                                ac.m1589e(f1007z[6], f1007z[Opcodes.FCMPL]);
                                z = false;
                            }
                        } else {
                            ac.m1589e(f1007z[6], new StringBuilder(f1007z[146]).append(PushActivity.class.getCanonicalName()).toString());
                            z = false;
                        }
                    } else {
                        ac.m1589e(f1007z[6], new StringBuilder(f1007z[150]).append(AlarmReceiver.class.getCanonicalName()).toString());
                        z = false;
                    }
                } else {
                    ac.m1589e(f1007z[6], new StringBuilder(f1007z[150]).append(PushReceiver.class.getCanonicalName()).toString());
                    C0490b.m1664C(context);
                }
                z = true;
            } else {
                ac.m1589e(f1007z[6], f1007z[134]);
                z = false;
            }
        } else {
            ac.m1589e(f1007z[6], new StringBuilder(f1007z[AVException.INVALID_ROLE_NAME]).append(PushService.class.getCanonicalName()).toString());
            z = false;
        }
        if (!an.m1657a(C0404a.m1050E())) {
            z2 = true;
        } else if (an.m1657a(C0448e.f754f)) {
            ac.m1587d(f1007z[6], f1007z[133]);
            z2 = false;
        } else {
            C0404a.m1112h(C0448e.f754f);
            z2 = true;
        }
        if (!(C0448e.f759k || C0490b.m1663B(context))) {
            ac.m1587d(f1007z[6], f1007z[Opcodes.DCMPL]);
        }
        return z2 && z;
    }

    /* renamed from: p */
    private static String m1730p(Context context, String str) {
        if (C0490b.m1697c(context, f1007z[94])) {
            try {
                str = System.getString(context.getContentResolver(), f1007z[93]);
            } catch (Exception e) {
                ac.m1588e();
            }
        }
        return str;
    }

    /* renamed from: p */
    public static void m1731p(Context context) {
        if (f1006h != null && !C0490b.m1701d(context, PushReceiver.class.getCanonicalName())) {
            try {
                context.unregisterReceiver(f1006h);
            } catch (Exception e) {
                e.getMessage();
                ac.m1588e();
            }
        }
    }

    /* renamed from: q */
    public static String m1732q(Context context) {
        String str = C0448e.f754f;
        if (an.m1657a(str)) {
            try {
                ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (!(applicationInfo == null || applicationInfo.metaData == null)) {
                    str = applicationInfo.metaData.getString(f1007z[44]);
                }
            } catch (NameNotFoundException e) {
            }
        }
        return str;
    }

    /* renamed from: q */
    private static String m1733q(Context context, String str) {
        if (C0490b.m1697c(context, f1007z[94])) {
            try {
                if (System.putString(context.getContentResolver(), f1007z[93], str)) {
                    return str;
                }
            } catch (Exception e) {
                ac.m1588e();
            }
        }
        return null;
    }

    /* renamed from: r */
    public static String m1734r(Context context) {
        String str = "";
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(f1007z[64])).getActiveNetworkInfo();
            if (activeNetworkInfo == null) {
                return f1007z[18];
            }
            if (activeNetworkInfo.getType() == 1) {
                return f1007z[Opcodes.RET];
            }
            if (activeNetworkInfo.getType() == 0) {
                int subtype = activeNetworkInfo.getSubtype();
                if (subtype == 4 || subtype == 1 || subtype == 2) {
                    return f1007z[Opcodes.IRETURN];
                }
                if (subtype == 3 || subtype == 8 || subtype == 6 || subtype == 5 || subtype == 12) {
                    return f1007z[173];
                }
                if (subtype == 13) {
                    return f1007z[174];
                }
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: r */
    private static boolean m1735r(Context context, String str) {
        try {
            ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context.getPackageName(), str), 128);
            new StringBuilder(f1007z[107]).append(serviceInfo.processName);
            ac.m1576a();
            if (serviceInfo.processName.contains(context.getPackageName() + ":")) {
                return true;
            }
        } catch (NameNotFoundException e) {
        } catch (NullPointerException e2) {
            new StringBuilder(f1007z[106]).append(str);
            ac.m1576a();
        }
        return false;
    }

    /* renamed from: s */
    public static void m1736s(Context context) {
        if (!C0404a.m1052G()) {
            String j = C0490b.m1719j(context, "");
            String H = C0404a.m1053H();
            String i = C0490b.m1716i(context);
            if (an.m1657a(i)) {
                i = " ";
            }
            String g = C0490b.m1712g(context, "");
            if (an.m1657a(g)) {
                g = " ";
            }
            int f = C0490b.m1708f(H);
            int f2 = C0490b.m1708f(j);
            if (f == 0 || f2 == 0) {
                if (C0404a.m1081b(i, g)) {
                    return;
                }
            } else if (1 != f || 2 != f2) {
                if (2 != f || 1 != f2) {
                    if (f == f2) {
                        if (j.equals(H)) {
                            if (C0404a.m1072a(j, i)) {
                                return;
                            }
                        } else if (C0404a.m1081b(i, g)) {
                            return;
                        }
                    }
                }
                return;
            } else {
                return;
            }
            ac.m1581b();
            C0404a.m1146z();
            C0490b.m1733q(context, "");
            C0490b.m1728o(context, "");
        }
    }

    /* renamed from: t */
    public static List<ComponentName> m1737t(Context context) {
        ac.m1581b();
        List<ComponentName> arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(f1007z[71]);
        List queryIntentServices = packageManager.queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.size() == 0) {
            return null;
        }
        for (int i = 0; i < queryIntentServices.size(); i++) {
            ServiceInfo serviceInfo = ((ResolveInfo) queryIntentServices.get(i)).serviceInfo;
            String str = serviceInfo.name;
            String str2 = serviceInfo.packageName;
            if (serviceInfo.exported && serviceInfo.enabled && !C0448e.f751c.equals(str2)) {
                new StringBuilder(f1007z[72]).append(str2).append("/").append(str).append(C0880h.f2222d);
                ac.m1581b();
                arrayList.add(new ComponentName(str2, str));
            }
        }
        return arrayList;
    }

    /* renamed from: u */
    public static boolean m1738u(Context context) {
        CharSequence a = C0480s.m1536a(context, f1007z[104]);
        new StringBuilder(f1007z[101]).append(a);
        ac.m1581b();
        CharSequence a2 = C0480s.m1536a(context, f1007z[100]);
        if (!(TextUtils.isEmpty(a) || !f1007z[102].equals(a) || TextUtils.isEmpty(a2))) {
            Object a3 = C0480s.m1536a(context, f1007z[105]);
            if (!TextUtils.isEmpty(a3) && a3.startsWith(f1007z[103])) {
                ac.m1586d();
                return false;
            }
        }
        return true;
    }

    /* renamed from: v */
    public static double m1739v(Context context) {
        double pow;
        double pow2;
        Point point = new Point();
        if (context instanceof Activity) {
            Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
            if (VERSION.SDK_INT >= 17) {
                defaultDisplay.getRealSize(point);
            } else if (VERSION.SDK_INT >= 13) {
                defaultDisplay.getSize(point);
            } else {
                point.x = defaultDisplay.getWidth();
                point.y = defaultDisplay.getHeight();
            }
            new StringBuilder(f1007z[TransportMediator.KEYCODE_MEDIA_RECORD]).append(point.x).append(f1007z[127]).append(point.y);
            ac.m1581b();
        }
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        new StringBuilder(f1007z[129]).append(displayMetrics.xdpi).append(f1007z[128]).append(displayMetrics.ydpi);
        ac.m1581b();
        if (context instanceof Activity) {
            pow = Math.pow((double) (((float) point.x) / displayMetrics.xdpi), 2.0d);
            pow2 = Math.pow((double) (((float) point.y) / displayMetrics.ydpi), 2.0d);
        } else {
            new StringBuilder(f1007z[AVException.INVALID_EMAIL_ADDRESS]).append(displayMetrics.widthPixels).append(f1007z[126]).append(displayMetrics.heightPixels);
            ac.m1581b();
            pow = Math.pow((double) (((float) displayMetrics.widthPixels) / displayMetrics.xdpi), 2.0d);
            pow2 = Math.pow((double) (((float) displayMetrics.heightPixels) / displayMetrics.ydpi), 2.0d);
        }
        pow2 = Math.sqrt(pow2 + pow);
        new StringBuilder(f1007z[AVException.TIMEOUT]).append(pow2);
        ac.m1581b();
        return pow2;
    }

    /* renamed from: w */
    private static String m1740w(Context context) {
        String str = null;
        if (C0490b.m1697c(context, f1007z[75])) {
            try {
                String g = C0490b.m1712g(context, "");
                if (!(g == null || g.equals(""))) {
                    new StringBuilder(f1007z[74]).append(g);
                    ac.m1584c();
                    str = C0490b.m1671a(g + Build.MODEL);
                }
            } catch (Exception e) {
                ac.m1593i();
            }
        }
        return str;
    }

    /* renamed from: x */
    private static String m1741x(Context context) {
        try {
            String j = C0490b.m1719j(context, "");
            if (C0490b.m1702d(j)) {
                return j;
            }
            j = C0490b.m1716i(context);
            if (!an.m1657a(j) && C0490b.m1702d(j) && !f1007z[23].equals(j.toLowerCase(Locale.getDefault()))) {
                return j;
            }
            j = C0490b.m1740w(context);
            if (!an.m1657a(j) && C0490b.m1702d(j)) {
                return j;
            }
            j = C0490b.m1742y(context);
            return j == null ? " " : j;
        } catch (Exception e) {
            ac.m1593i();
            return C0490b.m1742y(context);
        }
    }

    /* renamed from: y */
    private static String m1742y(Context context) {
        ac.m1581b();
        String string = context.getSharedPreferences(f1007z[Opcodes.IINC], 0).getString(f1007z[131], null);
        if (!an.m1657a(string)) {
            return string;
        }
        if (!C0490b.m1681a()) {
            return C0490b.m1662A(context);
        }
        string = C0404a.m1110h(context);
        return string == null ? (VERSION.SDK_INT < 23 || (C0490b.m1697c(context, f1007z[13]) && C0490b.m1697c(context, f1007z[14]))) ? C0490b.m1743z(context) : C0490b.m1662A(context) : string;
    }

    /* renamed from: z */
    private static String m1743z(Context context) {
        FileOutputStream fileOutputStream;
        Throwable th;
        FileOutputStream fileOutputStream2 = null;
        String e = C0490b.m1703e();
        e = e == null ? null : e + f1007z[43];
        File file = !an.m1657a(e) ? new File(e) : null;
        if (file != null) {
            try {
                if (file.exists()) {
                    ArrayList a = C0505r.m1788a(new FileInputStream(file));
                    if (a.size() > 0) {
                        e = (String) a.get(0);
                        C0404a.m1086c(context, e);
                        new StringBuilder(f1007z[42]).append(e);
                        ac.m1584c();
                        return e;
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        e = an.m1659b(UUID.nameUUIDFromBytes((System.currentTimeMillis()).getBytes()).toString());
        C0404a.m1086c(context, e);
        if (file != null) {
            try {
                file.createNewFile();
                try {
                    fileOutputStream = new FileOutputStream(file);
                    try {
                        fileOutputStream.write(e.getBytes());
                        fileOutputStream.flush();
                        ac.m1584c();
                        if (fileOutputStream == null) {
                            return e;
                        }
                        try {
                            fileOutputStream.close();
                            return e;
                        } catch (IOException e3) {
                            return e;
                        }
                    } catch (IOException e4) {
                        fileOutputStream2 = fileOutputStream;
                        try {
                            ac.m1593i();
                            if (fileOutputStream2 != null) {
                                return e;
                            }
                            try {
                                fileOutputStream2.close();
                                return e;
                            } catch (IOException e5) {
                                return e;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                            fileOutputStream = fileOutputStream2;
                            if (fileOutputStream != null) {
                                try {
                                    fileOutputStream.close();
                                } catch (IOException e6) {
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        throw th;
                    }
                } catch (IOException e7) {
                    ac.m1593i();
                    if (fileOutputStream2 != null) {
                        return e;
                    }
                    fileOutputStream2.close();
                    return e;
                } catch (Throwable th4) {
                    th = th4;
                    fileOutputStream = null;
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    throw th;
                }
            } catch (IOException e8) {
                ac.m1593i();
                return e;
            }
        }
        ac.m1588e();
        return e;
    }
}
