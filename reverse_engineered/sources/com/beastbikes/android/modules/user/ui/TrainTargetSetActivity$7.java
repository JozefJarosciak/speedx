package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.train.dto.TrainTargetDto;
import com.beastbikes.android.modules.train.p076a.C2351d;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.apache.commons.cli.HelpFormatter;

class TrainTargetSetActivity$7 extends AsyncTask<Void, Void, TrainTargetDto> {
    /* renamed from: a */
    final /* synthetic */ C1802i f11784a;
    /* renamed from: b */
    final /* synthetic */ C2351d f11785b;
    /* renamed from: c */
    final /* synthetic */ TrainTargetSetActivity f11786c;

    TrainTargetSetActivity$7(TrainTargetSetActivity trainTargetSetActivity, C1802i c1802i, C2351d c2351d) {
        this.f11786c = trainTargetSetActivity;
        this.f11784a = c1802i;
        this.f11785b = c2351d;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12544a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12545a((TrainTargetDto) obj);
    }

    protected void onPreExecute() {
        if (!this.f11784a.isShowing()) {
            this.f11784a.show();
        }
    }

    /* renamed from: a */
    protected TrainTargetDto m12544a(Void... voidArr) {
        return this.f11785b.m12010a(TrainTargetSetActivity.d(this.f11786c), TrainTargetSetActivity.b(this.f11786c), TrainTargetSetActivity.g(this.f11786c), TrainTargetSetActivity.n(this.f11786c), TrainTargetSetActivity.i(this.f11786c));
    }

    /* renamed from: a */
    protected void m12545a(TrainTargetDto trainTargetDto) {
        if (this.f11784a.isShowing()) {
            this.f11784a.dismiss();
        }
        if (trainTargetDto == null) {
            Toasts.show(this.f11786c, (int) C1373R.string.network_not_awesome);
            return;
        }
        AVUser currentUser = AVUser.getCurrentUser();
        if (TrainTargetSetActivity.r(this.f11786c) <= 0) {
            TrainTargetSetActivity.g(this.f11786c, 200);
        }
        currentUser.setFtp(TrainTargetSetActivity.r(this.f11786c));
        currentUser.setHeight(TrainTargetSetActivity.d(this.f11786c));
        currentUser.setWeight(TrainTargetSetActivity.g(this.f11786c));
        currentUser.setGender(TrainTargetSetActivity.b(this.f11786c));
        currentUser.setMaxHeartRate(TrainTargetSetActivity.n(this.f11786c));
        currentUser.setBirthDay(TrainTargetSetActivity.l(this.f11786c) + HelpFormatter.DEFAULT_OPT_PREFIX + TrainTargetSetActivity.k(this.f11786c) + HelpFormatter.DEFAULT_OPT_PREFIX + TrainTargetSetActivity.l(this.f11786c));
        AVUser.saveCurrentUser(currentUser);
        this.f11786c.finish();
    }
}
