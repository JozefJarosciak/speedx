package com.beastbikes.android.modules.cycling.club.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.beastbikes.android.C1373R;

class ClubActInfoEditorActivity$7 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ClubActInfoEditorActivity f9426a;

    ClubActInfoEditorActivity$7(ClubActInfoEditorActivity clubActInfoEditorActivity) {
        this.f9426a = clubActInfoEditorActivity;
    }

    public void onClick(View view) {
        if (ClubActInfoEditorActivity.h(this.f9426a)) {
            ((ImageButton) ClubActInfoEditorActivity.b(this.f9426a).findViewById(C1373R.id.action_bold)).setImageResource(C1373R.drawable.not_rich_bold);
        } else {
            ((ImageButton) ClubActInfoEditorActivity.b(this.f9426a).findViewById(C1373R.id.action_bold)).setImageResource(C1373R.drawable.rich_bold);
        }
        ClubActInfoEditorActivity.b(this.f9426a, !ClubActInfoEditorActivity.h(this.f9426a));
        ClubActInfoEditorActivity.f(this.f9426a).m11101b();
    }
}
