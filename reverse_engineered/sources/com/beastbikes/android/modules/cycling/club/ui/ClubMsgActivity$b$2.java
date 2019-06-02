package com.beastbikes.android.modules.cycling.club.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1592b;
import com.beastbikes.android.modules.cycling.club.ui.ClubMsgActivity.C1410b;
import org.json.JSONObject;

class ClubMsgActivity$b$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ JSONObject f9780a;
    /* renamed from: b */
    final /* synthetic */ C1410b f9781b;

    ClubMsgActivity$b$2(C1410b c1410b, JSONObject jSONObject) {
        this.f9781b = c1410b;
        this.f9780a = jSONObject;
    }

    public void onClick(View view) {
        if (this.f9780a.has("page")) {
            Intent b = C1592b.m8559a().m8562b(this.f9780a, view.getContext());
            if (!b.getBooleanExtra("extra_activity_null", false)) {
                try {
                    this.f9781b.f5245a.startActivity(b);
                } catch (Exception e) {
                }
            }
        }
    }
}
