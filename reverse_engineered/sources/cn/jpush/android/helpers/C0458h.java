package cn.jpush.android.helpers;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import cn.jpush.android.C0404a;
import cn.jpush.android.C0448e;
import cn.jpush.android.api.C0408d;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0430a;
import cn.jpush.android.data.C0432d;
import cn.jpush.android.service.PushProtocol;
import cn.jpush.android.service.ServiceInterface;
import cn.jpush.android.util.C0403i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.aj;
import cn.jpush.android.util.an;
import cn.jpush.android.util.ao;
import cn.jpush.p005b.p006a.p007a.C0514i;
import cn.jpush.p005b.p006a.p007a.C0516b;
import cn.jpush.p005b.p006a.p007a.C0523k;
import cn.jpush.p005b.p006a.p007a.C0526n;
import com.google.gson.jpush.C3975w;
import com.google.gson.jpush.C4054z;
import com.google.gson.jpush.ab;
import com.google.gson.jpush.af;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.StringReader;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.helpers.h */
public final class C0458h {
    /* renamed from: a */
    private static Queue<C0432d> f781a = new ConcurrentLinkedQueue();
    /* renamed from: b */
    private static ab f782b = new ab();
    /* renamed from: z */
    private static final String[] f783z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 68;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0005\u0015\u0017\u001bb\u0018\u0013\u0003";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
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
            case 0: goto L_0x0317;
            case 1: goto L_0x031b;
            case 2: goto L_0x031f;
            case 3: goto L_0x0323;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 7;
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
        goto L_0x0017;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0012;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            case 5: goto L_0x006b;
            case 6: goto L_0x0073;
            case 7: goto L_0x007c;
            case 8: goto L_0x0086;
            case 9: goto L_0x0091;
            case 10: goto L_0x009c;
            case 11: goto L_0x00a7;
            case 12: goto L_0x00b2;
            case 13: goto L_0x00bd;
            case 14: goto L_0x00c8;
            case 15: goto L_0x00d3;
            case 16: goto L_0x00de;
            case 17: goto L_0x00e9;
            case 18: goto L_0x00f4;
            case 19: goto L_0x00ff;
            case 20: goto L_0x010a;
            case 21: goto L_0x0115;
            case 22: goto L_0x0120;
            case 23: goto L_0x012b;
            case 24: goto L_0x0136;
            case 25: goto L_0x0141;
            case 26: goto L_0x014c;
            case 27: goto L_0x0157;
            case 28: goto L_0x0162;
            case 29: goto L_0x016d;
            case 30: goto L_0x0178;
            case 31: goto L_0x0183;
            case 32: goto L_0x018e;
            case 33: goto L_0x0199;
            case 34: goto L_0x01a4;
            case 35: goto L_0x01af;
            case 36: goto L_0x01ba;
            case 37: goto L_0x01c5;
            case 38: goto L_0x01d0;
            case 39: goto L_0x01db;
            case 40: goto L_0x01e6;
            case 41: goto L_0x01f1;
            case 42: goto L_0x01fc;
            case 43: goto L_0x0207;
            case 44: goto L_0x0212;
            case 45: goto L_0x021d;
            case 46: goto L_0x0228;
            case 47: goto L_0x0233;
            case 48: goto L_0x023e;
            case 49: goto L_0x0249;
            case 50: goto L_0x0254;
            case 51: goto L_0x025f;
            case 52: goto L_0x026a;
            case 53: goto L_0x0275;
            case 54: goto L_0x0280;
            case 55: goto L_0x028b;
            case 56: goto L_0x0296;
            case 57: goto L_0x02a1;
            case 58: goto L_0x02ac;
            case 59: goto L_0x02b7;
            case 60: goto L_0x02c2;
            case 61: goto L_0x02cd;
            case 62: goto L_0x02d8;
            case 63: goto L_0x02e3;
            case 64: goto L_0x02ee;
            case 65: goto L_0x02f9;
            case 66: goto L_0x0304;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u001c\u0003\t\u0000D\u0019\u001e\u0012\u000bi\u0002J";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "ZP\u0005\u0001j\u0006\u001f\b\u000bi\u0002>\u0007\u0003bL";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "#\u001e\u0003\u0016w\u0013\u0013\u0012\u000bcLP\u0013\u0000l\u0018\u001f\u0011\u0000'\u0002\t\u0016\u000b=";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0010\u0011\u000f\u0002b\u0012P\u0012\u0001'\u0005\u0004\u0007\u001csV\u0007\u000f\u001aoV\u0019\b\u001ab\u0018\u0004\\";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0003\u001e\u0003\u0016w\u0013\u0013\u0012\u000bcW";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0006\u001b\u0001 f\u001b\u0015";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006b:
        r3[r2] = r1;
        r2 = 7;
        r1 = "&\u0005\u0015\u0006J\u0013\u0003\u0015\u000f`\u0013 \u0014\u0001d\u0013\u0003\u0015\u0001u";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0073:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0005\u0015\u0014\u0018n\u0015\u0015(\u000fj\u0013";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007c:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0015\u001f\b\u001ab\u0018\u0004";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0086:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0003\u001e\u0003\u0016w\u0013\u0013\u0012\u000bcWP\u000e\u000ftV\u0007\u0014\u0001i\u0011P\u0011\u0007s\u001eP,=H85\u001e\rb\u0006\u0004\u000f\u0001i";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0091:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0002\t\u0016\u000b";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009c:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u0005\u0005\u0005\rb\u0013\u0014F\u001ahV\u0003\u0012\u000fu\u0002P\u0011\u0007s\u001eP\u000f\u0000s\u0013\u001e\u0012T";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a7:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u0005\u0004\u0007\u001cs\"\t\u0016\u000b=";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b2:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u001b\u0003\u0001-h\u0018\u0004\u0003\u0000sLPl";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00bd:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\"\u0019\u000b\u000b'\u0002\u001fF\u001eu\u0019\u0013\u0003\u001dtV\u0002\u0003\rb\u001f\u0006\u0003\n'\u001b\u0003\u0001@";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c8:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u0017\u0013\u0012\u0007h\u0018J\u0014\u000bd\u0013\u0019\u0010\u000bc&\u0005\u0015\u0006J\u0013\u0003\u0015\u000f`\u0013P\u000b\u001d`?\u0014FS'";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d3:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0015\u001eH\u0004w\u0003\u0003\u000e@f\u0018\u0014\u0014\u0001n\u0012^\u000f\u0000s\u0013\u001e\u0012@I9$/(N512'H8/4+D390+C) 4!_/";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00de:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\u0015\u001eH\u0004w\u0003\u0003\u000e@f\u0018\u0014\u0014\u0001n\u0012^\u000f\u0000s\u0013\u001e\u0012@S779/K?151D7<*,F5;";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00e9:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u001b\u0003\u00011n\u0012";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f4:
        r3[r2] = r1;
        r2 = 20;
        r1 = "\u0015\u001f\u0002\u000b";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x00ff:
        r3[r2] = r1;
        r2 = 21;
        r1 = "ZP\u000b\u001d`?\u0014FS'";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010a:
        r3[r2] = r1;
        r2 = 22;
        r1 = ";\u0015\u0015\u001df\u0011\u0015F(n\u0013\u001c\u0002\u001d'[P\u0007\u001ew?\u0014\\";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0115:
        r3[r2] = r1;
        r2 = 23;
        r1 = "%\u0005\u0005\rb\u0013\u0014F\u001ahV\u0002\u0003\u001eh\u0004\u0004F\u001cb\u0015\u0015\u000f\u0018b\u0012PKN";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0120:
        r3[r2] = r1;
        r2 = 24;
        r1 = "#\u001e\u0003\u0016w\u0013\u0013\u0012\u000bcLP\u0013\u0000l\u0018\u001f\u0011\u0000'\u0006\u0005\u0015\u0006'\u001b\u0003\u0001Ns\u000f\u0000\u0003N*";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012b:
        r3[r2] = r1;
        r2 = 25;
        r1 = "2\u0005\u0016\u0002n\u0015\u0011\u0012\u000bcV\u001d\u0015\t)V7\u000f\u0018bV\u0005\u0016Nw\u0004\u001f\u0005\u000bt\u0005\u0019\b\t'[P";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0136:
        r3[r2] = r1;
        r2 = 26;
        r1 = "\u0006\u0002\t\rb\u0005\u0003$\u000ft\u001f\u0013#\u0000s\u001f\u0004\u001fNs\u000f\u0000\u0003T";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0141:
        r3[r2] = r1;
        r2 = 27;
        r1 = "X\u0000\u0003\u001cj\u001f\u0003\u0015\u0007h\u0018^,>R%89#B%#')B";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014c:
        r3[r2] = r1;
        r2 = 28;
        r1 = "\u0002\u0011\u0001\u000fk\u001f\u0011\u00151t\u0013\u0001\u000f\n";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0157:
        r3[r2] = r1;
        r2 = 29;
        r1 = "ZP\u0015\u000bi\u0012\u0015\u0014'cL";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0162:
        r3[r2] = r1;
        r2 = 30;
        r1 = "A\u0016\u0003\b1\u0017G\u0002Y1\u0015G^\\eG\u0016V\u000bc\u0017DRXeD\u0013P\r3F\u0011";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016d:
        r3[r2] = r1;
        r2 = 31;
        r1 = "\u0005\u0015\b\nb\u00049\u0002";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0178:
        r3[r2] = r1;
        r2 = 32;
        r1 = "\u0006\u0011\u0014\u001db8\u001f\u0014\u0003f\u001aPKN'\u001b\u0003\u0001'cL";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0183:
        r3[r2] = r1;
        r2 = 33;
        r1 = "\u0017\u0000\u0016'c";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018e:
        r3[r2] = r1;
        r2 = 34;
        r1 = "\u0002\u0011\u0001\u000fk\u001f\u0011\u0015Nj\u0005\u0017%\u0001i\u0002\u0015\b\u001a=";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x0199:
        r3[r2] = r1;
        r2 = 35;
        r1 = "ZP\u000b\u001d`5\u001f\b\u001ab\u0018\u0004\\";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a4:
        r3[r2] = r1;
        r2 = 36;
        r1 = "\u0015\u001eH\u0004w\u0003\u0003\u000e@f\u0018\u0014\u0014\u0001n\u0012^(!S?6/-F\"9) X\")6+";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01af:
        r3[r2] = r1;
        r2 = 37;
        r1 = "\u001b\u0015\u0015\u001df\u0011\u0015";
        r0 = 36;
        r3 = r4;
        goto L_0x0009;
    L_0x01ba:
        r3[r2] = r1;
        r2 = 38;
        r1 = "\u001b\u0003\u0001:~\u0006\u0015FS'";
        r0 = 37;
        r3 = r4;
        goto L_0x0009;
    L_0x01c5:
        r3[r2] = r1;
        r2 = 39;
        r1 = "\u0002\u0011\u0001\u000fk\u001f\u0011\u00151b\u0004\u0002\t\u001cd\u0019\u0014\u0003";
        r0 = 38;
        r3 = r4;
        goto L_0x0009;
    L_0x01d0:
        r3[r2] = r1;
        r2 = 40;
        r1 = "\u0004\u0015\u0016\u0001u\u00023\u0012\u001ck$\u0015\u0005\u000bn\u0000\u0015\u0002N*V\u0002\u0003\u001a=";
        r0 = 39;
        r3 = r4;
        goto L_0x0009;
    L_0x01db:
        r3[r2] = r1;
        r2 = 41;
        r1 = "\u0010\u0002\u0003\u001fr\u0013\u001e\u0005\u0017";
        r0 = 40;
        r3 = r4;
        goto L_0x0009;
    L_0x01e6:
        r3[r2] = r1;
        r2 = 42;
        r1 = "\u0012\u0019\u0015\u000fe\u001a\u0015";
        r0 = 41;
        r3 = r4;
        goto L_0x0009;
    L_0x01f1:
        r3[r2] = r1;
        r2 = 43;
        r1 = "\u001a\u0012\u0015Nu\u0013\u0000\t\u001csV\u0019\u0015Nc\u001f\u0003\u0007\fk\u0013\u0014H@)";
        r0 = 42;
        r3 = r4;
        goto L_0x0009;
    L_0x01fc:
        r3[r2] = r1;
        r2 = 44;
        r1 = "\u0015\u0004\u0014\u0002'[P\u0005\u0003cL";
        r0 = 43;
        r3 = r4;
        goto L_0x0009;
    L_0x0207:
        r3[r2] = r1;
        r2 = 45;
        r1 = "0\u0011\u000f\u0002b\u0012P\u0012\u0001'\u0004\u0015\u0016\u0001u\u0002P\u0014\u000bd\u0013\u0019\u0010\u000bcV]F";
        r0 = 44;
        r3 = r4;
        goto L_0x0009;
    L_0x0212:
        r3[r2] = r1;
        r2 = 46;
        r1 = "#\u001e\u0003\u0016w\u0013\u0013\u0012\u000bcLP\u0013\u0000l\u0018\u001f\u0011\u0000'\u0006\u0005\u0015\u0006'\u0015\u0004\u0014\u0002'\u0015\u001d\u0002T'";
        r0 = 45;
        r3 = r4;
        goto L_0x0009;
    L_0x021d:
        r3[r2] = r1;
        r2 = 47;
        r1 = "\u0017\u0013\u0012\u0007h\u0018PKNw\u0004\u001f\u0005\u000bt\u0005<\t\rf\u0002\u0019\t\u0000+V\u001d\u0015\tD\u0019\u001e\u0012\u000bi\u0002J";
        r0 = 46;
        r3 = r4;
        goto L_0x0009;
    L_0x0228:
        r3[r2] = r1;
        r2 = 48;
        r1 = "\u001a\u0012\u0015Nu\u0013\u0000\t\u001csV\u0019\u0015Nb\u0018\u0011\u0004\u0002b\u0012^H@";
        r0 = 47;
        r3 = r4;
        goto L_0x0009;
    L_0x0233:
        r3[r2] = r1;
        r2 = 49;
        r1 = "\u0015\u001d\u0002";
        r0 = 48;
        r3 = r4;
        goto L_0x0009;
    L_0x023e:
        r3[r2] = r1;
        r2 = 50;
        r1 = "\u0017\u0013\u0012\u0007h\u0018PKNw\u0004\u001f\u0005\u000bt\u0005<\t\rf\u0002\u0019\t\u0000+V\u0002\u0003\u001eh\u0004\u0004 \u001cb\u0007\u0005\u0003\u0000d\u000fJ";
        r0 = 49;
        r3 = r4;
        goto L_0x0009;
    L_0x0249:
        r3[r2] = r1;
        r2 = 51;
        r1 = "\u001c\u0003\t\u0000H\u0014\u001a\u0003\rsL";
        r0 = 50;
        r3 = r4;
        goto L_0x0009;
    L_0x0254:
        r3[r2] = r1;
        r2 = 52;
        r1 = "\u0000\u0015\u0014\u001dn\u0019\u001e\\";
        r0 = 51;
        r3 = r4;
        goto L_0x0009;
    L_0x025f:
        r3[r2] = r1;
        r2 = 53;
        r1 = "\u0017\u0000\u00161t\u0013\u0013\u0014\u000bs";
        r0 = 52;
        r3 = r4;
        goto L_0x0009;
    L_0x026a:
        r3[r2] = r1;
        r2 = 54;
        r1 = "V\u0000\u0014\u0001q\u001f\u0014\u0003\u001cn\u0018\u0014\u0003\u0016=";
        r0 = 53;
        r3 = r4;
        goto L_0x0009;
    L_0x0275:
        r3[r2] = r1;
        r2 = 55;
        r1 = "\u001e\u0004\u0012\u001e=Y_";
        r0 = 54;
        r3 = r4;
        goto L_0x0009;
    L_0x0280:
        r3[r2] = r1;
        r2 = 56;
        r1 = "\u0000\u0015\u0014\u001dn\u0019\u001e";
        r0 = 55;
        r3 = r4;
        goto L_0x0009;
    L_0x028b:
        r3[r2] = r1;
        r2 = 57;
        r1 = "\u0017\u0000\u00161n\u0012";
        r0 = 56;
        r3 = r4;
        goto L_0x0009;
    L_0x0296:
        r3[r2] = r1;
        r2 = 58;
        r1 = "\u0003\u0000\n\u0001f\u0012\u001e\u0013\u0003e\u0013\u0002";
        r0 = 57;
        r3 = r4;
        goto L_0x0009;
    L_0x02a1:
        r3[r2] = r1;
        r2 = 59;
        r1 = "\u0015\u0011\u0014\u001cn\u0013\u0002\u0015";
        r0 = 58;
        r3 = r4;
        goto L_0x0009;
    L_0x02ac:
        r3[r2] = r1;
        r2 = 60;
        r1 = "!9 '";
        r0 = 59;
        r3 = r4;
        goto L_0x0009;
    L_0x02b7:
        r3[r2] = r1;
        r2 = 61;
        r1 = "V\u0005\u0014\u0002=";
        r0 = 60;
        r3 = r4;
        goto L_0x0009;
    L_0x02c2:
        r3[r2] = r1;
        r2 = 62;
        r1 = "\u0015\u0011\u0014\u001cn\u0013\u0002\\";
        r0 = 61;
        r3 = r4;
        goto L_0x0009;
    L_0x02cd:
        r3[r2] = r1;
        r2 = 63;
        r1 = "|P\u0007\u001ew%\u0015\u0005\u001cb\u0002J";
        r0 = 62;
        r3 = r4;
        goto L_0x0009;
    L_0x02d8:
        r3[r2] = r1;
        r2 = 64;
        r1 = "|P\u0007\u001ew?4\\";
        r0 = 63;
        r3 = r4;
        goto L_0x0009;
    L_0x02e3:
        r3[r2] = r1;
        r2 = 65;
        r1 = "\u0003\u0002\n";
        r0 = 64;
        r3 = r4;
        goto L_0x0009;
    L_0x02ee:
        r3[r2] = r1;
        r2 = 66;
        r1 = "|P\u0014\u000bw\u0019\u0002\u0012\u0000r\u001b\u0012\u0003\u001c=";
        r0 = 65;
        r3 = r4;
        goto L_0x0009;
    L_0x02f9:
        r3[r2] = r1;
        r2 = 67;
        r1 = "\u0015\u0011\u0014\u001cn\u0013\u0002";
        r0 = 66;
        r3 = r4;
        goto L_0x0009;
    L_0x0304:
        r3[r2] = r1;
        f783z = r4;
        r0 = new java.util.concurrent.ConcurrentLinkedQueue;
        r0.<init>();
        f781a = r0;
        r0 = new com.google.gson.jpush.ab;
        r0.<init>();
        f782b = r0;
        return;
    L_0x0317:
        r9 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        goto L_0x001f;
    L_0x031b:
        r9 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        goto L_0x001f;
    L_0x031f:
        r9 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        goto L_0x001f;
    L_0x0323:
        r9 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.h.<clinit>():void");
    }

    /* renamed from: a */
    private static JSONObject m1404a(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            JSONObject jSONObject;
            JSONException jSONException = e;
            try {
                jSONObject = new JSONObject(C0403i.m1042e(str, ""));
            } catch (Exception e2) {
                jSONException.printStackTrace();
                jSONObject = null;
            }
            new StringBuilder(f783z[51]).append(jSONObject);
            ac.m1576a();
            return jSONObject;
        } catch (Exception e3) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m1405a(Context context, Handler handler, long j) {
        new StringBuilder(f783z[50]).append(j);
        ac.m1576a();
        C0404a.m1083c();
        C0404a.m1063a(context, j);
        Message.obtain(handler, 1002).sendToTarget();
    }

    /* renamed from: a */
    public static void m1406a(Context context, Handler handler, long j, C0514i c0514i) {
        int i = 0;
        C0523k c0523k = (C0523k) c0514i;
        int a = c0523k.mo2234a();
        long d = c0523k.m1858d();
        if (PushProtocol.MsgResponse(j, 0, C0404a.m1144x(), (byte) a, d, c0523k.m1828f().m1848a().longValue(), C0404a.m1123l()) != 0) {
            ac.m1581b();
        } else {
            new StringBuilder(f783z[23]).append(d);
            ac.m1581b();
        }
        long d2 = c0523k.m1858d();
        int a2 = c0523k.mo2234a();
        String h = c0523k.m1859h();
        new StringBuilder(f783z[38]).append(a2).append(f783z[21]).append(d2);
        ac.m1581b();
        new StringBuilder(f783z[14]).append(h);
        ac.m1576a();
        LineNumberReader lineNumberReader = new LineNumberReader(new StringReader(h));
        try {
            String readLine = lineNumberReader.readLine();
            if (readLine == null) {
                ac.m1588e();
                return;
            }
            String readLine2 = lineNumberReader.readLine();
            if (readLine2 == null) {
                ac.m1588e();
                return;
            }
            int length = (readLine.length() + readLine2.length()) + 2;
            if (h.length() > length + 1) {
                h = h.substring(length);
            } else {
                ac.m1581b();
                h = "";
            }
            new StringBuilder(f783z[22]).append(readLine).append(f783z[29]).append(readLine2).append(f783z[35]).append(h);
            ac.m1576a();
            switch (a2) {
                case 0:
                case 2:
                    try {
                        new StringBuilder(f783z[32]).append(d2);
                        ac.m1581b();
                        if (ServiceInterface.m1476e(context)) {
                            ac.m1584c();
                            return;
                        }
                        ao aoVar = new ao(f783z[7], f783z[15]);
                        if (TextUtils.isEmpty(readLine) || TextUtils.isEmpty(readLine2)) {
                            ac.m1586d();
                        } else if (TextUtils.isEmpty(h)) {
                            ac.m1588e();
                        } else {
                            new StringBuilder(f783z[16]).append(d2);
                            ac.m1581b();
                            C0429c a3 = C0456f.m1397a(context, h, readLine, readLine2, d2);
                            if (a3 != null) {
                                C0432d c0432d = new C0432d(a3, a3);
                                if (f781a.contains(c0432d)) {
                                    new StringBuilder(f783z[25]).append(c0432d);
                                    ac.m1588e();
                                } else {
                                    if (f781a.size() >= 200) {
                                        f781a.poll();
                                    }
                                    f781a.offer(c0432d);
                                    if (readLine2.equalsIgnoreCase(f783z[30])) {
                                        C0456f.m1400a(context, (C0430a) a3);
                                    } else if (a3.e) {
                                        i = 1;
                                        if (a3.b == 4) {
                                            i = 3;
                                        }
                                    } else {
                                        i = 2;
                                    }
                                    String str = d2;
                                    new StringBuilder(f783z[26]).append(i);
                                    ac.m1581b();
                                    if ((i & 1) != 0) {
                                        ac.m1581b();
                                        Intent intent = new Intent(f783z[17]);
                                        intent.putExtra(f783z[31], readLine2);
                                        intent.putExtra(f783z[33], readLine);
                                        intent.putExtra(f783z[37], h);
                                        intent.putExtra(f783z[19], str);
                                        intent.putExtra(f783z[36], a3.g);
                                        intent.addCategory(readLine);
                                        context.sendOrderedBroadcast(intent, readLine + f783z[27]);
                                    }
                                    if ((i & 2) != 0) {
                                        ac.m1581b();
                                        if (!(an.m1657a(a3.i) && an.m1657a(a3.l))) {
                                            C0490b.m1688b(context, a3);
                                        }
                                    }
                                }
                            }
                        }
                        aoVar.m1661a();
                        return;
                    } catch (Exception e) {
                        ac.m1593i();
                        return;
                    }
                case 3:
                case 6:
                case 21:
                case 22:
                    return;
                case 4:
                    C0459i.m1411a(context);
                    return;
                case 5:
                    C0458h.m1405a(context, handler, 0);
                    return;
                case 9:
                    C0459i.m1417b(context);
                    return;
                case 20:
                    Message.obtain(handler, 1009, new C0526n(Long.valueOf(C0458h.m1408b(h)).longValue(), c0523k.m1828f().m1849b(), 0, null, 0)).sendToTarget();
                    try {
                        JSONObject jSONObject = new JSONObject(h);
                        i = jSONObject.optInt(f783z[20], C0408d.f523i);
                        d2 = jSONObject.optLong(f783z[0]);
                        Intent intent2 = new Intent();
                        intent2.addCategory(C0448e.f751c);
                        intent2.setAction(f783z[18]);
                        intent2.putExtra(f783z[39], i);
                        intent2.putExtra(f783z[28], d2);
                        context.sendBroadcast(intent2);
                        return;
                    } catch (Exception e2) {
                        new StringBuilder(f783z[34]).append(h);
                        ac.m1586d();
                        return;
                    }
                case 44:
                    aj.m1639a(context);
                    return;
                default:
                    new StringBuilder(f783z[24]).append(a2);
                    ac.m1581b();
                    return;
            }
        } catch (IOException e3) {
            ac.m1593i();
        }
    }

    /* renamed from: a */
    private static void m1407a(Context context, JSONObject jSONObject) {
        ac.m1581b();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(f783z[9]);
            new StringBuilder(f783z[1]).append(jSONObject2);
            ac.m1581b();
            int optInt = jSONObject2.optInt(f783z[11]);
            new StringBuilder(f783z[13]).append(optInt);
            ac.m1576a();
            switch (optInt) {
                case 1:
                    String optString = jSONObject2.optString(f783z[6]);
                    String optString2 = jSONObject2.optString(f783z[8]);
                    Intent intent = new Intent();
                    intent.setClassName(optString, optString2);
                    ComponentName startService = context.startService(intent);
                    if (startService == null) {
                        ac.m1587d(f783z[7], new StringBuilder(f783z[4]).append(intent).append(f783z[2]).append(startService).toString());
                        return;
                    } else {
                        ac.m1582b(f783z[7], new StringBuilder(f783z[12]).append(intent).append(f783z[2]).append(startService).toString());
                        return;
                    }
                case 2:
                    ac.m1581b();
                    return;
                case 10:
                    ac.m1581b();
                    return;
                default:
                    new StringBuilder(f783z[3]).append(optInt);
                    ac.m1581b();
                    return;
            }
        } catch (Throwable e) {
            ac.m1583b(f783z[7], f783z[10], e);
        } catch (Throwable e2) {
            ac.m1583b(f783z[7], f783z[5], e2);
        }
    }

    /* renamed from: b */
    private static long m1408b(String str) {
        try {
            C3975w a = f782b.a(str);
            if (a instanceof C4054z) {
                a = a.h().b(f783z[0]);
                if (a != null && (a instanceof com.google.gson.jpush.ac)) {
                    return a.e();
                }
                ac.m1588e();
                return 0;
            }
            ac.m1588e();
            return 0;
        } catch (af e) {
            ac.m1588e();
            return 0;
        }
    }

    /* renamed from: b */
    public static void m1409b(Context context, Handler handler, long j, C0514i c0514i) {
        ac.m1576a();
        C0516b c0516b = (C0516b) c0514i;
        long a = c0516b.mo2234a();
        int CtrlResponse = PushProtocol.CtrlResponse(j, 0, C0404a.m1144x(), a, C0404a.m1129n(), C0404a.m1123l());
        new StringBuilder(f783z[40]).append(CtrlResponse);
        ac.m1576a();
        if (CtrlResponse != 0) {
            ac.m1587d(f783z[7], new StringBuilder(f783z[45]).append(a).toString());
        } else {
            new StringBuilder(f783z[23]).append(a);
            ac.m1581b();
        }
        if (CtrlResponse == 0) {
            String d = c0516b.m1838d();
            new StringBuilder(f783z[47]).append(d);
            ac.m1581b();
            if (an.m1657a(d)) {
                ac.m1586d();
                return;
            }
            try {
                JSONObject a2 = C0458h.m1404a(d);
                int optInt = a2.optInt(f783z[49]);
                new StringBuilder(f783z[44]).append(optInt);
                ac.m1581b();
                switch (optInt) {
                    case 4:
                        C0459i.m1411a(context);
                        return;
                    case 5:
                        a2 = a2.getJSONObject(f783z[9]);
                        new StringBuilder(f783z[1]).append(a2);
                        ac.m1581b();
                        if (a2.optBoolean(f783z[42])) {
                            ac.m1582b(f783z[7], f783z[43]);
                            C0404a.m1087c(context, false);
                            return;
                        }
                        ac.m1582b(f783z[7], f783z[48]);
                        C0404a.m1087c(context, true);
                        C0458h.m1405a(context, handler, a2.optLong(f783z[41]));
                        return;
                    case 6:
                    case 21:
                    case 22:
                        return;
                    case 9:
                        C0459i.m1417b(context);
                        return;
                    case 44:
                        aj.m1639a(context);
                        return;
                    case 50:
                        C0458h.m1407a(context, a2);
                        return;
                    case 51:
                        C0458h.m1410b(context, a2);
                        return;
                    default:
                        new StringBuilder(f783z[46]).append(optInt);
                        ac.m1586d();
                        return;
                }
            } catch (Throwable e) {
                ac.m1579a(f783z[7], f783z[10], e);
            } catch (Throwable e2) {
                ac.m1583b(f783z[7], f783z[5], e2);
            }
        }
    }

    /* renamed from: b */
    private static void m1410b(Context context, JSONObject jSONObject) {
        ac.m1581b();
        try {
            JSONObject jSONObject2 = jSONObject.getJSONObject(f783z[9]);
            new StringBuilder(f783z[1]).append(jSONObject2);
            ac.m1581b();
            boolean optBoolean = jSONObject2.optBoolean(f783z[58], false);
            String optString = jSONObject2.optString(f783z[56], "");
            String optString2 = jSONObject2.optString(f783z[57], "");
            String optString3 = jSONObject2.optString(f783z[53], "");
            JSONArray optJSONArray = jSONObject2.optJSONArray(f783z[59]);
            if (optJSONArray != null) {
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject jSONObject3 = optJSONArray.getJSONObject(i);
                    if (jSONObject3 != null) {
                        String optString4 = jSONObject3.optString(f783z[67], "");
                        String optString5 = jSONObject3.optString(f783z[65], "");
                        if (!(an.m1657a(optString4) || an.m1657a(optString5))) {
                            int a = cn.jpush.android.util.af.m1597a(optString4);
                            if (!optString5.startsWith(f783z[55])) {
                                optString5 = new StringBuilder(f783z[55]).append(optString5).toString();
                            }
                            if (!optString5.endsWith("/")) {
                                optString5 = optString5 + "/";
                            }
                            if (a != -1) {
                                C0404a.m1062a(context, a, optString5);
                            }
                            new StringBuilder(f783z[62]).append(optString4).append(f783z[61]).append(optString5).append(f783z[54]).append(a);
                            ac.m1576a();
                        }
                    }
                }
            }
            if (!an.m1657a(optString)) {
                C0404a.m1102f(context, optString);
            }
            if (an.m1657a(optString2)) {
                C0404a.m1106g(context, optString2);
            }
            if (an.m1657a(optString3)) {
                C0404a.m1111h(context, optString3);
            }
            new StringBuilder(f783z[52]).append(optString).append(f783z[64]).append(optString2).append(f783z[63]).append(optString3).append(f783z[66]).append(optBoolean);
            ac.m1581b();
            if (!optBoolean) {
                return;
            }
            if (C0490b.m1696c(context).toUpperCase().startsWith(f783z[60]) && an.m1657a(C0404a.m1130n(context))) {
                C0404a.m1092d(context, true);
            } else {
                cn.jpush.android.util.af.m1602a(context);
            }
        } catch (Throwable e) {
            ac.m1583b(f783z[7], f783z[5], e);
        }
    }
}
