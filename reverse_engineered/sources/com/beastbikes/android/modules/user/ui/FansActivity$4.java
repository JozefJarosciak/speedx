package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.framework.ui.android.utils.Toasts;

class FansActivity$4 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f11591a;
    /* renamed from: b */
    final /* synthetic */ int f11592b;
    /* renamed from: c */
    final /* synthetic */ FansActivity f11593c;

    FansActivity$4(FansActivity fansActivity, String str, int i) {
        this.f11593c = fansActivity;
        this.f11591a = str;
        this.f11592b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12436a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12437a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m12436a(String... strArr) {
        return Boolean.valueOf(FansActivity.e(this.f11593c).m12141g(this.f11591a));
    }

    /* renamed from: a */
    protected void m12437a(Boolean bool) {
        if (FansActivity.a(this.f11593c) != null && this.f11592b < FansActivity.a(this.f11593c).size() && bool.booleanValue()) {
            Toasts.show(this.f11593c, (int) C1373R.string.lable_unfollow_success_msg);
            FriendDTO friendDTO = (FriendDTO) FansActivity.a(this.f11593c).get(this.f11592b);
            int status = friendDTO.getStatus() - 2;
            if (status < 0) {
                status = 0;
            }
            friendDTO.setStatus(status);
            FansActivity.b(this.f11593c).m13151a(this.f11592b);
        }
    }
}
