package com.beastbikes.android.modules.train.dto;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SingleTrainCourseDTO */
/* renamed from: com.beastbikes.android.modules.train.dto.a */
public class C2365a {
    /* renamed from: a */
    private int f11235a;
    /* renamed from: b */
    private String f11236b;
    /* renamed from: c */
    private String f11237c;
    /* renamed from: d */
    private ArrayList<CourseDTO> f11238d;

    public C2365a(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.f11235a = jSONObject.optInt("category_id");
            this.f11236b = jSONObject.optString("category_name");
            this.f11237c = jSONObject.optString("en_category_name");
            JSONArray optJSONArray = jSONObject.optJSONArray("courses");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                this.f11238d = new ArrayList();
                for (int i = 0; i < length; i++) {
                    this.f11238d.add(new CourseDTO(optJSONArray.optJSONObject(i)));
                }
            }
        }
    }

    /* renamed from: a */
    public String m12061a() {
        return this.f11236b;
    }

    /* renamed from: b */
    public String m12062b() {
        return this.f11237c;
    }

    /* renamed from: c */
    public ArrayList<CourseDTO> m12063c() {
        return this.f11238d;
    }
}
