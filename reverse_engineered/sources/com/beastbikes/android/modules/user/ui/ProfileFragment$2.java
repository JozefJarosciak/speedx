package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ProfileFragment$2 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f11752a;
    /* renamed from: b */
    final /* synthetic */ ProfileFragment f11753b;

    ProfileFragment$2(ProfileFragment profileFragment, String str) {
        this.f11753b = profileFragment;
        this.f11752a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12519a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12520a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m12519a(String... strArr) {
        return Boolean.valueOf(ProfileFragment.d(this.f11753b).m12141g(this.f11752a));
    }

    /* renamed from: a */
    protected void m12520a(Boolean bool) {
        if (bool.booleanValue()) {
            Toasts.show(this.f11753b.getActivity(), (int) C1373R.string.lable_unfollow_success_msg);
            int intValue = Integer.valueOf(ProfileFragment.m(this.f11753b).getText().toString()).intValue();
            if (intValue > 0) {
                ProfileFragment.m(this.f11753b).setText(String.valueOf(intValue - 1));
            }
            ProfileFragment.a(this.f11753b, ProfileFragment.n(this.f11753b) - 2);
            ProfileFragment.o(this.f11753b);
        }
    }
}
