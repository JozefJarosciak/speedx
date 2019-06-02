package com.beastbikes.android.modules.cycling.club.ui;

import android.content.SharedPreferences.Editor;
import android.view.MenuItem;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.p062c.C1882d;
import com.squareup.picasso.Picasso;

class ClubInfoSettingActivity$2 implements C1882d {
    /* renamed from: a */
    final /* synthetic */ String f9703a;
    /* renamed from: b */
    final /* synthetic */ String f9704b;
    /* renamed from: c */
    final /* synthetic */ MenuItem f9705c;
    /* renamed from: d */
    final /* synthetic */ ClubInfoSettingActivity f9706d;

    ClubInfoSettingActivity$2(ClubInfoSettingActivity clubInfoSettingActivity, String str, String str2, MenuItem menuItem) {
        this.f9706d = clubInfoSettingActivity;
        this.f9703a = str;
        this.f9704b = str2;
        this.f9705c = menuItem;
    }

    /* renamed from: a */
    public void mo3362a(String str) {
        Picasso.with(this.f9706d).invalidate(str);
        if (AVUser.getCurrentUser() != null) {
            Editor edit = this.f9706d.getSharedPreferences(AVUser.getCurrentUser().getObjectId(), 0).edit();
            edit.putString("beast.club.logo.locale", ClubInfoSettingActivity.a(this.f9706d));
            edit.putString("beast.club.logo", str);
            edit.putLong("beast.club.logo.change", System.currentTimeMillis());
            edit.apply();
        }
        ClubInfoSettingActivity.a(this.f9706d, this.f9706d.f5138a, this.f9703a, this.f9704b, str);
        this.f9705c.setEnabled(true);
    }

    /* renamed from: a */
    public void mo3361a() {
        ClubInfoSettingActivity.a(this.f9706d, "", this.f9703a, this.f9704b, "");
        this.f9705c.setEnabled(true);
    }
}
