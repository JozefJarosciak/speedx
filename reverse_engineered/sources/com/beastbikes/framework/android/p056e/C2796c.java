package com.beastbikes.framework.android.p056e;

import android.content.Context;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/* compiled from: RequestQueueFactory */
/* renamed from: com.beastbikes.framework.android.e.c */
public class C2796c {
    /* renamed from: a */
    private static C2796c f13015a;
    /* renamed from: b */
    private RequestQueue f13016b;

    private C2796c(Context context) {
        this.f13016b = m13747b(context);
    }

    /* renamed from: a */
    public static synchronized C2796c m13744a(Context context) {
        C2796c c2796c;
        synchronized (C2796c.class) {
            if (f13015a == null) {
                f13015a = new C2796c(context);
            }
            c2796c = f13015a;
        }
        return c2796c;
    }

    /* renamed from: b */
    public RequestQueue m13747b(Context context) {
        if (this.f13016b == null) {
            this.f13016b = Volley.newRequestQueue(context.getApplicationContext());
        }
        return this.f13016b;
    }

    /* renamed from: a */
    public <T> void m13745a(Request<T> request, Object obj) {
        if (obj == null) {
            obj = this;
        }
        request.setTag(obj);
        this.f13016b.add(request);
    }

    /* renamed from: a */
    public void m13746a(Object obj) {
        if (this.f13016b != null) {
            this.f13016b.cancelAll(obj == null ? this : obj);
            if (obj == null) {
                this.f13016b.stop();
            }
        }
    }
}
