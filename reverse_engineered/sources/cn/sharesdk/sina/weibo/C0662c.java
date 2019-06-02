package cn.sharesdk.sina.weibo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.content.pm.Signature;
import android.os.IBinder;
import android.text.TextUtils;
import ch.qos.logback.core.joran.action.Action;
import cn.sharesdk.framework.authorize.C0558d;
import cn.sharesdk.framework.authorize.C0579c;
import com.baidu.mapapi.SDKInitializer;
import io.rong.imlib.common.RongLibConst;
import java.lang.reflect.Method;

/* compiled from: SinaWeiboSSOProcessor */
/* renamed from: cn.sharesdk.sina.weibo.c */
public class C0662c extends C0558d implements ServiceConnection {
    /* renamed from: d */
    private String f1558d;
    /* renamed from: e */
    private String f1559e;
    /* renamed from: f */
    private String[] f1560f;

    public C0662c(C0579c c0579c) {
        super(c0579c);
    }

    /* renamed from: a */
    public void m2536a(String str, String str2, String[] strArr) {
        this.f1558d = str;
        this.f1559e = str2;
        this.f1560f = strArr;
    }

    /* renamed from: a */
    public void mo2262a() {
        Intent intent = new Intent();
        intent.setClassName("com.sina.weibo", "com.sina.weibo.business.RemoteSSOService");
        if (!this.a.getContext().getApplicationContext().bindService(intent, this, 1)) {
            this.a.finish();
            if (this.c != null) {
                this.c.onFailed(new Throwable());
            }
        }
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            Class cls = Class.forName("com.sina.sso.RemoteSSO$Stub");
            Method method = cls.getMethod("asInterface", new Class[]{IBinder.class});
            method.setAccessible(true);
            Object invoke = method.invoke(null, new Object[]{iBinder});
            Method method2 = cls.getMethod("getPackageName", new Class[0]);
            method2.setAccessible(true);
            String valueOf = String.valueOf(method2.invoke(invoke, new Object[0]));
            Method method3 = cls.getMethod("getActivityName", new Class[0]);
            method3.setAccessible(true);
            if (!m2530a(valueOf, String.valueOf(method3.invoke(invoke, new Object[0])))) {
                this.a.finish();
                if (this.c != null) {
                    this.c.onFailed(new Throwable());
                }
            }
        } catch (Throwable th) {
            this.a.finish();
            if (this.c != null) {
                this.c.onFailed(th);
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        this.a.finish();
        if (this.c != null) {
            this.c.onFailed(new Throwable());
        }
    }

    /* renamed from: a */
    private boolean m2530a(String str, String str2) {
        boolean z = true;
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        intent.putExtra(RongLibConst.KEY_APPKEY, this.f1558d);
        intent.putExtra("redirectUri", this.f1559e);
        if (this.f1560f != null && this.f1560f.length > 0) {
            intent.putExtra(Action.SCOPE_ATTRIBUTE, TextUtils.join(",", this.f1560f));
        }
        if (!m2531b(intent)) {
            return false;
        }
        try {
            this.a.startActivityForResult(intent, this.b);
        } catch (Throwable th) {
            z = false;
        }
        this.a.getContext().getApplicationContext().unbindService(this);
        return z;
    }

    /* renamed from: b */
    private boolean m2531b(Intent intent) {
        ResolveInfo resolveActivity = this.a.getContext().getPackageManager().resolveActivity(intent, 0);
        if (resolveActivity == null) {
            return false;
        }
        try {
            for (Signature toCharsString : this.a.getContext().getPackageManager().getPackageInfo(resolveActivity.activityInfo.packageName, 64).signatures) {
                if ("30820295308201fea00302010202044b4ef1bf300d06092a864886f70d010105050030818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c74643020170d3130303131343130323831355a180f32303630303130323130323831355a30818d310b300906035504061302434e3110300e060355040813074265694a696e673110300e060355040713074265694a696e67312c302a060355040a132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c7464312c302a060355040b132353696e612e436f6d20546563686e6f6c6f677920284368696e612920436f2e204c746430819f300d06092a864886f70d010101050003818d00308189028181009d367115bc206c86c237bb56c8e9033111889b5691f051b28d1aa8e42b66b7413657635b44786ea7e85d451a12a82a331fced99c48717922170b7fc9bc1040753c0d38b4cf2b22094b1df7c55705b0989441e75913a1a8bd2bc591aa729a1013c277c01c98cbec7da5ad7778b2fad62b85ac29ca28ced588638c98d6b7df5a130203010001300d06092a864886f70d0101050500038181000ad4b4c4dec800bd8fd2991adfd70676fce8ba9692ae50475f60ec468d1b758a665e961a3aedbece9fd4d7ce9295cd83f5f19dc441a065689d9820faedbb7c4a4c4635f5ba1293f6da4b72ed32fb8795f736a20c95cda776402099054fccefb4a1a558664ab8d637288feceba9508aa907fc1fe2b1ae5a0dec954ed831c0bea4".equals(toCharsString.toCharsString())) {
                    return true;
                }
            }
            return false;
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    /* renamed from: a */
    public void mo2263a(int i, int i2, Intent intent) {
        this.a.finish();
        if (i == this.b) {
            switch (i2) {
                case -1:
                    m2532c(intent);
                    return;
                case 0:
                    m2533d(intent);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: c */
    private void m2532c(Intent intent) {
        if (this.c != null) {
            String stringExtra = intent.getStringExtra("error");
            if (stringExtra == null) {
                stringExtra = intent.getStringExtra("error_type");
            }
            if (stringExtra == null) {
                this.c.onComplete(intent.getExtras());
            } else if (stringExtra.equals("access_denied") || stringExtra.equals("OAuthAccessDeniedException")) {
                this.c.onCancel();
            } else {
                String stringExtra2 = intent.getStringExtra("error_description");
                if (stringExtra2 != null) {
                    stringExtra = stringExtra + ": " + stringExtra2;
                }
                this.c.onFailed(new Throwable(stringExtra));
            }
        }
    }

    /* renamed from: d */
    private void m2533d(Intent intent) {
        if (intent != null) {
            String stringExtra = intent.getStringExtra("error");
            Throwable th = new Throwable(stringExtra + " (" + intent.getIntExtra(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, -1) + ")");
            if (this.c != null) {
                this.c.onFailed(th);
            }
        } else if (this.c != null) {
            this.c.onCancel();
        }
    }
}
