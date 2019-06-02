package com.beastbikes.android.modules.train.p076a;

import android.content.Context;
import com.alipay.sdk.util.C0882j;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.modules.train.dto.FtpDTO;
import com.beastbikes.android.modules.train.dto.TrainTargetDto;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.mapbox.mapboxsdk.style.layers.Property;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: TrainManager */
/* renamed from: com.beastbikes.android.modules.train.a.d */
public class C2351d extends C1397a {
    /* renamed from: a */
    private static final Logger f11205a = LoggerFactory.getLogger(C2351d.class);
    /* renamed from: b */
    private C1439e f11206b;

    public C2351d(Context context) {
        super((C1372b) context.getApplicationContext());
        this.f11206b = (C1439e) new C1772d(context).m9399a(C1439e.class, C1768c.f8075a, C1768c.m9391a(context));
    }

    /* renamed from: a */
    public TrainTargetDto m12009a() {
        try {
            JSONObject b = this.f11206b.b();
            if (b == null) {
                f11205a.error("getUserTrain(), result json is null");
                return null;
            }
            f11205a.info("getUserTrain(), json = " + b.toString());
            if (b.optInt("code") != 0) {
                f11205a.error("getUserTrain(), result json code is not 0");
                return null;
            }
            JSONObject optJSONObject = b.optJSONObject(C0882j.f2229c);
            if (optJSONObject == null) {
                f11205a.error("getUserTrain(), result json obj is null");
                return null;
            }
            TrainTargetDto trainTargetDto = new TrainTargetDto();
            trainTargetDto.setHeight(optJSONObject.optDouble(Property.ICON_TEXT_FIT_HEIGHT));
            trainTargetDto.setWeight(optJSONObject.optDouble("weight"));
            trainTargetDto.setSex(Integer.parseInt(optJSONObject.optString("sex")));
            trainTargetDto.setHeartRate(optJSONObject.optInt("cardiac_rate"));
            trainTargetDto.setBirthDayDate(optJSONObject.optLong("birthday"));
            return trainTargetDto;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public FtpDTO m12008a(int i, int i2, long j) {
        if (i <= 0) {
            try {
                JSONObject a = this.f11206b.a(i2, j);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        a = this.f11206b.a(i, i2, j);
        if (a == null) {
            f11205a.error("saveOrUpdateFtp(), result json is null");
            return null;
        } else if (a.optInt("code") != 0) {
            f11205a.error("saveOrUpdateFtp(), result json code is nut 0");
            return null;
        } else {
            JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
            if (optJSONObject != null) {
                return new FtpDTO(optJSONObject);
            }
            f11205a.error("saveOrUpdateFtp(), result json obj is null");
            return null;
        }
    }

    /* renamed from: b */
    public ArrayList<FtpDTO> m12012b() {
        try {
            JSONObject a = this.f11206b.a();
            if (a == null) {
                f11205a.error("getFtps(), result json is null");
                return null;
            } else if (a.optInt("code") != 0) {
                f11205a.error("getFtps(), result json code is nut 0");
                return null;
            } else {
                JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
                if (optJSONArray == null || optJSONArray.length() <= 0) {
                    f11205a.error("getFtps(), result json obj is null");
                    return null;
                }
                ArrayList<FtpDTO> arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        arrayList.add(new FtpDTO(optJSONObject));
                    }
                }
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public TrainTargetDto m12010a(double d, int i, double d2, int i2, long j) {
        try {
            JSONObject a = this.f11206b.a(d, String.valueOf(i), d2, i2, j);
            if (a == null) {
                f11205a.error("getFtps(), result json is null");
                return null;
            } else if (a.optInt("code") != 0) {
                f11205a.error("getFtps(), result json code is nut 0");
                return null;
            } else {
                JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
                if (optJSONObject == null || optJSONObject.length() <= 0) {
                    f11205a.error("getFtps(), result json obj is null");
                    return null;
                }
                TrainTargetDto trainTargetDto = new TrainTargetDto();
                trainTargetDto.setHeight(optJSONObject.optDouble(Property.ICON_TEXT_FIT_HEIGHT));
                trainTargetDto.setWeight(optJSONObject.optDouble("weight"));
                trainTargetDto.setSex(Integer.parseInt(optJSONObject.optString("sex")));
                trainTargetDto.setBirthDayDate(optJSONObject.optLong("birthday"));
                trainTargetDto.setHeartRate(optJSONObject.optInt("cardiac_rate"));
                return trainTargetDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public TrainTargetDto m12011a(int i) {
        try {
            JSONObject a = this.f11206b.a(i);
            if (a == null) {
                f11205a.error("getFtps(), result json is null");
                return null;
            } else if (a.optInt("code") != 0) {
                f11205a.error("getFtps(), result json code is nut 0");
                return null;
            } else {
                JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
                if (optJSONObject == null || optJSONObject.length() <= 0) {
                    f11205a.error("getFtps(), result json obj is null");
                    return null;
                }
                TrainTargetDto trainTargetDto = new TrainTargetDto();
                trainTargetDto.setHeartRate(optJSONObject.optInt("cardiac_rate"));
                return trainTargetDto;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
