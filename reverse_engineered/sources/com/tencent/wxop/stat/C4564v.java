package com.tencent.wxop.stat;

import android.content.Context;
import com.tencent.wxop.stat.p201a.C4516c;

/* renamed from: com.tencent.wxop.stat.v */
final class C4564v implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f16156a;
    /* renamed from: b */
    final /* synthetic */ C4516c f16157b;
    /* renamed from: c */
    final /* synthetic */ Context f16158c;

    C4564v(String str, C4516c c4516c, Context context) {
        this.f16156a = str;
        this.f16157b = c4516c;
        this.f16158c = context;
    }

    public final void run() {
        try {
            if (StatServiceImpl.m17882a(this.f16156a)) {
                StatServiceImpl.f15886q.error((Object) "The event_id of StatService.trackCustomBeginEvent() can not be null or empty.");
                return;
            }
            if (StatConfig.isDebugEnable()) {
                StatServiceImpl.f15886q.m18012i("add begin key:" + this.f16157b.toString());
            }
            if (StatServiceImpl.f15874e.containsKey(this.f16157b)) {
                StatServiceImpl.f15886q.error("Duplicate CustomEvent key: " + this.f16157b.toString() + ", trackCustomBeginEvent() repeated?");
            } else if (StatServiceImpl.f15874e.size() <= StatConfig.getMaxParallelTimmingEvents()) {
                StatServiceImpl.f15874e.put(this.f16157b, Long.valueOf(System.currentTimeMillis()));
            } else {
                StatServiceImpl.f15886q.error("The number of timedEvent exceeds the maximum value " + Integer.toString(StatConfig.getMaxParallelTimmingEvents()));
            }
        } catch (Throwable th) {
            StatServiceImpl.f15886q.m18011e(th);
            StatServiceImpl.m17880a(this.f16158c, th);
        }
    }
}
