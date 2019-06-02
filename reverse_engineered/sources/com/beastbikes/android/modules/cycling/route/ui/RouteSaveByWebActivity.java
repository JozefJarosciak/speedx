package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.modules.cycling.route.p068a.C2185a;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.BaseActivity;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.json.JSONObject;

public class RouteSaveByWebActivity extends BaseActivity {
    /* renamed from: a */
    private C2185a f10333a;

    /* renamed from: com.beastbikes.android.modules.cycling.route.ui.RouteSaveByWebActivity$1 */
    class C21971 extends AsyncTask<String, Void, JSONObject> {
        /* renamed from: a */
        final /* synthetic */ RouteSaveByWebActivity f10332a;

        C21971(RouteSaveByWebActivity routeSaveByWebActivity) {
            this.f10332a = routeSaveByWebActivity;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return m11257a((String[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            m11258a((JSONObject) obj);
        }

        /* renamed from: a */
        protected JSONObject m11257a(String... strArr) {
            try {
                return this.f10332a.f10333a.m11206f(strArr[0]);
            } catch (BusinessException e) {
                return null;
            }
        }

        /* renamed from: a */
        protected void m11258a(JSONObject jSONObject) {
            if (jSONObject == null) {
                Toasts.show(this.f10332a.getApplicationContext(), (CharSequence) "保存失败");
                this.f10332a.finish();
                return;
            }
            if (jSONObject.has("code") && jSONObject.optInt("code") == 0) {
                Toasts.show(this.f10332a.getApplicationContext(), jSONObject.optString(AVStatus.MESSAGE_TAG));
            } else {
                Toasts.show(this.f10332a.getApplicationContext(), jSONObject.optString(AVStatus.MESSAGE_TAG));
            }
            this.f10332a.finish();
        }
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Intent intent = getIntent();
        if (intent == null) {
            finish();
            return;
        }
        String stringExtra = intent.getStringExtra("route_id");
        if (TextUtils.isEmpty(stringExtra)) {
            finish();
            return;
        }
        this.f10333a = new C2185a(this);
        m11260a(stringExtra);
    }

    /* renamed from: a */
    private void m11260a(String str) {
        getAsyncTaskQueue().m13740a(new C21971(this), str);
    }
}
