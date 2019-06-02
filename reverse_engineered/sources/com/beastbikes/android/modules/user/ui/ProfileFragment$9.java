package com.beastbikes.android.modules.user.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.widget.C2621c;

class ProfileFragment$9 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ String f11763a;
    /* renamed from: b */
    final /* synthetic */ C2621c f11764b;
    /* renamed from: c */
    final /* synthetic */ ProfileFragment f11765c;

    ProfileFragment$9(ProfileFragment profileFragment, String str, C2621c c2621c) {
        this.f11765c = profileFragment;
        this.f11763a = str;
        this.f11764b = c2621c;
    }

    public void onClick(View view) {
        ProfileFragment.b(this.f11765c, this.f11763a);
        this.f11764b.m13069b();
    }
}
