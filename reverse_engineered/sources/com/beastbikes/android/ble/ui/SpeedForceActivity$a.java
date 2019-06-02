package com.beastbikes.android.ble.ui;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import com.beastbikes.android.ble.ui.p098a.C1720i;
import java.lang.ref.WeakReference;

class SpeedForceActivity$a extends Handler {
    /* renamed from: a */
    final /* synthetic */ SpeedForceActivity f7712a;
    /* renamed from: b */
    private WeakReference<Activity> f7713b;

    public SpeedForceActivity$a(SpeedForceActivity speedForceActivity, Activity activity) {
        this.f7712a = speedForceActivity;
        this.f7713b = new WeakReference(activity);
    }

    public void handleMessage(Message message) {
        Activity activity = (Activity) this.f7713b.get();
        if (activity != null && !SpeedForceActivity.v(this.f7712a) && !SpeedForceActivity.w(this.f7712a)) {
            SpeedForceActivity.d(this.f7712a, true);
            new C1720i(activity).show();
        }
    }
}
