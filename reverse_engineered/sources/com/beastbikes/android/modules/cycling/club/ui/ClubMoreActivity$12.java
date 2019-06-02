package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.cycling.club.biz.ClubManager;
import com.beastbikes.framework.business.BusinessException;

class ClubMoreActivity$12 extends AsyncTask<Void, Void, String> {
    /* renamed from: a */
    final /* synthetic */ ClubMoreActivity f9748a;
    /* renamed from: b */
    final /* synthetic */ int f9749b;
    /* renamed from: c */
    final /* synthetic */ ClubMoreActivity f9750c;

    ClubMoreActivity$12(ClubMoreActivity clubMoreActivity, ClubMoreActivity clubMoreActivity2, int i) {
        this.f9750c = clubMoreActivity;
        this.f9748a = clubMoreActivity2;
        this.f9749b = i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10846a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10847a((String) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        ClubMoreActivity.a(this.f9750c, new C1802i(this.f9748a, this.f9750c.getString(C1373R.string.club_info_waiting), true));
        ClubMoreActivity.o(this.f9750c).show();
    }

    /* renamed from: a */
    protected String m10846a(Void... voidArr) {
        try {
            if (ClubMoreActivity.a(this.f9750c) == null) {
                ClubMoreActivity.a(this.f9750c, new ClubManager(this.f9750c));
            }
            return ClubMoreActivity.a(this.f9750c).m10556i();
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10847a(String str) {
        if (ClubMoreActivity.o(this.f9750c) != null && ClubMoreActivity.o(this.f9750c).isShowing()) {
            ClubMoreActivity.o(this.f9750c).dismiss();
        }
        if (TextUtils.isEmpty(str)) {
            Intent intent = new Intent(this.f9750c, ClubMemberManagerActivity.class);
            intent.putExtra("club_select_mode", true);
            intent.putExtra("club_id", ClubMoreActivity.q(this.f9750c).getObjectId());
            intent.putExtra("club_info", ClubMoreActivity.q(this.f9750c));
            intent.putExtra("is_quit", this.f9749b);
            this.f9750c.startActivityForResult(intent, 1009);
            return;
        }
        ClubMoreActivity.c(this.f9750c, str);
    }
}
