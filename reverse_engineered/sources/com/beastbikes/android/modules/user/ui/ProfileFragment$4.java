package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.social.im.p074a.C1434c;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.modules.user.p077a.C2389c.C2388a;
import com.beastbikes.framework.android.p176b.C2790a;
import com.beastbikes.framework.business.BusinessException;

class ProfileFragment$4 extends AsyncTask<String, Void, ProfileDTO> {
    /* renamed from: a */
    final /* synthetic */ C2388a f11755a;
    /* renamed from: b */
    final /* synthetic */ ProfileFragment f11756b;

    ProfileFragment$4(ProfileFragment profileFragment, C2388a c2388a) {
        this.f11756b = profileFragment;
        this.f11755a = c2388a;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12523a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12524a((ProfileDTO) obj);
    }

    /* renamed from: a */
    protected ProfileDTO m12523a(String... strArr) {
        try {
            ProfileDTO a = ProfileFragment.d(this.f11756b).m12122a(strArr[0], this.f11755a);
            if (a == null) {
                return null;
            }
            if (ProfileFragment.e(this.f11756b) && C1434c.c() != null) {
                C1434c.c().a(a);
            }
            C2790a.m13728a().m13732a(strArr[0], a.getAvatar());
            return a;
        } catch (BusinessException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    protected void m12524a(ProfileDTO profileDTO) {
        if (profileDTO != null && this.f11756b.getActivity() != null) {
            ProfileFragment.a(this.f11756b, profileDTO);
            ProfileFragment.b(this.f11756b, profileDTO);
        }
    }
}
