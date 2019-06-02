package com.beastbikes.android.modules.cycling.ranking.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.authentication.ui.C1563a;
import com.beastbikes.android.modules.cycling.ranking.p067a.C2172b;
import com.beastbikes.android.utils.C2574s;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.json.JSONObject;

class RankFragment$2 extends AsyncTask<Void, Void, JSONObject> {
    /* renamed from: a */
    final /* synthetic */ String f10193a;
    /* renamed from: b */
    final /* synthetic */ RankFragment f10194b;

    RankFragment$2(RankFragment rankFragment, String str) {
        this.f10194b = rankFragment;
        this.f10193a = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m11156a((Void[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m11157a((JSONObject) obj);
    }

    /* renamed from: a */
    protected JSONObject m11156a(Void... voidArr) {
        return new C2172b(this.f10194b.getActivity()).m11134a(this.f10193a);
    }

    /* renamed from: a */
    protected void m11157a(JSONObject jSONObject) {
        if (jSONObject == null) {
            RankFragment.b(this.f10194b, "CN.22.2038349");
        } else if (jSONObject.has("code") && jSONObject.optInt("code") == 0) {
            RankFragment.b(this.f10194b, jSONObject.optString(C0882j.f2229c));
        } else {
            Toasts.show(this.f10194b.getActivity(), jSONObject.optString(AVStatus.MESSAGE_TAG));
            RankFragment.b(this.f10194b, "CN.22.2038349");
        }
        if (TextUtils.isEmpty(RankFragment.c(this.f10194b))) {
            RankFragment.b(this.f10194b, "CN.22.2038349");
        }
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            currentUser.setGeoCode(RankFragment.c(this.f10194b));
            AVUser.saveCurrentUser(currentUser);
        }
        String str = "";
        String[] split = RankFragment.c(this.f10194b).split("\\.");
        if (RankFragment.d(this.f10194b) == 1 && split.length > 0) {
            str = RankFragment.c(this.f10194b).split("\\.")[0];
        } else if (RankFragment.d(this.f10194b) == 2 && split.length > 1) {
            str = RankFragment.c(this.f10194b).split("\\.")[0] + "." + RankFragment.c(this.f10194b).split("\\.")[1];
        }
        C2574s.m12893a().m12895a(new C1563a(str));
    }
}
