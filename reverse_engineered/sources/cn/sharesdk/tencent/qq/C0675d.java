package cn.sharesdk.tencent.qq;

import android.content.Intent;
import android.os.Bundle;
import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.utils.C0621d;
import com.alipay.sdk.packet.C0861d;
import com.alipay.sdk.util.C0882j;
import com.mob.tools.FakeActivity;
import com.mob.tools.MobUIShell;
import com.mob.tools.utils.C4275R;
import com.mob.tools.utils.Hashon;

/* compiled from: ResultReceiver */
/* renamed from: cn.sharesdk.tencent.qq.d */
public class C0675d extends FakeActivity {
    /* renamed from: a */
    private String f1599a;
    /* renamed from: b */
    private Platform f1600b;
    /* renamed from: c */
    private PlatformActionListener f1601c;

    /* renamed from: a */
    public void m2615a(String str) {
        this.f1599a = str;
    }

    /* renamed from: a */
    public void m2614a(Platform platform, PlatformActionListener platformActionListener) {
        this.f1600b = platform;
        this.f1601c = platformActionListener;
    }

    public void onCreate() {
        try {
            Intent intent = this.activity.getIntent();
            String scheme = intent.getScheme();
            finish();
            if (scheme != null && scheme.startsWith(this.f1599a)) {
                Bundle urlToBundle = C4275R.urlToBundle(intent.getDataString());
                scheme = String.valueOf(urlToBundle.get(C0882j.f2229c));
                String valueOf = String.valueOf(urlToBundle.get(C0861d.f2143o));
                if ("shareToQQ".equals(valueOf) || "shareToQzone".equals(valueOf)) {
                    if ("complete".equals(scheme)) {
                        if (this.f1601c != null) {
                            this.f1601c.onComplete(this.f1600b, 9, new Hashon().fromJson(String.valueOf(urlToBundle.get("response"))));
                        }
                    } else if ("error".equals(scheme)) {
                        if (this.f1601c != null) {
                            this.f1601c.onError(this.f1600b, 9, new Throwable(String.valueOf(urlToBundle.get("response"))));
                        }
                    } else if (this.f1601c != null) {
                        this.f1601c.onCancel(this.f1600b, 9);
                    }
                }
                intent = new Intent("android.intent.action.VIEW");
                intent.setClass(this.activity, MobUIShell.class);
                intent.setFlags(335544320);
                startActivity(intent);
            }
        } catch (Throwable th) {
            C0621d.m2279a().d(th);
        }
    }
}
