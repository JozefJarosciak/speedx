package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.modules.user.dto.HistogramDTO;

class ProfileFragment$3 extends AsyncTask<String, Void, HistogramDTO> {
    /* renamed from: a */
    final /* synthetic */ ProfileFragment f11754a;

    ProfileFragment$3(ProfileFragment profileFragment) {
        this.f11754a = profileFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12521a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12522a((HistogramDTO) obj);
    }

    /* renamed from: a */
    protected HistogramDTO m12521a(String... strArr) {
        return ProfileFragment.d(this.f11754a).m12119a(0, ProfileFragment.p(this.f11754a), 30);
    }

    /* renamed from: a */
    protected void m12522a(HistogramDTO histogramDTO) {
        ProfileFragment.q(this.f11754a).setHistogramDTO(histogramDTO);
    }
}
