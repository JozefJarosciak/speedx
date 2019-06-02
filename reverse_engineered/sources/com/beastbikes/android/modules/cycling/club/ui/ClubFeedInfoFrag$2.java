package com.beastbikes.android.modules.cycling.club.ui;

import java.util.List;

class ClubFeedInfoFrag$2 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ List f9638a;
    /* renamed from: b */
    final /* synthetic */ ClubFeedInfoFrag f9639b;

    ClubFeedInfoFrag$2(ClubFeedInfoFrag clubFeedInfoFrag, List list) {
        this.f9639b = clubFeedInfoFrag;
        this.f9638a = list;
    }

    public void run() {
        if (ClubFeedInfoFrag.g(this.f9639b) != null) {
            ClubFeedInfoFrag.g(this.f9639b).m10945a(this.f9638a);
            ClubFeedInfoFrag.g(this.f9639b).m10939a();
        }
    }
}
