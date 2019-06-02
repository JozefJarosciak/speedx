package com.tencent.mm.sdk.modelbase;

import android.os.Bundle;
import com.tencent.mm.sdk.p198b.C4487a;

public abstract class BaseReq {
    public String openId;
    public String transaction;

    public abstract boolean checkArgs();

    public void fromBundle(Bundle bundle) {
        this.transaction = C4487a.m17821b(bundle, "_wxapi_basereq_transaction");
        this.openId = C4487a.m17821b(bundle, "_wxapi_basereq_openid");
    }

    public abstract int getType();

    public void toBundle(Bundle bundle) {
        bundle.putInt("_wxapi_command_type", getType());
        bundle.putString("_wxapi_basereq_transaction", this.transaction);
        bundle.putString("_wxapi_basereq_openid", this.openId);
    }
}
