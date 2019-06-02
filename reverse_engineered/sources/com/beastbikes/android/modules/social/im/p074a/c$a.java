package com.beastbikes.android.modules.social.im.p074a;

import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.text.TextUtils;

/* compiled from: RongCloudManager */
/* renamed from: com.beastbikes.android.modules.social.im.a.c$a */
final class c$a extends AsyncTask<String, Void, String> {
    /* renamed from: a */
    final /* synthetic */ C1434c f11138a;

    c$a(C1434c c1434c) {
        this.f11138a = c1434c;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11960a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11961a((String) obj);
    }

    /* renamed from: a */
    protected String m11960a(String... strArr) {
        return new C2341a(C1434c.b(this.f11138a)).m11954b();
    }

    /* renamed from: a */
    protected void m11961a(String str) {
        C1434c.g().error("rongcloud token", str);
        if (!TextUtils.isEmpty(str) && !C1434c.a(this.f11138a).getString("beast.rongcloud.user.token", "").equals(str)) {
            Editor edit = C1434c.a(this.f11138a).edit();
            edit.putString("beast.rongcloud.user.token", str);
            edit.apply();
            C1434c.a(this.f11138a, false);
            C1434c.a(this.f11138a, str, this.f11138a);
        }
    }
}
