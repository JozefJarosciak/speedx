package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubMoreActivity$5 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubMoreActivity f9763a;
    /* renamed from: b */
    final /* synthetic */ ClubMoreActivity f9764b;

    ClubMoreActivity$5(ClubMoreActivity clubMoreActivity, ClubMoreActivity clubMoreActivity2) {
        this.f9764b = clubMoreActivity;
        this.f9763a = clubMoreActivity2;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10852a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10853a((Boolean) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        ClubMoreActivity.a(this.f9764b, new C1802i(this.f9763a, this.f9764b.getString(C1373R.string.club_info_waiting), true));
        ClubMoreActivity.o(this.f9764b).show();
    }

    /* renamed from: a */
    protected Boolean m10852a(Void... voidArr) {
        try {
            return Boolean.valueOf(ClubMoreActivity.a(this.f9764b).m10555h());
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10853a(Boolean bool) {
        if (ClubMoreActivity.o(this.f9764b) != null && ClubMoreActivity.o(this.f9764b).isShowing()) {
            ClubMoreActivity.o(this.f9764b).dismiss();
        }
        if (bool.booleanValue()) {
            Toasts.show(this.f9763a, (int) C1373R.string.club_transfer_activity_cancel_transfer_ok);
        } else {
            Toasts.show(this.f9763a, (int) C1373R.string.club_transfer_activity_cancel_transfer_fail);
        }
    }
}
