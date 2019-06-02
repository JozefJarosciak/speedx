package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;

class ClubRankActivity$1 extends AsyncTask<Integer, Void, ClubInfoCompact> {
    /* renamed from: a */
    final /* synthetic */ int f9791a;
    /* renamed from: b */
    final /* synthetic */ ClubRankActivity f9792b;

    ClubRankActivity$1(ClubRankActivity clubRankActivity, int i) {
        this.f9792b = clubRankActivity;
        this.f9791a = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10864a((Integer[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10865a((ClubInfoCompact) obj);
    }

    /* renamed from: a */
    protected ClubInfoCompact m10864a(Integer... numArr) {
        if (ClubRankActivity.a(this.f9792b)) {
            return ClubRankActivity.b(this.f9792b).m10532a(this.f9791a);
        }
        return null;
    }

    /* renamed from: a */
    protected void m10865a(ClubInfoCompact clubInfoCompact) {
        if (clubInfoCompact != null) {
            if (C1849a.m9645b(this.f9792b)) {
                ClubRankActivity.c(this.f9792b).setText(Math.round(clubInfoCompact.getMilestone() / 1000.0d) + "" + this.f9792b.getResources().getText(C1373R.string.kilometre));
            } else {
                ClubRankActivity.c(this.f9792b).setText(Math.round(C1849a.m9638a(clubInfoCompact.getMilestone() / 1000.0d)) + "" + this.f9792b.getResources().getText(C1373R.string.str_mileage_unit_mile));
            }
            ClubRankActivity.d(this.f9792b).setText(clubInfoCompact.getRank() + "");
        }
    }
}
