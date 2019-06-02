package com.baidu.platform.comapi.favrite;

import android.os.Bundle;
import android.text.TextUtils;
import ch.qos.logback.core.joran.action.Action;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.platform.comjni.map.favorite.C1289a;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.comapi.favrite.a */
public class C1227a {
    /* renamed from: b */
    private static C1227a f3635b = null;
    /* renamed from: a */
    private C1289a f3636a = null;
    /* renamed from: c */
    private boolean f3637c = false;
    /* renamed from: d */
    private boolean f3638d = false;
    /* renamed from: e */
    private Vector<String> f3639e = null;
    /* renamed from: f */
    private Vector<String> f3640f = null;
    /* renamed from: g */
    private boolean f3641g = false;
    /* renamed from: h */
    private C1226c f3642h = new C1226c();
    /* renamed from: i */
    private C1225b f3643i = new C1225b();

    /* renamed from: com.baidu.platform.comapi.favrite.a$a */
    class C1224a implements Comparator<String> {
        /* renamed from: a */
        final /* synthetic */ C1227a f3627a;

        C1224a(C1227a c1227a) {
            this.f3627a = c1227a;
        }

        /* renamed from: a */
        public int m4575a(String str, String str2) {
            return str2.compareTo(str);
        }

        public /* synthetic */ int compare(Object obj, Object obj2) {
            return m4575a((String) obj, (String) obj2);
        }
    }

    /* renamed from: com.baidu.platform.comapi.favrite.a$b */
    private class C1225b {
        /* renamed from: a */
        final /* synthetic */ C1227a f3628a;
        /* renamed from: b */
        private long f3629b;
        /* renamed from: c */
        private long f3630c;

        private C1225b(C1227a c1227a) {
            this.f3628a = c1227a;
        }

        /* renamed from: a */
        private void m4576a() {
            this.f3629b = System.currentTimeMillis();
        }

        /* renamed from: b */
        private void m4578b() {
            this.f3630c = System.currentTimeMillis();
        }

        /* renamed from: c */
        private boolean m4581c() {
            return this.f3630c - this.f3629b > 1000;
        }
    }

    /* renamed from: com.baidu.platform.comapi.favrite.a$c */
    private class C1226c {
        /* renamed from: a */
        final /* synthetic */ C1227a f3631a;
        /* renamed from: b */
        private String f3632b;
        /* renamed from: c */
        private long f3633c;
        /* renamed from: d */
        private long f3634d;

        private C1226c(C1227a c1227a) {
            this.f3631a = c1227a;
            this.f3633c = 5000;
            this.f3634d = 0;
        }

        /* renamed from: a */
        private String m4582a() {
            return this.f3632b;
        }

        /* renamed from: a */
        private void m4584a(String str) {
            this.f3632b = str;
            this.f3634d = System.currentTimeMillis();
        }

        /* renamed from: b */
        private boolean m4586b() {
            return TextUtils.isEmpty(this.f3632b);
        }

        /* renamed from: c */
        private boolean m4589c() {
            return true;
        }
    }

    private C1227a() {
    }

    /* renamed from: a */
    public static C1227a m4590a() {
        if (f3635b == null) {
            synchronized (C1227a.class) {
                if (f3635b == null) {
                    f3635b = new C1227a();
                    f3635b.m4592h();
                }
            }
        }
        return f3635b;
    }

    /* renamed from: g */
    public static boolean m4591g() {
        return (f3635b == null || f3635b.f3636a == null || !f3635b.f3636a.m4962d()) ? false : true;
    }

    /* renamed from: h */
    private boolean m4592h() {
        if (this.f3636a != null) {
            return true;
        }
        this.f3636a = new C1289a();
        if (this.f3636a.m4952a() == 0) {
            this.f3636a = null;
            return false;
        }
        m4594j();
        m4593i();
        return true;
    }

    /* renamed from: i */
    private boolean m4593i() {
        if (this.f3636a == null) {
            return false;
        }
        String str = "fav_poi";
        this.f3636a.m4953a(1);
        return this.f3636a.m4956a(SysOSUtil.getModuleFileName() + "/", str, "fifo", 10, 501, -1);
    }

    /* renamed from: j */
    private void m4594j() {
        this.f3637c = false;
        this.f3638d = false;
    }

    /* renamed from: a */
    public synchronized int m4595a(String str, FavSyncPoi favSyncPoi) {
        int i;
        if (this.f3636a == null) {
            i = 0;
        } else if (str == null || str.equals("") || favSyncPoi == null) {
            i = -1;
        } else {
            m4594j();
            ArrayList e = m4603e();
            if ((e != null ? e.size() : 0) + 1 > 500) {
                i = -2;
            } else {
                if (e != null && e.size() > 0) {
                    Iterator it = e.iterator();
                    while (it.hasNext()) {
                        FavSyncPoi b = m4597b((String) it.next());
                        if (b != null && str.equals(b.f3618b)) {
                            i = -1;
                            break;
                        }
                    }
                }
                String str2 = "";
                try {
                    JSONObject jSONObject = new JSONObject();
                    favSyncPoi.f3618b = str;
                    String valueOf = String.valueOf(System.currentTimeMillis());
                    String str3 = valueOf + "_" + favSyncPoi.hashCode();
                    favSyncPoi.f3624h = valueOf;
                    favSyncPoi.f3617a = str3;
                    jSONObject.put("bdetail", favSyncPoi.f3625i);
                    jSONObject.put("uspoiname", favSyncPoi.f3618b);
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("x", favSyncPoi.f3619c.getmPtx());
                    jSONObject2.put("y", favSyncPoi.f3619c.getmPty());
                    jSONObject.put("pt", jSONObject2);
                    jSONObject.put("ncityid", favSyncPoi.f3621e);
                    jSONObject.put("npoitype", favSyncPoi.f3623g);
                    jSONObject.put("uspoiuid", favSyncPoi.f3622f);
                    jSONObject.put("addr", favSyncPoi.f3620d);
                    jSONObject.put("addtimesec", favSyncPoi.f3624h);
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("Fav_Sync", jSONObject);
                    jSONObject2.put("Fav_Content", favSyncPoi.f3626j);
                    if (this.f3636a.m4955a(str3, jSONObject2.toString())) {
                        m4594j();
                        i = 1;
                    } else {
                        C1227a.m4591g();
                        i = 0;
                    }
                } catch (JSONException e2) {
                    i = e2;
                    i = 0;
                    return i;
                } finally {
                    C1227a.m4591g();
                }
            }
        }
        return i;
    }

    /* renamed from: a */
    public synchronized boolean m4596a(String str) {
        boolean z = false;
        synchronized (this) {
            if (!(this.f3636a == null || str == null)) {
                if (!str.equals("") && m4601c(str)) {
                    m4594j();
                    z = this.f3636a.m4954a(str);
                }
            }
        }
        return z;
    }

    /* renamed from: b */
    public FavSyncPoi m4597b(String str) {
        if (this.f3636a == null || str == null || str.equals("")) {
            return null;
        }
        try {
            if (!m4601c(str)) {
                return null;
            }
            FavSyncPoi favSyncPoi = new FavSyncPoi();
            String b = this.f3636a.m4958b(str);
            if (b == null || b.equals("")) {
                return null;
            }
            JSONObject jSONObject = new JSONObject(b);
            JSONObject optJSONObject = jSONObject.optJSONObject("Fav_Sync");
            String optString = jSONObject.optString("Fav_Content");
            favSyncPoi.f3618b = optJSONObject.optString("uspoiname");
            JSONObject optJSONObject2 = optJSONObject.optJSONObject("pt");
            favSyncPoi.f3619c = new Point(optJSONObject2.optInt("x"), optJSONObject2.optInt("y"));
            favSyncPoi.f3621e = optJSONObject.optString("ncityid");
            favSyncPoi.f3622f = optJSONObject.optString("uspoiuid");
            favSyncPoi.f3623g = optJSONObject.optInt("npoitype");
            favSyncPoi.f3620d = optJSONObject.optString("addr");
            favSyncPoi.f3624h = optJSONObject.optString("addtimesec");
            favSyncPoi.f3625i = optJSONObject.optBoolean("bdetail");
            favSyncPoi.f3626j = optString;
            favSyncPoi.f3617a = str;
            return favSyncPoi;
        } catch (NullPointerException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* renamed from: b */
    public void m4598b() {
        if (f3635b != null) {
            if (f3635b.f3636a != null) {
                f3635b.f3636a.m4957b();
                f3635b.f3636a = null;
            }
            f3635b = null;
        }
    }

    /* renamed from: b */
    public synchronized boolean m4599b(String str, FavSyncPoi favSyncPoi) {
        boolean z = false;
        synchronized (this) {
            if (!(this.f3636a == null || str == null || str.equals("") || favSyncPoi == null)) {
                if (m4601c(str)) {
                    try {
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("uspoiname", favSyncPoi.f3618b);
                        JSONObject jSONObject2 = new JSONObject();
                        jSONObject2.put("x", favSyncPoi.f3619c.getmPtx());
                        jSONObject2.put("y", favSyncPoi.f3619c.getmPty());
                        jSONObject.put("pt", jSONObject2);
                        jSONObject.put("ncityid", favSyncPoi.f3621e);
                        jSONObject.put("npoitype", favSyncPoi.f3623g);
                        jSONObject.put("uspoiuid", favSyncPoi.f3622f);
                        jSONObject.put("addr", favSyncPoi.f3620d);
                        favSyncPoi.f3624h = String.valueOf(System.currentTimeMillis());
                        jSONObject.put("addtimesec", favSyncPoi.f3624h);
                        jSONObject.put("bdetail", false);
                        jSONObject2 = new JSONObject();
                        jSONObject2.put("Fav_Sync", jSONObject);
                        jSONObject2.put("Fav_Content", favSyncPoi.f3626j);
                        m4594j();
                        if (this.f3636a != null && this.f3636a.m4959b(str, jSONObject2.toString())) {
                            z = true;
                        }
                    } catch (JSONException e) {
                    }
                }
            }
        }
        return z;
    }

    /* renamed from: c */
    public synchronized boolean m4600c() {
        boolean z;
        if (this.f3636a == null) {
            z = false;
        } else {
            m4594j();
            z = this.f3636a.m4960c();
            C1227a.m4591g();
        }
        return z;
    }

    /* renamed from: c */
    public boolean m4601c(String str) {
        return (this.f3636a == null || str == null || str.equals("") || !this.f3636a.m4961c(str)) ? false : true;
    }

    /* renamed from: d */
    public ArrayList<String> m4602d() {
        if (this.f3636a == null) {
            return null;
        }
        if (this.f3638d && this.f3640f != null) {
            return new ArrayList(this.f3640f);
        }
        try {
            Bundle bundle = new Bundle();
            this.f3636a.m4951a(bundle);
            String[] stringArray = bundle.getStringArray("rstString");
            if (stringArray != null) {
                if (this.f3640f == null) {
                    this.f3640f = new Vector();
                } else {
                    this.f3640f.clear();
                }
                for (int i = 0; i < stringArray.length; i++) {
                    if (!stringArray[i].equals("data_version")) {
                        String b = this.f3636a.m4958b(stringArray[i]);
                        if (!(b == null || b.equals(""))) {
                            this.f3640f.add(stringArray[i]);
                        }
                    }
                }
                if (this.f3640f.size() > 0) {
                    try {
                        Collections.sort(this.f3640f, new C1224a(this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.f3638d = true;
                }
            } else if (this.f3640f != null) {
                this.f3640f.clear();
                this.f3640f = null;
            }
            ArrayList<String> arrayList = (this.f3640f == null || this.f3640f.isEmpty()) ? null : new ArrayList(this.f3640f);
            return arrayList;
        } catch (Exception e2) {
            return null;
        }
    }

    /* renamed from: e */
    public ArrayList<String> m4603e() {
        if (this.f3636a == null) {
            return null;
        }
        if (this.f3637c && this.f3639e != null) {
            return new ArrayList(this.f3639e);
        }
        try {
            Bundle bundle = new Bundle();
            this.f3636a.m4951a(bundle);
            String[] stringArray = bundle.getStringArray("rstString");
            if (stringArray != null) {
                if (this.f3639e == null) {
                    this.f3639e = new Vector();
                } else {
                    this.f3639e.clear();
                }
                for (String str : stringArray) {
                    if (!str.equals("data_version")) {
                        this.f3639e.add(str);
                    }
                }
                if (this.f3639e.size() > 0) {
                    try {
                        Collections.sort(this.f3639e, new C1224a(this));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    this.f3637c = true;
                }
            } else if (this.f3639e != null) {
                this.f3639e.clear();
                this.f3639e = null;
            }
            return (this.f3639e == null || this.f3639e.size() == 0) ? null : new ArrayList(this.f3639e);
        } catch (Exception e2) {
            return null;
        }
    }

    /* renamed from: f */
    public String m4604f() {
        if (this.f3643i.m4581c() && !this.f3642h.m4589c() && !this.f3642h.m4586b()) {
            return this.f3642h.m4582a();
        }
        this.f3643i.m4576a();
        if (this.f3636a == null) {
            return null;
        }
        ArrayList d = m4602d();
        JSONObject jSONObject = new JSONObject();
        if (d != null) {
            try {
                JSONArray jSONArray = new JSONArray();
                int i = 0;
                Iterator it = d.iterator();
                while (it.hasNext()) {
                    int i2;
                    String str = (String) it.next();
                    if (!(str == null || str.equals("data_version"))) {
                        String b = this.f3636a.m4958b(str);
                        if (!(b == null || b.equals(""))) {
                            JSONObject optJSONObject = new JSONObject(b).optJSONObject("Fav_Sync");
                            optJSONObject.put(Action.KEY_ATTRIBUTE, str);
                            jSONArray.put(i, optJSONObject);
                            i2 = i + 1;
                            i = i2;
                        }
                    }
                    i2 = i;
                    i = i2;
                }
                if (i > 0) {
                    jSONObject.put("favcontents", jSONArray);
                    jSONObject.put("favpoinum", i);
                }
            } catch (JSONException e) {
                return null;
            }
        }
        this.f3643i.m4578b();
        this.f3642h.m4584a(jSONObject.toString());
        return this.f3642h.m4582a();
    }
}
