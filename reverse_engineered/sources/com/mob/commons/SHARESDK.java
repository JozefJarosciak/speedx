package com.mob.commons;

import android.content.Context;
import cn.sharesdk.framework.ShareSDK;
import com.mob.commons.authorize.DeviceAuthorizer;

public class SHARESDK implements MobProduct {
    private static String appKey;

    public static void setAppKey(String str) {
        appKey = str;
    }

    public String getProductTag() {
        return ShareSDK.SDK_TAG;
    }

    public String getProductAppkey() {
        return appKey;
    }

    public int getSdkver() {
        return 60068;
    }

    public String getDuid(Context context) {
        return DeviceAuthorizer.authorize(context, (MobProduct) this);
    }
}
