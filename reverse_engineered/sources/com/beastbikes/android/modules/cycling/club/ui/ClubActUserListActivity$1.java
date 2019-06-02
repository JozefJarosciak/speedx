package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.club.dto.C2063b;
import java.util.Collection;

class ClubActUserListActivity$1 extends AsyncTask<Void, Void, C2063b> {
    /* renamed from: a */
    final /* synthetic */ ClubActUserListActivity f9441a;

    ClubActUserListActivity$1(ClubActUserListActivity clubActUserListActivity) {
        this.f9441a = clubActUserListActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10672a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10673a((C2063b) obj);
    }

    /* renamed from: a */
    protected C2063b m10672a(Void... voidArr) {
        if (TextUtils.isEmpty(ClubActUserListActivity.a(this.f9441a))) {
            return null;
        }
        return ClubActUserListActivity.d(this.f9441a).m10562b(ClubActUserListActivity.a(this.f9441a), ClubActUserListActivity.b(this.f9441a), ClubActUserListActivity.c(this.f9441a));
    }

    /* renamed from: a */
    protected void m10673a(C2063b c2063b) {
        Collection a = c2063b.m10625a();
        if (a == null || a.size() == 0) {
            ClubActUserListActivity.e(this.f9441a).setHasFooter(false);
            ClubActUserListActivity.e(this.f9441a).setCanLoadMore(false);
            ClubActUserListActivity.e(this.f9441a).m13150a();
        }
        if (ClubActUserListActivity.f(this.f9441a)) {
            ClubActUserListActivity.a(this.f9441a, false);
            ClubActUserListActivity.g(this.f9441a).clear();
        }
        ClubActUserListActivity.g(this.f9441a).addAll(a);
        ClubActUserListActivity.e(this.f9441a).m13153b();
        ClubActUserListActivity.e(this.f9441a).m13150a();
        if (ClubActUserListActivity.h(this.f9441a) == ClubActUserListActivity.g(this.f9441a).size() || (ClubActUserListActivity.b(this.f9441a) == 1 && ClubActUserListActivity.g(this.f9441a).size() < ClubActUserListActivity.c(this.f9441a))) {
            ClubActUserListActivity.e(this.f9441a).setCanLoadMore(false);
            ClubActUserListActivity.e(this.f9441a).setHasFooter(false);
        }
    }
}
