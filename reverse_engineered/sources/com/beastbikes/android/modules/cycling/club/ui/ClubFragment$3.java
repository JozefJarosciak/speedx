package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;

class ClubFragment$3 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ ClubFragment f9665a;

    ClubFragment$3(ClubFragment clubFragment) {
        this.f9665a = clubFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10800a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10801a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m10800a(String... strArr) {
        try {
            if (TextUtils.isEmpty(this.f9665a.f5083b)) {
                return Boolean.valueOf(false);
            }
            return Boolean.valueOf(ClubFragment.a(this.f9665a).m10541a(2, this.f9665a.f5083b, null, null));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m10801a(Boolean bool) {
        if (ClubFragment.b(this.f9665a) != null && ClubFragment.b(this.f9665a).isShowing()) {
            ClubFragment.b(this.f9665a).dismiss();
        }
        if (bool.booleanValue()) {
            this.f9665a.startActivity(new Intent(this.f9665a.getActivity(), ClubCreateActivity.class));
        } else {
            Toasts.show(this.f9665a.getActivity(), this.f9665a.getString(C1373R.string.club_info_cancel_toast_fail));
        }
    }
}
