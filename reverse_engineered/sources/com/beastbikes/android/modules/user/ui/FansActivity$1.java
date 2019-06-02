package com.beastbikes.android.modules.user.ui;

import android.text.TextUtils;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import rx.p208a.C5694b;

class FansActivity$1 implements C5694b<Object> {
    /* renamed from: a */
    final /* synthetic */ FansActivity f11586a;

    FansActivity$1(FansActivity fansActivity) {
        this.f11586a = fansActivity;
    }

    public void call(Object obj) {
        if (obj instanceof ProfileFragment$a) {
            for (int i = 0; i < FansActivity.a(this.f11586a).size(); i++) {
                if (TextUtils.equals(((FriendDTO) FansActivity.a(this.f11586a).get(i)).getFriendId(), ((ProfileFragment$a) obj).f11766a)) {
                    ((FriendDTO) FansActivity.a(this.f11586a).get(i)).setRemarks(((ProfileFragment$a) obj).f11767b);
                    FansActivity.b(this.f11586a).m13151a(i);
                    return;
                }
            }
        }
    }
}
