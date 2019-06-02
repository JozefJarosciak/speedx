package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.Intent;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.utils.C2580w;

class CyclingStartAnimationActivity$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ CyclingStartAnimationActivity f8686a;

    CyclingStartAnimationActivity$1(CyclingStartAnimationActivity cyclingStartAnimationActivity) {
        this.f8686a = cyclingStartAnimationActivity;
    }

    public void run() {
        Intent intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.putExtra(C0861d.f2143o, "com.beastbikes.intent.action.ACTIVITY_START");
        if (CyclingStartAnimationActivity.a(this.f8686a) != null) {
            intent.putExtra("train_course", CyclingStartAnimationActivity.a(this.f8686a));
        }
        intent.setPackage(this.f8686a.getPackageName());
        this.f8686a.startService(intent);
        C2580w.m12905a(this.f8686a, this.f8686a.getString(C1373R.string.activity_fragment_event_click_start_riding), null);
        intent = new Intent(this.f8686a, CyclingActivity.class);
        intent.putExtra("cycling_type", CyclingStartAnimationActivity.b(this.f8686a));
        if (CyclingStartAnimationActivity.a(this.f8686a) != null) {
            intent.putExtra("course_info", CyclingStartAnimationActivity.a(this.f8686a));
        }
        this.f8686a.startActivity(intent);
        this.f8686a.finish();
    }
}
