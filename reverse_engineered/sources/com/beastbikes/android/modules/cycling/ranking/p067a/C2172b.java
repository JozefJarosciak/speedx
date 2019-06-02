package com.beastbikes.android.modules.cycling.ranking.p067a;

import android.app.Activity;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.cycling.ranking.dto.RankDTO;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: RankingManager */
/* renamed from: com.beastbikes.android.modules.cycling.ranking.a.b */
public class C2172b extends C1397a {
    /* renamed from: a */
    private C1415a f10164a;
    /* renamed from: b */
    private Activity f10165b;

    public C2172b(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f10165b = activity;
        this.f10164a = (C1415a) new C1772d(activity).m9399a(C1415a.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    /* renamed from: a */
    public List<RankDTO> m11133a(int i, String str, int i2, int i3) throws BusinessException {
        try {
            JSONObject a = this.f10164a.a(i, str, i2, i3);
            if (a == null) {
                return null;
            }
            if (a.optInt("code") == 0) {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                int length = optJSONArray.length();
                List<RankDTO> arrayList = new ArrayList();
                int i4 = 1;
                for (int i5 = 0; i5 < length; i5++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i5);
                    if (optJSONObject.has("userId")) {
                        RankDTO rankDTO = new RankDTO(optJSONObject);
                        rankDTO.setRankType(i);
                        rankDTO.setOrdinal(i4);
                        arrayList.add(rankDTO);
                        i4++;
                    }
                }
                return arrayList;
            }
            String optString = a.optString(C0882j.f2229c);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f10165b, optString);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public RankDTO m11132a(int i, String str) throws BusinessException {
        RankDTO rankDTO = null;
        try {
            JSONObject a = this.f10164a.a(i, str);
            if (a != null) {
                if (a.optInt("code") == 0) {
                    rankDTO = new RankDTO(a.optJSONObject(C0882j.f2229c));
                } else {
                    String optString = a.optString(AVStatus.MESSAGE_TAG);
                    if (!TextUtils.isEmpty(AVStatus.MESSAGE_TAG)) {
                        Toasts.showOnUiThread(this.f10165b, optString);
                    }
                }
            }
            return rankDTO;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public JSONObject m11134a(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "北京";
        }
        try {
            return this.f10164a.a(str);
        } catch (Exception e) {
            return null;
        }
    }
}
