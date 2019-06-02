package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubTransferActivity$2 extends AsyncTask<Void, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubTransferActivity f9806a;
    /* renamed from: b */
    final /* synthetic */ ClubTransferActivity f9807b;

    ClubTransferActivity$2(ClubTransferActivity clubTransferActivity, ClubTransferActivity clubTransferActivity2) {
        this.f9807b = clubTransferActivity;
        this.f9806a = clubTransferActivity2;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10880a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10881a((Boolean) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        ClubTransferActivity.a(this.f9807b, new C1802i(this.f9806a, this.f9807b.getString(C1373R.string.club_info_waiting), true));
        ClubTransferActivity.a(this.f9807b).show();
    }

    /* renamed from: a */
    protected Boolean m10880a(Void... voidArr) {
        try {
            return Boolean.valueOf(ClubTransferActivity.b(this.f9807b).m10554g());
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10881a(Boolean bool) {
        if (ClubTransferActivity.a(this.f9807b) != null && ClubTransferActivity.a(this.f9807b).isShowing()) {
            ClubTransferActivity.a(this.f9807b).dismiss();
        }
        if (bool.booleanValue()) {
            Toasts.show(this.f9806a, (int) C1373R.string.club_transfer_activity_tip_transfer_ok);
        }
    }
}
