package com.beastbikes.android.modules.preferences.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.provider.MediaStore.Images.Media;

class UserSettingFragment$14 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ UserSettingFragment f10974a;

    UserSettingFragment$14(UserSettingFragment userSettingFragment) {
        this.f10974a = userSettingFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        Intent intent = new Intent("android.intent.action.PICK", null);
        intent.setDataAndType(Media.EXTERNAL_CONTENT_URI, "image/*");
        this.f10974a.startActivityForResult(intent, 2);
        UserSettingFragment.a().info("select image from gallery");
    }
}
