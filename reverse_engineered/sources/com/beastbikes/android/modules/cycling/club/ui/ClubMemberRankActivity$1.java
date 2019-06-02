package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import java.util.List;

class ClubMemberRankActivity$1 extends AsyncTask<String, Void, List<RankDTO>> {
    /* renamed from: a */
    final /* synthetic */ ClubMemberRankActivity f9739a;

    ClubMemberRankActivity$1(ClubMemberRankActivity clubMemberRankActivity) {
        this.f9739a = clubMemberRankActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10840a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10841a((List) obj);
    }

    /* renamed from: a */
    protected List<RankDTO> m10840a(String... strArr) {
        try {
            return ClubMemberRankActivity.a(this.f9739a).m10540a(strArr[0], 1, 1, 50);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m10841a(List<RankDTO> list) {
        if (list != null && !list.isEmpty()) {
            ClubMemberRankActivity.b(this.f9739a).clear();
            ClubMemberRankActivity.b(this.f9739a).addAll(list);
            ClubMemberRankActivity.c(this.f9739a).notifyDataSetChanged();
        }
    }
}
