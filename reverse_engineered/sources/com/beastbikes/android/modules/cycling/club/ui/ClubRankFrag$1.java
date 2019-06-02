package com.beastbikes.android.modules.cycling.club.ui;

import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.android.modules.cycling.club.dto.ClubRankBean;
import com.beastbikes.android.utils.C2562j;

class ClubRankFrag$1 implements OnItemClickListener {
    /* renamed from: a */
    final /* synthetic */ ClubRankFrag f9795a;

    ClubRankFrag$1(ClubRankFrag clubRankFrag) {
        this.f9795a = clubRankFrag;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        ClubRankBean clubRankBean = (ClubRankBean) adapterView.getItemAtPosition(i);
        if (clubRankBean != null) {
            ClubInfoCompact clubInfoCompact = new ClubInfoCompact(clubRankBean.getName(), clubRankBean.getLogo(), clubRankBean.getMembers(), clubRankBean.getMilestone(), clubRankBean.getCity(), clubRankBean.getObjectId());
            clubInfoCompact.setIsPrivate(clubRankBean.isPrivate());
            C2562j.m12866a(this.f9795a.getContext(), clubInfoCompact);
        }
    }
}
