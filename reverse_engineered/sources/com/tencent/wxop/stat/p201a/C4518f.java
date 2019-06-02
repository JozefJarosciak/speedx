package com.tencent.wxop.stat.p201a;

/* renamed from: com.tencent.wxop.stat.a.f */
public enum C4518f {
    PAGE_VIEW(1),
    SESSION_ENV(2),
    ERROR(3),
    CUSTOM(1000),
    ADDITION(1001),
    MONITOR_STAT(1002),
    MTA_GAME_USER(1003),
    NETWORK_MONITOR(1004),
    NETWORK_DETECTOR(1005);
    
    /* renamed from: j */
    private int f15927j;

    private C4518f(int i) {
        this.f15927j = i;
    }

    /* renamed from: a */
    public final int m17920a() {
        return this.f15927j;
    }
}
