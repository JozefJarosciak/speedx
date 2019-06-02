package com.beastbikes.android.modules.shop.p073a;

import android.app.Activity;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.shop.dto.BikeShopInfoDTO;
import com.beastbikes.android.modules.shop.dto.BikeShopListDTO;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: BikeShopManager */
/* renamed from: com.beastbikes.android.modules.shop.a.a */
public class C2327a extends C1397a implements C1371a {
    /* renamed from: a */
    private C1431b f11061a;
    /* renamed from: b */
    private Activity f11062b;

    public C2327a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f11062b = activity;
        this.f11061a = (C1431b) new C1772d(activity).m9399a(C1431b.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    /* renamed from: a */
    public List<BikeShopListDTO> m11890a(double d, double d2, float f, String str, double d3, double d4, String str2) throws BusinessException {
        try {
            double d5;
            double d6;
            float f2;
            double d7;
            double d8;
            if ("mine".equals(str2)) {
                d5 = 0.0d;
                d6 = 0.0d;
                f2 = 0.0f;
                d7 = 0.0d;
                d8 = 0.0d;
            } else {
                d8 = d4;
                d7 = d3;
                f2 = f;
                d6 = d2;
                d5 = d;
            }
            JSONObject a = this.f11061a.a(d5, d6, f2, str, d7, d8, str2);
            if (a == null) {
                Toasts.showOnUiThread(this.f11062b, this.f11062b.getString(C1373R.string.club_act_release_failure));
                return null;
            } else if (a.optInt("code") == 0) {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    return null;
                }
                List<BikeShopListDTO> arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(new BikeShopListDTO(optJSONArray.optJSONObject(i)));
                }
                return arrayList;
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f11062b, optString);
                }
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public BikeShopInfoDTO m11889a(long j, float f, float f2) throws BusinessException {
        try {
            JSONObject a = this.f11061a.a(j, f, f2);
            if (a == null) {
                Toasts.showOnUiThread(this.f11062b, this.f11062b.getString(C1373R.string.club_act_release_failure));
                return null;
            } else if (a.optInt("code") == 0) {
                return new BikeShopInfoDTO(a.optJSONObject(C0882j.f2229c));
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (TextUtils.isEmpty(optString)) {
                    return null;
                }
                Toasts.showOnUiThread(this.f11062b, optString);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public boolean m11892a(long j) throws BusinessException {
        try {
            JSONObject a = this.f11061a.a(j);
            if (a == null) {
                Toasts.showOnUiThread(this.f11062b, this.f11062b.getString(C1373R.string.club_act_release_failure));
                return false;
            } else if (a.optInt("code") == 0) {
                return a.optBoolean(C0882j.f2229c);
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (TextUtils.isEmpty(optString)) {
                    return false;
                }
                Toasts.showOnUiThread(this.f11062b, optString);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public List<BikeShopListDTO> m11891a(double d, double d2, String str, int i, int i2, int i3) {
        JSONObject a;
        if (i == 0) {
            try {
                a = this.f11061a.a(d, d2, str, i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            a = this.f11061a.a(d, d2, str, i, i2, i3);
        }
        if (a == null) {
            return null;
        }
        if (a.has("code") && a.optInt("code", -1) == 0) {
            a = a.optJSONObject(C0882j.f2229c);
            if (a == null) {
                return null;
            }
            JSONArray optJSONArray = a.optJSONArray("shops");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            List<BikeShopListDTO> arrayList = new ArrayList();
            for (int i4 = 0; i4 < optJSONArray.length(); i4++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i4);
                Iterator keys = optJSONObject.keys();
                while (keys.hasNext()) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray((String) keys.next());
                    if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                        for (int i5 = 0; i5 < optJSONArray2.length(); i5++) {
                            JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i5);
                            if (optJSONObject2 != null) {
                                arrayList.add(new BikeShopListDTO(optJSONObject2));
                            }
                        }
                    }
                }
            }
            return arrayList;
        }
        return null;
    }
}
