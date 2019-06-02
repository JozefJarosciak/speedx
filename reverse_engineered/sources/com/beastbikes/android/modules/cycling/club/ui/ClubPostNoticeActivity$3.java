package com.beastbikes.android.modules.cycling.club.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.widget.C2621c;

class ClubPostNoticeActivity$3 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f9787a;
    /* renamed from: b */
    final /* synthetic */ ClubPostNoticeActivity f9788b;

    ClubPostNoticeActivity$3(ClubPostNoticeActivity clubPostNoticeActivity, C2621c c2621c) {
        this.f9788b = clubPostNoticeActivity;
        this.f9787a = c2621c;
    }

    public void onClick(View view) {
        this.f9787a.m13069b();
        ClubPostNoticeActivity.f5260a = ClubPostNoticeActivity.a(this.f9788b).getText().toString();
        this.f9788b.finish();
    }
}
