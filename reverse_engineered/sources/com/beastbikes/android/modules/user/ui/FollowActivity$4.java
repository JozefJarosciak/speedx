package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.framework.ui.android.utils.Toasts;

class FollowActivity$4 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f11625a;
    /* renamed from: b */
    final /* synthetic */ int f11626b;
    /* renamed from: c */
    final /* synthetic */ FollowActivity f11627c;

    FollowActivity$4(FollowActivity followActivity, String str, int i) {
        this.f11627c = followActivity;
        this.f11625a = str;
        this.f11626b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12454a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12455a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m12454a(String... strArr) {
        return Boolean.valueOf(FollowActivity.c(this.f11627c).m12141g(this.f11625a));
    }

    /* renamed from: a */
    protected void m12455a(Boolean bool) {
        if (FollowActivity.f(this.f11627c) != null && this.f11626b < FollowActivity.f(this.f11627c).size() && bool.booleanValue()) {
            Toasts.show(this.f11627c, (int) C1373R.string.lable_unfollow_success_msg);
            FriendDTO friendDTO = (FriendDTO) FollowActivity.f(this.f11627c).get(this.f11626b);
            int status = friendDTO.getStatus() - 2;
            if (status < 0) {
                status = 0;
            }
            friendDTO.setStatus(status);
            FollowActivity.d(this.f11627c).m13151a(this.f11626b);
        }
    }
}
