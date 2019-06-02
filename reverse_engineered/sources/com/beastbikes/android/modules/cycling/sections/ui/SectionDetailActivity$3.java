package com.beastbikes.android.modules.cycling.sections.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.sections.dto.C2223d;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class SectionDetailActivity$3 extends AsyncTask<Void, Void, List<C2223d>> {
    /* renamed from: a */
    final /* synthetic */ SectionDetailActivity f10587a;

    SectionDetailActivity$3(SectionDetailActivity sectionDetailActivity) {
        this.f10587a = sectionDetailActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11459a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11460a((List) obj);
    }

    /* renamed from: a */
    protected List<C2223d> m11459a(Void... voidArr) {
        try {
            return SectionDetailActivity.c(this.f10587a).m11403a(SectionDetailActivity.b(this.f10587a), SectionDetailActivity.p(this.f10587a), SectionDetailActivity.q(this.f10587a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11460a(List<C2223d> list) {
        SectionDetailActivity.a(this.f10587a, false);
        if (list != null && list.size() != 0) {
            SectionDetailActivity.r(this.f10587a).addAll(list);
            SectionDetailActivity.s(this.f10587a).notifyDataSetChanged();
        }
    }
}
