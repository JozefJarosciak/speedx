package com.beastbikes.android.authentication.ui;

import android.content.Intent;
import android.os.AsyncTask;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.json.JSONObject;

class FindPassWordActivity$1 extends AsyncTask<String, Void, JSONObject> {
    /* renamed from: a */
    final /* synthetic */ C1802i f7286a;
    /* renamed from: b */
    final /* synthetic */ String f7287b;
    /* renamed from: c */
    final /* synthetic */ FindPassWordActivity f7288c;

    FindPassWordActivity$1(FindPassWordActivity findPassWordActivity, C1802i c1802i, String str) {
        this.f7288c = findPassWordActivity;
        this.f7286a = c1802i;
        this.f7287b = str;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m8525a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m8526a((JSONObject) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        FindPassWordActivity.a(this.f7288c).setClickable(false);
        this.f7286a.show();
    }

    /* renamed from: a */
    protected JSONObject m8525a(String... strArr) {
        return FindPassWordActivity.b(this.f7288c).m8460a(this.f7287b);
    }

    /* renamed from: a */
    protected void m8526a(JSONObject jSONObject) {
        FindPassWordActivity.a(this.f7288c).setClickable(true);
        this.f7286a.cancel();
        if (jSONObject == null) {
            Toasts.show(this.f7288c, (int) C1373R.string.authentication_email_not_registered);
        } else if (jSONObject.optInt("code") == 0) {
            Intent intent = new Intent(this.f7288c, FindPasswordSuccessActivity.class);
            intent.putExtra(FindPasswordSuccessActivity.f4103a, this.f7287b);
            this.f7288c.startActivityForResult(intent, 292);
        } else {
            Toasts.show(this.f7288c, jSONObject.optString(AVStatus.MESSAGE_TAG));
        }
    }
}
