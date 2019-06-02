package io.fabric.sdk.android.services.p094c;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Build.VERSION;
import io.fabric.sdk.android.C1468h;

/* compiled from: PreferenceStoreImpl */
/* renamed from: io.fabric.sdk.android.services.c.c */
public class C1521c implements C4862b {
    /* renamed from: a */
    private final SharedPreferences f7177a;
    /* renamed from: b */
    private final String f7178b;
    /* renamed from: c */
    private final Context f7179c;

    public C1521c(Context context, String str) {
        if (context == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.f7179c = context;
        this.f7178b = str;
        this.f7177a = this.f7179c.getSharedPreferences(this.f7178b, 0);
    }

    @Deprecated
    public C1521c(C1468h c1468h) {
        this(c1468h.m8079q(), c1468h.getClass().getName());
    }

    /* renamed from: a */
    public SharedPreferences m8370a() {
        return this.f7177a;
    }

    /* renamed from: b */
    public Editor m8372b() {
        return this.f7177a.edit();
    }

    @TargetApi(9)
    /* renamed from: a */
    public boolean m8371a(Editor editor) {
        if (VERSION.SDK_INT < 9) {
            return editor.commit();
        }
        editor.apply();
        return true;
    }
}
