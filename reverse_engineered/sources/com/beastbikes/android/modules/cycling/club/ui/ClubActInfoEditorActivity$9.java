package com.beastbikes.android.modules.cycling.club.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.beastbikes.android.C1373R;

class ClubActInfoEditorActivity$9 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ClubActInfoEditorActivity f9428a;

    ClubActInfoEditorActivity$9(ClubActInfoEditorActivity clubActInfoEditorActivity) {
        this.f9428a = clubActInfoEditorActivity;
    }

    public void onClick(View view) {
        if (ClubActInfoEditorActivity.j(this.f9428a)) {
            ((ImageButton) ClubActInfoEditorActivity.b(this.f9428a).findViewById(C1373R.id.action_italic)).setImageResource(C1373R.drawable.not_rich_italic);
        } else {
            ((ImageButton) ClubActInfoEditorActivity.b(this.f9428a).findViewById(C1373R.id.action_italic)).setImageResource(C1373R.drawable.rich_italic);
        }
        ClubActInfoEditorActivity.d(this.f9428a, !ClubActInfoEditorActivity.j(this.f9428a));
        ClubActInfoEditorActivity.f(this.f9428a).m11102c();
    }
}
