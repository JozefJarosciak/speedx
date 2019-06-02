package com.twitter.sdk.android.core;

import android.content.Context;
import ch.qos.logback.core.net.ssl.SSL;
import io.fabric.sdk.android.services.network.C4656e;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;

/* compiled from: TwitterPinningInfoProvider */
/* renamed from: com.twitter.sdk.android.core.o */
class C4657o implements C4656e {
    /* renamed from: a */
    private static final String[] f16407a;
    /* renamed from: b */
    private final Context f16408b;

    static {
        HashMap hashMap = new HashMap();
        hashMap.put("VERISIGN_CLASS1", "2343d148a255899b947d461a797ec04cfed170b7");
        hashMap.put("VERISIGN_CLASS1_G3", "5519b278acb281d7eda7abc18399c3bb690424b5");
        hashMap.put("VERISIGN_CLASS2_G2", "1237ba4517eead2926fdc1cdfebeedf2ded9145c");
        hashMap.put("VERISIGN_CLASS2_G3", "5abec575dcaef3b08e271943fc7f250c3df661e3");
        hashMap.put("VERISIGN_CLASS3_G2", "1a21b4952b6293ce18b365ec9c0e934cb381e6d4");
        hashMap.put("VERISIGN_CLASS3_G3", "22f19e2ec6eaccfc5d2346f4c2e8f6c554dd5e07");
        hashMap.put("VERISIGN_CLASS3_G4", "ed663135d31bd4eca614c429e319069f94c12650");
        hashMap.put("VERISIGN_CLASS3_G5", "b181081a19a4c0941ffae89528c124c99b34acc7");
        hashMap.put("VERISIGN_CLASS4_G3", "3c03436868951cf3692ab8b426daba8fe922e5bd");
        hashMap.put("VERISIGN_UNIVERSAL", "bbc23e290bb328771dad3ea24dbdf423bd06b03d");
        hashMap.put("GEOTRUST_GLOBAL", "c07a98688d89fbab05640c117daa7d65b8cacc4e");
        hashMap.put("GEOTRUST_GLOBAL2", "713836f2023153472b6eba6546a9101558200509");
        hashMap.put("GEOTRUST_PRIMARY", "b01989e7effb4aafcb148f58463976224150e1ba");
        hashMap.put("GEOTRUST_PRIMARY_G2", "bdbea71bab7157f9e475d954d2b727801a822682");
        hashMap.put("GEOTRUST_PRIMARY_G3", "9ca98d00af740ddd8180d21345a58b8f2e9438d6");
        hashMap.put("GEOTRUST_UNIVERAL", "87e85b6353c623a3128cb0ffbbf551fe59800e22");
        hashMap.put("GEOTRUST_UNIVERSAL2", "5e4f538685dd4f9eca5fdc0d456f7d51b1dc9b7b");
        hashMap.put("DIGICERT_GLOBAL_ROOT", "d52e13c1abe349dae8b49594ef7c3843606466bd");
        hashMap.put("DIGICERT_EV_ROOT", "83317e62854253d6d7783190ec919056e991b9e3");
        hashMap.put("DIGICERT_ASSUREDID_ROOT", "68330e61358521592983a3c8d2d2e1406e7ab3c1");
        hashMap.put("TWITTER1", "56fef3c2147d4ed38837fdbd3052387201e5778d");
        Collection values = hashMap.values();
        f16407a = (String[]) values.toArray(new String[values.size()]);
    }

    public C4657o(Context context) {
        this.f16408b = context.getApplicationContext();
    }

    /* renamed from: a */
    public InputStream mo6151a() {
        return this.f16408b.getResources().openRawResource(C4573R.raw.tw__cacerts);
    }

    /* renamed from: b */
    public String mo6152b() {
        return SSL.DEFAULT_KEYSTORE_PASSWORD;
    }

    /* renamed from: c */
    public String[] mo6153c() {
        return f16407a;
    }

    /* renamed from: d */
    public long mo6154d() {
        return 1461817507106L;
    }
}
