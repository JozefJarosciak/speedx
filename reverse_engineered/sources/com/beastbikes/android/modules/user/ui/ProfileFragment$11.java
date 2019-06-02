package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ProfileFragment$11 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f11749a;
    /* renamed from: b */
    final /* synthetic */ ProfileFragment f11750b;

    ProfileFragment$11(ProfileFragment profileFragment, String str) {
        this.f11750b = profileFragment;
        this.f11749a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12515a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12516a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m12515a(String... strArr) {
        return Boolean.valueOf(ProfileFragment.d(this.f11750b).m12140f(this.f11749a));
    }

    /* renamed from: a */
    protected void m12516a(Boolean bool) {
        if (bool.booleanValue() && this.f11750b.getActivity() != null) {
            Toasts.show(this.f11750b.getActivity(), (int) C1373R.string.lable_follow_success_msg);
            ProfileFragment.m(this.f11750b).setText(String.valueOf(Integer.valueOf(ProfileFragment.m(this.f11750b).getText().toString()).intValue() + 1));
            ProfileFragment.a(this.f11750b, ProfileFragment.n(this.f11750b) + 2);
            ProfileFragment.o(this.f11750b);
        }
    }
}
