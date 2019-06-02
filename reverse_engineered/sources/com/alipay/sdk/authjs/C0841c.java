package com.alipay.sdk.authjs;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.Toast;
import com.alipay.sdk.authjs.C0840a.C0839a;
import com.android.volley.DefaultRetryPolicy;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.authjs.c */
public final class C0841c {
    /* renamed from: a */
    C0829b f2039a;
    /* renamed from: b */
    Context f2040b;

    /* renamed from: a */
    private static /* synthetic */ int m3246a(C0841c c0841c, C0840a c0840a) {
        if (c0840a != null && "toast".equals(c0840a.f2035k)) {
            JSONObject jSONObject = c0840a.f2037m;
            CharSequence optString = jSONObject.optString("content");
            int optInt = jSONObject.optInt("duration");
            int i = 1;
            if (optInt < DefaultRetryPolicy.DEFAULT_TIMEOUT_MS) {
                i = 0;
            }
            Toast.makeText(c0841c.f2040b, optString, i).show();
            new Timer().schedule(new C0843e(c0841c, c0840a), (long) i);
        }
        return C0839a.f2019a;
    }

    public C0841c(Context context, C0829b c0829b) {
        this.f2040b = context;
        this.f2039a = c0829b;
    }

    /* renamed from: a */
    private void m3250a(String str) {
        String str2;
        try {
            JSONObject jSONObject = new JSONObject(str);
            String string = jSONObject.getString(C0840a.f2029e);
            try {
                if (!TextUtils.isEmpty(string)) {
                    JSONObject jSONObject2 = jSONObject.getJSONObject(C0840a.f2030f);
                    if (jSONObject2 instanceof JSONObject) {
                        jSONObject2 = jSONObject2;
                    } else {
                        jSONObject2 = null;
                    }
                    String string2 = jSONObject.getString(C0840a.f2031g);
                    String string3 = jSONObject.getString(C0840a.f2028d);
                    C0840a c0840a = new C0840a("call");
                    c0840a.f2034j = string3;
                    c0840a.f2035k = string2;
                    c0840a.f2037m = jSONObject2;
                    c0840a.f2033i = string;
                    m3253a(c0840a);
                }
            } catch (Exception e) {
                str2 = string;
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        m3254a(str2, C0839a.f2022d);
                    } catch (JSONException e2) {
                    }
                }
            }
        } catch (Exception e3) {
            str2 = null;
            if (!TextUtils.isEmpty(str2)) {
                m3254a(str2, C0839a.f2022d);
            }
        }
    }

    /* renamed from: a */
    public final void m3253a(C0840a c0840a) throws JSONException {
        if (TextUtils.isEmpty(c0840a.f2035k)) {
            m3254a(c0840a.f2033i, C0839a.f2021c);
            return;
        }
        Runnable c0842d = new C0842d(this, c0840a);
        if ((Looper.getMainLooper() == Looper.myLooper() ? 1 : null) != null) {
            c0842d.run();
        } else {
            new Handler(Looper.getMainLooper()).post(c0842d);
        }
    }

    /* renamed from: a */
    public final void m3254a(String str, int i) throws JSONException {
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("error", i - 1);
            C0840a c0840a = new C0840a(C0840a.f2027c);
            c0840a.f2037m = jSONObject;
            c0840a.f2033i = str;
            this.f2039a.mo2425a(c0840a);
        }
    }

    /* renamed from: a */
    private static void m3249a(Runnable runnable) {
        if ((Looper.getMainLooper() == Looper.myLooper() ? 1 : null) != null) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    /* renamed from: b */
    private int m3251b(C0840a c0840a) {
        if (c0840a != null && "toast".equals(c0840a.f2035k)) {
            JSONObject jSONObject = c0840a.f2037m;
            CharSequence optString = jSONObject.optString("content");
            int optInt = jSONObject.optInt("duration");
            int i = 1;
            if (optInt < DefaultRetryPolicy.DEFAULT_TIMEOUT_MS) {
                i = 0;
            }
            Toast.makeText(this.f2040b, optString, i).show();
            new Timer().schedule(new C0843e(this, c0840a), (long) i);
        }
        return C0839a.f2019a;
    }

    /* renamed from: c */
    private void m3252c(C0840a c0840a) {
        JSONObject jSONObject = c0840a.f2037m;
        CharSequence optString = jSONObject.optString("content");
        int optInt = jSONObject.optInt("duration");
        int i = 1;
        if (optInt < DefaultRetryPolicy.DEFAULT_TIMEOUT_MS) {
            i = 0;
        }
        Toast.makeText(this.f2040b, optString, i).show();
        new Timer().schedule(new C0843e(this, c0840a), (long) i);
    }
}
