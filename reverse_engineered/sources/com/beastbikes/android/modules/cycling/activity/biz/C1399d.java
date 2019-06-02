package com.beastbikes.android.modules.cycling.activity.biz;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import com.beastbikes.framework.business.BusinessException;
import org.json.JSONObject;

/* compiled from: CyclingServiceStub */
/* renamed from: com.beastbikes.android.modules.cycling.activity.biz.d */
public interface C1399d extends C1600d {
    @C1447d(a = "/getGoalConfig")
    /* renamed from: a */
    JSONObject m5884a();

    @C1447d(a = "/setMyGoal")
    /* renamed from: a */
    JSONObject m5885a(@C1444a(a = "distance") double d);

    @C1447d(a = "/getActivityDataByUserId")
    /* renamed from: a */
    JSONObject m5886a(@C1444a(a = "userId") String str);

    @C1447d(a = "/routes")
    /* renamed from: a */
    JSONObject m5887a(@C1444a(a = "sport_route_id") String str, @C1444a(a = "calories") double d, @C1444a(a = "is_baidu_map") boolean z, @C1444a(a = "speed_avg") double d2, @C1444a(a = "speed_max") double d3, @C1444a(a = "start_date") String str2, @C1444a(a = "stop_date") String str3, @C1444a(a = "time") double d4, @C1444a(a = "title") String str4, @C1444a(a = "rise_total") double d5, @C1444a(a = "distance") double d6, @C1444a(a = "is_private") boolean z2, @C1444a(a = "central_id") String str5, @C1444a(a = "cardiac_rate_avg") double d7, @C1444a(a = "cardiac_rate_max") double d8, @C1444a(a = "cadence_avg") double d9, @C1444a(a = "cadence_max") double d10, @C1444a(a = "is_repair") boolean z3, @C1444a(a = "is_training") boolean z4, @C1444a(a = "course_id") int i, @C1444a(a = "source") String str6, @C1444a(a = "uphill_distance") double d11, @C1444a(a = "avg_watts") double d12, @C1444a(a = "max_watts") double d13, @C1444a(a = "kilojoules") double d14, @C1444a(a = "is_virtual_watts") boolean z5, @C1444a(a = "course_date") long j, @C1444a(a = "np") double d15, @C1444a(a = "total_decline") double d16);

    @C1447d(a = "/updateCyclingRecord")
    /* renamed from: a */
    JSONObject m5888a(@C1444a(a = "activityId") String str, @C1444a(a = "isPrivate") int i);

    @C1447d(a = "/saveSample")
    /* renamed from: a */
    JSONObject m5889a(@C1444a(a = "activityId") String str, @C1444a(a = "sequence") int i, @C1444a(a = "data") String str2);

    @C1446c(a = "/routes")
    /* renamed from: a */
    JSONObject m5890a(@C1452i(a = "user_id") String str, @C1452i(a = "start_date") long j, @C1452i(a = "end_date") long j2, @C1452i(a = "source") String str2, @C1452i(a = "page") int i, @C1452i(a = "count") int i2);

    @C1447d(a = "/getActivityInfoByActivityId")
    /* renamed from: a */
    JSONObject m5891a(@C1444a(a = "userId") String str, @C1444a(a = "activityId") String str2);

    @C1447d(a = "/postAppeal")
    /* renamed from: a */
    JSONObject m5892a(@C1444a(a = "phone") String str, @C1444a(a = "description") String str2, @C1444a(a = "activityId") String str3, @C1444a(a = "phoneMode") String str4, @C1444a(a = "phoneSystem") String str5);

    @C1447d(a = "/getMyGoalInfo")
    /* renamed from: b */
    JSONObject m5893b();

    @C1447d(a = "/deleteActivityByActivityId")
    /* renamed from: b */
    JSONObject m5894b(@C1444a(a = "activityId") String str);

    @C1447d(a = "/updateRouteStatueBySportIdentify")
    /* renamed from: b */
    JSONObject m5895b(@C1444a(a = "sport_identify") String str, @C1444a(a = "status") int i);

    @C1447d(a = "/getActivitySamplesByActivityId")
    /* renamed from: b */
    JSONObject m5896b(@C1444a(a = "userId") String str, @C1444a(a = "activityId") String str2);

    @C1446c(a = "/routes/devices")
    /* renamed from: c */
    JSONObject m5897c();

    @C1447d(a = "/postReportSportRoute")
    /* renamed from: c */
    JSONObject m5898c(@C1444a(a = "activityId") String str, @C1444a(a = "reason") String str2);

    @C1446c(a = "/routes/data")
    /* renamed from: d */
    JSONObject m5899d();

    @C1447d(a = "/updateCyclingRecord")
    /* renamed from: d */
    JSONObject m5900d(@C1444a(a = "activityId") String str, @C1444a(a = "title") String str2) throws BusinessException;
}
