package com.beastbikes.android.modules.cycling.ranking.ui;

import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.modules.user.ui.ProfileFragment$a;
import rx.p208a.C5694b;

class WeeklyRankFragment$1 implements C5694b<Object> {
    /* renamed from: a */
    final /* synthetic */ WeeklyRankFragment f10205a;

    WeeklyRankFragment$1(WeeklyRankFragment weeklyRankFragment) {
        this.f10205a = weeklyRankFragment;
    }

    public void call(Object obj) {
        if (obj instanceof ProfileFragment$a) {
            for (int i = 0; i < WeeklyRankFragment.a(this.f10205a).size(); i++) {
                if (TextUtils.equals(((RankDTO) WeeklyRankFragment.a(this.f10205a).get(i)).getUserId(), ((ProfileFragment$a) obj).f11766a)) {
                    ((RankDTO) WeeklyRankFragment.a(this.f10205a).get(i)).setRemarks(((ProfileFragment$a) obj).f11767b);
                    WeeklyRankFragment.b(this.f10205a).m13153b();
                    return;
                }
            }
        }
    }
}
