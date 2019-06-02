package cn.jpush.android;

import android.content.Context;
import android.content.SharedPreferences;
import cn.jpush.android.helpers.C0453c;
import cn.jpush.android.service.C0462a;
import cn.jpush.android.util.C0403i;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.android.util.af;
import cn.jpush.android.util.an;
import java.util.Random;

/* renamed from: cn.jpush.android.a */
public final class C0404a extends C0403i {
    /* renamed from: z */
    private static final String[] f494z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 81;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\t\tV\u0004D\b3P\fT\u001e\u001bO\u001fC";
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
            case 0: goto L_0x0399;
            case 1: goto L_0x039d;
            case 2: goto L_0x03a1;
            case 3: goto L_0x03a5;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 39;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0007\u001cU\u001eO2\u001eE\nN\u001e\u0018E\u001fx\u000e\u0003D\b";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u001e\tR\u001bN\u000e\tS2K\f\u0019N\u000eO\b\b\u0019N\u0000\t";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u001e\bK2Q\b\u001eS\u0004H\u0003";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "_\u000e\u0019]C\b\\F\\AUTE\fAYU\u0015T\u0014\u000b]DU\u0015Z\u000e\u0011TD[_";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0003\u0019M\u000fB\u001f3A\u001dW\u001e\tC\u001fB\u0019";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u000e\u0003N\u0003B\u000e\u0018I\u0003@2\u001fT\fS\b";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u0001\rS\u0019x\u001f\tP\u0002U\u00193L\u0002D\f\u0018I\u0002I";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0005\tA\u001fS\u000f\tA\u0019x\u0004\u0002T\bU\u001b\rL";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0003\u000e\u0018W\u0001\u0003A\t";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0001\rS\u0019x\u001e\u0005S2U\b\u001dU\bT\u00193T\u0004J\b";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\t\tV\u0004D\b3M\fN\u00033I\u0000B\u0004";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\t\tV\u0004D\b3M\fN\u00033A\u0003C\u001f\u0003I\tx\u0004\b";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u0003\u0019M\u000fB\u001f3N\u0018J";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0004\u0001\u0001H\n\u0005N2D\u0002\u0019N\u0019";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "\t\tV\u000eN\b3I\tx\n\tN\bU\f\u0018E\t";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\u001d\u0019S\u0005x\u0018\bI\t";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0001\rS\u0019x\u000e\u0004E\u000eL2\u0019S\bU\f\u001cP2T\u0019\rT\u0018T";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\t\tF\fR\u0001\u0018\u000eH\u0003\u0002\u0004W";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\\]\u0013C\u0014\\B\u0011Z\t\\\\\u0016";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "\u0001\u0003G\u0004I2\u0000O\u000eF\u00013T\u0004J\b";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u0003\u0003T\u0004A\f\u000fT\u0004H\u00033N\u0018J";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u001e\tR\u001bN\u000e\t\u001eS\u0002\u001cE\t";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\u001d\u0019S\u0005S\u0004\u0001E";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "\t\tV\u0004D\b3M\fN\u00033I\tT";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "\u0007\u001cU\u001eO2\u001fI\u001ex\u001f\tC\bN\u001b\tR2T\u0019\u001eI\u0003@";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "\u0007\u001cU\u001eO2\u000fO\u0003I2\u0001I\u001d";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "\u0001\u000fT\u0004J\b";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "\u000f\rC\u0006R\u001d3R\bW\u0002\u001eT2F\t\bR";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "\u0000<O\u001fS";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "\u000e\u0002\u000e\u0007W\u0018\u001fHCT\b\u001eV\bU\u000e\u0003N\u000bN\n";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "\u001f\tG\u0004T\u0019\tR2C\b\u001aI\u000eB2\u0001A\u000e";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "\t\tV2N\u0003\nO2U\b\u001c\u0019N\u0000\t";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "\u0001\u0003G\u0004I2\u001fE\u001fQ\b\u001e\u0019N\u0000\t";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "\u0001\rS\u0019x\u001f\tP\u0002U\u00193D\bQ\u0004\u000fE2N\u0003\nO";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = "\u001e\tT\u0019N\u0003\u000b\u001eN\u0001\tN\u000eB2\u001cU\u001eO2\u0018I\u0000B";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        r2 = 36;
        r1 = "\u0004\u0001E\u0004";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01b0:
        r3[r2] = r1;
        r2 = 37;
        r1 = "\u0007\u001cU\u001eO2\u001fI\u001ex\u0003\tT\u0019^\u001d\t";
        r0 = 36;
        r3 = r4;
        goto L_0x0009;
    L_0x01bb:
        r3[r2] = r1;
        r2 = 38;
        r1 = "\u0001\rS\u0019x\n\u0003O\tx\u000e\u0003N\u0003x\u001d\u0003R\u0019";
        r0 = 37;
        r3 = r4;
        goto L_0x0009;
    L_0x01c6:
        r3[r2] = r1;
        r2 = 39;
        r1 = "\u001f\tG\u0004T\u0019\u001eA\u0019N\u0002\u0002\u0004C";
        r0 = 38;
        r3 = r4;
        goto L_0x0009;
    L_0x01d1:
        r3[r2] = r1;
        r2 = 40;
        r1 = "\u0005\u0018T\u001dx\u001f\tP\u0002U\u00193S\u0004T2\u0019R\u0001";
        r0 = 39;
        r3 = r4;
        goto L_0x0009;
    L_0x01dc:
        r3[r2] = r1;
        r2 = 41;
        r1 = "\u001f\tG\u0004T\u0019\tR2C\b\u001aI\u000eB2\rN\tU\u0002\u0005D2N\t";
        r0 = 40;
        r3 = r4;
        goto L_0x0009;
    L_0x01e7:
        r3[r2] = r1;
        r2 = 42;
        r1 = "\t\tF\fR\u0001\u0018\u000eH\u0003\u0002\u001dH\u001f\u0018";
        r0 = 41;
        r3 = r4;
        goto L_0x0009;
    L_0x01f2:
        r3[r2] = r1;
        r2 = 43;
        r1 = "'<U\u001eO2(E\u001bN\u000e\ti\t";
        r0 = 42;
        r3 = r4;
        goto L_0x0009;
    L_0x01fd:
        r3[r2] = r1;
        r2 = 44;
        r1 = "\u0007\u001cU\u001eO2\u000fO\u0003I2\u0001P\u0002U\u0019";
        r0 = 43;
        r3 = r4;
        goto L_0x0009;
    L_0x0208:
        r3[r2] = r1;
        r2 = 45;
        r1 = "\u001e\tR\u001bN\u000e\t\u000eH\u0003\u0002E\u000eB\u0019";
        r0 = 44;
        r3 = r4;
        goto L_0x0009;
    L_0x0213:
        r3[r2] = r1;
        r2 = 46;
        r1 = "\t\tV\u0004D\b3M\fN\u00033M\fD";
        r0 = 45;
        r3 = r4;
        goto L_0x0009;
    L_0x021e:
        r3[r2] = r1;
        r2 = 47;
        r1 = "\u000e\u0002\u000e\u0007W\u0018\u001fHCF\u0003\bR\u0002N\tBa=w&)y";
        r0 = 46;
        r3 = r4;
        goto L_0x0009;
    L_0x0229:
        r3[r2] = r1;
        r2 = 48;
        r1 = "\u001f\tG\u0004T\u0019\tR2C\b\u001aI\u000eB2\u0005M\bN";
        r0 = 47;
        r3 = r4;
        goto L_0x0009;
    L_0x0234:
        r3[r2] = r1;
        r2 = 49;
        r1 = "\u0001\rS\u0019x\n\u0003O\tx\u000e\u0003N\u0003x\u0004\u001c";
        r0 = 48;
        r3 = r4;
        goto L_0x0009;
    L_0x023f:
        r3[r2] = r1;
        r2 = 50;
        r1 = "\u0001\rS\u0019x\u000e\u0003N\u0003B\u000e\u0018I\u0002I2\u0018Y\u001dB";
        r0 = 49;
        r3 = r4;
        goto L_0x0009;
    L_0x024a:
        r3[r2] = r1;
        r2 = 51;
        r1 = "\u0000%p";
        r0 = 50;
        r3 = r4;
        goto L_0x0009;
    L_0x0255:
        r3[r2] = r1;
        r2 = 52;
        r1 = "\u0001\rS\u0019x\n\u0003O\tx\u001e\u0005S";
        r0 = 51;
        r3 = r4;
        goto L_0x0009;
    L_0x0260:
        r3[r2] = r1;
        r2 = 53;
        r1 = "\u001e\u0005L\bI\u000e\tp\u0018T\u00058I\u0000B";
        r0 = 52;
        r3 = r4;
        goto L_0x0009;
    L_0x026b:
        r3[r2] = r1;
        r2 = 54;
        r1 = "\u0001\u0003G\u0004I2\u001eE\u001dH\u001f\u0018\u0019N\u0000\t";
        r0 = 53;
        r3 = r4;
        goto L_0x0009;
    L_0x0276:
        r3[r2] = r1;
        r2 = 55;
        r1 = "\u0003\u0003T\u0004A\u0004\u000fA\u0019N\u0002\u0002\u0003R\u0000";
        r0 = 54;
        r3 = r4;
        goto L_0x0009;
    L_0x0281:
        r3[r2] = r1;
        r2 = 56;
        r1 = "\u001e\tT\u0019N\u0003\u000b\u001dR\u001e\u0004\u0019N\u0000\t";
        r0 = 55;
        r3 = r4;
        goto L_0x0009;
    L_0x028c:
        r3[r2] = r1;
        r2 = 57;
        r1 = "\u001f\tG\u0004T\u0019\tR2C\b\u001aI\u000eB2\u0005N\u000bH";
        r0 = 56;
        r3 = r4;
        goto L_0x0009;
    L_0x0297:
        r3[r2] = r1;
        r2 = 58;
        r1 = "\\B\u0013C\u0017";
        r0 = 57;
        r3 = r4;
        goto L_0x0009;
    L_0x02a2:
        r3[r2] = r1;
        r2 = 59;
        r1 = "\u0003\u0019M\u000fB\u001f3V\bU\u001e\u0005O\u0003";
        r0 = 58;
        r3 = r4;
        goto L_0x0009;
    L_0x02ad:
        r3[r2] = r1;
        r2 = 60;
        r1 = "\t\tV\u0004D\b3R\b@\u0004\u001fT\bU\b\b\fW\u001d\u0007E\u0014";
        r0 = 59;
        r3 = r4;
        goto L_0x0009;
    L_0x02b8:
        r3[r2] = r1;
        r2 = 61;
        r1 = "\u0003\u0003T\u0004A\u0004\u000fA\u0019N\u0002\u0002\bI\f\u000eL\bC";
        r0 = 60;
        r3 = r4;
        goto L_0x0009;
    L_0x02c3:
        r3[r2] = r1;
        r2 = 62;
        r1 = "\u001e\tS\u001eN\u0002\u0002\u0004C";
        r0 = 61;
        r3 = r4;
        goto L_0x0009;
    L_0x02ce:
        r3[r2] = r1;
        r2 = 63;
        r1 = "\t\tV\u0004D\b3A\u001dW\u0006\tY";
        r0 = 62;
        r3 = r4;
        goto L_0x0009;
    L_0x02d9:
        r3[r2] = r1;
        r2 = 64;
        r1 = "\u0000\rXMI\u0002\u0018I\u000bN\u000e\rT\u0004H\u0003V";
        r0 = 63;
        r3 = r4;
        goto L_0x0009;
    L_0x02e4:
        r3[r2] = r1;
        r2 = 65;
        r1 = "\u001e\u0018O\u001dx\b\u0014E\u000eR\u0019\tD2H\u00033I\u0000K\u0002\u000bI\u0003";
        r0 = 64;
        r3 = r4;
        goto L_0x0009;
    L_0x02ef:
        r3[r2] = r1;
        r2 = 66;
        r1 = "\u0007\u001cU\u001eO2\u001fA\u001bB2\u000fU\u001eS\u0002\u0001\u000fR\u0004\u0000D\bU";
        r0 = 65;
        r3 = r4;
        goto L_0x0009;
    L_0x02fa:
        r3[r2] = r1;
        r2 = 67;
        r1 = "\u000f\rC\u0006R\u001d3U\u001eB\u001f3A\tC\u001f";
        r0 = 66;
        r3 = r4;
        goto L_0x0009;
    L_0x0305:
        r3[r2] = r1;
        r2 = 68;
        r1 = "\u0001\rS\u0019x\u001f\tP\u0002U\u00193I\u0003C\b\u0014";
        r0 = 67;
        r3 = r4;
        goto L_0x0009;
    L_0x0310:
        r3[r2] = r1;
        r2 = 69;
        r1 = "\t\tV\u0004D\b3C\u0005F\u0003\u0002E\u0001";
        r0 = 68;
        r3 = r4;
        goto L_0x0009;
    L_0x031b:
        r3[r2] = r1;
        r2 = 70;
        r1 = "\t\tV\u0004D\b3U\u0004C";
        r0 = 69;
        r3 = r4;
        goto L_0x0009;
    L_0x0326:
        r3[r2] = r1;
        r2 = 71;
        r1 = "\u0004\u001f\u0004J2\u0000O\n@\b\b\u0004I";
        r0 = 70;
        r3 = r4;
        goto L_0x0009;
    L_0x0331:
        r3[r2] = r1;
        r2 = 72;
        r1 = "\u0003\u0019M\u000fB\u001f3A\u001dW\u0004\b";
        r0 = 71;
        r3 = r4;
        goto L_0x0009;
    L_0x033c:
        r3[r2] = r1;
        r2 = 73;
        r1 = "\t\tV\u0004D\b3R\b@\u0004\u001fT\u001fF\u0019\u0005O\u0003x\u0004\b";
        r0 = 72;
        r3 = r4;
        goto L_0x0009;
    L_0x0347:
        r3[r2] = r1;
        r2 = 74;
        r1 = "\u001f\tP\u0002U\u00193L\u0002D\f\u0018I\u0002I2\nR\bV\u0018\tN\u000e^";
        r0 = 73;
        r3 = r4;
        goto L_0x0009;
    L_0x0352:
        r3[r2] = r1;
        r2 = 75;
        r1 = "\u0001\u000eS2U\b\u001cO\u001fS2\tN\fE\u0001\t";
        r0 = 74;
        r3 = r4;
        goto L_0x0009;
    L_0x035d:
        r3[r2] = r1;
        r2 = 76;
        r1 = "\u0003\u000e\u0001F\u001e\u0018T\u0004J\b";
        r0 = 75;
        r3 = r4;
        goto L_0x0009;
    L_0x0368:
        r3[r2] = r1;
        r2 = 77;
        r1 = "\u0003\u0019M\u000fB\u001f3U\u001fK";
        r0 = 76;
        r3 = r4;
        goto L_0x0009;
    L_0x0373:
        r3[r2] = r1;
        r2 = 78;
        r1 = ":%f$";
        r0 = 77;
        r3 = r4;
        goto L_0x0009;
    L_0x037e:
        r3[r2] = r1;
        r2 = 79;
        r1 = "\u0003\tX\u0019x\u001f\u0005D";
        r0 = 78;
        r3 = r4;
        goto L_0x0009;
    L_0x0389:
        r3[r2] = r1;
        r2 = 80;
        r1 = "\u0005\u0018T\u001d\u001dBC\u0011U\u0015CU\u0012C\u0015]B\u0011U\u001eWU\u0010T\u001eB";
        r0 = 79;
        r3 = r4;
        goto L_0x0009;
    L_0x0394:
        r3[r2] = r1;
        f494z = r4;
        return;
    L_0x0399:
        r9 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        goto L_0x0020;
    L_0x039d:
        r9 = 108; // 0x6c float:1.51E-43 double:5.34E-322;
        goto L_0x0020;
    L_0x03a1:
        r9 = 32;
        goto L_0x0020;
    L_0x03a5:
        r9 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.<clinit>():void");
    }

    /* renamed from: A */
    public static String m1046A() {
        String str = C0448e.f756h;
        if (!an.m1657a(str)) {
            return str;
        }
        str = C0453c.m1378b(C0448e.f753e, f494z[0], "");
        C0448e.f756h = str;
        return str;
    }

    /* renamed from: B */
    public static String m1047B() {
        return C0453c.m1378b(C0448e.f753e, f494z[73], "");
    }

    /* renamed from: C */
    public static String m1048C() {
        return C0403i.m1041d(f494z[60], null);
    }

    /* renamed from: D */
    public static String m1049D() {
        return C0453c.m1378b(C0448e.f753e, f494z[15], "");
    }

    /* renamed from: E */
    public static String m1050E() {
        return C0453c.m1378b(C0448e.f753e, f494z[63], "");
    }

    /* renamed from: F */
    public static String m1051F() {
        return C0403i.m1041d(f494z[69], null);
    }

    /* renamed from: G */
    public static boolean m1052G() {
        String d = C0403i.m1041d(f494z[11], "");
        String d2 = C0403i.m1041d(f494z[12], "");
        String d3 = C0403i.m1041d(f494z[46], "");
        if (!d.isEmpty() || !d2.isEmpty() || !d3.isEmpty()) {
            return false;
        }
        ac.m1584c();
        return true;
    }

    /* renamed from: H */
    public static String m1053H() {
        return C0403i.m1041d(f494z[11], "");
    }

    /* renamed from: I */
    public static String m1054I() {
        return C0403i.m1041d(f494z[3], "");
    }

    /* renamed from: J */
    public static void m1055J() {
        C0403i.m1035a(f494z[76], System.currentTimeMillis());
    }

    /* renamed from: K */
    private static long m1056K() {
        long b = C0453c.m1377b(C0448e.f753e, f494z[74], 3600000);
        return b > 0 ? b : 3600000;
    }

    /* renamed from: a */
    public static C0462a m1057a(Context context) {
        return C0462a.valueOf(C0453c.m1378b(context, f494z[6], C0462a.f831b.name()));
    }

    /* renamed from: a */
    public static void m1058a(int i) {
        C0403i.m1034a(f494z[38], i);
    }

    /* renamed from: a */
    public static void m1059a(long j) {
        C0403i.m1035a(f494z[33], j);
        C0403i.m1035a(f494z[20], System.currentTimeMillis());
    }

    /* renamed from: a */
    public static void m1060a(long j, String str, String str2, String str3, String str4) {
        C0404a.m1068a(Long.valueOf(j));
        C0404a.m1135p(str);
        C0404a.m1137q(str2);
        if (!an.m1657a(str3)) {
            C0404a.m1107g(str3);
        }
        C0403i.m1040c(f494z[60], str4);
    }

    /* renamed from: a */
    public static void m1061a(Context context, int i) {
        C0453c.m1372a(context, f494z[55], i);
    }

    /* renamed from: a */
    public static void m1062a(Context context, int i, String str) {
        if (i >= 0 && i < 3) {
            C0404a.m1091d(context, new StringBuilder(f494z[77]).append(i).toString(), str);
        }
    }

    /* renamed from: a */
    public static void m1063a(Context context, long j) {
        C0453c.m1373a(context, f494z[74], j > 0 ? 1000 * j : C0404a.m1056K());
    }

    /* renamed from: a */
    public static void m1064a(Context context, C0462a c0462a) {
        C0453c.m1374a(context, f494z[6], c0462a.name());
    }

    /* renamed from: a */
    public static void m1065a(Context context, String str) {
        C0453c.m1374a(context, f494z[35], str);
    }

    /* renamed from: a */
    public static void m1066a(Context context, String str, String str2) {
        C0453c.m1374a(context, new StringBuilder(f494z[66]).append(str).toString(), str2);
    }

    /* renamed from: a */
    public static void m1067a(Context context, boolean z) {
        C0453c.m1375a(context, f494z[65], z);
    }

    /* renamed from: a */
    private static void m1068a(Long l) {
        C0448e.f755g = l.longValue();
        C0453c.m1373a(C0448e.f753e, f494z[70], l.longValue());
    }

    /* renamed from: a */
    public static void m1069a(String str) {
        C0403i.m1040c(f494z[49], str);
    }

    /* renamed from: a */
    public static void m1070a(boolean z) {
        C0453c.m1375a(C0448e.f753e, f494z[71], z);
    }

    /* renamed from: a */
    public static boolean m1071a() {
        long b = C0403i.m1037b(f494z[34], 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - b <= 86400000) {
            return false;
        }
        C0403i.m1035a(f494z[34], currentTimeMillis);
        return true;
    }

    /* renamed from: a */
    public static boolean m1072a(String str, String str2) {
        String d = C0403i.m1041d(f494z[11], "");
        String d2 = C0403i.m1041d(f494z[12], "");
        if (str.equals(d) && str2.equals(d2)) {
            ac.m1584c();
            return true;
        }
        ac.m1584c();
        return false;
    }

    /* renamed from: b */
    public static int m1073b(Context context) {
        int b = C0453c.m1376b(context, f494z[55], 5);
        new StringBuilder(f494z[64]).append(b);
        ac.m1576a();
        return b;
    }

    /* renamed from: b */
    public static void m1074b(int i) {
        C0403i.m1034a(f494z[62], i);
    }

    /* renamed from: b */
    public static void m1075b(long j) {
        C0403i.m1035a(f494z[2], j);
    }

    /* renamed from: b */
    public static void m1076b(Context context, int i) {
        C0453c.m1372a(context, f494z[22], i);
    }

    /* renamed from: b */
    public static void m1077b(Context context, String str) {
        C0453c.m1374a(context, f494z[56], str);
    }

    /* renamed from: b */
    public static void m1078b(Context context, boolean z) {
        C0453c.m1375a(context, f494z[61], z);
    }

    /* renamed from: b */
    public static boolean m1079b() {
        long b = C0403i.m1037b(f494z[7], 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - b <= C0404a.m1056K()) {
            return false;
        }
        C0403i.m1035a(f494z[7], currentTimeMillis);
        return true;
    }

    /* renamed from: b */
    public static boolean m1080b(String str) {
        if (str == null) {
            return true;
        }
        if (str.equalsIgnoreCase(C0403i.m1041d(f494z[50], null))) {
            return false;
        }
        C0403i.m1040c(f494z[50], str);
        return true;
    }

    /* renamed from: b */
    public static boolean m1081b(String str, String str2) {
        String d = C0403i.m1041d(f494z[12], "");
        String d2 = C0403i.m1041d(f494z[46], "");
        if (an.m1657a(str2) || an.m1657a(d2)) {
            return str.equals(d);
        }
        if (str.equals(d) && str2.equals(d2)) {
            ac.m1584c();
            return true;
        }
        ac.m1584c();
        return false;
    }

    /* renamed from: c */
    public static int m1082c(Context context) {
        return C0453c.m1376b(context, f494z[22], 0);
    }

    /* renamed from: c */
    public static void m1083c() {
        C0403i.m1035a(f494z[7], System.currentTimeMillis());
    }

    /* renamed from: c */
    public static void m1084c(int i) {
        C0403i.m1034a(f494z[42], i);
    }

    /* renamed from: c */
    public static void m1085c(Context context, int i) {
        C0453c.m1372a(context, f494z[1], i);
    }

    /* renamed from: c */
    public static void m1086c(Context context, String str) {
        C0403i.m1038b(context, f494z[16], str);
    }

    /* renamed from: c */
    public static void m1087c(Context context, boolean z) {
        C0453c.m1375a(context, f494z[75], z);
    }

    /* renamed from: c */
    public static void m1088c(String str) {
        C0403i.m1040c(f494z[18], str);
    }

    /* renamed from: d */
    public static String m1089d(Context context, int i) {
        return (i < 0 || i >= 3) ? f494z[80] : C0404a.m1098e(context, new StringBuilder(f494z[77]).append(i).toString(), f494z[80]);
    }

    /* renamed from: d */
    public static void m1090d(Context context, String str) {
        C0403i.m1038b(context, f494z[36], str);
    }

    /* renamed from: d */
    private static void m1091d(Context context, String str, String str2) {
        C0453c.m1374a(context, str, C0403i.m1044o(str2));
    }

    /* renamed from: d */
    public static void m1092d(Context context, boolean z) {
        C0453c.m1375a(context, f494z[9], z);
    }

    /* renamed from: d */
    public static void m1093d(String str) {
        C0453c.m1374a(C0448e.f753e, f494z[28], str);
    }

    /* renamed from: d */
    public static boolean m1094d() {
        long b = C0403i.m1037b(f494z[17], 0);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - b <= 3600000) {
            return false;
        }
        C0403i.m1035a(f494z[17], currentTimeMillis);
        return true;
    }

    /* renamed from: d */
    public static boolean m1095d(Context context) {
        return C0453c.m1379b(context, f494z[65], false);
    }

    /* renamed from: e */
    public static String m1096e(Context context) {
        return C0453c.m1378b(context, f494z[35], "");
    }

    /* renamed from: e */
    public static String m1097e(Context context, String str) {
        return C0453c.m1378b(context, new StringBuilder(f494z[66]).append(str).toString(), "");
    }

    /* renamed from: e */
    private static String m1098e(Context context, String str, String str2) {
        String b = C0453c.m1378b(context, str, "");
        return an.m1657a(b) ? str2 : C0403i.m1042e(b, str2);
    }

    /* renamed from: e */
    public static void m1099e() {
        C0403i.m1035a(f494z[17], System.currentTimeMillis());
    }

    /* renamed from: e */
    public static void m1100e(String str) {
        C0403i.m1040c(f494z[67], str);
    }

    /* renamed from: f */
    public static String m1101f(Context context) {
        return C0453c.m1378b(context, f494z[56], "");
    }

    /* renamed from: f */
    public static void m1102f(Context context, String str) {
        C0404a.m1091d(context, f494z[59], str);
    }

    /* renamed from: f */
    public static void m1103f(String str) {
        C0403i.m1040c(f494z[52], str);
    }

    /* renamed from: f */
    public static synchronized boolean m1104f() {
        boolean z;
        synchronized (C0404a.class) {
            long b = C0403i.m1037b(f494z[68], 0);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - b > 86400000) {
                C0403i.m1035a(f494z[68], currentTimeMillis);
                z = true;
            } else {
                z = false;
            }
        }
        return z;
    }

    /* renamed from: g */
    public static long m1105g() {
        return C0403i.m1037b(f494z[68], 0);
    }

    /* renamed from: g */
    public static void m1106g(Context context, String str) {
        C0404a.m1091d(context, f494z[72], str);
    }

    /* renamed from: g */
    public static void m1107g(String str) {
        C0453c.m1374a(C0448e.f753e, f494z[15], str);
    }

    /* renamed from: g */
    public static boolean m1108g(Context context) {
        return C0453c.m1379b(context, f494z[61], true);
    }

    /* renamed from: h */
    public static String m1109h() {
        return C0403i.m1041d(f494z[49], null);
    }

    /* renamed from: h */
    public static String m1110h(Context context) {
        return C0403i.m1039c(context, f494z[16], "");
    }

    /* renamed from: h */
    public static void m1111h(Context context, String str) {
        C0404a.m1091d(context, f494z[5], str);
    }

    /* renamed from: h */
    public static void m1112h(String str) {
        C0453c.m1374a(C0448e.f753e, f494z[63], str);
    }

    /* renamed from: i */
    public static int m1113i() {
        return C0403i.m1036b(f494z[38], 0);
    }

    /* renamed from: i */
    public static String m1114i(Context context) {
        return C0403i.m1039c(context, f494z[36], "");
    }

    /* renamed from: i */
    public static void m1115i(Context context, String str) {
        String b = af.m1606b(context);
        if (an.m1657a(b)) {
            b = f494z[13];
        }
        C0404a.m1091d(context, b, str);
    }

    /* renamed from: i */
    public static void m1116i(String str) {
        C0403i.m1040c(f494z[69], str);
    }

    /* renamed from: j */
    public static void m1117j(String str) {
        C0403i.m1040c(f494z[11], str);
    }

    /* renamed from: j */
    public static boolean m1118j() {
        return System.currentTimeMillis() - C0403i.m1037b(f494z[10], 0) > 180000;
    }

    /* renamed from: j */
    public static boolean m1119j(Context context) {
        return C0453c.m1379b(context, f494z[75], true);
    }

    /* renamed from: k */
    public static String m1120k(Context context) {
        return C0404a.m1098e(context, f494z[59], f494z[58]);
    }

    /* renamed from: k */
    public static void m1121k() {
        C0403i.m1035a(f494z[10], System.currentTimeMillis());
    }

    /* renamed from: k */
    public static void m1122k(String str) {
        C0403i.m1040c(f494z[12], str);
    }

    /* renamed from: l */
    public static int m1123l() {
        return C0403i.m1036b(f494z[62], 0);
    }

    /* renamed from: l */
    public static String m1124l(Context context) {
        return C0404a.m1098e(context, f494z[72], "7");
    }

    /* renamed from: l */
    public static void m1125l(String str) {
        C0403i.m1040c(f494z[46], str);
    }

    /* renamed from: m */
    public static long m1126m() {
        long b = C0403i.m1037b(f494z[20], 0);
        return ((C0403i.m1037b(f494z[33], 0) - b) + System.currentTimeMillis()) / 1000;
    }

    /* renamed from: m */
    public static String m1127m(Context context) {
        return C0404a.m1098e(context, f494z[5], f494z[4]);
    }

    /* renamed from: m */
    public static void m1128m(String str) {
        C0403i.m1040c(f494z[3], str);
    }

    /* renamed from: n */
    public static synchronized long m1129n() {
        long abs;
        synchronized (C0404a.class) {
            String str = f494z[79];
            abs = (long) Math.abs(new Random().nextInt(32767));
            if (abs % 2 == 0) {
                abs++;
            }
            abs = C0403i.m1037b(str, abs) % 32767;
            C0403i.m1035a(f494z[79], abs + 2);
            abs += 2;
        }
        return abs;
    }

    /* renamed from: n */
    public static String m1130n(Context context) {
        String b = af.m1606b(context);
        if (an.m1657a(b)) {
            b = f494z[13];
        }
        return C0404a.m1098e(context, b, "");
    }

    /* renamed from: o */
    public static boolean m1131o() {
        return C0453c.m1379b(C0448e.f753e, f494z[71], false);
    }

    /* renamed from: o */
    public static boolean m1132o(Context context) {
        if (C0490b.m1696c(context).toUpperCase().startsWith(f494z[78])) {
            return false;
        }
        if (!C0453c.m1379b(context, f494z[9], false) && !an.m1657a(C0404a.m1130n(context))) {
            return false;
        }
        return System.currentTimeMillis() - C0403i.m1037b(f494z[76], 0) > 3600000;
    }

    /* renamed from: p */
    public static void m1133p() {
        C0453c.m1372a(C0448e.f753e, f494z[14], C0453c.m1376b(C0448e.f753e, f494z[14], -1) + 1);
    }

    /* renamed from: p */
    public static void m1134p(Context context) {
        C0403i.m1045q(context);
        SharedPreferences sharedPreferences = context.getSharedPreferences(f494z[30], 0);
        C0403i.m1038b(context, f494z[6], sharedPreferences.getInt(f494z[45], 0) == 1 ? C0462a.f830a.name() : C0462a.f831b.name());
        C0403i.m1038b(context, f494z[24], sharedPreferences.getString(f494z[57], ""));
        C0403i.m1038b(context, f494z[11], sharedPreferences.getString(f494z[48], ""));
        C0403i.m1038b(context, f494z[12], sharedPreferences.getString(f494z[41], ""));
        C0403i.m1038b(context, f494z[46], sharedPreferences.getString(f494z[31], ""));
        C0403i.m1038b(context, f494z[50], sharedPreferences.getString(f494z[37], ""));
        C0403i.m1038b(context, f494z[52], sharedPreferences.getString(f494z[25], ""));
        C0403i.m1038b(context, f494z[18], sharedPreferences.getString(f494z[51], ""));
        C0403i.m1038b(context, f494z[28], sharedPreferences.getString(f494z[40], ""));
        C0403i.m1038b(context, f494z[49], sharedPreferences.getString(f494z[26], ""));
        C0403i.m1038b(context, f494z[16], sharedPreferences.getString(f494z[16], ""));
        C0403i.m1038b(context, f494z[36], sharedPreferences.getString(f494z[36], ""));
        C0403i.m1032a(context, f494z[42], sharedPreferences.getInt(f494z[29], 0));
        C0403i.m1032a(context, f494z[38], sharedPreferences.getInt(f494z[44], 0));
        C0403i.m1033a(context, f494z[20], sharedPreferences.getLong(f494z[27], 0));
        C0403i.m1033a(context, f494z[34], sharedPreferences.getLong(f494z[32], 0));
        C0403i.m1033a(context, f494z[33], sharedPreferences.getLong(f494z[33], 0));
        C0403i.m1033a(context, f494z[7], sharedPreferences.getLong(f494z[54], 0));
        C0404a.m1137q(sharedPreferences.getString(f494z[39], ""));
        C0404a.m1112h(sharedPreferences.getString(f494z[47], ""));
        C0404a.m1107g(sharedPreferences.getString(f494z[43], ""));
        C0453c.m1374a(context, f494z[35], sharedPreferences.getString(f494z[53], ""));
        C0453c.m1374a(context, f494z[56], sharedPreferences.getString(f494z[23], ""));
        C0453c.m1372a(context, f494z[55], sharedPreferences.getInt(f494z[21], 5));
        C0453c.m1372a(context, f494z[22], sharedPreferences.getInt(f494z[22], 0));
    }

    /* renamed from: p */
    private static void m1135p(String str) {
        C0448e.f756h = str;
        C0453c.m1374a(C0448e.f753e, f494z[0], str);
    }

    /* renamed from: q */
    public static int m1136q() {
        return C0403i.m1036b(f494z[8], 290);
    }

    /* renamed from: q */
    private static void m1137q(String str) {
        C0453c.m1374a(C0448e.f753e, f494z[73], str);
    }

    /* renamed from: r */
    public static void m1138r() {
        C0403i.m1034a(f494z[8], 86400);
    }

    /* renamed from: s */
    public static String m1139s() {
        return C0403i.m1041d(f494z[18], f494z[19]);
    }

    /* renamed from: t */
    public static int m1140t() {
        return C0403i.m1036b(f494z[42], 7000);
    }

    /* renamed from: u */
    public static String m1141u() {
        return C0453c.m1378b(C0448e.f753e, f494z[28], null);
    }

    /* renamed from: v */
    public static String m1142v() {
        return C0403i.m1041d(f494z[52], null);
    }

    /* renamed from: w */
    public static long m1143w() {
        return C0403i.m1037b(f494z[2], -1);
    }

    /* renamed from: x */
    public static long m1144x() {
        long j = C0448e.f755g;
        if (j != 0) {
            return j;
        }
        j = C0453c.m1377b(C0448e.f753e, f494z[70], 0);
        C0448e.f755g = j;
        return j;
    }

    /* renamed from: y */
    public static boolean m1145y() {
        return C0404a.m1144x() > 0 && !an.m1657a(C0404a.m1047B());
    }

    /* renamed from: z */
    public static void m1146z() {
        C0404a.m1068a(Long.valueOf(0));
        C0404a.m1135p("");
        C0404a.m1137q("");
        C0404a.m1107g("");
        C0403i.m1043n(f494z[60]);
    }
}
