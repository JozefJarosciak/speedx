package com.beastbikes.android.modules.train.ui;

import android.os.AsyncTask;
import org.json.JSONObject;

class TrainDefaultFragment$3 extends AsyncTask<String, Void, JSONObject> {
    /* renamed from: a */
    final /* synthetic */ TrainDefaultFragment f11262a;

    TrainDefaultFragment$3(TrainDefaultFragment trainDefaultFragment) {
        this.f11262a = trainDefaultFragment;
    }

    protected /* synthetic */ Object doInBackground(Object[] objArr) {
        return m12075a((String[]) objArr);
    }

    protected /* synthetic */ void onPostExecute(Object obj) {
        m12076a((JSONObject) obj);
    }

    /* renamed from: a */
    protected JSONObject m12075a(String... strArr) {
        return TrainDefaultFragment.a(this.f11262a).m12002a();
    }

    /* renamed from: a */
    protected void m12076a(JSONObject jSONObject) {
        if (jSONObject != null && jSONObject.optInt("code") == 0) {
            TrainDefaultFragment.a(this.f11262a, jSONObject);
        }
    }
}
