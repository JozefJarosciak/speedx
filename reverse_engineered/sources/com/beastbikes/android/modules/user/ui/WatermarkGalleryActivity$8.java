package com.beastbikes.android.modules.user.ui;

import com.android.volley.Response.Listener;
import com.beastbikes.android.modules.user.dto.C2412b;
import java.util.List;
import org.json.JSONArray;

class WatermarkGalleryActivity$8 implements Listener<JSONArray> {
    /* renamed from: a */
    final /* synthetic */ List f11826a;
    /* renamed from: b */
    final /* synthetic */ WatermarkGalleryActivity f11827b;

    WatermarkGalleryActivity$8(WatermarkGalleryActivity watermarkGalleryActivity, List list) {
        this.f11827b = watermarkGalleryActivity;
        this.f11826a = list;
    }

    public /* synthetic */ void onResponse(Object obj) {
        m12559a((JSONArray) obj);
    }

    /* renamed from: a */
    public void m12559a(JSONArray jSONArray) {
        if (jSONArray != null && jSONArray.length() != 0) {
            for (int i = 0; i < jSONArray.length(); i++) {
                this.f11826a.add(new C2412b(jSONArray.optJSONObject(i)));
            }
            this.f11827b.a(this.f11826a);
        }
    }
}
