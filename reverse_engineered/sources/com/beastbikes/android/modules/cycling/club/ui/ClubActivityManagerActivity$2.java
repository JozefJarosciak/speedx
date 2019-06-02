package com.beastbikes.android.modules.cycling.club.ui;

import android.os.AsyncTask;
import com.alipay.sdk.util.C0882j;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.p168e.p169a.C2642c;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.WebActivity;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.json.JSONObject;

class ClubActivityManagerActivity$2 extends AsyncTask<String, Void, JSONObject> {
    /* renamed from: a */
    final /* synthetic */ ClubActivityManagerActivity f9475a;

    ClubActivityManagerActivity$2(ClubActivityManagerActivity clubActivityManagerActivity) {
        this.f9475a = clubActivityManagerActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m10702a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m10703a((JSONObject) obj);
    }

    protected void onPreExecute() {
        if (ClubActivityManagerActivity.l(this.f9475a) != null) {
            ClubActivityManagerActivity.l(this.f9475a).show();
        }
    }

    /* renamed from: a */
    protected JSONObject m10702a(String... strArr) {
        JSONObject jSONObject = null;
        try {
            jSONObject = ClubActivityManagerActivity.m(this.f9475a).m10529a(ClubActivityManagerActivity.a(this.f9475a).getActId(), 3, null);
        } catch (BusinessException e) {
        }
        return jSONObject;
    }

    /* renamed from: a */
    protected void m10703a(JSONObject jSONObject) {
        if (!(this.f9475a.isFinishing() || ClubActivityManagerActivity.l(this.f9475a) == null)) {
            ClubActivityManagerActivity.l(this.f9475a).dismiss();
        }
        if (jSONObject == null) {
            Toasts.show(this.f9475a, (int) C1373R.string.task_get_share_content_error);
        } else if (jSONObject.optInt("code") == 0) {
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            if (optJSONObject != null) {
                ClubActivityManagerActivity.a(this.f9475a, optJSONObject.optString(WebActivity.EXTRA_TITLE));
                ClubActivityManagerActivity.b(this.f9475a, optJSONObject.optString("desc"));
                ClubActivityManagerActivity.c(this.f9475a, optJSONObject.optString("url"));
                ClubActivityManagerActivity.d(this.f9475a, optJSONObject.optString("shareLink"));
            }
            C2642c c2642c = new C2642c();
            c2642c.m13160b(ClubActivityManagerActivity.n(this.f9475a));
            c2642c.m13164d(ClubActivityManagerActivity.o(this.f9475a));
            c2642c.m13158a(ClubActivityManagerActivity.p(this.f9475a));
            c2642c.m13162c(ClubActivityManagerActivity.q(this.f9475a));
            c2642c.m13166f(ClubActivityManagerActivity.o(this.f9475a));
            c2642c.m13165e(ClubActivityManagerActivity.o(this.f9475a));
            ClubActivityManagerActivity.a(this.f9475a, c2642c);
        }
    }
}
