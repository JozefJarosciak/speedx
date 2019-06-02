package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;

class CyclingFragment$4 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ CyclingFragment f8658a;

    CyclingFragment$4(CyclingFragment cyclingFragment) {
        this.f8658a = cyclingFragment;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        if (!CyclingFragment.d(this.f8658a).contains("beast.ble.message.on") || CyclingFragment.d(this.f8658a).getInt("beast.ble.message.on", 0) == 1) {
            try {
                this.f8658a.startActivityForResult(new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"), 1);
            } catch (ActivityNotFoundException e) {
                CyclingFragment.a().error("跳转到系统设置通知使用权页面错误, " + e);
            }
        }
    }
}
