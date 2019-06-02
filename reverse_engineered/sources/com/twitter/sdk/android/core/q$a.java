package com.twitter.sdk.android.core;

import android.text.TextUtils;
import com.google.gson.Gson;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.p094c.C4574e;

/* compiled from: TwitterSession */
class q$a implements C4574e<C1514q> {
    /* renamed from: a */
    private final Gson f16413a = new Gson();

    /* renamed from: b */
    public /* synthetic */ Object mo6122b(String str) {
        return m18404a(str);
    }

    /* renamed from: a */
    public String m18405a(C1514q c1514q) {
        if (!(c1514q == null || c1514q.d() == null)) {
            try {
                return this.f16413a.toJson(c1514q);
            } catch (Exception e) {
                C1520c.h().mo6215a("Twitter", e.getMessage());
            }
        }
        return "";
    }

    /* renamed from: a */
    public C1514q m18404a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                return (C1514q) this.f16413a.fromJson(str, C1514q.class);
            } catch (Exception e) {
                C1520c.h().mo6215a("Twitter", e.getMessage());
            }
        }
        return null;
    }
}
