package com.beastbikes.android.modules.preferences.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.ProfileDTO;

class UserSettingFragment$8 extends AsyncTask<String, Void, ProfileDTO> {
    /* renamed from: a */
    final /* synthetic */ UserSettingFragment f10997a;

    UserSettingFragment$8(UserSettingFragment userSettingFragment) {
        this.f10997a = userSettingFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11839a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11840a((ProfileDTO) obj);
    }

    /* renamed from: a */
    protected ProfileDTO m11839a(String... strArr) {
        try {
            return UserSettingFragment.h(this.f10997a).m12131b(strArr[0]);
        } catch (Throwable e) {
            UserSettingFragment.a().error("Get RemoteUserInfo failed", e);
            return null;
        }
    }

    /* renamed from: a */
    protected void m11840a(ProfileDTO profileDTO) {
        if (profileDTO != null) {
            UserSettingFragment.a(this.f10997a, profileDTO);
        }
    }
}
