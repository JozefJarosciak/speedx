package com.beastbikes.android.modules.cycling.activity.biz;

import android.app.Activity;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.activity.dto.GoalConfigDTO;
import com.beastbikes.android.modules.cycling.activity.dto.MyGoalInfoDTO;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: CyclingManager */
/* renamed from: com.beastbikes.android.modules.cycling.activity.biz.c */
public class C1916c extends C1397a {
    /* renamed from: a */
    private static final Logger f8569a = LoggerFactory.getLogger("CyclingManager");
    /* renamed from: b */
    private final Activity f8570b;
    /* renamed from: c */
    private final C1399d f8571c;
    /* renamed from: d */
    private SharedPreferences f8572d;

    public C1916c(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f8570b = activity;
        this.f8571c = (C1399d) new C1772d(activity).m9399a(C1399d.class, C1768c.f8075a, C1768c.m9391a(activity));
        AVUser currentUser = AVUser.getCurrentUser();
        if (currentUser != null) {
            this.f8572d = activity.getSharedPreferences(currentUser.getObjectId(), 0);
        }
    }

    /* renamed from: a */
    public List<GoalConfigDTO> m9900a() {
        try {
            JSONObject a = this.f8571c.a();
            if (a == null) {
                return null;
            }
            if (a.optInt("code") == 0) {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    return null;
                }
                List<GoalConfigDTO> arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    GoalConfigDTO goalConfigDTO = new GoalConfigDTO(optJSONArray.optJSONObject(i));
                    if (this.f8572d != null && this.f8572d.getString("cycling_target_setting", "sa").equals(goalConfigDTO.getKey())) {
                        goalConfigDTO.setChecked(true);
                    }
                    arrayList.add(goalConfigDTO);
                }
                return arrayList;
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString) || this.f8570b == null) {
                return null;
            }
            Toasts.showOnUiThread(this.f8570b, optString);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public MyGoalInfoDTO m9902b() {
        try {
            JSONObject b = this.f8571c.b();
            if (b == null) {
                return null;
            }
            if (b.optInt("code") == 0) {
                JSONObject optJSONObject = b.optJSONObject(C0882j.f2229c);
                if (this.f8572d != null) {
                    f8569a.info("Save my goal to local!");
                    this.f8572d.edit().putString("cycling_my_goal", optJSONObject.toString()).commit();
                } else {
                    f8569a.error("Save my goal to local failed with userSp is null!");
                }
                return new MyGoalInfoDTO(optJSONObject);
            }
            String optString = b.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString) || this.f8570b == null) {
                return null;
            }
            Toasts.showOnUiThread(this.f8570b, optString);
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public boolean m9901a(double d) {
        try {
            JSONObject a = this.f8571c.a(d);
            if (a == null) {
                return false;
            }
            if (a.optInt("code") == 0) {
                return a.optBoolean(C0882j.f2229c);
            }
            String optString = a.optString(AVStatus.MESSAGE_TAG);
            if (TextUtils.isEmpty(optString) || this.f8570b == null) {
                return false;
            }
            Toasts.showOnUiThread(this.f8570b, optString);
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
