package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import com.beastbikes.android.modules.cycling.ranking.dto.C2173a;
import com.beastbikes.android.modules.user.ui.ProfileActivity;

class TotalMemberRankFrag$1 implements OnItemClickListener {
    /* renamed from: a */
    final /* synthetic */ TotalMemberRankFrag f9838a;

    TotalMemberRankFrag$1(TotalMemberRankFrag totalMemberRankFrag) {
        this.f9838a = totalMemberRankFrag;
    }

    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        C2173a c2173a = (C2173a) adapterView.getItemAtPosition(i);
        if (c2173a != null) {
            Intent intent = new Intent();
            intent.setClass(this.f9838a.getActivity(), ProfileActivity.class);
            intent.putExtra("user_id", c2173a.m11137b());
            intent.putExtra("avatar", c2173a.m11141f());
            intent.putExtra("nick_name", c2173a.m11142g());
            intent.putExtra("remarks", c2173a.m11145j());
            this.f9838a.startActivity(intent);
        }
    }
}
