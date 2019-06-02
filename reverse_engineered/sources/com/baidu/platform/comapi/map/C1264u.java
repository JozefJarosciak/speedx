package com.baidu.platform.comapi.map;

import android.os.Handler;
import com.alibaba.fastjson.asm.Opcodes;
import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comjni.map.basemap.C1283a;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.comapi.map.u */
public class C1264u {
    /* renamed from: a */
    private static final String f3823a = C1264u.class.getSimpleName();
    /* renamed from: c */
    private static C1264u f3824c;
    /* renamed from: b */
    private C1283a f3825b;
    /* renamed from: d */
    private C1268z f3826d;
    /* renamed from: e */
    private Handler f3827e;

    private C1264u() {
    }

    /* renamed from: a */
    public static C1264u m4781a() {
        if (f3824c == null) {
            f3824c = new C1264u();
            f3824c.m4784g();
        }
        return f3824c;
    }

    /* renamed from: g */
    private void m4784g() {
        m4785h();
        this.f3826d = new C1268z();
        this.f3827e = new C1265v(this);
        MessageCenter.registMessage(m_AppUI.V_WM_VDATAENGINE, this.f3827e);
    }

    /* renamed from: h */
    private void m4785h() {
        EnvironmentUtilities.initAppDirectory(BMapManager.getContext());
        this.f3825b = new C1283a();
        this.f3825b.m4885a();
        String moduleFileName = SysOSUtil.getModuleFileName();
        String appSDCardPath = EnvironmentUtilities.getAppSDCardPath();
        String appCachePath = EnvironmentUtilities.getAppCachePath();
        String appSecondCachePath = EnvironmentUtilities.getAppSecondCachePath();
        int mapTmpStgMax = EnvironmentUtilities.getMapTmpStgMax();
        int domTmpStgMax = EnvironmentUtilities.getDomTmpStgMax();
        int itsTmpStgMax = EnvironmentUtilities.getItsTmpStgMax();
        String str = SysOSUtil.getDensityDpi() >= Opcodes.GETFIELD ? "/h/" : "/l/";
        String str2 = moduleFileName + "/cfg";
        String str3 = appSDCardPath + "/vmp";
        moduleFileName = str2 + "/a/";
        String str4 = str2 + "/a/";
        String str5 = str2 + "/idrres/";
        this.f3825b.m4890a(moduleFileName, str3 + str, appCachePath + "/tmp/", appSecondCachePath + "/tmp/", str3 + str, str4, null, str5, SysOSUtil.getScreenSizeX(), SysOSUtil.getScreenSizeY(), SysOSUtil.getDensityDpi(), mapTmpStgMax, domTmpStgMax, itsTmpStgMax, 0);
        this.f3825b.m4913f();
    }

    /* renamed from: a */
    public ArrayList<C1263t> m4786a(String str) {
        if (str.equals("") || this.f3825b == null) {
            return null;
        }
        String str2 = "";
        String a = this.f3825b.m4879a(str);
        if (a == null || a.equals("")) {
            return null;
        }
        ArrayList<C1263t> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(a);
            if (jSONObject == null || jSONObject.length() == 0) {
                return null;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("dataset");
            if (optJSONArray == null) {
                return null;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                C1263t c1263t = new C1263t();
                JSONObject jSONObject2 = optJSONArray.getJSONObject(i);
                c1263t.f3818a = jSONObject2.optInt("id");
                c1263t.f3819b = jSONObject2.optString("name");
                c1263t.f3820c = jSONObject2.optInt("mapsize");
                c1263t.f3821d = jSONObject2.optInt("cty");
                if (jSONObject2.has("child")) {
                    JSONArray optJSONArray2 = jSONObject2.optJSONArray("child");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        C1263t c1263t2 = new C1263t();
                        JSONObject optJSONObject = optJSONArray2.optJSONObject(i2);
                        c1263t2.f3818a = optJSONObject.optInt("id");
                        c1263t2.f3819b = optJSONObject.optString("name");
                        c1263t2.f3820c = optJSONObject.optInt("mapsize");
                        c1263t2.f3821d = optJSONObject.optInt("cty");
                        arrayList2.add(c1263t2);
                    }
                    c1263t.m4780a(arrayList2);
                }
                arrayList.add(c1263t);
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void m4787a(C1131y c1131y) {
        if (this.f3826d != null) {
            this.f3826d.m4804a(c1131y);
        }
    }

    /* renamed from: a */
    public boolean m4788a(int i) {
        return (this.f3825b == null || i < 0) ? false : this.f3825b.m4898b(i);
    }

    /* renamed from: a */
    public boolean m4789a(boolean z, boolean z2) {
        return this.f3825b == null ? false : this.f3825b.m4891a(z, z2);
    }

    /* renamed from: b */
    public void m4790b() {
        MessageCenter.unregistMessage(m_AppUI.V_WM_VDATAENGINE, this.f3827e);
        this.f3825b.m4897b();
        f3824c = null;
    }

    /* renamed from: b */
    public void m4791b(C1131y c1131y) {
        if (this.f3826d != null) {
            this.f3826d.m4805b(c1131y);
        }
    }

    /* renamed from: b */
    public boolean m4792b(int i) {
        return (this.f3825b == null || i < 0) ? false : this.f3825b.m4887a(i, false, 0);
    }

    /* renamed from: c */
    public ArrayList<C1263t> m4793c() {
        if (this.f3825b == null) {
            return null;
        }
        String str = "";
        String o = this.f3825b.m4925o();
        ArrayList<C1263t> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(o).optJSONArray("dataset");
            for (int i = 0; i < optJSONArray.length(); i++) {
                C1263t c1263t = new C1263t();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                c1263t.f3818a = optJSONObject.optInt("id");
                c1263t.f3819b = optJSONObject.optString("name");
                c1263t.f3820c = optJSONObject.optInt("mapsize");
                c1263t.f3821d = optJSONObject.optInt("cty");
                if (optJSONObject.has("child")) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("child");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        C1263t c1263t2 = new C1263t();
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                        c1263t2.f3818a = optJSONObject2.optInt("id");
                        c1263t2.f3819b = optJSONObject2.optString("name");
                        c1263t2.f3820c = optJSONObject2.optInt("mapsize");
                        c1263t2.f3821d = optJSONObject2.optInt("cty");
                        arrayList2.add(c1263t2);
                    }
                    c1263t.m4780a(arrayList2);
                }
                arrayList.add(c1263t);
            }
            return arrayList;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public boolean m4794c(int i) {
        return (this.f3825b == null || i < 0) ? false : this.f3825b.m4900b(i, false, 0);
    }

    /* renamed from: d */
    public ArrayList<C1263t> m4795d() {
        String str = "";
        if (this.f3825b == null) {
            return null;
        }
        String a = this.f3825b.m4879a("");
        ArrayList<C1263t> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(a).optJSONArray("dataset");
            for (int i = 0; i < optJSONArray.length(); i++) {
                C1263t c1263t = new C1263t();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                c1263t.f3818a = optJSONObject.optInt("id");
                c1263t.f3819b = optJSONObject.optString("name");
                c1263t.f3820c = optJSONObject.optInt("mapsize");
                c1263t.f3821d = optJSONObject.optInt("cty");
                if (optJSONObject.has("child")) {
                    JSONArray optJSONArray2 = optJSONObject.optJSONArray("child");
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                        C1263t c1263t2 = new C1263t();
                        JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                        c1263t2.f3818a = optJSONObject2.optInt("id");
                        c1263t2.f3819b = optJSONObject2.optString("name");
                        c1263t2.f3820c = optJSONObject2.optInt("mapsize");
                        c1263t2.f3821d = optJSONObject2.optInt("cty");
                        arrayList2.add(c1263t2);
                    }
                    c1263t.m4780a(arrayList2);
                }
                arrayList.add(c1263t);
            }
            return arrayList;
        } catch (JSONException e) {
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    /* renamed from: d */
    public boolean m4796d(int i) {
        return this.f3825b == null ? false : this.f3825b.m4900b(0, true, i);
    }

    /* renamed from: e */
    public ArrayList<C1267x> m4797e() {
        if (this.f3825b == null) {
            return null;
        }
        String str = "";
        String n = this.f3825b.m4924n();
        if (n == null || n.equals("")) {
            return null;
        }
        ArrayList<C1267x> arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(n);
            if (jSONObject.length() == 0) {
                return null;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("dataset");
            for (int i = 0; i < optJSONArray.length(); i++) {
                C1267x c1267x = new C1267x();
                C1266w c1266w = new C1266w();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                c1266w.f3829a = optJSONObject.optInt("id");
                c1266w.f3830b = optJSONObject.optString("name");
                c1266w.f3831c = optJSONObject.optString("pinyin");
                c1266w.f3836h = optJSONObject.optInt("mapoldsize");
                c1266w.f3837i = optJSONObject.optInt("ratio");
                c1266w.f3840l = optJSONObject.optInt("status");
                c1266w.f3835g = new GeoPoint((double) optJSONObject.optInt("y"), (double) optJSONObject.optInt("x"));
                if (optJSONObject.optInt("up") == 1) {
                    c1266w.f3838j = true;
                } else {
                    c1266w.f3838j = false;
                }
                c1266w.f3833e = optJSONObject.optInt("lev");
                if (c1266w.f3838j) {
                    c1266w.f3839k = optJSONObject.optInt("mapsize");
                } else {
                    c1266w.f3839k = 0;
                }
                c1267x.m4802a(c1266w);
                arrayList.add(c1267x);
            }
            return arrayList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: e */
    public boolean m4798e(int i) {
        return (this.f3825b == null || i < 0) ? false : this.f3825b.m4899b(i, false);
    }

    /* renamed from: f */
    public boolean m4799f(int i) {
        return (this.f3825b == null || i < 0) ? false : this.f3825b.m4886a(i, false);
    }

    /* renamed from: g */
    public C1267x m4800g(int i) {
        if (this.f3825b == null || i < 0) {
            return null;
        }
        String str = "";
        String c = this.f3825b.m4903c(i);
        if (c == null || c.equals("")) {
            return null;
        }
        C1267x c1267x = new C1267x();
        C1266w c1266w = new C1266w();
        try {
            JSONObject jSONObject = new JSONObject(c);
            if (jSONObject.length() == 0) {
                return null;
            }
            c1266w.f3829a = jSONObject.optInt("id");
            c1266w.f3830b = jSONObject.optString("name");
            c1266w.f3831c = jSONObject.optString("pinyin");
            c1266w.f3832d = jSONObject.optString("headchar");
            c1266w.f3836h = jSONObject.optInt("mapoldsize");
            c1266w.f3837i = jSONObject.optInt("ratio");
            c1266w.f3840l = jSONObject.optInt("status");
            c1266w.f3835g = new GeoPoint((double) jSONObject.optInt("y"), (double) jSONObject.optInt("x"));
            if (jSONObject.optInt("up") == 1) {
                c1266w.f3838j = true;
            } else {
                c1266w.f3838j = false;
            }
            c1266w.f3833e = jSONObject.optInt("lev");
            if (c1266w.f3838j) {
                c1266w.f3839k = jSONObject.optInt("mapsize");
            } else {
                c1266w.f3839k = 0;
            }
            c1266w.f3834f = jSONObject.optInt("ver");
            c1267x.m4802a(c1266w);
            return c1267x;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
