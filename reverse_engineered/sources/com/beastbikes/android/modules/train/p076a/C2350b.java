package com.beastbikes.android.modules.train.p076a;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.train.dto.C2365a;
import com.beastbikes.android.modules.train.dto.CalendarDto;
import com.beastbikes.android.modules.train.dto.TrainCourseDTO;
import com.beastbikes.android.modules.train.dto.TrainInfoDTO;
import com.beastbikes.android.modules.train.dto.TrainResultDTO;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: TrainCourseManager */
/* renamed from: com.beastbikes.android.modules.train.a.b */
public class C2350b extends C1397a {
    /* renamed from: a */
    private static Logger f11202a = LoggerFactory.getLogger(C2350b.class);
    /* renamed from: b */
    private C1438c f11203b;
    /* renamed from: c */
    private Context f11204c;

    public C2350b(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f11204c = context;
        this.f11203b = (C1438c) new C1772d(context).m9399a(C1438c.class, C1768c.f8075a, C1768c.m9391a(context));
    }

    /* renamed from: a */
    public JSONObject m12002a() {
        try {
            return this.f11203b.a();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public HashMap<String, CalendarDto> m12001a(int i, int i2, int i3) {
        try {
            JSONObject a = this.f11203b.a(i, i2, i3);
            if (a == null) {
                f11202a.error("getCalendars(), result is null");
                return null;
            }
            if (a.optInt("code", -1) == 0) {
                JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
                if (optJSONObject == null) {
                    f11202a.error("getCalendars(), result json is null");
                    return null;
                }
                Iterator keys = optJSONObject.keys();
                if (keys != null) {
                    HashMap<String, CalendarDto> hashMap = new HashMap();
                    while (keys.hasNext()) {
                        String str = (String) keys.next();
                        hashMap.put(str, new CalendarDto(optJSONObject.optJSONObject(str)));
                    }
                    return hashMap;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public TrainResultDTO m12004b(int i, int i2, int i3) {
        try {
            JSONObject a = this.f11203b.a(i, i2, i3, 2);
            if (a == null) {
                f11202a.error("createTrainPlan(), result json is null");
                return null;
            } else if (a.optInt("code", -1) != 0) {
                return null;
            } else {
                JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
                if (optJSONObject != null) {
                    return new TrainResultDTO(optJSONObject);
                }
                f11202a.error("createTrainPlan(), get Result is null, Result = " + a.toString());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public TrainResultDTO m12000a(int i) {
        try {
            JSONObject a = this.f11203b.a(1, i);
            if (a == null) {
                f11202a.error("createTrainPlan(), result json is null");
                return null;
            } else if (a.optInt("code", -1) != 0) {
                return null;
            } else {
                JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
                if (optJSONObject != null) {
                    return new TrainResultDTO(optJSONObject);
                }
                f11202a.error("createTrainPlan(), get Result is null, Result = " + a.toString());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public ArrayList<C2365a> m12005b() {
        try {
            JSONObject b = this.f11203b.b();
            if (b == null) {
                f11202a.error("getSingleTrainCourseList(), result json is null");
                return null;
            } else if (b.optInt("code", -1) != 0) {
                return null;
            } else {
                JSONArray optJSONArray = b.optJSONArray(C0882j.f2229c);
                if (optJSONArray == null) {
                    f11202a.error("getSingleTrainCourseList(), get Result is null, Result = " + b.toString());
                    return null;
                }
                int length = optJSONArray.length();
                ArrayList<C2365a> arrayList = new ArrayList();
                for (int i = 0; i < length; i++) {
                    arrayList.add(new C2365a(optJSONArray.optJSONObject(i)));
                }
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public TrainCourseDTO m12003b(int i) {
        try {
            JSONObject a = this.f11203b.a(i);
            if (a == null) {
                f11202a.error("getCourseInfo(), result json is null");
                return null;
            }
            CharSequence optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.show(this.f11204c, optString);
            }
            if (a.optInt("code", -1) != 0) {
                return null;
            }
            JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
            if (optJSONObject != null) {
                return new TrainCourseDTO(optJSONObject);
            }
            f11202a.error("getCourseInfo(), get Result is null, Result = " + a.toString());
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public TrainInfoDTO m12006c(int i) {
        try {
            JSONObject b = this.f11203b.b(i);
            if (b == null) {
                f11202a.error("getLongTrainInfo(), result json is null");
                return null;
            } else if (b.optInt("code", -1) != 0) {
                return null;
            } else {
                JSONObject optJSONObject = b.optJSONObject(C0882j.f2229c);
                if (optJSONObject != null) {
                    return new TrainInfoDTO(optJSONObject);
                }
                f11202a.error("getLongTrainInfo(), get Result is null, Result = " + b.toString());
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: d */
    public boolean m12007d(int i) {
        boolean z = false;
        try {
            JSONObject c = this.f11203b.c(i);
            if (c == null) {
                f11202a.error("finish(), result json is null");
            } else if (c.optInt("code", -1) == 0) {
                z = c.optBoolean(C0882j.f2229c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return z;
    }
}
