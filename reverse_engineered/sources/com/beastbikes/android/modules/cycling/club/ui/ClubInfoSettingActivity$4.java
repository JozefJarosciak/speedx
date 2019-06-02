package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.club.dto.ClubInfoCompact;
import com.beastbikes.framework.business.BusinessException;
import com.squareup.picasso.Picasso;

class ClubInfoSettingActivity$4 extends AsyncTask<Void, Void, ClubInfoCompact> {
    /* renamed from: a */
    final /* synthetic */ String f9712a;
    /* renamed from: b */
    final /* synthetic */ ClubInfoSettingActivity f9713b;

    ClubInfoSettingActivity$4(ClubInfoSettingActivity clubInfoSettingActivity, String str) {
        this.f9713b = clubInfoSettingActivity;
        this.f9712a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10827a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10828a((ClubInfoCompact) obj);
    }

    /* renamed from: a */
    protected ClubInfoCompact m10827a(Void... voidArr) {
        ClubInfoCompact clubInfoCompact = null;
        if (ClubInfoSettingActivity.c(this.f9713b) != null) {
            try {
                clubInfoCompact = ClubInfoSettingActivity.c(this.f9713b).m10533a(this.f9712a);
            } catch (BusinessException e) {
            }
        }
        return clubInfoCompact;
    }

    /* renamed from: a */
    protected void m10828a(ClubInfoCompact clubInfoCompact) {
        if (clubInfoCompact != null) {
            ClubInfoSettingActivity.a(this.f9713b, clubInfoCompact);
            CharSequence name = clubInfoCompact.getName();
            if (!TextUtils.isEmpty(name)) {
                ClubInfoSettingActivity.d(this.f9713b).setText(name);
            }
            name = clubInfoCompact.getDesc();
            if (!TextUtils.isEmpty(name)) {
                ClubInfoSettingActivity.e(this.f9713b).setText(name);
            }
            Object logo = clubInfoCompact.getLogo();
            if (TextUtils.isEmpty(logo)) {
                ClubInfoSettingActivity.f(this.f9713b).setImageResource(C1373R.drawable.ic_club_setting_default_logo);
            } else {
                Picasso.with(this.f9713b).load(logo).fit().error(C1373R.drawable.ic_club_setting_default_logo).placeholder(C1373R.drawable.ic_club_setting_default_logo).centerCrop().into(ClubInfoSettingActivity.f(this.f9713b));
            }
            if (ClubInfoSettingActivity.g(this.f9713b).getIsPrivate()) {
                ClubInfoSettingActivity.h(this.f9713b).setChecked(true);
                ClubInfoSettingActivity.a(this.f9713b, 1);
                return;
            }
            ClubInfoSettingActivity.h(this.f9713b).setChecked(false);
            ClubInfoSettingActivity.a(this.f9713b, 0);
        }
    }
}
