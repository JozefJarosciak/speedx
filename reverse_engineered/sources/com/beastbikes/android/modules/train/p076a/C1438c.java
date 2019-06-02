package com.beastbikes.android.modules.train.p076a;

import com.beastbikes.android.sphere.restful.C1600d;
import com.beastbikes.android.sphere.restful.p078a.C1444a;
import com.beastbikes.android.sphere.restful.p078a.C1446c;
import com.beastbikes.android.sphere.restful.p078a.C1447d;
import com.beastbikes.android.sphere.restful.p078a.C1451h;
import com.beastbikes.android.sphere.restful.p078a.C1452i;
import org.json.JSONObject;

/* compiled from: TrainCourseStub */
/* renamed from: com.beastbikes.android.modules.train.a.c */
public interface C1438c extends C1600d {
    @C1446c(a = "/trainings/index")
    /* renamed from: a */
    JSONObject m7382a();

    @C1446c(a = "/trainings/courses/{course_id}")
    /* renamed from: a */
    JSONObject m7383a(@C1451h(a = "course_id") int i);

    @C1447d(a = "/trainings")
    /* renamed from: a */
    JSONObject m7384a(@C1444a(a = "course_type") int i, @C1444a(a = "course_id") int i2);

    @C1446c(a = "/routes/calendar")
    /* renamed from: a */
    JSONObject m7385a(@C1452i(a = "year") int i, @C1452i(a = "month") int i2, @C1452i(a = "type") int i3);

    @C1447d(a = "/trainings")
    /* renamed from: a */
    JSONObject m7386a(@C1444a(a = "frequency") int i, @C1444a(a = "single_time_type") int i2, @C1444a(a = "cycle") int i3, @C1444a(a = "course_type") int i4);

    @C1446c(a = "/trainings/courses")
    /* renamed from: b */
    JSONObject m7387b();

    @C1446c(a = "/trainings/{training_id}")
    /* renamed from: b */
    JSONObject m7388b(@C1451h(a = "training_id") int i);

    @C1446c(a = "/trainings/{training_id}/finish")
    /* renamed from: c */
    JSONObject m7389c(@C1451h(a = "training_id") int i);
}
