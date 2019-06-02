package com.baidu.mapapi.map;

/* renamed from: com.baidu.mapapi.map.g */
class C1122g implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f3249a;
    /* renamed from: b */
    final /* synthetic */ int f3250b;
    /* renamed from: c */
    final /* synthetic */ int f3251c;
    /* renamed from: d */
    final /* synthetic */ HeatMap f3252d;

    C1122g(HeatMap heatMap, int i, int i2, int i3) {
        this.f3252d = heatMap;
        this.f3249a = i;
        this.f3250b = i2;
        this.f3251c = i3;
    }

    public void run() {
        this.f3252d.m4121b(this.f3249a, this.f3250b, this.f3251c);
    }
}
