package com.beastbikes.android.modules.social.im.ui.conversation;

class LocationSelectActivity$3 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ LocationSelectActivity f11189a;

    LocationSelectActivity$3(LocationSelectActivity locationSelectActivity) {
        this.f11189a = locationSelectActivity;
    }

    public void run() {
        LocationSelectActivity.a(this.f11189a, true);
        LocationSelectActivity.a(this.f11189a).setVisibility(8);
        LocationSelectActivity.d(this.f11189a).clearAnimation();
    }
}
