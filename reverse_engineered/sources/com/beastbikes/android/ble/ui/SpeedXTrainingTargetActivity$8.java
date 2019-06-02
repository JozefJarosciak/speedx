package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.locale.C1849a;
import com.beastbikes.android.modules.user.dao.entity.LocalUser;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.framework.business.BusinessException;

class SpeedXTrainingTargetActivity$8 extends AsyncTask<String, Void, LocalUser> {
    /* renamed from: a */
    final /* synthetic */ C2389c f7834a;
    /* renamed from: b */
    final /* synthetic */ AVUser f7835b;
    /* renamed from: c */
    final /* synthetic */ SpeedXTrainingTargetActivity f7836c;

    SpeedXTrainingTargetActivity$8(SpeedXTrainingTargetActivity speedXTrainingTargetActivity, C2389c c2389c, AVUser aVUser) {
        this.f7836c = speedXTrainingTargetActivity;
        this.f7834a = c2389c;
        this.f7835b = aVUser;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9255a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9256a((LocalUser) obj);
    }

    /* renamed from: a */
    protected LocalUser m9255a(String... strArr) {
        try {
            return this.f7834a.m12118a(this.f7835b.getObjectId());
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m9256a(LocalUser localUser) {
        if (localUser == null) {
            SpeedXTrainingTargetActivity.a().error("LocalUser is null");
            return;
        }
        SpeedXTrainingTargetActivity.a(this.f7836c, (int) localUser.getHeight());
        SpeedXTrainingTargetActivity.b(this.f7836c, (int) localUser.getWeight());
        SpeedXTrainingTargetActivity.c(this.f7836c, localUser.getGender());
        SpeedXTrainingTargetActivity.e(this.f7836c).setText(SpeedXTrainingTargetActivity.d(this.f7836c) == 0 ? C1373R.string.str_gender_female : C1373R.string.str_gender_male);
        if (SpeedXTrainingTargetActivity.f(this.f7836c)) {
            SpeedXTrainingTargetActivity.a(this.f7836c).setText(SpeedXTrainingTargetActivity.g(this.f7836c) + "cm");
            SpeedXTrainingTargetActivity.c(this.f7836c).setText(SpeedXTrainingTargetActivity.b(this.f7836c) + "kg");
        } else {
            SpeedXTrainingTargetActivity.a(this.f7836c).setText(C1849a.m9652g((double) SpeedXTrainingTargetActivity.g(this.f7836c)) + "'" + C1849a.m9651f((double) SpeedXTrainingTargetActivity.g(this.f7836c)) + "\"");
            SpeedXTrainingTargetActivity.c(this.f7836c).setText(((int) Math.round(C1849a.m9653h((double) SpeedXTrainingTargetActivity.b(this.f7836c)))) + "lb");
        }
        SpeedXTrainingTargetActivity.a(this.f7836c, localUser.getBirthday());
        SpeedXTrainingTargetActivity.i(this.f7836c).setText(SpeedXTrainingTargetActivity.h(this.f7836c));
    }
}
