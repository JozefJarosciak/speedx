package cn.jpush.android.service;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import cn.jpush.android.C0404a;
import cn.jpush.android.C0448e;
import cn.jpush.android.api.C0408d;
import cn.jpush.android.helpers.C0460j;
import cn.jpush.android.helpers.ConnectingHelper;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import cn.jpush.p005b.p006a.p007a.C0514i;
import cn.jpush.p005b.p006a.p007a.C0517h;
import cn.jpush.p005b.p006a.p007a.C0518c;
import cn.jpush.p005b.p006a.p007a.C0525m;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;

/* renamed from: cn.jpush.android.service.n */
public final class C0475n extends HandlerThread {
    /* renamed from: z */
    private static final String[] f877z;
    /* renamed from: a */
    private Map<Long, C0477p> f878a = new ConcurrentHashMap();
    /* renamed from: b */
    private Deque<C0477p> f879b = new LinkedBlockingDeque();
    /* renamed from: c */
    private Deque<C0477p> f880c = new LinkedBlockingDeque();
    /* renamed from: d */
    private Context f881d;
    /* renamed from: e */
    private Handler f882e;
    /* renamed from: f */
    private Handler f883f;
    /* renamed from: g */
    private boolean f884g = false;
    /* renamed from: h */
    private int f885h = 0;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 32;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "Y\u0006\u0014}qvEM4{v\u00013qpl1\ty{w\u0010\u0014438\u0017\tp$";
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
            case 0: goto L_0x017e;
            case 1: goto L_0x0182;
            case 2: goto L_0x0186;
            case 3: goto L_0x018a;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 30;
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
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "4E\u0012qmh\n\u000eg{\"";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "Y\u0006\u0014}qvEM4vy\u000b\u0004x{J\u0000\u0013dqv\u0016\u0005438\u0006\u000fzp}\u0006\u0014}qv_";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "Y\u0006\u0014}qvEM4nj\f\u000e`M}\u000b\u0014Ek}\u0010\u0005438\u0016\tn{\"";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "Y\u0006\u0014}qvEM4qv)\u000fsy}\u0001)z";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "J\u0000\u0011a{k\u0011\tzyL\r\u0012q|";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "4E\u0014|l}\u0004\u0004]z\"";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "4E\u0014}s}\n\u0015`$";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "Y\u0006\u0014}qvEM4m}\u000b\u0004F{i\u0010\u0005gjQ\u000b\u0014qlv\u0004\f438\u0006\u000fzp}\u0006\u0014}qv_";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "q\b?ws|";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "Y\u0006\u0014}qvEM4qv7\u0005ek}\u0016\u0014@wu\u0000\u000faj8H@";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "l\u0004\u0007urq\u0004\u0013Km}\u0014\tp";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "l\u0004\u0007urq\u0004\u0013K{j\u0017\u000ff}w\u0001\u0005";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "Y\u0006\u0014}qvEM4qv,\r@wu\u0000\u000fajL\n2q}}\f\u0016ql4E\ty]u\u0001Z";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "{\u000bN~nm\u0016\b:wuK\u0001zzj\n\tp0y\u0006\u0014}qvK)YAJ 3DQV6%";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "{\u000bN~nm\u0016\b:v\u0001\u0012{w|K\tzj}\u000b\u0014:JY\"?URQ$3KJQ(%[KL";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "j\f\u0004";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "q\b?`wu\u0000\u000faj";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "Y\u0006\u0014}qvEM4l}\u0016\u0014{l}6\u0005zjI\u0010\u0005a{8H@g{v\u00111a{m\u00003}d}_";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "Y\u0006\u0014}qvEM4qv!\tg}w\u000b\u000eq}l\u0000\u0004";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "J\u0000\u0011a{k\u0011\tzyL\r\u0012q|E\u0013`j\u0011\u0005p>5E\u0014|l}\u0004\u0004]z\"";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "Y\u0006\u0014}qvEM4l}\u0016\u0005zzJ\u0000\u0011a{k\u0011\tzyI\u0010\u0005a{8H@gwb\u0000Z";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "8H@";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "Y\u0006\u0014}qvEM4nj\f\u000e`L}\u0014\u0015qml\f\u000esOm\u0000\u0015q>5E\u0013}d}_";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "Y\u0006\u0014}qvEM4nj\f\u000e`L}\u0014\u0015qml\f\u000es]y\u0006\bq>5E\u0013}d}_";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "J\u0000\u0011a{k\u0011@dj\u0004\rg>5E\u0003yz\"";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        r2 = 26;
        r1 = "4E\naw|_";
        r0 = 25;
        r3 = r4;
        goto L_0x0009;
    L_0x0142:
        r3[r2] = r1;
        r2 = 27;
        r1 = "4E\u0013}z\"";
        r0 = 26;
        r3 = r4;
        goto L_0x0009;
    L_0x014d:
        r3[r2] = r1;
        r2 = 28;
        r1 = "p\u0000\u0001fjz\u0000\u0001`>5E\u0006x_";
        r0 = 27;
        r3 = r4;
        goto L_0x0009;
    L_0x0158:
        r3[r2] = r1;
        r2 = 29;
        r1 = "Y\u0006\u0014}qvEM4m}\u000b\u0004Wqu\b\u0001zzO\f\u0014|Rw\u0002\u0007qzQ\u000b";
        r0 = 28;
        r3 = r4;
        goto L_0x0009;
    L_0x0163:
        r3[r2] = r1;
        r2 = 30;
        r1 = "J\u0000\u0014fg8\u0011\u000f4m}\u000b\u00044l}\u0014\u0015qmlEM4";
        r0 = 29;
        r3 = r4;
        goto L_0x0009;
    L_0x016e:
        r3[r2] = r1;
        r2 = 31;
        r1 = "Y\u0006\u0014}qvEM4qv6\u0005zjL\f\rqqm\u0011@9>";
        r0 = 30;
        r3 = r4;
        goto L_0x0009;
    L_0x0179:
        r3[r2] = r1;
        f877z = r4;
        return;
    L_0x017e:
        r9 = 24;
        goto L_0x0020;
    L_0x0182:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0020;
    L_0x0186:
        r9 = 96;
        goto L_0x0020;
    L_0x018a:
        r9 = 20;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.n.<clinit>():void");
    }

    public C0475n(Context context, Handler handler) {
        super(f877z[5]);
        this.f881d = context;
        this.f883f = handler;
        start();
        this.f882e = new C0476o(getLooper(), this);
    }

    /* renamed from: a */
    private synchronized C0477p m1514a(Long l) {
        C0477p c0477p;
        ac.m1581b();
        c0477p = null;
        for (C0477p c0477p2 : this.f880c) {
            C0477p c0477p22;
            if (l.longValue() == c0477p22.f889a.m1828f().m1848a().longValue()) {
                this.f880c.remove(c0477p22);
            } else {
                c0477p22 = c0477p;
            }
            c0477p = c0477p22;
        }
        return c0477p;
    }

    /* renamed from: a */
    static /* synthetic */ void m1516a(C0475n c0475n, long j, Object obj) {
        C0514i c0514i = (C0514i) obj;
        new StringBuilder(f877z[2]).append(j).append(f877z[1]).append(c0514i.toString());
        ac.m1581b();
        if (j != C0472k.f865a.get()) {
            ac.m1586d();
        }
        Object a = c0514i.m1828f().m1848a();
        C0477p a2 = c0475n.m1514a((Long) a);
        if (a2 == null) {
            ac.m1586d();
        } else {
            a = a2.f889a.m1828f().m1848a();
            new StringBuilder(f877z[0]).append(a);
            ac.m1581b();
            c0475n.f882e.removeMessages(7404, a);
        }
        C0477p c0477p = (C0477p) c0475n.f878a.get(a);
        if (c0477p != null) {
            c0475n.m1526d(c0477p);
        } else {
            ac.m1586d();
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m1517a(C0475n c0475n, C0477p c0477p) {
        new StringBuilder(f877z[31]).append(c0477p.toString());
        ac.m1576a();
        c0475n.m1514a(c0477p.f889a.m1828f().m1848a());
        if (c0477p.f890b > 0) {
            if (c0475n.f884g) {
                new StringBuilder(f877z[30]).append(c0477p.toString());
                ac.m1576a();
                c0477p.m1531a();
                c0475n.m1522b(c0477p);
            } else {
                ac.m1576a();
                c0475n.f879b.offerFirst(c0477p);
            }
            if (c0477p.f891c >= 2) {
                c0475n.f883f.sendEmptyMessageDelayed(1005, 2000);
                return;
            }
            return;
        }
        c0475n.m1519a(c0477p);
    }

    /* renamed from: a */
    static /* synthetic */ void m1518a(C0475n c0475n, C0517h c0517h, int i) {
        ac.m1582b(f877z[5], new StringBuilder(f877z[8]).append(C0472k.f865a.get()).append(f877z[7]).append(i).append(f877z[6]).append(Thread.currentThread().getId()).toString());
        c0517h.toString();
        ac.m1576a();
        Long a = c0517h.m1828f().m1848a();
        Object obj = (c0517h.m1827e() == 100 && C0460j.m1419a(((C0518c) c0517h).mo2234a().m1890b())) ? 1 : null;
        C0477p c0477p = new C0477p(c0517h, i);
        if (obj == null) {
            c0475n.f878a.put(a, c0477p);
        }
        if (i > 10000) {
            ac.m1576a();
            c0475n.f882e.sendMessageDelayed(Message.obtain(c0475n.f882e, 7403, c0477p.f889a.m1828f().m1848a()), (long) c0477p.f890b);
        }
        if (C0472k.f866b.get() || !c0475n.f884g) {
            ac.m1584c();
            c0475n.f879b.offerLast(c0477p);
            return;
        }
        c0477p.m1531a();
        c0475n.m1522b(c0477p);
    }

    /* renamed from: a */
    private void m1519a(C0477p c0477p) {
        new StringBuilder(f877z[10]).append(c0477p.toString());
        ac.m1581b();
        int e = c0477p.f889a.m1827e();
        Long a = c0477p.f889a.m1828f().m1848a();
        m1526d(c0477p);
        switch (e) {
            case 2:
                return;
            case 10:
                C0517h c0517h = c0477p.f889a;
                ac.m1581b();
                Intent intent = new Intent();
                intent.addCategory(C0448e.f751c);
                intent.setAction(f877z[15]);
                intent.putExtra(f877z[12], C0408d.f516b);
                intent.putExtra(f877z[11], c0517h.m1828f().m1848a().longValue());
                this.f881d.sendBroadcast(intent);
                return;
            case 100:
                e = ((C0518c) c0477p.f889a).mo2234a().m1890b();
                long longValue = a.longValue();
                new StringBuilder(f877z[13]).append(e);
                ac.m1581b();
                switch (e) {
                    case 1:
                        C0460j.m1421c(C0448e.f753e);
                        break;
                }
                Bundle bundle = new Bundle();
                bundle.putBoolean(f877z[17], true);
                bundle.putLong(f877z[16], longValue);
                bundle.putInt(f877z[9], e);
                C0490b.m1675a(this.f881d, f877z[14], bundle);
                return;
            default:
                ac.m1581b();
                return;
        }
    }

    /* renamed from: b */
    static /* synthetic */ void m1520b(C0475n c0475n) {
        new StringBuilder(f877z[21]).append(c0475n.f879b.size());
        ac.m1581b();
        c0475n.m1523c();
        c0475n.m1525d();
        while (true) {
            C0477p c0477p = (C0477p) c0475n.f879b.pollFirst();
            if (c0477p == null) {
                return;
            }
            if (c0477p.f889a.m1827e() == 2) {
                c0475n.f879b.remove(c0477p);
                c0475n.f878a.remove(c0477p.f889a.m1828f().m1848a());
            } else {
                c0477p.m1531a();
                c0475n.m1522b(c0477p);
            }
        }
    }

    /* renamed from: b */
    private void m1522b(C0477p c0477p) {
        ac.m1582b(f877z[5], f877z[29]);
        ac.m1578a(f877z[5], c0477p.toString());
        C0517h c0517h = c0477p.f889a;
        Long a = c0517h.m1828f().m1848a();
        int e = c0517h.m1827e();
        long x = C0404a.m1144x();
        ac.m1582b(f877z[5], new StringBuilder(f877z[25]).append(e).append(f877z[27]).append(this.f885h).append(f877z[26]).append(x).toString());
        switch (e) {
            case 2:
                short iMLoginFlag = ConnectingHelper.getIMLoginFlag();
                new StringBuilder(f877z[28]).append(iMLoginFlag);
                ac.m1581b();
                PushProtocol.HbJPush(C0472k.f865a.get(), a.longValue(), this.f885h, x, iMLoginFlag);
                e = 0;
                break;
            case 10:
                PushProtocol.TagAlias(C0472k.f865a.get(), a.longValue(), this.f885h, x, C0448e.f754f, ((C0525m) c0517h).mo2234a());
                e = 0;
                break;
            case 100:
                c0517h.m1840d(this.f885h);
                c0517h.m1839b(x);
                PushProtocol.IMProtocol(C0472k.f865a.get(), c0517h.m1829g(), 0);
                if (!C0460j.m1419a(((C0518c) c0517h).mo2234a().m1890b())) {
                    e = 0;
                    break;
                } else {
                    e = 1;
                    break;
                }
            default:
                e = 0;
                ac.m1586d();
                break;
        }
        if (e == 0) {
            m1524c(c0477p);
            ac.m1581b();
            this.f882e.sendMessageDelayed(Message.obtain(this.f882e, 7404, a), 9800);
            return;
        }
        ac.m1576a();
    }

    /* renamed from: c */
    private void m1523c() {
        int i = 0;
        int size = this.f879b != null ? this.f879b.size() : 0;
        new StringBuilder(f877z[23]).append(size);
        ac.m1576a();
        for (C0477p c0477p : this.f879b) {
            i++;
            new StringBuilder().append(i).append("/").append(size).append(f877z[22]).append(c0477p.toString());
            ac.m1576a();
        }
    }

    /* renamed from: c */
    private synchronized void m1524c(C0477p c0477p) {
        Object obj;
        ac.m1581b();
        long longValue = c0477p.f889a.m1828f().m1848a().longValue();
        for (C0477p c0477p2 : this.f880c) {
            if (c0477p2.f889a.m1828f().m1848a().longValue() == longValue) {
                obj = 1;
                break;
            }
        }
        obj = null;
        if (obj == null) {
            this.f880c.offerLast(c0477p);
            if (this.f880c != null) {
                new StringBuilder(f877z[3]).append(this.f880c.size());
                ac.m1576a();
            }
        }
    }

    /* renamed from: d */
    private void m1525d() {
        int i = 0;
        int size = this.f878a != null ? this.f878a.size() : 0;
        new StringBuilder(f877z[24]).append(size);
        ac.m1576a();
        for (C0477p c0477p : this.f878a.values()) {
            i++;
            new StringBuilder().append(i).append("/").append(size).append(f877z[22]).append(c0477p.toString());
            ac.m1576a();
        }
    }

    /* renamed from: d */
    private void m1526d(C0477p c0477p) {
        ac.m1576a();
        Long a = c0477p.f889a.m1828f().m1848a();
        if (((C0477p) this.f878a.remove(a)) == null) {
            ac.m1586d();
        }
        this.f879b.remove(c0477p);
        this.f882e.removeMessages(7403, a);
    }

    /* renamed from: a */
    public final void m1527a() {
        ac.m1582b(f877z[5], f877z[4]);
        this.f884g = true;
        this.f885h = C0404a.m1123l();
        this.f882e.sendEmptyMessage(7405);
    }

    /* renamed from: a */
    public final void m1528a(long j, Object obj) {
        ConnectingHelper.sendConnectionToHandler(Message.obtain(this.f882e, 7402, obj), j);
    }

    /* renamed from: a */
    public final void m1529a(C0517h c0517h, int i) {
        Message.obtain(this.f882e, 7401, i, 0, c0517h).sendToTarget();
    }

    /* renamed from: b */
    public final void m1530b() {
        ac.m1578a(f877z[5], f877z[19]);
        C0472k.f866b.set(false);
        this.f885h = 0;
        this.f884g = false;
        new StringBuilder(f877z[18]).append(this.f880c.size());
        ac.m1581b();
        this.f882e.removeMessages(7404);
        while (true) {
            C0477p c0477p = (C0477p) this.f880c.pollLast();
            if (c0477p != null) {
                this.f879b.offerFirst(c0477p);
            } else {
                m1523c();
                m1525d();
                return;
            }
        }
    }

    public final void run() {
        new StringBuilder(f877z[20]).append(Thread.currentThread().getId());
        ac.m1581b();
        super.run();
    }
}
