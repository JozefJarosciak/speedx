package com.beastbikes.android.modules.p109b;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.BaseFragmentActivity;
import com.pingplusplus.android.Pingpp;

/* compiled from: PayHelper */
/* renamed from: com.beastbikes.android.modules.b.a */
public class C1871a extends C1397a {
    /* renamed from: a */
    private BaseFragmentActivity f8402a = null;
    /* renamed from: b */
    private C1872b f8403b;
    /* renamed from: c */
    private AsyncTask f8404c = null;

    public C1871a(BaseFragmentActivity baseFragmentActivity) {
        super((C1372b) baseFragmentActivity.getApplicationContext());
        this.f8402a = baseFragmentActivity;
        this.f8403b = (C1872b) new C1772d(baseFragmentActivity).m9399a(C1872b.class, C1768c.f8075a, C1768c.m9391a(baseFragmentActivity));
    }

    /* renamed from: a */
    public void m9719a(String str) {
        if (TextUtils.isEmpty(str) || this.f8402a == null) {
            throw new RuntimeException("pay error!!");
        }
        Pingpp.createPayment(this.f8402a, str);
    }
}
