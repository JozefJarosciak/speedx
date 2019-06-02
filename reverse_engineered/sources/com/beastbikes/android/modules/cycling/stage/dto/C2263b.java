package com.beastbikes.android.modules.cycling.stage.dto;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: StageRankDataDTO */
/* renamed from: com.beastbikes.android.modules.cycling.stage.dto.b */
public class C2263b {
    /* renamed from: a */
    private ArrayList<StageRankDTO> f10742a;
    /* renamed from: b */
    private boolean f10743b;
    /* renamed from: c */
    private StageRankDTO f10744c;

    public C2263b(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("rank_data");
        if (optJSONArray != null && optJSONArray.length() > 0) {
            this.f10742a = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    this.f10742a.add(new StageRankDTO(optJSONObject));
                }
            }
        }
        this.f10743b = jSONObject.optBoolean("is_join");
        if (this.f10743b) {
            this.f10744c = new StageRankDTO(jSONObject.optJSONObject("self_data"));
        }
    }

    /* renamed from: a */
    public ArrayList<StageRankDTO> m11601a() {
        return this.f10742a;
    }

    /* renamed from: b */
    public boolean m11602b() {
        return this.f10743b;
    }

    /* renamed from: c */
    public StageRankDTO m11603c() {
        return this.f10744c;
    }
}
