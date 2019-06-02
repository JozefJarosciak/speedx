package com.facebook;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.alipay.sdk.util.C0880h;
import com.avos.avoscloud.AVStatus;
import com.baidu.mapapi.SDKInitializer;
import com.facebook.internal.C3017h;
import com.facebook.internal.C3048s;
import com.facebook.internal.C3048s.C3045b;
import java.net.HttpURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public final class FacebookRequestError implements Parcelable {
    public static final Creator<FacebookRequestError> CREATOR = new C29401();
    /* renamed from: a */
    static final C2941a f13376a = new C2941a(200, 299);
    /* renamed from: b */
    private final Category f13377b;
    /* renamed from: c */
    private final int f13378c;
    /* renamed from: d */
    private final int f13379d;
    /* renamed from: e */
    private final int f13380e;
    /* renamed from: f */
    private final String f13381f;
    /* renamed from: g */
    private final String f13382g;
    /* renamed from: h */
    private final String f13383h;
    /* renamed from: i */
    private final String f13384i;
    /* renamed from: j */
    private final String f13385j;
    /* renamed from: k */
    private final JSONObject f13386k;
    /* renamed from: l */
    private final JSONObject f13387l;
    /* renamed from: m */
    private final Object f13388m;
    /* renamed from: n */
    private final HttpURLConnection f13389n;
    /* renamed from: o */
    private final FacebookException f13390o;

    /* renamed from: com.facebook.FacebookRequestError$1 */
    static class C29401 implements Creator<FacebookRequestError> {
        C29401() {
        }

        public /* synthetic */ Object createFromParcel(Parcel parcel) {
            return m14296a(parcel);
        }

        public /* synthetic */ Object[] newArray(int i) {
            return m14297a(i);
        }

        /* renamed from: a */
        public FacebookRequestError m14296a(Parcel parcel) {
            return new FacebookRequestError(parcel);
        }

        /* renamed from: a */
        public FacebookRequestError[] m14297a(int i) {
            return new FacebookRequestError[i];
        }
    }

    public enum Category {
        LOGIN_RECOVERABLE,
        OTHER,
        TRANSIENT
    }

    /* renamed from: com.facebook.FacebookRequestError$a */
    private static class C2941a {
        /* renamed from: a */
        private final int f13374a;
        /* renamed from: b */
        private final int f13375b;

        private C2941a(int i, int i2) {
            this.f13374a = i;
            this.f13375b = i2;
        }

        /* renamed from: a */
        boolean m14298a(int i) {
            return this.f13374a <= i && i <= this.f13375b;
        }
    }

    private FacebookRequestError(int i, int i2, int i3, String str, String str2, String str3, String str4, boolean z, JSONObject jSONObject, JSONObject jSONObject2, Object obj, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        Category category;
        this.f13378c = i;
        this.f13379d = i2;
        this.f13380e = i3;
        this.f13381f = str;
        this.f13382g = str2;
        this.f13387l = jSONObject;
        this.f13386k = jSONObject2;
        this.f13388m = obj;
        this.f13389n = httpURLConnection;
        this.f13383h = str3;
        this.f13384i = str4;
        Object obj2 = null;
        if (facebookException != null) {
            this.f13390o = facebookException;
            obj2 = 1;
        } else {
            this.f13390o = new FacebookServiceException(this, str2);
        }
        C3017h g = m14300g();
        if (obj2 != null) {
            category = Category.OTHER;
        } else {
            category = g.m14588a(i2, i3, z);
        }
        this.f13377b = category;
        this.f13385j = g.m14589a(this.f13377b);
    }

    FacebookRequestError(HttpURLConnection httpURLConnection, Exception exception) {
        FacebookException facebookException;
        if (exception instanceof FacebookException) {
            facebookException = (FacebookException) exception;
        } else {
            facebookException = new FacebookException((Throwable) exception);
        }
        this(-1, -1, -1, null, null, null, null, false, null, null, null, httpURLConnection, facebookException);
    }

    public FacebookRequestError(int i, String str, String str2) {
        this(-1, i, -1, str, str2, null, null, false, null, null, null, null, null);
    }

    /* renamed from: a */
    public int m14301a() {
        return this.f13378c;
    }

    /* renamed from: b */
    public int m14302b() {
        return this.f13379d;
    }

    /* renamed from: c */
    public int m14303c() {
        return this.f13380e;
    }

    /* renamed from: d */
    public String m14304d() {
        return this.f13381f;
    }

    /* renamed from: e */
    public String m14305e() {
        if (this.f13382g != null) {
            return this.f13382g;
        }
        return this.f13390o.getLocalizedMessage();
    }

    /* renamed from: f */
    public FacebookException m14306f() {
        return this.f13390o;
    }

    public String toString() {
        return "{HttpStatus: " + this.f13378c + ", errorCode: " + this.f13379d + ", errorType: " + this.f13381f + ", errorMessage: " + m14305e() + C0880h.f2222d;
    }

    /* renamed from: a */
    static FacebookRequestError m14299a(JSONObject jSONObject, Object obj, HttpURLConnection httpURLConnection) {
        try {
            if (jSONObject.has("code")) {
                JSONObject jSONObject2;
                int i = jSONObject.getInt("code");
                Object a = C3048s.m14730a(jSONObject, "body", "FACEBOOK_NON_JSON_RESULT");
                if (a != null && (a instanceof JSONObject)) {
                    jSONObject2 = (JSONObject) a;
                    String str = null;
                    String str2 = null;
                    String str3 = null;
                    String str4 = null;
                    boolean z = false;
                    int i2 = -1;
                    int i3 = -1;
                    Object obj2 = null;
                    if (jSONObject2.has("error")) {
                        JSONObject jSONObject3 = (JSONObject) C3048s.m14730a(jSONObject2, "error", null);
                        str = jSONObject3.optString("type", null);
                        str2 = jSONObject3.optString(AVStatus.MESSAGE_TAG, null);
                        i2 = jSONObject3.optInt("code", -1);
                        i3 = jSONObject3.optInt("error_subcode", -1);
                        str3 = jSONObject3.optString("error_user_msg", null);
                        str4 = jSONObject3.optString("error_user_title", null);
                        z = jSONObject3.optBoolean("is_transient", false);
                        obj2 = 1;
                    } else if (jSONObject2.has(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE) || jSONObject2.has("error_msg") || jSONObject2.has("error_reason")) {
                        str = jSONObject2.optString("error_reason", null);
                        str2 = jSONObject2.optString("error_msg", null);
                        i2 = jSONObject2.optInt(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, -1);
                        i3 = jSONObject2.optInt("error_subcode", -1);
                        obj2 = 1;
                    }
                    if (obj2 != null) {
                        return new FacebookRequestError(i, i2, i3, str, str2, str4, str3, z, jSONObject2, jSONObject, obj, httpURLConnection, null);
                    }
                }
                if (!f13376a.m14298a(i)) {
                    if (jSONObject.has("body")) {
                        jSONObject2 = (JSONObject) C3048s.m14730a(jSONObject, "body", "FACEBOOK_NON_JSON_RESULT");
                    } else {
                        jSONObject2 = null;
                    }
                    return new FacebookRequestError(i, -1, -1, null, null, null, null, false, jSONObject2, jSONObject, obj, httpURLConnection, null);
                }
            }
        } catch (JSONException e) {
        }
        return null;
    }

    /* renamed from: g */
    static synchronized C3017h m14300g() {
        C3017h a;
        synchronized (FacebookRequestError.class) {
            C3045b c = C3048s.m14772c(C1472c.i());
            if (c == null) {
                a = C3017h.m14584a();
            } else {
                a = c.m14718d();
            }
        }
        return a;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f13378c);
        parcel.writeInt(this.f13379d);
        parcel.writeInt(this.f13380e);
        parcel.writeString(this.f13381f);
        parcel.writeString(this.f13382g);
        parcel.writeString(this.f13383h);
        parcel.writeString(this.f13384i);
    }

    private FacebookRequestError(Parcel parcel) {
        this(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readString(), parcel.readString(), parcel.readString(), parcel.readString(), false, null, null, null, null, null);
    }

    public int describeContents() {
        return 0;
    }
}
