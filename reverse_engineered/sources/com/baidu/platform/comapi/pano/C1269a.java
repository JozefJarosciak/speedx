package com.baidu.platform.comapi.pano;

import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.alipay.sdk.packet.C0861d;
import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.http.HttpClient.HttpStateError;
import com.baidu.platform.comjni.util.AppMD5;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import org.apache.http.HttpHost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.comapi.pano.a */
public class C1269a {
    /* renamed from: a */
    AsyncHttpClient f3849a = new AsyncHttpClient();

    /* renamed from: com.baidu.platform.comapi.pano.a$a */
    public interface C1200a<T> {
        /* renamed from: a */
        void mo2662a(HttpStateError httpStateError);

        /* renamed from: a */
        void mo2663a(T t);
    }

    /* renamed from: a */
    private C1271c m4807a(String str) {
        if (str == null || str.equals("")) {
            return new C1271c(PanoStateError.PANO_NOT_FOUND);
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            if (optJSONObject == null) {
                return new C1271c(PanoStateError.PANO_NOT_FOUND);
            }
            if (optJSONObject.optInt("error") != 0) {
                return new C1271c(PanoStateError.PANO_UID_ERROR);
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("content");
            if (optJSONArray == null) {
                return new C1271c(PanoStateError.PANO_NOT_FOUND);
            }
            C1271c c1271c = null;
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i).optJSONObject("poiinfo");
                if (optJSONObject2 != null) {
                    c1271c = new C1271c(PanoStateError.PANO_NO_ERROR);
                    c1271c.m4813a(optJSONObject2.optString("PID"));
                    c1271c.m4812a(optJSONObject2.optInt("hasstreet"));
                }
            }
            return c1271c;
        } catch (JSONException e) {
            e.printStackTrace();
            return new C1271c(PanoStateError.PANO_NOT_FOUND);
        }
    }

    /* renamed from: a */
    private String m4808a(Builder builder) {
        Builder buildUpon = Uri.parse(builder.build().toString() + HttpClient.getPhoneInfo()).buildUpon();
        buildUpon.appendQueryParameter("sign", AppMD5.getSignMD5String(buildUpon.build().getEncodedQuery()));
        return buildUpon.build().toString();
    }

    /* renamed from: a */
    private void m4809a(Builder builder, String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
            builder.appendQueryParameter(str, str2);
        }
    }

    /* renamed from: a */
    public void m4810a(String str, C1200a<C1271c> c1200a) {
        Builder builder = new Builder();
        builder.scheme(HttpHost.DEFAULT_SCHEME_NAME);
        builder.encodedAuthority("api.map.baidu.com");
        builder.path("/sdkproxy/lbs_androidsdk/pano/v1/");
        m4809a(builder, "qt", GeocodingCriteria.TYPE_POI);
        m4809a(builder, "uid", str);
        m4809a(builder, C0861d.f2143o, "0");
        String authToken = HttpClient.getAuthToken();
        if (authToken == null) {
            c1200a.mo2663a(new C1271c(PanoStateError.PANO_NO_TOKEN));
            return;
        }
        m4809a(builder, "token", authToken);
        this.f3849a.get(m4808a(builder), new C1270b(this, c1200a));
    }
}
