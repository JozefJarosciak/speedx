package com.baidu.platform.comjni.map.basemap;

import android.os.Bundle;
import android.util.Log;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.comjni.map.basemap.a */
public class C1283a {
    /* renamed from: a */
    private static final String f3924a = C1283a.class.getSimpleName();
    /* renamed from: d */
    private static List<JNIBaseMap> f3925d = new ArrayList();
    /* renamed from: b */
    private long f3926b;
    /* renamed from: c */
    private JNIBaseMap f3927c;

    public C1283a() {
        this.f3926b = 0;
        this.f3927c = null;
        this.f3927c = new JNIBaseMap();
    }

    /* renamed from: a */
    public static int m4872a(long j, int i, int i2, int i3) {
        return JNIBaseMap.MapProc(j, i, i2, i3);
    }

    /* renamed from: b */
    public static void m4873b(long j, boolean z) {
        JNIBaseMap.SetMapCustomEnable(j, z);
    }

    /* renamed from: d */
    public static List<JNIBaseMap> m4874d() {
        return f3925d;
    }

    /* renamed from: a */
    public int m4875a(int i) {
        return this.f3927c.SetMapControlMode(this.f3926b, i);
    }

    /* renamed from: a */
    public long m4876a(int i, int i2, String str) {
        return this.f3927c.AddLayer(this.f3926b, i, i2, str);
    }

    /* renamed from: a */
    public String m4877a(int i, int i2) {
        return this.f3927c.ScrPtToGeoPoint(this.f3926b, i, i2);
    }

    /* renamed from: a */
    public String m4878a(int i, int i2, int i3, int i4) {
        return this.f3927c.GetNearlyObjID(this.f3926b, (long) i, i2, i3, i4);
    }

    /* renamed from: a */
    public String m4879a(String str) {
        return this.f3927c.OnSchcityGet(this.f3926b, str);
    }

    /* renamed from: a */
    public void m4880a(long j, boolean z) {
        this.f3927c.ShowLayers(this.f3926b, j, z);
    }

    /* renamed from: a */
    public void m4881a(Bundle bundle) {
        this.f3927c.SetMapStatus(this.f3926b, bundle);
    }

    /* renamed from: a */
    public void m4882a(String str, Bundle bundle) {
        this.f3927c.SaveScreenToLocal(this.f3926b, str, bundle);
    }

    /* renamed from: a */
    public void m4883a(boolean z) {
        this.f3927c.ShowSatelliteMap(this.f3926b, z);
    }

    /* renamed from: a */
    public void m4884a(Bundle[] bundleArr) {
        this.f3927c.addOverlayItems(this.f3926b, bundleArr, bundleArr.length);
    }

    /* renamed from: a */
    public boolean m4885a() {
        if (f3925d.size() == 0) {
            this.f3926b = this.f3927c.Create();
        } else {
            this.f3926b = this.f3927c.CreateDuplicate(((JNIBaseMap) f3925d.get(0)).f3923a);
        }
        this.f3927c.f3923a = this.f3926b;
        f3925d.add(this.f3927c);
        this.f3927c.SetCallback(this.f3926b, null);
        return true;
    }

    /* renamed from: a */
    public boolean m4886a(int i, boolean z) {
        return this.f3927c.OnRecordReload(this.f3926b, i, z);
    }

    /* renamed from: a */
    public boolean m4887a(int i, boolean z, int i2) {
        return this.f3927c.OnRecordStart(this.f3926b, i, z, i2);
    }

    /* renamed from: a */
    public boolean m4888a(long j) {
        return this.f3927c.LayersIsShow(this.f3926b, j);
    }

    /* renamed from: a */
    public boolean m4889a(String str, String str2) {
        return this.f3927c.SwitchBaseIndoorMapFloor(this.f3926b, str, str2);
    }

    /* renamed from: a */
    public boolean m4890a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        return this.f3927c.Init(this.f3926b, str, str2, str3, str4, str5, str6, str7, str8, i, i2, i3, i4, i5, i6, i7);
    }

    /* renamed from: a */
    public boolean m4891a(boolean z, boolean z2) {
        return this.f3927c.OnRecordImport(this.f3926b, z, z2);
    }

    /* renamed from: a */
    public int[] m4892a(int[] iArr, int i, int i2) {
        return this.f3927c.GetScreenBuf(this.f3926b, iArr, i, i2);
    }

    /* renamed from: b */
    public String m4893b(int i, int i2) {
        return this.f3927c.GeoPtToScrPoint(this.f3926b, i, i2);
    }

    /* renamed from: b */
    public void m4894b(long j) {
        this.f3927c.UpdateLayers(this.f3926b, j);
    }

    /* renamed from: b */
    public void m4895b(Bundle bundle) {
        this.f3927c.setMapStatusLimits(this.f3926b, bundle);
    }

    /* renamed from: b */
    public void m4896b(boolean z) {
        this.f3927c.ShowHotMap(this.f3926b, z);
    }

    /* renamed from: b */
    public boolean m4897b() {
        this.f3927c.Release(this.f3926b);
        f3925d.remove(this.f3927c);
        return true;
    }

    /* renamed from: b */
    public boolean m4898b(int i) {
        return this.f3927c.OnRecordAdd(this.f3926b, i);
    }

    /* renamed from: b */
    public boolean m4899b(int i, boolean z) {
        return this.f3927c.OnRecordRemove(this.f3926b, i, z);
    }

    /* renamed from: b */
    public boolean m4900b(int i, boolean z, int i2) {
        return this.f3927c.OnRecordSuspend(this.f3926b, i, z, i2);
    }

    /* renamed from: c */
    public float m4901c(Bundle bundle) {
        return this.f3927c.GetZoomToBound(this.f3926b, bundle);
    }

    /* renamed from: c */
    public long m4902c() {
        return this.f3926b;
    }

    /* renamed from: c */
    public String m4903c(int i) {
        return this.f3927c.OnRecordGetAt(this.f3926b, i);
    }

    /* renamed from: c */
    public void m4904c(boolean z) {
        this.f3927c.ShowTrafficMap(this.f3926b, z);
    }

    /* renamed from: c */
    public boolean m4905c(long j) {
        return this.f3927c.cleanSDKTileDataCache(this.f3926b, j);
    }

    /* renamed from: d */
    public void m4906d(long j) {
        this.f3927c.ClearLayer(this.f3926b, j);
    }

    /* renamed from: d */
    public void m4907d(boolean z) {
        this.f3927c.enableDrawHouseHeight(this.f3926b, z);
    }

    /* renamed from: d */
    public boolean m4908d(Bundle bundle) {
        return this.f3927c.updateSDKTile(this.f3926b, bundle);
    }

    /* renamed from: e */
    public String m4909e(long j) {
        return this.f3927c.getCompassPosition(this.f3926b, j);
    }

    /* renamed from: e */
    public void m4910e() {
        this.f3927c.OnPause(this.f3926b);
    }

    /* renamed from: e */
    public void m4911e(boolean z) {
        this.f3927c.ShowBaseIndoorMap(this.f3926b, z);
    }

    /* renamed from: e */
    public boolean m4912e(Bundle bundle) {
        return this.f3927c.addtileOverlay(this.f3926b, bundle);
    }

    /* renamed from: f */
    public void m4913f() {
        this.f3927c.OnResume(this.f3926b);
    }

    /* renamed from: f */
    public void m4914f(Bundle bundle) {
        this.f3927c.addOneOverlayItem(this.f3926b, bundle);
    }

    /* renamed from: g */
    public void m4915g() {
        this.f3927c.OnBackground(this.f3926b);
    }

    /* renamed from: g */
    public void m4916g(Bundle bundle) {
        this.f3927c.updateOneOverlayItem(this.f3926b, bundle);
    }

    /* renamed from: h */
    public void m4917h() {
        this.f3927c.OnForeground(this.f3926b);
    }

    /* renamed from: h */
    public void m4918h(Bundle bundle) {
        this.f3927c.removeOneOverlayItem(this.f3926b, bundle);
    }

    /* renamed from: i */
    public void m4919i() {
        this.f3927c.ResetImageRes(this.f3926b);
    }

    /* renamed from: j */
    public Bundle m4920j() {
        return this.f3927c.GetMapStatus(this.f3926b);
    }

    /* renamed from: k */
    public Bundle m4921k() {
        Bundle mapStatusLimits = this.f3927c.getMapStatusLimits(this.f3926b);
        Log.d("test", "GetMapStatusLimits, maddr: " + this.f3926b + "bundle: " + mapStatusLimits);
        return mapStatusLimits;
    }

    /* renamed from: l */
    public Bundle m4922l() {
        return this.f3927c.getDrawingMapStatus(this.f3926b);
    }

    /* renamed from: m */
    public boolean m4923m() {
        return this.f3927c.GetBaiduHotMapCityInfo(this.f3926b);
    }

    /* renamed from: n */
    public String m4924n() {
        return this.f3927c.OnRecordGetAll(this.f3926b);
    }

    /* renamed from: o */
    public String m4925o() {
        return this.f3927c.OnHotcityGet(this.f3926b);
    }

    /* renamed from: p */
    public void m4926p() {
        this.f3927c.PostStatInfo(this.f3926b);
    }

    /* renamed from: q */
    public boolean m4927q() {
        return this.f3927c.isDrawHouseHeightEnable(this.f3926b);
    }

    /* renamed from: r */
    public void m4928r() {
        this.f3927c.clearHeatMapLayerCache(this.f3926b);
    }

    /* renamed from: s */
    public MapBaseIndoorMapInfo m4929s() {
        JSONException e;
        String str = this.f3927c.getfocusedBaseIndoorMapInfo(this.f3926b);
        if (str == null) {
            return null;
        }
        String str2 = "";
        String str3 = new String();
        ArrayList arrayList = new ArrayList(1);
        try {
            JSONObject jSONObject = new JSONObject(str);
            str2 = jSONObject.optString("focusindoorid");
            str = jSONObject.optString("curfloor");
            try {
                JSONArray optJSONArray = jSONObject.optJSONArray("floorlist");
                if (optJSONArray == null) {
                    return null;
                }
                for (int i = 0; i < optJSONArray.length(); i++) {
                    arrayList.add(optJSONArray.get(i).toString());
                }
                return new MapBaseIndoorMapInfo(str2, str, arrayList);
            } catch (JSONException e2) {
                e = e2;
                e.printStackTrace();
                return new MapBaseIndoorMapInfo(str2, str, arrayList);
            }
        } catch (JSONException e3) {
            e = e3;
            str = str3;
            e.printStackTrace();
            return new MapBaseIndoorMapInfo(str2, str, arrayList);
        }
    }

    /* renamed from: t */
    public boolean m4930t() {
        return this.f3927c.IsBaseIndoorMapMode(this.f3926b);
    }
}
