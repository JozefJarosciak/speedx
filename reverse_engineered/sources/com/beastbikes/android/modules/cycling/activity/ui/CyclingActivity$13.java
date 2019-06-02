package com.beastbikes.android.modules.cycling.activity.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.ui.android.utils.Toasts;

class CyclingActivity$13 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f8613a;
    /* renamed from: b */
    final /* synthetic */ CyclingActivity f8614b;

    CyclingActivity$13(CyclingActivity cyclingActivity, C2621c c2621c) {
        this.f8614b = cyclingActivity;
        this.f8613a = c2621c;
    }

    public void onClick(View view) {
        this.f8613a.m13069b();
        CyclingActivity.a(this.f8614b);
        Toasts.show(this.f8614b, (int) C1373R.string.activity_state_label_finish_message);
    }
}
