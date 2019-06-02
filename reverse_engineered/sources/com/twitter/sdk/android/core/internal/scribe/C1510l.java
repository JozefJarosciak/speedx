package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.annotations.SerializedName;
import java.util.List;

/* compiled from: SyndicationClientEvent */
/* renamed from: com.twitter.sdk.android.core.internal.scribe.l */
public class C1510l extends C1503f {
    @SerializedName("language")
    /* renamed from: f */
    public final String f7084f;
    @SerializedName("external_ids")
    /* renamed from: g */
    public final C1509a f7085g;

    /* compiled from: SyndicationClientEvent */
    /* renamed from: com.twitter.sdk.android.core.internal.scribe.l$a */
    public class C1509a {
        @SerializedName("6")
        /* renamed from: a */
        public final String f7082a;
        /* renamed from: b */
        final /* synthetic */ C1510l f7083b;

        public C1509a(C1510l c1510l, String str) {
            this.f7083b = c1510l;
            this.f7082a = str;
        }
    }

    public C1510l(C1502c c1502c, long j, String str, String str2, List<C1506j> list) {
        super("tfw_client_event", c1502c, j, list);
        this.f7084f = str;
        this.f7085g = new C1509a(this, str2);
    }
}
