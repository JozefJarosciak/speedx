package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import io.fabric.sdk.android.services.common.C4878j;
import io.fabric.sdk.android.services.p202b.C4636c;
import io.fabric.sdk.android.services.p202b.C4638d;
import io.fabric.sdk.android.services.p202b.C4859l;
import java.io.IOException;
import java.util.UUID;

/* compiled from: ScribeFilesManager */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.h */
class C4639h extends C4638d<C1503f> {
    public C4639h(Context context, C4636c<C1503f> c4636c, C4878j c4878j, C4859l c4859l, int i) throws IOException {
        super(context, c4636c, c4878j, c4859l, i);
    }

    /* renamed from: a */
    protected String mo6149a() {
        return "se" + "_" + UUID.randomUUID().toString() + "_" + this.c.mo6251a() + ".tap";
    }
}
