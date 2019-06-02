package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* compiled from: SyndicatedSdkImpressionEvent */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.k */
public class C1508k extends C1503f {
    @SerializedName("external_ids")
    /* renamed from: f */
    public final C1507a f7079f;
    @SerializedName("device_id_created_at")
    /* renamed from: g */
    public final long f7080g = 0;
    @SerializedName("language")
    /* renamed from: h */
    public final String f7081h;

    /* compiled from: SyndicatedSdkImpressionEvent */
    /* renamed from: com.twitter.sdk.android.core.internal.scribe.k$a */
    public class C1507a {
        @SerializedName("AD_ID")
        /* renamed from: a */
        public final String f7077a;
        /* renamed from: b */
        final /* synthetic */ C1508k f7078b;

        public C1507a(C1508k c1508k, String str) {
            this.f7078b = c1508k;
            this.f7077a = str;
        }
    }

    public C1508k(C1502c c1502c, long j, String str, String str2, List<C1506j> list) {
        super("syndicated_sdk_impression", c1502c, j, list);
        this.f7081h = str;
        this.f7079f = new C1507a(this, str2);
    }
}
