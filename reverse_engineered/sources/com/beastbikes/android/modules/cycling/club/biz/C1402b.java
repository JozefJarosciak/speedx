package com.beastbikes.android.modules.cycling.club.biz;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import org.json.JSONObject;

/* compiled from: ClubActivityStub */
/* renamed from: com.beastbikes.android.modules.cycling.club.biz.b */
public interface C1402b extends C1600d {
    @C1447d(a = "/clubActivityInfo")
    /* renamed from: a */
    JSONObject m6118a(@C1444a(a = "activityId") String str);

    @C1447d(a = "/clubActivityList")
    /* renamed from: a */
    JSONObject m6119a(@C1444a(a = "clubId") String str, @C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/ClubActSignIn")
    /* renamed from: a */
    JSONObject m6120a(@C1444a(a = "objectId") String str, @C1444a(a = "activityId") String str2);

    @C1447d(a = "/createClubActivity")
    /* renamed from: a */
    JSONObject m6121a(@C1444a(a = "title") String str, @C1444a(a = "desc") String str2, @C1444a(a = "mobPlace") String str3, @C1444a(a = "mobPoint") String str4, @C1444a(a = "startDate") String str5, @C1444a(a = "endDate") String str6, @C1444a(a = "routeId") String str7, @C1444a(a = "routeName") String str8, @C1444a(a = "mobilephone") String str9, @C1444a(a = "applyEndDate") String str10, @C1444a(a = "maxMembers") int i, @C1444a(a = "isClubPrivate") int i2, @C1444a(a = "decstiption") String str11, @C1444a(a = "cover") String str12);

    @C1447d(a = "/updateClubActivity")
    /* renamed from: a */
    JSONObject m6122a(@C1444a(a = "activityId") String str, @C1444a(a = "title") String str2, @C1444a(a = "desc") String str3, @C1444a(a = "mobPlace") String str4, @C1444a(a = "mobPoint") String str5, @C1444a(a = "startDate") String str6, @C1444a(a = "endDate") String str7, @C1444a(a = "routeId") String str8, @C1444a(a = "routeName") String str9, @C1444a(a = "mobilephone") String str10, @C1444a(a = "applyEndDate") String str11, @C1444a(a = "maxMembers") int i, @C1444a(a = "isClubPrivate") int i2, @C1444a(a = "decstiption") String str12, @C1444a(a = "cover") String str13);

    @C1447d(a = "/cancelClubActivity")
    /* renamed from: b */
    JSONObject m6123b(@C1444a(a = "activityId") String str);

    @C1447d(a = "/clubActivityMemberList")
    /* renamed from: b */
    JSONObject m6124b(@C1444a(a = "activityId") String str, @C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/getClubActivityStatisticsByActId")
    /* renamed from: c */
    JSONObject m6125c(@C1444a(a = "activityId") String str);

    @C1447d(a = "/sendClubActSms")
    /* renamed from: d */
    JSONObject m6126d(@C1444a(a = "activityId") String str);
}
