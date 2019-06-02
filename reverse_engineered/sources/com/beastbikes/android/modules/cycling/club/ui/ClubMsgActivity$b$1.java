package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.modules.cycling.club.dto.C2066e;
import com.beastbikes.android.modules.cycling.club.ui.ClubMsgActivity.C1410b;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.ui.ProfileActivity;
import com.beastbikes.android.utils.C2562j;

class ClubMsgActivity$b$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2066e f9777a;
    /* renamed from: b */
    final /* synthetic */ ProfileDTO f9778b;
    /* renamed from: c */
    final /* synthetic */ C1410b f9779c;

    ClubMsgActivity$b$1(C1410b c1410b, C2066e c2066e, ProfileDTO profileDTO) {
        this.f9779c = c1410b;
        this.f9777a = c2066e;
        this.f9778b = profileDTO;
    }

    public void onClick(View view) {
        switch (this.f9777a.m10645d()) {
            case 0:
                if (this.f9778b != null) {
                    Intent intent = new Intent(this.f9779c.f5245a, ProfileActivity.class);
                    intent.putExtra("user_id", this.f9778b.getUserId());
                    intent.putExtra("avatar", this.f9778b.getAvatar());
                    this.f9779c.f5245a.startActivity(intent);
                    return;
                }
                return;
            case 1:
                C2562j.m12866a(view.getContext(), this.f9777a.m10648g());
                return;
            default:
                return;
        }
    }
}
