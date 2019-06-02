package com.beastbikes.android.authentication.ui;

import com.beastbikes.android.authentication.C1537a;
import com.beastbikes.android.authentication.C1541b.C1540b;
import com.beastbikes.android.modules.user.dto.AccountDTO;

class AuthenticationActivity$11 implements C1540b {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7269a;

    /* renamed from: com.beastbikes.android.authentication.ui.AuthenticationActivity$11$1 */
    class C15551 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ AuthenticationActivity$11 f7268a;

        C15551(AuthenticationActivity$11 authenticationActivity$11) {
            this.f7268a = authenticationActivity$11;
        }

        public void run() {
            if (AuthenticationActivity.n(this.f7268a.f7269a) != null) {
                AuthenticationActivity.n(this.f7268a.f7269a).show();
            }
        }
    }

    AuthenticationActivity$11(AuthenticationActivity authenticationActivity) {
        this.f7269a = authenticationActivity;
    }

    /* renamed from: a */
    public void mo3123a(C1537a c1537a) {
        if (c1537a != null) {
            this.f7269a.runOnUiThread(new C15551(this));
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAuthKey(c1537a.m8475b());
            accountDTO.setAccessToken(c1537a.m8474a());
            accountDTO.setThirdNick(c1537a.m8476c());
            accountDTO.setAuthType(8);
            AuthenticationActivity.o(this.f7269a).m8465a(accountDTO, this.f7269a);
        }
    }
}
