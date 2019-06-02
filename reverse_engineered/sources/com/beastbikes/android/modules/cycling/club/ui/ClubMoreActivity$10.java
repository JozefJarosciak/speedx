package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.business.BusinessException;

class ClubMoreActivity$10 extends AsyncTask<String, Void, ClubInfoCompact> {
    /* renamed from: a */
    final /* synthetic */ ClubMoreActivity f9743a;

    ClubMoreActivity$10(ClubMoreActivity clubMoreActivity) {
        this.f9743a = clubMoreActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10842a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10843a((ClubInfoCompact) obj);
    }

    /* renamed from: a */
    protected ClubInfoCompact m10842a(String... strArr) {
        try {
            return ClubMoreActivity.a(this.f9743a).m10547c(strArr[0]);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10843a(ClubInfoCompact clubInfoCompact) {
        if (clubInfoCompact != null) {
            ClubMoreActivity.a(this.f9743a, clubInfoCompact);
            ClubMoreActivity.n(this.f9743a);
        }
    }
}
