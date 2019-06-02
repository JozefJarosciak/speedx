package com.beastbikes.android.modules.user.ui.binding;

import com.alipay.sdk.util.C0880h;
import com.beastbikes.android.authentication.C1537a;

class AccountManagerActivity$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C1537a f11848a;
    /* renamed from: b */
    final /* synthetic */ AccountManagerActivity f11849b;

    AccountManagerActivity$1(AccountManagerActivity accountManagerActivity, C1537a c1537a) {
        this.f11849b = accountManagerActivity;
        this.f11848a = c1537a;
    }

    public void run() {
        if (this.f11848a != null) {
            String d = this.f11848a.m8477d();
            int i = -1;
            switch (d.hashCode()) {
                case -1707903162:
                    if (d.equals("Wechat")) {
                        i = 2;
                        break;
                    }
                    break;
                case 2592:
                    if (d.equals("QQ")) {
                        i = 1;
                        break;
                    }
                    break;
                case 318270399:
                    if (d.equals("SinaWeibo")) {
                        i = 0;
                        break;
                    }
                    break;
                case 458192787:
                    if (d.equals("GooglePlus")) {
                        i = 3;
                        break;
                    }
                    break;
                case 561774310:
                    if (d.equals("Facebook")) {
                        i = 4;
                        break;
                    }
                    break;
                case 748307027:
                    if (d.equals("Twitter")) {
                        i = 5;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                case 1:
                case 2:
                case 3:
                case 4:
                    this.f11849b.a(this.f11848a.m8475b(), this.f11848a.m8474a(), this.f11848a.m8477d(), 0, this.f11848a.m8476c());
                    return;
                case 5:
                    this.f11849b.a(this.f11848a.m8475b(), this.f11848a.m8474a() + C0880h.f2220b + this.f11848a.m8478e(), this.f11848a.m8477d(), 0, this.f11848a.m8476c());
                    return;
                default:
                    return;
            }
        }
    }
}
