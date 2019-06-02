package com.beastbikes.android.modules.social.im.ui;

import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.framework.android.p088g.C2799c;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;

class FriendsApplyActivity$4 extends AsyncTask<Integer, Void, List<FriendDTO>> {
    /* renamed from: a */
    final /* synthetic */ FriendsApplyActivity f11149a;

    FriendsApplyActivity$4(FriendsApplyActivity friendsApplyActivity) {
        this.f11149a = friendsApplyActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11965a((Integer[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11966a((List) obj);
    }

    protected void onPreExecute() {
        if (C2799c.m13753a(this.f11149a.getApplicationContext()) == null) {
            Toasts.show(this.f11149a.getApplicationContext(), (int) C1373R.string.setting_fragment_item_upload_error_log_failed);
        } else if (FriendsApplyActivity.b(this.f11149a) != null) {
            FriendsApplyActivity.b(this.f11149a).show();
        }
    }

    /* renamed from: a */
    protected List<FriendDTO> m11965a(Integer... numArr) {
        return FriendsApplyActivity.c(this.f11149a).m11955b(1, 50);
    }

    /* renamed from: a */
    protected void m11966a(List<FriendDTO> list) {
        if (FriendsApplyActivity.b(this.f11149a) != null) {
            FriendsApplyActivity.b(this.f11149a).dismiss();
        }
        if (list != null && !list.isEmpty()) {
            FriendsApplyActivity.g(this.f11149a).setPullLoadEnable(true);
            if (list.size() < 50) {
                FriendsApplyActivity.g(this.f11149a).setPullLoadEnable(false);
                FriendsApplyActivity.g(this.f11149a).m12977a(0);
            }
            if (FriendsApplyActivity.d(this.f11149a) == null) {
                FriendsApplyActivity.a(this.f11149a, new ArrayList());
            }
            if (FriendsApplyActivity.h(this.f11149a) == 1) {
                FriendsApplyActivity.d(this.f11149a).clear();
            }
            FriendsApplyActivity.d(this.f11149a).addAll(list);
            FriendsApplyActivity.e(this.f11149a).notifyDataSetChanged();
            if (FriendsApplyActivity.d(this.f11149a) == null || FriendsApplyActivity.d(this.f11149a).isEmpty()) {
                FriendsApplyActivity.g(this.f11149a).setVisibility(8);
                FriendsApplyActivity.i(this.f11149a).setVisibility(0);
                return;
            }
            FriendsApplyActivity.g(this.f11149a).setVisibility(0);
            FriendsApplyActivity.i(this.f11149a).setVisibility(8);
            Editor edit = FriendsApplyActivity.j(this.f11149a).edit();
            edit.putLong("beast.friend.apply.last.time", System.currentTimeMillis() / 1000);
            edit.apply();
        }
    }
}
