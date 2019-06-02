package com.beastbikes.android.modules.cycling.club.ui;

import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.utils.C2560h.C2082a;
import com.squareup.picasso.Picasso;

class ClubCreateActivity$5 implements C2082a {
    /* renamed from: a */
    final /* synthetic */ ClubCreateActivity f9537a;

    ClubCreateActivity$5(ClubCreateActivity clubCreateActivity) {
        this.f9537a = clubCreateActivity;
    }

    /* renamed from: a */
    public void mo3376a(String str) {
        ClubCreateActivity.a(this.f9537a, str);
        if (TextUtils.isEmpty(str)) {
            ClubCreateActivity.a(this.f9537a).setImageResource(C1373R.drawable.ic_avatar_club);
        } else {
            Picasso.with(this.f9537a).load("file://" + str).fit().centerCrop().error(C1373R.drawable.ic_avatar_club).placeholder(C1373R.drawable.ic_avatar_club).into(ClubCreateActivity.a(this.f9537a));
        }
    }
}
