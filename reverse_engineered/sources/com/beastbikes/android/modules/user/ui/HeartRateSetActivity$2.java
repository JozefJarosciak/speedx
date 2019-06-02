package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.train.dto.TrainTargetDto;
import com.beastbikes.android.modules.train.p076a.C2351d;
import com.beastbikes.framework.ui.android.utils.Toasts;

class HeartRateSetActivity$2 extends AsyncTask<Void, Void, TrainTargetDto> {
    /* renamed from: a */
    final /* synthetic */ C2351d f11720a;
    /* renamed from: b */
    final /* synthetic */ HeartRateSetActivity f11721b;

    HeartRateSetActivity$2(HeartRateSetActivity heartRateSetActivity, C2351d c2351d) {
        this.f11721b = heartRateSetActivity;
        this.f11720a = c2351d;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12498a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12499a((TrainTargetDto) obj);
    }

    /* renamed from: a */
    protected TrainTargetDto m12498a(Void... voidArr) {
        return this.f11720a.m12011a(HeartRateSetActivity.c(this.f11721b));
    }

    /* renamed from: a */
    protected void m12499a(TrainTargetDto trainTargetDto) {
        if (trainTargetDto == null) {
            Toasts.show(this.f11721b, (int) C1373R.string.setting_fragment_item_upload_error_log_failed);
        } else {
            this.f11721b.finish();
        }
    }
}
