package com.facebook;

import android.support.v4.os.EnvironmentCompat;
import com.alipay.sdk.util.C0880h;
import com.facebook.internal.C3025m;
import com.facebook.internal.C3048s;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.apache.http.HttpStatus;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* compiled from: GraphResponse */
/* renamed from: com.facebook.f */
public class C2987f {
    /* renamed from: a */
    private final HttpURLConnection f13508a;
    /* renamed from: b */
    private final JSONObject f13509b;
    /* renamed from: c */
    private final JSONArray f13510c;
    /* renamed from: d */
    private final FacebookRequestError f13511d;
    /* renamed from: e */
    private final String f13512e;
    /* renamed from: f */
    private final GraphRequest f13513f;

    C2987f(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject) {
        this(graphRequest, httpURLConnection, str, jSONObject, null, null);
    }

    C2987f(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONArray jSONArray) {
        this(graphRequest, httpURLConnection, str, null, jSONArray, null);
    }

    C2987f(GraphRequest graphRequest, HttpURLConnection httpURLConnection, FacebookRequestError facebookRequestError) {
        this(graphRequest, httpURLConnection, null, null, null, facebookRequestError);
    }

    C2987f(GraphRequest graphRequest, HttpURLConnection httpURLConnection, String str, JSONObject jSONObject, JSONArray jSONArray, FacebookRequestError facebookRequestError) {
        this.f13513f = graphRequest;
        this.f13508a = httpURLConnection;
        this.f13512e = str;
        this.f13509b = jSONObject;
        this.f13510c = jSONArray;
        this.f13511d = facebookRequestError;
    }

    /* renamed from: a */
    public final FacebookRequestError m14499a() {
        return this.f13511d;
    }

    /* renamed from: b */
    public final JSONObject m14500b() {
        return this.f13509b;
    }

    public String toString() {
        String format;
        try {
            Locale locale = Locale.US;
            String str = "%d";
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf(this.f13508a != null ? this.f13508a.getResponseCode() : 200);
            format = String.format(locale, str, objArr);
        } catch (IOException e) {
            format = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        return "{Response: " + " responseCode: " + format + ", graphObject: " + this.f13509b + ", error: " + this.f13511d + C0880h.f2222d;
    }

    /* renamed from: a */
    static List<C2987f> m14496a(HttpURLConnection httpURLConnection, C2986e c2986e) {
        List<C2987f> a;
        Closeable closeable = null;
        try {
            if (httpURLConnection.getResponseCode() >= HttpStatus.SC_BAD_REQUEST) {
                closeable = httpURLConnection.getErrorStream();
            } else {
                closeable = httpURLConnection.getInputStream();
            }
            a = C2987f.m14494a((InputStream) closeable, httpURLConnection, c2986e);
        } catch (FacebookException e) {
            C3025m.m14620a(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", e);
            a = C2987f.m14498a((List) c2986e, httpURLConnection, e);
        } catch (Throwable e2) {
            C3025m.m14620a(LoggingBehavior.REQUESTS, "Response", "Response <Error>: %s", e2);
            a = C2987f.m14498a((List) c2986e, httpURLConnection, new FacebookException(e2));
        } finally {
            C3048s.m14750a(closeable);
        }
        return a;
    }

    /* renamed from: a */
    static List<C2987f> m14494a(InputStream inputStream, HttpURLConnection httpURLConnection, C2986e c2986e) throws FacebookException, JSONException, IOException {
        C3025m.m14620a(LoggingBehavior.INCLUDE_RAW_RESPONSES, "Response", "Response (raw)\n  Size: %d\n  Response:\n%s\n", Integer.valueOf(C3048s.m14734a(inputStream).length()), r0);
        return C2987f.m14495a(C3048s.m14734a(inputStream), httpURLConnection, c2986e);
    }

    /* renamed from: a */
    static List<C2987f> m14495a(String str, HttpURLConnection httpURLConnection, C2986e c2986e) throws FacebookException, JSONException, IOException {
        C3025m.m14620a(LoggingBehavior.REQUESTS, "Response", "Response\n  Id: %s\n  Size: %d\n  Responses:\n%s\n", c2986e.m14484b(), Integer.valueOf(str.length()), C2987f.m14497a(httpURLConnection, (List) c2986e, new JSONTokener(str).nextValue()));
        return C2987f.m14497a(httpURLConnection, (List) c2986e, new JSONTokener(str).nextValue());
    }

    /* renamed from: a */
    private static List<C2987f> m14497a(HttpURLConnection httpURLConnection, List<GraphRequest> list, Object obj) throws FacebookException, JSONException {
        JSONArray jSONArray;
        int i = 0;
        int size = list.size();
        List<C2987f> arrayList = new ArrayList(size);
        if (size == 1) {
            GraphRequest graphRequest = (GraphRequest) list.get(0);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("body", obj);
                jSONObject.put("code", httpURLConnection != null ? httpURLConnection.getResponseCode() : 200);
                jSONArray = new JSONArray();
                jSONArray.put(jSONObject);
            } catch (Exception e) {
                arrayList.add(new C2987f(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, e)));
                jSONArray = obj;
            } catch (Exception e2) {
                arrayList.add(new C2987f(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, e2)));
            }
            if ((jSONArray instanceof JSONArray) || jSONArray.length() != size) {
                throw new FacebookException("Unexpected number of results");
            }
            jSONArray = jSONArray;
            while (i < jSONArray.length()) {
                graphRequest = (GraphRequest) list.get(i);
                try {
                    arrayList.add(C2987f.m14493a(graphRequest, httpURLConnection, jSONArray.get(i), obj));
                } catch (Exception e3) {
                    arrayList.add(new C2987f(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, e3)));
                } catch (Exception e32) {
                    arrayList.add(new C2987f(graphRequest, httpURLConnection, new FacebookRequestError(httpURLConnection, e32)));
                }
                i++;
            }
            return arrayList;
        }
        jSONArray = obj;
        if (jSONArray instanceof JSONArray) {
        }
        throw new FacebookException("Unexpected number of results");
    }

    /* renamed from: a */
    private static C2987f m14493a(GraphRequest graphRequest, HttpURLConnection httpURLConnection, Object obj, Object obj2) throws JSONException {
        if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            FacebookRequestError a = FacebookRequestError.m14299a(jSONObject, obj2, httpURLConnection);
            if (a != null) {
                if (a.m14302b() == 190 && C3048s.m14759a(graphRequest.m14381f())) {
                    AccessToken.m14274a(null);
                }
                return new C2987f(graphRequest, httpURLConnection, a);
            }
            Object a2 = C3048s.m14730a(jSONObject, "body", "FACEBOOK_NON_JSON_RESULT");
            if (a2 instanceof JSONObject) {
                return new C2987f(graphRequest, httpURLConnection, a2.toString(), (JSONObject) a2);
            }
            if (a2 instanceof JSONArray) {
                return new C2987f(graphRequest, httpURLConnection, a2.toString(), (JSONArray) a2);
            }
            obj = JSONObject.NULL;
        }
        if (obj == JSONObject.NULL) {
            return new C2987f(graphRequest, httpURLConnection, obj.toString(), (JSONObject) null);
        }
        throw new FacebookException("Got unexpected object type in response, class: " + obj.getClass().getSimpleName());
    }

    /* renamed from: a */
    static List<C2987f> m14498a(List<GraphRequest> list, HttpURLConnection httpURLConnection, FacebookException facebookException) {
        int size = list.size();
        List<C2987f> arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new C2987f((GraphRequest) list.get(i), httpURLConnection, new FacebookRequestError(httpURLConnection, (Exception) facebookException)));
        }
        return arrayList;
    }
}
