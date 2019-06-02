package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeed;
import com.beastbikes.android.widget.SwipeRefreshAndLoadLayout;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ClubFeedInfoFrag$6 extends AsyncTask<String, Void, List<ClubFeed>> {
    /* renamed from: a */
    final /* synthetic */ ClubFeedInfoFrag f9645a;

    ClubFeedInfoFrag$6(ClubFeedInfoFrag clubFeedInfoFrag) {
        this.f9645a = clubFeedInfoFrag;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10794a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10795a((List) obj);
    }

    /* renamed from: a */
    protected List<ClubFeed> m10794a(String... strArr) {
        try {
            return ClubFeedInfoFrag.f(this.f9645a).m10574a(strArr[0], ClubFeedInfoFrag.k(this.f9645a), 0, 10, this.f9645a);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10795a(List<ClubFeed> list) {
        boolean z = false;
        if (ClubFeedInfoFrag.e(this.f9645a) != null) {
            ClubFeedInfoFrag.e(this.f9645a).setLoading(false);
            SwipeRefreshAndLoadLayout e = ClubFeedInfoFrag.e(this.f9645a);
            if (list != null && list.size() >= 10) {
                z = true;
            }
            e.setCanLoad(z);
            if (list != null && list.size() > 0) {
                ClubFeedInfoFrag.a(this.f9645a, ((ClubFeed) list.get(list.size() - 1)).getStamp());
                if (ClubFeedInfoFrag.g(this.f9645a) != null) {
                    ClubFeedInfoFrag.g(this.f9645a).m10948b(list, true);
                }
            }
        }
    }
}
