package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubUser;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ThumbsListActivity$1 extends AsyncTask<Void, Void, List<ClubUser>> {
    /* renamed from: a */
    final /* synthetic */ ThumbsListActivity f9835a;

    ThumbsListActivity$1(ThumbsListActivity thumbsListActivity) {
        this.f9835a = thumbsListActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10916a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10917a((List) obj);
    }

    /* renamed from: a */
    protected List<ClubUser> m10916a(Void... voidArr) {
        try {
            if (ThumbsListActivity.a(this.f9835a) == -1) {
                return ThumbsListActivity.e(this.f9835a).m10595d(ThumbsListActivity.b(this.f9835a), ThumbsListActivity.c(this.f9835a), ThumbsListActivity.d(this.f9835a));
            }
            return ThumbsListActivity.e(this.f9835a).m10571a(ThumbsListActivity.a(this.f9835a), ThumbsListActivity.c(this.f9835a), ThumbsListActivity.d(this.f9835a));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m10917a(List<ClubUser> list) {
        if (ThumbsListActivity.f(this.f9835a) != null) {
            ThumbsListActivity.f(this.f9835a).dismiss();
        }
        if (list == null) {
            if (ThumbsListActivity.c(this.f9835a) == 1) {
                ThumbsListActivity.g(this.f9835a).setVisibility(0);
                ThumbsListActivity.h(this.f9835a).setVisibility(0);
            }
            ThumbsListActivity.i(this.f9835a).setPullLoadEnable(false);
            return;
        }
        ThumbsListActivity.j(this.f9835a).addAll(list);
        ThumbsListActivity.k(this.f9835a).notifyDataSetChanged();
    }
}
