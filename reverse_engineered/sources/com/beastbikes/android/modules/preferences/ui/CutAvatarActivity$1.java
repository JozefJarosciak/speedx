package com.beastbikes.android.modules.preferences.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class CutAvatarActivity$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ CutAvatarActivity f10925a;

    CutAvatarActivity$1(CutAvatarActivity cutAvatarActivity) {
        this.f10925a = cutAvatarActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.f10925a.finish();
    }
}
