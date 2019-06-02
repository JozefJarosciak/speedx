package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityListDTO;
import java.util.List;

class ClubActivityListFragment$1 extends AsyncTask<Void, Void, List<ClubActivityListDTO>> {
    /* renamed from: a */
    final /* synthetic */ ClubActivityListFragment f9454a;

    ClubActivityListFragment$1(ClubActivityListFragment clubActivityListFragment) {
        this.f9454a = clubActivityListFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10683a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10684a((List) obj);
    }

    /* renamed from: a */
    protected List<ClubActivityListDTO> m10683a(Void... voidArr) {
        return ClubActivityListFragment.c(this.f9454a).m10561a(ClubActivityListFragment.a(this.f9454a), ClubActivityListFragment.b(this.f9454a), 20);
    }

    /* renamed from: a */
    protected void m10684a(List<ClubActivityListDTO> list) {
        if (list == null) {
            ClubActivityListFragment.d(this.f9454a).setHasFooter(false);
            ClubActivityListFragment.d(this.f9454a).m13150a();
        } else if (list.size() == 0) {
            ClubActivityListFragment.d(this.f9454a).setCanLoadMore(false);
            ClubActivityListFragment.d(this.f9454a).setHasFooter(false);
            ClubActivityListFragment.d(this.f9454a).m13150a();
        } else {
            if (ClubActivityListFragment.e(this.f9454a)) {
                ClubActivityListFragment.a(this.f9454a, false);
                ClubActivityListFragment.f(this.f9454a).clear();
            }
            ClubActivityListFragment.f(this.f9454a).addAll(list);
            ClubActivityListFragment.d(this.f9454a).m13153b();
            ClubActivityListFragment.d(this.f9454a).m13150a();
            if (ClubActivityListFragment.g(this.f9454a)) {
                ClubActivityListFragment.b(this.f9454a, false);
                if (ClubActivityListFragment.f(this.f9454a).size() < 20) {
                    ClubActivityListFragment.d(this.f9454a).setHasFooter(false);
                }
            }
        }
    }
}
