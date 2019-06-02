package com.baidu.mapapi.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class OpenClientUtil {
    public static int getBaiduMapVersion(Context context) {
        int i = 0;
        if (context != null) {
            String str = "";
            try {
                str = context.getPackageManager().getPackageInfo("com.baidu.BaiduMap", 0).versionName;
                if (str != null && str.length() > 0) {
                    i = Integer.valueOf(str.trim().replace(".", "").trim()).intValue();
                }
            } catch (Exception e) {
            }
        }
        return i;
    }

    public static void getLatestBaiduMapApp(Context context) {
        if (context != null) {
            String b = C1192a.m4481b(context);
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            intent.setData(Uri.parse("http://map.baidu.com/zt/client/index/?fr=sdk_[" + b + "]"));
            context.startActivity(intent);
        }
    }
}
