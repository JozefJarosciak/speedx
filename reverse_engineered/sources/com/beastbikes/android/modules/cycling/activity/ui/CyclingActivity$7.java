package com.beastbikes.android.modules.cycling.activity.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.train.p076a.C2350b;

class CyclingActivity$7 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ C2350b f8629a;
    /* renamed from: b */
    final /* synthetic */ int f8630b;
    /* renamed from: c */
    final /* synthetic */ CyclingActivity f8631c;

    CyclingActivity$7(CyclingActivity cyclingActivity, C2350b c2350b, int i) {
        this.f8631c = cyclingActivity;
        this.f8629a = c2350b;
        this.f8630b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9936a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m9937a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m9936a(Void... voidArr) {
        return Boolean.valueOf(this.f8629a.m12007d(this.f8630b));
    }

    /* renamed from: a */
    protected void m9937a(Boolean bool) {
        super.onPostExecute(bool);
    }
}
