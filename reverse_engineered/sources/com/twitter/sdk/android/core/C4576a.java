package com.twitter.sdk.android.core;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.internal.oauth.OAuth2Token;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.p094c.C4574e;

/* compiled from: AppSession */
/* renamed from: com.twitter.sdk.android.core.a */
public class C4576a extends C1469k<OAuth2Token> {

    /* compiled from: AppSession */
    /* renamed from: com.twitter.sdk.android.core.a$a */
    static class C4575a implements C4574e<C4576a> {
        /* renamed from: a */
        private final Gson f16202a = new GsonBuilder().registerTypeAdapter(OAuth2Token.class, new C4577c()).create();

        /* renamed from: b */
        public /* synthetic */ Object mo6122b(String str) {
            return m18138a(str);
        }

        /* renamed from: a */
        public String m18139a(C4576a c4576a) {
            if (!(c4576a == null || c4576a.d() == null)) {
                try {
                    return this.f16202a.toJson(c4576a);
                } catch (Exception e) {
                    C1520c.h().mo6215a("Twitter", "Failed to serialize session " + e.getMessage());
                }
            }
            return "";
        }

        /* renamed from: a */
        public C4576a m18138a(String str) {
            if (!TextUtils.isEmpty(str)) {
                try {
                    return (C4576a) this.f16202a.fromJson(str, C4576a.class);
                } catch (Exception e) {
                    C1520c.h().mo6215a("Twitter", "Failed to deserialize session " + e.getMessage());
                }
            }
            return null;
        }
    }

    C4576a(OAuth2Token oAuth2Token) {
        super(oAuth2Token, 0);
    }
}
