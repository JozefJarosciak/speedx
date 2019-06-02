package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import java.util.List;

class FollowActivity$2 extends AsyncTask<String, Void, List<FriendDTO>> {
    /* renamed from: a */
    final /* synthetic */ FollowActivity f11621a;

    FollowActivity$2(FollowActivity followActivity) {
        this.f11621a = followActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12450a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12451a((List) obj);
    }

    /* renamed from: a */
    protected List<FriendDTO> m12450a(String... strArr) {
        return FollowActivity.c(this.f11621a).m12132b(FollowActivity.a(this.f11621a), FollowActivity.b(this.f11621a), 20);
    }

    /* renamed from: a */
    protected void m12451a(List<FriendDTO> list) {
        FollowActivity.a(this.f11621a, false);
        FollowActivity.d(this.f11621a).m13150a();
        if (list == null || list.size() <= 0) {
            FollowActivity.d(this.f11621a).setCanLoadMore(false);
            FollowActivity.d(this.f11621a).setHasFooter(false);
            return;
        }
        FollowActivity.e(this.f11621a).setVisibility(8);
        if (list.size() < 20) {
            FollowActivity.d(this.f11621a).setCanLoadMore(false);
            FollowActivity.d(this.f11621a).setHasFooter(false);
        }
        if (FollowActivity.b(this.f11621a) == 1) {
            FollowActivity.f(this.f11621a).clear();
        }
        FollowActivity.a(this.f11621a, FollowActivity.b(this.f11621a) + 1);
        FollowActivity.f(this.f11621a).addAll(list);
        FollowActivity.d(this.f11621a).m13153b();
    }
}
