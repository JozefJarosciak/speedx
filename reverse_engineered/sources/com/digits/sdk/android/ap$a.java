package com.digits.sdk.android;

import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twitter.sdk.android.core.C1500b;
import com.twitter.sdk.android.core.C4577c;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.p094c.C4574e;

/* compiled from: DigitsSession */
public class ap$a implements C4574e<ap> {
    /* renamed from: a */
    private final Gson f13193a = new GsonBuilder().registerTypeAdapter(C1500b.class, new C4577c()).create();

    /* renamed from: b */
    public /* synthetic */ Object m13982b(String str) {
        return m13979a(str);
    }

    /* renamed from: a */
    public String m13980a(ap apVar) {
        if (!(apVar == null || apVar.d() == null)) {
            try {
                return this.f13193a.toJson((Object) apVar);
            } catch (Exception e) {
                C1520c.h().a("Digits", e.getMessage());
            }
        }
        return "";
    }

    /* renamed from: a */
    public ap m13979a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                ap apVar = (ap) this.f13193a.fromJson(str, ap.class);
                return new ap(apVar.d(), apVar.e(), ap.a(apVar) == null ? "" : ap.a(apVar), ap.b(apVar) == null ? ap.f6876a : ap.b(apVar));
            } catch (Exception e) {
                C1520c.h().a("Digits", e.getMessage());
            }
        }
        return null;
    }
}
