package com.facebook.internal;

import android.os.Bundle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: BundleJSONConverter */
/* renamed from: com.facebook.internal.c */
public class C3009c {
    /* renamed from: a */
    private static final Map<Class<?>, C3001a> f13543a = new HashMap();

    /* compiled from: BundleJSONConverter */
    /* renamed from: com.facebook.internal.c$a */
    public interface C3001a {
        /* renamed from: a */
        void mo3693a(Bundle bundle, String str, Object obj) throws JSONException;
    }

    /* compiled from: BundleJSONConverter */
    /* renamed from: com.facebook.internal.c$1 */
    static class C30021 implements C3001a {
        C30021() {
        }

        /* renamed from: a */
        public void mo3693a(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putBoolean(str, ((Boolean) obj).booleanValue());
        }
    }

    /* compiled from: BundleJSONConverter */
    /* renamed from: com.facebook.internal.c$2 */
    static class C30032 implements C3001a {
        C30032() {
        }

        /* renamed from: a */
        public void mo3693a(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putInt(str, ((Integer) obj).intValue());
        }
    }

    /* compiled from: BundleJSONConverter */
    /* renamed from: com.facebook.internal.c$3 */
    static class C30043 implements C3001a {
        C30043() {
        }

        /* renamed from: a */
        public void mo3693a(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putLong(str, ((Long) obj).longValue());
        }
    }

    /* compiled from: BundleJSONConverter */
    /* renamed from: com.facebook.internal.c$4 */
    static class C30054 implements C3001a {
        C30054() {
        }

        /* renamed from: a */
        public void mo3693a(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putDouble(str, ((Double) obj).doubleValue());
        }
    }

    /* compiled from: BundleJSONConverter */
    /* renamed from: com.facebook.internal.c$5 */
    static class C30065 implements C3001a {
        C30065() {
        }

        /* renamed from: a */
        public void mo3693a(Bundle bundle, String str, Object obj) throws JSONException {
            bundle.putString(str, (String) obj);
        }
    }

    /* compiled from: BundleJSONConverter */
    /* renamed from: com.facebook.internal.c$6 */
    static class C30076 implements C3001a {
        C30076() {
        }

        /* renamed from: a */
        public void mo3693a(Bundle bundle, String str, Object obj) throws JSONException {
            throw new IllegalArgumentException("Unexpected type from JSON");
        }
    }

    /* compiled from: BundleJSONConverter */
    /* renamed from: com.facebook.internal.c$7 */
    static class C30087 implements C3001a {
        C30087() {
        }

        /* renamed from: a */
        public void mo3693a(Bundle bundle, String str, Object obj) throws JSONException {
            JSONArray jSONArray = (JSONArray) obj;
            ArrayList arrayList = new ArrayList();
            if (jSONArray.length() == 0) {
                bundle.putStringArrayList(str, arrayList);
                return;
            }
            int i = 0;
            while (i < jSONArray.length()) {
                Object obj2 = jSONArray.get(i);
                if (obj2 instanceof String) {
                    arrayList.add((String) obj2);
                    i++;
                } else {
                    throw new IllegalArgumentException("Unexpected type in an array: " + obj2.getClass());
                }
            }
            bundle.putStringArrayList(str, arrayList);
        }
    }

    static {
        f13543a.put(Boolean.class, new C30021());
        f13543a.put(Integer.class, new C30032());
        f13543a.put(Long.class, new C30043());
        f13543a.put(Double.class, new C30054());
        f13543a.put(String.class, new C30065());
        f13543a.put(String[].class, new C30076());
        f13543a.put(JSONArray.class, new C30087());
    }

    /* renamed from: a */
    public static Bundle m14559a(JSONObject jSONObject) throws JSONException {
        Bundle bundle = new Bundle();
        Iterator keys = jSONObject.keys();
        while (keys.hasNext()) {
            String str = (String) keys.next();
            Object obj = jSONObject.get(str);
            if (!(obj == null || obj == JSONObject.NULL)) {
                if (obj instanceof JSONObject) {
                    bundle.putBundle(str, C3009c.m14559a((JSONObject) obj));
                } else {
                    C3001a c3001a = (C3001a) f13543a.get(obj.getClass());
                    if (c3001a == null) {
                        throw new IllegalArgumentException("Unsupported type: " + obj.getClass());
                    }
                    c3001a.mo3693a(bundle, str, obj);
                }
            }
        }
        return bundle;
    }
}
