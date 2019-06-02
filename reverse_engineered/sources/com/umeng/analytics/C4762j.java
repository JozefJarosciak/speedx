package com.umeng.analytics;

import android.content.Context;
import android.content.SharedPreferences;
import ch.qos.logback.core.CoreConstants;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import com.umeng.onlineconfig.OnlineConfigAgent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONObject;
import p203u.aly.C5955u;
import p203u.aly.af;
import p203u.aly.ag;
import p203u.aly.ah;
import p203u.aly.av;
import p203u.aly.av.C5858e;
import p203u.aly.av.C5859f;
import p203u.aly.av.C5861h;
import p203u.aly.av.C5863i;
import p203u.aly.av.C5864j;
import p203u.aly.av.C5866l;
import p203u.aly.av.C5869o;
import p203u.aly.bo;
import p203u.aly.bq;

/* compiled from: StoreHelper */
/* renamed from: com.umeng.analytics.j */
public final class C4762j {
    /* renamed from: a */
    private static C4762j f16677a = null;
    /* renamed from: b */
    private static Context f16678b;
    /* renamed from: c */
    private static String f16679c;
    /* renamed from: e */
    private static long f16680e = 1209600000;
    /* renamed from: f */
    private static long f16681f = 2097152;
    /* renamed from: d */
    private C4760a f16682d;

    /* compiled from: StoreHelper */
    /* renamed from: com.umeng.analytics.j$1 */
    class C47551 extends bo {
        /* renamed from: a */
        final /* synthetic */ C4762j f16664a;

        C47551(C4762j c4762j) {
            this.f16664a = c4762j;
        }

        /* renamed from: a */
        public void mo6179a(Object obj, boolean z) {
            if (!obj.equals("success")) {
            }
        }
    }

    /* compiled from: StoreHelper */
    /* renamed from: com.umeng.analytics.j$a */
    public static class C4760a {
        /* renamed from: a */
        private final int f16674a;
        /* renamed from: b */
        private File f16675b;
        /* renamed from: c */
        private FilenameFilter f16676c;

        /* compiled from: StoreHelper */
        /* renamed from: com.umeng.analytics.j$a$2 */
        class C47592 implements FilenameFilter {
            /* renamed from: a */
            final /* synthetic */ C4760a f16673a;

            C47592(C4760a c4760a) {
                this.f16673a = c4760a;
            }

            public boolean accept(File file, String str) {
                return str.startsWith("um");
            }
        }

        public C4760a(Context context) {
            this(context, ".um");
        }

        public C4760a(Context context, String str) {
            this.f16674a = 10;
            this.f16676c = new C47592(this);
            this.f16675b = new File(context.getFilesDir(), str);
            if (!this.f16675b.exists() || !this.f16675b.isDirectory()) {
                this.f16675b.mkdir();
            }
        }

        /* renamed from: a */
        public boolean m18678a() {
            File[] listFiles = this.f16675b.listFiles();
            if (listFiles == null || listFiles.length <= 0) {
                return false;
            }
            return true;
        }

        /* renamed from: a */
        public void m18676a(C4761b c4761b) {
            int i;
            int i2 = 0;
            File[] listFiles = this.f16675b.listFiles(this.f16676c);
            if (listFiles != null && listFiles.length >= 10) {
                Arrays.sort(listFiles);
                final int length = listFiles.length - 10;
                C4754h.m18673b(new Runnable(this) {
                    /* renamed from: b */
                    final /* synthetic */ C4760a f16672b;

                    public void run() {
                        if (length > 0) {
                            bq.m21732a(C4762j.f16678b).m21752a((long) length, System.currentTimeMillis(), "__evp_file_of");
                        }
                    }
                });
                for (i = 0; i < length; i++) {
                    listFiles[i].delete();
                }
            }
            if (listFiles != null && listFiles.length > 0) {
                c4761b.mo7227a(this.f16675b);
                i = listFiles.length;
                while (i2 < i) {
                    try {
                        if (c4761b.mo7228b(listFiles[i2])) {
                            listFiles[i2].delete();
                        }
                    } catch (Throwable th) {
                        listFiles[i2].delete();
                    }
                    i2++;
                }
                c4761b.mo7229c(this.f16675b);
            }
        }

        /* renamed from: a */
        public void m18677a(byte[] bArr) {
            if (bArr != null && bArr.length != 0) {
                try {
                    ag.m21148a(new File(this.f16675b, String.format(Locale.US, "um_cache_%d.env", new Object[]{Long.valueOf(System.currentTimeMillis())})), bArr);
                } catch (Exception e) {
                }
            }
        }
    }

    /* compiled from: StoreHelper */
    /* renamed from: com.umeng.analytics.j$b */
    public interface C4761b {
        /* renamed from: a */
        void mo7227a(File file);

        /* renamed from: b */
        boolean mo7228b(File file);

        /* renamed from: c */
        void mo7229c(File file);
    }

    public C4762j(Context context) {
        this.f16682d = new C4760a(context);
    }

    /* renamed from: a */
    public static synchronized C4762j m18682a(Context context) {
        C4762j c4762j;
        synchronized (C4762j.class) {
            f16678b = context.getApplicationContext();
            f16679c = context.getPackageName();
            if (f16677a == null) {
                f16677a = new C4762j(context);
            }
            c4762j = f16677a;
        }
        return c4762j;
    }

    /* renamed from: a */
    private static boolean m18685a(File file) {
        long length = file.length();
        if (!file.exists() || length <= f16681f) {
            return false;
        }
        bq.m21732a(f16678b).m21752a(length, System.currentTimeMillis(), "__data_size_of");
        return true;
    }

    /* renamed from: a */
    public String[] m18696a() {
        SharedPreferences j = m18689j();
        String string = j.getString("au_p", null);
        String string2 = j.getString("au_u", null);
        if (string == null || string2 == null) {
            return null;
        }
        return new String[]{string, string2};
    }

    /* renamed from: b */
    String m18697b() {
        SharedPreferences a = C5955u.m22014a(f16678b);
        if (a != null) {
            return a.getString("appkey", null);
        }
        return null;
    }

    /* renamed from: a */
    void m18693a(String str) {
        SharedPreferences a = C5955u.m22014a(f16678b);
        if (a != null) {
            a.edit().putString("appkey", str).commit();
        }
    }

    /* renamed from: c */
    String m18699c() {
        SharedPreferences a = C5955u.m22014a(f16678b);
        if (a != null) {
            return a.getString("st", null);
        }
        return null;
    }

    /* renamed from: a */
    void m18692a(int i) {
        SharedPreferences a = C5955u.m22014a(f16678b);
        if (a != null) {
            a.edit().putInt("vt", i).commit();
        }
    }

    /* renamed from: d */
    int m18700d() {
        SharedPreferences a = C5955u.m22014a(f16678b);
        if (a != null) {
            return a.getInt("vt", 0);
        }
        return 0;
    }

    /* renamed from: e */
    public av m18701e() {
        Exception e;
        Throwable th;
        Throwable e2;
        av avVar;
        try {
            File file = new File(f16678b.getApplicationContext().getFilesDir().getAbsolutePath(), m18691l());
            if (C4762j.m18685a(file)) {
                file.delete();
                return null;
            } else if (!file.exists()) {
                return null;
            } else {
                FileInputStream fileInputStream;
                ObjectInputStream objectInputStream;
                try {
                    fileInputStream = new FileInputStream(file);
                    try {
                        objectInputStream = new ObjectInputStream(fileInputStream);
                    } catch (Exception e3) {
                        e = e3;
                        objectInputStream = null;
                        try {
                            e.printStackTrace();
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e4) {
                                    e4.printStackTrace();
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                    avVar = null;
                                } catch (IOException e42) {
                                    e42.printStackTrace();
                                    avVar = null;
                                }
                            } else {
                                avVar = null;
                            }
                            return avVar;
                        } catch (Throwable th2) {
                            th = th2;
                            if (objectInputStream != null) {
                                try {
                                    objectInputStream.close();
                                } catch (IOException e5) {
                                    e5.printStackTrace();
                                }
                            }
                            if (fileInputStream != null) {
                                try {
                                    fileInputStream.close();
                                } catch (IOException e52) {
                                    e52.printStackTrace();
                                }
                            }
                            throw th;
                        }
                    } catch (Throwable th3) {
                        th = th3;
                        objectInputStream = null;
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        if (fileInputStream != null) {
                            fileInputStream.close();
                        }
                        throw th;
                    }
                    try {
                        avVar = (av) objectInputStream.readObject();
                        if (objectInputStream != null) {
                            try {
                                objectInputStream.close();
                            } catch (IOException e6) {
                                try {
                                    e6.printStackTrace();
                                } catch (Exception e7) {
                                    e2 = e7;
                                    if (ah.f18581a) {
                                        ah.m21157a(e2);
                                    }
                                    return avVar;
                                }
                            }
                        }
                        if (fileInputStream != null) {
                            try {
                                fileInputStream.close();
                            } catch (IOException e62) {
                                e62.printStackTrace();
                            }
                        }
                    } catch (Exception e8) {
                        e = e8;
                        e.printStackTrace();
                        if (objectInputStream != null) {
                            objectInputStream.close();
                        }
                        if (fileInputStream != null) {
                            avVar = null;
                        } else {
                            fileInputStream.close();
                            avVar = null;
                        }
                        return avVar;
                    }
                } catch (Exception e9) {
                    e = e9;
                    objectInputStream = null;
                    fileInputStream = null;
                    e.printStackTrace();
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                        avVar = null;
                    } else {
                        avVar = null;
                    }
                    return avVar;
                } catch (Throwable th4) {
                    th = th4;
                    objectInputStream = null;
                    fileInputStream = null;
                    if (objectInputStream != null) {
                        objectInputStream.close();
                    }
                    if (fileInputStream != null) {
                        fileInputStream.close();
                    }
                    throw th;
                }
                return avVar;
            }
        } catch (Throwable th5) {
            Throwable th6 = th5;
            avVar = null;
            e2 = th6;
            if (ah.f18581a) {
                ah.m21157a(e2);
            }
            return avVar;
        }
    }

    /* renamed from: a */
    public void m18694a(av avVar) {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;
        Throwable e;
        FileOutputStream fileOutputStream2 = null;
        try {
            fileOutputStream = new FileOutputStream(new File(f16678b.getApplicationContext().getFilesDir().getAbsolutePath(), m18691l()));
            try {
                objectOutputStream = new ObjectOutputStream(fileOutputStream);
            } catch (Exception e2) {
                e = e2;
                objectOutputStream = null;
                fileOutputStream2 = fileOutputStream;
                try {
                    ah.m21157a(e);
                    e.printStackTrace();
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    if (fileOutputStream2 == null) {
                        try {
                            fileOutputStream2.close();
                        } catch (IOException e32) {
                            e32.printStackTrace();
                            return;
                        }
                    }
                } catch (Throwable th) {
                    e = th;
                    fileOutputStream = fileOutputStream2;
                    if (objectOutputStream != null) {
                        try {
                            objectOutputStream.close();
                        } catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                    if (fileOutputStream != null) {
                        try {
                            fileOutputStream.close();
                        } catch (IOException e42) {
                            e42.printStackTrace();
                        }
                    }
                    throw e;
                }
            } catch (Throwable th2) {
                e = th2;
                objectOutputStream = null;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw e;
            }
            try {
                objectOutputStream.writeObject(avVar);
                objectOutputStream.flush();
                if (objectOutputStream != null) {
                    try {
                        objectOutputStream.close();
                    } catch (IOException e322) {
                        e322.printStackTrace();
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (IOException e3222) {
                        e3222.printStackTrace();
                    }
                }
            } catch (Exception e5) {
                e = e5;
                fileOutputStream2 = fileOutputStream;
                ah.m21157a(e);
                e.printStackTrace();
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream2 == null) {
                    fileOutputStream2.close();
                }
            } catch (Throwable th3) {
                e = th3;
                if (objectOutputStream != null) {
                    objectOutputStream.close();
                }
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                }
                throw e;
            }
        } catch (Exception e6) {
            e = e6;
            objectOutputStream = null;
            ah.m21157a(e);
            e.printStackTrace();
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (fileOutputStream2 == null) {
                fileOutputStream2.close();
            }
        } catch (Throwable th4) {
            e = th4;
            objectOutputStream = null;
            fileOutputStream = null;
            if (objectOutputStream != null) {
                objectOutputStream.close();
            }
            if (fileOutputStream != null) {
                fileOutputStream.close();
            }
            throw e;
        }
    }

    /* renamed from: f */
    public void m18702f() {
        f16678b.deleteFile(m18690k());
        f16678b.deleteFile(m18691l());
        bq.m21732a(f16678b).m21758d(new C47551(this));
    }

    /* renamed from: a */
    public void m18695a(byte[] bArr) {
        this.f16682d.m18677a(bArr);
    }

    /* renamed from: g */
    public boolean m18703g() {
        return this.f16682d.m18678a();
    }

    /* renamed from: h */
    public C4760a m18704h() {
        return this.f16682d;
    }

    /* renamed from: j */
    private SharedPreferences m18689j() {
        return f16678b.getSharedPreferences("mobclick_agent_user_" + f16679c, 0);
    }

    /* renamed from: k */
    private String m18690k() {
        return "mobclick_agent_header_" + f16679c;
    }

    /* renamed from: l */
    private String m18691l() {
        SharedPreferences a = C5955u.m22014a(f16678b);
        if (a == null) {
            return "mobclick_agent_cached_" + f16679c + af.m21111a(f16678b);
        }
        int i = a.getInt("versioncode", 0);
        int parseInt = Integer.parseInt(af.m21111a(f16678b));
        if (i == 0 || parseInt == i) {
            return "mobclick_agent_cached_" + f16679c + af.m21111a(f16678b);
        }
        return "mobclick_agent_cached_" + f16679c + i;
    }

    /* renamed from: b */
    public byte[] m18698b(final av avVar) {
        try {
            JSONObject jSONObject = new JSONObject();
            final StringBuilder stringBuilder = new StringBuilder();
            jSONObject.put("header", new JSONObject(this) {
                /* renamed from: c */
                final /* synthetic */ C4762j f16667c;
            });
            JSONObject c47573 = new JSONObject(this) {
                /* renamed from: c */
                final /* synthetic */ C4762j f16670c;
            };
            if (c47573.length() > 0) {
                jSONObject.put("body", c47573);
            }
            ah.m21153a("serialize entry:" + String.valueOf(stringBuilder));
            return String.valueOf(jSONObject).getBytes();
        } catch (Throwable e) {
            ah.m21160b("Fail to serialize log ...", e);
            return null;
        }
    }

    /* renamed from: a */
    private void m18684a(av avVar, JSONObject jSONObject, StringBuilder stringBuilder) throws Exception {
        jSONObject.put("appkey", avVar.f18720a.f18678a);
        jSONObject.put(OnlineConfigAgent.KEY_CHANNEL, avVar.f18720a.f18679b);
        if (avVar.f18720a.f18680c != null) {
            jSONObject.put("secret", avVar.f18720a.f18680c);
        }
        jSONObject.put("app_version", avVar.f18720a.f18681d);
        jSONObject.put("display_name", avVar.f18720a.f18684g);
        jSONObject.put("package_name", avVar.f18720a.f18682e);
        jSONObject.put("app_signature", avVar.f18720a.f18683f);
        jSONObject.put(OnlineConfigAgent.KEY_VERSION_CODE, avVar.f18720a.f18685h);
        jSONObject.put("wrapper_type", avVar.f18720a.f18686i);
        jSONObject.put("wrapper_version", avVar.f18720a.f18687j);
        jSONObject.put("sdk_type", avVar.f18720a.f18688k);
        jSONObject.put(OnlineConfigAgent.KEY_SDK_VERSION, avVar.f18720a.f18689l);
        jSONObject.put("vertical_type", avVar.f18720a.f18690m);
        jSONObject.put(OnlineConfigAgent.KEY_ID, avVar.f18720a.f18691n);
        jSONObject.put("cpu", avVar.f18720a.f18692o);
        jSONObject.put("os", avVar.f18720a.f18693p);
        jSONObject.put("os_version", avVar.f18720a.f18694q);
        jSONObject.put(MapboxEvent.ATTRIBUTE_RESOLUTION, avVar.f18720a.f18695r);
        jSONObject.put("mc", avVar.f18720a.f18696s);
        jSONObject.put("device_id", avVar.f18720a.f18697t);
        jSONObject.put("device_model", avVar.f18720a.f18698u);
        jSONObject.put("device_board", avVar.f18720a.f18699v);
        jSONObject.put("device_brand", avVar.f18720a.f18700w);
        jSONObject.put("device_manutime", avVar.f18720a.f18701x);
        jSONObject.put("device_manufacturer", avVar.f18720a.f18702y);
        jSONObject.put("device_manuid", avVar.f18720a.f18703z);
        jSONObject.put("device_name", avVar.f18720a.f18663A);
        if (avVar.f18720a.f18664B != null) {
            jSONObject.put("sub_os_name", avVar.f18720a.f18664B);
        }
        if (avVar.f18720a.f18665C != null) {
            jSONObject.put("sub_os_version", avVar.f18720a.f18665C);
        }
        jSONObject.put("timezone", avVar.f18720a.f18666D);
        jSONObject.put("language", avVar.f18720a.f18667E);
        jSONObject.put(GeocodingCriteria.TYPE_COUNTRY, avVar.f18720a.f18668F);
        jSONObject.put(MapboxEvent.ATTRIBUTE_CARRIER, avVar.f18720a.f18669G);
        jSONObject.put("access", avVar.f18720a.f18670H);
        jSONObject.put("access_subtype", avVar.f18720a.f18671I);
        jSONObject.put("mccmnc", avVar.f18720a.f18672J == null ? "" : avVar.f18720a.f18672J);
        jSONObject.put("successful_requests", avVar.f18720a.f18673K);
        jSONObject.put("failed_requests", avVar.f18720a.f18674L);
        jSONObject.put("req_time", avVar.f18720a.f18675M);
        jSONObject.put("imprint", avVar.f18720a.f18676N);
        jSONObject.put("id_tracking", avVar.f18720a.f18677O);
        stringBuilder.append("sdk_version:").append(avVar.f18720a.f18689l).append(";device_id:").append(avVar.f18720a.f18697t).append(";device_manufacturer:").append(avVar.f18720a.f18702y).append(";device_board:").append(avVar.f18720a.f18699v).append(";device_brand:").append(avVar.f18720a.f18700w).append(";os_version:").append(avVar.f18720a.f18694q);
    }

    /* renamed from: b */
    private void m18687b(av avVar, JSONObject jSONObject, StringBuilder stringBuilder) throws Exception {
        JSONObject jSONObject2;
        List list;
        JSONArray jSONArray;
        int i;
        JSONArray jSONArray2;
        C5861h c5861h;
        JSONArray jSONArray3;
        int i2;
        JSONObject jSONObject3;
        Object value;
        JSONObject jSONObject4;
        JSONObject jSONObject5 = new JSONObject();
        if (!(avVar.f18721b.f18659h == null || avVar.f18721b.f18659h.f18612a == null || avVar.f18721b.f18659h.f18612a.size() <= 0)) {
            jSONObject2 = new JSONObject();
            for (Entry entry : avVar.f18721b.f18659h.f18612a.entrySet()) {
                String str = (String) entry.getKey();
                list = (List) entry.getValue();
                jSONArray = new JSONArray();
                for (i = 0; i < list.size(); i++) {
                    C5858e c5858e = (C5858e) list.get(i);
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.put("v_sum", c5858e.f18615a);
                    jSONObject6.put("ts_sum", c5858e.f18616b);
                    jSONObject6.put("tw_num", c5858e.f18617c);
                    jSONObject6.put("count", c5858e.f18618d);
                    jSONObject6.put("labels", new JSONArray(c5858e.f18619e));
                    jSONArray.put(jSONObject6);
                }
                jSONObject2.put(str, jSONArray);
            }
            jSONObject5.put("ag", jSONObject2);
        }
        if (!(avVar.f18721b.f18659h == null || avVar.f18721b.f18659h.f18613b == null || avVar.f18721b.f18659h.f18613b.size() <= 0)) {
            jSONObject2 = new JSONObject();
            for (Entry entry2 : avVar.f18721b.f18659h.f18613b.entrySet()) {
                str = (String) entry2.getKey();
                list = (List) entry2.getValue();
                jSONArray = new JSONArray();
                for (i = 0; i < list.size(); i++) {
                    C5859f c5859f = (C5859f) list.get(i);
                    jSONObject6 = new JSONObject();
                    jSONObject6.put("value", c5859f.f18621a);
                    jSONObject6.put("ts", c5859f.f18622b);
                    jSONObject6.put("label", c5859f.f18623c);
                    jSONArray.put(jSONObject6);
                }
                jSONObject2.put(str, jSONArray);
            }
            jSONObject5.put("ve_meta", jSONObject2);
        }
        if (jSONObject5 != null && jSONObject5.length() > 0) {
            jSONObject.put("cc", jSONObject5);
            stringBuilder.append("; cc: ").append(jSONObject5.toString());
        }
        if (avVar.f18721b.f18652a != null && avVar.f18721b.f18652a.size() > 0) {
            jSONArray2 = new JSONArray();
            for (i = 0; i < avVar.f18721b.f18652a.size(); i++) {
                c5861h = (C5861h) avVar.f18721b.f18652a.get(i);
                jSONArray3 = new JSONArray();
                for (i2 = 0; i2 < c5861h.f18630b.size(); i2++) {
                    jSONObject3 = new JSONObject();
                    C5864j c5864j = (C5864j) c5861h.f18630b.get(i2);
                    jSONObject3.put("id", c5864j.f18638c);
                    jSONObject3.put("ts", c5864j.f18639d);
                    jSONObject3.put("du", c5864j.f18640e);
                    for (Entry entry3 : c5864j.f18641f.entrySet()) {
                        value = entry3.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject3.put((String) entry3.getKey(), entry3.getValue());
                        }
                    }
                    jSONArray3.put(jSONObject3);
                }
                if (!(c5861h.f18629a == null || jSONArray3 == null || jSONArray3.length() <= 0)) {
                    JSONObject jSONObject7 = new JSONObject();
                    jSONObject7.put(c5861h.f18629a, jSONArray3);
                    jSONArray2.put(jSONObject7);
                }
            }
            if (jSONArray2 != null && jSONArray2.length() > 0) {
                jSONObject.put("ekv", jSONArray2);
                stringBuilder.append(";ekv:").append(jSONArray2.toString());
            }
        }
        if (avVar.f18721b.f18653b != null && avVar.f18721b.f18653b.size() > 0) {
            jSONArray2 = new JSONArray();
            for (i = 0; i < avVar.f18721b.f18653b.size(); i++) {
                c5861h = (C5861h) avVar.f18721b.f18653b.get(i);
                jSONArray3 = new JSONArray();
                for (i2 = 0; i2 < c5861h.f18630b.size(); i2++) {
                    c5864j = (C5864j) c5861h.f18630b.get(i2);
                    jSONObject3 = new JSONObject();
                    jSONObject3.put("id", c5864j.f18638c);
                    jSONObject3.put("ts", c5864j.f18639d);
                    jSONObject3.put("du", c5864j.f18640e);
                    for (Entry entry32 : c5864j.f18641f.entrySet()) {
                        value = entry32.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject3.put((String) entry32.getKey(), entry32.getValue());
                        }
                    }
                    jSONArray3.put(jSONObject3);
                }
                if (!(c5861h.f18629a == null || jSONArray3 == null || jSONArray3.length() <= 0)) {
                    jSONObject7 = new JSONObject();
                    jSONObject7.put(c5861h.f18629a, jSONArray3);
                    jSONArray2.put(jSONObject7);
                }
            }
            if (jSONArray2 != null && jSONArray2.length() > 0) {
                jSONObject.put("gkv", jSONArray2);
                stringBuilder.append("; gkv:").append(jSONArray2.toString());
            }
        }
        if (avVar.f18721b.f18660i != null && avVar.f18721b.f18660i.size() > 0) {
            JSONArray jSONArray4 = new JSONArray();
            for (int i3 = 0; i3 < avVar.f18721b.f18660i.size(); i3++) {
                C5863i c5863i = (C5863i) avVar.f18721b.f18660i.get(i3);
                JSONObject jSONObject8 = new JSONObject();
                jSONObject8.put("ts", c5863i.f18632a);
                jSONObject8.put("error_source", c5863i.f18633b);
                jSONObject8.put(CoreConstants.CONTEXT_SCOPE_VALUE, c5863i.f18634c);
                jSONArray4.put(jSONObject8);
            }
            jSONObject.put("error", jSONArray4);
        }
        if (avVar.f18721b.f18654c != null && avVar.f18721b.f18654c.size() > 0) {
            JSONArray jSONArray5 = new JSONArray();
            for (int i4 = 0; i4 < avVar.f18721b.f18654c.size(); i4++) {
                C5869o c5869o = (C5869o) avVar.f18721b.f18654c.get(i4);
                jSONObject5 = new JSONObject();
                jSONObject5.put("id", c5869o.f18707b);
                jSONObject5.put("start_time", c5869o.f18708c);
                jSONObject5.put("end_time", c5869o.f18709d);
                jSONObject5.put("duration", c5869o.f18710e);
                if (!(c5869o.f18713i.f18716a == 0 && c5869o.f18713i.f18717b == 0)) {
                    jSONObject7 = new JSONObject();
                    jSONObject7.put("download_traffic", c5869o.f18713i.f18716a);
                    jSONObject7.put("upload_traffic", c5869o.f18713i.f18717b);
                    jSONObject5.put("traffic", jSONObject7);
                }
                if (c5869o.f18712h.size() > 0) {
                    jSONArray2 = new JSONArray();
                    for (C5866l c5866l : c5869o.f18712h) {
                        jSONObject3 = new JSONObject();
                        jSONObject3.put("page_name", c5866l.f18647a);
                        jSONObject3.put("duration", c5866l.f18648b);
                        jSONArray2.put(jSONObject3);
                    }
                    jSONObject5.put("pages", jSONArray2);
                }
                if (c5869o.f18714j.f18645c != 0) {
                    JSONArray jSONArray6 = new JSONArray();
                    jSONObject2 = new JSONObject();
                    jSONObject2.put(MapboxEvent.KEY_LATITUDE, c5869o.f18714j.f18643a);
                    jSONObject2.put(MapboxEvent.KEY_LONGITUDE, c5869o.f18714j.f18644b);
                    jSONObject2.put("ts", c5869o.f18714j.f18645c);
                    jSONArray6.put(jSONObject2);
                    jSONObject5.put("locations", jSONArray6);
                }
                jSONArray5.put(jSONObject5);
            }
            if (jSONArray5 != null && jSONArray5.length() > 0) {
                jSONObject.put("sessions", jSONArray5);
                stringBuilder.append("; sessions:").append(jSONArray5.toString());
            }
        }
        if (avVar.f18721b.f18655d.f18607a != 0) {
            jSONObject4 = new JSONObject();
            jSONObject4.put("ts", avVar.f18721b.f18655d.f18607a);
            if (jSONObject4.length() > 0) {
                jSONObject.put("activate_msg", jSONObject4);
                stringBuilder.append("; active_msg: ").append(jSONObject4.toString());
            }
        }
        if (avVar.f18721b.f18656e.f18627c) {
            jSONObject4 = new JSONObject();
            jSONObject7 = new JSONObject();
            jSONObject7.put("interval", avVar.f18721b.f18656e.f18626b);
            jSONObject7.put("latency", avVar.f18721b.f18656e.f18625a);
            jSONObject4.put("latent", jSONObject7);
            if (jSONObject4.length() > 0) {
                jSONObject.put("control_policy", jSONObject4);
                stringBuilder.append("; control_policy: ").append(jSONObject4.toString());
            }
        }
        if (avVar.f18721b.f18657f.size() > 0) {
            JSONObject jSONObject9 = new JSONObject();
            for (Entry entry22 : avVar.f18721b.f18657f.entrySet()) {
                jSONObject9.put((String) entry22.getKey(), entry22.getValue());
            }
            jSONObject.put("group_info", jSONObject9);
        }
        if (!(avVar.f18721b.f18658g.f18609a == null && avVar.f18721b.f18658g.f18610b == null)) {
            jSONObject4 = new JSONObject();
            jSONObject4.put("provider", avVar.f18721b.f18658g.f18609a);
            jSONObject4.put("puid", avVar.f18721b.f18658g.f18610b);
            if (jSONObject4.length() > 0) {
                jSONObject.put("active_user", jSONObject4);
                stringBuilder.append("; active_user: ").append(jSONObject4.toString());
            }
        }
        if (avVar.f18721b.f18661j != null) {
            jSONObject.put("userlevel", avVar.f18721b.f18661j);
        }
    }
}
