package com.twitter.sdk.android.core.internal.scribe;

import com.google.gson.Gson;
import io.fabric.sdk.android.services.p202b.C4636c;
import java.io.IOException;

/* compiled from: ScribeEvent */
public class f$a implements C4636c<C1503f> {
    /* renamed from: a */
    private final Gson f16344a;

    public f$a(Gson gson) {
        this.f16344a = gson;
    }

    /* renamed from: a */
    public byte[] m18347a(C1503f c1503f) throws IOException {
        return this.f16344a.toJson(c1503f).getBytes("UTF-8");
    }
}
