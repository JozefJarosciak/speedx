package com.beastbikes.android.modules.cycling.activity.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.modules.cycling.activity.dto.PreviewDto;
import org.json.JSONObject;

class CyclingSettingPageActivity$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ CyclingSettingPageActivity f8665a;

    CyclingSettingPageActivity$2(CyclingSettingPageActivity cyclingSettingPageActivity) {
        this.f8665a = cyclingSettingPageActivity;
    }

    public void onClick(View view) {
        CyclingSettingPageActivity.b(this.f8665a).m13069b();
        if (CyclingSettingPageActivity.f4639a != null && CyclingSettingPageActivity.f4639a.size() > 0) {
            JSONObject jSONObject = new JSONObject();
            for (int i = 0; i < CyclingSettingPageActivity.f4639a.size(); i++) {
                try {
                    jSONObject.put(String.valueOf(i), CyclingSettingPageActivity.f4639a.get(i));
                } catch (Exception e) {
                    CyclingSettingPageActivity.a().error("Cycling data add data error, " + e);
                }
            }
            CyclingSettingPageActivity.c(this.f8665a).add(new PreviewDto(this.f8665a.getApplicationContext(), jSONObject));
            CyclingSettingPageActivity.d(this.f8665a).notifyDataSetChanged();
        }
    }
}
