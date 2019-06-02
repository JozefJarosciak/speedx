package com.beastbikes.android.modules.user.ui;

import android.text.TextUtils;
import com.beastbikes.android.dialog.Wheelview.C1703f;

class FeedBackActivity$1 implements C1703f {
    /* renamed from: a */
    final /* synthetic */ FeedBackActivity f11617a;

    FeedBackActivity$1(FeedBackActivity feedBackActivity) {
        this.f11617a = feedBackActivity;
    }

    /* renamed from: a */
    public void mo3218a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            FeedBackActivity.a(this.f11617a, str);
            this.f11617a.f6370d.setText(FeedBackActivity.a(this.f11617a));
        }
    }
}
