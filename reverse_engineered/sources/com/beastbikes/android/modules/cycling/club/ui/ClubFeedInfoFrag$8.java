package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.club.biz.C2052c.C2050a;
import com.beastbikes.android.modules.cycling.club.dto.ClubFeed;
import com.beastbikes.android.widget.SwipeRefreshAndLoadLayout;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ClubFeedInfoFrag$8 extends AsyncTask<String, Void, List<ClubFeed>> {
    /* renamed from: a */
    final /* synthetic */ boolean f9649a;
    /* renamed from: b */
    final /* synthetic */ C2050a f9650b;
    /* renamed from: c */
    final /* synthetic */ ClubFeedInfoFrag f9651c;

    ClubFeedInfoFrag$8(ClubFeedInfoFrag clubFeedInfoFrag, boolean z, C2050a c2050a) {
        this.f9651c = clubFeedInfoFrag;
        this.f9649a = z;
        this.f9650b = c2050a;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10798a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10799a((List) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        ClubFeedInfoFrag.e(this.f9651c).setRefreshing(this.f9649a);
    }

    /* renamed from: a */
    protected List<ClubFeed> m10798a(String... strArr) {
        try {
            return ClubFeedInfoFrag.f(this.f9651c).m10573a(strArr[0], 10, this.f9650b);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10799a(List<ClubFeed> list) {
        ClubFeedInfoFrag.e(this.f9651c).setRefreshing(false);
        SwipeRefreshAndLoadLayout e = ClubFeedInfoFrag.e(this.f9651c);
        boolean z = list != null && list.size() >= 10;
        e.setCanLoad(z);
        if (list == null) {
            ClubFeedInfoFrag.b(this.f9651c).edit().putInt("beast.club.status", 0).apply();
            AVUser currentUser = AVUser.getCurrentUser();
            if (currentUser != null) {
                currentUser.setClubId("");
            }
        } else if (list.size() > 0) {
            ClubFeedInfoFrag.a(this.f9651c, ((ClubFeed) list.get(list.size() - 1)).getStamp());
            if (ClubFeedInfoFrag.g(this.f9651c) != null) {
                ClubFeedInfoFrag.g(this.f9651c).m10948b(list, false);
            }
        }
    }
}
