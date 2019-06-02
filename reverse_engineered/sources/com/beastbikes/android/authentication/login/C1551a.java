package com.beastbikes.android.authentication.login;

import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.C1530e;
import com.beastbikes.android.authentication.C1542d;
import com.beastbikes.android.authentication.p055a.C1536a.C1535a;
import com.beastbikes.android.modules.user.dto.AccountDTO;

/* compiled from: LoginPresenter */
/* renamed from: com.beastbikes.android.authentication.login.a */
class C1551a extends C1542d {
    C1551a(C1530e c1530e) {
        super(c1530e);
    }

    /* renamed from: c */
    void mo3124c() {
        int i = this.a.mo3115i();
        String e = this.a.mo3111e();
        String h = this.a.mo3114h();
        if (TextUtils.isEmpty(e)) {
            this.a.mo3109c(this.b.getString(i == 2 ? C1373R.string.str_please_input_phone_number : C1373R.string.str_please_input_email));
        } else if (i != 2 || e.length() >= 6) {
            Object g = this.a.mo3113g();
            if (TextUtils.isEmpty(g)) {
                this.a.mo3109c(this.b.getString(C1373R.string.str_please_input_password));
                return;
            }
            this.a.mo3118l();
            AccountDTO accountDTO = new AccountDTO();
            if (i == 2) {
                e = h + e;
            }
            accountDTO.setUsername(e);
            accountDTO.setPassword(g);
            accountDTO.setAuthType(i);
            this.c.m8465a(accountDTO, (C1535a) this);
        } else {
            this.a.mo3109c(this.b.getString(C1373R.string.str_phone_error));
        }
    }
}
