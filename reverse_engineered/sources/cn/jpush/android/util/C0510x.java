package cn.jpush.android.util;

/* renamed from: cn.jpush.android.util.x */
final class C0510x implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f1062a;
    /* renamed from: b */
    final /* synthetic */ JRecorder f1063b;

    C0510x(JRecorder jRecorder, int i) {
        this.f1063b = jRecorder;
        this.f1062a = i;
    }

    public final void run() {
        JRecorder.m1555a(this.f1063b, (double) this.f1062a);
    }
}
