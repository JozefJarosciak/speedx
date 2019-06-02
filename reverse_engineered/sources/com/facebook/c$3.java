package com.facebook;

import android.content.Context;
import com.facebook.appevents.AppEventsLogger;
import java.util.concurrent.Callable;

/* compiled from: FacebookSdk */
class c$3 implements Callable<Void> {
    /* renamed from: a */
    final /* synthetic */ c$a f13495a;
    /* renamed from: b */
    final /* synthetic */ Context f13496b;

    c$3(c$a c_a, Context context) {
        this.f13495a = c_a;
        this.f13496b = context;
    }

    public /* synthetic */ Object call() throws Exception {
        return m14471a();
    }

    /* renamed from: a */
    public Void m14471a() throws Exception {
        C2983b.m14457a().m14468c();
        C2990i.m14512a().m14517c();
        if (AccessToken.m14270a() != null && Profile.m14394a() == null) {
            Profile.m14396b();
        }
        if (this.f13495a != null) {
            this.f13495a.m14472a();
        }
        AppEventsLogger.m14408a(this.f13496b.getApplicationContext()).m14417b();
        return null;
    }
}
