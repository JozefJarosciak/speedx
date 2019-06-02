package com.beastbikes.android.modules.cycling.club.ui;

import android.text.Editable;
import android.text.TextWatcher;

class ClubFeedPicUploadActivity$1 implements TextWatcher {
    /* renamed from: a */
    final /* synthetic */ ClubFeedPicUploadActivity f9654a;

    ClubFeedPicUploadActivity$1(ClubFeedPicUploadActivity clubFeedPicUploadActivity) {
        this.f9654a = clubFeedPicUploadActivity;
    }

    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        if (this.f9654a.i != null) {
            if (ClubFeedPicUploadActivity.a(this.f9654a) == null || ClubFeedPicUploadActivity.a(this.f9654a).isEmpty()) {
                this.f9654a.i.setEnabled(false);
            } else {
                this.f9654a.i.setEnabled(true);
            }
        }
    }

    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    public void afterTextChanged(Editable editable) {
    }
}
