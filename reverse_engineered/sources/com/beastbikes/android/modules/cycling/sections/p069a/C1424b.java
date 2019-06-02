package com.beastbikes.android.modules.cycling.sections.p069a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import org.json.JSONObject;

/* compiled from: SectionServiceStub */
/* renamed from: com.beastbikes.android.modules.cycling.sections.a.b */
public interface C1424b extends C1600d {
    @C1447d(a = "/getSegmentList")
    /* renamed from: a */
    JSONObject m6894a(@C1444a(a = "longitude") double d, @C1444a(a = "latitude") double d2, @C1444a(a = "range") float f, @C1444a(a = "difficult") String str, @C1444a(a = "legRange") String str2, @C1444a(a = "altRange") String str3, @C1444a(a = "slopeRange") String str4, @C1444a(a = "orderby") String str5);

    @C1447d(a = "/favorSegment")
    /* renamed from: a */
    JSONObject m6895a(@C1444a(a = "segmentId") long j);

    @C1447d(a = "/getSegmentInfo")
    /* renamed from: a */
    JSONObject m6896a(@C1444a(a = "segmentId") long j, @C1444a(a = "longitude") float f, @C1444a(a = "latitude") float f2);

    @C1447d(a = "/getSegmentRankList")
    /* renamed from: a */
    JSONObject m6897a(@C1444a(a = "segmentId") long j, @C1444a(a = "page") int i, @C1444a(a = "count") int i2);

    @C1447d(a = "/getRecordSegmentList")
    /* renamed from: a */
    JSONObject m6898a(@C1444a(a = "sportIdentify") String str);

    @C1447d(a = "/getUserSegmentList")
    /* renamed from: a */
    JSONObject m6899a(@C1444a(a = "userId") String str, @C1444a(a = "page") int i, @C1444a(a = "count") int i2);
}
