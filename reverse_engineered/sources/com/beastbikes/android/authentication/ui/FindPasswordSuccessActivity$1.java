package com.beastbikes.android.authentication.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.json.JSONObject;

class FindPasswordSuccessActivity$1 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ FindPasswordSuccessActivity f7307a;

    FindPasswordSuccessActivity$1(FindPasswordSuccessActivity findPasswordSuccessActivity) {
        this.f7307a = findPasswordSuccessActivity;
    }

    public void onClick(View view) {
        FindPasswordSuccessActivity.a(this.f7307a).setClickable(false);
        if (TextUtils.isEmpty(FindPasswordSuccessActivity.b(this.f7307a).getText())) {
            Toasts.show(this.f7307a, (int) C1373R.string.str_please_input_email);
            FindPasswordSuccessActivity.a(this.f7307a).setClickable(true);
            return;
        }
        final String charSequence = FindPasswordSuccessActivity.b(this.f7307a).getText().toString();
        final C1802i c1802i = new C1802i(this.f7307a, this.f7307a.getString(C1373R.string.loading_msg), false);
        c1802i.setCancelable(true);
        this.f7307a.getAsyncTaskQueue().m13740a(new AsyncTask<String, Void, JSONObject>(this) {
            /* renamed from: c */
            final /* synthetic */ FindPasswordSuccessActivity$1 f7306c;

            protected /* synthetic */ Object doInBackground(Object[] objArr) {
                return m8531a((String[]) objArr);
            }

            protected /* synthetic */ void onPostExecute(Object obj) {
                m8532a((JSONObject) obj);
            }

            protected void onPreExecute() {
                super.onPreExecute();
                c1802i.show();
            }

            /* renamed from: a */
            protected JSONObject m8531a(String... strArr) {
                return FindPasswordSuccessActivity.c(this.f7306c.f7307a).m8460a(charSequence);
            }

            /* renamed from: a */
            protected void m8532a(JSONObject jSONObject) {
                FindPasswordSuccessActivity.a(this.f7306c.f7307a).setClickable(true);
                c1802i.cancel();
                if (jSONObject == null) {
                    Toasts.show(this.f7306c.f7307a, (int) C1373R.string.authentication_email_not_registered);
                } else if (jSONObject.optInt("code") == 0) {
                    Toasts.show(this.f7306c.f7307a, (int) C1373R.string.authentication_sent_password_reset_request_success);
                } else {
                    Toasts.show(this.f7306c.f7307a, jSONObject.optString(AVStatus.MESSAGE_TAG));
                }
            }
        }, new String[0]);
    }
}
