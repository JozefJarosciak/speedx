package com.baidu.location.p041a;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.wifi.WifiInfo;
import android.os.Bundle;
import com.baidu.location.Jni;
import com.baidu.location.p042d.C1041e;
import com.baidu.location.p042d.C1091b;
import com.baidu.location.p042d.C1100j;
import com.baidu.location.p043b.C1072a;
import com.baidu.location.p043b.C1074b;
import com.baidu.location.p043b.C1082g;
import com.baidu.location.p043b.C1085h;
import java.io.File;
import java.util.HashMap;
import org.apache.http.HttpStatus;
import org.json.JSONObject;

/* renamed from: com.baidu.location.a.g */
public class C1051g {
    /* renamed from: b */
    private static Object f2433b = new Object();
    /* renamed from: c */
    private static C1051g f2434c = null;
    /* renamed from: d */
    private static final String f2435d = (C1100j.m4025g() + "/hst.db");
    /* renamed from: a */
    C1050a f2436a = null;
    /* renamed from: e */
    private SQLiteDatabase f2437e = null;
    /* renamed from: f */
    private boolean f2438f = false;

    /* renamed from: com.baidu.location.a.g$a */
    class C1050a extends C1041e {
        /* renamed from: a */
        final /* synthetic */ C1051g f2430a;
        /* renamed from: b */
        private String f2431b;
        /* renamed from: c */
        private String f2432c;

        C1050a(C1051g c1051g) {
            this.f2430a = c1051g;
            this.f2431b = null;
            this.f2432c = null;
            this.k = new HashMap();
        }

        /* renamed from: a */
        public void mo2597a() {
            this.i = 1;
            this.h = C1100j.m4019c();
            String encodeTp4 = Jni.encodeTp4(this.f2432c);
            this.f2432c = null;
            this.k.put("bloc", encodeTp4);
        }

        /* renamed from: a */
        public void m3732a(String str, String str2) {
            if (!this.f2430a.f2438f) {
                this.f2430a.f2438f = true;
                this.f2431b = str;
                this.f2432c = str2;
                m3670b(C1100j.f2735f);
            }
        }

        /* renamed from: a */
        public void mo2598a(boolean z) {
            JSONObject jSONObject = null;
            if (!z || this.j == null) {
                this.f2430a.m3742g();
            } else {
                try {
                    JSONObject jSONObject2 = new JSONObject(this.j);
                    if (jSONObject2.has("content")) {
                        jSONObject = jSONObject2.getJSONObject("content");
                    }
                    if (jSONObject != null && jSONObject.has("imo")) {
                        Long valueOf = Long.valueOf(jSONObject.getJSONObject("imo").getString("mac"));
                        int i = jSONObject.getJSONObject("imo").getInt("mv");
                        if (Jni.encode3(this.f2431b).longValue() == valueOf.longValue()) {
                            ContentValues contentValues = new ContentValues();
                            contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
                            contentValues.put("hst", Integer.valueOf(i));
                            try {
                                if (this.f2430a.f2437e.update("hstdata", contentValues, "id = \"" + valueOf + "\"", null) <= 0) {
                                    contentValues.put("id", valueOf);
                                    this.f2430a.f2437e.insert("hstdata", null, contentValues);
                                }
                            } catch (Exception e) {
                            }
                            Bundle bundle = new Bundle();
                            bundle.putByteArray("mac", this.f2431b.getBytes());
                            bundle.putInt("hotspot", i);
                            this.f2430a.m3736a(bundle);
                        }
                    }
                } catch (Exception e2) {
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
            this.f2430a.f2438f = false;
        }
    }

    /* renamed from: a */
    public static C1051g m3735a() {
        C1051g c1051g;
        synchronized (f2433b) {
            if (f2434c == null) {
                f2434c = new C1051g();
            }
            c1051g = f2434c;
        }
        return c1051g;
    }

    /* renamed from: a */
    private void m3736a(Bundle bundle) {
        C1038a.m3645a().m3649a(bundle, HttpStatus.SC_NOT_ACCEPTABLE);
    }

    /* renamed from: f */
    private String m3741f() {
        C1072a f = C1074b.m3866a().m3884f();
        C1082g m = C1085h.m3959a().m3975m();
        StringBuffer stringBuffer = new StringBuffer(1024);
        if (f != null && f.m3855b()) {
            stringBuffer.append(f.m3860g());
        }
        if (m != null && m.m3945a() > 1) {
            stringBuffer.append(m.m3946a(15));
        } else if (C1085h.m3959a().m3972j() != null) {
            stringBuffer.append(C1085h.m3959a().m3972j());
        }
        stringBuffer.append("&imo=1");
        stringBuffer.append(C1091b.m3989a().m3990a(false));
        stringBuffer.append(C1038a.m3645a().m3654c());
        return stringBuffer.toString();
    }

    /* renamed from: g */
    private void m3742g() {
        Bundle bundle = new Bundle();
        bundle.putInt("hotspot", -1);
        m3736a(bundle);
    }

    /* renamed from: a */
    public void m3743a(String str) {
        JSONObject jSONObject = null;
        if (!this.f2438f) {
            try {
                JSONObject jSONObject2 = new JSONObject(str);
                if (jSONObject2.has("content")) {
                    jSONObject = jSONObject2.getJSONObject("content");
                }
                if (jSONObject != null && jSONObject.has("imo")) {
                    Long valueOf = Long.valueOf(jSONObject.getJSONObject("imo").getString("mac"));
                    int i = jSONObject.getJSONObject("imo").getInt("mv");
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("tt", Integer.valueOf((int) (System.currentTimeMillis() / 1000)));
                    contentValues.put("hst", Integer.valueOf(i));
                    try {
                        if (this.f2437e.update("hstdata", contentValues, "id = \"" + valueOf + "\"", null) <= 0) {
                            contentValues.put("id", valueOf);
                            this.f2437e.insert("hstdata", null, contentValues);
                        }
                    } catch (Exception e) {
                    }
                }
            } catch (Exception e2) {
            }
        }
    }

    /* renamed from: b */
    public void m3744b() {
        try {
            File file = new File(f2435d);
            if (!file.exists()) {
                file.createNewFile();
            }
            if (file.exists()) {
                this.f2437e = SQLiteDatabase.openOrCreateDatabase(file, null);
                this.f2437e.execSQL("CREATE TABLE IF NOT EXISTS hstdata(id Long PRIMARY KEY,hst INT,tt INT);");
                this.f2437e.setVersion(1);
            }
        } catch (Exception e) {
            this.f2437e = null;
        }
    }

    /* renamed from: c */
    public void m3745c() {
        if (this.f2437e != null) {
            try {
                this.f2437e.close();
            } catch (Exception e) {
            } finally {
                this.f2437e = null;
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: d */
    public int m3746d() {
        /*
        r7 = this;
        r1 = 0;
        r0 = -3;
        r2 = r7.f2438f;
        if (r2 == 0) goto L_0x0007;
    L_0x0006:
        return r0;
    L_0x0007:
        r2 = com.baidu.location.p043b.C1085h.m3963h();	 Catch:{ Exception -> 0x007b }
        if (r2 == 0) goto L_0x0006;
    L_0x000d:
        r2 = r7.f2437e;	 Catch:{ Exception -> 0x007b }
        if (r2 == 0) goto L_0x0006;
    L_0x0011:
        r2 = com.baidu.location.p043b.C1085h.m3959a();	 Catch:{ Exception -> 0x007b }
        r2 = r2.m3971i();	 Catch:{ Exception -> 0x007b }
        if (r2 == 0) goto L_0x0006;
    L_0x001b:
        r3 = r2.getBSSID();	 Catch:{ Exception -> 0x007b }
        if (r3 == 0) goto L_0x0006;
    L_0x0021:
        r2 = r2.getBSSID();	 Catch:{ Exception -> 0x007b }
        r3 = ":";
        r4 = "";
        r2 = r2.replace(r3, r4);	 Catch:{ Exception -> 0x007b }
        r2 = com.baidu.location.Jni.encode3(r2);	 Catch:{ Exception -> 0x007b }
        r3 = r7.f2437e;	 Catch:{ Exception -> 0x0068, all -> 0x0071 }
        r4 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x0068, all -> 0x0071 }
        r4.<init>();	 Catch:{ Exception -> 0x0068, all -> 0x0071 }
        r5 = "select * from hstdata where id = \"";
        r4 = r4.append(r5);	 Catch:{ Exception -> 0x0068, all -> 0x0071 }
        r2 = r4.append(r2);	 Catch:{ Exception -> 0x0068, all -> 0x0071 }
        r4 = "\";";
        r2 = r2.append(r4);	 Catch:{ Exception -> 0x0068, all -> 0x0071 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x0068, all -> 0x0071 }
        r4 = 0;
        r1 = r3.rawQuery(r2, r4);	 Catch:{ Exception -> 0x0068, all -> 0x0071 }
        if (r1 == 0) goto L_0x0066;
    L_0x0053:
        r2 = r1.moveToFirst();	 Catch:{ Exception -> 0x0068, all -> 0x007f }
        if (r2 == 0) goto L_0x0066;
    L_0x0059:
        r2 = 1;
        r0 = r1.getInt(r2);	 Catch:{ Exception -> 0x0068, all -> 0x007f }
    L_0x005e:
        if (r1 == 0) goto L_0x0006;
    L_0x0060:
        r1.close();	 Catch:{ Exception -> 0x0064 }
        goto L_0x0006;
    L_0x0064:
        r1 = move-exception;
        goto L_0x0006;
    L_0x0066:
        r0 = -2;
        goto L_0x005e;
    L_0x0068:
        r2 = move-exception;
        if (r1 == 0) goto L_0x0006;
    L_0x006b:
        r1.close();	 Catch:{ Exception -> 0x006f }
        goto L_0x0006;
    L_0x006f:
        r1 = move-exception;
        goto L_0x0006;
    L_0x0071:
        r2 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
    L_0x0075:
        if (r2 == 0) goto L_0x007a;
    L_0x0077:
        r2.close();	 Catch:{ Exception -> 0x007d }
    L_0x007a:
        throw r1;	 Catch:{ Exception -> 0x007b }
    L_0x007b:
        r1 = move-exception;
        goto L_0x0006;
    L_0x007d:
        r2 = move-exception;
        goto L_0x007a;
    L_0x007f:
        r2 = move-exception;
        r6 = r2;
        r2 = r1;
        r1 = r6;
        goto L_0x0075;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.g.d():int");
    }

    /* renamed from: e */
    public void m3747e() {
        Cursor cursor;
        Cursor cursor2 = null;
        Object obj = 1;
        if (!this.f2438f) {
            String replace;
            Object obj2;
            try {
                if (!C1085h.m3963h() || this.f2437e == null) {
                    m3742g();
                    return;
                }
                WifiInfo i = C1085h.m3959a().m3971i();
                if (i == null || i.getBSSID() == null) {
                    m3742g();
                    return;
                }
                replace = i.getBSSID().replace(":", "");
                obj2 = null;
                try {
                    cursor2 = this.f2437e.rawQuery("select * from hstdata where id = \"" + Jni.encode3(replace) + "\";", null);
                    if (cursor2 != null) {
                        if (cursor2.moveToFirst()) {
                            int i2 = cursor2.getInt(1);
                            if ((System.currentTimeMillis() / 1000) - ((long) cursor2.getInt(2)) <= 259200) {
                                Bundle bundle = new Bundle();
                                bundle.putByteArray("mac", replace.getBytes());
                                bundle.putInt("hotspot", i2);
                                m3736a(bundle);
                                obj = null;
                            }
                            obj2 = obj;
                            if (cursor2 != null) {
                                try {
                                    cursor2.close();
                                } catch (Exception e) {
                                }
                            }
                            if (obj2 == null) {
                                if (this.f2436a == null) {
                                    this.f2436a = new C1050a(this);
                                }
                                if (this.f2436a == null) {
                                    this.f2436a.m3732a(replace, m3741f());
                                }
                            }
                        }
                    }
                    int i3 = 1;
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                } catch (Exception e2) {
                    cursor = cursor2;
                    if (cursor != null) {
                        try {
                            cursor.close();
                        } catch (Exception e3) {
                        }
                    }
                    if (obj2 == null) {
                        if (this.f2436a == null) {
                            this.f2436a = new C1050a(this);
                        }
                        if (this.f2436a == null) {
                            this.f2436a.m3732a(replace, m3741f());
                        }
                    }
                } catch (Throwable th) {
                    if (cursor2 != null) {
                        try {
                            cursor2.close();
                        } catch (Exception e4) {
                        }
                    }
                }
                if (obj2 == null) {
                    if (this.f2436a == null) {
                        this.f2436a = new C1050a(this);
                    }
                    if (this.f2436a == null) {
                        this.f2436a.m3732a(replace, m3741f());
                    }
                }
            } catch (Exception e5) {
                cursor = cursor2;
                if (cursor != null) {
                    cursor.close();
                }
                if (obj2 == null) {
                    if (this.f2436a == null) {
                        this.f2436a = new C1050a(this);
                    }
                    if (this.f2436a == null) {
                        this.f2436a.m3732a(replace, m3741f());
                    }
                }
            } catch (Exception e6) {
            } catch (Throwable th2) {
                if (cursor2 != null) {
                    try {
                        cursor2.close();
                    } catch (Exception e42) {
                    }
                }
            }
        }
    }
}
