package com.facebook.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import com.facebook.appevents.AppEventsLogger;
import org.apache.commons.cli.HelpFormatter;

public class BoltsMeasurementEventListener extends BroadcastReceiver {
    /* renamed from: a */
    private static BoltsMeasurementEventListener f13523a;
    /* renamed from: b */
    private Context f13524b;

    private BoltsMeasurementEventListener(Context context) {
        this.f13524b = context.getApplicationContext();
    }

    /* renamed from: a */
    private void m14520a() {
        LocalBroadcastManager.getInstance(this.f13524b).registerReceiver(this, new IntentFilter("com.parse.bolts.measurement_event"));
    }

    /* renamed from: b */
    private void m14521b() {
        LocalBroadcastManager.getInstance(this.f13524b).unregisterReceiver(this);
    }

    /* renamed from: a */
    public static BoltsMeasurementEventListener m14519a(Context context) {
        if (f13523a != null) {
            return f13523a;
        }
        f13523a = new BoltsMeasurementEventListener(context);
        f13523a.m14520a();
        return f13523a;
    }

    protected void finalize() throws Throwable {
        try {
            m14521b();
        } finally {
            super.finalize();
        }
    }

    public void onReceive(Context context, Intent intent) {
        AppEventsLogger a = AppEventsLogger.m14408a(context);
        String str = "bf_" + intent.getStringExtra("event_name");
        Bundle bundleExtra = intent.getBundleExtra("event_args");
        Bundle bundle = new Bundle();
        for (String str2 : bundleExtra.keySet()) {
            bundle.putString(str2.replaceAll("[^0-9a-zA-Z _-]", HelpFormatter.DEFAULT_OPT_PREFIX).replaceAll("^[ -]*", "").replaceAll("[ -]*$", ""), (String) bundleExtra.get(str2));
        }
        a.m14415a(str, bundle);
    }
}
