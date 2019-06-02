package com.beastbikes.android.modules.cycling.club.ui;

import android.content.SharedPreferences.Editor;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class ClubFeedPostActivity$3 implements OnCheckedChangeListener {
    /* renamed from: a */
    final /* synthetic */ ClubFeedPostActivity f9659a;

    ClubFeedPostActivity$3(ClubFeedPostActivity clubFeedPostActivity) {
        this.f9659a = clubFeedPostActivity;
    }

    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        ClubFeedPostActivity.a(this.f9659a, z);
        Editor edit = ClubFeedPostActivity.a(this.f9659a).edit();
        edit.putBoolean("NEEDSYNC", z);
        edit.apply();
    }
}
