package com.beastbikes.android.authentication.findpassword;

import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.C1530e;
import com.beastbikes.android.authentication.C1542d;

/* compiled from: FindPasswordPresenter */
/* renamed from: com.beastbikes.android.authentication.findpassword.a */
class C1548a extends C1542d {
    C1548a(C1530e c1530e) {
        super(c1530e);
    }

    /* renamed from: c */
    void mo3124c() {
        int i = this.a.mo3115i();
        String e = this.a.mo3111e();
        String h = this.a.mo3114h();
        if (TextUtils.isEmpty(e)) {
            this.a.mo3109c(this.b.getString(i == 2 ? C1373R.string.str_please_input_phone_number : C1373R.string.str_please_input_email));
        } else if (i == 2) {
            m8492a(h + e, "resetPwdPhone");
        } else if (i == 1) {
            m8492a(e, "resetPwdEmail");
        }
    }
}
