package io.fabric.sdk.android.services.p094c;

import android.content.Context;
import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import java.io.File;

/* compiled from: FileStoreImpl */
/* renamed from: io.fabric.sdk.android.services.c.a */
public class C4861a {
    /* renamed from: a */
    private final Context f17136a;
    /* renamed from: b */
    private final String f17137b;
    /* renamed from: c */
    private final String f17138c;

    public C4861a(C1468h c1468h) {
        if (c1468h.q() == null) {
            throw new IllegalStateException("Cannot get directory before context has been set. Call Fabric.with() first");
        }
        this.f17136a = c1468h.q();
        this.f17137b = c1468h.s();
        this.f17138c = "Android/" + this.f17136a.getPackageName();
    }

    /* renamed from: a */
    public File m19105a() {
        return m19106a(this.f17136a.getFilesDir());
    }

    /* renamed from: a */
    File m19106a(File file) {
        if (file == null) {
            C1520c.h().mo6215a("Fabric", "Null File");
        } else if (file.exists() || file.mkdirs()) {
            return file;
        } else {
            C1520c.h().mo6219c("Fabric", "Couldn't create file");
        }
        return null;
    }
}
