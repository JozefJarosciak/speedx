package com.baidu.platform.comjni.map.cloud;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import com.baidu.mapapi.cloud.CloudListener;
import com.baidu.mapapi.cloud.CloudRgcResult;
import com.baidu.mapapi.cloud.CloudSearchResult;
import com.baidu.mapapi.cloud.DetailSearchResult;
import com.baidu.mapapi.http.AsyncHttpClient;
import com.baidu.mapapi.http.HttpClient;
import com.baidu.platform.comjni.util.AppMD5;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.comjni.map.cloud.a */
public class C1284a implements ICloudCenter {
    /* renamed from: a */
    private int f3928a;
    /* renamed from: b */
    private CloudListener f3929b;
    /* renamed from: c */
    private boolean f3930c = true;
    /* renamed from: d */
    private boolean f3931d = true;
    /* renamed from: e */
    private AsyncHttpClient f3932e = new AsyncHttpClient();
    /* renamed from: f */
    private Handler f3933f = new Handler(Looper.getMainLooper());
    /* renamed from: g */
    private String f3934g;

    /* renamed from: a */
    private boolean m4936a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: d */
    private boolean m4940d(String str) {
        if (str == null) {
            return false;
        }
        this.f3932e.get(str, new C1285b(this));
        return true;
    }

    /* renamed from: e */
    private String m4941e(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String authToken = HttpClient.getAuthToken();
        if (authToken == null) {
            return null;
        }
        if (this.f3930c) {
            str = str + "&token=" + AppMD5.encodeUrlParamsValue(authToken);
        }
        String str2 = str + HttpClient.getPhoneInfo();
        if (!this.f3931d) {
            return str2;
        }
        return str2 + "&sign=" + AppMD5.getSignMD5String(Uri.parse(str2).buildUpon().build().getEncodedQuery());
    }

    /* renamed from: f */
    private void m4942f(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (this.f3928a) {
                case 10001:
                    CloudSearchResult cloudSearchResult = new CloudSearchResult();
                    try {
                        cloudSearchResult.parseFromJSON(jSONObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    this.f3929b.onGetSearchResult(cloudSearchResult, cloudSearchResult.status);
                    return;
                case 10002:
                    DetailSearchResult detailSearchResult = new DetailSearchResult();
                    try {
                        detailSearchResult.parseFromJSON(jSONObject);
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                    this.f3929b.onGetDetailSearchResult(detailSearchResult, detailSearchResult.status);
                    return;
                case 10003:
                    CloudRgcResult cloudRgcResult = new CloudRgcResult();
                    try {
                        cloudRgcResult.parseFromJSON(jSONObject);
                    } catch (JSONException e22) {
                        e22.printStackTrace();
                    }
                    this.f3929b.onGetCloudRgcResult(cloudRgcResult, cloudRgcResult.status);
                    return;
                default:
                    return;
            }
        } catch (JSONException e222) {
            e222.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo2678a(CloudListener cloudListener) {
        this.f3929b = cloudListener;
    }

    /* renamed from: a */
    public boolean mo2679a(String str) {
        this.f3928a = 10001;
        this.f3930c = false;
        return m4940d(m4941e(str));
    }

    /* renamed from: b */
    public boolean mo2680b(String str) {
        this.f3928a = 10002;
        this.f3930c = false;
        return m4940d(m4941e(str));
    }

    /* renamed from: c */
    public boolean mo2681c(String str) {
        this.f3928a = 10003;
        this.f3930c = true;
        return m4940d(m4941e(str));
    }
}
