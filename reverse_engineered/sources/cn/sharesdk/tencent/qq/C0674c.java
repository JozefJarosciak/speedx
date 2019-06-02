package cn.sharesdk.tencent.qq;

import android.content.Intent;
import android.os.Bundle;
import ch.qos.logback.core.joran.action.Action;
import cn.sharesdk.framework.authorize.C0558d;
import cn.sharesdk.framework.authorize.C0579c;
import com.alipay.sdk.cons.C0844a;
import org.json.JSONObject;

/* compiled from: QQSSOProcessor */
/* renamed from: cn.sharesdk.tencent.qq.c */
public class C0674c extends C0558d {
    /* renamed from: d */
    private String f1597d;
    /* renamed from: e */
    private String f1598e;

    public C0674c(C0579c c0579c) {
        super(c0579c);
    }

    /* renamed from: a */
    public void m2613a(String str, String str2) {
        this.f1597d = str;
        this.f1598e = str2;
    }

    /* renamed from: a */
    public void mo2262a() {
        String str = "com.tencent.mobileqq";
        try {
            if (this.a.getContext().getPackageManager().getPackageInfo(str, 0) == null) {
                this.a.finish();
                if (this.c != null) {
                    this.c.onFailed(new TencentSSOClientNotInstalledException());
                    return;
                }
                return;
            }
            Intent intent = new Intent();
            intent.setClassName(str, "com.tencent.open.agent.AgentActivity");
            if (this.a.getContext().getPackageManager().resolveActivity(intent, 0) == null) {
                this.a.finish();
                if (this.c != null) {
                    this.c.onFailed(new TencentSSOClientNotInstalledException());
                    return;
                }
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString(Action.SCOPE_ATTRIBUTE, this.f1598e);
            bundle.putString("client_id", this.f1597d);
            bundle.putString("pf", "openmobile_android");
            bundle.putString("need_pay", C0844a.f2048d);
            intent.putExtra("key_params", bundle);
            intent.putExtra("key_request_code", this.b);
            intent.putExtra("key_action", "action_login");
            try {
                this.a.startActivityForResult(intent, this.b);
            } catch (Throwable th) {
                this.a.finish();
                if (this.c != null) {
                    this.c.onFailed(th);
                }
            }
        } catch (Throwable th2) {
            this.a.finish();
            if (this.c != null) {
                this.c.onFailed(new TencentSSOClientNotInstalledException());
            }
        }
    }

    /* renamed from: a */
    public void mo2263a(int i, int i2, Intent intent) {
        this.a.finish();
        if (i2 == 0) {
            if (this.c != null) {
                this.c.onCancel();
            }
        } else if (intent != null) {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                if (this.c != null) {
                    this.c.onFailed(new Throwable("response is empty"));
                }
            } else if (extras.containsKey("key_response")) {
                String string = extras.getString("key_response");
                if (string != null && string.length() > 0) {
                    try {
                        JSONObject jSONObject = new JSONObject(string);
                        extras = new Bundle();
                        extras.putInt("ret", jSONObject.optInt("ret"));
                        extras.putString("pay_token", jSONObject.optString("pay_token"));
                        extras.putString("pf", jSONObject.optString("pf"));
                        extras.putString("open_id", jSONObject.optString("openid"));
                        extras.putString("expires_in", jSONObject.optString("expires_in"));
                        extras.putString("pfkey", jSONObject.optString("pfkey"));
                        extras.putString("msg", jSONObject.optString("msg"));
                        extras.putString("access_token", jSONObject.optString("access_token"));
                        if (this.c != null) {
                            this.c.onComplete(extras);
                            this.c = null;
                        }
                    } catch (Throwable th) {
                        if (this.c != null) {
                            this.c.onFailed(th);
                        }
                    }
                } else if (this.c != null) {
                    this.c.onFailed(new Throwable("response is empty"));
                }
            } else if (this.c != null) {
                this.c.onFailed(new Throwable("response is empty"));
            }
        } else if (this.c != null) {
            this.c.onFailed(new Throwable("response is empty"));
        }
    }
}
