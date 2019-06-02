package com.baidu.location.p041a;

/* renamed from: com.baidu.location.a.l */
class C1061l implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C1060k f2507a;

    C1061l(C1060k c1060k) {
        this.f2507a = c1060k;
    }

    public void run() {
        if (this.f2507a.f2494c != null && !this.f2507a.f2498h) {
            this.f2507a.f2494c.unregisterListener(C1060k.f2491d, this.f2507a.f2494c.getDefaultSensor(6));
            this.f2507a.f2506p = false;
        }
    }
}
