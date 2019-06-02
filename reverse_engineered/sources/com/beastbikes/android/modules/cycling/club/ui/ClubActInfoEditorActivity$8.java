package com.beastbikes.android.modules.cycling.club.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.beastbikes.android.C1373R;

class ClubActInfoEditorActivity$8 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ClubActInfoEditorActivity f9427a;

    ClubActInfoEditorActivity$8(ClubActInfoEditorActivity clubActInfoEditorActivity) {
        this.f9427a = clubActInfoEditorActivity;
    }

    public void onClick(View view) {
        if (ClubActInfoEditorActivity.i(this.f9427a)) {
            ((ImageButton) ClubActInfoEditorActivity.b(this.f9427a).findViewById(C1373R.id.action_underline)).setImageResource(C1373R.drawable.not_rich_underline);
        } else {
            ((ImageButton) ClubActInfoEditorActivity.b(this.f9427a).findViewById(C1373R.id.action_underline)).setImageResource(C1373R.drawable.rich_underline);
        }
        ClubActInfoEditorActivity.c(this.f9427a, !ClubActInfoEditorActivity.i(this.f9427a));
        ClubActInfoEditorActivity.f(this.f9427a).m11103d();
    }
}
