package com.beastbikes.android.modules.train.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dao.entity.LocalUser;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.framework.business.BusinessException;

class CreateTrainLongTimePlanActivity$1 extends AsyncTask<String, Void, LocalUser> {
    /* renamed from: a */
    final /* synthetic */ C2389c f11243a;
    /* renamed from: b */
    final /* synthetic */ CreateTrainLongTimePlanActivity f11244b;

    CreateTrainLongTimePlanActivity$1(CreateTrainLongTimePlanActivity createTrainLongTimePlanActivity, C2389c c2389c) {
        this.f11244b = createTrainLongTimePlanActivity;
        this.f11243a = c2389c;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12068a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12069a((LocalUser) obj);
    }

    /* renamed from: a */
    protected LocalUser m12068a(String... strArr) {
        try {
            return this.f11243a.m12118a(CreateTrainLongTimePlanActivity.a(this.f11244b));
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m12069a(LocalUser localUser) {
        if (localUser != null) {
        }
    }
}
