package com.beastbikes.android.authentication.validcode;

import com.beastbikes.android.authentication.C1530e;
import com.beastbikes.android.authentication.C1542d;

/* compiled from: ValidCodePresenter */
/* renamed from: com.beastbikes.android.authentication.validcode.a */
class C1564a extends C1542d {
    C1564a(C1530e c1530e) {
        super(c1530e);
    }

    /* renamed from: a */
    void m8534a(String str) {
        String e = this.a.mo3111e();
        if ("resetPwdPhone".equals(str)) {
            e = this.a.mo3114h() + e;
        }
        super.m8492a(e, str);
    }

    /* renamed from: b */
    void m8535b(String str) {
        String e = this.a.mo3111e();
        String f = this.a.mo3112f();
        if ("resetPwdPhone".equals(str)) {
            e = this.a.mo3114h() + e;
        }
        super.m8495b(e, f);
    }
}
