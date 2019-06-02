package com.beastbikes.android.modules.user.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.widget.C2621c;

class FansActivity$6 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ String f11596a;
    /* renamed from: b */
    final /* synthetic */ int f11597b;
    /* renamed from: c */
    final /* synthetic */ C2621c f11598c;
    /* renamed from: d */
    final /* synthetic */ FansActivity f11599d;

    FansActivity$6(FansActivity fansActivity, String str, int i, C2621c c2621c) {
        this.f11599d = fansActivity;
        this.f11596a = str;
        this.f11597b = i;
        this.f11598c = c2621c;
    }

    public void onClick(View view) {
        FansActivity.a(this.f11599d, this.f11596a, this.f11597b);
        this.f11598c.m13069b();
    }
}
