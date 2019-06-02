package com.beastbikes.android.modules.user.p077a;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.user.dto.SearchRegionDTO;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RegionManager */
/* renamed from: com.beastbikes.android.modules.user.a.a */
public class C2387a extends C1397a {
    /* renamed from: a */
    private C1440b f11332a;

    public C2387a(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f11332a = (C1440b) new C1772d(context).m9399a(C1440b.class, C1768c.f8075a, C1768c.m9391a(context));
    }

    /* renamed from: a */
    public ArrayList<SearchRegionDTO> m12116a(String str, String str2) {
        ArrayList<SearchRegionDTO> arrayList = null;
        if (!TextUtils.isEmpty(str)) {
            JSONObject a = this.f11332a.a(str, str2, "5000");
            if (a != null && a.has("code") && a.optInt("code") == 0) {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    int length = optJSONArray.length();
                    arrayList = new ArrayList();
                    for (int i = 0; i < length; i++) {
                        arrayList.add(new SearchRegionDTO(optJSONArray.optJSONObject(i)));
                    }
                }
            }
        }
        return arrayList;
    }
}
