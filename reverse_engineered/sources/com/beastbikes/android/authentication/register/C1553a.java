package com.beastbikes.android.authentication.register;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.authentication.C1530e;
import com.beastbikes.android.authentication.C1542d;
import com.beastbikes.android.modules.user.ui.binding.p159a.C2510a;
import org.json.JSONObject;

/* compiled from: RegisterPresenter */
/* renamed from: com.beastbikes.android.authentication.register.a */
class C1553a extends C1542d {
    C1553a(C1530e c1530e) {
        super(c1530e);
    }

    /* renamed from: c */
    void mo3124c() {
        int i = this.a.mo3115i();
        String e = this.a.mo3111e();
        if (TextUtils.isEmpty(e)) {
            this.a.mo3109c(this.b.getString(i == 2 ? C1373R.string.str_please_input_phone_number : C1373R.string.str_please_input_email));
        } else if (i == 2) {
            m8492a(this.a.mo3114h() + e, "regPhone");
        } else if (i == 1) {
            m8512a(e, i);
        }
    }

    /* renamed from: a */
    private void m8512a(final String str, final int i) {
        if (C2510a.m12614c(str)) {
            this.a.mo3118l();
            this.b.getAsyncTaskQueue().m13740a(new AsyncTask<Object, Object, JSONObject>(this) {
                /* renamed from: c */
                final /* synthetic */ C1553a f7265c;

                protected /* synthetic */ Object doInBackground(Object[] objArr) {
                    return m8509a(objArr);
                }

                protected /* synthetic */ void onPostExecute(Object obj) {
                    m8510a((JSONObject) obj);
                }

                /* renamed from: a */
                protected JSONObject m8509a(Object... objArr) {
                    return this.f7265c.c.m8461a(str, i);
                }

                /* renamed from: a */
                protected void m8510a(JSONObject jSONObject) {
                    this.f7265c.a.mo3119m();
                    if (jSONObject != null) {
                        if (jSONObject.optInt("code", -1) == 0) {
                            this.f7265c.a.mo3117k();
                            return;
                        }
                        CharSequence optString = jSONObject.optString(AVStatus.MESSAGE_TAG);
                        if (!TextUtils.isEmpty(optString)) {
                            this.f7265c.a.mo3109c(optString);
                        }
                    }
                }
            }, new Object[0]);
            return;
        }
        this.a.mo3109c(this.b.getString(C1373R.string.msg_email_format_error));
    }
}
