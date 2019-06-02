package com.beastbikes.android.modules.cycling.ranking.ui;

import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.modules.user.ui.ProfileFragment$a;
import rx.p208a.C5694b;

class TotallyRankFragment$1 implements C5694b<Object> {
    /* renamed from: a */
    final /* synthetic */ TotallyRankFragment f10200a;

    TotallyRankFragment$1(TotallyRankFragment totallyRankFragment) {
        this.f10200a = totallyRankFragment;
    }

    public void call(Object obj) {
        if (obj instanceof ProfileFragment$a) {
            for (int i = 0; i < TotallyRankFragment.a(this.f10200a).size(); i++) {
                if (TextUtils.equals(((RankDTO) TotallyRankFragment.a(this.f10200a).get(i)).getUserId(), ((ProfileFragment$a) obj).f11766a)) {
                    ((RankDTO) TotallyRankFragment.a(this.f10200a).get(i)).setRemarks(((ProfileFragment$a) obj).f11767b);
                    TotallyRankFragment.b(this.f10200a).m13153b();
                    return;
                }
            }
        }
    }
}
