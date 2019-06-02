package com.beastbikes.android.modules;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.beastbikes.android.ble.biz.S605CentralInvocation;
import com.beastbikes.android.ble.ui.p098a.C1724l;
import com.beastbikes.android.ble.ui.p098a.C1726m;
import com.beastbikes.android.ble.ui.p098a.C1726m.C1725a;
import com.beastbikes.android.ble.ui.p098a.C1728n;
import com.beastbikes.framework.android.p088g.C1465f;

class SessionFragmentActivity$2 extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ SessionFragmentActivity f8391a;

    SessionFragmentActivity$2(SessionFragmentActivity sessionFragmentActivity) {
        this.f8391a = sessionFragmentActivity;
    }

    public void onReceive(Context context, Intent intent) {
        CharSequence action = intent.getAction();
        if (!TextUtils.isEmpty(action)) {
            if (context instanceof Activity) {
                ComponentName componentName = ((Activity) context).getComponentName();
                if (componentName == null || !TextUtils.equals(componentName.getClassName(), C1465f.d(context))) {
                    return;
                }
            }
            if ("s605.dialog.type.wifi.password".equals(action)) {
                final S605CentralInvocation s605CentralInvocation = (S605CentralInvocation) intent.getParcelableExtra("s605.central.invocation");
                if (s605CentralInvocation != null) {
                    new C1726m(context, new C1725a(this) {
                        /* renamed from: b */
                        final /* synthetic */ SessionFragmentActivity$2 f8390b;

                        /* renamed from: a */
                        public void mo3260a(String str) {
                            s605CentralInvocation.m8702a(str);
                        }
                    }).show();
                } else {
                    return;
                }
            }
            if ("s605.dialog.type.wifi.status".equals(action)) {
                new C1728n(context, intent.getIntExtra("s605.dialog.wifi.status", 0), intent.getStringExtra("s605.dialog.wifi.uuid")).show();
            }
            if ("s605.dialog.update.success".equals(action)) {
                new C1724l(context, intent.getIntExtra("dialog.update.type", 0), intent.getIntExtra("s605.dialog.wifi.status", 0)).show();
            }
        }
    }
}
