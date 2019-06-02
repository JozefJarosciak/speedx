package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.UserDetailDTO;
import com.beastbikes.framework.business.BusinessException;

class ProfileFragment$6 extends AsyncTask<String, Void, UserDetailDTO> {
    /* renamed from: a */
    final /* synthetic */ ProfileFragment f11758a;

    ProfileFragment$6(ProfileFragment profileFragment) {
        this.f11758a = profileFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12527a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12528a((UserDetailDTO) obj);
    }

    /* renamed from: a */
    protected UserDetailDTO m12527a(String... strArr) {
        try {
            return ProfileFragment.d(this.f11758a).m12138d(strArr[0]);
        } catch (BusinessException e) {
            return null;
        }
    }

    /* renamed from: a */
    protected void m12528a(UserDetailDTO userDetailDTO) {
        if (this.f11758a.getActivity() != null && !this.f11758a.getActivity().isFinishing() && userDetailDTO != null) {
            ProfileFragment.a(this.f11758a, userDetailDTO);
            ProfileFragment.f(this.f11758a);
        }
    }
}
