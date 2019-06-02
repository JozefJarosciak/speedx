package cn.sharesdk.wechat.utils;

import android.os.Bundle;

public abstract class WechatResp {
    /* renamed from: f */
    public int f1660f;
    /* renamed from: g */
    public String f1661g;
    /* renamed from: h */
    public String f1662h;

    public interface ErrCode {
        public static final int ERR_AUTH_DENIED = -4;
        public static final int ERR_COMM = -1;
        public static final int ERR_OK = 0;
        public static final int ERR_SENT_FAILED = -3;
        public static final int ERR_UNSUPPORT = -5;
        public static final int ERR_USER_CANCEL = -2;
    }

    /* renamed from: a */
    public abstract int mo2305a();

    public WechatResp(Bundle bundle) {
        mo2306a(bundle);
    }

    /* renamed from: b */
    public void mo2307b(Bundle bundle) {
        bundle.putInt("_wxapi_command_type", mo2305a());
        bundle.putInt("_wxapi_baseresp_errcode", this.f1660f);
        bundle.putString("_wxapi_baseresp_errstr", this.f1661g);
        bundle.putString("_wxapi_baseresp_transaction", this.f1662h);
    }

    /* renamed from: a */
    public void mo2306a(Bundle bundle) {
        this.f1660f = bundle.getInt("_wxapi_baseresp_errcode");
        this.f1661g = bundle.getString("_wxapi_baseresp_errstr");
        this.f1662h = bundle.getString("_wxapi_baseresp_transaction");
    }
}
