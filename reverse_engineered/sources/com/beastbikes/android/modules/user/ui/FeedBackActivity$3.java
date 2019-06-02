package com.beastbikes.android.modules.user.ui;

import android.os.AsyncTask;
import android.text.TextUtils;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1802i;
import com.beastbikes.android.modules.cycling.club.biz.C2060g;
import com.beastbikes.android.modules.user.p077a.C2389c;
import com.beastbikes.framework.android.p088g.C2798a;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.services.directions.v4.DirectionsCriteria;
import java.io.File;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

class FeedBackActivity$3 extends AsyncTask<String, Void, Boolean> {
    /* renamed from: a */
    final /* synthetic */ FeedBackActivity f11619a;

    FeedBackActivity$3(FeedBackActivity feedBackActivity) {
        this.f11619a = feedBackActivity;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12446a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12447a((Boolean) obj);
    }

    protected void onPreExecute() {
        super.onPreExecute();
        this.f11619a.f6379m = new C1802i(this.f11619a, this.f11619a.getString(C1373R.string.feedback_submitting), true);
        this.f11619a.f6379m.show();
    }

    /* renamed from: a */
    protected Boolean m12446a(String... strArr) {
        String str = null;
        try {
            String str2;
            File file;
            FeedBackActivity.b(this.f11619a);
            if (TextUtils.isEmpty(FeedBackActivity.c(this.f11619a))) {
                str2 = str;
            } else {
                str2 = FeedBackActivity.c(this.f11619a).toString().replace("logs/", "");
            }
            JSONObject jSONObject = new JSONObject();
            if (TextUtils.isEmpty(FeedBackActivity.d(this.f11619a))) {
                Object obj = str;
            } else {
                file = new File(FeedBackActivity.d(this.f11619a));
            }
            if (file != null && file.exists()) {
                C2060g c2060g = new C2060g();
                try {
                    str = C2798a.m13750a(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                jSONObject.put(AVStatus.IMAGE_TAG, c2060g.m10617a(file, str, 5).m10618a().getUrl());
            }
            jSONObject.put(DirectionsCriteria.INSTRUCTIONS_TEXT, FeedBackActivity.e(this.f11619a));
            FeedBackActivity.b().info("Feedback log id : " + str2);
            return Boolean.valueOf(new C2389c(this.f11619a).m12130a(FeedBackActivity.a(this.f11619a), strArr[0], FeedBackActivity.f(this.f11619a), jSONObject.toString(), str2));
        } catch (BusinessException e2) {
            return Boolean.valueOf(false);
        } catch (JSONException e3) {
            e3.printStackTrace();
            return Boolean.valueOf(false);
        }
    }

    /* renamed from: a */
    protected void m12447a(Boolean bool) {
        if (this.f11619a.f6379m != null && this.f11619a.f6379m.isShowing()) {
            this.f11619a.f6379m.dismiss();
        }
        if (bool.booleanValue()) {
            Toasts.show(this.f11619a, (int) C1373R.string.feedback_submit_success);
            this.f11619a.finish();
        }
    }
}
