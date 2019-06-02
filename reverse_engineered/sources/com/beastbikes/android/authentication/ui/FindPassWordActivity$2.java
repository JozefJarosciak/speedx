package com.beastbikes.android.authentication.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.view.View.OnClickListener;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.json.JSONObject;

class FindPassWordActivity$2 extends AsyncTask<String, Void, JSONObject> {
    /* renamed from: a */
    final /* synthetic */ C1802i f7293a;
    /* renamed from: b */
    final /* synthetic */ String f7294b;
    /* renamed from: c */
    final /* synthetic */ String f7295c;
    /* renamed from: d */
    final /* synthetic */ FindPassWordActivity f7296d;

    FindPassWordActivity$2(FindPassWordActivity findPassWordActivity, C1802i c1802i, String str, String str2) {
        this.f7296d = findPassWordActivity;
        this.f7293a = c1802i;
        this.f7294b = str;
        this.f7295c = str2;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m8527a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m8528a((JSONObject) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.f7293a.show();
    }

    /* renamed from: a */
    protected JSONObject m8527a(String... strArr) {
        return FindPassWordActivity.b(this.f7296d).m8462a(this.f7294b + this.f7295c, "resetPwd");
    }

    /* renamed from: a */
    protected void m8528a(JSONObject jSONObject) {
        this.f7293a.cancel();
        if (jSONObject != null) {
            if (jSONObject.optInt("code") == 1007) {
                final C2621c c2621c = new C2621c(this.f7296d);
                c2621c.m13065b((int) C1373R.string.activity_find_pass_not_account_ms);
                c2621c.m13059a((int) C1373R.string.activity_find_pass_ok_ms, new OnClickListener(this) {
                    /* renamed from: b */
                    final /* synthetic */ FindPassWordActivity$2 f7290b;

                    public void onClick(View view) {
                        Intent intent = this.f7290b.f7296d.getIntent();
                        intent.putExtra("extra_result_redirect", 549);
                        intent.putExtra("extra_result_areacode", this.f7290b.f7294b);
                        intent.putExtra("extra_result_phone", this.f7290b.f7295c);
                        this.f7290b.f7296d.setResult(-1, intent);
                        c2621c.m13069b();
                        this.f7290b.f7296d.finish();
                    }
                });
                c2621c.m13066b((int) C1373R.string.activity_alert_dialog_text_cancel, new OnClickListener(this) {
                    /* renamed from: b */
                    final /* synthetic */ FindPassWordActivity$2 f7292b;

                    public void onClick(View view) {
                        c2621c.m13069b();
                    }
                });
                c2621c.m13063a();
            } else if (jSONObject.optInt("code") == 0) {
                FindPassWordActivity.a(this.f7296d, new FindPassWordActivity$a(this.f7296d, ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD, 1000));
                FindPassWordActivity.c(this.f7296d).start();
            } else {
                Toasts.show(this.f7296d, jSONObject.optString(AVStatus.MESSAGE_TAG));
            }
        }
    }
}
