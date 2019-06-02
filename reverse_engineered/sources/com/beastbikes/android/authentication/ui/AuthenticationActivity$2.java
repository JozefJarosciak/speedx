package com.beastbikes.android.authentication.ui;

import com.alipay.sdk.util.C0880h;
import com.beastbikes.android.authentication.C1537a;
import com.beastbikes.android.authentication.C1541b.C1540b;
import com.beastbikes.android.modules.user.dto.AccountDTO;

class AuthenticationActivity$2 implements C1540b {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7276a;

    /* renamed from: com.beastbikes.android.authentication.ui.AuthenticationActivity$2$1 */
    class C15581 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ AuthenticationActivity$2 f7275a;

        C15581(AuthenticationActivity$2 authenticationActivity$2) {
            this.f7275a = authenticationActivity$2;
        }

        public void run() {
            if (AuthenticationActivity.n(this.f7275a.f7276a) != null) {
                AuthenticationActivity.n(this.f7275a.f7276a).show();
            }
        }
    }

    AuthenticationActivity$2(AuthenticationActivity authenticationActivity) {
        this.f7276a = authenticationActivity;
    }

    /* renamed from: a */
    public void mo3123a(C1537a c1537a) {
        if (c1537a != null) {
            this.f7276a.runOnUiThread(new C15581(this));
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAuthKey(c1537a.m8475b());
            accountDTO.setAccessToken(c1537a.m8474a() + C0880h.f2220b + c1537a.m8478e());
            accountDTO.setThirdNick(c1537a.m8476c());
            accountDTO.setAuthType(32);
            AuthenticationActivity.o(this.f7276a).m8465a(accountDTO, this.f7276a);
        }
    }
}
