package com.beastbikes.android.home;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.update.p162b.C2548a;
import com.beastbikes.android.widget.C2621c;

class HomeActivity$3 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2548a f8228a;
    /* renamed from: b */
    final /* synthetic */ C2621c f8229b;
    /* renamed from: c */
    final /* synthetic */ HomeActivity f8230c;

    HomeActivity$3(HomeActivity homeActivity, C2548a c2548a, C2621c c2621c) {
        this.f8230c = homeActivity;
        this.f8228a = c2548a;
        this.f8229b = c2621c;
    }

    public void onClick(View view) {
        if (this.f8228a.m12747d() == 0) {
            this.f8229b.m13069b();
            HomeActivity.c(this.f8230c).edit().putInt("beast.version.update", this.f8228a.m12746c()).apply();
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(Uri.parse(this.f8228a.m12748e()));
        this.f8230c.startActivity(intent);
    }
}
