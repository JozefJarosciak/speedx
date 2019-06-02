package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.framework.ui.android.utils.Toasts;

class FollowSearchResultActivity$4 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f11685a;
    /* renamed from: b */
    final /* synthetic */ int f11686b;
    /* renamed from: c */
    final /* synthetic */ FollowSearchResultActivity f11687c;

    FollowSearchResultActivity$4(FollowSearchResultActivity followSearchResultActivity, String str, int i) {
        this.f11687c = followSearchResultActivity;
        this.f11685a = str;
        this.f11686b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12480a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12481a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m12480a(String... strArr) {
        return Boolean.valueOf(FollowSearchResultActivity.a(this.f11687c).m12140f(this.f11685a));
    }

    /* renamed from: a */
    protected void m12481a(Boolean bool) {
        if (FollowSearchResultActivity.c(this.f11687c) != null && this.f11686b < FollowSearchResultActivity.c(this.f11687c).size() && bool.booleanValue()) {
            Toasts.show(this.f11687c, (int) C1373R.string.lable_follow_success_msg);
            FriendDTO friendDTO = (FriendDTO) FollowSearchResultActivity.c(this.f11687c).get(this.f11686b);
            int status = friendDTO.getStatus() + 2;
            if (status > 4) {
                status = 3;
            }
            friendDTO.setStatus(status);
            FollowSearchResultActivity.d(this.f11687c).m13151a(this.f11686b);
        }
    }
}
