package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeedComment;
import java.util.List;

class ClubImageDetailsActivity$2 extends AsyncTask<Integer, Void, List<ClubFeedComment>> {
    /* renamed from: a */
    final /* synthetic */ int f9698a;
    /* renamed from: b */
    final /* synthetic */ ClubImageDetailsActivity f9699b;

    ClubImageDetailsActivity$2(ClubImageDetailsActivity clubImageDetailsActivity, int i) {
        this.f9699b = clubImageDetailsActivity;
        this.f9698a = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10818a((Integer[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10819a((List) obj);
    }

    /* renamed from: a */
    protected List<ClubFeedComment> m10818a(Integer... numArr) {
        return ClubImageDetailsActivity.b(this.f9699b).m10591c(this.f9698a, 1, 1000);
    }

    /* renamed from: a */
    protected void m10819a(List<ClubFeedComment> list) {
        if (list != null && list.size() > 0) {
            ClubImageDetailsActivity.a(this.f9699b).setCommentList(list);
            ClubImageDetailsActivity.c(this.f9699b).m11013a(ClubImageDetailsActivity.a(this.f9699b));
        }
    }
}
