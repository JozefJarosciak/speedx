package com.baidu.platform.comapi.map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.view.Display;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import com.alibaba.fastjson.asm.Opcodes;
import com.alipay.sdk.authjs.C0840a;
import com.alipay.sdk.packet.C0861d;
import com.avos.avoscloud.AVException;
import com.baidu.mapapi.UIMsg.k_event;
import com.baidu.mapapi.UIMsg.m_AppUI;
import com.baidu.mapapi.common.EnvironmentUtilities;
import com.baidu.mapapi.common.SysOSUtil;
import com.baidu.mapapi.map.MapBaseIndoorMapInfo;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.model.LatLngBounds.Builder;
import com.baidu.mapapi.model.ParcelItem;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comapi.map.C1255j.C1254a;
import com.baidu.platform.comjni.map.basemap.BaseMapCallback;
import com.baidu.platform.comjni.map.basemap.C1248b;
import com.baidu.platform.comjni.map.basemap.C1283a;
import com.baidu.platform.comjni.map.basemap.JNIBaseMap;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.protocol.HttpRequestExecutor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"NewApi"})
/* renamed from: com.baidu.platform.comapi.map.e */
public class C1249e implements C1248b {
    /* renamed from: N */
    private static int f3726N;
    /* renamed from: O */
    private static int f3727O;
    private static List<JNIBaseMap> aq;
    /* renamed from: k */
    static long f3728k = 0;
    /* renamed from: o */
    private static final String f3729o = C1255j.class.getSimpleName();
    /* renamed from: A */
    private Context f3730A;
    /* renamed from: B */
    private List<C1228d> f3731B;
    /* renamed from: C */
    private C1230A f3732C;
    /* renamed from: D */
    private C1251g f3733D;
    /* renamed from: E */
    private C1259o f3734E;
    /* renamed from: F */
    private C1239H f3735F;
    /* renamed from: G */
    private C1241K f3736G;
    /* renamed from: H */
    private C1262s f3737H;
    /* renamed from: I */
    private C1258n f3738I;
    /* renamed from: J */
    private C1260p f3739J;
    /* renamed from: K */
    private C1245a f3740K;
    /* renamed from: L */
    private C1116q f3741L;
    /* renamed from: M */
    private C1240I f3742M;
    /* renamed from: P */
    private int f3743P;
    /* renamed from: Q */
    private int f3744Q;
    /* renamed from: R */
    private int f3745R;
    /* renamed from: S */
    private C1254a f3746S = new C1254a();
    /* renamed from: T */
    private VelocityTracker f3747T;
    /* renamed from: U */
    private long f3748U;
    /* renamed from: V */
    private long f3749V;
    /* renamed from: W */
    private long f3750W;
    /* renamed from: X */
    private long f3751X;
    /* renamed from: Y */
    private int f3752Y;
    /* renamed from: Z */
    private float f3753Z;
    /* renamed from: a */
    public float f3754a = 22.0f;
    private float aa;
    private boolean ab;
    private long ac;
    private long ad;
    private boolean ae = false;
    private boolean af = false;
    private float ag;
    private float ah;
    private float ai;
    private float aj;
    private long ak = 0;
    private long al = 0;
    private C1250f am;
    private String an;
    private C1246b ao;
    private C1247c ap;
    /* renamed from: b */
    public float f3755b = 3.0f;
    /* renamed from: c */
    public float f3756c = 22.0f;
    /* renamed from: d */
    boolean f3757d = true;
    /* renamed from: e */
    boolean f3758e = true;
    /* renamed from: f */
    List<C1114l> f3759f;
    /* renamed from: g */
    C1283a f3760g;
    /* renamed from: h */
    long f3761h;
    /* renamed from: i */
    boolean f3762i;
    /* renamed from: j */
    public int f3763j;
    /* renamed from: l */
    boolean f3764l;
    /* renamed from: m */
    boolean f3765m;
    /* renamed from: n */
    boolean f3766n;
    /* renamed from: p */
    private boolean f3767p;
    /* renamed from: q */
    private boolean f3768q;
    /* renamed from: r */
    private boolean f3769r = true;
    /* renamed from: s */
    private boolean f3770s = false;
    /* renamed from: t */
    private boolean f3771t = false;
    /* renamed from: u */
    private boolean f3772u = false;
    /* renamed from: v */
    private boolean f3773v = true;
    /* renamed from: w */
    private boolean f3774w = true;
    /* renamed from: x */
    private boolean f3775x = false;
    /* renamed from: y */
    private C1242M f3776y;
    /* renamed from: z */
    private C1118L f3777z;

    public C1249e(Context context, String str) {
        this.f3730A = context;
        this.f3759f = new ArrayList();
        this.an = str;
    }

    /* renamed from: N */
    private void m4648N() {
        if (this.f3771t || this.f3768q || this.f3767p || this.f3772u) {
            if (this.f3754a > 20.0f) {
                this.f3754a = 20.0f;
            }
            if (m4660D().f3678a > 20.0f) {
                C1235E D = m4660D();
                D.f3678a = 20.0f;
                m4681a(D);
                return;
            }
            return;
        }
        this.f3754a = this.f3756c;
    }

    /* renamed from: O */
    private boolean m4649O() {
        if (this.f3760g == null || !this.f3762i) {
            return true;
        }
        this.af = false;
        if (!this.f3757d) {
            return false;
        }
        long j = this.al - this.ak;
        float abs = (Math.abs(this.ai - this.ag) * 1000.0f) / ((float) j);
        float abs2 = (Math.abs(this.aj - this.ah) * 1000.0f) / ((float) j);
        abs2 = (float) Math.sqrt((double) ((abs2 * abs2) + (abs * abs)));
        if (abs2 <= 500.0f) {
            return false;
        }
        m4749z();
        m4671a(34, (int) (abs2 * 0.6f), (((int) this.aj) << 16) | ((int) this.ai));
        m4668L();
        return true;
    }

    /* renamed from: a */
    private Activity m4650a(Context context) {
        return context == null ? null : context instanceof Activity ? (Activity) context : context instanceof ContextWrapper ? m4650a(((ContextWrapper) context).getBaseContext()) : null;
    }

    /* renamed from: a */
    private void m4651a(String str, String str2, long j) {
        try {
            Class cls = Class.forName(str);
            Object newInstance = cls.newInstance();
            cls.getMethod(str2, new Class[]{Long.TYPE}).invoke(newInstance, new Object[]{Long.valueOf(j)});
        } catch (Exception e) {
        }
    }

    /* renamed from: e */
    private boolean m4652e(float f, float f2) {
        if (this.f3760g == null || !this.f3762i) {
            return true;
        }
        this.ae = false;
        GeoPoint b = m4696b((int) f, (int) f2);
        if (b == null) {
            return false;
        }
        for (C1114l b2 : this.f3759f) {
            b2.mo2634b(b);
        }
        if (!this.f3758e) {
            return false;
        }
        C1235E D = m4660D();
        D.f3678a += 1.0f;
        D.f3681d = b.getLongitudeE6();
        D.f3682e = b.getLatitudeE6();
        m4682a(D, 300);
        f3728k = System.currentTimeMillis();
        return true;
    }

    /* renamed from: e */
    private boolean m4653e(Bundle bundle) {
        return this.f3760g == null ? false : this.f3760g.m4912e(bundle);
    }

    /* renamed from: f */
    private boolean m4654f(Bundle bundle) {
        boolean z = false;
        if (!(bundle == null || this.f3760g == null)) {
            z = this.f3760g.m4908d(bundle);
            if (z) {
                m4705c(z);
                this.f3760g.m4894b(this.f3776y.a);
            }
        }
        return z;
    }

    /* renamed from: g */
    private void m4655g(Bundle bundle) {
        if (bundle.get(C0840a.f2030f) != null) {
            Bundle bundle2 = (Bundle) bundle.get(C0840a.f2030f);
            int i = bundle2.getInt("type");
            if (i == C1252h.ground.ordinal()) {
                bundle2.putLong("layer_addr", this.f3734E.a);
                return;
            } else if (i >= C1252h.arc.ordinal()) {
                bundle2.putLong("layer_addr", this.f3738I.a);
                return;
            } else if (i == C1252h.popup.ordinal()) {
                bundle2.putLong("layer_addr", this.f3737H.a);
                return;
            } else {
                bundle2.putLong("layer_addr", this.f3736G.a);
                return;
            }
        }
        int i2 = bundle.getInt("type");
        if (i2 == C1252h.ground.ordinal()) {
            bundle.putLong("layer_addr", this.f3734E.a);
        } else if (i2 >= C1252h.arc.ordinal()) {
            bundle.putLong("layer_addr", this.f3738I.a);
        } else if (i2 == C1252h.popup.ordinal()) {
            bundle.putLong("layer_addr", this.f3737H.a);
        } else {
            bundle.putLong("layer_addr", this.f3736G.a);
        }
    }

    /* renamed from: j */
    public static void m4656j(boolean z) {
        aq = C1283a.m4874d();
        if (aq == null || aq.size() == 0) {
            C1283a.m4873b(0, z);
            return;
        }
        C1283a.m4873b(((JNIBaseMap) aq.get(0)).f3923a, z);
        for (JNIBaseMap jNIBaseMap : aq) {
            jNIBaseMap.ClearLayer(jNIBaseMap.f3923a, -1);
        }
    }

    /* renamed from: A */
    void m4657A() {
        this.f3765m = false;
        this.f3764l = false;
        for (C1114l c : this.f3759f) {
            c.mo2639c(m4660D());
        }
    }

    /* renamed from: B */
    public boolean m4658B() {
        return this.f3760g != null ? this.f3760g.m4888a(this.f3735F.a) : false;
    }

    /* renamed from: C */
    public boolean m4659C() {
        return this.f3760g != null ? this.f3760g.m4888a(this.ap.a) : false;
    }

    /* renamed from: D */
    public C1235E m4660D() {
        if (this.f3760g == null) {
            return null;
        }
        Bundle j = this.f3760g.m4920j();
        C1235E c1235e = new C1235E();
        c1235e.m4617a(j);
        return c1235e;
    }

    /* renamed from: E */
    public LatLngBounds m4661E() {
        if (this.f3760g == null) {
            return null;
        }
        Bundle k = this.f3760g.m4921k();
        Builder builder = new Builder();
        int i = k.getInt("maxCoorx");
        int i2 = k.getInt("minCoorx");
        builder.include(CoordUtil.mc2ll(new GeoPoint((double) k.getInt("minCoory"), (double) i))).include(CoordUtil.mc2ll(new GeoPoint((double) k.getInt("maxCoory"), (double) i2)));
        return builder.build();
    }

    /* renamed from: F */
    public int m4662F() {
        return this.f3743P;
    }

    /* renamed from: G */
    public int m4663G() {
        return this.f3744Q;
    }

    /* renamed from: H */
    public C1235E m4664H() {
        if (this.f3760g == null) {
            return null;
        }
        Bundle l = this.f3760g.m4922l();
        C1235E c1235e = new C1235E();
        c1235e.m4617a(l);
        return c1235e;
    }

    /* renamed from: I */
    public double m4665I() {
        return m4660D().f3690m;
    }

    /* renamed from: J */
    void m4666J() {
        if (!this.f3764l) {
            this.f3764l = true;
            this.f3765m = false;
            for (C1114l a : this.f3759f) {
                a.mo2629a(m4660D());
            }
        }
    }

    /* renamed from: K */
    void m4667K() {
        this.f3764l = false;
        if (!this.f3765m) {
            for (C1114l c : this.f3759f) {
                c.mo2639c(m4660D());
            }
        }
    }

    /* renamed from: L */
    void m4668L() {
        this.f3745R = 0;
        this.f3746S.f3795e = false;
        this.f3746S.f3798h = 0.0d;
    }

    /* renamed from: M */
    void m4669M() {
        if (this.f3760g != null) {
            this.f3760g.m4897b();
            this.f3760g = null;
        }
    }

    /* renamed from: a */
    public float m4670a(int i, int i2, int i3, int i4, int i5, int i6) {
        if (!this.f3762i) {
            return 12.0f;
        }
        if (this.f3760g == null) {
            return 0.0f;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("left", i);
        bundle.putInt("right", i3);
        bundle.putInt(Property.TEXT_ANCHOR_BOTTOM, i4);
        bundle.putInt(Property.TEXT_ANCHOR_TOP, i2);
        bundle.putInt("hasHW", 1);
        bundle.putInt(Property.ICON_TEXT_FIT_WIDTH, i5);
        bundle.putInt(Property.ICON_TEXT_FIT_HEIGHT, i6);
        return this.f3760g.m4901c(bundle);
    }

    /* renamed from: a */
    int m4671a(int i, int i2, int i3) {
        return C1283a.m4872a(this.f3761h, i, i2, i3);
    }

    /* renamed from: a */
    public int mo2675a(Bundle bundle, long j, int i, Bundle bundle2) {
        if (j == this.f3733D.a) {
            bundle.putString("jsondata", this.f3733D.m4605a());
            bundle.putBundle(C0840a.f2030f, this.f3733D.m4608b());
            return this.f3733D.g;
        } else if (j == this.f3732C.a) {
            bundle.putString("jsondata", this.f3732C.m4605a());
            bundle.putBundle(C0840a.f2030f, this.f3732C.m4608b());
            return this.f3732C.g;
        } else if (j == this.f3739J.a) {
            bundle.putBundle(C0840a.f2030f, this.f3741L.mo2645a(bundle2.getInt("x"), bundle2.getInt("y"), bundle2.getInt(MapboxEvent.KEY_ZOOM)));
            return this.f3739J.g;
        } else if (j != this.f3776y.a) {
            return 0;
        } else {
            bundle.putBundle(C0840a.f2030f, this.f3777z.mo2646a(bundle2.getInt("x"), bundle2.getInt("y"), bundle2.getInt(MapboxEvent.KEY_ZOOM), this.f3730A));
            return this.f3776y.g;
        }
    }

    /* renamed from: a */
    public Point m4673a(GeoPoint geoPoint) {
        return this.f3742M.m4629a(geoPoint);
    }

    /* renamed from: a */
    void m4674a() {
        this.f3760g = new C1283a();
        this.f3760g.m4885a();
        this.f3761h = this.f3760g.m4902c();
        m4651a("com.baidu.platform.comapi.wnplatform.walkmap.WNaviBaiduMap", "setId", this.f3761h);
        if (SysOSUtil.getDensityDpi() < Opcodes.GETFIELD) {
            this.f3763j = 18;
        } else if (SysOSUtil.getDensityDpi() < 240) {
            this.f3763j = 25;
        } else if (SysOSUtil.getDensityDpi() < 320) {
            this.f3763j = 37;
        } else {
            this.f3763j = 50;
        }
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
        appSDCardPath = str3 + str;
        str2 = str3 + str;
        appCachePath = appCachePath + "/tmp/";
        appSecondCachePath = appSecondCachePath + "/tmp/";
        Activity a = m4650a(this.f3730A);
        if (a != null) {
            Display defaultDisplay = a.getWindowManager().getDefaultDisplay();
            this.f3760g.m4890a(moduleFileName, appSDCardPath, appCachePath, appSecondCachePath, str2, str4, this.an, str5, defaultDisplay.getWidth(), defaultDisplay.getHeight(), SysOSUtil.getDensityDpi(), mapTmpStgMax, domTmpStgMax, itsTmpStgMax, 0);
            this.f3760g.m4913f();
            return;
        }
        throw new RuntimeException("Please give the right context.");
    }

    /* renamed from: a */
    public void m4675a(float f, float f2) {
        this.f3754a = f;
        this.f3756c = f;
        this.f3755b = f2;
    }

    /* renamed from: a */
    void m4676a(int i, int i2) {
        this.f3743P = i;
        this.f3744Q = i2;
    }

    /* renamed from: a */
    public void m4677a(Bitmap bitmap) {
        if (this.f3760g != null) {
            Bundle bundle;
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject.put("type", 0);
                jSONObject2.put("x", f3726N);
                jSONObject2.put("y", f3727O);
                jSONObject2.put("hidetime", 1000);
                jSONArray.put(jSONObject2);
                jSONObject.put(C0861d.f2139k, jSONArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (bitmap == null) {
                bundle = null;
            } else {
                Bundle bundle2 = new Bundle();
                ArrayList arrayList = new ArrayList();
                ParcelItem parcelItem = new ParcelItem();
                Bundle bundle3 = new Bundle();
                Buffer allocate = ByteBuffer.allocate((bitmap.getWidth() * bitmap.getHeight()) * 4);
                bitmap.copyPixelsToBuffer(allocate);
                bundle3.putByteArray("imgdata", allocate.array());
                bundle3.putInt("imgindex", bitmap.hashCode());
                bundle3.putInt("imgH", bitmap.getHeight());
                bundle3.putInt("imgW", bitmap.getWidth());
                bundle3.putInt("hasIcon", 1);
                parcelItem.setBundle(bundle3);
                arrayList.add(parcelItem);
                if (arrayList.size() > 0) {
                    Parcelable[] parcelableArr = new ParcelItem[arrayList.size()];
                    for (int i = 0; i < arrayList.size(); i++) {
                        parcelableArr[i] = (ParcelItem) arrayList.get(i);
                    }
                    bundle2.putParcelableArray("icondata", parcelableArr);
                }
                bundle = bundle2;
            }
            m4702b(jSONObject.toString(), bundle);
            this.f3760g.m4894b(this.f3733D.a);
        }
    }

    /* renamed from: a */
    void m4678a(Handler handler) {
        MessageCenter.registMessage(m_AppUI.MSG_APP_SAVESCREEN, handler);
        MessageCenter.registMessage(39, handler);
        MessageCenter.registMessage(41, handler);
        MessageCenter.registMessage(49, handler);
        MessageCenter.registMessage(m_AppUI.V_WM_VDATAENGINE, handler);
        MessageCenter.registMessage(50, handler);
        MessageCenter.registMessage(AVException.UNKNOWN, handler);
        BaseMapCallback.addLayerDataInterface(this.f3761h, this);
    }

    /* renamed from: a */
    public void m4679a(LatLngBounds latLngBounds) {
        if (latLngBounds != null && this.f3760g != null) {
            LatLng latLng = latLngBounds.northeast;
            LatLng latLng2 = latLngBounds.southwest;
            GeoPoint ll2mc = CoordUtil.ll2mc(latLng);
            GeoPoint ll2mc2 = CoordUtil.ll2mc(latLng2);
            int longitudeE6 = (int) ll2mc.getLongitudeE6();
            int latitudeE6 = (int) ll2mc2.getLatitudeE6();
            int longitudeE62 = (int) ll2mc2.getLongitudeE6();
            int latitudeE62 = (int) ll2mc.getLatitudeE6();
            Bundle bundle = new Bundle();
            bundle.putInt("maxCoorx", longitudeE6);
            bundle.putInt("minCoory", latitudeE6);
            bundle.putInt("minCoorx", longitudeE62);
            bundle.putInt("maxCoory", latitudeE62);
            this.f3760g.m4895b(bundle);
        }
    }

    /* renamed from: a */
    void m4680a(C1232C c1232c) {
        C1235E c1235e = new C1235E();
        if (c1232c == null) {
            c1232c = new C1232C();
        }
        c1235e = c1232c.f3657a;
        this.f3773v = c1232c.f3662f;
        this.f3774w = c1232c.f3660d;
        this.f3757d = c1232c.f3661e;
        this.f3758e = c1232c.f3663g;
        this.f3760g.m4881a(c1235e.m4616a(this));
        this.f3760g.m4875a(C1231B.DEFAULT.ordinal());
        this.f3769r = c1232c.f3658b;
        if (c1232c.f3658b) {
            f3726N = (int) (SysOSUtil.getDensity() * 40.0f);
            f3727O = (int) (SysOSUtil.getDensity() * 40.0f);
            JSONObject jSONObject = new JSONObject();
            JSONArray jSONArray = new JSONArray();
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put("x", f3726N);
                jSONObject2.put("y", f3727O);
                jSONObject2.put("hidetime", 1000);
                jSONArray.put(jSONObject2);
                jSONObject.put(C0861d.f2139k, jSONArray);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            this.f3733D.m4607a(jSONObject.toString());
            this.f3760g.m4880a(this.f3733D.a, true);
        } else {
            this.f3760g.m4880a(this.f3733D.a, false);
        }
        int i = c1232c.f3659c;
        if (i == 2) {
            m4689a(true);
        }
        if (i == 3) {
            this.f3760g.m4880a(this.am.a, false);
        }
    }

    /* renamed from: a */
    public void m4681a(C1235E c1235e) {
        if (this.f3760g != null) {
            Bundle a = c1235e.m4616a(this);
            a.putInt("animation", 0);
            a.putInt("animatime", 0);
            this.f3760g.m4881a(a);
        }
    }

    /* renamed from: a */
    public void m4682a(C1235E c1235e, int i) {
        if (this.f3760g != null) {
            Bundle a = c1235e.m4616a(this);
            a.putInt("animation", 1);
            a.putInt("animatime", i);
            m4749z();
            this.f3760g.m4881a(a);
        }
    }

    /* renamed from: a */
    public void m4683a(C1118L c1118l) {
        this.f3777z = c1118l;
    }

    /* renamed from: a */
    void m4684a(C1228d c1228d) {
        if (this.f3760g != null) {
            c1228d.f3644a = this.f3760g.m4876a(c1228d.f3646c, c1228d.f3647d, c1228d.f3645b);
            this.f3731B.add(c1228d);
        }
    }

    /* renamed from: a */
    public void m4685a(C1114l c1114l) {
        this.f3759f.add(c1114l);
    }

    /* renamed from: a */
    public void m4686a(C1116q c1116q) {
        this.f3741L = c1116q;
    }

    /* renamed from: a */
    public void m4687a(String str, Bundle bundle) {
        if (this.f3760g != null) {
            this.f3732C.m4607a(str);
            this.f3732C.m4606a(bundle);
            this.f3760g.m4894b(this.f3732C.a);
        }
    }

    /* renamed from: a */
    public void m4688a(List<Bundle> list) {
        if (this.f3760g != null && list != null) {
            int size = list.size();
            Bundle[] bundleArr = new Bundle[list.size()];
            for (int i = 0; i < size; i++) {
                m4655g((Bundle) list.get(i));
                bundleArr[i] = (Bundle) list.get(i);
            }
            this.f3760g.m4884a(bundleArr);
        }
    }

    /* renamed from: a */
    public void m4689a(boolean z) {
        if (this.f3760g != null) {
            if (!this.f3760g.m4888a(this.am.a)) {
                this.f3760g.m4880a(this.am.a, true);
            }
            this.f3768q = z;
            m4648N();
            this.f3760g.m4883a(this.f3768q);
        }
    }

    /* renamed from: a */
    public boolean m4690a(float f, float f2, float f3, float f4) {
        float f5 = ((float) this.f3744Q) - f2;
        float f6 = ((float) this.f3744Q) - f4;
        if (this.f3746S.f3795e) {
            double sqrt;
            int log;
            int atan2;
            if (this.f3745R == 0) {
                if ((this.f3746S.f3793c - f5 <= 0.0f || this.f3746S.f3794d - f6 <= 0.0f) && (this.f3746S.f3793c - f5 >= 0.0f || this.f3746S.f3794d - f6 >= 0.0f)) {
                    this.f3745R = 2;
                } else {
                    sqrt = Math.sqrt((double) (((f3 - f) * (f3 - f)) + ((f6 - f5) * (f6 - f5)))) / this.f3746S.f3798h;
                    log = (int) ((Math.log(sqrt) / Math.log(2.0d)) * 10000.0d);
                    atan2 = (int) (((Math.atan2((double) (f6 - f5), (double) (f3 - f)) - Math.atan2((double) (this.f3746S.f3794d - this.f3746S.f3793c), (double) (this.f3746S.f3792b - this.f3746S.f3791a))) * 180.0d) / 3.1416d);
                    if ((sqrt <= 0.0d || (log <= HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE && log >= -3000)) && Math.abs(atan2) < 10) {
                        this.f3745R = 1;
                    } else {
                        this.f3745R = 2;
                    }
                }
                if (this.f3745R == 0) {
                    return true;
                }
            }
            if (this.f3745R == 1 && this.f3773v) {
                if (this.f3746S.f3793c - f5 > 0.0f && this.f3746S.f3794d - f6 > 0.0f) {
                    m4666J();
                    m4671a(1, 83, 0);
                } else if (this.f3746S.f3793c - f5 < 0.0f && this.f3746S.f3794d - f6 < 0.0f) {
                    m4666J();
                    m4671a(1, 87, 0);
                }
            } else if (this.f3745R == 2 || this.f3745R == 4 || this.f3745R == 3) {
                double atan22 = Math.atan2((double) (f6 - f5), (double) (f3 - f)) - Math.atan2((double) (this.f3746S.f3794d - this.f3746S.f3793c), (double) (this.f3746S.f3792b - this.f3746S.f3791a));
                sqrt = Math.sqrt((double) (((f3 - f) * (f3 - f)) + ((f6 - f5) * (f6 - f5)))) / this.f3746S.f3798h;
                log = (int) ((Math.log(sqrt) / Math.log(2.0d)) * 10000.0d);
                double atan23 = Math.atan2((double) (this.f3746S.f3797g - this.f3746S.f3793c), (double) (this.f3746S.f3796f - this.f3746S.f3791a));
                double sqrt2 = Math.sqrt((double) (((this.f3746S.f3796f - this.f3746S.f3791a) * (this.f3746S.f3796f - this.f3746S.f3791a)) + ((this.f3746S.f3797g - this.f3746S.f3793c) * (this.f3746S.f3797g - this.f3746S.f3793c))));
                float cos = (float) (((Math.cos(atan23 + atan22) * sqrt2) * sqrt) + ((double) f));
                float sin = (float) (((Math.sin(atan23 + atan22) * sqrt2) * sqrt) + ((double) f5));
                atan2 = (int) ((atan22 * 180.0d) / 3.1416d);
                if (sqrt > 0.0d && (3 == this.f3745R || (Math.abs(log) > m_AppUI.MSG_APP_DATA_OK && 2 == this.f3745R))) {
                    this.f3745R = 3;
                    float f7 = m4660D().f3678a;
                    if (this.f3758e) {
                        if (sqrt > 1.0d) {
                            if (f7 >= this.f3754a) {
                                return false;
                            }
                            m4666J();
                            m4671a((int) k_event.V_WM_ROTATE, 3, log);
                        } else if (f7 <= this.f3755b) {
                            return false;
                        } else {
                            m4666J();
                            m4671a((int) k_event.V_WM_ROTATE, 3, log);
                        }
                    }
                } else if (atan2 != 0 && (4 == this.f3745R || (Math.abs(atan2) > 10 && 2 == this.f3745R))) {
                    this.f3745R = 4;
                    if (this.f3774w) {
                        m4666J();
                        m4671a((int) k_event.V_WM_ROTATE, 1, atan2);
                    }
                }
                this.f3746S.f3796f = cos;
                this.f3746S.f3797g = sin;
            }
        }
        if (2 != this.f3745R) {
            this.f3746S.f3793c = f5;
            this.f3746S.f3794d = f6;
            this.f3746S.f3791a = f;
            this.f3746S.f3792b = f3;
        }
        if (!this.f3746S.f3795e) {
            this.f3746S.f3796f = (float) (this.f3743P / 2);
            this.f3746S.f3797g = (float) (this.f3744Q / 2);
            this.f3746S.f3795e = true;
            if (0.0d == this.f3746S.f3798h) {
                this.f3746S.f3798h = Math.sqrt((double) (((this.f3746S.f3792b - this.f3746S.f3791a) * (this.f3746S.f3792b - this.f3746S.f3791a)) + ((this.f3746S.f3794d - this.f3746S.f3793c) * (this.f3746S.f3794d - this.f3746S.f3793c))));
            }
        }
        return true;
    }

    /* renamed from: a */
    public boolean mo2676a(long j) {
        for (C1228d c1228d : this.f3731B) {
            if (c1228d.f3644a == j) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    public boolean m4692a(Point point) {
        if (point == null || this.f3760g == null || point.x < 0 || point.y < 0) {
            return false;
        }
        f3726N = point.x;
        f3727O = point.y;
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject2.put("x", f3726N);
            jSONObject2.put("y", f3727O);
            jSONObject2.put("hidetime", 1000);
            jSONArray.put(jSONObject2);
            jSONObject.put(C0861d.f2139k, jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.f3733D.m4607a(jSONObject.toString());
        this.f3760g.m4894b(this.f3733D.a);
        return true;
    }

    /* renamed from: a */
    public boolean m4693a(Bundle bundle) {
        if (this.f3760g == null) {
            return false;
        }
        this.f3776y = new C1242M();
        long a = this.f3760g.m4876a(this.f3776y.c, this.f3776y.d, this.f3776y.b);
        if (a == 0) {
            return false;
        }
        this.f3776y.a = a;
        this.f3731B.add(this.f3776y);
        bundle.putLong("sdktileaddr", a);
        return m4653e(bundle) && m4654f(bundle);
    }

    /* renamed from: a */
    boolean m4694a(MotionEvent motionEvent) {
        int pointerCount = motionEvent.getPointerCount();
        if (pointerCount == 2 && !(m4708c((int) motionEvent.getX(0), (int) motionEvent.getY(0)) && m4708c((int) motionEvent.getX(1), (int) motionEvent.getY(1)))) {
            pointerCount = 1;
        }
        if (pointerCount == 2) {
            float y = ((float) this.f3744Q) - motionEvent.getY(0);
            float y2 = ((float) this.f3744Q) - motionEvent.getY(1);
            float x = motionEvent.getX(0);
            float x2 = motionEvent.getX(1);
            switch (motionEvent.getAction()) {
                case 5:
                    this.f3749V = motionEvent.getEventTime();
                    this.f3752Y--;
                    break;
                case 6:
                    this.f3751X = motionEvent.getEventTime();
                    this.f3752Y++;
                    break;
                case 261:
                    this.f3748U = motionEvent.getEventTime();
                    this.f3752Y--;
                    break;
                case 262:
                    this.f3750W = motionEvent.getEventTime();
                    this.f3752Y++;
                    break;
            }
            if (this.f3747T == null) {
                this.f3747T = VelocityTracker.obtain();
            }
            this.f3747T.addMovement(motionEvent);
            int minimumFlingVelocity = ViewConfiguration.getMinimumFlingVelocity();
            this.f3747T.computeCurrentVelocity(1000, (float) ViewConfiguration.getMaximumFlingVelocity());
            float xVelocity = this.f3747T.getXVelocity(1);
            float yVelocity = this.f3747T.getYVelocity(1);
            float xVelocity2 = this.f3747T.getXVelocity(2);
            float yVelocity2 = this.f3747T.getYVelocity(2);
            if (Math.abs(xVelocity) > ((float) minimumFlingVelocity) || Math.abs(yVelocity) > ((float) minimumFlingVelocity) || Math.abs(xVelocity2) > ((float) minimumFlingVelocity) || Math.abs(yVelocity2) > ((float) minimumFlingVelocity)) {
                if (this.f3746S.f3795e) {
                    double sqrt;
                    int log;
                    if (this.f3745R == 0) {
                        if ((this.f3746S.f3793c - y <= 0.0f || this.f3746S.f3794d - y2 <= 0.0f) && (this.f3746S.f3793c - y >= 0.0f || this.f3746S.f3794d - y2 >= 0.0f)) {
                            this.f3745R = 2;
                        } else {
                            sqrt = Math.sqrt((double) (((x2 - x) * (x2 - x)) + ((y2 - y) * (y2 - y)))) / this.f3746S.f3798h;
                            log = (int) ((Math.log(sqrt) / Math.log(2.0d)) * 10000.0d);
                            minimumFlingVelocity = (int) (((Math.atan2((double) (y2 - y), (double) (x2 - x)) - Math.atan2((double) (this.f3746S.f3794d - this.f3746S.f3793c), (double) (this.f3746S.f3792b - this.f3746S.f3791a))) * 180.0d) / 3.1416d);
                            if ((sqrt <= 0.0d || (log <= HttpRequestExecutor.DEFAULT_WAIT_FOR_CONTINUE && log >= -3000)) && Math.abs(minimumFlingVelocity) < 10) {
                                this.f3745R = 1;
                            } else {
                                this.f3745R = 2;
                            }
                        }
                        if (this.f3745R == 0) {
                            return true;
                        }
                    }
                    if (this.f3745R == 1 && this.f3773v) {
                        if (this.f3746S.f3793c - y > 0.0f && this.f3746S.f3794d - y2 > 0.0f) {
                            m4666J();
                            m4671a(1, 83, 0);
                        } else if (this.f3746S.f3793c - y < 0.0f && this.f3746S.f3794d - y2 < 0.0f) {
                            m4666J();
                            m4671a(1, 87, 0);
                        }
                    } else if (this.f3745R == 2 || this.f3745R == 4 || this.f3745R == 3) {
                        double atan2 = Math.atan2((double) (y2 - y), (double) (x2 - x)) - Math.atan2((double) (this.f3746S.f3794d - this.f3746S.f3793c), (double) (this.f3746S.f3792b - this.f3746S.f3791a));
                        sqrt = Math.sqrt((double) (((x2 - x) * (x2 - x)) + ((y2 - y) * (y2 - y)))) / this.f3746S.f3798h;
                        log = (int) ((Math.log(sqrt) / Math.log(2.0d)) * 10000.0d);
                        double atan22 = Math.atan2((double) (this.f3746S.f3797g - this.f3746S.f3793c), (double) (this.f3746S.f3796f - this.f3746S.f3791a));
                        double sqrt2 = Math.sqrt((double) (((this.f3746S.f3796f - this.f3746S.f3791a) * (this.f3746S.f3796f - this.f3746S.f3791a)) + ((this.f3746S.f3797g - this.f3746S.f3793c) * (this.f3746S.f3797g - this.f3746S.f3793c))));
                        float cos = (float) (((Math.cos(atan22 + atan2) * sqrt2) * sqrt) + ((double) x));
                        float sin = (float) (((Math.sin(atan22 + atan2) * sqrt2) * sqrt) + ((double) y));
                        minimumFlingVelocity = (int) ((atan2 * 180.0d) / 3.1416d);
                        if (sqrt > 0.0d && (3 == this.f3745R || (Math.abs(log) > m_AppUI.MSG_APP_DATA_OK && 2 == this.f3745R))) {
                            this.f3745R = 3;
                            float f = m4660D().f3678a;
                            if (this.f3758e) {
                                if (sqrt > 1.0d) {
                                    if (f >= this.f3754a) {
                                        return false;
                                    }
                                    m4666J();
                                    m4671a((int) k_event.V_WM_ROTATE, 3, log);
                                } else if (f <= this.f3755b) {
                                    return false;
                                } else {
                                    m4666J();
                                    m4671a((int) k_event.V_WM_ROTATE, 3, log);
                                }
                            }
                        } else if (minimumFlingVelocity != 0 && (4 == this.f3745R || (Math.abs(minimumFlingVelocity) > 10 && 2 == this.f3745R))) {
                            this.f3745R = 4;
                            if (this.f3774w) {
                                m4666J();
                                m4671a((int) k_event.V_WM_ROTATE, 1, minimumFlingVelocity);
                            }
                        }
                        this.f3746S.f3796f = cos;
                        this.f3746S.f3797g = sin;
                    }
                }
            } else if (this.f3745R == 0 && this.f3752Y == 0) {
                this.f3750W = this.f3750W > this.f3751X ? this.f3750W : this.f3751X;
                this.f3748U = this.f3748U < this.f3749V ? this.f3749V : this.f3748U;
                if (this.f3750W - this.f3748U < 200 && this.f3758e) {
                    C1235E D = m4660D();
                    if (D != null) {
                        D.f3678a -= 1.0f;
                        m4682a(D, 300);
                    }
                }
            }
            if (2 != this.f3745R) {
                this.f3746S.f3793c = y;
                this.f3746S.f3794d = y2;
                this.f3746S.f3791a = x;
                this.f3746S.f3792b = x2;
            }
            if (!this.f3746S.f3795e) {
                this.f3746S.f3796f = (float) (this.f3743P / 2);
                this.f3746S.f3797g = (float) (this.f3744Q / 2);
                this.f3746S.f3795e = true;
                if (0.0d == this.f3746S.f3798h) {
                    this.f3746S.f3798h = Math.sqrt((double) (((this.f3746S.f3792b - this.f3746S.f3791a) * (this.f3746S.f3792b - this.f3746S.f3791a)) + ((this.f3746S.f3794d - this.f3746S.f3793c) * (this.f3746S.f3794d - this.f3746S.f3793c))));
                }
            }
            return true;
        }
        switch (motionEvent.getAction()) {
            case 0:
                m4701b(motionEvent);
                return true;
            case 1:
                return m4714d(motionEvent);
            case 2:
                m4709c(motionEvent);
                return true;
            default:
                return false;
        }
    }

    /* renamed from: a */
    public boolean m4695a(String str, String str2) {
        return this.f3760g.m4889a(str, str2);
    }

    /* renamed from: b */
    public GeoPoint m4696b(int i, int i2) {
        return this.f3742M.m4630a(i, i2);
    }

    /* renamed from: b */
    void m4697b() {
        this.f3731B = new ArrayList();
        this.am = new C1250f();
        m4684a(this.am);
        this.ao = new C1246b();
        m4684a(this.ao);
        if (this.f3760g != null) {
            this.f3760g.m4911e(false);
        }
        this.f3734E = new C1259o();
        m4684a(this.f3734E);
        this.f3739J = new C1260p();
        m4684a(this.f3739J);
        this.f3740K = new C1245a();
        m4684a(this.f3740K);
        m4684a(new C1261r());
        this.f3735F = new C1239H();
        m4684a(this.f3735F);
        this.ap = new C1247c();
        m4684a(this.ap);
        this.f3738I = new C1258n();
        m4684a(this.f3738I);
        this.f3736G = new C1241K();
        m4684a(this.f3736G);
        this.f3733D = new C1251g();
        m4684a(this.f3733D);
        this.f3732C = new C1230A();
        m4684a(this.f3732C);
        this.f3737H = new C1262s();
        m4684a(this.f3737H);
    }

    /* renamed from: b */
    void m4698b(float f, float f2) {
        if (!this.f3746S.f3795e) {
            this.ad = System.currentTimeMillis();
            if (this.ad - this.ac >= 400) {
                this.ac = this.ad;
            } else if (Math.abs(f - this.f3753Z) >= 120.0f || Math.abs(f2 - this.aa) >= 120.0f) {
                this.ac = this.ad;
            } else {
                this.ac = 0;
                this.ae = true;
            }
            this.f3753Z = f;
            this.aa = f2;
            m4671a(4, 0, ((int) f) | (((int) f2) << 16));
            this.ab = true;
        }
    }

    /* renamed from: b */
    public void m4699b(Bundle bundle) {
        if (this.f3760g != null) {
            m4655g(bundle);
            this.f3760g.m4914f(bundle);
        }
    }

    /* renamed from: b */
    void m4700b(Handler handler) {
        MessageCenter.unregistMessage(m_AppUI.MSG_APP_SAVESCREEN, handler);
        MessageCenter.unregistMessage(41, handler);
        MessageCenter.unregistMessage(49, handler);
        MessageCenter.unregistMessage(39, handler);
        MessageCenter.unregistMessage(m_AppUI.V_WM_VDATAENGINE, handler);
        MessageCenter.unregistMessage(50, handler);
        MessageCenter.unregistMessage(AVException.UNKNOWN, handler);
        BaseMapCallback.removeLayerDataInterface(this.f3761h);
    }

    /* renamed from: b */
    void m4701b(MotionEvent motionEvent) {
        if (!this.f3746S.f3795e) {
            this.ad = motionEvent.getDownTime();
            if (this.ad - this.ac >= 400) {
                this.ac = this.ad;
            } else if (Math.abs(motionEvent.getX() - this.f3753Z) >= 120.0f || Math.abs(motionEvent.getY() - this.aa) >= 120.0f) {
                this.ac = this.ad;
            } else {
                this.ac = 0;
            }
            this.f3753Z = motionEvent.getX();
            this.aa = motionEvent.getY();
            m4671a(4, 0, ((int) motionEvent.getX()) | (((int) motionEvent.getY()) << 16));
            this.ab = true;
        }
    }

    /* renamed from: b */
    public void m4702b(String str, Bundle bundle) {
        if (this.f3760g != null) {
            this.f3733D.m4607a(str);
            this.f3733D.m4606a(bundle);
            this.f3760g.m4894b(this.f3733D.a);
        }
    }

    /* renamed from: b */
    public void m4703b(boolean z) {
        this.f3775x = z;
    }

    /* renamed from: c */
    public void m4704c(Bundle bundle) {
        if (this.f3760g != null) {
            m4655g(bundle);
            this.f3760g.m4916g(bundle);
        }
    }

    /* renamed from: c */
    public void m4705c(boolean z) {
        if (this.f3760g != null) {
            this.f3760g.m4880a(this.f3776y.a, z);
        }
    }

    /* renamed from: c */
    public boolean m4706c() {
        return this.f3775x;
    }

    /* renamed from: c */
    boolean m4707c(float f, float f2) {
        if (this.f3746S.f3795e) {
            return true;
        }
        if (System.currentTimeMillis() - f3728k < 300) {
            return true;
        }
        if (this.f3766n) {
            for (C1114l d : this.f3759f) {
                d.mo2641d(m4696b((int) f, (int) f2));
            }
            return true;
        }
        float abs = Math.abs(f - this.f3753Z);
        float abs2 = Math.abs(f2 - this.aa);
        float density = (float) (((double) SysOSUtil.getDensity()) > 1.5d ? ((double) SysOSUtil.getDensity()) * 1.5d : (double) SysOSUtil.getDensity());
        if (this.ab && abs / density <= 3.0f && abs2 / density <= 3.0f) {
            return true;
        }
        this.ab = false;
        int i = (int) f;
        int i2 = (int) f2;
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (!this.f3757d) {
            return false;
        }
        this.ag = this.ai;
        this.ah = this.aj;
        this.ai = f;
        this.aj = f2;
        this.ak = this.al;
        this.al = System.currentTimeMillis();
        this.af = true;
        m4666J();
        m4671a(3, 0, (i2 << 16) | i);
        return false;
    }

    /* renamed from: c */
    boolean m4708c(int i, int i2) {
        return i >= 0 && i <= this.f3743P + 0 && i2 >= 0 && i2 <= this.f3744Q + 0;
    }

    /* renamed from: c */
    boolean m4709c(MotionEvent motionEvent) {
        if (this.f3746S.f3795e) {
            return true;
        }
        if (System.currentTimeMillis() - f3728k < 300) {
            return true;
        }
        if (this.f3766n) {
            for (C1114l d : this.f3759f) {
                d.mo2641d(m4696b((int) motionEvent.getX(), (int) motionEvent.getY()));
            }
            return true;
        }
        float abs = Math.abs(motionEvent.getX() - this.f3753Z);
        float abs2 = Math.abs(motionEvent.getY() - this.aa);
        float density = (float) (((double) SysOSUtil.getDensity()) > 1.5d ? ((double) SysOSUtil.getDensity()) * 1.5d : (double) SysOSUtil.getDensity());
        if (this.ab && abs / density <= 3.0f && abs2 / density <= 3.0f) {
            return true;
        }
        this.ab = false;
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (!this.f3757d) {
            return false;
        }
        m4666J();
        m4671a(3, 0, (y << 16) | x);
        return false;
    }

    /* renamed from: d */
    public void m4710d(Bundle bundle) {
        if (this.f3760g != null) {
            m4655g(bundle);
            this.f3760g.m4918h(bundle);
        }
    }

    /* renamed from: d */
    public void m4711d(boolean z) {
        if (this.f3760g != null) {
            this.f3760g.m4880a(this.am.a, z);
        }
    }

    /* renamed from: d */
    public boolean m4712d() {
        return (this.f3776y == null || this.f3760g == null) ? false : this.f3760g.m4905c(this.f3776y.a);
    }

    /* renamed from: d */
    boolean m4713d(float f, float f2) {
        if (this.f3766n) {
            for (C1114l e : this.f3759f) {
                e.mo2643e(m4696b((int) f, (int) f2));
            }
            this.f3766n = false;
            return true;
        }
        if (!this.f3746S.f3795e) {
            if (this.ae) {
                return m4652e(f, f2);
            }
            if (this.af) {
                return m4649O();
            }
            if (System.currentTimeMillis() - this.ad < 400 && Math.abs(f - this.f3753Z) < 10.0f && Math.abs(f2 - this.aa) < 10.0f) {
                m4668L();
                return true;
            }
        }
        m4668L();
        int i = (int) f;
        int i2 = (int) f2;
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        m4671a(5, 0, (i2 << 16) | i);
        return true;
    }

    /* renamed from: d */
    boolean m4714d(MotionEvent motionEvent) {
        if (this.f3766n) {
            for (C1114l e : this.f3759f) {
                e.mo2643e(m4696b((int) motionEvent.getX(), (int) motionEvent.getY()));
            }
            this.f3766n = false;
            return true;
        }
        boolean z = !this.f3746S.f3795e && motionEvent.getEventTime() - this.ad < 400 && Math.abs(motionEvent.getX() - this.f3753Z) < 10.0f && Math.abs(motionEvent.getY() - this.aa) < 10.0f;
        m4668L();
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        if (z) {
            return false;
        }
        if (x < 0) {
            x = 0;
        }
        m4671a(5, 0, ((y < 0 ? 0 : y) << 16) | x);
        return true;
    }

    /* renamed from: e */
    void m4715e() {
        if (this.f3760g != null) {
            this.f3742M = new C1240I(this.f3760g);
        }
    }

    /* renamed from: e */
    public void m4716e(boolean z) {
        if (this.f3760g != null) {
            this.f3772u = z;
            this.f3760g.m4896b(this.f3772u);
        }
    }

    /* renamed from: f */
    public void m4717f(boolean z) {
        if (this.f3760g != null) {
            this.f3767p = z;
            this.f3760g.m4904c(this.f3767p);
        }
    }

    /* renamed from: f */
    public boolean m4718f() {
        return this.f3767p;
    }

    /* renamed from: g */
    public String m4719g() {
        return this.f3760g == null ? null : this.f3760g.m4909e(this.f3733D.a);
    }

    /* renamed from: g */
    public void m4720g(boolean z) {
        if (this.f3760g != null) {
            this.f3760g.m4907d(z);
        }
    }

    /* renamed from: h */
    public void m4721h(boolean z) {
        if (this.f3760g != null) {
            this.f3769r = z;
            this.f3760g.m4880a(this.f3733D.a, z);
        }
    }

    /* renamed from: h */
    public boolean m4722h() {
        return this.f3772u;
    }

    /* renamed from: i */
    public void m4723i(boolean z) {
        this.f3760g.m4911e(z);
        this.f3760g.m4906d(this.ao.a);
        this.f3760g.m4906d(this.ap.a);
    }

    /* renamed from: i */
    public boolean m4724i() {
        return this.f3760g == null ? false : this.f3760g.m4923m();
    }

    /* renamed from: j */
    public boolean m4725j() {
        return this.f3768q;
    }

    /* renamed from: k */
    public void m4726k(boolean z) {
        if (this.f3760g != null) {
            this.f3770s = z;
            this.f3760g.m4880a(this.f3732C.a, z);
        }
    }

    /* renamed from: k */
    public boolean m4727k() {
        return this.f3760g.m4888a(this.am.a);
    }

    /* renamed from: l */
    public void m4728l(boolean z) {
        if (this.f3760g != null) {
            this.f3771t = z;
            this.f3760g.m4880a(this.f3739J.a, z);
        }
    }

    /* renamed from: l */
    public boolean m4729l() {
        return this.f3760g == null ? false : this.f3760g.m4927q();
    }

    /* renamed from: m */
    public void m4730m() {
        if (this.f3760g != null) {
            this.f3760g.m4906d(this.f3734E.a);
            this.f3760g.m4906d(this.f3738I.a);
            this.f3760g.m4906d(this.f3736G.a);
            this.f3760g.m4906d(this.f3737H.a);
        }
    }

    /* renamed from: m */
    public void m4731m(boolean z) {
        this.f3757d = z;
    }

    /* renamed from: n */
    public void m4732n() {
        if (this.f3760g != null) {
            this.f3760g.m4928r();
            this.f3760g.m4894b(this.f3739J.a);
        }
    }

    /* renamed from: n */
    public void m4733n(boolean z) {
        this.f3758e = z;
    }

    /* renamed from: o */
    public MapBaseIndoorMapInfo m4734o() {
        return this.f3760g.m4929s();
    }

    /* renamed from: o */
    public void m4735o(boolean z) {
        this.f3774w = z;
    }

    /* renamed from: p */
    public void m4736p(boolean z) {
        this.f3773v = z;
    }

    /* renamed from: p */
    public boolean m4737p() {
        return this.f3760g.m4930t();
    }

    /* renamed from: q */
    public void m4738q(boolean z) {
        if (this.f3760g != null) {
            this.f3760g.m4880a(this.f3735F.a, z);
        }
    }

    /* renamed from: q */
    public boolean m4739q() {
        return this.f3769r;
    }

    /* renamed from: r */
    public void m4740r(boolean z) {
        if (this.f3760g != null) {
            this.f3760g.m4880a(this.ap.a, z);
        }
    }

    /* renamed from: r */
    public boolean m4741r() {
        return this.f3770s;
    }

    /* renamed from: s */
    public void m4742s() {
        if (this.f3760g != null) {
            this.f3760g.m4894b(this.f3739J.a);
        }
    }

    /* renamed from: t */
    public void m4743t() {
        if (this.f3760g != null) {
            this.f3760g.m4915g();
        }
    }

    /* renamed from: u */
    public void m4744u() {
        if (this.f3760g != null) {
            this.f3760g.m4917h();
        }
    }

    /* renamed from: v */
    public boolean m4745v() {
        return this.f3757d;
    }

    /* renamed from: w */
    public boolean m4746w() {
        return this.f3758e;
    }

    /* renamed from: x */
    public boolean m4747x() {
        return this.f3774w;
    }

    /* renamed from: y */
    public boolean m4748y() {
        return this.f3773v;
    }

    /* renamed from: z */
    void m4749z() {
        if (!this.f3764l && !this.f3765m) {
            this.f3765m = true;
            for (C1114l a : this.f3759f) {
                a.mo2629a(m4660D());
            }
        }
    }
}
