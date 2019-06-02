package com.digits.sdk.android;

import android.content.Context;
import android.telephony.PhoneNumberUtils;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import io.fabric.sdk.android.services.common.C4877i;
import io.rong.imlib.statistics.UserData;
import java.util.Locale;

/* compiled from: SimManager */
class bt {
    /* renamed from: a */
    private final TelephonyManager f13308a;
    /* renamed from: b */
    private final boolean f13309b;

    /* renamed from: a */
    public static bt m14168a(Context context) {
        return new bt((TelephonyManager) context.getSystemService(UserData.PHONE_KEY), C4877i.c(context, "android.permission.READ_PHONE_STATE"));
    }

    protected bt(TelephonyManager telephonyManager, boolean z) {
        this.f13308a = telephonyManager;
        this.f13309b = z;
    }

    /* renamed from: a */
    protected String m14172a() {
        if (this.f13308a == null || !this.f13309b) {
            return "";
        }
        return m14169a(this.f13308a.getLine1Number());
    }

    /* renamed from: a */
    private String m14169a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            int digit = Character.digit(charAt, 10);
            if (digit != -1) {
                stringBuilder.append(digit);
            } else if (i == 0 && charAt == '+') {
                stringBuilder.append(charAt);
            } else if ((charAt >= 'a' && charAt <= 'z') || (charAt >= 'A' && charAt <= 'Z')) {
                return m14169a(PhoneNumberUtils.convertKeypadLettersToDigits(str));
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    protected String m14173b() {
        if (this.f13308a != null) {
            String simCountryIso = this.f13308a.getSimCountryIso();
            if (m14170b(simCountryIso)) {
                return simCountryIso.toUpperCase(Locale.getDefault());
            }
            if (!m14171c()) {
                simCountryIso = this.f13308a.getNetworkCountryIso();
                if (m14170b(simCountryIso)) {
                    return simCountryIso.toUpperCase(Locale.getDefault());
                }
            }
        }
        return "";
    }

    /* renamed from: b */
    private boolean m14170b(String str) {
        return str != null && str.length() == 2;
    }

    /* renamed from: c */
    private boolean m14171c() {
        return this.f13308a.getPhoneType() == 2;
    }
}
