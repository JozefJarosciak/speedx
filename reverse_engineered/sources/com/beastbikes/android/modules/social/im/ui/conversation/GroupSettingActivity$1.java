package com.beastbikes.android.modules.social.im.ui.conversation;

import android.os.AsyncTask;
import com.beastbikes.android.modules.social.im.p074a.C2341a;
import com.beastbikes.framework.business.BusinessException;

class GroupSettingActivity$1 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ String f11178a;
    /* renamed from: b */
    final /* synthetic */ GroupSettingActivity f11179b;

    GroupSettingActivity$1(GroupSettingActivity groupSettingActivity, String str) {
        this.f11179b = groupSettingActivity;
        this.f11178a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11982a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11983a((Boolean) obj);
    }

    /* renamed from: a */
    protected Boolean m11982a(String... strArr) {
        try {
            return Boolean.valueOf(new C2341a(this.f11179b).m11957c(this.f11178a, GroupSettingActivity.a(this.f11179b).getObjectId()));
        } catch (BusinessException e) {
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m11983a(Boolean bool) {
    }
}
