package com.beastbikes.android.authentication.ui;

import com.beastbikes.android.authentication.C1537a;
import com.beastbikes.android.authentication.C1541b.C1540b;
import com.beastbikes.android.modules.user.dto.AccountDTO;

class AuthenticationActivity$10 implements C1540b {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7267a;

    /* renamed from: com.beastbikes.android.authentication.ui.AuthenticationActivity$10$1 */
    class C15541 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ AuthenticationActivity$10 f7266a;

        C15541(AuthenticationActivity$10 authenticationActivity$10) {
            this.f7266a = authenticationActivity$10;
        }

        public void run() {
            if (AuthenticationActivity.n(this.f7266a.f7267a) != null) {
                AuthenticationActivity.n(this.f7266a.f7267a).show();
            }
        }
    }

    AuthenticationActivity$10(AuthenticationActivity authenticationActivity) {
        this.f7267a = authenticationActivity;
    }

    /* renamed from: a */
    public void mo3123a(C1537a c1537a) {
        if (c1537a != null) {
            this.f7267a.runOnUiThread(new C15541(this));
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAuthKey(c1537a.m8475b());
            accountDTO.setAccessToken(c1537a.m8474a());
            accountDTO.setThirdNick(c1537a.m8476c());
            accountDTO.setAuthType(4);
            AuthenticationActivity.o(this.f7267a).m8465a(accountDTO, this.f7267a);
        }
    }
}
