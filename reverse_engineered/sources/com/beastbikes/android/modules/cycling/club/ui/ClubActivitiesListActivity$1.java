package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityInfoList;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityListDTO;
import com.beastbikes.android.modules.cycling.club.ui.widget.ClubActivityOntListFragment;
import java.util.List;

class ClubActivitiesListActivity$1 extends AsyncTask<Void, Void, List<ClubActivityListDTO>> {
    /* renamed from: a */
    final /* synthetic */ ClubActivitiesListActivity f9450a;

    ClubActivitiesListActivity$1(ClubActivitiesListActivity clubActivitiesListActivity) {
        this.f9450a = clubActivitiesListActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10680a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10681a((List) obj);
    }

    /* renamed from: a */
    protected List<ClubActivityListDTO> m10680a(Void... voidArr) {
        return ClubActivitiesListActivity.b(this.f9450a).m10561a(ClubActivitiesListActivity.a(this.f9450a), 1, 20);
    }

    /* renamed from: a */
    protected void m10681a(List<ClubActivityListDTO> list) {
        if (!this.f9450a.isFinishing()) {
            if (list != null && !list.isEmpty()) {
                Fragment clubActivityListFragment = new ClubActivityListFragment();
                Bundle bundle = new Bundle();
                bundle.putString("club_id", ClubActivitiesListActivity.a(this.f9450a));
                bundle.putBoolean("isclub", ClubActivitiesListActivity.c(this.f9450a));
                bundle.putBoolean("club_member_has_footer", list.size() == 20);
                bundle.putSerializable(C0861d.f2139k, new ClubActivityInfoList(list));
                clubActivityListFragment.setArguments(bundle);
                ClubActivitiesListActivity.d(this.f9450a).beginTransaction().replace(C1373R.id.activity_club_activities_list_fragment, clubActivityListFragment).commitAllowingStateLoss();
            } else if (list != null && list.isEmpty()) {
                Fragment clubActivityOntListFragment = new ClubActivityOntListFragment();
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("isclub", ClubActivitiesListActivity.c(this.f9450a));
                bundle2.putString("club_id", ClubActivitiesListActivity.a(this.f9450a));
                clubActivityOntListFragment.setArguments(bundle2);
                ClubActivitiesListActivity.d(this.f9450a).beginTransaction().replace(C1373R.id.activity_club_activities_list_fragment, clubActivityOntListFragment).commitAllowingStateLoss();
            }
        }
    }
}
