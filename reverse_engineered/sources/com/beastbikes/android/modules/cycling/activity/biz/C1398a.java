package com.beastbikes.android.modules.cycling.activity.biz;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.util.C0882j;
import com.avos.avoscloud.AVStatus;
import com.avos.avoscloud.AVUtils;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.activity.dao.C1917a;
import com.beastbikes.android.modules.cycling.activity.dao.C1918b;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample;
import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.modules.user.dto.ActivityDTO.Leg;
import com.beastbikes.android.modules.user.dto.C2411a;
import com.beastbikes.android.modules.user.dto.RemoteSamplesDTO;
import com.beastbikes.android.modules.user.p153c.C2408a;
import com.beastbikes.android.p057b.C1381a;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.android.widget.p081b.C2409c;
import com.beastbikes.framework.android.ApplicationContext;
import com.beastbikes.framework.android.p176b.C2790a;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import com.beastbikes.framework.persistence.PersistenceException;
import com.beastbikes.framework.ui.android.WebActivity;
import com.beastbikes.framework.ui.android.utils.Toasts;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: ActivityManager */
/* renamed from: com.beastbikes.android.modules.cycling.activity.biz.a */
public class C1398a extends C1397a {
    /* renamed from: a */
    private static final Logger f4490a = LoggerFactory.getLogger(C1398a.class);
    /* renamed from: b */
    private final C1917a f4491b;
    /* renamed from: c */
    private final C1918b f4492c;
    /* renamed from: d */
    private C1399d f4493d;
    /* renamed from: e */
    private Activity f4494e;

    /* renamed from: a */
    public static String m5854a(Context context) {
        String str = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences(context.getPackageName(), 0);
        if (sharedPreferences.contains("activity.id")) {
            try {
                str = sharedPreferences.getString("activity.id", null);
            } catch (ClassCastException e) {
            }
        }
        return str;
    }

    /* renamed from: a */
    public static boolean m5855a(Context context, String str) {
        Editor edit = context.getSharedPreferences(context.getPackageName(), 0).edit();
        if (TextUtils.isEmpty(str)) {
            edit.remove("activity.id");
        } else {
            edit.putString("activity.id", str);
        }
        return edit.commit();
    }

    public C1398a(Context context) {
        super((C1372b) context.getApplicationContext());
        C1381a d = ((BeastBikes) ApplicationContext.m5242j().getApplicationContext()).m5253d();
        this.f4491b = new C1917a(d);
        this.f4492c = new C1918b(d);
        this.f4493d = (C1399d) new C1772d(context).a(C1399d.class, C1768c.f8075a, C1768c.a(context));
    }

    public C1398a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f4494e = activity;
        C1381a d = ((BeastBikes) ApplicationContext.m5242j().getApplicationContext()).m5253d();
        this.f4491b = new C1917a(d);
        this.f4492c = new C1918b(d);
        this.f4493d = (C1399d) new C1772d(activity).a(C1399d.class, C1768c.f8075a, C1768c.a(activity));
    }

    /* renamed from: a */
    public LocalActivity m5861a() {
        Object a = C1398a.m5854a((Context) m5852j());
        if (TextUtils.isEmpty(a)) {
            return null;
        }
        try {
            return (LocalActivity) this.f4491b.c(a);
        } catch (PersistenceException e) {
            return null;
        }
    }

    /* renamed from: a */
    public ActivityDTO m5862a(String str, String str2) throws BusinessException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        ActivityDTO activityDTO;
        LocalActivity b;
        try {
            JSONObject a = this.f4493d.m5891a(str, str2);
            if (a != null && a.optInt("code") == 0) {
                JSONObject optJSONObject = a.optJSONObject(C0882j.f2229c);
                if (optJSONObject != null) {
                    activityDTO = new ActivityDTO(optJSONObject);
                    if (activityDTO == null) {
                        return activityDTO;
                    }
                    b = m5868b(str2);
                    if (b == null) {
                        return new ActivityDTO(b);
                    }
                    return activityDTO;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        activityDTO = null;
        if (activityDTO == null) {
            return activityDTO;
        }
        b = m5868b(str2);
        if (b == null) {
            return activityDTO;
        }
        return new ActivityDTO(b);
    }

    @SuppressLint({"UseSparseArrays"})
    /* renamed from: a */
    public List<C2411a> m5864a(String str, String str2, String str3, boolean z) throws BusinessException {
        List<C2411a> arrayList = new ArrayList();
        if (!z) {
            List<LocalActivitySample> d = m5877d(str3);
            if (!(d == null || d.isEmpty())) {
                for (LocalActivitySample c2411a : d) {
                    arrayList.add(new C2411a(c2411a));
                }
            }
        }
        if (!arrayList.isEmpty()) {
            f4490a.trace("get activity samples by local");
            return arrayList;
        } else if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str3)) {
            return null;
        } else {
            try {
                JSONObject b = this.f4493d.m5896b(str2, str3);
                if (b == null) {
                    return null;
                }
                if (b.optInt("code") == 0) {
                    JSONArray optJSONArray = b.optJSONArray(C0882j.f2229c);
                    int length = optJSONArray.length();
                    for (int i = 0; i < length; i++) {
                        RemoteSamplesDTO remoteSamplesDTO = new RemoteSamplesDTO(optJSONArray.optJSONObject(i));
                        if (!arrayList.contains(remoteSamplesDTO.getDatas())) {
                            arrayList.addAll(remoteSamplesDTO.getDatas());
                        }
                    }
                    AVUser currentUser = AVUser.getCurrentUser();
                    if (currentUser == null || !str2.equals(currentUser.getObjectId())) {
                        return arrayList;
                    }
                    new Thread(new a$1(this, str3, arrayList, str, str2)).start();
                    return arrayList;
                }
                Object optString = b.optString(AVStatus.MESSAGE_TAG);
                if (!TextUtils.isEmpty(optString)) {
                    Toasts.showOnUiThread(this.f4494e, optString);
                }
                return null;
            } catch (Throwable e) {
                throw new BusinessException(e);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public int m5860a(com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity r15) throws com.beastbikes.framework.business.BusinessException {
        /*
        r14 = this;
        r3 = r15.getId();
        r0 = 0;
    L_0x0005:
        r1 = r14.f4492c;	 Catch:{ Exception -> 0x0118 }
        r2 = 0;
        r4 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        r4 = r1.b(r3, r2, r4);	 Catch:{ Exception -> 0x0118 }
        if (r4 == 0) goto L_0x0016;
    L_0x0010:
        r1 = r4.size();	 Catch:{ Exception -> 0x0118 }
        if (r1 > 0) goto L_0x0022;
    L_0x0016:
        r0 = 4;
        r1 = r15.getState();	 Catch:{ Exception -> 0x0118 }
        if (r0 != r1) goto L_0x02aa;
    L_0x001d:
        r0 = r14.m5858g(r15);	 Catch:{ Exception -> 0x0118 }
    L_0x0021:
        return r0;
    L_0x0022:
        r1 = r4.size();	 Catch:{ Exception -> 0x0118 }
        r2 = 500; // 0x1f4 float:7.0E-43 double:2.47E-321;
        if (r1 >= r2) goto L_0x0031;
    L_0x002a:
        r1 = 4;
        r2 = r15.getState();	 Catch:{ Exception -> 0x0118 }
        if (r1 != r2) goto L_0x028a;
    L_0x0031:
        r1 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118 }
        r2.<init>();	 Catch:{ Exception -> 0x0118 }
        r5 = "ActivityManager: Found ";
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x0118 }
        r5 = r4.size();	 Catch:{ Exception -> 0x0118 }
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x0118 }
        r5 = " samples";
        r2 = r2.append(r5);	 Catch:{ Exception -> 0x0118 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0118 }
        r1.info(r2);	 Catch:{ Exception -> 0x0118 }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ Exception -> 0x0118 }
        r5 = new org.json.JSONArray;	 Catch:{ Exception -> 0x0118 }
        r5.<init>();	 Catch:{ Exception -> 0x0118 }
        r2 = r0 + 1;
        r8 = r4.iterator();	 Catch:{ Exception -> 0x0118 }
    L_0x0062:
        r0 = r8.hasNext();	 Catch:{ Exception -> 0x0118 }
        if (r0 == 0) goto L_0x01f7;
    L_0x0068:
        r0 = r8.next();	 Catch:{ Exception -> 0x0118 }
        r0 = (com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample) r0;	 Catch:{ Exception -> 0x0118 }
        r1 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r9 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118 }
        r9.<init>();	 Catch:{ Exception -> 0x0118 }
        r10 = "ActivityManager: Packing sample ";
        r9 = r9.append(r10);	 Catch:{ Exception -> 0x0118 }
        r10 = r0.getId();	 Catch:{ Exception -> 0x0118 }
        r9 = r9.append(r10);	 Catch:{ Exception -> 0x0118 }
        r9 = r9.toString();	 Catch:{ Exception -> 0x0118 }
        r1.trace(r9);	 Catch:{ Exception -> 0x0118 }
        r9 = new org.json.JSONObject;	 Catch:{ Exception -> 0x0118 }
        r9.<init>();	 Catch:{ Exception -> 0x0118 }
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8576c;	 Catch:{ Throwable -> 0x011f }
        r10 = r0.getLatitude1();	 Catch:{ Throwable -> 0x011f }
        r10 = java.lang.Double.parseDouble(r10);	 Catch:{ Throwable -> 0x011f }
        r9.put(r1, r10);	 Catch:{ Throwable -> 0x011f }
    L_0x009c:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8577d;	 Catch:{ Throwable -> 0x012b }
        r10 = r0.getLongitude1();	 Catch:{ Throwable -> 0x012b }
        r10 = java.lang.Double.parseDouble(r10);	 Catch:{ Throwable -> 0x012b }
        r9.put(r1, r10);	 Catch:{ Throwable -> 0x012b }
    L_0x00a9:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8578e;	 Catch:{ Throwable -> 0x0137 }
        r10 = r0.getAltitude();	 Catch:{ Throwable -> 0x0137 }
        r10 = java.lang.Double.parseDouble(r10);	 Catch:{ Throwable -> 0x0137 }
        r9.put(r1, r10);	 Catch:{ Throwable -> 0x0137 }
    L_0x00b6:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8579f;	 Catch:{ JSONException -> 0x0143 }
        r10 = r0.getCurrTime();	 Catch:{ JSONException -> 0x0143 }
        r12 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r10 = r10 / r12;
        r9.put(r1, r10);	 Catch:{ JSONException -> 0x0143 }
    L_0x00c2:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8580g;	 Catch:{ JSONException -> 0x014f }
        r10 = r0.getDistance();	 Catch:{ JSONException -> 0x014f }
        r9.put(r1, r10);	 Catch:{ JSONException -> 0x014f }
    L_0x00cb:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8581h;	 Catch:{ JSONException -> 0x015b }
        r10 = r0.getVelocity();	 Catch:{ JSONException -> 0x015b }
        r9.put(r1, r10);	 Catch:{ JSONException -> 0x015b }
    L_0x00d4:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8582i;	 Catch:{ JSONException -> 0x0167 }
        r10 = r0.getCalorie();	 Catch:{ JSONException -> 0x0167 }
        r9.put(r1, r10);	 Catch:{ JSONException -> 0x0167 }
    L_0x00dd:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8583j;	 Catch:{ JSONException -> 0x0173 }
        r10 = r0.getCardiacRate();	 Catch:{ JSONException -> 0x0173 }
        r9.put(r1, r10);	 Catch:{ JSONException -> 0x0173 }
    L_0x00e6:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8584k;	 Catch:{ JSONException -> 0x017f }
        r10 = r0.getCadence();	 Catch:{ JSONException -> 0x017f }
        r9.put(r1, r10);	 Catch:{ JSONException -> 0x017f }
    L_0x00ef:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8585l;	 Catch:{ JSONException -> 0x018b }
        r10 = r0.getCyclingStatus();	 Catch:{ JSONException -> 0x018b }
        r9.put(r1, r10);	 Catch:{ JSONException -> 0x018b }
    L_0x00f8:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8586m;	 Catch:{ JSONException -> 0x01a6 }
        r10 = r0.getIsRepair();	 Catch:{ JSONException -> 0x01a6 }
        r9.put(r1, r10);	 Catch:{ JSONException -> 0x01a6 }
    L_0x0101:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8587n;	 Catch:{ JSONException -> 0x01c1 }
        r10 = r0.getPower();	 Catch:{ JSONException -> 0x01c1 }
        r9.put(r1, r10);	 Catch:{ JSONException -> 0x01c1 }
    L_0x010a:
        r1 = com.beastbikes.android.modules.cycling.activity.dao.entity.C1919a.f8588o;	 Catch:{ JSONException -> 0x01dc }
        r0 = r0.getExtend();	 Catch:{ JSONException -> 0x01dc }
        r9.put(r1, r0);	 Catch:{ JSONException -> 0x01dc }
    L_0x0113:
        r5.put(r9);	 Catch:{ Exception -> 0x0118 }
        goto L_0x0062;
    L_0x0118:
        r0 = move-exception;
        r1 = new com.beastbikes.framework.business.BusinessException;
        r1.<init>(r0);
        throw r1;
    L_0x011f:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = "ActivityManager";
        r12 = "Set sample latitude1 error";
        r10.error(r11, r12, r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x009c;
    L_0x012b:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = "ActivityManager";
        r12 = "Set sample longitude1 error";
        r10.error(r11, r12, r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x00a9;
    L_0x0137:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = "ActivityManager";
        r12 = "Set sample altitude error";
        r10.error(r11, r12, r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x00b6;
    L_0x0143:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = "ActivityManager";
        r12 = "Set sample time error";
        r10.error(r11, r12, r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x00c2;
    L_0x014f:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = "ActivityManager";
        r12 = "Set sample distance error";
        r10.error(r11, r12, r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x00cb;
    L_0x015b:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = "ActivityManager";
        r12 = "Set sample velocity error";
        r10.error(r11, r12, r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x00d4;
    L_0x0167:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = "ActivityManager";
        r12 = "Set sample calorie error";
        r10.error(r11, r12, r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x00dd;
    L_0x0173:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = "ActivityManager";
        r12 = "Set sample cardiac rate error";
        r10.error(r11, r12, r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x00e6;
    L_0x017f:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = "ActivityManager";
        r12 = "Set sample cadence error";
        r10.error(r11, r12, r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x00ef;
    L_0x018b:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118 }
        r11.<init>();	 Catch:{ Exception -> 0x0118 }
        r12 = "Set sample cycling status error, ";
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x0118 }
        r1 = r11.append(r1);	 Catch:{ Exception -> 0x0118 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0118 }
        r10.error(r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x00f8;
    L_0x01a6:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118 }
        r11.<init>();	 Catch:{ Exception -> 0x0118 }
        r12 = "Set sample repair error, ";
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x0118 }
        r1 = r11.append(r1);	 Catch:{ Exception -> 0x0118 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0118 }
        r10.error(r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x0101;
    L_0x01c1:
        r1 = move-exception;
        r10 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r11 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118 }
        r11.<init>();	 Catch:{ Exception -> 0x0118 }
        r12 = "Set sample power error, ";
        r11 = r11.append(r12);	 Catch:{ Exception -> 0x0118 }
        r1 = r11.append(r1);	 Catch:{ Exception -> 0x0118 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0118 }
        r10.error(r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x010a;
    L_0x01dc:
        r0 = move-exception;
        r1 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r10 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118 }
        r10.<init>();	 Catch:{ Exception -> 0x0118 }
        r11 = "Set sample extend error, ";
        r10 = r10.append(r11);	 Catch:{ Exception -> 0x0118 }
        r0 = r10.append(r0);	 Catch:{ Exception -> 0x0118 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0118 }
        r1.error(r0);	 Catch:{ Exception -> 0x0118 }
        goto L_0x0113;
    L_0x01f7:
        r0 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118 }
        r1.<init>();	 Catch:{ Exception -> 0x0118 }
        r8 = "ActivityManager: Syncing samples of activity ";
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0118 }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x0118 }
        r8 = " sequence ";
        r1 = r1.append(r8);	 Catch:{ Exception -> 0x0118 }
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0118 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0118 }
        r0.info(r1);	 Catch:{ Exception -> 0x0118 }
        r0 = r14.f4493d;	 Catch:{ Exception -> 0x0118 }
        r1 = r5.toString();	 Catch:{ Exception -> 0x0118 }
        r0 = r0.m5889a(r3, r2, r1);	 Catch:{ Exception -> 0x0118 }
        if (r0 == 0) goto L_0x022d;
    L_0x0225:
        r1 = "result";
        r1 = r0.optBoolean(r1);	 Catch:{ Exception -> 0x0118 }
        if (r1 != 0) goto L_0x0230;
    L_0x022d:
        r0 = -1;
        goto L_0x0021;
    L_0x0230:
        r1 = "result";
        r1 = r0.optBoolean(r1);	 Catch:{ Exception -> 0x0118 }
        if (r1 == 0) goto L_0x0287;
    L_0x0238:
        r1 = "code";
        r5 = -1;
        r0 = r0.optInt(r1, r5);	 Catch:{ Exception -> 0x0118 }
        if (r0 != 0) goto L_0x0287;
    L_0x0241:
        r0 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118 }
        r1.<init>();	 Catch:{ Exception -> 0x0118 }
        r5 = "ActivityManager: Sync samples of activity ";
        r1 = r1.append(r5);	 Catch:{ Exception -> 0x0118 }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x0118 }
        r5 = " success";
        r1 = r1.append(r5);	 Catch:{ Exception -> 0x0118 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0118 }
        r0.info(r1);	 Catch:{ Exception -> 0x0118 }
        r1 = r4.iterator();	 Catch:{ Exception -> 0x0118 }
    L_0x0263:
        r0 = r1.hasNext();	 Catch:{ Exception -> 0x0118 }
        if (r0 == 0) goto L_0x0282;
    L_0x0269:
        r0 = r1.next();	 Catch:{ Exception -> 0x0118 }
        r0 = (com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivitySample) r0;	 Catch:{ Exception -> 0x0118 }
        r5 = 1;
        r0.setSynced(r5);	 Catch:{ Exception -> 0x0118 }
        r0.setSyncTime(r6);	 Catch:{ Exception -> 0x0118 }
        r5 = java.util.UUID.randomUUID();	 Catch:{ Exception -> 0x0118 }
        r5 = r5.toString();	 Catch:{ Exception -> 0x0118 }
        r0.setRemoteId(r5);	 Catch:{ Exception -> 0x0118 }
        goto L_0x0263;
    L_0x0282:
        r0 = r14.f4492c;	 Catch:{ Exception -> 0x0118 }
        r0.a(r4);	 Catch:{ Exception -> 0x0118 }
    L_0x0287:
        r0 = r2;
        goto L_0x0005;
    L_0x028a:
        r0 = f4490a;	 Catch:{ Exception -> 0x0118 }
        r1 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0118 }
        r1.<init>();	 Catch:{ Exception -> 0x0118 }
        r2 = "ActivityManager:Samples of activity ";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0118 }
        r1 = r1.append(r3);	 Catch:{ Exception -> 0x0118 }
        r2 = " isn't enough to sync";
        r1 = r1.append(r2);	 Catch:{ Exception -> 0x0118 }
        r1 = r1.toString();	 Catch:{ Exception -> 0x0118 }
        r0.info(r1);	 Catch:{ Exception -> 0x0118 }
        goto L_0x0016;
    L_0x02aa:
        r0 = -1;
        goto L_0x0021;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.beastbikes.android.modules.cycling.activity.biz.a.a(com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity):int");
    }

    /* renamed from: g */
    private int m5858g(LocalActivity localActivity) throws BusinessException {
        if (localActivity == null) {
            return -1;
        }
        boolean z;
        String title = localActivity.getTitle();
        if (TextUtils.isEmpty(title)) {
            title = new SimpleDateFormat("MM-dd", Locale.getDefault()).format(new Date(localActivity.getStartTime())) + C2408a.a(localActivity.getStartTime());
        }
        String id = localActivity.getId();
        double a = m5859a(localActivity.getId(), localActivity.getTotalElapsedTime(), localActivity.getTotalDistance());
        String a2 = C2555d.a(localActivity.getStartTime());
        String a3 = C2555d.a(localActivity.getFinishTime());
        Object obj = null;
        if (!TextUtils.isEmpty(localActivity.getCentralId())) {
            obj = localActivity.getCentralId();
        }
        String str = null;
        if (!TextUtils.isEmpty(localActivity.getSource())) {
            str = localActivity.getSource();
        }
        BeastBikes beastBikes = (BeastBikes) ApplicationContext.m5242j();
        boolean z2 = beastBikes.m5260h() && beastBikes.m5261i();
        if (TextUtils.isEmpty(obj)) {
            z = z2;
        } else {
            z = false;
        }
        boolean z3 = localActivity.getCourseId() > 0;
        long trainCourseDate = localActivity.getTrainCourseDate();
        if (trainCourseDate > 0) {
            trainCourseDate /= 1000;
        }
        try {
            JSONObject a4 = this.f4493d.m5887a(id, localActivity.getTotalCalorie(), false, a, localActivity.getMaxVelocity(), a2, a3, localActivity.getTotalElapsedTime(), title, localActivity.getTotalRisenAltitude(), localActivity.getTotalDistance(), false, obj, localActivity.getCardiacRate(), localActivity.getMaxCardiacRate(), localActivity.getCadence(), localActivity.getMaxCadence(), z, z3, localActivity.getCourseId(), str, localActivity.getTotalUphillDistance(), localActivity.getAvgPower(), localActivity.getMaxPower(), 0.0d, localActivity.isVirtualPower(), trainCourseDate, (double) localActivity.getStandardPower(), localActivity.getTotalDescent());
            if (a4 == null) {
                return -1;
            }
            if (a4.optInt("code") == 0) {
                JSONObject optJSONObject = a4.optJSONObject(C0882j.f2229c);
                if (optJSONObject == null) {
                    f4490a.error("Upload local activity error by activityId " + id);
                    return -1;
                }
                localActivity.setSynced(true);
                localActivity.setSyncTime(System.currentTimeMillis());
                localActivity.setTitle(optJSONObject.optString(WebActivity.EXTRA_TITLE));
                localActivity.setRemoteId(optJSONObject.optString(AVUtils.objectIdTag));
                localActivity.setFake(optJSONObject.optBoolean("fake") ? 1 : 0);
                localActivity.setActivityUrl(optJSONObject.optString("cyclingImage"));
                localActivity.setSpeed(optJSONObject.optDouble(MapboxEvent.KEY_SPEED));
                localActivity.setMaxVelocity(optJSONObject.optDouble("speedMax"));
                localActivity.setTotalRisenAltitude(optJSONObject.optDouble("riseTotal"));
                localActivity.setTotalUphillDistance(optJSONObject.optDouble("uphillDistance"));
                localActivity.setTotalCalorie(optJSONObject.optDouble("calories"));
                localActivity.setTotalDistance(optJSONObject.optDouble("distance"));
                localActivity.setCentralName(optJSONObject.optString("centralName"));
                localActivity.setIsRepair(optJSONObject.optBoolean("is_repair") ? 1 : 0);
                localActivity.setJoinLeg(optJSONObject.optBoolean("is_join_leg"));
                a4 = optJSONObject.optJSONObject("leg");
                if (a4 != null) {
                    Leg leg = new Leg(a4);
                    localActivity.setAchievement(leg.getAchievement());
                    localActivity.setAchievementName(leg.getAchievementName());
                    localActivity.setOtherLegCount(leg.getOtherLegCount());
                    localActivity.setLegName(leg.getLegName());
                }
                localActivity.setMaxPower(optJSONObject.optDouble("max_watts"));
                localActivity.setAvgPower(optJSONObject.optDouble("avg_watts"));
                localActivity.setPowerOutput(optJSONObject.optDouble("kilojoules"));
                this.f4491b.b(localActivity);
                f4490a.info("Upload local activity success by activityId " + id + "uid =" + localActivity.getUserId() + "json=" + optJSONObject.toString());
                return 0;
            }
            f4490a.error("Upload local activity error by activityId " + id + ", uid=" + localActivity.getUserId() + "result = " + a4.toString());
            return -1;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public double m5859a(String str, double d, double d2) {
        double distance;
        double d3 = 0.0d;
        LocalActivitySample localActivitySample = null;
        int i = 0;
        while (true) {
            List a = this.f4492c.a(str, i, 500);
            if (a == null || a.size() <= 0) {
                d3 = d2 - d3;
            } else {
                int i2 = 0;
                LocalActivitySample localActivitySample2 = localActivitySample;
                while (i2 < a.size()) {
                    try {
                        LocalActivitySample localActivitySample3;
                        localActivitySample = (LocalActivitySample) a.get(i2);
                        if (i2 > 1) {
                            localActivitySample3 = (LocalActivitySample) a.get(i2 - 1);
                        } else {
                            localActivitySample3 = localActivitySample2;
                        }
                        if (localActivitySample3 != null) {
                            distance = localActivitySample.getDistance() - localActivitySample3.getDistance();
                            if (distance > 200.0d) {
                                distance += d3;
                                i2++;
                                d3 = distance;
                                localActivitySample2 = localActivitySample3;
                            }
                        }
                        distance = d3;
                        i2++;
                        d3 = distance;
                        localActivitySample2 = localActivitySample3;
                    } catch (Exception e) {
                        Exception exception = e;
                        distance = 0.0d;
                    }
                }
                i++;
                localActivitySample = localActivitySample2;
            }
        }
        d3 = d2 - d3;
        if (d3 <= 0.0d || d <= 0.0d) {
            distance = 0.0d;
        } else {
            distance = 3.6d * (d3 / d);
        }
        try {
            f4490a.info("computerAvgSpeed diffDistance " + d3 + "  speed: " + distance);
        } catch (Exception e2) {
            exception = e2;
            exception.printStackTrace();
            f4490a.error("computerAvgSpeed error e=" + exception.getMessage());
            return distance;
        }
        return distance;
    }

    /* renamed from: a */
    public boolean m5865a(String str) throws BusinessException {
        try {
            JSONObject b = this.f4493d.m5894b(str);
            if (b == null) {
                return false;
            }
            f4490a.info("delete activity result = " + b.toString());
            if (b.optInt("code") == 0) {
                Toasts.showOnUiThread(this.f4494e, this.f4494e.getResources().getString(C1373R.string.delete_success));
                return true;
            }
            Object optString = b.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f4494e, optString);
            }
            return b.optBoolean(C0882j.f2229c);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public boolean m5872b(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        try {
            JSONObject d = this.f4493d.m5900d(str, str2);
            if (d == null || !d.optBoolean(C0882j.f2229c)) {
                return false;
            }
            f4490a.info("Update cycling record title is success");
            this.f4491b.a(str, str2);
            f4490a.info("Update local activity title is success");
            return true;
        } catch (Exception e) {
            f4490a.error("update cycling record title is error");
            return false;
        }
    }

    /* renamed from: b */
    public LocalActivity m5868b(String str) throws BusinessException {
        try {
            return (LocalActivity) this.f4491b.c(str);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public synchronized void m5870b(LocalActivity localActivity) throws BusinessException {
        try {
            this.f4491b.d(new LocalActivity[]{localActivity});
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: c */
    public void m5875c(LocalActivity localActivity) throws BusinessException {
        try {
            this.f4491b.a(localActivity);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: d */
    public void m5878d(LocalActivity localActivity) throws BusinessException {
        try {
            this.f4491b.c(localActivity);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: e */
    public void m5880e(LocalActivity localActivity) throws BusinessException {
        try {
            this.f4491b.b(new LocalActivity[]{localActivity});
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: c */
    public synchronized void m5876c(String str) throws BusinessException {
        try {
            this.f4491b.a(new String[]{str});
            f4490a.info("Delete LocalActivity activity = " + str + "success");
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: c */
    public synchronized List<LocalActivity> m5874c(String str, String str2) throws BusinessException {
        List<LocalActivity> list;
        try {
            if (TextUtils.isEmpty(str)) {
                list = null;
            } else if (TextUtils.isEmpty(str2)) {
                list = this.f4491b.b("WHERE user_id=? and state=? and length(trim(ifnull(remote_id, ''))) = 0 order by finish_time desc", new String[]{str, String.valueOf(4)});
            } else {
                list = this.f4491b.b("WHERE user_id=? and central_id=? and state=? and length(trim(ifnull(remote_id, ''))) = 0 order by finish_time desc", new String[]{str, str2, String.valueOf(4)});
            }
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
        return list;
    }

    /* renamed from: d */
    public synchronized List<LocalActivitySample> m5877d(String str) throws BusinessException {
        try {
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
        return this.f4492c.b("WHERE activity_id=? and velocity >= 0 and velocity < 1.79769313486231570e+308", new String[]{str});
    }

    /* renamed from: e */
    public void m5881e(String str) throws BusinessException {
        try {
            List d = m5877d(str);
            if (d != null && d.size() > 0) {
                this.f4492c.c(d);
            }
            f4490a.info("Delete LocalActivitySamples activityId = " + str + "success");
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: b */
    public void m5869b() throws BusinessException {
        try {
            this.f4491b.a("DELETE FROM activity WHERE synced IS NOT 0 AND remote_id IS NOT NULL", new String[0]);
            this.f4492c.a("DELETE FROM activity_sample WHERE synced IS NOT 0 AND remote_id IS NOT NULL", new String[0]);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: d */
    public void m5879d(String str, String str2) {
        try {
            this.f4491b.a("DELETE FROM activity WHERE synced IS NOT 0 AND user_id=? AND device_id=? AND ble_data_type=2", new String[]{str2, str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public boolean m5867a(String str, String str2, String str3) throws BusinessException {
        try {
            JSONObject a = this.f4493d.m5892a(str, str2, str3, Build.FINGERPRINT, VERSION.RELEASE);
            if (a == null) {
                return false;
            }
            Object optString = a.optString(AVStatus.MESSAGE_TAG);
            if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f4494e, optString);
            }
            return a.optBoolean(C0882j.f2229c);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: a */
    public boolean m5866a(String str, int i) throws BusinessException {
        try {
            JSONObject a = this.f4493d.m5888a(str, i);
            if (a == null) {
                return false;
            }
            Object optString = a.optString(AVStatus.MESSAGE_TAG);
            int optInt = a.optInt("code");
            if (optInt == 0 || TextUtils.isEmpty(optString)) {
                this.f4491b.a(str, i);
            } else {
                Toasts.showOnUiThread(this.f4494e, optString);
            }
            if (optInt == 0) {
                return true;
            }
            return false;
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: e */
    public boolean m5882e(String str, String str2) throws BusinessException {
        try {
            JSONObject c = this.f4493d.m5898c(str, str2);
            if (c == null) {
                return false;
            }
            Object optString = c.optString(AVStatus.MESSAGE_TAG);
            if (c.optInt("code") == 0) {
                Toasts.showOnUiThread(this.f4494e, this.f4494e.getResources().getString(C1373R.string.postReport));
            } else if (!TextUtils.isEmpty(optString)) {
                Toasts.showOnUiThread(this.f4494e, optString);
            }
            return c.optBoolean(C0882j.f2229c);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }

    /* renamed from: f */
    public void m5883f(LocalActivity localActivity) throws PersistenceException {
        this.f4491b.c(new LocalActivity[]{localActivity});
    }

    /* renamed from: b */
    public void m5871b(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            try {
                JSONObject b = this.f4493d.m5895b(str, i);
                if (b != null && b.optInt("code") == 0) {
                    this.f4491b.b(str, i);
                }
            } catch (Exception e) {
                f4490a.error("Update activity showStatus error, e = " + e);
            }
        }
    }

    /* renamed from: a */
    public List<C2409c> m5863a(String str, long j, long j2, String str2, int i, int i2) {
        try {
            JSONObject a = this.f4493d.m5890a(str, j, j2, str2, i, i2);
            if (a == null) {
                f4490a.error("getRoutes(), result is null");
                return null;
            }
            int optInt = a.optInt("code", -1);
            if (optInt != 0) {
                f4490a.error("getRoutes(), result code = " + optInt);
                return null;
            }
            JSONArray optJSONArray = a.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                f4490a.error("getRoutes(), result array is empty");
                return null;
            }
            C2790a a2 = C2790a.a();
            Calendar instance = Calendar.getInstance();
            List<C2409c> arrayList = new ArrayList();
            for (int i3 = 0; i3 < optJSONArray.length(); i3++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i3);
                int optInt2 = optJSONObject.optInt("month");
                optInt = optJSONObject.optInt("year");
                int optInt3 = optJSONObject.optInt("total_count");
                long optInt4 = (long) optJSONObject.optInt("total_time");
                double optDouble = optJSONObject.optDouble("total_distance");
                instance.set(optInt, optInt2, 0, 0, 0, 0);
                long timeInMillis = instance.getTimeInMillis() / 1000;
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("routes");
                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                    for (int i4 = 0; i4 < optJSONArray2.length(); i4++) {
                        ActivityDTO activityDTO = new ActivityDTO(optJSONArray2.optJSONObject(i4));
                        activityDTO.setAvatarUrl(a2.a(str));
                        activityDTO.setHeaderId(timeInMillis);
                        activityDTO.setMonth(optInt2);
                        activityDTO.setYear(optInt);
                        activityDTO.setTotalCount(optInt3);
                        activityDTO.setTotalTime(optInt4);
                        activityDTO.setMonthDistance(optDouble);
                        arrayList.add(activityDTO);
                    }
                }
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public ArrayList<String> m5873c() {
        try {
            JSONObject c = this.f4493d.m5897c();
            if (c == null) {
                f4490a.error("getRouteDevices(), result is null");
                return null;
            }
            int optInt = c.optInt("code", -1);
            if (optInt != 0) {
                f4490a.error("getRouteDevices(), result code = " + optInt);
                return null;
            }
            JSONArray optJSONArray = c.optJSONArray(C0882j.f2229c);
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return null;
            }
            ArrayList<String> arrayList = new ArrayList();
            for (optInt = 0; optInt < optJSONArray.length(); optInt++) {
                arrayList.add(optJSONArray.optString(optInt));
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
