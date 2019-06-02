package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.framework.ui.android.utils.Toasts;

class FollowSearchResultActivity$5 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f11688a;
    /* renamed from: b */
    final /* synthetic */ int f11689b;
    /* renamed from: c */
    final /* synthetic */ FollowSearchResultActivity f11690c;

    FollowSearchResultActivity$5(FollowSearchResultActivity followSearchResultActivity, String str, int i) {
        this.f11690c = followSearchResultActivity;
        this.f11688a = str;
        this.f11689b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12482a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12483a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m12482a(String... strArr) {
        return Boolean.valueOf(FollowSearchResultActivity.a(this.f11690c).m12141g(this.f11688a));
    }

    /* renamed from: a */
    protected void m12483a(Boolean bool) {
        if (FollowSearchResultActivity.c(this.f11690c) != null && this.f11689b < FollowSearchResultActivity.c(this.f11690c).size() && bool.booleanValue()) {
            Toasts.show(this.f11690c, (int) C1373R.string.lable_unfollow_success_msg);
            FriendDTO friendDTO = (FriendDTO) FollowSearchResultActivity.c(this.f11690c).get(this.f11689b);
            int status = friendDTO.getStatus() - 2;
            if (status < 0) {
                status = 0;
            }
            friendDTO.setStatus(status);
            FollowSearchResultActivity.d(this.f11690c).m13151a(this.f11689b);
        }
    }
}
