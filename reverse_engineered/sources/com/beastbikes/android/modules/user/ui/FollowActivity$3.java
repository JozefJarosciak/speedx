package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.framework.ui.android.utils.Toasts;

class FollowActivity$3 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f11622a;
    /* renamed from: b */
    final /* synthetic */ int f11623b;
    /* renamed from: c */
    final /* synthetic */ FollowActivity f11624c;

    FollowActivity$3(FollowActivity followActivity, String str, int i) {
        this.f11624c = followActivity;
        this.f11622a = str;
        this.f11623b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12452a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12453a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m12452a(String... strArr) {
        return Boolean.valueOf(FollowActivity.c(this.f11624c).m12140f(this.f11622a));
    }

    /* renamed from: a */
    protected void m12453a(Boolean bool) {
        if (FollowActivity.f(this.f11624c) != null && this.f11623b < FollowActivity.f(this.f11624c).size() && bool.booleanValue()) {
            Toasts.show(this.f11624c, (int) C1373R.string.lable_follow_success_msg);
            FriendDTO friendDTO = (FriendDTO) FollowActivity.f(this.f11624c).get(this.f11623b);
            int status = friendDTO.getStatus() + 2;
            if (status > 4) {
                status = 3;
            }
            friendDTO.setStatus(status);
            FollowActivity.d(this.f11624c).m13151a(this.f11623b);
        }
    }
}
