package com.beastbikes.android.modules.cycling.club.ui.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.modules.cycling.club.ui.ClubActivityReleaseActivity;

class ClubActivityOntListFragment$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ boolean f10028a;
    /* renamed from: b */
    final /* synthetic */ Bundle f10029b;
    /* renamed from: c */
    final /* synthetic */ ClubActivityOntListFragment f10030c;

    ClubActivityOntListFragment$1(ClubActivityOntListFragment clubActivityOntListFragment, boolean z, Bundle bundle) {
        this.f10030c = clubActivityOntListFragment;
        this.f10028a = z;
        this.f10029b = bundle;
    }

    public void onClick(View view) {
        Intent intent = new Intent(this.f10030c.getActivity(), ClubActivityReleaseActivity.class);
        intent.putExtra("isclub", this.f10028a);
        intent.putExtra("club_id", this.f10029b.getString("club_id"));
        this.f10030c.getActivity().startActivity(intent);
    }
}
