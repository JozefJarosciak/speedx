package com.beastbikes.framework.android.p088g;

import android.content.Context;
import android.telephony.TelephonyManager;
import io.rong.imlib.statistics.UserData;

/* compiled from: TelephonyUtils */
/* renamed from: com.beastbikes.framework.android.g.i */
public abstract class C2803i {
    /* renamed from: a */
    public static String m13764a(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
        if (telephonyManager == null) {
            return null;
        }
        return telephonyManager.getDeviceId();
    }
}
