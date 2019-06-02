package com.beastbikes.android.authentication.ui;

import com.beastbikes.android.authentication.C1537a;
import com.beastbikes.android.authentication.C1541b.C1540b;
import com.beastbikes.android.modules.user.dto.AccountDTO;

class AuthenticationActivity$3 implements C1540b {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7278a;

    /* renamed from: com.beastbikes.android.authentication.ui.AuthenticationActivity$3$1 */
    class C15591 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ AuthenticationActivity$3 f7277a;

        C15591(AuthenticationActivity$3 authenticationActivity$3) {
            this.f7277a = authenticationActivity$3;
        }

        public void run() {
            if (AuthenticationActivity.n(this.f7277a.f7278a) != null) {
                AuthenticationActivity.n(this.f7277a.f7278a).show();
            }
        }
    }

    AuthenticationActivity$3(AuthenticationActivity authenticationActivity) {
        this.f7278a = authenticationActivity;
    }

    /* renamed from: a */
    public void mo3123a(C1537a c1537a) {
        if (c1537a != null) {
            this.f7278a.runOnUiThread(new C15591(this));
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAuthKey(c1537a.m8475b());
            accountDTO.setAccessToken(c1537a.m8474a());
            accountDTO.setThirdNick(c1537a.m8476c());
            accountDTO.setAuthType(128);
            AuthenticationActivity.o(this.f7278a).m8465a(accountDTO, this.f7278a);
        }
    }
}
