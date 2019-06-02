package com.beastbikes.android.modules.cycling.sections.p069a;

import android.app.Activity;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.cycling.sections.dto.C2220a;
import com.beastbikes.android.modules.cycling.sections.dto.C2221b;
import com.beastbikes.android.modules.cycling.sections.dto.C2222c;
import com.beastbikes.android.modules.cycling.sections.dto.C2223d;
import com.beastbikes.android.modules.cycling.sections.dto.C2224e;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: SectionManager */
/* renamed from: com.beastbikes.android.modules.cycling.sections.a.a */
public class C2219a extends C1397a implements C1371a {
    /* renamed from: a */
    private C1424b f10486a;
    /* renamed from: b */
    private Activity f10487b;

    public C2219a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        C1772d c1772d = new C1772d(activity);
        this.f10487b = activity;
        this.f10486a = (C1424b) c1772d.m9399a(C1424b.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    /* renamed from: a */
    public List<C2222c> m11402a(double d, double d2, float f, String str, String str2, String str3, String str4, String str5) throws BusinessException {
        try {
            JSONObject a = this.f10486a.a(d, d2, f, str, str2, str3, str4, str5);
            if (a == null) {
                Toasts.showOnUiThread(this.f10487b, this.f10487b.getString(C1373R.string.club_act_release_failure));
                return null;
            } else if (a.optInt("code") == 0) {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    return null;
                }
                List<C2222c> arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(new C2222c(optJSONArray.optJSONObject(i)));
                }
                return arrayList;
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f10487b, optString);
                }
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public C2221b m11401a(long j, float f, float f2) throws BusinessException {
        try {
            JSONObject a = this.f10486a.a(j, f, f2);
            if (a == null) {
                Toasts.showOnUiThread(this.f10487b, this.f10487b.getString(C1373R.string.club_act_release_failure));
                return null;
            } else if (a.optInt("code") == 0) {
                return new C2221b(a.optJSONObject(C0882j.f2229c));
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (TextUtils.isEmpty(optString)) {
                    return null;
                }
                Toasts.showOnUiThread(this.f10487b, optString);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public List<C2223d> m11403a(long j, int i, int i2) throws BusinessException {
        try {
            JSONObject a = this.f10486a.a(j, i, i2);
            if (a == null) {
                Toasts.showOnUiThread(this.f10487b, this.f10487b.getString(C1373R.string.club_act_release_failure));
                return null;
            } else if (a.optInt("code") == 0) {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    return null;
                }
                List<C2223d> arrayList = new ArrayList();
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    arrayList.add(new C2223d(optJSONArray.optJSONObject(i3)));
                }
                return arrayList;
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (TextUtils.isEmpty(optString)) {
                    return null;
                }
                Toasts.showOnUiThread(this.f10487b, optString);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public int m11399a(long j) throws BusinessException {
        try {
            JSONObject a = this.f10486a.a(j);
            if (a == null) {
                Toasts.showOnUiThread(this.f10487b, this.f10487b.getString(C1373R.string.club_act_release_failure));
                return -1;
            } else if (a.optInt("code") == 0) {
                return a.optInt(C0882j.f2229c);
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (TextUtils.isEmpty(optString)) {
                    return -1;
                }
                Toasts.showOnUiThread(this.f10487b, optString);
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* renamed from: a */
    public List<C2224e> m11404a(String str, int i, int i2) throws BusinessException {
        try {
            JSONObject a = this.f10486a.a(str, i, i2);
            if (a == null) {
                Toasts.showOnUiThread(this.f10487b, this.f10487b.getString(C1373R.string.club_act_release_failure));
                return null;
            } else if (a.optInt("code") == 0) {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    return null;
                }
                List<C2224e> arrayList = new ArrayList();
                for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                    arrayList.add(new C2224e(optJSONArray.optJSONObject(i3)));
                }
                return arrayList;
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (TextUtils.isEmpty(optString)) {
                    return null;
                }
                Toasts.showOnUiThread(this.f10487b, optString);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public C2220a m11400a(String str) throws BusinessException {
        try {
            JSONObject a = this.f10486a.a(str);
            if (a == null) {
                Toasts.showOnUiThread(this.f10487b, this.f10487b.getString(C1373R.string.club_act_release_failure));
                return null;
            } else if (a.optInt("code") == 0) {
                JSONArray optJSONArray = a.optJSONObject(C0882j.f2229c).optJSONArray("segmentList");
                if (optJSONArray == null || optJSONArray.length() == 0) {
                    return null;
                }
                List arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(new C2224e(optJSONArray.optJSONObject(i)));
                }
                return new C2220a(a.optBoolean("needWait"), arrayList);
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (TextUtils.isEmpty(optString)) {
                    return null;
                }
                Toasts.showOnUiThread(this.f10487b, optString);
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
