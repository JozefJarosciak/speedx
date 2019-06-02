package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.business.BusinessException;

class ClubMoreActivity$6 extends AsyncTask<Void, Void, ClubInfoCompact> {
    /* renamed from: a */
    final /* synthetic */ String f9765a;
    /* renamed from: b */
    final /* synthetic */ ClubMoreActivity f9766b;

    ClubMoreActivity$6(ClubMoreActivity clubMoreActivity, String str) {
        this.f9766b = clubMoreActivity;
        this.f9765a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10854a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10855a((ClubInfoCompact) obj);
    }

    /* renamed from: a */
    protected ClubInfoCompact m10854a(Void... voidArr) {
        ClubInfoCompact clubInfoCompact = null;
        if (ClubMoreActivity.a(this.f9766b) != null) {
            try {
                clubInfoCompact = ClubMoreActivity.a(this.f9766b).m10533a(this.f9765a);
            } catch (BusinessException e) {
            }
        }
        return clubInfoCompact;
    }

    /* renamed from: a */
    protected void m10855a(ClubInfoCompact clubInfoCompact) {
        if (clubInfoCompact != null) {
            ClubMoreActivity.a(this.f9766b, clubInfoCompact.getName());
            if (!TextUtils.isEmpty(ClubMoreActivity.b(this.f9766b))) {
                ClubMoreActivity.b(this.f9766b, this.f9766b.getString(C1373R.string.club) + ClubMoreActivity.b(this.f9766b) + this.f9766b.getString(C1373R.string.recruit));
            }
            ClubMoreActivity.a(this.f9766b, clubInfoCompact.getLevel());
            if (ClubMoreActivity.c(this.f9766b) != 128) {
                ClubMoreActivity.d(this.f9766b).setVisibility(8);
                ClubMoreActivity.e(this.f9766b).setVisibility(8);
                ClubMoreActivity.f(this.f9766b).setVisibility(8);
                ClubMoreActivity.g(this.f9766b).setVisibility(8);
                ClubMoreActivity.h(this.f9766b).setVisibility(8);
                ClubMoreActivity.i(this.f9766b).setVisibility(8);
                ClubMoreActivity.j(this.f9766b).setVisibility(8);
                return;
            }
            ClubMoreActivity.k(this.f9766b).setVisibility(0);
            ClubMoreActivity.j(this.f9766b).setVisibility(0);
        }
    }
}
