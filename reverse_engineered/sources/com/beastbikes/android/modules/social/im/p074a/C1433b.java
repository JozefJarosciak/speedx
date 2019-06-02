package com.beastbikes.android.modules.social.im.p074a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import org.json.JSONObject;

/* compiled from: FriendServiceStub */
/* renamed from: com.beastbikes.android.modules.social.im.a.b */
public interface C1433b extends C1600d {
    @C1447d(a = "/cleanFriendRequests")
    /* renamed from: a */
    JSONObject m7278a();

    @C1447d(a = "/friendRequestCmd")
    /* renamed from: a */
    JSONObject m7279a(@C1444a(a = "requestId") int i, @C1444a(a = "command") int i2);

    @C1447d(a = "/getChatInfoByIds")
    /* renamed from: a */
    JSONObject m7280a(@C1444a(a = "userIds") String str);

    @C1447d(a = "/searchUserByNickname")
    /* renamed from: a */
    JSONObject m7281a(@C1444a(a = "keyName") String str, @C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/addFriend")
    /* renamed from: a */
    JSONObject m7282a(@C1444a(a = "userId") String str, @C1444a(a = "extra") String str2);

    @C1447d(a = "/getChatToken")
    /* renamed from: b */
    JSONObject m7283b();

    @C1446c(a = "/friendRequestsList")
    /* renamed from: b */
    JSONObject m7284b(@C1452i(a = "page") int i, @C1452i(a = "count") int i2);

    @C1447d(a = "/updateSocialInfo")
    /* renamed from: b */
    JSONObject m7285b(@C1444a(a = "target_user_id") String str, @C1444a(a = "remarks") String str2);

    @C1447d(a = "/setClubChatNick")
    /* renamed from: c */
    JSONObject m7286c(@C1444a(a = "clubId") String str, @C1444a(a = "nickname") String str2);

    @C1447d(a = "/getClubChatNick")
    /* renamed from: d */
    JSONObject m7287d(@C1444a(a = "clubId") String str, @C1444a(a = "userId") String str2);
}
