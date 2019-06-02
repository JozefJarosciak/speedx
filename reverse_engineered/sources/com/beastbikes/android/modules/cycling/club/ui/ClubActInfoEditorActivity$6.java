package com.beastbikes.android.modules.cycling.club.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;

class ClubActInfoEditorActivity$6 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ClubActInfoEditorActivity f9425a;

    ClubActInfoEditorActivity$6(ClubActInfoEditorActivity clubActInfoEditorActivity) {
        this.f9425a = clubActInfoEditorActivity;
    }

    public void onClick(View view) {
        int[] iArr = new int[2];
        ClubActInfoEditorActivity.b(this.f9425a).findViewById(C1373R.id.action_textcolor).getLocationOnScreen(iArr);
        ClubActInfoEditorActivity.g(this.f9425a).showAsDropDown(ClubActInfoEditorActivity.b(this.f9425a), iArr[0] - (ClubActInfoEditorActivity.b(this.f9425a).getWidth() / 5), 0);
    }
}
