package com.mob.commons.logcollector;

import android.content.Context;
import com.mob.tools.utils.SharePrefrenceHelper;

/* compiled from: LogsSharePrefrence */
/* renamed from: com.mob.commons.logcollector.d */
public class C4254d {
    /* renamed from: a */
    private static C4254d f14942a;
    /* renamed from: b */
    private SharePrefrenceHelper f14943b;

    private C4254d(Context context) {
        this.f14943b = new SharePrefrenceHelper(context.getApplicationContext());
        this.f14943b.open("mob_sdk_exception", 1);
    }

    /* renamed from: a */
    public static C4254d m16916a(Context context) {
        if (f14942a == null) {
            f14942a = new C4254d(context);
        }
        return f14942a;
    }

    /* renamed from: a */
    public void m16919a(long j) {
        this.f14943b.putLong("service_time", Long.valueOf(j));
    }

    /* renamed from: a */
    public long m16917a() {
        return this.f14943b.getLong("service_time");
    }

    /* renamed from: a */
    public void m16921a(boolean z) {
        this.f14943b.putInt("is_upload_err_log", Integer.valueOf(z ? 0 : 1));
    }

    /* renamed from: b */
    public boolean m16923b() {
        return this.f14943b.getInt("is_upload_err_log") == 0;
    }

    /* renamed from: a */
    public void m16918a(int i) {
        this.f14943b.putInt("is_upload_crash", Integer.valueOf(i));
    }

    /* renamed from: c */
    public int m16924c() {
        return this.f14943b.getInt("is_upload_crash");
    }

    /* renamed from: b */
    public void m16922b(int i) {
        this.f14943b.putInt("is_upload_sdkerr", Integer.valueOf(i));
    }

    /* renamed from: d */
    public int m16926d() {
        return this.f14943b.getInt("is_upload_sdkerr");
    }

    /* renamed from: c */
    public void m16925c(int i) {
        this.f14943b.putInt("is_upload_apperr", Integer.valueOf(i));
    }

    /* renamed from: e */
    public int m16927e() {
        return this.f14943b.getInt("is_upload_apperr");
    }

    /* renamed from: f */
    public String m16928f() {
        return this.f14943b.getString("err_log_filter");
    }

    /* renamed from: a */
    public void m16920a(String str) {
        this.f14943b.putString("err_log_filter", str);
    }
}
