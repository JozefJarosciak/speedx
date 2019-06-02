package com.beastbikes.android.authentication.ui;

import android.content.Intent;
import android.os.AsyncTask;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.json.JSONObject;

class FindPassWordActivity$3 extends AsyncTask<String, Void, JSONObject> {
    /* renamed from: a */
    final /* synthetic */ C1802i f7297a;
    /* renamed from: b */
    final /* synthetic */ String f7298b;
    /* renamed from: c */
    final /* synthetic */ String f7299c;
    /* renamed from: d */
    final /* synthetic */ String f7300d;
    /* renamed from: e */
    final /* synthetic */ String f7301e;
    /* renamed from: f */
    final /* synthetic */ FindPassWordActivity f7302f;

    FindPassWordActivity$3(FindPassWordActivity findPassWordActivity, C1802i c1802i, String str, String str2, String str3, String str4) {
        this.f7302f = findPassWordActivity;
        this.f7297a = c1802i;
        this.f7298b = str;
        this.f7299c = str2;
        this.f7300d = str3;
        this.f7301e = str4;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m8529a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m8530a((JSONObject) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        FindPassWordActivity.d(this.f7302f).setClickable(false);
        this.f7297a.show();
    }

    /* renamed from: a */
    protected JSONObject m8529a(String... strArr) {
        return FindPassWordActivity.b(this.f7302f).m8463a(this.f7298b + this.f7299c, this.f7300d, this.f7301e);
    }

    /* renamed from: a */
    protected void m8530a(JSONObject jSONObject) {
        FindPassWordActivity.d(this.f7302f).setClickable(true);
        this.f7297a.cancel();
        if (jSONObject != null) {
            if (jSONObject.optInt("code") == 0) {
                Intent intent = this.f7302f.getIntent();
                intent.putExtra("extra_result_redirect", 549);
                intent.putExtra("extra_result_phone", this.f7299c);
                this.f7302f.setResult(-1, intent);
                this.f7302f.finish();
                return;
            }
            Toasts.show(this.f7302f, jSONObject.optString(AVStatus.MESSAGE_TAG));
        }
    }
}
