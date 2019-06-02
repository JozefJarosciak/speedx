package com.beastbikes.android.modules.cycling.club.biz;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import org.json.JSONObject;

/* compiled from: ClubServiceStub */
/* renamed from: com.beastbikes.android.modules.cycling.club.biz.f */
public interface C1404f extends C1600d {
    @C1447d(a = "/getMyClubRelation")
    /* renamed from: a */
    JSONObject m6146a();

    @C1447d(a = "/getMyClubRank")
    /* renamed from: a */
    JSONObject m6147a(@C1444a(a = "rankType") int i);

    @C1447d(a = "/getClubApplyList")
    /* renamed from: a */
    JSONObject m6148a(@C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/getClubRankList")
    /* renamed from: a */
    JSONObject m6149a(@C1444a(a = "rankType") int i, @C1444a(a = "page") int i2, @C1444a(a = "count") int i3);

    @C1447d(a = "/getClubMemberRankList")
    /* renamed from: a */
    JSONObject m6150a(@C1444a(a = "rankType") int i, @C1444a(a = "page") int i2, @C1444a(a = "count") int i3, @C1444a(a = "clubId") String str);

    @C1447d(a = "/getClubInfo")
    /* renamed from: a */
    JSONObject m6151a(@C1444a(a = "clubId") String str);

    @C1447d(a = "/postClubApply")
    /* renamed from: a */
    JSONObject m6152a(@C1444a(a = "applyId") String str, @C1444a(a = "command") int i);

    @C1447d(a = "/getClubNoticeList")
    /* renamed from: a */
    JSONObject m6153a(@C1444a(a = "clubId") String str, @C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/getClubMemberList")
    /* renamed from: a */
    JSONObject m6154a(@C1444a(a = "clubId") String str, @C1444a(a = "page") int i, @C1444a(a = "count") int i2, @C1444a(a = "order") String str2);

    @C1447d(a = "/postCmdClub")
    /* renamed from: a */
    JSONObject m6155a(@C1444a(a = "clubId") String str, @C1444a(a = "command") int i, @C1444a(a = "extra") String str2);

    @C1447d(a = "/sendSmscode")
    /* renamed from: a */
    JSONObject m6156a(@C1444a(a = "mobilephone") String str, @C1444a(a = "msgType") String str2);

    @C1447d(a = "/getClubList")
    /* renamed from: a */
    JSONObject m6157a(@C1444a(a = "orderBy") String str, @C1444a(a = "city") String str2, @C1444a(a = "geo_code") String str3, @C1444a(a = "keyName") String str4, @C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/postUpdateClubInfo")
    /* renamed from: a */
    JSONObject m6158a(@C1444a(a = "name") String str, @C1444a(a = "logoId") String str2, @C1444a(a = "province") String str3, @C1444a(a = "city") String str4, @C1444a(a = "desc") String str5, @C1444a(a = "notice") String str6, @C1444a(a = "isPrivate") int i);

    @C1447d(a = "/postRegisterClub")
    /* renamed from: a */
    JSONObject m6159a(@C1444a(a = "name") String str, @C1444a(a = "logoId") String str2, @C1444a(a = "province") String str3, @C1444a(a = "city") String str4, @C1444a(a = "desc") String str5, @C1444a(a = "realName") String str6, @C1444a(a = "mobilephone") String str7, @C1444a(a = "qq") String str8, @C1444a(a = "vcode") String str9, @C1444a(a = "isPrivate") int i, @C1444a(a = "latitude") double d, @C1444a(a = "longitude") double d2);

    @C1447d(a = "/getUnReadCount")
    /* renamed from: b */
    JSONObject m6160b();

    @C1447d(a = "/postUpdateClubInfo")
    /* renamed from: b */
    JSONObject m6161b(@C1444a(a = "notice") String str);

    @C1447d(a = "/postCmdClubMember")
    /* renamed from: b */
    JSONObject m6162b(@C1444a(a = "memberId") String str, @C1444a(a = "command") int i);

    @C1447d(a = "/getClubLevelInfo")
    /* renamed from: c */
    JSONObject m6163c();

    @C1447d(a = "/transferClub")
    /* renamed from: c */
    JSONObject m6164c(@C1444a(a = "memberId") String str, @C1444a(a = "isQuit") int i);

    @C1447d(a = "/getClubPrivilegInfo")
    /* renamed from: d */
    JSONObject m6165d();

    @C1447d(a = "/getClubTransStatus")
    /* renamed from: e */
    JSONObject m6166e();

    @C1447d(a = "/cancelClubTrans")
    /* renamed from: f */
    JSONObject m6167f();

    @C1447d(a = "/sendClubTransNotify")
    /* renamed from: g */
    JSONObject m6168g();
}
