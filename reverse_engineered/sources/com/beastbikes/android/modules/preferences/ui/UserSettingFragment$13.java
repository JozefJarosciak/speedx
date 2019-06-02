package com.beastbikes.android.modules.preferences.ui;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;

class UserSettingFragment$13 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ UserSettingFragment f10973a;

    UserSettingFragment$13(UserSettingFragment userSettingFragment) {
        this.f10973a = userSettingFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", Uri.fromFile(UserSettingFragment.k(this.f10973a)));
        this.f10973a.startActivityForResult(intent, 3);
        UserSettingFragment.a().info("select image from camera");
    }
}
