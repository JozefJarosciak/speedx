package cn.sharesdk.wechat.utils;

import android.os.Bundle;

/* compiled from: AuthResp */
/* renamed from: cn.sharesdk.wechat.utils.b */
public class C0694b extends WechatResp {
    /* renamed from: a */
    public String f1666a;
    /* renamed from: b */
    public String f1667b;
    /* renamed from: c */
    public int f1668c;
    /* renamed from: d */
    public String f1669d;
    /* renamed from: e */
    public String f1670e;

    public C0694b(Bundle bundle) {
        super(bundle);
    }

    /* renamed from: a */
    public int mo2305a() {
        return 1;
    }

    /* renamed from: a */
    public void mo2306a(Bundle bundle) {
        super.mo2306a(bundle);
        this.f1666a = bundle.getString("_wxapi_sendauth_resp_userName");
        this.f1667b = bundle.getString("_wxapi_sendauth_resp_token");
        this.f1668c = bundle.getInt("_wxapi_sendauth_resp_expireDate", 0);
        this.f1669d = bundle.getString("_wxapi_sendauth_resp_state");
        this.f1670e = bundle.getString("_wxapi_sendauth_resp_url");
    }

    /* renamed from: b */
    public void mo2307b(Bundle bundle) {
        super.mo2307b(bundle);
        bundle.putString("_wxapi_sendauth_resp_userName", this.f1666a);
        bundle.putString("_wxapi_sendauth_resp_token", this.f1667b);
        bundle.putInt("_wxapi_sendauth_resp_expireDate", this.f1668c);
        bundle.putString("_wxapi_sendauth_resp_state", this.f1669d);
        bundle.putString("_wxapi_sendauth_resp_url", this.f1670e);
    }
}
