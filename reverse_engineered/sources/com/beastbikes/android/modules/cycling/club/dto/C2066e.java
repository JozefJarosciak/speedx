package com.beastbikes.android.modules.cycling.club.dto;

import com.alipay.sdk.authjs.C0840a;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.modules.user.dto.ProfileDTO;
import com.beastbikes.android.utils.C2563k;
import org.json.JSONObject;

/* compiled from: ClubMsgDTO */
/* renamed from: com.beastbikes.android.modules.cycling.club.dto.e */
public class C2066e {
    /* renamed from: a */
    private String f9382a;
    /* renamed from: b */
    private int f9383b;
    /* renamed from: c */
    private int f9384c;
    /* renamed from: d */
    private int f9385d;
    /* renamed from: e */
    private int f9386e;
    /* renamed from: f */
    private String f9387f;
    /* renamed from: g */
    private boolean f9388g;
    /* renamed from: h */
    private String f9389h;
    /* renamed from: i */
    private long f9390i;
    /* renamed from: j */
    private String f9391j;
    /* renamed from: k */
    private JSONObject f9392k;
    /* renamed from: l */
    private ProfileDTO f9393l;
    /* renamed from: m */
    private ClubInfoCompact f9394m;

    public C2066e(JSONObject jSONObject) {
        this.f9382a = jSONObject.optString("senderId");
        this.f9384c = jSONObject.optInt(C0840a.f2032h);
        this.f9385d = jSONObject.optInt("metaId");
        this.f9386e = jSONObject.optInt("status");
        this.f9387f = jSONObject.optString("content");
        this.f9388g = jSONObject.optBoolean("isReply");
        this.f9389h = jSONObject.optString("imageUrl");
        this.f9390i = jSONObject.optLong("stamp");
        this.f9391j = jSONObject.optString("createdAt");
        this.f9383b = jSONObject.optInt("senderType");
        this.f9392k = jSONObject.optJSONObject(C0861d.f2139k);
        JSONObject optJSONObject = jSONObject.optJSONObject("club");
        if (!C2563k.m12869a(optJSONObject)) {
            this.f9394m = new ClubInfoCompact(optJSONObject);
        }
        optJSONObject = jSONObject.optJSONObject("user");
        if (!C2563k.m12869a(optJSONObject)) {
            this.f9393l = new ProfileDTO(optJSONObject);
        }
    }

    /* renamed from: a */
    public String m10642a() {
        return this.f9387f;
    }

    /* renamed from: b */
    public long m10643b() {
        return this.f9390i;
    }

    /* renamed from: c */
    public String m10644c() {
        return this.f9391j;
    }

    /* renamed from: d */
    public int m10645d() {
        return this.f9383b;
    }

    /* renamed from: e */
    public JSONObject m10646e() {
        return this.f9392k;
    }

    /* renamed from: f */
    public ProfileDTO m10647f() {
        return this.f9393l;
    }

    /* renamed from: g */
    public ClubInfoCompact m10648g() {
        return this.f9394m;
    }
}
