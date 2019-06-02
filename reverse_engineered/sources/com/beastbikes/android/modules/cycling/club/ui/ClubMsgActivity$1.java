package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.framework.business.BusinessException;

class ClubMsgActivity$1 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubMsgActivity f9773a;

    ClubMsgActivity$1(ClubMsgActivity clubMsgActivity) {
        this.f9773a = clubMsgActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10856a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10857a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m10856a(Void... voidArr) {
        try {
            return Boolean.valueOf(ClubMsgActivity.a(this.f9773a).m10581a());
        } catch (BusinessException e) {
            e.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10857a(Boolean bool) {
        if (ClubMsgActivity.b(this.f9773a) != null) {
            ClubMsgActivity.b(this.f9773a).dismiss();
        }
        if (bool != null && bool.booleanValue()) {
            ClubMsgActivity.c(this.f9773a).clear();
            ClubMsgActivity.d(this.f9773a).notifyDataSetChanged();
            this.f9773a.finish();
        }
    }
}
