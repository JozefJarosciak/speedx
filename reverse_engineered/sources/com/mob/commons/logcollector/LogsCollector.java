package com.mob.commons.logcollector;

import android.content.Context;
import android.content.Intent;
import cn.sharesdk.framework.ShareSDK;
import com.mob.tools.MobLog;
import com.mob.tools.log.LogCollector;
import com.umeng.onlineconfig.OnlineConfigAgent;

public abstract class LogsCollector implements LogCollector {
    /* renamed from: a */
    private C4253c f14929a;
    /* renamed from: b */
    private boolean f14930b;

    protected abstract String getAppkey();

    protected abstract String getSDKTag();

    protected abstract int getSDKVersion();

    public LogsCollector(Context context) {
        this.f14929a = C4253c.m16901a(context);
        this.f14929a.m16914a(getSDKVersion(), getSDKTag(), getAppkey());
        try {
            if (context.getPackageManager().getPackageInfo("cn.sharesdk.log", 64) != null) {
                this.f14930b = true;
            }
        } catch (Throwable th) {
        }
        this.f14930b = false;
    }

    public final void log(String str, int i, int i2, String str2, String str3) {
        m16895a(i, str3);
        if (str != null && str.equals(getSDKTag())) {
            if (ShareSDK.SDK_TAG.equals(str) && (!str3.contains("com.mob.") || !str3.contains("cn.sharesdk."))) {
                return;
            }
            if (i2 == 1) {
                this.f14929a.m16915b(getSDKVersion(), i2, str, getAppkey(), str3);
            } else if (i2 == 2) {
                this.f14929a.m16913a(getSDKVersion(), i2, str, getAppkey(), str3);
            } else if (i == 5) {
                this.f14929a.m16913a(getSDKVersion(), i2, str, getAppkey(), str3);
            }
        }
    }

    /* renamed from: a */
    final int m16895a(int i, String str) {
        if (this.f14929a.m16912a() != null && this.f14930b) {
            try {
                Intent intent = new Intent();
                intent.setAction("cn.sharesdk.log");
                intent.putExtra(OnlineConfigAgent.KEY_PACKAGE, this.f14929a.m16912a().getPackageName());
                intent.putExtra("priority", i);
                intent.putExtra("msg", str);
                this.f14929a.m16912a().sendBroadcast(intent);
            } catch (Throwable th) {
                MobLog.getInstance().m16946w(th);
            }
        }
        return 0;
    }
}
