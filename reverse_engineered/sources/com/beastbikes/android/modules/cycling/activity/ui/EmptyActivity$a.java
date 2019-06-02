package com.beastbikes.android.modules.cycling.activity.ui;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import java.lang.ref.WeakReference;

class EmptyActivity$a extends BroadcastReceiver {
    /* renamed from: a */
    private WeakReference<Activity> f8710a;

    EmptyActivity$a(Activity activity) {
        this.f8710a = new WeakReference(activity);
    }

    public void onReceive(Context context, Intent intent) {
        Activity activity = (Activity) this.f8710a.get();
        if ("com.beastbikes.intent.action.ACTIVITY_COMPLETE".equals(intent.getAction()) && activity != null) {
            activity.finish();
        }
        if ("android.intent.action.SCREEN_ON".equals(intent.getAction()) && activity != null) {
            activity.finish();
        }
    }
}
