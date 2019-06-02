package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.business.BusinessException;

class ClubMoreActivity$13 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubMoreActivity f9751a;
    /* renamed from: b */
    final /* synthetic */ String f9752b;
    /* renamed from: c */
    final /* synthetic */ int f9753c;
    /* renamed from: d */
    final /* synthetic */ ClubMoreActivity f9754d;

    ClubMoreActivity$13(ClubMoreActivity clubMoreActivity, ClubMoreActivity clubMoreActivity2, String str, int i) {
        this.f9754d = clubMoreActivity;
        this.f9751a = clubMoreActivity2;
        this.f9752b = str;
        this.f9753c = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10848a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10849a((Boolean) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        ClubMoreActivity.a(this.f9754d, new C1802i(this.f9751a, this.f9754d.getString(C1373R.string.club_info_waiting), true));
        ClubMoreActivity.o(this.f9754d).show();
    }

    /* renamed from: a */
    protected Boolean m10848a(Void... voidArr) {
        try {
            return Boolean.valueOf(ClubMoreActivity.a(this.f9754d).m10549c(this.f9752b, this.f9753c));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10849a(Boolean bool) {
        if (ClubMoreActivity.o(this.f9754d) != null && ClubMoreActivity.o(this.f9754d).isShowing()) {
            ClubMoreActivity.o(this.f9754d).dismiss();
        }
    }
}
