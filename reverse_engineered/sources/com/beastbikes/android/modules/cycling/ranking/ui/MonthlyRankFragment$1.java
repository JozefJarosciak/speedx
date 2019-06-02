package com.beastbikes.android.modules.cycling.ranking.ui;

import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.modules.user.ui.ProfileFragment$a;
import rx.p208a.C5694b;

class MonthlyRankFragment$1 implements C5694b<Object> {
    /* renamed from: a */
    final /* synthetic */ MonthlyRankFragment f10186a;

    MonthlyRankFragment$1(MonthlyRankFragment monthlyRankFragment) {
        this.f10186a = monthlyRankFragment;
    }

    public void call(Object obj) {
        if (obj instanceof ProfileFragment$a) {
            for (int i = 0; i < MonthlyRankFragment.a(this.f10186a).size(); i++) {
                if (TextUtils.equals(((RankDTO) MonthlyRankFragment.a(this.f10186a).get(i)).getUserId(), ((ProfileFragment$a) obj).f11766a)) {
                    ((RankDTO) MonthlyRankFragment.a(this.f10186a).get(i)).setRemarks(((ProfileFragment$a) obj).f11767b);
                    MonthlyRankFragment.b(this.f10186a).m13153b();
                    return;
                }
            }
        }
    }
}
