package com.beastbikes.android.utils;

import android.content.Context;
import android.text.TextUtils;
import com.avos.avoscloud.AVAnalytics;
import com.umeng.analytics.MobclickAgent;

/* compiled from: SpeedxAnalytics */
/* renamed from: com.beastbikes.android.utils.w */
public class C2580w {
    /* renamed from: a */
    public static void m12905a(Context context, String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            AVAnalytics.onEvent(context, str);
        }
        if (!TextUtils.isEmpty(str2)) {
            MobclickAgent.a(context, str2);
        }
    }
}
