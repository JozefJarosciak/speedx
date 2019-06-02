package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.dialog.C1802i;
import com.squareup.picasso.Callback;

class AvatarViewer$1 implements Callback {
    /* renamed from: a */
    final /* synthetic */ C1802i f11536a;
    /* renamed from: b */
    final /* synthetic */ AvatarViewer f11537b;

    AvatarViewer$1(AvatarViewer avatarViewer, C1802i c1802i) {
        this.f11537b = avatarViewer;
        this.f11536a = c1802i;
    }

    public void onSuccess() {
        if (this.f11536a != null && this.f11537b != null && !this.f11537b.isFinishing()) {
            this.f11536a.dismiss();
        }
    }

    public void onError() {
        if (this.f11536a != null && this.f11537b != null && !this.f11537b.isFinishing()) {
            this.f11536a.dismiss();
        }
    }
}
