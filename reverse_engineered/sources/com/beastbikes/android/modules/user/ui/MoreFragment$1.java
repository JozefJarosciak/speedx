package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.framework.business.BusinessException;

class MoreFragment$1 extends AsyncTask<Void, Void, Void> {
    /* renamed from: a */
    final /* synthetic */ MoreFragment f11742a;

    MoreFragment$1(MoreFragment moreFragment) {
        this.f11742a = moreFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12506a((Void[]) objArr);
    }

    /* renamed from: a */
    protected Void m12506a(Void... voidArr) {
        try {
            MoreFragment.a(this.f11742a, new C2389c(this.f11742a.getActivity()).m12136c(MoreFragment.a(this.f11742a)));
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
