package com.beastbikes.android.authentication.ui;

import com.beastbikes.android.authentication.C1537a;
import com.beastbikes.android.authentication.C1541b.C1540b;
import com.beastbikes.android.modules.user.dto.AccountDTO;

class AuthenticationActivity$12 implements C1540b {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7271a;

    /* renamed from: com.beastbikes.android.authentication.ui.AuthenticationActivity$12$1 */
    class C15561 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ AuthenticationActivity$12 f7270a;

        C15561(AuthenticationActivity$12 authenticationActivity$12) {
            this.f7270a = authenticationActivity$12;
        }

        public void run() {
            if (AuthenticationActivity.n(this.f7270a.f7271a) != null) {
                AuthenticationActivity.n(this.f7270a.f7271a).show();
            }
        }
    }

    AuthenticationActivity$12(AuthenticationActivity authenticationActivity) {
        this.f7271a = authenticationActivity;
    }

    /* renamed from: a */
    public void mo3123a(C1537a c1537a) {
        if (c1537a != null) {
            this.f7271a.runOnUiThread(new C15561(this));
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAuthKey(c1537a.m8475b());
            accountDTO.setAccessToken(c1537a.m8474a());
            accountDTO.setThirdNick(c1537a.m8476c());
            accountDTO.setAuthType(16);
            AuthenticationActivity.o(this.f7271a).m8465a(accountDTO, this.f7271a);
        }
    }
}
