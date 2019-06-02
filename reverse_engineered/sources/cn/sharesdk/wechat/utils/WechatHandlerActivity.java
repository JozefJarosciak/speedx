package cn.sharesdk.wechat.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import cn.sharesdk.framework.utils.C0621d;

public class WechatHandlerActivity extends Activity {
    protected void onCreate(Bundle bundle) {
        setTheme(16973839);
        super.onCreate(bundle);
        try {
            WechatHelper.m2681a().m2705a(this);
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
        finish();
    }

    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        try {
            WechatHelper.m2681a().m2705a(this);
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
        finish();
    }

    public void onGetMessageFromWXReq(WXMediaMessage wXMediaMessage) {
    }

    public void onShowMessageFromWXReq(WXMediaMessage wXMediaMessage) {
    }
}
