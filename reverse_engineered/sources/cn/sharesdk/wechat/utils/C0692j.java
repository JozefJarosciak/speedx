package cn.sharesdk.wechat.utils;

import android.os.Bundle;

/* compiled from: WechatReq */
/* renamed from: cn.sharesdk.wechat.utils.j */
public abstract class C0692j {
    /* renamed from: c */
    public String f1663c;

    /* renamed from: a */
    public abstract int mo2302a();

    /* renamed from: b */
    public abstract boolean mo2304b();

    /* renamed from: a */
    public void mo2303a(Bundle bundle) {
        bundle.putInt("_wxapi_command_type", mo2302a());
        bundle.putString("_wxapi_basereq_transaction", this.f1663c);
    }
}
