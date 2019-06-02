package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.locale.googlemaputils.C1856b;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.android.utils.C2558g;

class ProfileSettingsActivity$1 extends AsyncTask<String, Void, C1856b> {
    /* renamed from: a */
    final /* synthetic */ C2389c f11769a;
    /* renamed from: b */
    final /* synthetic */ ProfileSettingsActivity f11770b;

    ProfileSettingsActivity$1(ProfileSettingsActivity profileSettingsActivity, C2389c c2389c) {
        this.f11770b = profileSettingsActivity;
        this.f11769a = c2389c;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12531a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12532a((C1856b) obj);
    }

    /* renamed from: a */
    protected C1856b m12531a(String... strArr) {
        return this.f11769a.m12143i(strArr[0]);
    }

    /* renamed from: a */
    protected void m12532a(C1856b c1856b) {
        if (c1856b != null) {
            ProfileSettingsActivity.a(this.f11770b).setText(C2558g.m12843a(c1856b.m9676c(), c1856b.m9678d(), c1856b.m9679e()));
        }
    }
}
