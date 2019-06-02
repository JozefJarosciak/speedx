package cn.jpush.android.service;

import android.app.Application;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Process;
import cn.jpush.android.C0404a;
import cn.jpush.android.C0448e;
import cn.jpush.android.api.BasicPushNotificationBuilder;
import cn.jpush.android.api.C0406b;
import cn.jpush.android.api.C0408d;
import cn.jpush.android.api.C0417m;
import cn.jpush.android.api.TagAliasCallback;
import cn.jpush.android.data.C0429c;
import cn.jpush.android.helpers.C0452b;
import cn.jpush.android.util.ac;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

public class ServiceInterface {
    /* renamed from: a */
    private static boolean f827a = false;
    /* renamed from: z */
    private static final String[] f828z;

    public class TagAliasOperator extends BroadcastReceiver {
        /* renamed from: a */
        private static TagAliasOperator f822a;
        /* renamed from: c */
        private static Object f823c = new Object();
        /* renamed from: z */
        private static final String[] f824z;
        /* renamed from: b */
        private ConcurrentHashMap<Long, C0406b> f825b = new ConcurrentHashMap();
        /* renamed from: d */
        private AtomicBoolean f826d = new AtomicBoolean(false);

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        static {
            /*
            r0 = 14;
            r3 = new java.lang.String[r0];
            r2 = 0;
            r1 = "$g<vk\u0000}\fV`(r\u001b7i{";
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
                case 0: goto L_0x00bf;
                case 1: goto L_0x00c3;
                case 2: goto L_0x00c7;
                case 3: goto L_0x00cb;
                default: goto L_0x001e;
            };
        L_0x001e:
            r9 = 23;
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
                default: goto L_0x003c;
            };
        L_0x003c:
            r3[r2] = r1;
            r2 = 1;
            r1 = "o/=\u0002gy2{Fvb%a\u0007~hoz\u0006ci/gFCM\u0006L)[E\u0000@7CE\fV'BX";
            r0 = 0;
            r3 = r4;
            goto L_0x0009;
        L_0x0044:
            r3[r2] = r1;
            r2 = 2;
            r1 = "o/=\u0002gy2{Fvb%a\u0007~hoz\u0006ci/gFCM\u0006L)[E\u0000@7TM\r_*VO\n";
            r0 = 1;
            r3 = r4;
            goto L_0x0009;
        L_0x004c:
            r3[r2] = r1;
            r2 = 3;
            r1 = "c5{\re,$k\u000br|5z\u0007y";
            r0 = 2;
            r3 = r4;
            goto L_0x0009;
        L_0x0054:
            r3[r2] = r1;
            r2 = 4;
            r1 = "_$a\u001e~o$Z\u0006ci3u\tti";
            r0 = 3;
            r3 = r4;
            goto L_0x0009;
        L_0x005c:
            r3[r2] = r1;
            r2 = 5;
            r1 = "^$p\r~z$aHyc53\u001ark(`\u001cr~$wD7o }\u0006xxap\t{`af\u0006ei&z\u001bci3A\rti(e\re";
            r0 = 4;
            r3 = r4;
            goto L_0x0009;
        L_0x0064:
            r3[r2] = r1;
            r2 = 6;
            r1 = ",5r\u000fV`(r\u001bTm-\nvo*`H-";
            r0 = 5;
            r3 = r4;
            goto L_0x0009;
        L_0x006c:
            r3[r2] = r1;
            r2 = 7;
            r1 = "x t\t{e `Htm-\nvo*3\u0001d,/f\u0004{7aa\u0001s1";
            r0 = 6;
            r3 = r4;
            goto L_0x0009;
        L_0x0074:
            r3[r2] = r1;
            r2 = 8;
            r1 = "_$g){e `)yh\u0015r\u000fd,5z\u0005rc4gHee%)";
            r0 = 7;
            r3 = r4;
            goto L_0x0009;
        L_0x007d:
            r3[r2] = r1;
            r2 = 9;
            r1 = "_$g){e `)yh\u0015r\u000fd,'z\u0006~)3R7i3a\u0007eO.w\r-";
            r0 = 8;
            r3 = r4;
            goto L_0x0009;
        L_0x0087:
            r3[r2] = r1;
            r2 = 10;
            r1 = "x t\t{e `7di0z\f";
            r0 = 9;
            r3 = r4;
            goto L_0x0009;
        L_0x0092:
            r3[r2] = r1;
            r2 = 11;
            r1 = "x t\t{e `7r~3|\u001atc%v";
            r0 = 10;
            r3 = r4;
            goto L_0x0009;
        L_0x009d:
            r3[r2] = r1;
            r2 = 12;
            r1 = ",3z\f-";
            r0 = 11;
            r3 = r4;
            goto L_0x0009;
        L_0x00a8:
            r3[r2] = r1;
            r2 = 13;
            r1 = "X t){e `'gi3r\u001cx~a|\u0006Ei\"v\u0001aiaz\u0006ci/gH~a}\u001d{`";
            r0 = 12;
            r3 = r4;
            goto L_0x0009;
        L_0x00b3:
            r3[r2] = r1;
            f824z = r4;
            r0 = new java.lang.Object;
            r0.<init>();
            f823c = r0;
            return;
        L_0x00bf:
            r9 = 12;
            goto L_0x0020;
        L_0x00c3:
            r9 = 65;
            goto L_0x0020;
        L_0x00c7:
            r9 = 19;
            goto L_0x0020;
        L_0x00cb:
            r9 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
            goto L_0x0020;
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.ServiceInterface.TagAliasOperator.<clinit>():void");
        }

        private TagAliasOperator() {
        }

        /* renamed from: a */
        public static TagAliasOperator m1452a() {
            synchronized (f823c) {
                if (f822a == null) {
                    f822a = new TagAliasOperator();
                }
            }
            return f822a;
        }

        /* renamed from: b */
        private C0406b m1453b(long j) {
            return (C0406b) this.f825b.get(Long.valueOf(j));
        }

        /* renamed from: b */
        private synchronized void m1454b(Context context) {
            if (this.f826d.get() && this.f825b != null && this.f825b.isEmpty()) {
                try {
                    context.unregisterReceiver(this);
                } catch (Throwable e) {
                    ac.m1579a(f824z[4], f824z[5], e);
                } catch (Throwable e2) {
                    ac.m1579a(f824z[4], f824z[3], e2);
                }
                this.f826d.set(false);
            }
            ac.m1576a();
        }

        /* renamed from: a */
        public final void m1455a(long j) {
            this.f825b.remove(Long.valueOf(j));
        }

        /* renamed from: a */
        public final void m1456a(Context context) {
            if (this.f826d.get()) {
                ac.m1581b();
                return;
            }
            try {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addCategory(C0448e.f751c);
                intentFilter.addAction(f824z[1]);
                intentFilter.addAction(f824z[2]);
                context.registerReceiver(this, intentFilter);
                this.f826d.set(true);
            } catch (Exception e) {
                new StringBuilder(f824z[0]).append(e.getMessage());
                ac.m1588e();
            }
        }

        /* renamed from: a */
        public final void m1457a(Long l, C0406b c0406b) {
            this.f825b.put(l, c0406b);
        }

        public void onReceive(Context context, Intent intent) {
            if (intent == null) {
                ac.m1587d(f824z[4], f824z[13]);
                return;
            }
            long longExtra = intent.getLongExtra(f824z[10], -1);
            int intExtra = intent.getIntExtra(f824z[11], 0);
            if (longExtra == -1) {
                ac.m1586d();
                return;
            }
            new StringBuilder(f824z[12]).append(longExtra).append(f824z[6]).append(this.f825b.toString());
            ac.m1576a();
            if (f824z[1].equals(intent.getAction())) {
                new StringBuilder(f824z[8]).append(longExtra);
                ac.m1576a();
                C0406b b = m1453b(longExtra);
                if (b != null) {
                    TagAliasCallback tagAliasCallback = b.f509c;
                    m1455a(longExtra);
                    if (tagAliasCallback != null) {
                        tagAliasCallback.gotResult(C0408d.f516b, b.f507a, b.f508b);
                    }
                } else {
                    new StringBuilder(f824z[7]).append(longExtra);
                    ac.m1586d();
                }
            } else {
                new StringBuilder(f824z[9]).append(intExtra).append(f824z[12]).append(longExtra);
                ac.m1576a();
                C0406b b2 = m1453b(longExtra);
                if (b2 != null) {
                    TagAliasCallback tagAliasCallback2 = b2.f509c;
                    m1455a(longExtra);
                    if (tagAliasCallback2 != null) {
                        tagAliasCallback2.gotResult(intExtra, b2.f507a, b2.f508b);
                    }
                } else {
                    new StringBuilder(f824z[7]).append(longExtra);
                    ac.m1586d();
                }
            }
            m1452a().m1454b(context);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 41;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\"i]0H\u0019rI0";
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
            case 0: goto L_0x01e4;
            case 1: goto L_0x01e8;
            case 2: goto L_0x01ec;
            case 3: goto L_0x01f0;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 60;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\"i]0H\u001ctR1";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "4sX6q8sO";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "4sX\nS$o";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "3rX;";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "2s\u0012(L$nTl]?yN-U53U,H4sHl\u001eSr\u0007\u0005Tj\u000bh\bB\n}\u001fZy";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "2rR,Y2iU-R|nH#H4";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "?hQ Y#=U,\u001c hY7Yk=";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0002xN4U2xu,H4oZ#_4";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\"xHbR>iU$U2|H+S?=Q#DqsI/\u001ck=";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "?rH+Z8~]6U>sc/])sI/";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "<hP6U\u000eiE2Y";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "2s\u0012(L$nTl]?yN-U53U,H4sHlq\u0004Qh\u000bc\u0001Os\u0001y\u0002N";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "5x_0Y0nY\fS%tZ+_0iU-Rk";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\"xH\fS%tZ+_0iU-R\u001fhQ Y#=\u0011b_>sH'D%=U1\u001c?hP.\u001d";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "2u],[4BL#_:|['R0pY";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "?rH+Z8~]6U>sc I8yP'N\u000etX";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u001fhP.\u001c2rR6Y)i\u0010bL=x]1YqtR+HqWl7O9<";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "?rH+Z8~]6U>sc I8yP'N";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "0mL";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "\"xN4U2x\u001c#P#x]&EqnH-L";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "2s\u0012(L$nTl]?yN-U53U,H4sHlo\u0005Rl\u0012i\u0002U";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "#i_";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "2s\u0012(L$nTl]?yN-U53U,H4sHln\u0005^";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "#i_\u001dX4q];";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "!hO*c\"iS2L4y";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "0qU#O";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "2s\u0012(L$nTl]?yN-U53U,H4sHl}\u001dT}\u0011c\u0005\\{\u0011";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "%|[b]=t]1\u001c#tXb\u0001q";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "%|[1";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "4'";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "\"xM\u001dU5";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        r2 = 32;
        r1 = "\"tP'R2xc2I\"uc6U<x";
        r0 = 31;
        r3 = r4;
        goto L_0x0009;
    L_0x0184:
        r3[r2] = r1;
        r2 = 33;
        r1 = "c3\rl\u000b";
        r0 = 32;
        r3 = r4;
        goto L_0x0009;
    L_0x018f:
        r3[r2] = r1;
        r2 = 34;
        r1 = "2s\u0012(L$nTl]?yN-U53U,H4sHlu\u001fTh";
        r0 = 33;
        r3 = r4;
        goto L_0x0009;
    L_0x019a:
        r3[r2] = r1;
        r2 = 35;
        r1 = "\u0005uYbO4oJ+_4=U1\u001c\"iS2L4y\u0010bU%=K+P==[+J4=I2\u001c0qPbH9x\u001c#_%tS,OqhR6U==E-Iq~].PqoY1I<xl7O9=Q'H9rXbH>=N'O$pYbH9x\u001c1Y#kU!Y";
        r0 = 34;
        r3 = r4;
        goto L_0x0009;
    L_0x01a5:
        r3[r2] = r1;
        r2 = 36;
        r1 = "2s\u0012(L$nTl]?yN-U53U,H4sHln\u0014Nh\rn\u0014Mi\u0011t";
        r0 = 35;
        r3 = r4;
        goto L_0x0009;
    L_0x01b0:
        r3[r2] = r1;
        r2 = 37;
        r1 = "\"xN4U2x\u001c+OqoI,R8s[b]=oY#X(";
        r0 = 36;
        r3 = r4;
        goto L_0x0009;
    L_0x01bb:
        r3[r2] = r1;
        r2 = 38;
        r1 = "2qY#N\u0010qP\fS%tZ+_0iU-Rq0\u001c!S?iY:HqtObR$qPc";
        r0 = 37;
        r3 = r4;
        goto L_0x0009;
    L_0x01c6:
        r3[r2] = r1;
        r2 = 39;
        r1 = "}=O6S!IE2Yk";
        r0 = 38;
        r3 = r4;
        goto L_0x0009;
    L_0x01d1:
        r3[r2] = r1;
        r2 = 40;
        r1 = "!tXx";
        r0 = 39;
        r3 = r4;
        goto L_0x0009;
    L_0x01dc:
        r3[r2] = r1;
        f828z = r4;
        r0 = 0;
        f827a = r0;
        return;
    L_0x01e4:
        r9 = 81;
        goto L_0x0020;
    L_0x01e8:
        r9 = 29;
        goto L_0x0020;
    L_0x01ec:
        r9 = 60;
        goto L_0x0020;
    L_0x01f0:
        r9 = 66;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.ServiceInterface.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1458a() {
        return f828z[33];
    }

    /* renamed from: a */
    public static void m1459a(Context context) {
        if (!m1476e(context)) {
            try {
                Intent intent = new Intent(context, PushService.class);
                intent.setAction(f828z[34]);
                intent.putExtra(f828z[19], context.getPackageName());
                context.startService(intent);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    /* renamed from: a */
    public static void m1460a(Context context, int i) {
        if (1 == m1479h(context)) {
            ac.m1582b(f828z[8], f828z[20]);
            return;
        }
        m1470b(context, false);
        C0404a.m1076b(context, 1);
        Intent intent = new Intent(context, PushService.class);
        intent.setAction(f828z[21]);
        intent.putExtra(f828z[19], context.getPackageName());
        context.startService(intent);
    }

    /* renamed from: a */
    public static void m1461a(Context context, C0429c c0429c) {
        ac.m1576a();
        Intent intent = new Intent(context, DownloadService.class);
        intent.putExtra(f828z[4], c0429c);
        context.startService(intent);
    }

    /* renamed from: a */
    public static void m1462a(Context context, Integer num, BasicPushNotificationBuilder basicPushNotificationBuilder) {
        if (context == null) {
            ac.m1589e(f828z[8], f828z[17]);
        } else if (m1471b() || !C0448e.f762n) {
            C0404a.m1066a(context, num, basicPushNotificationBuilder.toString());
        } else {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(f828z[12]);
            Bundle bundle = new Bundle();
            bundle.putInt(f828z[11], 1);
            bundle.putString(f828z[16], num);
            bundle.putString(f828z[18], basicPushNotificationBuilder.toString());
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    /* renamed from: a */
    public static void m1463a(Context context, String str) {
        if (context != null && !m1476e(context)) {
            if (m1471b() || !C0448e.f762n) {
                C0404a.m1065a(context, str);
                return;
            }
            Intent intent = new Intent(context, PushService.class);
            Bundle bundle = new Bundle();
            bundle.putInt(f828z[11], 4);
            bundle.putString(f828z[32], str);
            intent.setAction(f828z[12]);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    /* renamed from: a */
    public static void m1464a(Context context, String str, String str2, C0406b c0406b) {
        if (!m1476e(context)) {
            if (!(context instanceof Application)) {
                context = context.getApplicationContext();
            }
            if (C0448e.m1359a(context)) {
                long n = C0404a.m1129n();
                if (!(c0406b == null || c0406b.f509c == null)) {
                    TagAliasOperator.m1452a().m1457a(Long.valueOf(n), c0406b);
                }
                new StringBuilder(f828z[28]).append(n);
                ac.m1584c();
                TagAliasOperator.m1452a().m1456a(context);
                try {
                    Intent intent = new Intent(context, PushService.class);
                    intent.setAction(f828z[27]);
                    intent.putExtra(f828z[26], str);
                    intent.putExtra(f828z[29], str2);
                    intent.putExtra(f828z[31], n);
                    context.startService(intent);
                } catch (SecurityException e) {
                    new StringBuilder(f828z[30]).append(e.getMessage());
                    ac.m1586d();
                    if (c0406b != null && c0406b.f509c != null) {
                        c0406b.f509c.gotResult(C0408d.f523i, c0406b.f507a, c0406b.f508b);
                        TagAliasOperator.m1452a().m1455a(n);
                    }
                } catch (Exception e2) {
                    new StringBuilder(f828z[30]).append(e2.getMessage());
                    ac.m1586d();
                    if (c0406b != null && c0406b.f509c != null) {
                        c0406b.f509c.gotResult(C0408d.f523i, c0406b.f507a, c0406b.f508b);
                        TagAliasOperator.m1452a().m1455a(n);
                    }
                }
            }
        }
    }

    /* renamed from: a */
    public static void m1465a(Context context, boolean z) {
        if (context != null) {
            if (m1471b() || !C0448e.f762n) {
                C0404a.m1067a(context, z);
                return;
            }
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(f828z[12]);
            Bundle bundle = new Bundle();
            bundle.putInt(f828z[11], 5);
            bundle.putBoolean(f828z[25], z);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    /* renamed from: a */
    public static boolean m1466a(Context context, int i, int i2, int i3, int i4) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(f828z[0], i);
            jSONObject.put(f828z[1], i2);
            jSONObject.put(f828z[3], i3);
            jSONObject.put(f828z[2], i4);
            m1463a(context, jSONObject.toString());
            return true;
        } catch (JSONException e) {
            return false;
        }
    }

    /* renamed from: b */
    public static void m1467b(Context context) {
        if (!m1476e(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(f828z[23]);
            Bundle bundle = new Bundle();
            bundle.putString(f828z[22], f828z[22]);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    /* renamed from: b */
    public static void m1468b(Context context, int i) {
        if (m1479h(context) == 0) {
            ac.m1582b(f828z[8], f828z[37]);
            return;
        }
        m1470b(context, true);
        C0404a.m1076b(context, 0);
        Intent intent = new Intent(context, PushService.class);
        intent.setAction(f828z[36]);
        intent.putExtra(f828z[19], context.getPackageName());
        context.startService(intent);
    }

    /* renamed from: b */
    static void m1469b(Context context, String str) {
        Intent intent = new Intent(context, PushService.class);
        intent.setAction(f828z[12]);
        Bundle bundle = new Bundle();
        bundle.putInt(f828z[11], 11);
        bundle.putString(f828z[15], str);
        intent.putExtras(bundle);
        context.startService(intent);
    }

    /* renamed from: b */
    static void m1470b(Context context, boolean z) {
        PackageManager packageManager = context.getPackageManager();
        ComponentName componentName = new ComponentName(context.getApplicationContext(), PushReceiver.class);
        ComponentName componentName2 = new ComponentName(context.getApplicationContext(), AlarmReceiver.class);
        if (z) {
            ac.m1576a();
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
            packageManager.setComponentEnabledSetting(componentName2, 1, 1);
            return;
        }
        ac.m1576a();
        packageManager.setComponentEnabledSetting(componentName, 2, 1);
        packageManager.setComponentEnabledSetting(componentName2, 2, 1);
    }

    /* renamed from: b */
    public static boolean m1471b() {
        return C0448e.f763o != null;
    }

    /* renamed from: c */
    public static void m1472c(Context context) {
        if (context == null) {
            ac.m1587d(f828z[8], f828z[38]);
        } else if (m1471b() || !C0448e.f762n) {
            C0417m.m1212a(context.getApplicationContext());
        } else {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(f828z[12]);
            Bundle bundle = new Bundle();
            bundle.putInt(f828z[11], 10);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    /* renamed from: c */
    public static void m1473c(Context context, int i) {
        if (!m1476e(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(f828z[23]);
            Bundle bundle = new Bundle();
            bundle.putString(f828z[22], f828z[22]);
            bundle.putInt(f828z[24], i);
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    /* renamed from: d */
    public static void m1474d(Context context, int i) {
        if (context == null) {
            ac.m1587d(f828z[8], f828z[14]);
            return;
        }
        new StringBuilder(f828z[9]).append(i);
        ac.m1576a();
        if (m1471b() || !C0448e.f762n) {
            int b = C0452b.m1370b();
            new StringBuilder(f828z[7]).append(b);
            ac.m1576a();
            if (i < b) {
                b -= i;
                new StringBuilder(f828z[13]).append(b);
                ac.m1576a();
                C0417m.m1213a(context, b);
            }
            C0404a.m1061a(context, i);
            return;
        }
        Intent intent = new Intent(context, PushService.class);
        intent.setAction(f828z[12]);
        Bundle bundle = new Bundle();
        bundle.putInt(f828z[11], 2);
        bundle.putInt(f828z[10], i);
        intent.putExtras(bundle);
        context.startService(intent);
    }

    /* renamed from: d */
    public static boolean m1475d(Context context) {
        return m1479h(context) > 0;
    }

    /* renamed from: e */
    public static boolean m1476e(Context context) {
        boolean d = m1475d(context);
        if (d) {
            ac.m1582b(f828z[8], f828z[35]);
        }
        return d;
    }

    /* renamed from: f */
    public static void m1477f(Context context) {
        if (!m1476e(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(f828z[5]);
            Bundle bundle = new Bundle();
            bundle.putString(f828z[6], C0462a.f830a.name());
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    /* renamed from: g */
    public static void m1478g(Context context) {
        if (!m1476e(context)) {
            Intent intent = new Intent(context, PushService.class);
            intent.setAction(f828z[5]);
            Bundle bundle = new Bundle();
            bundle.putString(f828z[6], C0462a.f831b.name());
            intent.putExtras(bundle);
            context.startService(intent);
        }
    }

    /* renamed from: h */
    private static int m1479h(Context context) {
        int c = C0404a.m1082c(context);
        new StringBuilder(f828z[40]).append(Process.myPid()).append(f828z[39]).append(c);
        ac.m1581b();
        return c;
    }
}
