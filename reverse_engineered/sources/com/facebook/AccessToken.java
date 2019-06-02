package com.facebook;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.alipay.sdk.util.C0880h;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3049t;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class AccessToken implements Parcelable {
    public static final Creator<AccessToken> CREATOR = new C29381();
    /* renamed from: a */
    private static final Date f13357a = new Date(Long.MAX_VALUE);
    /* renamed from: b */
    private static final Date f13358b = f13357a;
    /* renamed from: c */
    private static final Date f13359c = new Date();
    /* renamed from: d */
    private static final AccessTokenSource f13360d = AccessTokenSource.FACEBOOK_APPLICATION_WEB;
    /* renamed from: e */
    private final Date f13361e;
    /* renamed from: f */
    private final Set<String> f13362f;
    /* renamed from: g */
    private final Set<String> f13363g;
    /* renamed from: h */
    private final String f13364h;
    /* renamed from: i */
    private final AccessTokenSource f13365i;
    /* renamed from: j */
    private final Date f13366j;
    /* renamed from: k */
    private final String f13367k;
    /* renamed from: l */
    private final String f13368l;

    /* renamed from: com.facebook.AccessToken$1 */
    static class C29381 implements Creator {
        C29381() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m14266a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m14267a(i);
        }

        /* renamed from: a */
        public AccessToken m14266a(Parcel parcel) {
            return new AccessToken(parcel);
        }

        /* renamed from: a */
        public AccessToken[] m14267a(int i) {
            return new AccessToken[i];
        }
    }

    /* renamed from: com.facebook.AccessToken$a */
    public interface C2939a {
        /* renamed from: a */
        void m14268a(AccessToken accessToken);

        /* renamed from: a */
        void m14269a(FacebookException facebookException);
    }

    public AccessToken(String str, String str2, String str3, @Nullable Collection<String> collection, @Nullable Collection<String> collection2, @Nullable AccessTokenSource accessTokenSource, @Nullable Date date, @Nullable Date date2) {
        C3049t.m14791a(str, "accessToken");
        C3049t.m14791a(str2, "applicationId");
        C3049t.m14791a(str3, "userId");
        if (date == null) {
            date = f13358b;
        }
        this.f13361e = date;
        this.f13362f = Collections.unmodifiableSet(collection != null ? new HashSet(collection) : new HashSet());
        this.f13363g = Collections.unmodifiableSet(collection2 != null ? new HashSet(collection2) : new HashSet());
        this.f13364h = str;
        if (accessTokenSource == null) {
            accessTokenSource = f13360d;
        }
        this.f13365i = accessTokenSource;
        if (date2 == null) {
            date2 = f13359c;
        }
        this.f13366j = date2;
        this.f13367k = str2;
        this.f13368l = str3;
    }

    /* renamed from: a */
    public static AccessToken m14270a() {
        return C2983b.m14457a().m14467b();
    }

    /* renamed from: a */
    public static void m14274a(AccessToken accessToken) {
        C2983b.m14457a().m14466a(accessToken);
    }

    /* renamed from: b */
    public String m14277b() {
        return this.f13364h;
    }

    /* renamed from: c */
    public Date m14278c() {
        return this.f13361e;
    }

    /* renamed from: d */
    public Set<String> m14279d() {
        return this.f13362f;
    }

    /* renamed from: e */
    public Set<String> m14280e() {
        return this.f13363g;
    }

    /* renamed from: f */
    public AccessTokenSource m14281f() {
        return this.f13365i;
    }

    /* renamed from: g */
    public Date m14282g() {
        return this.f13366j;
    }

    /* renamed from: h */
    public String m14283h() {
        return this.f13367k;
    }

    /* renamed from: i */
    public String m14284i() {
        return this.f13368l;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{AccessToken");
        stringBuilder.append(" token:").append(m14276k());
        m14275a(stringBuilder);
        stringBuilder.append(C0880h.f2222d);
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean equals(java.lang.Object r5) {
        /*
        r4 = this;
        r0 = 1;
        r1 = 0;
        if (r4 != r5) goto L_0x0005;
    L_0x0004:
        return r0;
    L_0x0005:
        r2 = r5 instanceof com.facebook.AccessToken;
        if (r2 != 0) goto L_0x000b;
    L_0x0009:
        r0 = r1;
        goto L_0x0004;
    L_0x000b:
        r5 = (com.facebook.AccessToken) r5;
        r2 = r4.f13361e;
        r3 = r5.f13361e;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0017:
        r2 = r4.f13362f;
        r3 = r5.f13362f;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0021:
        r2 = r4.f13363g;
        r3 = r5.f13363g;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x002b:
        r2 = r4.f13364h;
        r3 = r5.f13364h;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0035:
        r2 = r4.f13365i;
        r3 = r5.f13365i;
        if (r2 != r3) goto L_0x0057;
    L_0x003b:
        r2 = r4.f13366j;
        r3 = r5.f13366j;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0045:
        r2 = r4.f13367k;
        if (r2 != 0) goto L_0x0059;
    L_0x0049:
        r2 = r5.f13367k;
        if (r2 != 0) goto L_0x0057;
    L_0x004d:
        r2 = r4.f13368l;
        r3 = r5.f13368l;
        r2 = r2.equals(r3);
        if (r2 != 0) goto L_0x0004;
    L_0x0057:
        r0 = r1;
        goto L_0x0004;
    L_0x0059:
        r2 = r4.f13367k;
        r3 = r5.f13367k;
        r2 = r2.equals(r3);
        if (r2 == 0) goto L_0x0057;
    L_0x0063:
        goto L_0x004d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.facebook.AccessToken.equals(java.lang.Object):boolean");
    }

    public int hashCode() {
        return (((this.f13367k == null ? 0 : this.f13367k.hashCode()) + ((((((((((((this.f13361e.hashCode() + 527) * 31) + this.f13362f.hashCode()) * 31) + this.f13363g.hashCode()) * 31) + this.f13364h.hashCode()) * 31) + this.f13365i.hashCode()) * 31) + this.f13366j.hashCode()) * 31)) * 31) + this.f13368l.hashCode();
    }

    /* renamed from: a */
    static AccessToken m14271a(Bundle bundle) {
        Collection a = m14273a(bundle, "com.facebook.TokenCachingStrategy.Permissions");
        Collection a2 = m14273a(bundle, "com.facebook.TokenCachingStrategy.DeclinedPermissions");
        String d = C2988g.m14506d(bundle);
        if (C3048s.m14761a(d)) {
            d = C1472c.i();
        }
        String b = C2988g.m14504b(bundle);
        try {
            return new AccessToken(b, d, C3048s.m14776d(b).getString("id"), a, a2, C2988g.m14505c(bundle), C2988g.m14501a(bundle, "com.facebook.TokenCachingStrategy.ExpirationDate"), C2988g.m14501a(bundle, "com.facebook.TokenCachingStrategy.LastRefreshDate"));
        } catch (JSONException e) {
            return null;
        }
    }

    /* renamed from: a */
    static List<String> m14273a(Bundle bundle, String str) {
        Collection stringArrayList = bundle.getStringArrayList(str);
        if (stringArrayList == null) {
            return Collections.emptyList();
        }
        return Collections.unmodifiableList(new ArrayList(stringArrayList));
    }

    /* renamed from: j */
    JSONObject m14285j() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(MapboxEvent.ATTRIBUTE_VERSION, 1);
        jSONObject.put("token", this.f13364h);
        jSONObject.put("expires_at", this.f13361e.getTime());
        jSONObject.put("permissions", new JSONArray(this.f13362f));
        jSONObject.put("declined_permissions", new JSONArray(this.f13363g));
        jSONObject.put("last_refresh", this.f13366j.getTime());
        jSONObject.put(MapboxEvent.ATTRIBUTE_SOURCE, this.f13365i.name());
        jSONObject.put("application_id", this.f13367k);
        jSONObject.put("user_id", this.f13368l);
        return jSONObject;
    }

    /* renamed from: a */
    static AccessToken m14272a(JSONObject jSONObject) throws JSONException {
        if (jSONObject.getInt(MapboxEvent.ATTRIBUTE_VERSION) > 1) {
            throw new FacebookException("Unknown AccessToken serialization format.");
        }
        String string = jSONObject.getString("token");
        Date date = new Date(jSONObject.getLong("expires_at"));
        JSONArray jSONArray = jSONObject.getJSONArray("permissions");
        JSONArray jSONArray2 = jSONObject.getJSONArray("declined_permissions");
        Date date2 = new Date(jSONObject.getLong("last_refresh"));
        return new AccessToken(string, jSONObject.getString("application_id"), jSONObject.getString("user_id"), C3048s.m14743a(jSONArray), C3048s.m14743a(jSONArray2), AccessTokenSource.valueOf(jSONObject.getString(MapboxEvent.ATTRIBUTE_SOURCE)), date, date2);
    }

    /* renamed from: k */
    private String m14276k() {
        if (this.f13364h == null) {
            return "null";
        }
        if (C1472c.a(LoggingBehavior.INCLUDE_ACCESS_TOKENS)) {
            return this.f13364h;
        }
        return "ACCESS_TOKEN_REMOVED";
    }

    /* renamed from: a */
    private void m14275a(StringBuilder stringBuilder) {
        stringBuilder.append(" permissions:");
        if (this.f13362f == null) {
            stringBuilder.append("null");
            return;
        }
        stringBuilder.append("[");
        stringBuilder.append(TextUtils.join(", ", this.f13362f));
        stringBuilder.append("]");
    }

    AccessToken(Parcel parcel) {
        this.f13361e = new Date(parcel.readLong());
        Collection arrayList = new ArrayList();
        parcel.readStringList(arrayList);
        this.f13362f = Collections.unmodifiableSet(new HashSet(arrayList));
        arrayList.clear();
        parcel.readStringList(arrayList);
        this.f13363g = Collections.unmodifiableSet(new HashSet(arrayList));
        this.f13364h = parcel.readString();
        this.f13365i = AccessTokenSource.valueOf(parcel.readString());
        this.f13366j = new Date(parcel.readLong());
        this.f13367k = parcel.readString();
        this.f13368l = parcel.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.f13361e.getTime());
        parcel.writeStringList(new ArrayList(this.f13362f));
        parcel.writeStringList(new ArrayList(this.f13363g));
        parcel.writeString(this.f13364h);
        parcel.writeString(this.f13365i.name());
        parcel.writeLong(this.f13366j.getTime());
        parcel.writeString(this.f13367k);
        parcel.writeString(this.f13368l);
    }
}
