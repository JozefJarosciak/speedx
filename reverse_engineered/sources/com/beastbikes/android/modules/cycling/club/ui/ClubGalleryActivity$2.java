package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.business.BusinessException;

class ClubGalleryActivity$2 extends AsyncTask<String, Void, ClubInfoCompact> {
    /* renamed from: a */
    final /* synthetic */ ClubGalleryActivity f9671a;

    ClubGalleryActivity$2(ClubGalleryActivity clubGalleryActivity) {
        this.f9671a = clubGalleryActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10804a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10805a((ClubInfoCompact) obj);
    }

    /* renamed from: a */
    protected ClubInfoCompact m10804a(String... strArr) {
        try {
            return new ClubManager(this.f9671a.getApplicationContext()).m10547c(strArr[0]);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10805a(ClubInfoCompact clubInfoCompact) {
        if (clubInfoCompact != null) {
            ClubGalleryActivity.a(this.f9671a, clubInfoCompact.getCurPhotoNum());
            ClubGalleryActivity.b(this.f9671a, clubInfoCompact.getMaxPhotoNum());
            ClubGalleryActivity.k(this.f9671a).setText(String.format("(%d/%d)", new Object[]{Integer.valueOf(clubInfoCompact.getCurPhotoNum()), Integer.valueOf(clubInfoCompact.getMaxPhotoNum())}));
        }
    }
}
