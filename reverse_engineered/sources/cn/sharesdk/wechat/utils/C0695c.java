package cn.sharesdk.wechat.utils;

import android.os.Bundle;
import cn.sharesdk.wechat.utils.WXMediaMessage.C0690a;

/* compiled from: GetMessageFromWechatResp */
/* renamed from: cn.sharesdk.wechat.utils.c */
public class C0695c extends WechatResp {
    /* renamed from: a */
    public WXMediaMessage f1671a;

    public C0695c(Bundle bundle) {
        super(bundle);
    }

    /* renamed from: a */
    public int mo2305a() {
        return 3;
    }

    /* renamed from: a */
    public void mo2306a(Bundle bundle) {
        super.mo2306a(bundle);
        this.f1671a = C0690a.m2679a(bundle);
    }

    /* renamed from: b */
    public void mo2307b(Bundle bundle) {
        super.mo2307b(bundle);
        bundle.putAll(C0690a.m2678a(this.f1671a));
    }
}
