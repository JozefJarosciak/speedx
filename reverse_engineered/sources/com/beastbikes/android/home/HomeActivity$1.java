package com.beastbikes.android.home;

import com.beastbikes.android.modules.p060a.p108c.C1870a;

class HomeActivity$1 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ HomeActivity f8224a;

    HomeActivity$1(HomeActivity homeActivity) {
        this.f8224a = homeActivity;
    }

    public void run() {
        HomeActivity.b(this.f8224a).m9622b(new C1870a(this.f8224a, (double) HomeActivity.a(this.f8224a).getWidth()));
    }
}
