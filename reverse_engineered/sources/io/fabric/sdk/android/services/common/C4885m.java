package io.fabric.sdk.android.services.common;

import android.content.Context;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.p204a.C4851b;
import io.fabric.sdk.android.services.p204a.C4852d;

/* compiled from: InstallerPackageNameProvider */
/* renamed from: io.fabric.sdk.android.services.common.m */
public class C4885m {
    /* renamed from: a */
    private final C4852d<String> f17185a = new C48841(this);
    /* renamed from: b */
    private final C4851b<String> f17186b = new C4851b();

    /* compiled from: InstallerPackageNameProvider */
    /* renamed from: io.fabric.sdk.android.services.common.m$1 */
    class C48841 implements C4852d<String> {
        /* renamed from: a */
        final /* synthetic */ C4885m f17184a;

        C48841(C4885m c4885m) {
            this.f17184a = c4885m;
        }

        /* renamed from: a */
        public /* synthetic */ Object mo6250a(Context context) throws Exception {
            return m19195b(context);
        }

        /* renamed from: b */
        public String m19195b(Context context) throws Exception {
            String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
            return installerPackageName == null ? "" : installerPackageName;
        }
    }

    /* renamed from: a */
    public String m19196a(Context context) {
        try {
            String str = (String) this.f17186b.mo6237a(context, this.f17185a);
            if ("".equals(str)) {
                return null;
            }
            return str;
        } catch (Throwable e) {
            C1520c.h().mo6222d("Fabric", "Failed to determine installer package name", e);
            return null;
        }
    }
}
