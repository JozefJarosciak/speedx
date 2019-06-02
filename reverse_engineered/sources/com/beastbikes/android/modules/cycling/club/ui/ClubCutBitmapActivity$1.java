package com.beastbikes.android.modules.cycling.club.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class ClubCutBitmapActivity$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ClubCutBitmapActivity f9561a;

    ClubCutBitmapActivity$1(ClubCutBitmapActivity clubCutBitmapActivity) {
        this.f9561a = clubCutBitmapActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f9561a.finish();
    }
}
