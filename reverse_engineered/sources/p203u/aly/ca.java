package p203u.aly;

import android.content.Context;
import android.telephony.TelephonyManager;
import io.rong.imlib.statistics.UserData;

/* compiled from: ImeiTracker */
/* renamed from: u.aly.ca */
public class ca extends bu {
    /* renamed from: a */
    private Context f18961a;

    public ca(Context context) {
        super("imei");
        this.f18961a = context;
    }

    /* renamed from: a */
    public String mo7207a() {
        TelephonyManager telephonyManager = (TelephonyManager) this.f18961a.getSystemService(UserData.PHONE_KEY);
        if (telephonyManager == null) {
        }
        try {
            if (af.m21115a(this.f18961a, "android.permission.READ_PHONE_STATE")) {
                return telephonyManager.getDeviceId();
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
