package com.beastbikes.android.modules.cycling.stage.p070a;

import android.app.Activity;
import com.alipay.sdk.util.C0882j;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.cycling.stage.dto.C2262a;
import com.beastbikes.android.modules.cycling.stage.dto.C2263b;
import com.beastbikes.android.modules.cycling.stage.dto.C2264c;
import com.beastbikes.android.modules.cycling.stage.dto.StageDTO;
import com.beastbikes.android.utils.C2557f;
import com.beastbikes.android.utils.C2578u;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: StageManager */
/* renamed from: com.beastbikes.android.modules.cycling.stage.a.a */
public class C2236a extends C1397a {
    /* renamed from: a */
    private static final Logger f10669a = LoggerFactory.getLogger(C2236a.class);
    /* renamed from: b */
    private C1426b f10670b;
    /* renamed from: c */
    private Activity f10671c;

    public C2236a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f10671c = activity;
        this.f10670b = (C1426b) new C1772d(activity).m9399a(C1426b.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    /* renamed from: a */
    public ArrayList<StageDTO> m11502a(String str, int i) {
        try {
            JSONObject a = this.f10670b.a(str, i);
            if (a == null || !a.has("code") || a.optInt("code", -1) != 0) {
                return null;
            }
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            ArrayList<StageDTO> arrayList = new ArrayList();
            for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                if (optJSONObject != null) {
                    arrayList.add(new StageDTO(optJSONObject));
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public StageDTO m11499a(long j) {
        try {
            JSONObject a = this.f10670b.a(j);
            if (a == null || !a.has("code") || a.optInt("code", -1) != 0) {
                return null;
            }
            JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
            if (optJSONObject != null) {
                return new StageDTO(optJSONObject);
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public StageDTO m11506b(long j) {
        byte[] a = C2557f.m12836a(C2557f.m12829a(this.f10671c, "use_stage_cache", String.valueOf(j)));
        if (a == null) {
            return null;
        }
        try {
            StageDTO stageDTO = (StageDTO) C2578u.m12902a(a);
            if (stageDTO != null) {
                return stageDTO;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: a */
    public void m11505a(StageDTO stageDTO) {
        try {
            C2557f.m12835a(C2578u.m12903a((Serializable) stageDTO), C2557f.m12829a(this.f10671c, "use_stage_cache", String.valueOf(stageDTO.getStageId())));
        } catch (Exception e) {
            f10669a.error("use stage save cache is error,  e=" + e.getMessage());
        }
    }

    /* renamed from: a */
    public List<StageDTO> m11504a() {
        try {
            JSONObject a = this.f10670b.a();
            if (a == null || !a.has("code") || a.optInt("code", -1) != 0) {
                return null;
            }
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            List<StageDTO> arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    arrayList.add(new StageDTO(optJSONObject));
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public C2263b m11500a(int i, int i2) {
        try {
            JSONObject a = this.f10670b.a(i, i2);
            if (a != null && a.has("code") && a.optInt("code", -1) == 0) {
                return new C2263b(a.optJSONObject(C0882j.f2229c));
            }
        } catch (Exception e) {
            e.printStackTrace();
            f10669a.error("getStageRankById error e: " + e);
        }
        return null;
    }

    /* renamed from: a */
    public ArrayList<C2264c> m11503a(String str, String str2) {
        try {
            JSONObject a = this.f10670b.a(str, str2);
            if (a != null && a.has("code") && a.optInt("code", -1) == 0) {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    return null;
                }
                ArrayList<C2264c> arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        arrayList.add(new C2264c(optJSONObject));
                    }
                }
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            f10669a.error("getStageRankById error e: " + e);
        }
        return null;
    }

    /* renamed from: a */
    public ArrayList<C2262a> m11501a(int i, String str) {
        try {
            JSONObject a = this.f10670b.a(i, str);
            if (a != null && a.has("code") && a.optInt("code", -1) == 0) {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    return null;
                }
                ArrayList<C2262a> arrayList = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        arrayList.add(new C2262a(optJSONObject));
                    }
                }
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            f10669a.error("getProfileScoreList error e: " + e);
        }
        return null;
    }

    /* renamed from: a */
    public int m11498a(int i, boolean z) {
        JSONObject b;
        if (z) {
            b = this.f10670b.b(i);
        } else {
            b = this.f10670b.a(i);
        }
        if (b != null) {
            try {
                if (b.has("code") && b.optInt("code", -1) == 0) {
                    if (z) {
                        return -1;
                    }
                    JSONObject optJSONObject = b.optJSONObject(C0882j.f2229c);
                    if (optJSONObject != null) {
                        return optJSONObject.optInt("collect_id");
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
