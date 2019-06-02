package com.alipay.sdk.authjs;

import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.alipay.sdk.authjs.a */
public final class C0840a {
    /* renamed from: a */
    public static final String f2025a = "CallInfo";
    /* renamed from: b */
    public static final String f2026b = "call";
    /* renamed from: c */
    public static final String f2027c = "callback";
    /* renamed from: d */
    public static final String f2028d = "bundleName";
    /* renamed from: e */
    public static final String f2029e = "clientId";
    /* renamed from: f */
    public static final String f2030f = "param";
    /* renamed from: g */
    public static final String f2031g = "func";
    /* renamed from: h */
    public static final String f2032h = "msgType";
    /* renamed from: i */
    public String f2033i;
    /* renamed from: j */
    public String f2034j;
    /* renamed from: k */
    public String f2035k;
    /* renamed from: l */
    public String f2036l;
    /* renamed from: m */
    public JSONObject f2037m;
    /* renamed from: n */
    private boolean f2038n = false;

    /* renamed from: com.alipay.sdk.authjs.a$1 */
    static /* synthetic */ class C08381 {
        /* renamed from: a */
        static final /* synthetic */ int[] f2018a = new int[C0839a.m3231a().length];

        static {
            try {
                f2018a[C0839a.f2020b - 1] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                f2018a[C0839a.f2021c - 1] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                f2018a[C0839a.f2022d - 1] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* renamed from: com.alipay.sdk.authjs.a$a */
    public enum C0839a {
        ;

        /* renamed from: a */
        public static int[] m3231a() {
            return (int[]) f2024f.clone();
        }

        static {
            f2019a = 1;
            f2020b = 2;
            f2021c = 3;
            f2022d = 4;
            f2023e = 5;
            f2024f = new int[]{f2019a, f2020b, f2021c, f2022d, f2023e};
        }
    }

    /* renamed from: a */
    private static String m3232a(int i) {
        switch (C08381.f2018a[i - 1]) {
            case 1:
                return "function not found";
            case 2:
                return "invalid parameter";
            case 3:
                return "runtime error";
            default:
                return "none";
        }
    }

    /* renamed from: a */
    private boolean m3236a() {
        return this.f2038n;
    }

    /* renamed from: a */
    private void m3235a(boolean z) {
        this.f2038n = z;
    }

    public C0840a(String str) {
        this.f2036l = str;
    }

    /* renamed from: b */
    private String m3237b() {
        return this.f2033i;
    }

    /* renamed from: a */
    private void m3233a(String str) {
        this.f2033i = str;
    }

    /* renamed from: c */
    private String m3239c() {
        return this.f2034j;
    }

    /* renamed from: b */
    private void m3238b(String str) {
        this.f2034j = str;
    }

    /* renamed from: d */
    private String m3241d() {
        return this.f2035k;
    }

    /* renamed from: c */
    private void m3240c(String str) {
        this.f2035k = str;
    }

    /* renamed from: e */
    private String m3243e() {
        return this.f2036l;
    }

    /* renamed from: d */
    private void m3242d(String str) {
        this.f2036l = str;
    }

    /* renamed from: f */
    private JSONObject m3244f() {
        return this.f2037m;
    }

    /* renamed from: a */
    private void m3234a(JSONObject jSONObject) {
        this.f2037m = jSONObject;
    }

    /* renamed from: g */
    private String m3245g() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put(f2029e, this.f2033i);
        jSONObject.put(f2031g, this.f2035k);
        jSONObject.put(f2030f, this.f2037m);
        jSONObject.put(f2032h, this.f2036l);
        return jSONObject.toString();
    }
}
