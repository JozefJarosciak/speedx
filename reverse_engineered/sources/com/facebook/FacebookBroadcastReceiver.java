package com.facebook;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.facebook.internal.C3035o;

public class FacebookBroadcastReceiver extends BroadcastReceiver {
    public void onReceive(Context context, Intent intent) {
        String stringExtra = intent.getStringExtra("com.facebook.platform.protocol.CALL_ID");
        String stringExtra2 = intent.getStringExtra("com.facebook.platform.protocol.PROTOCOL_ACTION");
        if (stringExtra != null && stringExtra2 != null) {
            Bundle extras = intent.getExtras();
            if (C3035o.m14692e(intent)) {
                m14293b(stringExtra, stringExtra2, extras);
            } else {
                m14292a(stringExtra, stringExtra2, extras);
            }
        }
    }

    /* renamed from: a */
    protected void m14292a(String str, String str2, Bundle bundle) {
    }

    /* renamed from: b */
    protected void m14293b(String str, String str2, Bundle bundle) {
    }
}
