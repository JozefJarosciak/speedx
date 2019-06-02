package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import java.util.List;

class ClubGalleryActivity$6 extends AsyncTask<String, Void, List<Integer>> {
    /* renamed from: a */
    final /* synthetic */ ClubGalleryActivity f9678a;

    ClubGalleryActivity$6(ClubGalleryActivity clubGalleryActivity) {
        this.f9678a = clubGalleryActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10806a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10807a((List) obj);
    }

    protected void onPreExecute() {
        ClubGalleryActivity.a(this.f9678a, new C1802i(this.f9678a, this.f9678a.getString(C1373R.string.loading_msg), true));
        ClubGalleryActivity.c(this.f9678a).show();
    }

    /* renamed from: a */
    protected List<Integer> m10806a(String... strArr) {
        return ClubGalleryActivity.e(this.f9678a).m10592c(strArr[0]);
    }

    /* renamed from: a */
    protected void m10807a(List<Integer> list) {
        if (list != null) {
            this.f9678a.onRefresh();
            ClubGalleryActivity.b(this.f9678a, false);
            ClubGalleryActivity.m(this.f9678a);
        }
    }
}
