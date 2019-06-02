package com.beastbikes.android.modules.cycling.ranking.ui;

import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.modules.user.ui.ProfileFragment$a;
import rx.p208a.C5694b;

class YearlyRankFragment$1 implements C5694b<Object> {
    /* renamed from: a */
    final /* synthetic */ YearlyRankFragment f10210a;

    YearlyRankFragment$1(YearlyRankFragment yearlyRankFragment) {
        this.f10210a = yearlyRankFragment;
    }

    public void call(Object obj) {
        if (obj instanceof ProfileFragment$a) {
            for (int i = 0; i < YearlyRankFragment.a(this.f10210a).size(); i++) {
                if (TextUtils.equals(((RankDTO) YearlyRankFragment.a(this.f10210a).get(i)).getUserId(), ((ProfileFragment$a) obj).f11766a)) {
                    ((RankDTO) YearlyRankFragment.a(this.f10210a).get(i)).setRemarks(((ProfileFragment$a) obj).f11767b);
                    YearlyRankFragment.b(this.f10210a).m13153b();
                    return;
                }
            }
        }
    }
}
