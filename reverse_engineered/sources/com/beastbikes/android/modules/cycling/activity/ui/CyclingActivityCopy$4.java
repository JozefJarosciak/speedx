package com.beastbikes.android.modules.cycling.activity.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.ui.android.utils.Toasts;

class CyclingActivityCopy$4 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f8643a;
    /* renamed from: b */
    final /* synthetic */ CyclingActivityCopy f8644b;

    CyclingActivityCopy$4(CyclingActivityCopy cyclingActivityCopy, C2621c c2621c) {
        this.f8644b = cyclingActivityCopy;
        this.f8643a = c2621c;
    }

    public void onClick(View view) {
        this.f8643a.m13069b();
        CyclingActivityCopy.a(this.f8644b);
        Toasts.show(this.f8644b, (int) C1373R.string.activity_state_label_finish_message);
    }
}
