package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.modules.user.dto.ProfileDTO;

class ClubCreateActivity$10 extends AsyncTask<String, Void, ProfileDTO> {
    /* renamed from: a */
    final /* synthetic */ ClubCreateActivity f9530a;

    ClubCreateActivity$10(ClubCreateActivity clubCreateActivity) {
        this.f9530a = clubCreateActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10720a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10721a((ProfileDTO) obj);
    }

    /* renamed from: a */
    protected ProfileDTO m10720a(String... strArr) {
        try {
            return ClubCreateActivity.j(this.f9530a).m12136c(strArr[0]);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10721a(ProfileDTO profileDTO) {
        if (profileDTO != null) {
            ClubCreateActivity.c(this.f9530a, profileDTO.getCity());
            ClubCreateActivity.d(this.f9530a, profileDTO.getProvince());
            if (!TextUtils.isEmpty(ClubCreateActivity.k(this.f9530a)) && !ClubCreateActivity.k(this.f9530a).equals("null")) {
                ClubCreateActivity.l(this.f9530a).setText(ClubCreateActivity.k(this.f9530a));
            }
        }
    }
}
