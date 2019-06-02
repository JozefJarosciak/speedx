package com.beastbikes.android.modules.cycling.club.biz;

import android.app.Activity;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1371a;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.cycling.club.dto.C2062a;
import com.beastbikes.android.modules.cycling.club.dto.C2063b;
import com.beastbikes.android.modules.cycling.club.dto.C2064c;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityInfo;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityListDTO;
import com.beastbikes.android.modules.cycling.club.dto.ClubActivityUser;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: ClubActivityManager */
/* renamed from: com.beastbikes.android.modules.cycling.club.biz.a */
public class C2049a extends C1397a implements C1371a {
    /* renamed from: a */
    private C1402b f9335a;
    /* renamed from: b */
    private Activity f9336b;

    public C2049a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        C1772d c1772d = new C1772d(activity);
        this.f9336b = activity;
        this.f9335a = (C1402b) c1772d.m9399a(C1402b.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    /* renamed from: a */
    public ClubActivityInfo m10559a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, int i, int i2, String str11, String str12) {
        try {
            JSONObject a = this.f9335a.a(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, i, i2, str11, str12);
            if (a == null) {
                Toasts.showOnUiThread(this.f9336b, this.f9336b.getString(C1373R.string.club_act_release_failure));
                return null;
            } else if (a.optInt("code") == 0) {
                return new ClubActivityInfo(a.optJSONObject(C0882j.f2229c));
            } else {
                String optString = a.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f9336b, optString);
                }
                return null;
            }
        } catch (Exception e) {
        }
    }

    /* renamed from: a */
    public List<ClubActivityListDTO> m10561a(String str, int i, int i2) {
        JSONObject a = this.f9335a.a(str, i, i2);
        if (a == null) {
            Toasts.showOnUiThread(this.f9336b, this.f9336b.getString(C1373R.string.club_act_list_failure));
            return null;
        } else if (a.optInt("code") == 0) {
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            List<ClubActivityListDTO> arrayList = new ArrayList();
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                arrayList.add(new ClubActivityListDTO(optJSONArray.optJSONObject(i3)));
            }
            return arrayList;
        } else {
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            Toasts.showOnUiThread(this.f9336b, optString);
            return null;
        }
    }

    /* renamed from: b */
    public C2063b m10562b(String str, int i, int i2) {
        JSONObject b = this.f9335a.b(str, i, i2);
        if (b == null) {
            Toasts.showOnUiThread(this.f9336b, this.f9336b.getString(C1373R.string.club_act_memberlist_failure));
            return null;
        } else if (b.optInt("code") == 0) {
            C2063b c2063b = new C2063b();
            b = b.optJSONObject(C0882j.f2229c);
            JSONObject optJSONObject = b.optJSONObject("originator");
            JSONArray optJSONArray = b.optJSONArray("members");
            List arrayList = new ArrayList();
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                arrayList.add(new ClubActivityUser(optJSONArray.optJSONObject(i3)));
            }
            c2063b.m10628a(b.optString("actId"));
            c2063b.m10627a(new C2064c(optJSONObject));
            c2063b.m10630a(b.optBoolean("isManager"));
            c2063b.m10629a(arrayList);
            c2063b.m10626a(b.optInt("count"));
            return c2063b;
        } else {
            String optString = b.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            Toasts.showOnUiThread(this.f9336b, optString);
            return null;
        }
    }

    /* renamed from: a */
    public ClubActivityInfo m10558a(String str) {
        JSONObject a = this.f9335a.a(str);
        if (a == null) {
            Toasts.showOnUiThread(this.f9336b, this.f9336b.getString(C1373R.string.club_act_info_failure));
            return null;
        } else if (a.optInt("code") == 0) {
            return new ClubActivityInfo(a.optJSONObject(C0882j.f2229c));
        } else {
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            Toasts.showOnUiThread(this.f9336b, optString);
            return null;
        }
    }

    /* renamed from: b */
    public boolean m10563b(String str) {
        JSONObject b = this.f9335a.b(str);
        if (b == null) {
            Toasts.showOnUiThread(this.f9336b, this.f9336b.getString(C1373R.string.club_act_cancel_failure));
            return false;
        }
        String optString = b.optString(AVStatus.MESSAGE_TAG);
        if (!TextUtils.isEmpty(optString)) {
            Toasts.showOnUiThread(this.f9336b, optString);
        }
        if (b.optInt("code") == 0) {
            return b.optBoolean(C0882j.f2229c);
        }
        return false;
    }

    /* renamed from: c */
    public C2062a m10564c(String str) {
        JSONObject c = this.f9335a.c(str);
        if (c == null) {
            Toasts.showOnUiThread(this.f9336b, this.f9336b.getString(C1373R.string.club_act_cancel_failure));
            return null;
        } else if (c.optInt("code") == 0) {
            c = c.optJSONObject(C0882j.f2229c);
            C2062a c2062a = new C2062a();
            c2062a.m10624b(c.optInt("read_count"));
            c2062a.m10622a(c.optInt("like_count"));
            return c2062a;
        } else {
            String optString = c.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return null;
            }
            Toasts.showOnUiThread(this.f9336b, optString);
            return null;
        }
    }

    /* renamed from: a */
    public ClubActivityInfo m10560a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, int i, int i2, String str12, String str13) {
        JSONObject a = this.f9335a.a(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, str11, i, i2, str12, str13);
        if (a == null) {
            Toasts.showOnUiThread(this.f9336b, this.f9336b.getString(C1373R.string.club_act_cancel_failure));
            return null;
        } else if (a.optInt("code") == 0) {
            return new ClubActivityInfo(a.optJSONObject(C0882j.f2229c));
        } else {
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f9336b, optString);
            }
            return null;
        }
    }

    /* renamed from: d */
    public boolean m10565d(String str) {
        JSONObject d = this.f9335a.d(str);
        if (d == null) {
            Toasts.showOnUiThread(this.f9336b, this.f9336b.getString(C1373R.string.club_act_cancel_failure));
            return false;
        } else if (d.optInt("code") == 0) {
            Toasts.showOnUiThread(this.f9336b, this.f9336b.getResources().getString(C1373R.string.send_message_successfully));
            return true;
        } else {
            String optString = d.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return false;
            }
            Toasts.showOnUiThread(this.f9336b, optString);
            return false;
        }
    }

    /* renamed from: a */
    public int m10557a(String str, String str2) {
        JSONObject a = this.f9335a.a(str, str2);
        if (a == null) {
            Toasts.showOnUiThread(this.f9336b, this.f9336b.getString(C1373R.string.club_act_cancel_failure));
            return -1;
        } else if (a.optInt("code") == 0) {
            int optInt = a.optInt(C0882j.f2229c);
            Toasts.showOnUiThread(this.f9336b, this.f9336b.getResources().getString(C1373R.string.sign_in_success));
            return optInt;
        } else {
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString)) {
                return -1;
            }
            Toasts.showOnUiThread(this.f9336b, optString);
            return -1;
        }
    }
}
