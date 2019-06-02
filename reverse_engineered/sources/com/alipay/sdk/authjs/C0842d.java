package com.alipay.sdk.authjs;

import android.widget.Toast;
import com.alipay.sdk.authjs.C0840a.C0839a;
import com.android.volley.DefaultRetryPolicy;
import java.util.Timer;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.authjs.d */
final class C0842d implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0840a f2041a;
    /* renamed from: b */
    final /* synthetic */ C0841c f2042b;

    C0842d(C0841c c0841c, C0840a c0840a) {
        this.f2042b = c0841c;
        this.f2041a = c0840a;
    }

    public final void run() {
        int i;
        C0841c c0841c = this.f2042b;
        C0840a c0840a = this.f2041a;
        if (c0840a != null && "toast".equals(c0840a.f2035k)) {
            JSONObject jSONObject = c0840a.f2037m;
            CharSequence optString = jSONObject.optString("content");
            int optInt = jSONObject.optInt("duration");
            i = 1;
            if (optInt < DefaultRetryPolicy.DEFAULT_TIMEOUT_MS) {
                i = 0;
            }
            Toast.makeText(c0841c.f2040b, optString, i).show();
            new Timer().schedule(new C0843e(c0841c, c0840a), (long) i);
        }
        i = C0839a.f2019a;
        if (i != C0839a.f2019a) {
            try {
                this.f2042b.m3254a(this.f2041a.f2033i, i);
            } catch (JSONException e) {
            }
        }
    }
}
