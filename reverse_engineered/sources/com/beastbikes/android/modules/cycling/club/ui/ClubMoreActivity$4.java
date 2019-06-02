package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubMoreActivity$4 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubMoreActivity f9761a;
    /* renamed from: b */
    final /* synthetic */ ClubMoreActivity f9762b;

    ClubMoreActivity$4(ClubMoreActivity clubMoreActivity, ClubMoreActivity clubMoreActivity2) {
        this.f9762b = clubMoreActivity;
        this.f9761a = clubMoreActivity2;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10850a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10851a((Boolean) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        ClubMoreActivity.a(this.f9762b, new C1802i(this.f9761a, this.f9762b.getString(C1373R.string.club_info_waiting), true));
        ClubMoreActivity.o(this.f9762b).show();
    }

    /* renamed from: a */
    protected Boolean m10850a(Void... voidArr) {
        try {
            return Boolean.valueOf(ClubMoreActivity.a(this.f9762b).m10554g());
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10851a(Boolean bool) {
        if (ClubMoreActivity.o(this.f9762b) != null && ClubMoreActivity.o(this.f9762b).isShowing()) {
            ClubMoreActivity.o(this.f9762b).dismiss();
        }
        if (bool.booleanValue()) {
            Toasts.show(this.f9761a, (int) C1373R.string.club_transfer_activity_tip_transfer_ok);
        }
    }
}
