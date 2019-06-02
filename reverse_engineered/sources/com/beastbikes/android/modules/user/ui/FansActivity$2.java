package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import java.util.List;

class FansActivity$2 extends AsyncTask<String, Void, List<FriendDTO>> {
    /* renamed from: a */
    final /* synthetic */ FansActivity f11587a;

    FansActivity$2(FansActivity fansActivity) {
        this.f11587a = fansActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12432a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12433a((List) obj);
    }

    /* renamed from: a */
    protected List<FriendDTO> m12432a(String... strArr) {
        return FansActivity.e(this.f11587a).m12126a(FansActivity.c(this.f11587a), FansActivity.d(this.f11587a), 20);
    }

    /* renamed from: a */
    protected void m12433a(List<FriendDTO> list) {
        FansActivity.b(this.f11587a).m13150a();
        if (list == null || list.size() <= 0) {
            FansActivity.b(this.f11587a).setCanLoadMore(false);
            FansActivity.b(this.f11587a).setHasFooter(false);
            return;
        }
        FansActivity.f(this.f11587a).setVisibility(8);
        if (list.size() < 20) {
            FansActivity.b(this.f11587a).setCanLoadMore(false);
            FansActivity.b(this.f11587a).setHasFooter(false);
        }
        if (FansActivity.d(this.f11587a) == 1) {
            FansActivity.a(this.f11587a).clear();
        }
        FansActivity.a(this.f11587a, FansActivity.d(this.f11587a) + 1);
        FansActivity.a(this.f11587a).addAll(list);
        FansActivity.b(this.f11587a).m13153b();
    }
}
