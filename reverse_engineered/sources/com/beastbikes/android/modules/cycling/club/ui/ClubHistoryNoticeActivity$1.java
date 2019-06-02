package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.C2067f;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ClubHistoryNoticeActivity$1 extends AsyncTask<Void, Void, List<C2067f>> {
    /* renamed from: a */
    final /* synthetic */ ClubHistoryNoticeActivity f9692a;

    ClubHistoryNoticeActivity$1(ClubHistoryNoticeActivity clubHistoryNoticeActivity) {
        this.f9692a = clubHistoryNoticeActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10814a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10815a((List) obj);
    }

    /* renamed from: a */
    protected List<C2067f> m10814a(Void... voidArr) {
        try {
            return ClubHistoryNoticeActivity.d(this.f9692a).m10539a(ClubHistoryNoticeActivity.a(this.f9692a), ClubHistoryNoticeActivity.b(this.f9692a), ClubHistoryNoticeActivity.c(this.f9692a));
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10815a(List<C2067f> list) {
        if (ClubHistoryNoticeActivity.e(this.f9692a) != null && ClubHistoryNoticeActivity.e(this.f9692a).isShowing()) {
            ClubHistoryNoticeActivity.e(this.f9692a).dismiss();
        }
        ClubHistoryNoticeActivity.f(this.f9692a).m12976a();
        if (list == null || list.size() == 0) {
            ClubHistoryNoticeActivity.f(this.f9692a).m12977a(0);
            ClubHistoryNoticeActivity.f(this.f9692a).setPullLoadEnable(false);
            return;
        }
        ClubHistoryNoticeActivity.g(this.f9692a).addAll(list);
        ClubHistoryNoticeActivity.h(this.f9692a).notifyDataSetChanged();
    }
}
