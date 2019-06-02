package com.beastbikes.android.modules.cycling.club.ui;

class ClubFeedInfoFrag$3 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ String f9640a;
    /* renamed from: b */
    final /* synthetic */ ClubFeedInfoFrag f9641b;

    ClubFeedInfoFrag$3(ClubFeedInfoFrag clubFeedInfoFrag, String str) {
        this.f9641b = clubFeedInfoFrag;
        this.f9640a = str;
    }

    public void run() {
        ClubFeedInfoFrag.a(this.f9641b, this.f9640a, false, null);
    }
}
