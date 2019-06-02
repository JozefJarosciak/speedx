package com.beastbikes.android.modules.cycling.club.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class ClubInfoSettingActivity$5 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ClubInfoSettingActivity f9714a;

    ClubInfoSettingActivity$5(ClubInfoSettingActivity clubInfoSettingActivity) {
        this.f9714a = clubInfoSettingActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", Uri.fromFile(ClubInfoSettingActivity.i(this.f9714a)));
        this.f9714a.startActivityForResult(intent, 3);
    }
}
