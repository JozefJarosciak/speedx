package com.beastbikes.android.modules.social.im.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import java.util.List;

class FriendsSearchResultActivity$1 extends AsyncTask<String, Void, List<FriendDTO>> {
    /* renamed from: a */
    final /* synthetic */ String f11156a;
    /* renamed from: b */
    final /* synthetic */ FriendsSearchResultActivity f11157b;

    FriendsSearchResultActivity$1(FriendsSearchResultActivity friendsSearchResultActivity, String str) {
        this.f11157b = friendsSearchResultActivity;
        this.f11156a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11969a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11970a((List) obj);
    }

    protected void onPreExecute() {
        if (FriendsSearchResultActivity.a(this.f11157b) != null) {
            FriendsSearchResultActivity.a(this.f11157b).show();
        }
    }

    /* renamed from: a */
    protected List<FriendDTO> m11969a(String... strArr) {
        return FriendsSearchResultActivity.b(this.f11157b).m11949a(this.f11156a, 1, 20);
    }

    /* renamed from: a */
    protected void m11970a(List<FriendDTO> list) {
        if (FriendsSearchResultActivity.a(this.f11157b) != null) {
            FriendsSearchResultActivity.a(this.f11157b).dismiss();
        }
        if (list != null) {
            FriendsSearchResultActivity.c(this.f11157b).setPullLoadEnable(true);
            if (list.size() < 20) {
                FriendsSearchResultActivity.c(this.f11157b).setPullLoadEnable(false);
                FriendsSearchResultActivity.c(this.f11157b).m12977a(0);
            }
            if (list != null && !list.isEmpty()) {
                if (FriendsSearchResultActivity.d(this.f11157b) == 1) {
                    FriendsSearchResultActivity.f(this.f11157b).clear();
                }
                FriendsSearchResultActivity.f(this.f11157b).addAll(list);
                FriendsSearchResultActivity.g(this.f11157b).notifyDataSetChanged();
                FriendsSearchResultActivity.h(this.f11157b);
                FriendsSearchResultActivity.c(this.f11157b).setVisibility(0);
                FriendsSearchResultActivity.e(this.f11157b).setVisibility(8);
            } else if (FriendsSearchResultActivity.d(this.f11157b) == 1) {
                FriendsSearchResultActivity.c(this.f11157b).setVisibility(8);
                FriendsSearchResultActivity.e(this.f11157b).setVisibility(0);
            }
        }
    }
}
