package cn.jpush.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.jpush.android.C0448e;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.data.C0430a;
import cn.jpush.android.data.C0431b;
import cn.jpush.android.data.C0437i;
import cn.jpush.android.helpers.C0456f;
import cn.jpush.android.helpers.C0459i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0503p;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.an;
import com.alipay.sdk.cons.C0844a;
import java.io.File;

public class PushReceiver extends BroadcastReceiver {
    /* renamed from: a */
    public static C0429c f805a = null;
    /* renamed from: z */
    private static final String[] f806z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 52;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "Knt;\u0005)mJ:\u00133BE-\u0013oKJ\u001e\u00174WAf_";
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
            case 0: goto L_0x025d;
            case 1: goto L_0x0261;
            case 2: goto L_0x0265;
            case 3: goto L_0x0269;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "#K@7";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "oTA<\u001b(WW'\u0019/\nn\u001e#\u0012l{\u00033\u0012we\t3";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = " TT\u0007\u0012";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "K皠.!\u0018\u0013AW;\u001b$\f\rD\u0019/tE;\u0005$\f\rD";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\"J\n$\u00064WL`\u0017/@V!\u001f%\nj\u0001\"\bbm\r7\u0015mk\u0000)\u0015}t\u000b";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\"J\n$\u00064WL`\u0017/@V!\u001f%\nM \u0002$JP`8\u000epm\b?\u0002ep\u00079\u000f{m\u0000%\u0015eh\u0002)\u0002hm\r=\u0004`";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0012AJ*V#VK/\u0012\"EW:V5K\u0004/\u00061\u001e\u0004";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u000fK\u0004\u000f5\u0015mk\u0000)\u000fkp\u00070\bge\u001a?\u000ej{\u0001&\u0004ja\nV%AB'\u0018$@\u0004'\u0018aIE \u001f'AW:ZaKT+\u0018aPL+V%AB/\u0003-P\u0004#\u0017(J\u0004/\u00155MR'\u00028";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = " TT";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "2AJ*\u00133m@";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "K.月掞祌厫圌弤原戥卄犒急久册珱\b叵幍戥卄丩伾冴珆o";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "/AP9\u00193Om \u0010.";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "1EG%\u0017&A\u001e";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u000btQ=\u001ea廞變隈扦aw`\u0005V斷劄丮纑诗亢砥仁變但揩逥敬柒ぴa..梎洽剱圌.";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "{\u0004c+\u0002aJKn\u0012 PEn\u00103KIn\u001f/PA \u0002o";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "K皠.!\u0018\u0013AW;\u001b$\f\rD";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = " J@<\u0019(@\n \u00135\nG!\u0018/\ng\u00018\u000fag\u001a?\u0017mp\u0017)\u0002le\u00001\u0004";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "Knt;\u0005)mJ:\u00133BE-\u0013oKJ\u001c\u00132QI+^h";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = ".PL+\u0004aJA:\u0001.VOn\u00055EP+Vl\u0004";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "/KP\u0011\u00174PK<\u0003/";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u0006KPn\u0017/\u0004A#\u00065]\u0004 \u00195MB'\u0015 PM!\u0018m\u0004@!\u0018fP\u0004=\u001e.S\u0004'\u0002`";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = " J@<\u0019(@\n'\u00185AJ:X GP'\u0019/\nq\u001d3\u0013{t\u001c3\u0012aj\u001a";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "%AF;\u0011\u001eJK:\u001f'MG/\u0002(KJ";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "\u0002KJ \u0013\"PM!\u0018aWP/\u0002$\u0004G&\u0017/CA*V5K\u0004cV";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "5KE=\u0002\u0015A\\:";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "\"J\n$\u00064WL`\u0017/@V!\u001f%\nM \u0002$JP`8\u000epm\b?\u0002ep\u00079\u000f{v\u000b5\u0004mr\u000b2\u001etv\u0001.\u0018";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "\u0013AG+\u001f7A@n\u0018.\u0004E-\u0002(KJn\u001f/PA \u0002aFV!\u0017%GE=\u0002o\u0004c'\u0000$\u0004Q>V1VK-\u00132WM \u0011o";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "旸泱三泯板豂甌";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = " TT\"\u001f\"EP'\u0019/\u000bR \u0012oEJ*\u0004.M@`\u0006 GO/\u0011$\tE<\u0015)MR+";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "\u0006APn\u00013KJ)V%EP/V2PV'\u0018&\u0004B<\u0019,\u0004M \u0002$JPtV";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = " J@<\u0019(@\n'\u00185AJ:X GP'\u0019/\nt\u000f5\nec\u000b)\u0013ai\u0001 \u0004`";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "\"J\n$\u00064WL`\u0017/@V!\u001f%\nM \u0002$JP`8\u000epm\b?\u0002ep\u00079\u000f{k\u001e3\u000fa`";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "\"J\n$\u00064WL`\u0017/@V!\u001f%\ni\u001d1\u001em`";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = " J@<\u0019(@\n'\u00185AJ:X GP'\u0019/\nf\u00019\u0015{g\u0001;\u0011ha\u001a3\u0005";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = "Knt;\u0005)mJ:\u00133BE-\u0013oKJ\u001c\u00132QI+^h.n\u001e\u00032Lm \u0002$VB/\u0015$\nK & QW+^h";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        r2 = 36;
        r1 = "K皠.!\u0018\u0011EQ=\u0013i\r.";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01b0:
        r3[r2] = r1;
        r2 = 37;
        r1 = ",WC\u0011\u001f%";
        r0 = 36;
        r3 = r4;
        goto L_0x0009;
    L_0x01bb:
        r3[r2] = r1;
        r2 = 38;
        r1 = " J@<\u0019(@\n'\u00185AJ:X GP'\u0019/\nt\u000f5\nec\u000b)\u0000``\u000b2";
        r0 = 37;
        r3 = r4;
        goto L_0x0009;
    L_0x01c6:
        r3[r2] = r1;
        r2 = 39;
        r1 = " J@<\u0019(@\n'\u00185AJ:X GP'\u0019/\nr\u00073\u0016";
        r0 = 38;
        r3 = r4;
        goto L_0x0009;
    L_0x01d1:
        r3[r2] = r1;
        r2 = 40;
        r1 = "o\u0004`!V/KP&\u001f/C\n";
        r0 = 39;
        r3 = r4;
        goto L_0x0009;
    L_0x01dc:
        r3[r2] = r1;
        r2 = 41;
        r1 = ".Jv+\u0015$MR+Vl\u0004";
        r0 = 40;
        r3 = r4;
        goto L_0x0009;
    L_0x01e7:
        r3[r2] = r1;
        r2 = 42;
        r1 = " GP'\u0000(P]";
        r0 = 41;
        r3 = r4;
        goto L_0x0009;
    L_0x01f2:
        r3[r2] = r1;
        r2 = 43;
        r1 = ",AW=\u0017&A";
        r0 = 42;
        r3 = r4;
        goto L_0x0009;
    L_0x01fd:
        r3[r2] = r1;
        r2 = 44;
        r1 = "/KP'\u0010(GE'\u0019/{P7\u0006$";
        r0 = 43;
        r3 = r4;
        goto L_0x0009;
    L_0x0208:
        r3[r2] = r1;
        r2 = 45;
        r1 = "/Kg!\u0018/AG:\u001f7MP7";
        r0 = 44;
        r3 = r4;
        goto L_0x0009;
    L_0x0213:
        r3[r2] = r1;
        r2 = 46;
        r1 = "\u0013AG+\u001f7A@n\u00184HHn\u001f/PA \u0002aFV!\u0017%GE=\u0002o\u0004c'\u0000$\u0004Q>V1VK-\u00132WM \u0011o";
        r0 = 45;
        r3 = r4;
        goto L_0x0009;
    L_0x021e:
        r3[r2] = r1;
        r2 = 47;
        r1 = "\u0000gp\u00079\u000f{j\u0001\"\bbm\r7\u0015mk\u0000)\u0013ag\u000b?\u0017a`\u0011&\u0013k|\u0017Va\u0004J!\u0002(BM-\u00175MK )5]T+V|\u0004";
        r0 = 46;
        r3 = r4;
        goto L_0x0009;
    L_0x0229:
        r3[r2] = r1;
        r2 = 48;
        r1 = "\u0011QW&$$GA'\u0000$V";
        r0 = 47;
        r3 = r4;
        goto L_0x0009;
    L_0x0234:
        r3[r2] = r1;
        r2 = 49;
        r1 = "5]T+";
        r0 = 48;
        r3 = r4;
        goto L_0x0009;
    L_0x023f:
        r3[r2] = r1;
        r2 = 50;
        r1 = "\"J\n$\u00064WL`\u0017/@V!\u001f%\nM \u0002$JP`8\u000epm\b?\u0002ep\u00079\u000f{k\u001e3\u000fa`\u0011&\u0013k|\u0017";
        r0 = 49;
        r3 = r4;
        goto L_0x0009;
    L_0x024a:
        r3[r2] = r1;
        r2 = 51;
        r1 = "2PV\u0000\"8TAnKa";
        r0 = 50;
        r3 = r4;
        goto L_0x0009;
    L_0x0255:
        r3[r2] = r1;
        f806z = r4;
        r0 = 0;
        f805a = r0;
        return;
    L_0x025d:
        r9 = 65;
        goto L_0x0020;
    L_0x0261:
        r9 = 36;
        goto L_0x0020;
    L_0x0265:
        r9 = 36;
        goto L_0x0020;
    L_0x0269:
        r9 = 78;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.PushReceiver.<clinit>():void");
    }

    public void onReceive(Context context, Intent intent) {
        boolean z = false;
        if (intent == null) {
            ac.m1587d(f806z[48], f806z[46]);
            return;
        }
        try {
            String action = intent.getAction();
            ac.m1582b(f806z[48], new StringBuilder(f806z[41]).append(action).toString());
            if (!C0448e.m1359a(context.getApplicationContext())) {
                return;
            }
            if (action.equals(f806z[34])) {
                C0503p.m1785c(context);
                ServiceInterface.m1473c(context, 500);
            } else if (action.equals(f806z[38])) {
                r1 = intent.getDataString();
                if (r1 == null) {
                    ac.m1587d(f806z[48], action + f806z[15]);
                } else if (r1.trim().length() <= 8 || !r1.startsWith(f806z[13])) {
                    ac.m1587d(f806z[48], new StringBuilder(f806z[30]).append(r1).toString());
                } else {
                    String substring = r1.substring(8);
                    Object a = C0431b.m1283a(context, substring);
                    C0459i.m1413a(context, C0479r.m1533a(substring));
                    ServiceInterface.m1469b(context.getApplicationContext(), substring);
                    try {
                        if (!TextUtils.isEmpty(a)) {
                            String[] split = a.split(",");
                            if (split.length > 0) {
                                r1 = split[0];
                                C0459i.m1415a(r1, 1002, context);
                                r2 = split.length >= 2 ? split[1] : "";
                                action = "";
                                if (split.length >= 3) {
                                    action = split[2];
                                }
                                ac.m1588e();
                                if (!(r2.equals(f806z[20]) || C0490b.m1683a(context, substring, r2))) {
                                    ac.m1581b();
                                    C0459i.m1415a(r1, 1100, context);
                                }
                                if (an.m1657a(action)) {
                                    action = r1;
                                }
                                C0417m.m1216a(context, action);
                            }
                        }
                    } catch (Exception e) {
                        e.getMessage();
                        ac.m1588e();
                    }
                }
            } else if (action.equals(f806z[31])) {
                r1 = intent.getDataString();
                if (r1 == null) {
                    ac.m1587d(f806z[48], action + f806z[15]);
                } else if (r1.trim().length() <= 8 || !r1.startsWith(f806z[13])) {
                    ac.m1587d(f806z[48], new StringBuilder(f806z[30]).append(r1).toString());
                } else {
                    action = r1.substring(8);
                    C0459i.m1413a(context, C0479r.m1535b(action));
                    ServiceInterface.m1469b(context.getApplicationContext(), action);
                }
            } else if (action.equals(f806z[22])) {
                ServiceInterface.m1467b(context);
            } else if (action.equals(f806z[6])) {
                C0429c c0429c = (C0429c) intent.getSerializableExtra(f806z[1]);
                if (c0429c != null && c0429c.m1273a()) {
                    C0459i.m1415a(c0429c.f613c, 1015, context);
                    C0437i c0437i = (C0437i) c0429c;
                    r1 = new Intent();
                    r1.addFlags(268435456);
                    r1.setAction(f806z[39]);
                    r1.setDataAndType(Uri.fromFile(new File(c0437i.f678P)), f806z[29]);
                    context.startActivity(r1);
                }
            } else if (action.startsWith(f806z[26])) {
                r0 = intent.getIntExtra(f806z[44], 0);
                new StringBuilder(f806z[47]).append(r0);
                ac.m1581b();
                if (r0 == 0) {
                    if (ServiceInterface.m1476e(context)) {
                        ac.m1584c();
                        return;
                    } else if (!C0490b.m1724m(context)) {
                        ac.m1584c();
                        return;
                    }
                }
                action = intent.getStringExtra(f806z[43]);
                if (an.m1657a(action)) {
                    ac.m1587d(f806z[48], f806z[21]);
                    return;
                }
                C0430a a2 = C0456f.m1397a(context, action, intent.getStringExtra(f806z[3]), intent.getStringExtra(f806z[10]), intent.getStringExtra(f806z[37]));
                if (a2 == null) {
                    ac.m1586d();
                    return;
                }
                a2.h = true;
                C0456f.m1400a(context, a2);
                abortBroadcast();
            } else if (action.startsWith(f806z[50])) {
                if (intent.getBooleanExtra(f806z[23], false)) {
                    CharSequence stringExtra = intent.getStringExtra(f806z[42]);
                    int intExtra = intent.getIntExtra(f806z[49], -1);
                    View view;
                    TextView textView;
                    if (intExtra == -1) {
                        CharSequence stringExtra2 = intent.getStringExtra(f806z[25]);
                        Toast makeText = Toast.makeText(context, stringExtra, 1);
                        view = makeText.getView();
                        if (view instanceof LinearLayout) {
                            view = ((LinearLayout) view).getChildAt(0);
                            if (view instanceof TextView) {
                                textView = (TextView) view;
                                if (!an.m1657a(stringExtra2)) {
                                    textView.setText(stringExtra2);
                                }
                                textView.setTextSize(13.0f);
                            }
                        }
                        makeText.show();
                        return;
                    } else if (!an.m1657a(stringExtra)) {
                        String str = f806z[14];
                        r2 = f806z[16];
                        action = f806z[28];
                        r1 = f806z[18];
                        switch (intExtra) {
                            case 1:
                                r2 = f806z[36];
                                action = f806z[28];
                                r1 = f806z[0];
                                break;
                            case 2:
                                r2 = f806z[4];
                                action = f806z[28];
                                r1 = f806z[35];
                                break;
                        }
                        CharSequence stringBuilder = new StringBuilder();
                        stringBuilder.append(str).append(stringExtra).append(r2).append(action).append(r1).append(f806z[11]);
                        CharSequence spannableStringBuilder = new SpannableStringBuilder(stringBuilder);
                        intExtra = str.length();
                        int length = stringExtra.length() + intExtra;
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(-13527041), intExtra, length, 33);
                        intExtra = length + 2;
                        int length2 = (r2.length() + intExtra) - 2;
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(-13527041), intExtra, length2, 33);
                        r0 = action.length() + length2;
                        spannableStringBuilder.setSpan(new ForegroundColorSpan(-13527041), r0, r1.length() + r0, 33);
                        Toast makeText2 = Toast.makeText(context, stringExtra, 1);
                        view = makeText2.getView();
                        if (view instanceof LinearLayout) {
                            view = ((LinearLayout) view).getChildAt(0);
                            if (view instanceof TextView) {
                                textView = (TextView) view;
                                if (spannableStringBuilder != null) {
                                    textView.setText(spannableStringBuilder);
                                }
                                textView.setTextSize(13.0f);
                            }
                        }
                        makeText2.show();
                        return;
                    } else {
                        return;
                    }
                }
                action = intent.getStringExtra(f806z[33]);
                if (!an.m1657a(action)) {
                    r1 = intent.getStringExtra(f806z[5]);
                    new StringBuilder(f806z[51]).append(r1);
                    ac.m1581b();
                    if (r1 != null && C0844a.f2048d.equals(r1)) {
                        z = true;
                    }
                    if (true != z) {
                        C0459i.m1415a(action, 1000, context);
                    }
                }
                if (C0490b.m1684a(context, f806z[32], true)) {
                    r1 = new Intent(f806z[32]);
                    r1.putExtras(intent.getExtras());
                    action = intent.getStringExtra(f806z[9]);
                    if (an.m1657a(action)) {
                        action = context.getPackageName();
                    }
                    r1.addCategory(action);
                    new StringBuilder(f806z[7]).append(action);
                    ac.m1584c();
                    context.sendBroadcast(r1, action + f806z[2]);
                    return;
                }
                ac.m1582b(f806z[48], f806z[8]);
                C0490b.m1705e(context);
            } else if (action.equalsIgnoreCase(f806z[17])) {
                NetworkInfo networkInfo = (NetworkInfo) intent.getParcelableExtra(f806z[12]);
                if (networkInfo == null) {
                    ac.m1586d();
                    return;
                }
                new StringBuilder(f806z[24]).append(networkInfo.toString());
                ac.m1581b();
                if (2 == networkInfo.getType() || 3 == networkInfo.getType()) {
                    ac.m1581b();
                    return;
                }
                boolean z2;
                if (intent.getBooleanExtra(f806z[45], false)) {
                    ac.m1581b();
                    C0463b.f835b = false;
                    ServiceInterface.m1478g(context);
                    z2 = false;
                } else if (State.CONNECTED == networkInfo.getState()) {
                    ac.m1581b();
                    ServiceInterface.m1477f(context);
                    C0463b.f835b = true;
                    if (DownloadService.m1431a()) {
                        DownloadService.m1425a(context);
                        z2 = true;
                    } else {
                        z2 = true;
                    }
                } else if (State.DISCONNECTED == networkInfo.getState()) {
                    ac.m1581b();
                    C0463b.f835b = false;
                    ServiceInterface.m1478g(context);
                    z2 = false;
                } else {
                    new StringBuilder(f806z[19]).append(networkInfo.getState()).append(f806z[40]);
                    z2 = false;
                    ac.m1581b();
                }
                C0490b.m1679a(context, z2);
            }
        } catch (NullPointerException e2) {
            ac.m1587d(f806z[48], f806z[27]);
        }
    }
}
