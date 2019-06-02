package com.beastbikes.android.modules.cycling.sections.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.cycling.sections.dto.C2222c;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class CompetitionSectionActivity$5 extends AsyncTask<Void, Void, List<C2222c>> {
    /* renamed from: a */
    final /* synthetic */ double f10560a;
    /* renamed from: b */
    final /* synthetic */ double f10561b;
    /* renamed from: c */
    final /* synthetic */ float f10562c;
    /* renamed from: d */
    final /* synthetic */ String f10563d;
    /* renamed from: e */
    final /* synthetic */ String f10564e;
    /* renamed from: f */
    final /* synthetic */ String f10565f;
    /* renamed from: g */
    final /* synthetic */ String f10566g;
    /* renamed from: h */
    final /* synthetic */ String f10567h;
    /* renamed from: i */
    final /* synthetic */ CompetitionSectionActivity f10568i;

    CompetitionSectionActivity$5(CompetitionSectionActivity competitionSectionActivity, double d, double d2, float f, String str, String str2, String str3, String str4, String str5) {
        this.f10568i = competitionSectionActivity;
        this.f10560a = d;
        this.f10561b = d2;
        this.f10562c = f;
        this.f10563d = str;
        this.f10564e = str2;
        this.f10565f = str3;
        this.f10566g = str4;
        this.f10567h = str5;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11439a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11440a((List) obj);
    }

    protected void onPreExecute() {
        CompetitionSectionActivity.a(this.f10568i, new C1802i(this.f10568i, this.f10568i.getResources().getString(C1373R.string.str_loading), true));
        CompetitionSectionActivity.d(this.f10568i).show();
    }

    /* renamed from: a */
    protected List<C2222c> m11439a(Void... voidArr) {
        try {
            return CompetitionSectionActivity.e(this.f10568i).m11402a(this.f10560a, this.f10561b, this.f10562c, this.f10563d, this.f10564e, this.f10565f, this.f10566g, this.f10567h);
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m11440a(List<C2222c> list) {
        if (CompetitionSectionActivity.d(this.f10568i) != null) {
            CompetitionSectionActivity.d(this.f10568i).dismiss();
        }
        if (list != null && list.size() != 0) {
            CompetitionSectionActivity.a(this.f10568i, list);
            if (CompetitionSectionActivity.g(this.f10568i) != null) {
                CompetitionSectionActivity.g(this.f10568i).m11453a((List) list);
            }
            if (CompetitionSectionActivity.h(this.f10568i) != null) {
                CompetitionSectionActivity.h(this.f10568i).m11484a((List) list);
            }
        } else if (CompetitionSectionActivity.f(this.f10568i)) {
            if (CompetitionSectionActivity.g(this.f10568i) != null) {
                CompetitionSectionActivity.g(this.f10568i).m11451a();
            }
            if (CompetitionSectionActivity.h(this.f10568i) != null) {
                CompetitionSectionActivity.h(this.f10568i).m11485b();
            }
        } else {
            if (CompetitionSectionActivity.g(this.f10568i) != null) {
                CompetitionSectionActivity.g(this.f10568i).m11454b(this.f10568i.getResources().getString(C1373R.string.section_filter_failed));
            }
            if (CompetitionSectionActivity.h(this.f10568i) != null) {
                CompetitionSectionActivity.h(this.f10568i).m11485b();
            }
        }
    }
}
