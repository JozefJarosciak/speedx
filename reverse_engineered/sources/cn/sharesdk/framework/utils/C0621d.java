package cn.sharesdk.framework.utils;

import android.content.Context;
import cn.sharesdk.framework.ShareSDK;
import com.mob.commons.logcollector.LogsCollector;
import com.mob.tools.log.NLog;

/* compiled from: SSDKLog */
/* renamed from: cn.sharesdk.framework.utils.d */
public class C0621d extends NLog {
    private C0621d(Context context, final int i, final String str) {
        C0621d.setCollector(ShareSDK.SDK_TAG, new LogsCollector(this, context) {
            /* renamed from: c */
            final /* synthetic */ C0621d f1404c;

            protected int getSDKVersion() {
                return i;
            }

            protected String getSDKTag() {
                return ShareSDK.SDK_TAG;
            }

            protected String getAppkey() {
                return str;
            }
        });
    }

    protected String getSDKTag() {
        return ShareSDK.SDK_TAG;
    }

    /* renamed from: a */
    public static NLog m2280a(Context context, int i, String str) {
        return new C0621d(context, i, str);
    }

    /* renamed from: a */
    public static NLog m2279a() {
        return C0621d.getInstanceForSDK(ShareSDK.SDK_TAG, true);
    }
}
