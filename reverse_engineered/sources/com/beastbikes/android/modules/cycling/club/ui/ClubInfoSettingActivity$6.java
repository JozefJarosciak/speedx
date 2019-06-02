package com.beastbikes.android.modules.cycling.club.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.provider.MediaStore.Images.Media;

class ClubInfoSettingActivity$6 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ClubInfoSettingActivity f9715a;

    ClubInfoSettingActivity$6(ClubInfoSettingActivity clubInfoSettingActivity) {
        this.f9715a = clubInfoSettingActivity;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        Intent intent = new Intent("android.intent.action.PICK", null);
        intent.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
        this.f9715a.startActivityForResult(intent, 2);
    }
}
