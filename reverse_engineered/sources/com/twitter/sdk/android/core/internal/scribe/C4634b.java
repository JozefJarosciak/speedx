package com.twitter.sdk.android.core.internal.scribe;

import android.content.Context;
import io.fabric.sdk.android.services.p202b.C4627k;
import io.fabric.sdk.android.services.p202b.C4633b;
import java.util.concurrent.ScheduledExecutorService;

/* compiled from: EnabledScribeStrategy */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.b */
class C4634b extends C4633b<C1503f> {
    /* renamed from: f */
    private final C4627k f16329f;

    public C4634b(Context context, ScheduledExecutorService scheduledExecutorService, C4639h c4639h, C4635e c4635e, ScribeFilesSender scribeFilesSender) {
        super(context, scheduledExecutorService, c4639h);
        this.f16329f = scribeFilesSender;
        m18330a(c4635e.f16343h);
    }

    /* renamed from: a */
    public C4627k mo6147a() {
        return this.f16329f;
    }
}
