package com.facebook.internal;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.webkit.WebView;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import org.json.JSONObject;

/* compiled from: FacebookWebFallbackDialog */
/* renamed from: com.facebook.internal.i */
public class C3020i extends C3019u {
    /* renamed from: a */
    private static final String f13570a = C3020i.class.getName();
    /* renamed from: b */
    private boolean f13571b;

    /* compiled from: FacebookWebFallbackDialog */
    /* renamed from: com.facebook.internal.i$1 */
    class C30181 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C3020i f13559a;

        C30181(C3020i c3020i) {
            this.f13559a = c3020i;
        }

        public void run() {
            super.cancel();
        }
    }

    public C3020i(Context context, String str, String str2) {
        super(context, str);
        m14605b(str2);
    }

    /* renamed from: a */
    protected Bundle mo3694a(String str) {
        Bundle b = C3048s.m14764b(Uri.parse(str).getQuery());
        String string = b.getString("bridge_args");
        b.remove("bridge_args");
        if (!C3048s.m14761a(string)) {
            try {
                b.putBundle("com.facebook.platform.protocol.BRIDGE_ARGS", C3009c.m14559a(new JSONObject(string)));
            } catch (Throwable e) {
                C3048s.m14755a(f13570a, "Unable to parse bridge_args JSON", e);
            }
        }
        string = b.getString("method_results");
        b.remove("method_results");
        if (!C3048s.m14761a(string)) {
            if (C3048s.m14761a(string)) {
                string = "{}";
            }
            try {
                b.putBundle("com.facebook.platform.protocol.RESULT_ARGS", C3009c.m14559a(new JSONObject(string)));
            } catch (Throwable e2) {
                C3048s.m14755a(f13570a, "Unable to parse bridge_args JSON", e2);
            }
        }
        b.remove(MapboxEvent.ATTRIBUTE_VERSION);
        b.putInt("com.facebook.platform.protocol.PROTOCOL_VERSION", C3035o.m14663a());
        return b;
    }

    public void cancel() {
        WebView c = m14607c();
        if (!m14606b() || m14604a() || c == null || !c.isShown()) {
            super.cancel();
        } else if (!this.f13571b) {
            this.f13571b = true;
            c.loadUrl("javascript:" + "(function() {  var event = document.createEvent('Event');  event.initEvent('fbPlatformDialogMustClose',true,true);  document.dispatchEvent(event);})();");
            new Handler(Looper.getMainLooper()).postDelayed(new C30181(this), 1500);
        }
    }
}
