package com.beastbikes.android.home;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.MedalDTO;
import java.util.List;

class HomeActivity$4 extends AsyncTask<String, Void, List<MedalDTO>> {
    /* renamed from: a */
    final /* synthetic */ HomeActivity f8231a;

    HomeActivity$4(HomeActivity homeActivity) {
        this.f8231a = homeActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9525a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9526a((List) obj);
    }

    /* renamed from: a */
    protected List<MedalDTO> m9525a(String... strArr) {
        try {
            return HomeActivity.e(this.f8231a).m12124a(2, 1, 1000, HomeActivity.d(this.f8231a));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m9526a(List<MedalDTO> list) {
        if (list != null && !list.isEmpty()) {
            HomeActivity.a(this.f8231a, list);
        }
    }
}
