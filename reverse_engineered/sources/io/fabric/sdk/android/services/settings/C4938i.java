package io.fabric.sdk.android.services.settings;

import io.fabric.sdk.android.C1468h;
import io.fabric.sdk.android.C1520c;
import io.fabric.sdk.android.services.common.C4877i;
import io.fabric.sdk.android.services.p094c.C4861a;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import org.json.JSONObject;

/* compiled from: DefaultCachedSettingsIo */
/* renamed from: io.fabric.sdk.android.services.settings.i */
class C4938i implements C4936g {
    /* renamed from: a */
    private final C1468h f17307a;

    public C4938i(C1468h c1468h) {
        this.f17307a = c1468h;
    }

    /* renamed from: a */
    public JSONObject mo6262a() {
        Closeable fileInputStream;
        Throwable e;
        Closeable closeable = null;
        C1520c.h().mo6215a("Fabric", "Reading cached settings...");
        try {
            JSONObject jSONObject;
            File file = new File(new C4861a(this.f17307a).m19105a(), "com.crashlytics.settings.json");
            if (file.exists()) {
                fileInputStream = new FileInputStream(file);
                try {
                    jSONObject = new JSONObject(C4877i.m19146a((InputStream) fileInputStream));
                    closeable = fileInputStream;
                } catch (Exception e2) {
                    e = e2;
                    try {
                        C1520c.h().mo6222d("Fabric", "Failed to fetch cached settings", e);
                        C4877i.m19160a(fileInputStream, "Error while closing settings cache file.");
                        return null;
                    } catch (Throwable th) {
                        e = th;
                        closeable = fileInputStream;
                        C4877i.m19160a(closeable, "Error while closing settings cache file.");
                        throw e;
                    }
                }
            }
            C1520c.h().mo6215a("Fabric", "No cached settings found.");
            jSONObject = null;
            C4877i.m19160a(closeable, "Error while closing settings cache file.");
            return jSONObject;
        } catch (Exception e3) {
            e = e3;
            fileInputStream = null;
            C1520c.h().mo6222d("Fabric", "Failed to fetch cached settings", e);
            C4877i.m19160a(fileInputStream, "Error while closing settings cache file.");
            return null;
        } catch (Throwable th2) {
            e = th2;
            C4877i.m19160a(closeable, "Error while closing settings cache file.");
            throw e;
        }
    }

    /* renamed from: a */
    public void mo6263a(long j, JSONObject jSONObject) {
        Closeable fileWriter;
        Throwable e;
        C1520c.h().mo6215a("Fabric", "Writing settings to cache file...");
        if (jSONObject != null) {
            Closeable closeable = null;
            try {
                jSONObject.put("expires_at", j);
                fileWriter = new FileWriter(new File(new C4861a(this.f17307a).m19105a(), "com.crashlytics.settings.json"));
                try {
                    fileWriter.write(jSONObject.toString());
                    fileWriter.flush();
                    C4877i.m19160a(fileWriter, "Failed to close settings writer.");
                } catch (Exception e2) {
                    e = e2;
                    try {
                        C1520c.h().mo6222d("Fabric", "Failed to cache settings", e);
                        C4877i.m19160a(fileWriter, "Failed to close settings writer.");
                    } catch (Throwable th) {
                        e = th;
                        closeable = fileWriter;
                        C4877i.m19160a(closeable, "Failed to close settings writer.");
                        throw e;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                fileWriter = null;
                C1520c.h().mo6222d("Fabric", "Failed to cache settings", e);
                C4877i.m19160a(fileWriter, "Failed to close settings writer.");
            } catch (Throwable th2) {
                e = th2;
                C4877i.m19160a(closeable, "Failed to close settings writer.");
                throw e;
            }
        }
    }
}
