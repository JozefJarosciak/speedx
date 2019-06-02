package com.beastbikes.android.authentication.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;

class AuthenticationActivity$4 extends AsyncTask<String, Void, Integer> {
    /* renamed from: a */
    final /* synthetic */ AuthenticationActivity f7279a;

    AuthenticationActivity$4(AuthenticationActivity authenticationActivity) {
        this.f7279a = authenticationActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m8523a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m8524a((Integer) obj);
    }

    /* renamed from: a */
    protected Integer m8523a(String... strArr) {
        return Integer.valueOf(new ClubManager(this.f7279a).m10531a(strArr[0], "regPhone"));
    }

    /* renamed from: a */
    protected void m8524a(Integer num) {
        if (num.intValue() != -1) {
            AuthenticationActivity.a(this.f7279a, num.intValue());
            return;
        }
        AuthenticationActivity.p(this.f7279a).setClickable(true);
        AuthenticationActivity.p(this.f7279a).setText(this.f7279a.getResources().getString(C1373R.string.get_verification_code));
    }
}
