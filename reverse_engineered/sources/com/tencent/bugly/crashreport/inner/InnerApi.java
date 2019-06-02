package com.tencent.bugly.crashreport.inner;

import com.tencent.bugly.crashreport.crash.C4439d;
import com.tencent.bugly.proguard.C4475w;
import java.util.Map;

/* compiled from: BUGLY */
public class InnerApi {
    public static void postU3dCrashAsync(String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            C4475w.m17753e("post u3d fail args null", new Object[0]);
        }
        C4475w.m17747a("post u3d crash %s %s", str, str2);
        C4439d.m17467a(Thread.currentThread(), 4, str, str2, str3, null);
    }

    public static void postCocos2dxCrashAsync(int i, String str, String str2, String str3) {
        if (str == null || str2 == null || str3 == null) {
            C4475w.m17753e("post cocos2d-x fail args null", new Object[0]);
        } else if (i == 5 || i == 6) {
            C4475w.m17747a("post cocos2d-x crash %s %s", str, str2);
            C4439d.m17467a(Thread.currentThread(), i, str, str2, str3, null);
        } else {
            C4475w.m17753e("post cocos2d-x fail category illeagle: %d", Integer.valueOf(i));
        }
    }

    public static void postH5CrashAsync(Thread thread, String str, String str2, String str3, Map<String, String> map) {
        if (str == null || str2 == null || str3 == null) {
            C4475w.m17753e("post h5 fail args null", new Object[0]);
            return;
        }
        C4475w.m17747a("post h5 crash %s %s", str, str2);
        C4439d.m17467a(thread, 8, str, str2, str3, map);
    }
}
