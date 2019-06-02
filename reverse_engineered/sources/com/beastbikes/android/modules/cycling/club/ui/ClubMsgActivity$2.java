package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.C2066e;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ClubMsgActivity$2 extends AsyncTask<Void, Void, List<C2066e>> {
    /* renamed from: a */
    final /* synthetic */ ClubMsgActivity f9774a;

    ClubMsgActivity$2(ClubMsgActivity clubMsgActivity) {
        this.f9774a = clubMsgActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10858a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10859a((List) obj);
    }

    /* renamed from: a */
    protected List<C2066e> m10858a(Void... voidArr) {
        try {
            return ClubMsgActivity.a(this.f9774a).m10578a(ClubMsgActivity.e(this.f9774a), ClubMsgActivity.f(this.f9774a), ClubMsgActivity.g(this.f9774a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m10859a(List<C2066e> list) {
        if (ClubMsgActivity.b(this.f9774a) != null) {
            ClubMsgActivity.b(this.f9774a).dismiss();
        }
        if (list == null) {
            ClubMsgActivity.h(this.f9774a).setPullLoadEnable(false);
            return;
        }
        ClubMsgActivity.a(this.f9774a, Long.valueOf(((C2066e) list.get(list.size() - 1)).m10643b()));
        ClubMsgActivity.c(this.f9774a).addAll(list);
        ClubMsgActivity.d(this.f9774a).notifyDataSetChanged();
    }
}
