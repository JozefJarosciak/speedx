package com.beastbikes.android.modules.user.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.widget.C2621c;

class FollowSearchResultActivity$7 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ String f11693a;
    /* renamed from: b */
    final /* synthetic */ int f11694b;
    /* renamed from: c */
    final /* synthetic */ C2621c f11695c;
    /* renamed from: d */
    final /* synthetic */ FollowSearchResultActivity f11696d;

    FollowSearchResultActivity$7(FollowSearchResultActivity followSearchResultActivity, String str, int i, C2621c c2621c) {
        this.f11696d = followSearchResultActivity;
        this.f11693a = str;
        this.f11694b = i;
        this.f11695c = c2621c;
    }

    public void onClick(View view) {
        FollowSearchResultActivity.a(this.f11696d, this.f11693a, this.f11694b);
        this.f11695c.m13069b();
    }
}
