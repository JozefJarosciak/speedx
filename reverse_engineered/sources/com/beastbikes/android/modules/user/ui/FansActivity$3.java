package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.framework.ui.android.utils.Toasts;

class FansActivity$3 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f11588a;
    /* renamed from: b */
    final /* synthetic */ int f11589b;
    /* renamed from: c */
    final /* synthetic */ FansActivity f11590c;

    FansActivity$3(FansActivity fansActivity, String str, int i) {
        this.f11590c = fansActivity;
        this.f11588a = str;
        this.f11589b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12434a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12435a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m12434a(String... strArr) {
        return Boolean.valueOf(FansActivity.e(this.f11590c).m12140f(this.f11588a));
    }

    /* renamed from: a */
    protected void m12435a(Boolean bool) {
        if (FansActivity.a(this.f11590c) != null && this.f11589b < FansActivity.a(this.f11590c).size() && bool.booleanValue()) {
            Toasts.show(this.f11590c, (int) C1373R.string.lable_follow_success_msg);
            FriendDTO friendDTO = (FriendDTO) FansActivity.a(this.f11590c).get(this.f11589b);
            int status = friendDTO.getStatus() + 2;
            if (status > 4) {
                status = 3;
            }
            friendDTO.setStatus(status);
            FansActivity.b(this.f11590c).m13151a(this.f11589b);
        }
    }
}
