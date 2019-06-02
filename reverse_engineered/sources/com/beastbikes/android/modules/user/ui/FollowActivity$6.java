package com.beastbikes.android.modules.user.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.widget.C2621c;

class FollowActivity$6 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ String f11630a;
    /* renamed from: b */
    final /* synthetic */ int f11631b;
    /* renamed from: c */
    final /* synthetic */ C2621c f11632c;
    /* renamed from: d */
    final /* synthetic */ FollowActivity f11633d;

    FollowActivity$6(FollowActivity followActivity, String str, int i, C2621c c2621c) {
        this.f11633d = followActivity;
        this.f11630a = str;
        this.f11631b = i;
        this.f11632c = c2621c;
    }

    public void onClick(View view) {
        FollowActivity.a(this.f11633d, this.f11630a, this.f11631b);
        this.f11632c.m13069b();
    }
}
