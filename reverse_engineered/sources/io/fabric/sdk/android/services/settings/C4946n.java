package io.fabric.sdk.android.services.settings;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.common.C4877i;

/* compiled from: IconRequest */
/* renamed from: io.fabric.sdk.android.services.settings.n */
public class C4946n {
    /* renamed from: a */
    public final String f17319a;
    /* renamed from: b */
    public final int f17320b;
    /* renamed from: c */
    public final int f17321c;
    /* renamed from: d */
    public final int f17322d;

    public C4946n(String str, int i, int i2, int i3) {
        this.f17319a = str;
        this.f17320b = i;
        this.f17321c = i2;
        this.f17322d = i3;
    }

    /* renamed from: a */
    public static C4946n m19409a(Context context, String str) {
        if (str != null) {
            try {
                int f = C4877i.m19172f(context);
                C1520c.h().mo6215a("Fabric", "App icon resource ID is " + f);
                Options options = new Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeResource(context.getResources(), f, options);
                return new C4946n(str, f, options.outWidth, options.outHeight);
            } catch (Throwable e) {
                C1520c.h().mo6222d("Fabric", "Failed to load icon", e);
            }
        }
        return null;
    }
}
