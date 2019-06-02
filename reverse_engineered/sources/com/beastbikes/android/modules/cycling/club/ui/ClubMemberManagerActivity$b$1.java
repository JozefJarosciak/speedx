package com.beastbikes.android.modules.cycling.club.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.modules.cycling.club.ui.ClubMemberManagerActivity.C1409b;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.android.utils.C2580w;

class ClubMemberManagerActivity$b$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ RankDTO f9733a;
    /* renamed from: b */
    final /* synthetic */ C1409b f9734b;

    ClubMemberManagerActivity$b$1(C1409b c1409b, RankDTO rankDTO) {
        this.f9734b = c1409b;
        this.f9733a = rankDTO;
    }

    public void onClick(View view) {
        C1409b.a(this.f9734b, this.f9733a);
        C2580w.m12905a(this.f9734b.getContext(), " 删除成员", null);
    }
}
