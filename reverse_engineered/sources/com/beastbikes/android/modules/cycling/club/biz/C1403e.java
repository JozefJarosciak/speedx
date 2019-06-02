package com.beastbikes.android.modules.cycling.club.biz;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import org.json.JSONObject;

/* compiled from: ClubFeedStub */
/* renamed from: com.beastbikes.android.modules.cycling.club.biz.e */
public interface C1403e extends C1600d {
    @C1447d(a = "/cleanMyClubMsgList")
    /* renamed from: a */
    JSONObject m6127a();

    @C1447d(a = "/getClubFeedInfo")
    /* renamed from: a */
    JSONObject m6128a(@C1444a(a = "feedId") int i);

    @C1447d(a = "/likeClubFeed")
    /* renamed from: a */
    JSONObject m6129a(@C1444a(a = "cmd") int i, @C1444a(a = "feedId") int i2);

    @C1447d(a = "/getClubFeedLikeList")
    /* renamed from: a */
    JSONObject m6130a(@C1444a(a = "feedId") int i, @C1444a(a = "page") int i2, @C1444a(a = "count") int i3);

    @C1447d(a = "/getPushRecordList")
    /* renamed from: a */
    JSONObject m6131a(@C1444a(a = "history") int i, @C1444a(a = "stamp") Long l, @C1444a(a = "count") int i2);

    @C1447d(a = "/postCommentClubFeed")
    /* renamed from: a */
    JSONObject m6132a(@C1444a(a = "feedId") int i, @C1444a(a = "content") String str, @C1444a(a = "replyId") int i2);

    @C1447d(a = "/deleteClubPhotos")
    /* renamed from: a */
    JSONObject m6133a(@C1444a(a = "photoIds") String str);

    @C1447d(a = "/getClubTimeLine")
    /* renamed from: a */
    JSONObject m6134a(@C1444a(a = "clubId") String str, @C1444a(a = "startStamp") long j, @C1444a(a = "endStamp") long j2, @C1444a(a = "count") int i);

    @C1447d(a = "/postClubPhotos")
    /* renamed from: a */
    JSONObject m6135a(@C1444a(a = "imageList") String str, @C1444a(a = "content") String str2);

    @C1447d(a = "/getClubGalleryCount")
    /* renamed from: a */
    JSONObject m6136a(@C1444a(a = "clubId") String str, @C1444a(a = "startDate") String str2, @C1444a(a = "endDate") String str3);

    @C1447d(a = "/getClubGalleryList")
    /* renamed from: a */
    JSONObject m6137a(@C1444a(a = "clubId") String str, @C1444a(a = "startDate") String str2, @C1444a(a = "endDate") String str3, @C1444a(a = "count") int i);

    @C1447d(a = "/postClubFeed")
    /* renamed from: a */
    JSONObject m6138a(@C1444a(a = "clubId") String str, @C1444a(a = "content") String str2, @C1444a(a = "sportIdentify") String str3, @C1444a(a = "imageList") String str4, @C1444a(a = "needSync") int i);

    @C1447d(a = "/deleteClubFeed")
    /* renamed from: b */
    JSONObject m6139b(@C1444a(a = "feedId") int i);

    @C1447d(a = "/likeClubPhoto")
    /* renamed from: b */
    JSONObject m6140b(@C1444a(a = "photoId") int i, @C1444a(a = "cmd") int i2);

    @C1447d(a = "/getClubFeedCommentList")
    /* renamed from: b */
    JSONObject m6141b(@C1444a(a = "feedId") int i, @C1444a(a = "page") int i2, @C1444a(a = "count") int i3);

    @C1447d(a = "/postClubPhotoComment")
    /* renamed from: b */
    JSONObject m6142b(@C1444a(a = "photoId") int i, @C1444a(a = "content") String str, @C1444a(a = "replyId") int i2);

    @C1447d(a = "/deleteClubFeedComment")
    /* renamed from: c */
    JSONObject m6143c(@C1444a(a = "commentId") int i);

    @C1447d(a = "/getClubPhotoCommentList")
    /* renamed from: c */
    JSONObject m6144c(@C1444a(a = "photoId") int i, @C1444a(a = "page") int i2, @C1444a(a = "count") int i3);

    @C1447d(a = "/getClubPhotoLikeList")
    /* renamed from: d */
    JSONObject m6145d(@C1444a(a = "photoId") int i, @C1444a(a = "page") int i2, @C1444a(a = "count") int i3);
}
