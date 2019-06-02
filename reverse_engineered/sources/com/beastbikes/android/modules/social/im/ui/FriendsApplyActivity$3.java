package com.beastbikes.android.modules.social.im.ui;

import android.os.AsyncTask;
import com.avos.avoscloud.AVAnalytics;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.social.im.p074a.C2341a;

class FriendsApplyActivity$3 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ int f11145a;
    /* renamed from: b */
    final /* synthetic */ int f11146b;
    /* renamed from: c */
    final /* synthetic */ int f11147c;
    /* renamed from: d */
    final /* synthetic */ FriendsApplyActivity f11148d;

    FriendsApplyActivity$3(FriendsApplyActivity friendsApplyActivity, int i, int i2, int i3) {
        this.f11148d = friendsApplyActivity;
        this.f11145a = i;
        this.f11146b = i2;
        this.f11147c = i3;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11963a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11964a((Boolean) obj);
    }

    protected void onPreExecute() {
        if (FriendsApplyActivity.b(this.f11148d) != null) {
            FriendsApplyActivity.b(this.f11148d).show();
        }
    }

    /* renamed from: a */
    protected Boolean m11963a(String... strArr) {
        return Boolean.valueOf(FriendsApplyActivity.c(this.f11148d).m11952a(this.f11145a, this.f11146b));
    }

    /* renamed from: a */
    protected void m11964a(Boolean bool) {
        if (FriendsApplyActivity.b(this.f11148d) != null) {
            FriendsApplyActivity.b(this.f11148d).dismiss();
        }
        if (bool.booleanValue()) {
            if (this.f11146b == 0) {
                FriendDTO friendDTO = (FriendDTO) FriendsApplyActivity.d(this.f11148d).get(this.f11147c);
                friendDTO.setStatus(3);
                FriendsApplyActivity.e(this.f11148d).notifyDataSetChanged();
                new C2341a(this.f11148d).m11950a(FriendsApplyActivity.f(this.f11148d), friendDTO);
                AVAnalytics.onEvent(this.f11148d, "同意好友申请");
            }
            if (this.f11146b == 1) {
                FriendsApplyActivity.d(this.f11148d).remove(this.f11147c);
                FriendsApplyActivity.e(this.f11148d).notifyDataSetChanged();
                AVAnalytics.onEvent(this.f11148d, "拒绝好友申请");
            }
        }
    }
}
