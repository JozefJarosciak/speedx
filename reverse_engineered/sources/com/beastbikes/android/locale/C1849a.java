package com.beastbikes.android.locale;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.beastbikes.android.C1371a;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import io.rong.imlib.statistics.UserData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: LocaleManager */
/* renamed from: com.beastbikes.android.locale.a */
public class C1849a implements C1371a {
    /* renamed from: a */
    public static boolean f8325a = false;
    /* renamed from: b */
    private static final Logger f8326b = LoggerFactory.getLogger(C1849a.class);

    /* renamed from: a */
    public static boolean m9641a() {
        String d = C1849a.m9649d();
        if (TextUtils.isEmpty(d) || d.equals("Asia/Shanghai") || d.equals("Asia/Hong_Kong") || d.equals("Asia/Chongqing") || d.equals("Asia/Harbin") || d.equals("Asia/Urumqi")) {
            return true;
        }
        return false;
    }

    /* renamed from: d */
    private static String m9649d() {
        return TimeZone.getDefault().getID();
    }

    /* renamed from: b */
    public static Map<String, Integer> m9644b() {
        Set<String> supportedRegions = PhoneNumberUtil.getInstance().getSupportedRegions();
        Map<String, Integer> hashMap = new HashMap();
        for (String str : supportedRegions) {
            hashMap.put(str.toUpperCase(), Integer.valueOf(PhoneNumberUtil.getInstance().getCountryCodeForRegion(str)));
        }
        return hashMap;
    }

    /* renamed from: a */
    public static int m9639a(Context context) {
        try {
            Object simCountryIso;
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(UserData.PHONE_KEY);
            CharSequence networkCountryIso = telephonyManager.getNetworkCountryIso();
            if (TextUtils.isEmpty(networkCountryIso)) {
                simCountryIso = telephonyManager.getSimCountryIso();
            } else {
                CharSequence charSequence = networkCountryIso;
            }
            if (!TextUtils.isEmpty(simCountryIso)) {
                String toUpperCase = simCountryIso.toUpperCase();
                Map b = C1849a.m9644b();
                if (b != null && b.containsKey(toUpperCase)) {
                    return ((Integer) b.get(toUpperCase)).intValue();
                }
            }
            return 86;
        } catch (Exception e) {
            return 86;
        }
    }

    /* renamed from: b */
    public static boolean m9645b(Context context) {
        if (context == null) {
            return true;
        }
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (defaultSharedPreferences == null || defaultSharedPreferences.getInt("km_or_mi", 0) == 0) {
            return true;
        }
        return false;
    }

    /* renamed from: c */
    public static boolean m9647c() {
        if (Locale.getDefault().getLanguage().endsWith("zh")) {
            return true;
        }
        return false;
    }

    /* renamed from: a */
    public static double m9638a(double d) {
        return 0.621371d * d;
    }

    /* renamed from: b */
    public static double m9642b(double d) {
        return 1.609344d * d;
    }

    /* renamed from: c */
    public static double m9646c(double d) {
        return 3.2808399d * d;
    }

    /* renamed from: d */
    public static double m9648d(double d) {
        return 0.621371d * d;
    }

    /* renamed from: e */
    public static double m9650e(double d) {
        return 3.2808399d * d;
    }

    /* renamed from: f */
    public static int m9651f(double d) {
        return (int) Math.round((d / 2.54d) % 12.0d);
    }

    /* renamed from: g */
    public static int m9652g(double d) {
        return (int) (d / 30.48d);
    }

    /* renamed from: h */
    public static double m9653h(double d) {
        return (double) Math.round(2.2046226d * d);
    }

    /* renamed from: i */
    public static double m9654i(double d) {
        return d / 2.2046226d;
    }

    /* renamed from: j */
    public static double m9655j(double d) {
        return 30.48d * d;
    }

    /* renamed from: k */
    public static double m9656k(double d) {
        return 2.54d * d;
    }

    /* renamed from: a */
    public static ArrayList<Double> m9640a(ArrayList<Double> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.set(i, Double.valueOf(C1849a.m9648d(((Double) arrayList.get(i)).doubleValue())));
        }
        return arrayList;
    }

    /* renamed from: b */
    public static ArrayList<Double> m9643b(ArrayList<Double> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            arrayList.set(i, Double.valueOf(C1849a.m9646c(((Double) arrayList.get(i)).doubleValue())));
        }
        return arrayList;
    }
}
