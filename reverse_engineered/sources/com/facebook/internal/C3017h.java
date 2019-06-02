package com.facebook.internal;

import com.facebook.FacebookRequestError.Category;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: FacebookRequestErrorClassification */
/* renamed from: com.facebook.internal.h */
public final class C3017h {
    /* renamed from: g */
    private static C3017h f13552g;
    /* renamed from: a */
    private final Map<Integer, Set<Integer>> f13553a;
    /* renamed from: b */
    private final Map<Integer, Set<Integer>> f13554b;
    /* renamed from: c */
    private final Map<Integer, Set<Integer>> f13555c;
    /* renamed from: d */
    private final String f13556d;
    /* renamed from: e */
    private final String f13557e;
    /* renamed from: f */
    private final String f13558f;

    C3017h(Map<Integer, Set<Integer>> map, Map<Integer, Set<Integer>> map2, Map<Integer, Set<Integer>> map3, String str, String str2, String str3) {
        this.f13553a = map;
        this.f13554b = map2;
        this.f13555c = map3;
        this.f13556d = str;
        this.f13557e = str2;
        this.f13558f = str3;
    }

    /* renamed from: a */
    public String m14589a(Category category) {
        switch (category) {
            case OTHER:
                return this.f13556d;
            case LOGIN_RECOVERABLE:
                return this.f13558f;
            case TRANSIENT:
                return this.f13557e;
            default:
                return null;
        }
    }

    /* renamed from: a */
    public Category m14588a(int i, int i2, boolean z) {
        if (z) {
            return Category.TRANSIENT;
        }
        Set set;
        if (this.f13553a != null && this.f13553a.containsKey(Integer.valueOf(i))) {
            set = (Set) this.f13553a.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return Category.OTHER;
            }
        }
        if (this.f13555c != null && this.f13555c.containsKey(Integer.valueOf(i))) {
            set = (Set) this.f13555c.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return Category.LOGIN_RECOVERABLE;
            }
        }
        if (this.f13554b != null && this.f13554b.containsKey(Integer.valueOf(i))) {
            set = (Set) this.f13554b.get(Integer.valueOf(i));
            if (set == null || set.contains(Integer.valueOf(i2))) {
                return Category.TRANSIENT;
            }
        }
        return Category.OTHER;
    }

    /* renamed from: a */
    public static synchronized C3017h m14584a() {
        C3017h c3017h;
        synchronized (C3017h.class) {
            if (f13552g == null) {
                f13552g = C3017h.m14587b();
            }
            c3017h = f13552g;
        }
        return c3017h;
    }

    /* renamed from: b */
    private static C3017h m14587b() {
        return new C3017h(null, new FacebookRequestErrorClassification$1(), new FacebookRequestErrorClassification$2(), null, null, null);
    }

    /* renamed from: a */
    private static Map<Integer, Set<Integer>> m14586a(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray("items");
        if (optJSONArray.length() == 0) {
            return null;
        }
        Map<Integer, Set<Integer>> hashMap = new HashMap();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                int optInt = optJSONObject.optInt("code");
                if (optInt != 0) {
                    Object obj;
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("subcodes");
                    if (optJSONArray2 == null || optJSONArray2.length() <= 0) {
                        obj = null;
                    } else {
                        Set hashSet = new HashSet();
                        for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                            int optInt2 = optJSONArray2.optInt(i2);
                            if (optInt2 != 0) {
                                hashSet.add(Integer.valueOf(optInt2));
                            }
                        }
                        obj = hashSet;
                    }
                    hashMap.put(Integer.valueOf(optInt), obj);
                }
            }
        }
        return hashMap;
    }

    /* renamed from: a */
    public static C3017h m14585a(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        String str = null;
        String str2 = null;
        String str3 = null;
        Map map = null;
        Map map2 = null;
        Map map3 = null;
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                String optString = optJSONObject.optString("name");
                if (optString != null) {
                    if (optString.equalsIgnoreCase("other")) {
                        str3 = optJSONObject.optString("recovery_message", null);
                        map3 = C3017h.m14586a(optJSONObject);
                    } else if (optString.equalsIgnoreCase("transient")) {
                        str2 = optJSONObject.optString("recovery_message", null);
                        map2 = C3017h.m14586a(optJSONObject);
                    } else if (optString.equalsIgnoreCase("login_recoverable")) {
                        str = optJSONObject.optString("recovery_message", null);
                        map = C3017h.m14586a(optJSONObject);
                    }
                }
            }
        }
        return new C3017h(map3, map2, map, str3, str2, str);
    }
}
