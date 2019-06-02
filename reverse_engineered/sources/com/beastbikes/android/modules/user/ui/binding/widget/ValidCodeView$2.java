package com.beastbikes.android.modules.user.ui.binding.widget;

import com.beastbikes.android.utils.p163b.C2552b;
import rx.p208a.C5694b;

class ValidCodeView$2 implements C5694b<C2552b> {
    /* renamed from: a */
    final /* synthetic */ ValidCodeView f11903a;

    ValidCodeView$2(ValidCodeView validCodeView) {
        this.f11903a = validCodeView;
    }

    public /* synthetic */ void call(Object obj) {
        m12621a((C2552b) obj);
    }

    /* renamed from: a */
    public void m12621a(C2552b c2552b) {
        if (c2552b.a == 9) {
            ValidCodeView.a(this.f11903a, new ValidCodeView$a(this.f11903a, ValidCodeView.b(this.f11903a), ValidCodeView.c(this.f11903a)));
            this.f11903a.setEnable(false);
            ValidCodeView.d(this.f11903a).start();
        }
    }
}
