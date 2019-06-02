package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.business.BusinessException;

class ClubCreateActivity$9 extends AsyncTask<String, Void, ClubInfoCompact> {
    /* renamed from: a */
    final /* synthetic */ ClubCreateActivity f9560a;

    ClubCreateActivity$9(ClubCreateActivity clubCreateActivity) {
        this.f9560a = clubCreateActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10732a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10733a((ClubInfoCompact) obj);
    }

    /* renamed from: a */
    protected ClubInfoCompact m10732a(String... strArr) {
        ClubInfoCompact clubInfoCompact = null;
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            try {
                clubInfoCompact = ClubCreateActivity.g(this.f9560a).m10533a(currentUser.getObjectId());
            } catch (BusinessException e) {
            }
        }
        return clubInfoCompact;
    }

    /* renamed from: a */
    protected void m10733a(ClubInfoCompact clubInfoCompact) {
        if (clubInfoCompact != null) {
            ClubCreateActivity.c(this.f9560a, clubInfoCompact.getStatus());
            ClubCreateActivity.b(this.f9560a, clubInfoCompact.getObjectId());
            ClubCreateActivity.i(this.f9560a);
        }
    }
}
