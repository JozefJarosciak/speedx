package cn.jpush.android.helpers;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.telephony.TelephonyManager;
import cn.jpush.android.C0404a;
import cn.jpush.android.C0448e;
import cn.jpush.android.service.C0462a;
import cn.jpush.android.service.C0472k;
import cn.jpush.android.service.C0479r;
import cn.jpush.android.service.PushProtocol;
import cn.jpush.android.service.SisInfo;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.C0507u;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.ah;
import cn.jpush.android.util.an;
import cn.jpush.p005b.p006a.p007a.C0514i;
import cn.jpush.p005b.p006a.p007a.C0520e;
import cn.jpush.p005b.p006a.p007a.C0522j;
import cn.jpush.p005b.p006a.p007a.C0524l;
import cn.jpush.p005b.p006a.p009c.C0551a;
import com.google.common.primitives.UnsignedBytes;
import com.google.gson.jpush.C3975w;
import com.google.gson.jpush.C4052t;
import com.google.gson.jpush.C4054z;
import com.google.gson.jpush.ab;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.List;
import org.apache.http.protocol.HttpRequestExecutor;
import org.json.JSONException;
import org.json.JSONObject;

public class ConnectingHelper {
    /* renamed from: a */
    private static final List<String> f768a;
    /* renamed from: z */
    private static final String[] f769z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 78;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "V GV\\v;@V^]*EH\\g";
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
            case 0: goto L_0x03a3;
            case 1: goto L_0x03a7;
            case 2: goto L_0x03ab;
            case 3: goto L_0x03af;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 57;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "Y JYU5*[JVgoM]Jv=@HM| G\u0002\u0019";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "1k";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "'a\u0018\u0016\u000e";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "@!AYWq#L\\\u0019f*[N\\go[]Je GK\\5*[JVgoJW]po\u0004\u0018";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "v!\u0007RI`<A\u0016X{+[WPqa@VMp!]\u0016kP\b`kmG\u000e}qv[";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "G*NQJa*[\u0018t&E]]58@LQ5<LJOp=\t]Kg [\u0018\u00145,F\\\\/";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "`!BVVb!";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "v!\u0007RI`<A\u0016X{+[WPqa{}~\\\u001c}jxA\u0006fvf\\\u000b";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "匐呂\u0013\u0018";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "G*NQJa*[\u0018J`,J]\\qo\u0004\u0018S`&M\u0002";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "G*NQJa*[\u0018N|;A\u0002\u0019~*P\u0002";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "9oL@M^*P\u0002";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "9oJTPp!]qWs \u0013";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "丘卶酤";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "9oHHRC*[KPz!\u0013";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "5乁\tyIe\u0004LA\u0003";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "9oM]O|,Lq]/";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "e.ZKNz=M\u0002";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "@!L@Ip,]]]/o[]^|<]JXa&FVpq`CMPqoZPV`#M\u0018Wz;\tZ\\5*DHMla\t";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "G*NQJa*[\u0018_t&E]]5b\tJ\\au";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "9o[]^|<]JXa&FVpqu";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "9oD]Jf.N]\u0003";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "9oYWKau";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "F\u0006z\u0018kp,LQO|!N\u0016\u0017;";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "R*]\u0018J|<\tQWs \tKLv,L]]58@LQ5'FKM/o";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "R*]\u0018J|<\tQWs \t]Kg [\u0018\u00145<@Kqz<]\u0002";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "A \t_\\aoZQJ5b\tPVf;\u0013";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "P7J]Ia&FV\u0019b'LV\u0019v#FK\\5:MH\u0019f JS\\ao\u0004\u0018";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "F\u0006z\u0018kp,LQOp+\tkMg&G_\u00035";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "9oZ]Up,]QV{u";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "S.@T\\qo]W\u0019g*ZWUc*\tPVf;\t\\Wfo\u0004\u0018";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "V.G\u0018Wz;\t_\\aoZQJ5&G^V5)[WT5'FKM/o";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "9oYWKau\u001a\b\t%";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "Z?LV\u0019v GV\\v;@WW58@LQ5'HJ]v M]]5'FKM5b\tQI/";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = "Z?LV\u0019v GV\\v;@WW58@LQ5+L^X`#]\u0018\u00145&Y\u0002";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        r2 = 36;
        r1 = "\\!_YU|+\t\\\\s.\\TM5,FVW;";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01b0:
        r3[r2] = r1;
        r2 = 37;
        r1 = "|\"\u001f\f\u0017?\\KQ;,G";
        r0 = 36;
        r3 = r4;
        goto L_0x0009;
    L_0x01bb:
        r3[r2] = r1;
        r2 = 38;
        r1 = "Z?LV\u0019v GV\\v;@WW5)HQUp+\t\u0015\u0019g*]\u0002";
        r0 = 37;
        r3 = r4;
        goto L_0x0009;
    L_0x01c6:
        r3[r2] = r1;
        r2 = 39;
        r1 = "e:ZPfa vQTJ+HLX";
        r0 = 38;
        r3 = r4;
        goto L_0x0009;
    L_0x01d1:
        r3[r2] = r1;
        r2 = 40;
        r1 = "e:ZPfy NQWJ<LJOp=vLPx*";
        r0 = 39;
        r3 = r4;
        goto L_0x0009;
    L_0x01dc:
        r3[r2] = r1;
        r2 = 41;
        r1 = "v!\u0007RI`<A\u0016PxaHV]g @\\\u0017t,]QV{a`ufG\nzhv[\u001cl";
        r0 = 40;
        r3 = r4;
        goto L_0x0009;
    L_0x01e7:
        r3[r2] = r1;
        r2 = 42;
        r1 = "T,]QV{o\u0004\u0018Jp!Mk\\g9LJm|\"LJ";
        r0 = 41;
        r3 = r4;
        goto L_0x0009;
    L_0x01f2:
        r3[r2] = r1;
        r2 = 43;
        r1 = "<FV|m,LHM| G\u0018\u00145";
        r0 = 42;
        r3 = r4;
        goto L_0x0009;
    L_0x01fd:
        r3[r2] = r1;
        r2 = 44;
        r1 = "e:ZPfy NQWJ#F[Xy\u0010]QTp";
        r0 = 43;
        r3 = r4;
        goto L_0x0009;
    L_0x0208:
        r3[r2] = r1;
        r2 = 45;
        r1 = "|?Z\u0018Xg=\u0007T\\{o\u0004\u0018";
        r0 = 44;
        r3 = r4;
        goto L_0x0009;
    L_0x0213:
        r3[r2] = r1;
        r2 = 46;
        r1 = "e.[K\\F&ZQWs \t[Kt<A\u0002\u0003";
        r0 = 45;
        r3 = r4;
        goto L_0x0009;
    L_0x021e:
        r3[r2] = r1;
        r2 = 47;
        r1 = "a'L\u0018Sf G\u0018\u00145";
        r0 = 46;
        r3 = r4;
        goto L_0x0009;
    L_0x0229:
        r3[r2] = r1;
        r2 = 48;
        r1 = "\\!_YU|+\tKPfo[]Je GK\\9oOYPy*M\u0018MzoYYKf*\trjZ\u0001\u0007";
        r0 = 47;
        r3 = r4;
        goto L_0x0009;
    L_0x0234:
        r3[r2] = r1;
        r2 = 49;
        r1 = "q*OYLy;\tPMa?\u0013";
        r0 = 48;
        r3 = r4;
        goto L_0x0009;
    L_0x023f:
        r3[r2] = r1;
        r2 = 50;
        r1 = "|?Z";
        r0 = 49;
        r3 = r4;
        goto L_0x0009;
    L_0x024a:
        r3[r2] = r1;
        r2 = 51;
        r1 = "T,]QV{o\u0004\u0018Jp!M{V{!L[M| G{Qt!N]]";
        r0 = 50;
        r3 = r4;
        goto L_0x0009;
    L_0x0255:
        r3[r2] = r1;
        r2 = 52;
        r1 = "v!\u0007RI`<A\u0016X{+[WPqa@VMp!]\u0016zZ\u0001g}zA\u0006fv";
        r0 = 51;
        r3 = r4;
        goto L_0x0009;
    L_0x0260:
        r3[r2] = r1;
        r2 = 53;
        r1 = "f;HL\\5&Z\u0018Wz;\t[Qt!N]]5b\t";
        r0 = 52;
        r3 = r4;
        goto L_0x0009;
    L_0x026b:
        r3[r2] = r1;
        r2 = 54;
        r1 = "v!\u0007RI`<A\u0016X{+[WPqajww[\njlpZ\u0001v{qT\u0001n}";
        r0 = 53;
        r3 = r4;
        goto L_0x0009;
    L_0x0276:
        r3[r2] = r1;
        r2 = 55;
        r1 = "v GV\\v;L\\";
        r0 = 54;
        r3 = r4;
        goto L_0x0009;
    L_0x0281:
        r3[r2] = r1;
        r2 = 56;
        r1 = "@\t";
        r0 = 55;
        r3 = r4;
        goto L_0x0009;
    L_0x028c:
        r3[r2] = r1;
        r2 = 57;
        r1 = "e'FV\\";
        r0 = 56;
        r3 = r4;
        goto L_0x0009;
    L_0x0297:
        r3[r2] = r1;
        r2 = 58;
        r1 = "Y NQW5)HQUp+\tOPa'\tK\\g9LJ\u0019p=[WK5b\t[Vq*\u0013";
        r0 = 57;
        r3 = r4;
        goto L_0x0009;
    L_0x02a2:
        r3[r2] = r1;
        r2 = 59;
        r1 = "Y NQW58@LQ5b\tRL|+\u0013";
        r0 = 58;
        r3 = r4;
        goto L_0x0009;
    L_0x02ad:
        r3[r2] = r1;
        r2 = 60;
        r1 = "Y NQW5)HQUp+\tOPa'\ttVv.E\u0018\\g=FJ\u00198oJW]pu";
        r0 = 59;
        r3 = r4;
        goto L_0x0009;
    L_0x02b8:
        r3[r2] = r1;
        r2 = 61;
        r1 = "9oHHI^*P\u0002";
        r0 = 60;
        r3 = r4;
        goto L_0x0009;
    L_0x02c3:
        r3[r2] = r1;
        r2 = 62;
        r1 = "9oLJKz=\u0013";
        r0 = 61;
        r3 = r4;
        goto L_0x0009;
    L_0x02ce:
        r3[r2] = r1;
        r2 = 63;
        r1 = "Y NQW5<\\[Zp*M\u0018\u00145<@\\\u0003";
        r0 = 62;
        r3 = r4;
        goto L_0x0009;
    L_0x02d9:
        r3[r2] = r1;
        r2 = 64;
        r1 = "y NQW5b\tRL|+\u0013";
        r0 = 63;
        r3 = r4;
        goto L_0x0009;
    L_0x02e4:
        r3[r2] = r1;
        r2 = 65;
        r1 = "9oZ]Kc*[lPx*\u0012";
        r0 = 64;
        r3 = r4;
        goto L_0x0009;
    L_0x02ef:
        r3[r2] = r1;
        r2 = 66;
        r1 = "9oZ\\RC*[KPz!\u0013";
        r0 = 65;
        r3 = r4;
        goto L_0x0009;
    L_0x02fa:
        r3[r2] = r1;
        r2 = 67;
        r1 = "9oOTXru";
        r0 = 66;
        r3 = r4;
        goto L_0x0009;
    L_0x0305:
        r3[r2] = r1;
        r2 = 68;
        r1 = "5#F_P{\u001dLKIz!Z]\u0019|<\tVLy#";
        r0 = 67;
        r3 = r4;
        goto L_0x0009;
    L_0x0310:
        r3[r2] = r1;
        r2 = 69;
        r1 = "Y NQW5)HQUp+\tOPa'\tJ\\a:[V\u0019v M]\u0003";
        r0 = 68;
        r3 = r4;
        goto L_0x0009;
    L_0x031b:
        r3[r2] = r1;
        r2 = 70;
        r1 = "v GV\\v;@WW";
        r0 = 69;
        r3 = r4;
        goto L_0x0009;
    L_0x0326:
        r3[r2] = r1;
        r2 = 71;
        r1 = "\\!_YU|+\tUX|!\t[V{!";
        r0 = 70;
        r3 = r4;
        goto L_0x0009;
    L_0x0331:
        r3[r2] = r1;
        r2 = 72;
        r1 = "S.@T\\qo^QM}oHTU5,FVWfa";
        r0 = 71;
        r3 = r4;
        goto L_0x0009;
    L_0x033c:
        r3[r2] = r1;
        r2 = 73;
        r1 = "9o@V]p7\u0013";
        r0 = 72;
        r3 = r4;
        goto L_0x0009;
    L_0x0347:
        r3[r2] = r1;
        r2 = 74;
        r1 = "Z?LV\u0019v GV\\v;@WW58@LQ5 YLPz!Z\u0018\u00145&Y\u0002";
        r0 = 73;
        r3 = r4;
        goto L_0x0009;
    L_0x0352:
        r3[r2] = r1;
        r2 = 75;
        r1 = "Z?LV\u0019v GV\\v;@WW58@LQ5\"HQW5b\tQI/";
        r0 = 74;
        r3 = r4;
        goto L_0x0009;
    L_0x035d:
        r3[r2] = r1;
        r2 = 76;
        r1 = "Z?LV\u0019v GV\\v;@WW58@LQ5#HKM5(FW]5b\tQI/";
        r0 = 75;
        r3 = r4;
        goto L_0x0009;
    L_0x0368:
        r3[r2] = r1;
        r2 = 77;
        r1 = "F:J[\\p+\tLV5 Y]W5,FVWp,]QV{o\u0004\u0018Peu";
        r0 = 76;
        r3 = r4;
        goto L_0x0009;
    L_0x0373:
        r3[r2] = r1;
        f769z = r4;
        r6 = new java.util.ArrayList;
        r6.<init>();
        f768a = r6;
        r0 = "faCHLf'\u0007[W";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x03c1;
    L_0x0389:
        r3 = r0;
        r4 = r2;
        r11 = r1;
        r1 = r0;
        r0 = r11;
    L_0x038e:
        r7 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x03b3;
            case 1: goto L_0x03b6;
            case 2: goto L_0x03b9;
            case 3: goto L_0x03bc;
            default: goto L_0x0395;
        };
    L_0x0395:
        r5 = 57;
    L_0x0397:
        r5 = r5 ^ r7;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x03bf;
    L_0x039f:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x038e;
    L_0x03a3:
        r9 = 21;
        goto L_0x0020;
    L_0x03a7:
        r9 = 79;
        goto L_0x0020;
    L_0x03ab:
        r9 = 41;
        goto L_0x0020;
    L_0x03af:
        r9 = 56;
        goto L_0x0020;
    L_0x03b3:
        r5 = 21;
        goto L_0x0397;
    L_0x03b6:
        r5 = 79;
        goto L_0x0397;
    L_0x03b9:
        r5 = 41;
        goto L_0x0397;
    L_0x03bc:
        r5 = 56;
        goto L_0x0397;
    L_0x03bf:
        r1 = r0;
        r0 = r3;
    L_0x03c1:
        if (r1 > r2) goto L_0x0389;
    L_0x03c3:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        r6.add(r0);
        r2 = f768a;
        r1 = "f&Z\u0016Se:ZP\u0017| ";
        r0 = -1;
    L_0x03d4:
        r1 = r1.toCharArray();
        r3 = r1.length;
        r4 = 0;
        r5 = 1;
        if (r3 > r5) goto L_0x0405;
    L_0x03dd:
        r5 = r1;
        r6 = r4;
        r11 = r3;
        r3 = r1;
        r1 = r11;
    L_0x03e2:
        r8 = r3[r4];
        r7 = r6 % 5;
        switch(r7) {
            case 0: goto L_0x03f7;
            case 1: goto L_0x03fa;
            case 2: goto L_0x03fd;
            case 3: goto L_0x0400;
            default: goto L_0x03e9;
        };
    L_0x03e9:
        r7 = 57;
    L_0x03eb:
        r7 = r7 ^ r8;
        r7 = (char) r7;
        r3[r4] = r7;
        r4 = r6 + 1;
        if (r1 != 0) goto L_0x0403;
    L_0x03f3:
        r3 = r5;
        r6 = r4;
        r4 = r1;
        goto L_0x03e2;
    L_0x03f7:
        r7 = 21;
        goto L_0x03eb;
    L_0x03fa:
        r7 = 79;
        goto L_0x03eb;
    L_0x03fd:
        r7 = 41;
        goto L_0x03eb;
    L_0x0400:
        r7 = 56;
        goto L_0x03eb;
    L_0x0403:
        r3 = r1;
        r1 = r5;
    L_0x0405:
        if (r3 > r4) goto L_0x03dd;
    L_0x0407:
        r3 = new java.lang.String;
        r3.<init>(r1);
        r1 = r3.intern();
        switch(r0) {
            case 0: goto L_0x041c;
            case 1: goto L_0x0425;
            default: goto L_0x0413;
        };
    L_0x0413:
        r2.add(r1);
        r2 = f768a;
        r1 = "p.ZAMz\"LKJt(L\u0016Zz\"";
        r0 = 0;
        goto L_0x03d4;
    L_0x041c:
        r2.add(r1);
        r2 = f768a;
        r1 = "$~\u001a\u0016\n$a\u0018\u000f\u0017$\u0011";
        r0 = 1;
        goto L_0x03d4;
    L_0x0425:
        r2.add(r1);
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.helpers.ConnectingHelper.<clinit>():void");
    }

    /* renamed from: a */
    private static int m1362a(C0472k c0472k, long j) {
        String s = C0404a.m1139s();
        int t = C0404a.m1140t();
        ac.m1582b(f769z[0], new StringBuilder(f769z[35]).append(s).append(f769z[23]).append(t).toString());
        if (s == null || t == 0) {
            ac.m1582b(f769z[0], f769z[36]);
            return -1;
        }
        int a = m1363a(c0472k, j, s, t);
        if (a == 0) {
            return a;
        }
        try {
            InetAddress a2 = m1365a(f769z[37]);
            if (a2 == null) {
                throw new Exception();
            }
            String hostAddress = a2.getHostAddress();
            new StringBuilder(f769z[34]).append(hostAddress).append(f769z[33]);
            ac.m1581b();
            return m1363a(c0472k, j, hostAddress, (int) HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE);
        } catch (Exception e) {
            ac.m1581b();
            return a;
        }
    }

    /* renamed from: a */
    private static int m1363a(C0472k c0472k, long j, String str, int i) {
        if (c0472k.m1513b()) {
            ac.m1586d();
            return -991;
        }
        int InitPush = PushProtocol.InitPush(j, str, i);
        if (InitPush == 0) {
            return InitPush;
        }
        if (c0472k.m1513b()) {
            new StringBuilder(f769z[38]).append(InitPush);
            ac.m1576a();
            return InitPush;
        }
        ac.m1582b(f769z[0], new StringBuilder(f769z[38]).append(InitPush).toString());
        return InitPush;
    }

    /* renamed from: a */
    private static SisInfo m1364a(Context context, boolean z, int i, int i2) {
        DatagramSocket datagramSocket;
        String str;
        Throwable e;
        String str2;
        DatagramSocket datagramSocket2;
        SisInfo parseSisInfo;
        while (true) {
            boolean b = C0404a.m1080b(C0490b.m1734r(context));
            if (z || b || C0404a.m1118j()) {
                String str3 = "";
                try {
                    byte[] bArr;
                    datagramSocket = new DatagramSocket();
                    try {
                        bArr = new byte[1024];
                        str = (String) f768a.get(i);
                    } catch (AssertionError e2) {
                        e = e2;
                        DatagramSocket datagramSocket3 = datagramSocket;
                        str2 = str3;
                        datagramSocket2 = datagramSocket3;
                        try {
                            ac.m1579a(f769z[0], new StringBuilder(f769z[26]).append(str2).toString(), e);
                            i++;
                            if (datagramSocket2 != null) {
                                try {
                                    datagramSocket2.close();
                                } catch (AssertionError e3) {
                                    new StringBuilder(f769z[28]).append(e3.toString());
                                    ac.m1588e();
                                }
                            }
                            if (i >= 4) {
                                return null;
                            }
                            z = true;
                        } catch (Throwable th) {
                            e = th;
                            datagramSocket = datagramSocket2;
                        }
                    } catch (Exception e4) {
                        e = e4;
                        try {
                            ac.m1579a(f769z[0], new StringBuilder(f769z[26]).append(str3).toString(), e);
                            i++;
                            if (datagramSocket != null) {
                                try {
                                    datagramSocket.close();
                                } catch (AssertionError e32) {
                                    new StringBuilder(f769z[28]).append(e32.toString());
                                    ac.m1588e();
                                }
                            }
                            if (i >= 4) {
                                return null;
                            }
                            z = true;
                        } catch (Throwable th2) {
                            e = th2;
                        }
                    }
                    try {
                        InetAddress a;
                        ac.m1582b(f769z[0], new StringBuilder(f769z[27]).append(str).append(f769z[23]).append(i2).append(f769z[30]).append(i).toString());
                        if (i <= 2) {
                            a = m1365a(str);
                            if (a == null) {
                                throw new Exception(new StringBuilder(f769z[31]).append(str).toString());
                            }
                        }
                        a = InetAddress.getByName(str);
                        DatagramPacket datagramPacket = new DatagramPacket(m1366a(context, C0448e.f754f, C0404a.m1144x()), 128, a, i2);
                        datagramSocket.setSoTimeout(6000);
                        datagramSocket.send(datagramPacket);
                        DatagramPacket datagramPacket2 = new DatagramPacket(bArr, 1024);
                        ac.m1582b(f769z[0], f769z[24]);
                        datagramSocket.receive(datagramPacket2);
                        Object obj = new byte[datagramPacket2.getLength()];
                        System.arraycopy(datagramPacket2.getData(), 0, obj, 0, obj.length);
                        str3 = new String(obj);
                        new StringBuilder(f769z[29]).append(str3);
                        ac.m1584c();
                        parseSisInfo = parseSisInfo(str3);
                        if (parseSisInfo == null) {
                            ac.m1587d(f769z[0], new StringBuilder(f769z[32]).append(str).toString());
                            break;
                        }
                        break;
                        if (datagramSocket != null) {
                            try {
                                datagramSocket.close();
                            } catch (AssertionError e322) {
                                new StringBuilder(f769z[28]).append(e322.toString());
                                ac.m1588e();
                            }
                        }
                        return parseSisInfo;
                    } catch (Throwable e5) {
                        Throwable th3 = e5;
                        datagramSocket2 = datagramSocket;
                        str2 = str;
                        e = th3;
                    } catch (Throwable e52) {
                        th3 = e52;
                        str3 = str;
                        e = th3;
                        ac.m1579a(f769z[0], new StringBuilder(f769z[26]).append(str3).toString(), e);
                        i++;
                        if (datagramSocket != null) {
                            datagramSocket.close();
                        }
                        if (i >= 4) {
                            return null;
                        }
                        z = true;
                    }
                } catch (AssertionError e6) {
                    e = e6;
                    str2 = str3;
                    datagramSocket2 = null;
                    ac.m1579a(f769z[0], new StringBuilder(f769z[26]).append(str2).toString(), e);
                    i++;
                    if (datagramSocket2 != null) {
                        datagramSocket2.close();
                    }
                    if (i >= 4) {
                        return null;
                    }
                    z = true;
                } catch (Exception e7) {
                    e = e7;
                    datagramSocket = null;
                    ac.m1579a(f769z[0], new StringBuilder(f769z[26]).append(str3).toString(), e);
                    i++;
                    if (datagramSocket != null) {
                        datagramSocket.close();
                    }
                    if (i >= 4) {
                        return null;
                    }
                    z = true;
                } catch (Throwable th4) {
                    e = th4;
                    datagramSocket = null;
                }
            } else {
                ac.m1584c();
                return parseSisInfo(C0404a.m1142v());
            }
            z = true;
        }
        ac.m1585c(f769z[0], new StringBuilder(f769z[25]).append(str).toString());
        C0404a.m1121k();
        if (datagramSocket != null) {
            datagramSocket.close();
        }
        return parseSisInfo;
        if (datagramSocket != null) {
            try {
                datagramSocket.close();
            } catch (AssertionError e8) {
                new StringBuilder(f769z[28]).append(e8.toString());
                ac.m1588e();
            }
        }
        throw e;
        throw e;
    }

    /* renamed from: a */
    private static InetAddress m1365a(String str) {
        InetAddress inetAddress = null;
        C0451a c0451a = new C0451a(str);
        try {
            c0451a.start();
            c0451a.join(3000);
            inetAddress = c0451a.m1367a();
        } catch (InterruptedException e) {
            ac.m1588e();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        return inetAddress;
    }

    /* renamed from: a */
    private static byte[] m1366a(Context context, String str, long j) {
        String networkOperator;
        int intValue;
        String c = C0490b.m1696c(context);
        String str2 = "";
        try {
            networkOperator = ((TelephonyManager) context.getSystemService(f769z[57])).getNetworkOperator();
        } catch (Exception e) {
            e.printStackTrace();
            networkOperator = str2;
        }
        str2 = f769z[56] + c;
        try {
            intValue = Integer.valueOf(networkOperator).intValue();
        } catch (Exception e2) {
            intValue = 0;
        }
        byte[] bArr = new byte[128];
        System.arraycopy(new byte[]{(byte) 0, UnsignedBytes.MAX_POWER_OF_TWO}, 0, bArr, 0, 2);
        C0551a.m1901a(bArr, str2, 2);
        C0551a.m1900a(bArr, intValue, 34);
        C0551a.m1900a(bArr, Integer.parseInt(((int) (2147483647L & j))), 38);
        if (str.length() > 50) {
            str = str.substring(0, 49);
        }
        C0551a.m1901a(bArr, str, 42);
        C0551a.m1901a(bArr, f769z[3], 92);
        C0551a.m1900a(bArr, 0, 102);
        return bArr;
    }

    public static short getIMLoginFlag() {
        return (short) 1;
    }

    public static int login(Context context, long j) {
        byte[] bArr = new byte[128];
        long x = C0404a.m1144x();
        String b = an.m1659b(C0404a.m1046A());
        String E = C0404a.m1050E();
        int c = C0490b.m1694c(f769z[3]);
        ac.m1585c(f769z[0], new StringBuilder(f769z[59]).append(x).append(f769z[61]).append(E).append(f769z[66]).append(c).toString());
        long currentTimeMillis = System.currentTimeMillis();
        short iMLoginFlag = getIMLoginFlag();
        new StringBuilder(f769z[64]).append(x).append(f769z[67]).append(iMLoginFlag);
        ac.m1581b();
        int LogPush = PushProtocol.LogPush(j, C0404a.m1129n(), bArr, x, b, E, (long) c, iMLoginFlag);
        long currentTimeMillis2 = System.currentTimeMillis();
        int i = 0;
        if (LogPush == 0 || LogPush == 9999) {
            C0522j c0522j = (C0522j) C0520e.m1847a(bArr);
            if (c0522j == null) {
                ac.m1587d(f769z[0], new StringBuilder(f769z[69]).append(LogPush).append(f769z[68]).toString());
                C0507u.m1805a(context, LogPush, currentTimeMillis2 - currentTimeMillis, 1);
                return -1;
            }
            c0522j.toString();
            ac.m1581b();
            i = c0522j.g;
            if (i == 0) {
                int a = c0522j.mo2234a();
                long d = ((long) c0522j.m1854d()) * 1000;
                C0404a.m1074b(a);
                C0404a.m1059a(d);
                ac.m1585c(f769z[0], new StringBuilder(f769z[63]).append(a).append(f769z[65]).append(d).toString());
                sendServerTimer(context, d);
                c = 0;
            } else if (i == 10000) {
                ac.m1587d(f769z[0], new StringBuilder(f769z[60]).append(i).append(f769z[62]).append(c0522j.h).toString());
                c = 1;
            } else {
                ac.m1587d(f769z[0], new StringBuilder(f769z[58]).append(i).append(f769z[62]).append(c0522j.h).toString());
                c = 0;
            }
        } else {
            c = 1;
            ac.m1587d(f769z[0], new StringBuilder(f769z[69]).append(LogPush).toString());
        }
        C0507u.m1805a(context, LogPush, currentTimeMillis2 - currentTimeMillis, c);
        return i == 10000 ? -1 : LogPush;
    }

    public static synchronized boolean openConnection(C0472k c0472k, Context context, long j, SisInfo sisInfo) {
        boolean z;
        synchronized (ConnectingHelper.class) {
            String h;
            int i;
            if (sisInfo == null) {
                h = C0404a.m1109h();
                i = C0404a.m1113i();
                if (h != null) {
                    ac.m1582b(f769z[0], new StringBuilder(f769z[76]).append(h).append(f769z[23]).append(i).toString());
                    if (m1363a(c0472k, j, h, i) == 0) {
                        z = true;
                    }
                }
                z = m1362a(c0472k, j) == 0;
            } else {
                int i2;
                String mainConnIp = sisInfo.getMainConnIp();
                i = sisInfo.getMainConnPort();
                ac.m1582b(f769z[0], new StringBuilder(f769z[75]).append(mainConnIp).append(f769z[23]).append(i).toString());
                if (mainConnIp == null || i == 0) {
                    i2 = -1;
                    ac.m1587d(f769z[0], f769z[71]);
                } else {
                    i2 = m1363a(c0472k, j, mainConnIp, i);
                }
                if (i2 != 0) {
                    int i3 = 0;
                    for (String h2 : sisInfo.getOptionConnIp()) {
                        int intValue = ((Integer) sisInfo.getOptionConnPort().get(i3)).intValue();
                        ac.m1582b(f769z[0], new StringBuilder(f769z[74]).append(h2).append(f769z[23]).append(intValue).append(f769z[73]).append(i3).toString());
                        i = m1363a(c0472k, j, h2, intValue);
                        int i4;
                        if (i == 0) {
                            i4 = i;
                            i = intValue;
                            mainConnIp = h2;
                            i2 = i4;
                            break;
                        }
                        i3++;
                        i4 = i;
                        i = intValue;
                        mainConnIp = h2;
                        i2 = i4;
                    }
                }
                if (i2 != 0) {
                    i2 = m1362a(c0472k, j);
                }
                if (i2 == 0) {
                    C0404a.m1069a(mainConnIp);
                    C0404a.m1058a(i);
                    ac.m1585c(f769z[0], new StringBuilder(f769z[77]).append(mainConnIp).append(f769z[23]).append(i).toString());
                    z = true;
                } else {
                    ac.m1587d(f769z[0], f769z[72]);
                    z = false;
                }
            }
        }
        return z;
    }

    public static SisInfo parseSisInfo(String str) {
        try {
            SisInfo fromJson = SisInfo.fromJson(str);
            if (fromJson == null) {
                ac.m1587d(f769z[0], f769z[48]);
                return null;
            }
            C3975w a = new ab().a(str);
            new StringBuilder(f769z[47]).append(a.toString());
            ac.m1576a();
            if (a instanceof C4054z) {
                C4054z h = a.h();
                if (h.a(f769z[50])) {
                    a = h.b(f769z[50]);
                    if (a instanceof C4052t) {
                        C4052t i = a.i();
                        new StringBuilder(f769z[45]).append(i.a());
                        ac.m1576a();
                        if (i.a() >= 3) {
                            String c = i.a(2).c();
                            new StringBuilder(f769z[49]).append(c);
                            ac.m1581b();
                            ah.m1614a(c);
                        }
                    }
                } else {
                    ac.m1588e();
                }
            }
            fromJson.parseAndSet(str);
            return !fromJson.isInvalidSis() ? fromJson : null;
        } catch (Exception e) {
            ac.m1587d(f769z[0], new StringBuilder(f769z[46]).append(e).toString());
            return null;
        }
    }

    public static boolean register(Context context, long j, boolean z) {
        byte[] bArr = new byte[128];
        String a = C0460j.m1418a(context);
        String b = C0460j.m1420b(context);
        String a2 = C0490b.m1670a(context, f769z[3]);
        String j2 = C0490b.m1718j(context);
        String i = C0490b.m1716i(context);
        String g = C0490b.m1712g(context, " ");
        String j3 = C0490b.m1719j(context, " ");
        String str = Build.SERIAL;
        if (an.m1657a(j2)) {
            j2 = " ";
        }
        if (an.m1657a(i)) {
            i = " ";
        }
        if (an.m1657a(g)) {
            g = " ";
        }
        if (an.m1657a(j3)) {
            j3 = " ";
        }
        if (an.m1657a(str) || f769z[7].equalsIgnoreCase(str)) {
            str = " ";
        }
        C0404a.m1117j(j3);
        C0404a.m1122k(i);
        C0404a.m1125l(g);
        str = C0490b.f999a + f769z[2] + j2 + f769z[2] + j3 + f769z[2] + i + f769z[2] + g + f769z[2] + str;
        new StringBuilder(f769z[11]).append(a).append(f769z[15]).append(b).append(f769z[13]).append(a2).append(f769z[12]).append(str);
        ac.m1581b();
        if (PushProtocol.RegPush(j, C0404a.m1129n(), a, b, a2, str) == -991) {
            return false;
        }
        int RecvPush = PushProtocol.RecvPush(j, bArr, 30);
        if (RecvPush > 0) {
            C0514i a3 = C0520e.m1847a(bArr);
            if (a3 == null) {
                ac.m1588e();
                return false;
            }
            a3.toString();
            ac.m1581b();
            if (a3.m1827e() != 0) {
                ac.m1588e();
                return false;
            }
            C0524l c0524l = (C0524l) a3;
            RecvPush = c0524l.g;
            C0404a.m1085c(context, RecvPush);
            if (RecvPush == 0) {
                long a4 = c0524l.mo2234a();
                g = c0524l.m1863d();
                j3 = c0524l.m1864h();
                a = c0524l.m1865i();
                ac.m1585c(f769z[0], new StringBuilder(f769z[10]).append(a4).append(f769z[21]).append(j3).append(f769z[17]).append(a).toString());
                new StringBuilder(f769z[18]).append(g);
                ac.m1576a();
                if (an.m1657a(j3) || 0 == a4) {
                    ac.m1589e(f769z[0], f769z[19]);
                }
                C0490b.m1723l(context, a);
                C0404a.m1060a(a4, g, j3, a, C0448e.f754f);
                C0448e.f755g = a4;
                C0448e.f756h = g;
                if (!z) {
                    C0490b.m1677a(context, f769z[5], f769z[8], j3);
                }
                return true;
            }
            ac.m1589e(f769z[0], new StringBuilder(f769z[6]).append(RecvPush).append(f769z[22]).append(c0524l.h).toString());
            i = C0479r.m1532a(RecvPush);
            if (i != null) {
                ac.m1587d(f769z[0], new StringBuilder(f769z[1]).append(i).toString());
            }
            if (1006 == RecvPush) {
                C0404a.m1138r();
            } else if (1007 == RecvPush) {
                ac.m1584c();
            } else if (1005 == RecvPush) {
                j2 = new StringBuilder(f769z[9]).append(C0448e.f751c).append(f769z[16]).append(C0448e.f754f).append(f769z[14]).toString();
                C0490b.m1690b(context, j2, j2, -1);
                C0404a.m1138r();
            } else if (1009 == RecvPush) {
                C0404a.m1138r();
            } else {
                new StringBuilder(f769z[4]).append(RecvPush);
                ac.m1584c();
            }
        } else {
            ac.m1589e(f769z[0], new StringBuilder(f769z[20]).append(RecvPush).toString());
        }
        return false;
    }

    public static void sendConnectionChanged(Context context, C0462a c0462a) {
        boolean z = false;
        ac.m1582b(f769z[0], f769z[51]);
        if (c0462a == C0404a.m1057a(context)) {
            new StringBuilder(f769z[53]).append(c0462a);
            ac.m1576a();
            return;
        }
        C0404a.m1064a(context, c0462a);
        Bundle bundle = new Bundle();
        String str = f769z[54];
        if (c0462a.name().equals(f769z[55])) {
            z = true;
        }
        bundle.putBoolean(str, z);
        C0490b.m1675a(context, f769z[52], bundle);
    }

    public static void sendConnectionToHandler(Message message, long j) {
        Bundle bundle = new Bundle();
        bundle.putLong(f769z[70], j);
        message.setData(bundle);
        message.sendToTarget();
    }

    public static void sendServerTimer(Context context, long j) {
        ac.m1582b(f769z[0], f769z[42]);
        try {
            Bundle bundle = new Bundle();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(f769z[40], j);
            jSONObject.put(f769z[44], System.currentTimeMillis());
            bundle.putString(f769z[39], jSONObject.toString());
            C0490b.m1675a(context, f769z[41], bundle);
        } catch (JSONException e) {
            new StringBuilder(f769z[43]).append(e.getMessage());
            ac.m1586d();
        }
    }

    public static synchronized SisInfo sendSis(Context context) {
        SisInfo a;
        synchronized (ConnectingHelper.class) {
            a = m1364a(context, false, 0, 19000);
        }
        return a;
    }
}
