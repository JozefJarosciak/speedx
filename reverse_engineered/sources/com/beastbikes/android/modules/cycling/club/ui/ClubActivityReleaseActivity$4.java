package com.beastbikes.android.modules.cycling.club.ui;

import android.support.v4.media.TransportMediator;

class ClubActivityReleaseActivity$4 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ClubActivityReleaseActivity f9528a;

    ClubActivityReleaseActivity$4(ClubActivityReleaseActivity clubActivityReleaseActivity) {
        this.f9528a = clubActivityReleaseActivity;
    }

    public void run() {
        ClubActivityReleaseActivity.f(this.f9528a).fullScroll(TransportMediator.KEYCODE_MEDIA_RECORD);
    }
}
