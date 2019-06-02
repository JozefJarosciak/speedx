package com.beastbikes.android.home;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.update.p162b.C2548a;
import com.beastbikes.android.widget.C2621c;

class HomeActivity$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f8225a;
    /* renamed from: b */
    final /* synthetic */ C2548a f8226b;
    /* renamed from: c */
    final /* synthetic */ HomeActivity f8227c;

    HomeActivity$2(HomeActivity homeActivity, C2621c c2621c, C2548a c2548a) {
        this.f8227c = homeActivity;
        this.f8225a = c2621c;
        this.f8226b = c2548a;
    }

    public void onClick(View view) {
        this.f8225a.m13069b();
        HomeActivity.c(this.f8227c).edit().putInt("beast.version.update", this.f8226b.m12746c()).apply();
    }
}
