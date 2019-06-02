package com.beastbikes.android.main.adv;

import android.graphics.Bitmap;
import android.view.View;
import android.view.View.OnClickListener;

class AdvertiseActivity$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ Bitmap f8384a;
    /* renamed from: b */
    final /* synthetic */ AdvertiseActivity f8385b;

    AdvertiseActivity$1(AdvertiseActivity advertiseActivity, Bitmap bitmap) {
        this.f8385b = advertiseActivity;
        this.f8384a = bitmap;
    }

    public void onClick(View view) {
        AdvertiseActivity.a(this.f8385b);
        if (AdvertiseActivity.b(this.f8385b) != null) {
            AdvertiseActivity.b(this.f8385b).setImageBitmap(null);
            AdvertiseActivity.a(this.f8385b, null);
        }
        if (!this.f8384a.isRecycled()) {
            this.f8384a.recycle();
        }
        AdvertiseActivity.a(this.f8385b, 111, null);
        this.f8385b.finish();
    }
}
