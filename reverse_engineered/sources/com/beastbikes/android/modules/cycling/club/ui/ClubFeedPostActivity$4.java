package com.beastbikes.android.modules.cycling.club.ui;

import android.text.Editable;
import android.text.TextWatcher;
import com.beastbikes.android.utils.C2581x;

class ClubFeedPostActivity$4 implements TextWatcher {
    /* renamed from: a */
    final /* synthetic */ ClubFeedPostActivity f9660a;

    ClubFeedPostActivity$4(ClubFeedPostActivity clubFeedPostActivity) {
        this.f9660a = clubFeedPostActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (C2581x.m12906a(this.f9660a.f5068f.getText().toString().trim()) > 0 || this.f9660a.f5070h.size() > 0) {
            ClubFeedPostActivity.b(this.f9660a, true);
        } else {
            ClubFeedPostActivity.b(this.f9660a, false);
        }
        ClubFeedPostActivity.b(this.f9660a);
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }
}
