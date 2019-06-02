package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;

class ClubCreateActivity$8 extends AsyncTask<String, Void, Integer> {
    /* renamed from: a */
    final /* synthetic */ ClubCreateActivity f9559a;

    ClubCreateActivity$8(ClubCreateActivity clubCreateActivity) {
        this.f9559a = clubCreateActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10730a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10731a((Integer) obj);
    }

    /* renamed from: a */
    protected Integer m10730a(String... strArr) {
        return Integer.valueOf(ClubCreateActivity.g(this.f9559a).m10531a(strArr[0], "regClub"));
    }

    /* renamed from: a */
    protected void m10731a(Integer num) {
        if (num.intValue() != -1) {
            ClubCreateActivity.b(this.f9559a, num.intValue());
            return;
        }
        ClubCreateActivity.h(this.f9559a).setClickable(true);
        ClubCreateActivity.h(this.f9559a).setBackgroundResource(C1373R.drawable.bg_verificationcodebtn);
        ClubCreateActivity.h(this.f9559a).setText(this.f9559a.getResources().getString(C1373R.string.get_verification_code));
    }
}
