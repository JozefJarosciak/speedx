package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubTransferActivity$1 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubTransferActivity f9804a;
    /* renamed from: b */
    final /* synthetic */ ClubTransferActivity f9805b;

    ClubTransferActivity$1(ClubTransferActivity clubTransferActivity, ClubTransferActivity clubTransferActivity2) {
        this.f9805b = clubTransferActivity;
        this.f9804a = clubTransferActivity2;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10878a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10879a((Boolean) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        ClubTransferActivity.a(this.f9805b, new C1802i(this.f9804a, this.f9805b.getString(C1373R.string.club_info_waiting), true));
        ClubTransferActivity.a(this.f9805b).show();
    }

    /* renamed from: a */
    protected Boolean m10878a(Void... voidArr) {
        try {
            return Boolean.valueOf(ClubTransferActivity.b(this.f9805b).m10555h());
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10879a(Boolean bool) {
        if (ClubTransferActivity.a(this.f9805b) != null && ClubTransferActivity.a(this.f9805b).isShowing()) {
            ClubTransferActivity.a(this.f9805b).dismiss();
        }
        if (bool.booleanValue()) {
            Toasts.show(this.f9804a, (int) C1373R.string.club_transfer_activity_cancel_transfer_ok);
            this.f9804a.finish();
            return;
        }
        Toasts.show(this.f9804a, (int) C1373R.string.club_transfer_activity_cancel_transfer_fail);
    }
}
