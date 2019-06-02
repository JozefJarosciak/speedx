package com.beastbikes.android.modules.cycling.club.ui;

import com.beastbikes.android.modules.cycling.club.ui.widget.richeditor.RichEditorRelativeLayout.C2071a;

class ClubActInfoEditorActivity$1 implements C2071a {
    /* renamed from: a */
    final /* synthetic */ ClubActInfoEditorActivity f9420a;

    ClubActInfoEditorActivity$1(ClubActInfoEditorActivity clubActInfoEditorActivity) {
        this.f9420a = clubActInfoEditorActivity;
    }

    /* renamed from: a */
    public void mo3369a(boolean z) {
        if (z) {
            ClubActInfoEditorActivity.a(this.f9420a).setVisibility(8);
            ClubActInfoEditorActivity.b(this.f9420a).setVisibility(0);
            return;
        }
        ClubActInfoEditorActivity.a(this.f9420a).setVisibility(0);
        ClubActInfoEditorActivity.b(this.f9420a).setVisibility(8);
    }
}
