package com.beastbikes.android.locale.googlemaputils;

import android.content.Context;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import com.android.volley.Request;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.beastbikes.framework.android.p056e.C2796c;
import java.lang.ref.WeakReference;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: GoogleMapCnAPI */
/* renamed from: com.beastbikes.android.locale.googlemaputils.a */
public class C1855a {
    /* renamed from: f */
    private static int f8341f = 3;
    /* renamed from: g */
    private static boolean f8342g = false;
    /* renamed from: a */
    private Logger f8343a = LoggerFactory.getLogger(C1855a.class);
    /* renamed from: b */
    private final String f8344b = "http://maps.google.cn/maps/api/geocode/json?latlng=";
    /* renamed from: c */
    private C1821c f8345c;
    /* renamed from: d */
    private String[] f8346d = new String[]{"administrative_area_level_1", "administrative_area_level_2", "administrative_area_level_3", "sublocality_level_1", "sublocality_level_2", "sublocality_level_3", "locality"};
    /* renamed from: e */
    private String[] f8347e = new String[]{"locality", "sublocality_level_1", "administrative_area_level_2", "administrative_area_level_3", "administrative_area_level_1", "sublocality_level_2"};
    /* renamed from: h */
    private ArrayMap<String, String> f8348h;
    /* renamed from: i */
    private String[] f8349i = new String[]{"administrative_area_level_1", "administrative_area_level_2", "administrative_area_level_3", "administrative_area_level_4", "administrative_area_level_5", "locality", "sublocality_level_1", "sublocality_level_2", "sublocality_level_3"};

    /* compiled from: GoogleMapCnAPI */
    /* renamed from: com.beastbikes.android.locale.googlemaputils.a$1 */
    class C18531 implements Listener<JSONObject> {
        /* renamed from: a */
        final /* synthetic */ C1855a f8339a;

        C18531(C1855a c1855a) {
            this.f8339a = c1855a;
        }

        public /* synthetic */ void onResponse(Object obj) {
            m9666a((JSONObject) obj);
        }

        /* renamed from: a */
        public void m9666a(JSONObject jSONObject) {
            if (jSONObject.optString("status").equals("OK")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("results");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(0);
                    String optString = optJSONObject.optString("formatted_address");
                    this.f8339a.m9670a(optJSONObject.optJSONArray("address_components"), optString);
                }
            }
        }
    }

    /* compiled from: GoogleMapCnAPI */
    /* renamed from: com.beastbikes.android.locale.googlemaputils.a$2 */
    class C18542 implements ErrorListener {
        /* renamed from: a */
        final /* synthetic */ C1855a f8340a;

        C18542(C1855a c1855a) {
            this.f8340a = c1855a;
        }

        public void onErrorResponse(VolleyError volleyError) {
            if (this.f8340a.f8345c != null) {
                this.f8340a.f8345c.mo3251a(volleyError);
            }
        }
    }

    /* renamed from: a */
    public void m9671a(Context context, C2796c c2796c, double d, double d2, C1821c c1821c) {
        C2796c c2796c2 = (C2796c) new WeakReference(c2796c).get();
        Context context2 = (Context) new WeakReference(context).get();
        if (c1821c != null) {
            this.f8345c = (C1821c) new WeakReference(c1821c).get();
        }
        Request jsonObjectRequest = new JsonObjectRequest("http://maps.google.cn/maps/api/geocode/json?latlng=" + d + "," + d2, null, new C18531(this), new C18542(this));
        if (c2796c2 != null) {
            c2796c2.m13745a(jsonObjectRequest, context);
        }
    }

    /* renamed from: a */
    private void m9670a(JSONArray jSONArray, String str) {
        if (jSONArray != null) {
            String str2 = "";
            String str3 = "";
            this.f8348h = new ArrayMap();
            int i = 0;
            StringBuilder stringBuilder = new StringBuilder();
            int i2 = 0;
            while (i2 < this.f8346d.length) {
                Object obj = this.f8346d[i2];
                String str4 = str3;
                str3 = str2;
                int i3 = 0;
                while (i3 < jSONArray.length() && i != 3) {
                    int i4;
                    String str5;
                    int i5;
                    JSONObject optJSONObject = jSONArray.optJSONObject(i3);
                    String optString = optJSONObject.optJSONArray("types").optString(0);
                    if (!TextUtils.isEmpty(optString)) {
                        if (optString.equals("locality")) {
                            str3 = optJSONObject.optString("long_name");
                        }
                        if (optString.equals("administrative_area_level_1")) {
                            str4 = optJSONObject.optString("long_name");
                        }
                        if (optString.equals(this.f8346d[3]) || optString.equals(this.f8346d[4]) || optString.equals(this.f8346d[5])) {
                            f8342g = true;
                        }
                        if (f8342g && optString.equals(this.f8346d[6])) {
                            i4 = i;
                            str5 = str4;
                            i5 = i4;
                            i3++;
                            i4 = i5;
                            str4 = str5;
                            i = i4;
                        } else if (optString.equals(obj)) {
                            String optString2 = optJSONObject.optString("long_name");
                            this.f8348h.put(optString, optString2);
                            stringBuilder.append(optString2 + ",");
                            i4 = i + 1;
                            str5 = str4;
                            i5 = i4;
                            i3++;
                            i4 = i5;
                            str4 = str5;
                            i = i4;
                        }
                    }
                    i4 = i;
                    str5 = str4;
                    i5 = i4;
                    i3++;
                    i4 = i5;
                    str4 = str5;
                    i = i4;
                }
                i2++;
                str2 = str3;
                str3 = str4;
            }
            if (stringBuilder.length() - 1 >= 0) {
                stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            }
            C1856b c1856b = new C1856b(stringBuilder.toString(), str);
            c1856b.m9675b(str2);
            c1856b.m9673a(str3);
            m9669a(c1856b, jSONArray);
        }
    }

    /* renamed from: a */
    private void m9669a(C1856b c1856b, JSONArray jSONArray) {
        if (jSONArray != null && jSONArray.length() > 0) {
            for (int length = this.f8347e.length - 1; length >= 0; length--) {
                String str = this.f8347e[length];
                for (int i = 0; i < jSONArray.length(); i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (str.equals(optJSONObject.optJSONArray("types").optString(0))) {
                        c1856b.m9675b(optJSONObject.optString("long_name"));
                    }
                }
            }
        }
        if (this.f8345c != null) {
            this.f8345c.mo3252a(c1856b);
        }
    }
}
