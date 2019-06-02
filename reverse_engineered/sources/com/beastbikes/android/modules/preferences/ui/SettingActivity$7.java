package com.beastbikes.android.modules.preferences.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.framework.android.p176b.C2790a;
import com.beastbikes.framework.business.BusinessException;
import com.squareup.picasso.Picasso;

class SettingActivity$7 extends AsyncTask<String, Void, ProfileDTO> {
    /* renamed from: a */
    final /* synthetic */ C2790a f10968a;
    /* renamed from: b */
    final /* synthetic */ SettingActivity f10969b;

    SettingActivity$7(SettingActivity settingActivity, C2790a c2790a) {
        this.f10969b = settingActivity;
        this.f10968a = c2790a;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11832a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11833a((ProfileDTO) obj);
    }

    /* renamed from: a */
    protected ProfileDTO m11832a(String... strArr) {
        try {
            if (SettingActivity.a(this.f10969b) == null) {
                return null;
            }
            ProfileDTO b = SettingActivity.a(this.f10969b).m12131b(strArr[0]);
            if (!(this.f10968a == null || b == null)) {
                this.f10968a.m13732a(strArr[0], b.getAvatar());
            }
            return b;
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m11833a(ProfileDTO profileDTO) {
        if (profileDTO != null) {
            Object avatar = profileDTO.getAvatar();
            CharSequence nickname = profileDTO.getNickname();
            CharSequence location = profileDTO.getLocation();
            if (TextUtils.isEmpty(avatar)) {
                SettingActivity.b(this.f10969b).setImageResource(C1373R.drawable.ic_avatar);
            } else {
                Picasso.with(this.f10969b).load(avatar).fit().centerCrop().error(C1373R.drawable.ic_avatar).placeholder(C1373R.drawable.ic_avatar).into(SettingActivity.b(this.f10969b));
            }
            if (!TextUtils.isEmpty(nickname)) {
                SettingActivity.c(this.f10969b).setText(profileDTO.getNickname());
            }
            if (!TextUtils.isEmpty(location)) {
                SettingActivity.d(this.f10969b).setText(profileDTO.getLocation());
            }
        }
    }
}
