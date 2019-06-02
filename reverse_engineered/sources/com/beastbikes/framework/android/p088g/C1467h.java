package com.beastbikes.framework.android.p088g;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningServiceInfo;
import android.content.Context;
import java.util.List;

/* compiled from: ServicesUtils */
/* renamed from: com.beastbikes.framework.android.g.h */
public class C1467h {
    /* renamed from: a */
    public static boolean m8067a(Context context, String str) {
        List runningServices = ((ActivityManager) context.getSystemService("activity")).getRunningServices(30);
        if (runningServices.size() <= 0) {
            return false;
        }
        for (int i = 0; i < runningServices.size(); i++) {
            if (((RunningServiceInfo) runningServices.get(i)).service.getClassName().equals(str)) {
                return true;
            }
        }
        return false;
    }
}
