package io.rong.push.core;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.huawei.android.pushagent.PushBootReceiver;
import io.rong.imlib.common.DeviceUtils;
import io.rong.push.PushConst;
import io.rong.push.PushReceiver;
import io.rong.push.common.RLog;
import io.rong.push.core.PushClient.ConnectStatusCallback;
import io.rong.push.core.PushClient.QueryCallback;
import io.rong.push.core.PushClient.QueryMethod;
import java.io.IOException;
import org.apache.commons.cli.HelpFormatter;

class PushConnectivityManager$2 implements ConnectStatusCallback {
    final /* synthetic */ PushConnectivityManager this$0;

    /* renamed from: io.rong.push.core.PushConnectivityManager$2$1 */
    class C54081 implements QueryCallback {
        C54081() {
        }

        public void onSuccess(String str) {
            if (!TextUtils.isEmpty(str) && (str.equals("MI") || str.equals("HW") || str.equals("GCM"))) {
                PushConnectivityManager.access$700(PushConnectivityManager$2.this.this$0).getSharedPreferences(PushConst.PUSH_SHARE_PREFERENCE, 0).edit().putString("pushTypeUsed", str).commit();
                RLog.d("PushConnectivityManager", "send to registration.");
                Intent intent = new Intent(PushConnectivityManager.access$700(PushConnectivityManager$2.this.this$0), PushRegistrationService.class);
                intent.putExtra("pushType", str);
                PushConnectivityManager.access$700(PushConnectivityManager$2.this.this$0).startService(intent);
            }
            if (PushConnectivityManager.access$600(PushConnectivityManager$2.this.this$0).contains("HW") && (str == null || !str.equals("HW"))) {
                RLog.d("PushConnectivityManager", "setToken. Stop HW.");
                try {
                    PushConnectivityManager.access$700(PushConnectivityManager$2.this.this$0).getPackageManager().setComponentEnabledSetting(new ComponentName(PushConnectivityManager.access$700(PushConnectivityManager$2.this.this$0), PushBootReceiver.class), 2, 1);
                } catch (Exception e) {
                }
            }
            if (str != null && !str.equals("RONG")) {
                try {
                    PushConnectivityManager.access$700(PushConnectivityManager$2.this.this$0).getPackageManager().setComponentEnabledSetting(new ComponentName(PushConnectivityManager.access$700(PushConnectivityManager$2.this.this$0), PushReceiver.class), 2, 1);
                } catch (Exception e2) {
                }
            }
        }

        public void onFailure() {
            RLog.e("PushConnectivityManager", "Failure when query!");
        }
    }

    PushConnectivityManager$2(PushConnectivityManager pushConnectivityManager) {
        this.this$0 = pushConnectivityManager;
    }

    public void onConnected() {
        RLog.d("PushConnectivityManager", "onConnected.");
        this.this$0.getHandler().sendEmptyMessage(2);
        if (!TextUtils.isEmpty(PushConnectivityManager.access$600(this.this$0))) {
            String replace = PushConnectivityManager.access$700(this.this$0).getPackageName().replace(HelpFormatter.DEFAULT_OPT_PREFIX, "_");
            String deviceManufacturer = DeviceUtils.getDeviceManufacturer();
            PushConnectivityManager.access$500(this.this$0).query(QueryMethod.GET_PUSH_TYPE, String.format("%s-%s-%s-%s", new Object[]{PushConnectivityManager.access$600(this.this$0), PushConnectivityManager.access$800(this.this$0), replace, deviceManufacturer}), PushConnectivityManager.access$900(this.this$0), new C54081());
        }
    }

    public void onError(IOException iOException) {
        RLog.d("PushConnectivityManager", "connect onError");
        this.this$0.getHandler().sendEmptyMessage(4);
        if (PushConnectivityManager.access$1000(this.this$0) > 0) {
            PushConnectivityManager.access$1010(this.this$0);
            Editor edit = PushConnectivityManager.access$700(this.this$0).getSharedPreferences(PushConst.PUSH_SHARE_PREFERENCE, 0).edit();
            edit.remove("navigation_ip_value");
            edit.remove("navigation_time");
            edit.commit();
            this.this$0.getHandler().sendEmptyMessageDelayed(1, 5000);
        }
    }
}
