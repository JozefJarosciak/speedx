package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ClubImageDetailsActivity$3 extends AsyncTask<Integer, Void, List<ClubUser>> {
    /* renamed from: a */
    final /* synthetic */ int f9700a;
    /* renamed from: b */
    final /* synthetic */ ClubImageDetailsActivity f9701b;

    ClubImageDetailsActivity$3(ClubImageDetailsActivity clubImageDetailsActivity, int i) {
        this.f9701b = clubImageDetailsActivity;
        this.f9700a = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10820a((Integer[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10821a((List) obj);
    }

    /* renamed from: a */
    protected List<ClubUser> m10820a(Integer... numArr) {
        try {
            return ClubImageDetailsActivity.b(this.f9701b).m10595d(this.f9700a, 1, 1000);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10821a(List<ClubUser> list) {
        if (list != null && list.size() > 0) {
            ClubImageDetailsActivity.a(this.f9701b).setLikeUserList(list);
            ClubImageDetailsActivity.c(this.f9701b).m11013a(ClubImageDetailsActivity.a(this.f9701b));
        }
    }
}
