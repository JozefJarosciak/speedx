package com.beastbikes.android.modules.train.p076a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import com.beastbikes.android.sphere.restful.p078a.C1448e;
import com.beastbikes.android.sphere.restful.p078a.C1451h;
import org.json.JSONObject;

/* compiled from: TrainStub */
/* renamed from: com.beastbikes.android.modules.train.a.e */
public interface C1439e extends C1600d {
    @C1446c(a = "/train_ftp/")
    /* renamed from: a */
    JSONObject m7390a();

    @C1447d(a = "/user/train")
    /* renamed from: a */
    JSONObject m7391a(@C1444a(a = "height") double d, @C1444a(a = "sex") String str, @C1444a(a = "weight") double d2, @C1444a(a = "cardiac_rate") int i, @C1444a(a = "birthday") long j);

    @C1447d(a = "/user/train")
    /* renamed from: a */
    JSONObject m7392a(@C1444a(a = "cardiac_rate") int i);

    @C1448e(a = "/train_ftp/{id}/")
    /* renamed from: a */
    JSONObject m7393a(@C1451h(a = "id") int i, @C1444a(a = "ftp") int i2, @C1444a(a = "start_date") long j);

    @C1447d(a = "/train_ftp/")
    /* renamed from: a */
    JSONObject m7394a(@C1444a(a = "ftp") int i, @C1444a(a = "start_date") long j);

    @C1446c(a = "/user/train")
    /* renamed from: b */
    JSONObject m7395b();
}
