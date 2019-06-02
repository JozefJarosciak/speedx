package com.beastbikes.android.modules.cycling.p064b.p065a;

import android.app.Activity;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.framework.business.BusinessException;
import com.beastbikes.framework.business.C1372b;
import com.beastbikes.framework.business.C1397a;
import org.json.JSONObject;

/* compiled from: TaskManager */
/* renamed from: com.beastbikes.android.modules.cycling.b.a.a */
public class C2047a extends C1397a {
    /* renamed from: a */
    Activity f9326a;
    /* renamed from: b */
    private final C1401b f9327b;

    public C2047a(Activity activity) {
        super((C1372b) activity.getApplicationContext());
        this.f9326a = activity;
        this.f9327b = (C1401b) new C1772d(activity).m9399a(C1401b.class, C1768c.f8075a, C1768c.m9391a(activity));
    }

    /* renamed from: a */
    public JSONObject m10529a(String str, int i, String str2) throws BusinessException {
        try {
            return this.f9327b.a(i, str, str2);
        } catch (Throwable e) {
            throw new BusinessException(e);
        }
    }
}
