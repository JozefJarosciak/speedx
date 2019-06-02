package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.framework.business.BusinessException;

class ProfileFragment$5 extends AsyncTask<String, Void, ProfileDTO> {
    /* renamed from: a */
    final /* synthetic */ ProfileFragment f11757a;

    ProfileFragment$5(ProfileFragment profileFragment) {
        this.f11757a = profileFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12525a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12526a((ProfileDTO) obj);
    }

    /* renamed from: a */
    protected ProfileDTO m12525a(String... strArr) {
        try {
            return ProfileFragment.d(this.f11757a).m12131b(strArr[0]);
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m12526a(ProfileDTO profileDTO) {
        if (profileDTO != null) {
            ProfileFragment.a(this.f11757a, profileDTO);
            ProfileFragment.b(this.f11757a, profileDTO);
        }
    }
}
