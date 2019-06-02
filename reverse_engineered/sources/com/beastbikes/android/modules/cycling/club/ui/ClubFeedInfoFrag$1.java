package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.business.BusinessException;

class ClubFeedInfoFrag$1 extends AsyncTask<String, Void, ClubInfoCompact> {
    /* renamed from: a */
    final /* synthetic */ ClubFeedInfoFrag f9637a;

    ClubFeedInfoFrag$1(ClubFeedInfoFrag clubFeedInfoFrag) {
        this.f9637a = clubFeedInfoFrag;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10788a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10789a((ClubInfoCompact) obj);
    }

    /* renamed from: a */
    protected ClubInfoCompact m10788a(String... strArr) {
        try {
            return ClubFeedInfoFrag.a(this.f9637a).m10547c(strArr[0]);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10789a(ClubInfoCompact clubInfoCompact) {
        boolean z = false;
        if (clubInfoCompact == null) {
            ClubFeedInfoFrag.b(this.f9637a).edit().putInt("beast.club.status", 0).apply();
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                currentUser.setClubId("");
                return;
            }
            return;
        }
        ClubFeedInfoFrag.a(this.f9637a, clubInfoCompact);
        ClubFeedInfoFrag.a(this.f9637a, ClubFeedInfoFrag.c(this.f9637a).getIsPrivate());
        ClubFeedInfoFrag clubFeedInfoFrag = this.f9637a;
        if (ClubFeedInfoFrag.c(this.f9637a).getLevel() == 128) {
            z = true;
        }
        ClubFeedInfoFrag.b(clubFeedInfoFrag, z);
        ClubFeedInfoFrag.d(this.f9637a);
    }
}
