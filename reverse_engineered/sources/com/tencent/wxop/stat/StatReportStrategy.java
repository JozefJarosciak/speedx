package com.tencent.wxop.stat;

public enum StatReportStrategy {
    INSTANT(1),
    ONLY_WIFI(2),
    BATCH(3),
    APP_LAUNCH(4),
    DEVELOPER(5),
    PERIOD(6),
    ONLY_WIFI_NO_CACHE(7);
    
    /* renamed from: a */
    int f15869a;

    private StatReportStrategy(int i) {
        this.f15869a = i;
    }

    public static StatReportStrategy getStatReportStrategy(int i) {
        for (StatReportStrategy statReportStrategy : values()) {
            if (i == statReportStrategy.m17873a()) {
                return statReportStrategy;
            }
        }
        return null;
    }

    /* renamed from: a */
    public final int m17873a() {
        return this.f15869a;
    }
}
