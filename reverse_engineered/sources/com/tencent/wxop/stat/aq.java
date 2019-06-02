package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.common.C4539k;
import com.tencent.wxop.stat.common.C4544p;
import com.tencent.wxop.stat.p201a.C4513e;

class aq {
    /* renamed from: f */
    private static volatile long f15985f = 0;
    /* renamed from: a */
    private C4513e f15986a;
    /* renamed from: b */
    private StatReportStrategy f15987b = null;
    /* renamed from: c */
    private boolean f15988c = false;
    /* renamed from: d */
    private Context f15989d = null;
    /* renamed from: e */
    private long f15990e = System.currentTimeMillis();

    public aq(C4513e c4513e) {
        this.f15986a = c4513e;
        this.f15987b = StatConfig.getStatSendStrategy();
        this.f15988c = c4513e.m17908f();
        this.f15989d = c4513e.m17907e();
    }

    /* renamed from: a */
    private void m17953a(C4526h c4526h) {
        C4551i.m18122b(StatServiceImpl.f15889t).m18123a(this.f15986a, c4526h);
    }

    /* renamed from: b */
    private void m17955b() {
        if (this.f15986a.m17906d() != null && this.f15986a.m17906d().isSendImmediately()) {
            this.f15987b = StatReportStrategy.INSTANT;
        }
        if (StatConfig.f15848j && C4525a.m17934a(StatServiceImpl.f15889t).m17946e()) {
            this.f15987b = StatReportStrategy.INSTANT;
        }
        if (StatConfig.isDebugEnable()) {
            StatServiceImpl.f15886q.m18012i("strategy=" + this.f15987b.name());
        }
        switch (ag.f15963a[this.f15987b.ordinal()]) {
            case 1:
                m17956c();
                return;
            case 2:
                au.m17968a(this.f15989d).m17997a(this.f15986a, null, this.f15988c, false);
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f15886q.m18012i("PERIOD currTime=" + this.f15990e + ",nextPeriodSendTs=" + StatServiceImpl.f15872c + ",difftime=" + (StatServiceImpl.f15872c - this.f15990e));
                }
                if (StatServiceImpl.f15872c == 0) {
                    StatServiceImpl.f15872c = C4544p.m18091a(this.f15989d, "last_period_ts", 0);
                    if (this.f15990e > StatServiceImpl.f15872c) {
                        StatServiceImpl.m17893e(this.f15989d);
                    }
                    long sendPeriodMinutes = this.f15990e + ((long) ((StatConfig.getSendPeriodMinutes() * 60) * 1000));
                    if (StatServiceImpl.f15872c > sendPeriodMinutes) {
                        StatServiceImpl.f15872c = sendPeriodMinutes;
                    }
                    C4547d.m18110a(this.f15989d).m18111a();
                }
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f15886q.m18012i("PERIOD currTime=" + this.f15990e + ",nextPeriodSendTs=" + StatServiceImpl.f15872c + ",difftime=" + (StatServiceImpl.f15872c - this.f15990e));
                }
                if (this.f15990e > StatServiceImpl.f15872c) {
                    StatServiceImpl.m17893e(this.f15989d);
                    return;
                }
                return;
            case 3:
            case 4:
                au.m17968a(this.f15989d).m17997a(this.f15986a, null, this.f15988c, false);
                return;
            case 5:
                au.m17968a(this.f15989d).m17997a(this.f15986a, new ar(this), this.f15988c, true);
                return;
            case 6:
                if (C4525a.m17934a(StatServiceImpl.f15889t).m17944c() == 1) {
                    m17956c();
                    return;
                } else {
                    au.m17968a(this.f15989d).m17997a(this.f15986a, null, this.f15988c, false);
                    return;
                }
            case 7:
                if (C4539k.m18060e(this.f15989d)) {
                    m17953a(new as(this));
                    return;
                }
                return;
            default:
                StatServiceImpl.f15886q.error("Invalid stat strategy:" + StatConfig.getStatSendStrategy());
                return;
        }
    }

    /* renamed from: c */
    private void m17956c() {
        if (au.m17982b().f15997a <= 0 || !StatConfig.f15850l) {
            m17953a(new at(this));
            return;
        }
        au.m17982b().m17997a(this.f15986a, null, this.f15988c, true);
        au.m17982b().m17996a(-1);
    }

    /* renamed from: d */
    private boolean m17958d() {
        if (StatConfig.f15846h > 0) {
            if (this.f15990e > StatServiceImpl.f15877h) {
                StatServiceImpl.f15876g.clear();
                StatServiceImpl.f15877h = this.f15990e + StatConfig.f15847i;
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f15886q.m18012i("clear methodsCalledLimitMap, nextLimitCallClearTime=" + StatServiceImpl.f15877h);
                }
            }
            Integer valueOf = Integer.valueOf(this.f15986a.mo6117a().m17920a());
            Integer num = (Integer) StatServiceImpl.f15876g.get(valueOf);
            if (num != null) {
                StatServiceImpl.f15876g.put(valueOf, Integer.valueOf(num.intValue() + 1));
                if (num.intValue() > StatConfig.f15846h) {
                    if (StatConfig.isDebugEnable()) {
                        StatServiceImpl.f15886q.m18010e("event " + this.f15986a.m17909g() + " was discard, cause of called limit, current:" + num + ", limit:" + StatConfig.f15846h + ", period:" + StatConfig.f15847i + " ms");
                    }
                    return true;
                }
            }
            StatServiceImpl.f15876g.put(valueOf, Integer.valueOf(1));
        }
        return false;
    }

    /* renamed from: a */
    public void m17959a() {
        if (!m17958d()) {
            if (StatConfig.f15851m > 0 && this.f15990e >= f15985f) {
                StatServiceImpl.flushDataToDB(this.f15989d);
                f15985f = this.f15990e + StatConfig.f15852n;
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f15886q.m18012i("nextFlushTime=" + f15985f);
                }
            }
            if (C4525a.m17934a(this.f15989d).m17947f()) {
                if (StatConfig.isDebugEnable()) {
                    StatServiceImpl.f15886q.m18012i("sendFailedCount=" + StatServiceImpl.f15870a);
                }
                if (StatServiceImpl.m17881a()) {
                    au.m17968a(this.f15989d).m17997a(this.f15986a, null, this.f15988c, false);
                    if (this.f15990e - StatServiceImpl.f15871b > 1800000) {
                        StatServiceImpl.m17891d(this.f15989d);
                        return;
                    }
                    return;
                }
                m17955b();
                return;
            }
            au.m17968a(this.f15989d).m17997a(this.f15986a, null, this.f15988c, false);
        }
    }
}
