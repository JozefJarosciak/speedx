package com.beastbikes.android.ble.ui;

import android.os.AsyncTask;

class SpeedForceActivity$14 extends AsyncTask<String, Void, Void> {
    /* renamed from: a */
    final /* synthetic */ String f7678a;
    /* renamed from: b */
    final /* synthetic */ float f7679b;
    /* renamed from: c */
    final /* synthetic */ SpeedForceActivity f7680c;

    SpeedForceActivity$14(SpeedForceActivity speedForceActivity, String str, float f) {
        this.f7680c = speedForceActivity;
        this.f7678a = str;
        this.f7679b = f;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m9176a((String[]) objArr);
    }

    /* renamed from: a */
    protected Void m9176a(String... strArr) {
        SpeedForceActivity.c(this.f7680c).m8887a(this.f7678a, this.f7679b);
        return null;
    }
}
