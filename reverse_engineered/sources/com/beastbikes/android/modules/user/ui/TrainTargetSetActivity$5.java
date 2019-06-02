package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.dialog.C1805k.C1804a;
import com.beastbikes.android.locale.C1849a;
import com.google.zxing.client.result.ExpandedProductParsedResult;

class TrainTargetSetActivity$5 implements C1804a {
    /* renamed from: a */
    final /* synthetic */ TrainTargetSetActivity f11782a;

    TrainTargetSetActivity$5(TrainTargetSetActivity trainTargetSetActivity) {
        this.f11782a = trainTargetSetActivity;
    }

    /* renamed from: a */
    public void mo3284a(int i, String str) {
        TrainTargetSetActivity.b(this.f11782a, (double) Integer.parseInt(str));
        if (!TrainTargetSetActivity.e(this.f11782a)) {
            TrainTargetSetActivity.b(this.f11782a, C1849a.m9654i(Double.parseDouble(str)));
        }
        TrainTargetSetActivity.h(this.f11782a).setText(str + (TrainTargetSetActivity.e(this.f11782a) ? ExpandedProductParsedResult.KILOGRAM : ExpandedProductParsedResult.POUND));
    }
}
