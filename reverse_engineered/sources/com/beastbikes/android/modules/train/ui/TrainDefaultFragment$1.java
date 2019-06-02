package com.beastbikes.android.modules.train.ui;

import com.beastbikes.android.modules.train.dto.TrainResultDTO;
import rx.p208a.C5694b;

class TrainDefaultFragment$1 implements C5694b<TrainResultDTO> {
    /* renamed from: a */
    final /* synthetic */ TrainDefaultFragment f11260a;

    TrainDefaultFragment$1(TrainDefaultFragment trainDefaultFragment) {
        this.f11260a = trainDefaultFragment;
    }

    public /* synthetic */ void call(Object obj) {
        m12074a((TrainResultDTO) obj);
    }

    /* renamed from: a */
    public void m12074a(TrainResultDTO trainResultDTO) {
        if (trainResultDTO != null) {
            this.f11260a.getActivity().finish();
        }
    }
}
