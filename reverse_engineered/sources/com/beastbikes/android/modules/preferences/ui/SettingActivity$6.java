package com.beastbikes.android.modules.preferences.ui;

import android.content.Context;
import android.os.AsyncTask;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.framework.android.p176b.C2790a;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.p052a.p053a.C1370a;

class SettingActivity$6 extends AsyncTask<Void, Void, Void> {
    /* renamed from: a */
    final /* synthetic */ C1802i f10966a;
    /* renamed from: b */
    final /* synthetic */ SettingActivity f10967b;

    SettingActivity$6(SettingActivity settingActivity, C1802i c1802i) {
        this.f10967b = settingActivity;
        this.f10966a = c1802i;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11830a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11831a((Void) obj);
    }

    protected void onPreExecute() {
        this.f10966a.show();
    }

    /* renamed from: a */
    protected void m11831a(Void voidR) {
        this.f10966a.dismiss();
    }

    /* renamed from: a */
    protected Void m11830a(Void... voidArr) {
        Context context = this.f10967b;
        try {
            new C1398a(context).b();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
        C2790a.m13728a().m13731a(context);
        C1370a.a(context).a();
        return null;
    }
}
