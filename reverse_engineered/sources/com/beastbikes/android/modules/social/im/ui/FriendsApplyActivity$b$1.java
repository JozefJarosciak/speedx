package com.beastbikes.android.modules.social.im.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.social.im.ui.FriendsApplyActivity.C1435b;

class FriendsApplyActivity$b$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ FriendDTO f11153a;
    /* renamed from: b */
    final /* synthetic */ int f11154b;
    /* renamed from: c */
    final /* synthetic */ C1435b f11155c;

    FriendsApplyActivity$b$1(C1435b c1435b, FriendDTO friendDTO, int i) {
        this.f11155c = c1435b;
        this.f11153a = friendDTO;
        this.f11154b = i;
    }

    public void onClick(View view) {
        FriendsApplyActivity.a(this.f11155c.f6127a, this.f11153a.getRequestId(), 0, this.f11154b);
    }
}
