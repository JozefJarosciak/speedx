package com.digits.sdk.p177a;

import com.google.common.primitives.Ints;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* compiled from: VCardConfig */
/* renamed from: com.digits.sdk.a.c */
public class C2866c {
    /* renamed from: a */
    static String f13058a = "v21_generic";
    /* renamed from: b */
    public static int f13059b = -1073741824;
    /* renamed from: c */
    private static final Map<String, Integer> f13060c = new HashMap();
    /* renamed from: d */
    private static final Set<Integer> f13061d = new HashSet();

    static {
        f13060c.put(f13058a, Integer.valueOf(-1073741824));
        f13060c.put("v30_generic", Integer.valueOf(-1073741823));
        f13060c.put("v21_europe", Integer.valueOf(-1073741820));
        f13060c.put("v30_europe", Integer.valueOf(-1073741819));
        f13060c.put("v21_japanese_utf8", Integer.valueOf(-1073741816));
        f13060c.put("v30_japanese_utf8", Integer.valueOf(-1073741815));
        f13060c.put("v21_japanese_mobile", Integer.valueOf(402653192));
        f13060c.put("docomo", Integer.valueOf(939524104));
        f13061d.add(Integer.valueOf(-1073741816));
        f13061d.add(Integer.valueOf(-1073741815));
        f13061d.add(Integer.valueOf(402653192));
        f13061d.add(Integer.valueOf(939524104));
    }

    /* renamed from: a */
    public static boolean m13796a(int i) {
        return (i & 3) == 0;
    }

    /* renamed from: b */
    public static boolean m13797b(int i) {
        return (i & 3) == 1;
    }

    /* renamed from: c */
    public static boolean m13798c(int i) {
        return (i & 3) == 2;
    }

    /* renamed from: d */
    public static boolean m13799d(int i) {
        return !C2866c.m13797b(i);
    }

    /* renamed from: e */
    public static int m13800e(int i) {
        return i & 12;
    }

    /* renamed from: f */
    public static boolean m13801f(int i) {
        return (Ints.MAX_POWER_OF_TWO & i) != 0;
    }

    /* renamed from: g */
    public static boolean m13802g(int i) {
        return (C2866c.m13799d(i) && (268435456 & i) == 0) ? false : true;
    }

    /* renamed from: h */
    public static boolean m13803h(int i) {
        return C2866c.m13797b(i) || (67108864 & i) != 0;
    }

    /* renamed from: i */
    public static boolean m13804i(int i) {
        return f13061d.contains(Integer.valueOf(i));
    }

    /* renamed from: j */
    static boolean m13805j(int i) {
        return (33554432 & i) != 0;
    }

    /* renamed from: k */
    public static boolean m13806k(int i) {
        return (134217728 & i) != 0;
    }

    /* renamed from: l */
    public static boolean m13807l(int i) {
        return (536870912 & i) != 0;
    }
}
