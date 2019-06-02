package com.beastbikes.android.modules;

import com.beastbikes.android.utils.p163b.C2552b;
import java.util.Iterator;
import rx.p208a.C5694b;

class SessionFragmentActivity$1 implements C5694b<C2552b> {
    /* renamed from: a */
    final /* synthetic */ SessionFragmentActivity f8388a;

    SessionFragmentActivity$1(SessionFragmentActivity sessionFragmentActivity) {
        this.f8388a = sessionFragmentActivity;
    }

    public /* synthetic */ void call(Object obj) {
        m9709a((C2552b) obj);
    }

    /* renamed from: a */
    public void m9709a(C2552b c2552b) {
        Iterator it = SessionFragmentActivity.a(this.f8388a).iterator();
        while (it.hasNext()) {
            if (((Integer) it.next()).intValue() == c2552b.a) {
                this.f8388a.b(c2552b.a, c2552b.b);
            }
        }
    }
}
