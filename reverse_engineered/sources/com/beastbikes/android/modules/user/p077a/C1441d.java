package com.beastbikes.android.modules.user.p077a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import com.beastbikes.android.sphere.restful.p078a.C1449f;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import java.io.File;
import org.json.JSONObject;

/* compiled from: UserServiceStub */
/* renamed from: com.beastbikes.android.modules.user.a.d */
public interface C1441d extends C1600d {
    @C1447d(a = "/get_app_setting")
    /* renamed from: a */
    JSONObject m7464a();

    @C1447d(a = "/updateDeviceInfo")
    /* renamed from: a */
    JSONObject m7465a(@C1444a(a = "latitude") double d, @C1444a(a = "longitude") double d2, @C1444a(a = "deviceToken") String str);

    @C1447d(a = "/updateUserInfo")
    /* renamed from: a */
    JSONObject m7466a(@C1444a(a = "cardiacRate") int i);

    @C1447d(a = "/getBadgeList")
    /* renamed from: a */
    JSONObject m7467a(@C1444a(a = "isHistory") int i, @C1444a(a = "page") int i2, @C1444a(a = "count") int i3, @C1444a(a = "userId") String str);

    @C1447d(a = "/seekFriends")
    /* renamed from: a */
    JSONObject m7468a(@C1444a(a = "seekType") int i, @C1444a(a = "thirdKey") String str, @C1444a(a = "thirdToken") String str2);

    @C1447d(a = "/seekFriends")
    /* renamed from: a */
    JSONObject m7469a(@C1444a(a = "seekType") int i, @C1444a(a = "thirdKey") String str, @C1444a(a = "thirdToken") String str2, @C1449f(a = "contact") File file);

    @C1447d(a = "/getUserInfoByUserId")
    /* renamed from: a */
    JSONObject m7470a(@C1444a(a = "userId") String str);

    @C1447d(a = "/getUserDiagram")
    /* renamed from: a */
    JSONObject m7471a(@C1444a(a = "userId") String str, @C1444a(a = "days") int i);

    @C1447d(a = "/getFansList")
    /* renamed from: a */
    JSONObject m7472a(@C1444a(a = "userId") String str, @C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/getUserGoalInfoByCentral")
    /* renamed from: a */
    JSONObject m7473a(@C1444a(a = "centralId") String str, @C1444a(a = "useId") String str2);

    @C1447d(a = "/feedback")
    /* renamed from: a */
    JSONObject m7474a(@C1444a(a = "content") String str, @C1444a(a = "contact") String str2, @C1444a(a = "type") int i, @C1444a(a = "detail") String str3, @C1444a(a = "logId") String str4);

    @C1447d(a = "/user/set")
    /* renamed from: a */
    JSONObject m7475a(@C1444a(a = "nickname") String str, @C1444a(a = "country") String str2, @C1444a(a = "city") String str3, @C1444a(a = "province") String str4, @C1444a(a = "avatarImageId") String str5);

    @C1447d(a = "/follow")
    /* renamed from: b */
    JSONObject m7476b(@C1444a(a = "targetId") String str);

    @C1447d(a = "/getClubDiagram")
    /* renamed from: b */
    JSONObject m7477b(@C1444a(a = "clubId") String str, @C1444a(a = "days") int i);

    @C1447d(a = "/getFollowList")
    /* renamed from: b */
    JSONObject m7478b(@C1444a(a = "userId") String str, @C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/unfollow")
    /* renamed from: c */
    JSONObject m7479c(@C1444a(a = "userId") String str);

    @C1446c(a = "/routes/summary")
    /* renamed from: c */
    JSONObject m7480c(@C1452i(a = "user_id") String str, @C1452i(a = "type") int i);
}
