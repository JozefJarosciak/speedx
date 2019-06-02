package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.framework.utils.C0621d;

/* compiled from: AuthReq */
/* renamed from: cn.sharesdk.wechat.utils.a */
public class C0693a extends C0692j {
    /* renamed from: a */
    public String f1664a;
    /* renamed from: b */
    public String f1665b;

    /* renamed from: a */
    public int mo2302a() {
        return 1;
    }

    /* renamed from: a */
    public void mo2303a(Bundle bundle) {
        super.mo2303a(bundle);
        bundle.putString("_wxapi_sendauth_req_scope", this.f1664a);
        bundle.putString("_wxapi_sendauth_req_state", this.f1665b);
    }

    /* renamed from: b */
    public boolean mo2304b() {
        if (this.f1664a == null || this.f1664a.length() == 0 || this.f1664a.length() > 1024) {
            C0621d.m2279a().d("MicroMsg.SDK.SendAuth.Req", new Object[]{"checkArgs fail, scope is invalid"});
            return false;
        } else if (this.f1665b == null || this.f1665b.length() <= 1024) {
            return true;
        } else {
            C0621d.m2279a().d("MicroMsg.SDK.SendAuth.Req", new Object[]{"checkArgs fail, state is invalid"});
            return false;
        }
    }
}
