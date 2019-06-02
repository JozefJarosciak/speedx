package com.beastbikes.android.modules.train.ui;

import com.beastbikes.android.modules.train.dto.TrainResultDTO;
import rx.p208a.C5694b;

class SingleTrainListActivity$1 implements C5694b<TrainResultDTO> {
    /* renamed from: a */
    final /* synthetic */ SingleTrainListActivity f11255a;

    SingleTrainListActivity$1(SingleTrainListActivity singleTrainListActivity) {
        this.f11255a = singleTrainListActivity;
    }

    public /* synthetic */ void call(Object obj) {
        m12073a((TrainResultDTO) obj);
    }

    /* renamed from: a */
    public void m12073a(TrainResultDTO trainResultDTO) {
        if (trainResultDTO != null) {
            this.f11255a.finish();
        }
    }
}
