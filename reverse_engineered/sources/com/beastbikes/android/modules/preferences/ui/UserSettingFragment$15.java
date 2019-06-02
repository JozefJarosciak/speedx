package com.beastbikes.android.modules.preferences.ui;

import android.os.AsyncTask;
import com.beastbikes.android.locale.googlemaputils.C1856b;
import com.beastbikes.android.utils.C2558g;

class UserSettingFragment$15 extends AsyncTask<String, Void, C1856b> {
    /* renamed from: a */
    final /* synthetic */ UserSettingFragment f10975a;

    UserSettingFragment$15(UserSettingFragment userSettingFragment) {
        this.f10975a = userSettingFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11837a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11838a((C1856b) obj);
    }

    /* renamed from: a */
    protected C1856b m11837a(String... strArr) {
        return UserSettingFragment.h(this.f10975a).m12143i(strArr[0]);
    }

    /* renamed from: a */
    protected void m11838a(C1856b c1856b) {
        if (c1856b != null) {
            UserSettingFragment.a(this.f10975a, c1856b.m9676c());
            UserSettingFragment.b(this.f10975a, c1856b.m9678d());
            UserSettingFragment.c(this.f10975a, c1856b.m9679e());
            UserSettingFragment.l(this.f10975a).setText(C2558g.m12843a(UserSettingFragment.c(this.f10975a), UserSettingFragment.d(this.f10975a), UserSettingFragment.e(this.f10975a)));
        }
    }
}
