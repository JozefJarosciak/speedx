package com.beastbikes.android.modules.cycling.sections.ui;

import android.os.AsyncTask;
import com.beastbikes.framework.business.BusinessException;

class SectionDetailActivity$1 extends AsyncTask<Void, Void, Integer> {
    /* renamed from: a */
    final /* synthetic */ SectionDetailActivity f10585a;

    SectionDetailActivity$1(SectionDetailActivity sectionDetailActivity) {
        this.f10585a = sectionDetailActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11455a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11456a((Integer) obj);
    }

    protected void onPreExecute() {
        if (SectionDetailActivity.a(this.f10585a) != null) {
            SectionDetailActivity.a(this.f10585a).show();
        }
    }

    /* renamed from: a */
    protected Integer m11455a(Void... voidArr) {
        try {
            return Integer.valueOf(SectionDetailActivity.c(this.f10585a).m11399a(SectionDetailActivity.b(this.f10585a)));
        } catch (BusinessException e) {
            e.printStackTrace();
            return Integer.valueOf(-1);
        }
    }

    /* renamed from: a */
    protected void m11456a(Integer num) {
        if (SectionDetailActivity.a(this.f10585a) != null) {
            SectionDetailActivity.a(this.f10585a).dismiss();
        }
        SectionDetailActivity.d(this.f10585a).setEnabled(true);
        if (num.intValue() != -1) {
            SectionDetailActivity.e(this.f10585a).setText("（" + num + "）");
            if (SectionDetailActivity.d(this.f10585a).isSelected()) {
                SectionDetailActivity.d(this.f10585a).setSelected(false);
            } else {
                SectionDetailActivity.d(this.f10585a).setSelected(true);
            }
        }
    }
}
