package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubInfoSettingActivity$3 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f9707a;
    /* renamed from: b */
    final /* synthetic */ String f9708b;
    /* renamed from: c */
    final /* synthetic */ String f9709c;
    /* renamed from: d */
    final /* synthetic */ String f9710d;
    /* renamed from: e */
    final /* synthetic */ ClubInfoSettingActivity f9711e;

    ClubInfoSettingActivity$3(ClubInfoSettingActivity clubInfoSettingActivity, String str, String str2, String str3, String str4) {
        this.f9711e = clubInfoSettingActivity;
        this.f9707a = str;
        this.f9708b = str2;
        this.f9709c = str3;
        this.f9710d = str4;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10825a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10826a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m10825a(Void... voidArr) {
        try {
            return Boolean.valueOf(ClubInfoSettingActivity.c(this.f9711e).m10543a(this.f9707a, this.f9708b, null, null, this.f9709c, null, ClubInfoSettingActivity.b(this.f9711e), this.f9710d));
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10826a(Boolean bool) {
        if (bool.booleanValue()) {
            Toasts.show(this.f9711e.getApplicationContext(), (int) C1373R.string.club_info_setting_save_success);
            this.f9711e.finish();
        } else {
            Toasts.show(this.f9711e.getApplicationContext(), (int) C1373R.string.club_info_setting_save_error);
        }
        super.onPostExecute(bool);
    }
}
