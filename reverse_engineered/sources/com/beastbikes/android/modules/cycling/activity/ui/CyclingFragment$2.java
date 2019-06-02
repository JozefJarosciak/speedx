package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.widget.C2621c;

class CyclingFragment$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f8654a;
    /* renamed from: b */
    final /* synthetic */ CyclingFragment f8655b;

    CyclingFragment$2(CyclingFragment cyclingFragment, C2621c c2621c) {
        this.f8655b = cyclingFragment;
        this.f8654a = c2621c;
    }

    public void onClick(View view) {
        this.f8654a.m13069b();
        this.f8655b.getActivity().startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }
}
