package com.beastbikes.android.modules.cycling.sections.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.sections.dto.C2224e;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class FavorSegmentActivity$1 extends AsyncTask<Void, Void, List<C2224e>> {
    /* renamed from: a */
    final /* synthetic */ FavorSegmentActivity f10571a;

    FavorSegmentActivity$1(FavorSegmentActivity favorSegmentActivity) {
        this.f10571a = favorSegmentActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11441a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11442a((List) obj);
    }

    /* renamed from: a */
    protected List<C2224e> m11441a(Void... voidArr) {
        try {
            return FavorSegmentActivity.d(this.f10571a).m11404a(FavorSegmentActivity.a(this.f10571a), FavorSegmentActivity.b(this.f10571a), FavorSegmentActivity.c(this.f10571a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11442a(List<C2224e> list) {
        if (list == null || list.size() == 0) {
            if (FavorSegmentActivity.b(this.f10571a) == 1) {
                FavorSegmentActivity.e(this.f10571a).setHasFooter(false);
                FavorSegmentActivity.f(this.f10571a).setVisibility(0);
            }
            FavorSegmentActivity.e(this.f10571a).m13150a();
            return;
        }
        FavorSegmentActivity.f(this.f10571a).setVisibility(8);
        if (FavorSegmentActivity.g(this.f10571a)) {
            FavorSegmentActivity.h(this.f10571a).clear();
        }
        FavorSegmentActivity.a(this.f10571a, false);
        FavorSegmentActivity.h(this.f10571a).addAll(list);
        if (FavorSegmentActivity.b(this.f10571a) == 1 && FavorSegmentActivity.h(this.f10571a).size() < FavorSegmentActivity.c(this.f10571a)) {
            FavorSegmentActivity.e(this.f10571a).setHasFooter(false);
        }
        FavorSegmentActivity.e(this.f10571a).m13153b();
        FavorSegmentActivity.e(this.f10571a).m13150a();
    }
}
