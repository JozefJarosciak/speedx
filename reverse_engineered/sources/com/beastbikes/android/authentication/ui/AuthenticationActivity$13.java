package com.beastbikes.android.authentication.ui;

import com.beastbikes.android.authentication.C1537a;
import com.beastbikes.android.authentication.C1541b.C1540b;
import com.beastbikes.android.modules.user.dto.AccountDTO;

class AuthenticationActivity$13 implements C1540b {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7273a;

    /* renamed from: com.beastbikes.android.authentication.ui.AuthenticationActivity$13$1 */
    class C15571 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ AuthenticationActivity$13 f7272a;

        C15571(AuthenticationActivity$13 authenticationActivity$13) {
            this.f7272a = authenticationActivity$13;
        }

        public void run() {
            if (AuthenticationActivity.n(this.f7272a.f7273a) != null) {
                AuthenticationActivity.n(this.f7272a.f7273a).show();
            }
        }
    }

    AuthenticationActivity$13(AuthenticationActivity authenticationActivity) {
        this.f7273a = authenticationActivity;
    }

    /* renamed from: a */
    public void mo3123a(C1537a c1537a) {
        if (c1537a != null) {
            this.f7273a.runOnUiThread(new C15571(this));
            AccountDTO accountDTO = new AccountDTO();
            accountDTO.setAuthKey(c1537a.m8475b());
            accountDTO.setAccessToken(c1537a.m8474a());
            accountDTO.setThirdNick(c1537a.m8476c());
            accountDTO.setAuthType(64);
            AuthenticationActivity.o(this.f7273a).m8465a(accountDTO, this.f7273a);
        }
    }
}
