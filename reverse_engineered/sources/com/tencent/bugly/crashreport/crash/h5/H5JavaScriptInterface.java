package com.tencent.bugly.crashreport.crash.h5;

import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.joran.action.Action;
import com.tencent.bugly.crashreport.inner.InnerApi;
import com.tencent.bugly.proguard.C4475w;
import com.tencent.bugly.proguard.C4479y;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: BUGLY */
public class H5JavaScriptInterface {
    /* renamed from: a */
    private static HashSet<Integer> f7038a = new HashSet();
    /* renamed from: b */
    private String f7039b = null;
    /* renamed from: c */
    private Thread f7040c = null;
    /* renamed from: d */
    private String f7041d = null;
    /* renamed from: e */
    private Map<String, String> f7042e = null;

    private H5JavaScriptInterface() {
    }

    public static H5JavaScriptInterface getInstance(WebView webView) {
        String str = null;
        if (webView == null || f7038a.contains(Integer.valueOf(webView.hashCode()))) {
            return null;
        }
        H5JavaScriptInterface h5JavaScriptInterface = new H5JavaScriptInterface();
        f7038a.add(Integer.valueOf(webView.hashCode()));
        h5JavaScriptInterface.f7040c = Thread.currentThread();
        Thread thread = h5JavaScriptInterface.f7040c;
        if (thread != null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 2; i < thread.getStackTrace().length; i++) {
                StackTraceElement stackTraceElement = thread.getStackTrace()[i];
                if (!stackTraceElement.toString().contains("crashreport")) {
                    stringBuilder.append(stackTraceElement.toString()).append("\n");
                }
            }
            str = stringBuilder.toString();
        }
        h5JavaScriptInterface.f7041d = str;
        Map hashMap = new HashMap();
        hashMap.put("[WebView] ContentDescription", webView.getContentDescription());
        h5JavaScriptInterface.f7042e = hashMap;
        return h5JavaScriptInterface;
    }

    /* renamed from: a */
    private static C4441a m8288a(String str) {
        if (str == null || str.length() <= 0) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            C4441a c4441a = new C4441a();
            c4441a.f15494a = jSONObject.getString("projectRoot");
            if (c4441a.f15494a == null) {
                return null;
            }
            c4441a.f15495b = jSONObject.getString(CoreConstants.CONTEXT_SCOPE_VALUE);
            if (c4441a.f15495b == null) {
                return null;
            }
            c4441a.f15496c = jSONObject.getString("url");
            if (c4441a.f15496c == null) {
                return null;
            }
            c4441a.f15497d = jSONObject.getString("userAgent");
            if (c4441a.f15497d == null) {
                return null;
            }
            c4441a.f15498e = jSONObject.getString("language");
            if (c4441a.f15498e == null) {
                return null;
            }
            c4441a.f15499f = jSONObject.getString("name");
            if (c4441a.f15499f == null || c4441a.f15499f.equals("null")) {
                return null;
            }
            String string = jSONObject.getString("stacktrace");
            if (string == null) {
                return null;
            }
            int indexOf = string.indexOf("\n");
            if (indexOf < 0) {
                C4475w.d("H5 crash stack's format is wrong!", new Object[0]);
                return null;
            }
            c4441a.f15501h = string.substring(indexOf + 1);
            c4441a.f15500g = string.substring(0, indexOf);
            int indexOf2 = c4441a.f15500g.indexOf(":");
            if (indexOf2 > 0) {
                c4441a.f15500g = c4441a.f15500g.substring(indexOf2 + 1);
            }
            c4441a.f15502i = jSONObject.getString(Action.FILE_ATTRIBUTE);
            if (c4441a.f15499f == null) {
                return null;
            }
            c4441a.f15503j = jSONObject.getLong("lineNumber");
            if (c4441a.f15503j < 0) {
                return null;
            }
            c4441a.f15504k = jSONObject.getLong("columnNumber");
            if (c4441a.f15504k < 0) {
                return null;
            }
            C4475w.a("H5 crash information is following: ", new Object[0]);
            C4475w.a("[projectRoot]: " + c4441a.f15494a, new Object[0]);
            C4475w.a("[context]: " + c4441a.f15495b, new Object[0]);
            C4475w.a("[url]: " + c4441a.f15496c, new Object[0]);
            C4475w.a("[userAgent]: " + c4441a.f15497d, new Object[0]);
            C4475w.a("[language]: " + c4441a.f15498e, new Object[0]);
            C4475w.a("[name]: " + c4441a.f15499f, new Object[0]);
            C4475w.a("[message]: " + c4441a.f15500g, new Object[0]);
            C4475w.a("[stacktrace]: \n" + c4441a.f15501h, new Object[0]);
            C4475w.a("[file]: " + c4441a.f15502i, new Object[0]);
            C4475w.a("[lineNumber]: " + c4441a.f15503j, new Object[0]);
            C4475w.a("[columnNumber]: " + c4441a.f15504k, new Object[0]);
            return c4441a;
        } catch (Throwable th) {
            if (C4475w.a(th)) {
                return null;
            }
            th.printStackTrace();
            return null;
        }
    }

    @JavascriptInterface
    public void printLog(String str) {
        C4475w.d("Log from js: %s", new Object[]{str});
    }

    @JavascriptInterface
    public void reportJSException(String str) {
        if (str == null) {
            C4475w.d("Payload from JS is null.", new Object[0]);
            return;
        }
        String b = C4479y.b(str.getBytes());
        if (this.f7039b == null || !this.f7039b.equals(b)) {
            this.f7039b = b;
            C4475w.d("Handling JS exception ...", new Object[0]);
            C4441a a = m8288a(str);
            if (a == null) {
                C4475w.d("Failed to parse payload.", new Object[0]);
                return;
            }
            Map linkedHashMap = new LinkedHashMap();
            Map linkedHashMap2 = new LinkedHashMap();
            if (a.f15494a != null) {
                linkedHashMap2.put("[JS] projectRoot", a.f15494a);
            }
            if (a.f15495b != null) {
                linkedHashMap2.put("[JS] context", a.f15495b);
            }
            if (a.f15496c != null) {
                linkedHashMap2.put("[JS] url", a.f15496c);
            }
            if (a.f15497d != null) {
                linkedHashMap2.put("[JS] userAgent", a.f15497d);
            }
            if (a.f15502i != null) {
                linkedHashMap2.put("[JS] file", a.f15502i);
            }
            if (a.f15503j != 0) {
                linkedHashMap2.put("[JS] lineNumber", Long.toString(a.f15503j));
            }
            linkedHashMap.putAll(linkedHashMap2);
            linkedHashMap.putAll(this.f7042e);
            linkedHashMap.put("Java Stack", this.f7041d);
            Thread thread = this.f7040c;
            if (a != null) {
                InnerApi.postH5CrashAsync(thread, a.f15499f, a.f15500g, a.f15501h, linkedHashMap);
                return;
            }
            return;
        }
        C4475w.d("Same payload from js. Please check whether you've injected bugly.js more than one times.", new Object[0]);
    }
}
