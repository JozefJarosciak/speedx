package com.pingplusplus.android;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.webkit.JsPromptResult;
import android.webkit.WebView;
import com.alipay.sdk.authjs.C0840a;
import com.alipay.sdk.util.C0880h;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.commons.cli.HelpFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

public class WebViewEx extends WebView {
    /* renamed from: a */
    private static final String[] f14949a = new String[]{"getClass", "hashCode", "notify", "notifyAll", "equals", "toString", "wait"};
    /* renamed from: b */
    private HashMap<String, Object> f14950b = new HashMap();
    /* renamed from: c */
    private String f14951c = null;

    public WebViewEx(Context context) {
        super(context);
        m16964a(context);
    }

    public WebViewEx(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m16964a(context);
    }

    public WebViewEx(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        m16964a(context);
    }

    /* renamed from: a */
    private Class<?> m16963a(Object obj) {
        Class cls = obj.getClass();
        return cls == Integer.class ? Integer.TYPE : cls == Boolean.class ? Boolean.TYPE : String.class;
    }

    /* renamed from: a */
    private void m16964a(Context context) {
        super.setWebChromeClient(new C4304y(this));
        super.setWebViewClient(new C4302z(this));
        m16968a();
    }

    /* renamed from: a */
    private void m16965a(WebView webView) {
        if (webView instanceof WebViewEx) {
            m16973b();
        }
    }

    /* renamed from: a */
    private void m16967a(String str, Object obj, StringBuilder stringBuilder) {
        if (!TextUtils.isEmpty(str) && obj != null && stringBuilder != null) {
            Class cls = obj.getClass();
            stringBuilder.append("if(typeof(window.").append(str).append(")!='undefined'){");
            stringBuilder.append("    console.log('window." + str + "_js_interface_name is exist!!');");
            stringBuilder.append("}else {");
            stringBuilder.append("    window.").append(str).append("={");
            for (Method method : cls.getMethods()) {
                String name = method.getName();
                if (!m16972a(name)) {
                    int i;
                    stringBuilder.append("        ").append(name).append(":function(");
                    int length = method.getParameterTypes().length;
                    if (length > 0) {
                        int i2 = length - 1;
                        for (i = 0; i < i2; i++) {
                            stringBuilder.append(HelpFormatter.DEFAULT_ARG_NAME).append(i).append(",");
                        }
                        stringBuilder.append(HelpFormatter.DEFAULT_ARG_NAME).append(length - 1);
                    }
                    stringBuilder.append(") {");
                    if (method.getReturnType() != Void.TYPE) {
                        stringBuilder.append("            return ").append("prompt('").append("MyApp:").append("'+");
                    } else {
                        stringBuilder.append("            prompt('").append("MyApp:").append("'+");
                    }
                    stringBuilder.append("JSON.stringify({");
                    stringBuilder.append("obj").append(":'").append(str).append("',");
                    stringBuilder.append(C0840a.f2031g).append(":'").append(name).append("',");
                    stringBuilder.append("args").append(":[");
                    if (length > 0) {
                        int i3 = length - 1;
                        for (i = 0; i < i3; i++) {
                            stringBuilder.append(HelpFormatter.DEFAULT_ARG_NAME).append(i).append(",");
                        }
                        stringBuilder.append(HelpFormatter.DEFAULT_ARG_NAME).append(i3);
                    }
                    stringBuilder.append("]})");
                    stringBuilder.append(");");
                    stringBuilder.append("        }, ");
                }
            }
            stringBuilder.append("    };");
            stringBuilder.append(C0880h.f2222d);
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: a */
    private boolean m16968a() {
        if (!m16976e() || m16977f()) {
            return false;
        }
        super.removeJavascriptInterface("searchBoxJavaBridge_");
        return true;
    }

    /* renamed from: a */
    private boolean m16969a(JsPromptResult jsPromptResult, String str, String str2, Object[] objArr) {
        boolean z = true;
        Object obj = this.f14950b.get(str);
        if (obj == null) {
            jsPromptResult.cancel();
            return false;
        }
        Class[] clsArr = null;
        int length = objArr != null ? objArr.length : 0;
        if (length > 0) {
            clsArr = new Class[length];
            for (int i = 0; i < length; i++) {
                clsArr[i] = m16963a(objArr[i]);
            }
        }
        try {
            Object invoke = obj.getClass().getMethod(str2, clsArr).invoke(obj, objArr);
            boolean z2 = invoke == null || invoke.getClass() == Void.TYPE;
            jsPromptResult.confirm(z2 ? "" : invoke.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            z = false;
        } catch (Exception e2) {
            e2.printStackTrace();
            z = false;
        }
        jsPromptResult.cancel();
        return z;
    }

    /* renamed from: a */
    private boolean m16970a(WebView webView, String str, String str2, String str3, JsPromptResult jsPromptResult) {
        String str4 = "MyApp:";
        if (!str2.startsWith(str4)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str2.substring(str4.length()));
            String string = jSONObject.getString("obj");
            String string2 = jSONObject.getString(C0840a.f2031g);
            JSONArray jSONArray = jSONObject.getJSONArray("args");
            Object[] objArr = null;
            if (jSONArray != null) {
                int length = jSONArray.length();
                if (length > 0) {
                    objArr = new Object[length];
                    for (int i = 0; i < length; i++) {
                        objArr[i] = jSONArray.get(i);
                    }
                }
            }
            if (m16969a(jsPromptResult, string, string2, objArr)) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        jsPromptResult.cancel();
        return false;
    }

    /* renamed from: a */
    private boolean m16972a(String str) {
        for (String equals : f14949a) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: b */
    private void m16973b() {
        if (TextUtils.isEmpty(this.f14951c)) {
            this.f14951c = m16975d();
            m16974c();
            return;
        }
        m16974c();
    }

    /* renamed from: c */
    private void m16974c() {
        loadUrl(this.f14951c);
    }

    /* renamed from: d */
    private String m16975d() {
        if (this.f14950b.size() == 0) {
            this.f14951c = null;
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("javascript:(function JsAddJavascriptInterface_(){");
        for (Entry entry : this.f14950b.entrySet()) {
            try {
                m16967a((String) entry.getKey(), entry.getValue(), stringBuilder);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append("})()");
        return stringBuilder.toString();
    }

    /* renamed from: e */
    private boolean m16976e() {
        return VERSION.SDK_INT >= 11;
    }

    /* renamed from: f */
    private boolean m16977f() {
        return VERSION.SDK_INT >= 17;
    }

    @SuppressLint({"JavascriptInterface"})
    public void addJavascriptInterface(Object obj, String str) {
        if (!TextUtils.isEmpty(str)) {
            if (m16977f()) {
                super.addJavascriptInterface(obj, str);
            } else {
                this.f14950b.put(str, obj);
            }
        }
    }

    @SuppressLint({"NewApi"})
    public void removeJavascriptInterface(String str) {
        if (m16977f()) {
            super.removeJavascriptInterface(str);
            return;
        }
        this.f14950b.remove(str);
        this.f14951c = null;
        m16973b();
    }
}
