package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.modules.user.dto.ProfileDTO;

class ProfileFragment$7 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ ProfileDTO f11759a;
    /* renamed from: b */
    final /* synthetic */ ProfileFragment f11760b;

    ProfileFragment$7(ProfileFragment profileFragment, ProfileDTO profileDTO) {
        this.f11760b = profileFragment;
        this.f11759a = profileDTO;
    }

    public void run() {
        ProfileFragment.b(this.f11760b, this.f11759a);
    }
}
