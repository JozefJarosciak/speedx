package com.beastbikes.android.ble.p095a;

import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1768c;
import com.beastbikes.android.C1772d;
import com.beastbikes.android.ble.biz.C1390l;
import com.beastbikes.android.ble.dto.S605FirmwareInfo;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: S605UpdateManager */
/* renamed from: com.beastbikes.android.ble.a.a */
public class C1599a {
    /* renamed from: a */
    private static Logger f7338a = LoggerFactory.getLogger(C1599a.class);
    /* renamed from: b */
    private static C1599a f7339b;
    /* renamed from: c */
    private C1601b f7340c;
    /* renamed from: d */
    private C1390l f7341d;

    private C1599a() {
        C1772d c1772d = new C1772d(BeastBikes.j().getApplicationContext());
        this.f7340c = (C1601b) c1772d.m9399a(C1601b.class, C1768c.f8075a, C1768c.m9390a());
        this.f7341d = (C1390l) c1772d.m9398a(C1390l.class, "https://fota4.adups.cn");
    }

    /* renamed from: a */
    public static C1599a m8586a() {
        if (f7339b == null) {
            synchronized (C1599a.class) {
                f7339b = new C1599a();
            }
        }
        return f7339b;
    }

    /* renamed from: a */
    public S605FirmwareInfo m8587a(S605FirmwareInfo s605FirmwareInfo) {
        if (s605FirmwareInfo == null) {
            return null;
        }
        try {
            JSONObject a = this.f7341d.a(s605FirmwareInfo.getMid(), s605FirmwareInfo.getRomCurrVersion(), "speedx2601_5.1", "coretek2601_wp_l", "7b27600ea0558d191a41cee425f2724d", "MTK2601_5.1", "bicycle", "3.0");
            if (a == null) {
                f7338a.error("checkVersion(), checkVersion response is null");
                return null;
            } else if (a.optInt("status") != 1000) {
                f7338a.error("checkVersion(), checkVersion is error, msg:" + a.optString("msg"));
                return null;
            } else {
                a = a.optJSONObject(MapboxEvent.ATTRIBUTE_VERSION);
                if (a == null) {
                    f7338a.error("checkVersion(), checkVersion response result is null");
                    return null;
                }
                s605FirmwareInfo.setRomNewVersion(a.optString("versionName"));
                return s605FirmwareInfo;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
