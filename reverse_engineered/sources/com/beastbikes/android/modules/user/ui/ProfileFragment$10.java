package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.json.JSONObject;

class ProfileFragment$10 extends AsyncTask<String, Void, JSONObject> {
    /* renamed from: a */
    final /* synthetic */ String f11747a;
    /* renamed from: b */
    final /* synthetic */ ProfileFragment f11748b;

    ProfileFragment$10(ProfileFragment profileFragment, String str) {
        this.f11748b = profileFragment;
        this.f11747a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12513a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12514a((JSONObject) obj);
    }

    protected void onPreExecute() {
        if (ProfileFragment.h(this.f11748b) != null) {
            ProfileFragment.h(this.f11748b).show();
        }
    }

    /* renamed from: a */
    protected JSONObject m12513a(String... strArr) {
        return ProfileFragment.j(this.f11748b).m11956b(ProfileFragment.i(this.f11748b), this.f11747a);
    }

    /* renamed from: a */
    protected void m12514a(JSONObject jSONObject) {
        if (ProfileFragment.h(this.f11748b) != null) {
            ProfileFragment.h(this.f11748b).dismiss();
        }
        if (jSONObject != null) {
            if (jSONObject.optInt("code") == 0) {
                ProfileFragment.a(this.f11748b, ProfileFragment.k(this.f11748b), jSONObject.optJSONObject(C0882j.f2229c).optString("remarks"));
                C2574s.m12893a().m12895a(new ProfileFragment$a(ProfileFragment.l(this.f11748b), jSONObject.optJSONObject(C0882j.f2229c).optString("remarks")));
                return;
            }
            Toasts.showOnUiThread(this.f11748b.getActivity(), jSONObject.optString(AVStatus.MESSAGE_TAG));
        }
    }
}
