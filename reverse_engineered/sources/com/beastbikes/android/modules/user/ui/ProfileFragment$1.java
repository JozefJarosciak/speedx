package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.beastbikes.framework.business.BusinessException;

class ProfileFragment$1 extends AsyncTask<Long, Void, Integer> {
    /* renamed from: a */
    final /* synthetic */ ProfileFragment f11751a;

    ProfileFragment$1(ProfileFragment profileFragment) {
        this.f11751a = profileFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12517a((Long[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12518a((Integer) obj);
    }

    /* renamed from: a */
    protected Integer m12517a(Long... lArr) {
        try {
            return Integer.valueOf(ProfileFragment.a(this.f11751a).m11805a(lArr[0].longValue()));
        } catch (BusinessException e) {
            return Integer.valueOf(-1);
        }
    }

    /* renamed from: a */
    protected void m12518a(Integer num) {
        ProfileFragment.b(this.f11751a).edit().putInt("beast.friend.new.message.count", num.intValue()).apply();
        ProfileFragment.c(this.f11751a);
    }
}
