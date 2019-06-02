package com.beastbikes.android.modules.social.im.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1800h;
import com.beastbikes.android.modules.social.im.dto.FriendDTO;
import com.beastbikes.android.modules.social.im.ui.FriendsSearchResultActivity.C1436b;
import com.beastbikes.android.utils.C2580w;

class FriendsSearchResultActivity$b$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ FriendDTO f11166a;
    /* renamed from: b */
    final /* synthetic */ C1436b f11167b;

    FriendsSearchResultActivity$b$1(C1436b c1436b, FriendDTO friendDTO) {
        this.f11167b = c1436b;
        this.f11166a = friendDTO;
    }

    public void onClick(View view) {
        FriendsSearchResultActivity.a(this.f11167b.f6142a, this.f11166a);
        if (this.f11166a.getStatus() == 0) {
            FriendsSearchResultActivity.a(this.f11167b.f6142a, new C1800h(this.f11167b.f6142a, null, this.f11167b.f6142a.getString(C1373R.string.friends_add_friend_hint), this.f11167b.f6142a, 20, true, false));
            FriendsSearchResultActivity.j(this.f11167b.f6142a).show();
        }
        if (this.f11166a.getStatus() == 1) {
            FriendsSearchResultActivity.a(this.f11167b.f6142a, this.f11166a.getRequestId());
            C2580w.m12905a(this.f11167b.f6142a, "同意好友申请", null);
        }
    }
}
